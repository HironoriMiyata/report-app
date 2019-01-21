package com.example.gensounosakura.report_app_free.View

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Looper
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.gensounosakura.report_app_free.Model.DB.TreatData.GetPersonData
import com.example.gensounosakura.report_app_free.R
import com.example.gensounosakura.report_app_free.Model.Timer
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import kotlinx.android.synthetic.main.activity_report.*
import permissions.dispatcher.*

@RuntimePermissions
class ReportActivity: AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    var latitude: Double = 0.0
    var longitude: Double = 0.0
    var txt1: String = ""
    var workNameTxt = ""
    var name =""
    var addr = ""
    var headPhrase = ""
    var foooterPhrase = ""
    lateinit var getPersonData:GetPersonData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)
        getPersonData = GetPersonData(this)
        name = getPersonData.getLastName()
        addr = getPersonData.getLastAddr()
        headPhrase = getPersonData.getLastHeadFixedPhrase()
        foooterPhrase = getPersonData.getLastFooterFixedPhrase()
        showContactsWithPermissionCheck()
        Departure.setOnClickListener {
            getLocation()
            setMailTxt("出勤")
        }
        StartWork.setOnClickListener {
            getLocation()
            setMailTxt("作業開始")
        }
        EndWork.setOnClickListener {
            getLocation()
            setMailTxt("作業終了")
        }
        GoingHome.setOnClickListener {
            getLocation()
            setMailTxt("帰宅")
        }
        go.setOnClickListener {
            callMailer()
        }
        autoInput.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) setPersonalData() else setNonPresonalData()
        }
        input.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) setInputData() else setNonInputData()
        }
        editName.setOnClickListener {
            name = editName.text.toString()
        }
        editMail.setOnClickListener {
            addr = editMail.text.toString()
        }
        workName.setOnClickListener {
            workNameTxt = workName.text.toString()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    fun showContacts() {
        getLocation()
    }

    @OnPermissionDenied(Manifest.permission.ACCESS_FINE_LOCATION)
    fun onContactsDenied() {
        Toast.makeText(this, "「許可しない」が選択されました\n現在地が必要な場合は許可をしてください", Toast.LENGTH_SHORT).show()
    }

    @OnShowRationale(Manifest.permission.ACCESS_FINE_LOCATION)
    fun showRationaleForContacts(request: PermissionRequest) {
        AlertDialog.Builder(this)
            .setPositiveButton("許可") { _, _ -> request.proceed() }
            .setNegativeButton("許可しない") { _, _ -> request.cancel() }
            .setCancelable(false)
            .setMessage("座標の取得のためには許可をする必要があります")
            .show()
    }

    @OnNeverAskAgain(Manifest.permission.ACCESS_FINE_LOCATION)
    fun onContactsNeverAskAgain() {
        Toast.makeText(this, "「今後表示しない」が選択されました", Toast.LENGTH_SHORT).show()
    }

    fun getLocation() {
        fusedLocationClient = FusedLocationProviderClient(this)

        // どのような取得方法を要求
        val locationRequest = LocationRequest().apply {
            // 精度重視(電力大)と省電力重視(精度低)を両立するため2種類の更新間隔を指定
            interval = 10000                                   // 最遅の更新間隔(但し正確ではない。)
            fastestInterval = 5000                             // 最短の更新間隔
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY  // 精度重視
        }

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                val location = locationResult?.lastLocation ?: return
                latitude = location.latitude
                longitude = location.longitude
            }
        }
        // 位置情報を更新
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())
    }


    fun setMailTxt(actionTxt: String) {
        val getUnixTime = Timer()
        val mailTxt =
            actionTxt + " 時間 " + getUnixTime.getToday() + "\n" + latitude.toString() + " " + longitude.toString()
        val Txt = txt1 + mailTxt + "\n"
        txt1 = Txt
        showMail.setText(txt1)
    }

    fun callMailer() {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, addr)
        startActivity(intent)
    }

    fun setPersonalData() {
        name = getPersonData.getLastName()
        addr = getPersonData.getLastAddr()
        editName.setText(name)
        editMail.setText(addr)
    }

    fun setNonPresonalData(){
        name = ""
        addr = ""
        editName.setText(name)
        editMail.setText(addr)
    }

    fun setInputData(){
        val mail = workNameTxt+"\n"+headPhrase+"\n"+txt1+foooterPhrase
        showMail.setText(mail)
    }

    fun setNonInputData(){
        val mail = name+"\n"+workNameTxt+"\n"+txt1
        showMail.setText(mail)
    }
}