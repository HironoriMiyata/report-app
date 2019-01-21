package com.example.gensounosakura.report_app_free.View

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.gensounosakura.report_app_free.Model.DB.TreatData.GetPersonData
import com.example.gensounosakura.report_app_free.Model.DB.TreatData.SetPersonData
import com.example.gensounosakura.report_app_free.R
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        val person = SetPersonData(this)
        val getPersonData = GetPersonData(this)
        var id = getPersonData.getLastId() + 1
        var name = getPersonData.getLastName()
        var addr = getPersonData.getLastAddr()
        var headerFixedPhrase = getPersonData.getLastHeadFixedPhrase()
        var footerFixedPhrase = getPersonData.getLastFooterFixedPhrase()

        Stetho.initialize(
            Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build())

        editTextName.setText(name)
        editAddr.setText(addr)
        editHeaderFixedPhrase.setText(headerFixedPhrase)
        editFooterFixedPhrase.setText(footerFixedPhrase)

        save.setOnClickListener {
            name = editTextName.text.toString()
            addr = editAddr.text.toString()
            headerFixedPhrase = editHeaderFixedPhrase.text.toString()
            footerFixedPhrase = editFooterFixedPhrase.text.toString()
            person.setPersonData(id,name,addr,headerFixedPhrase,footerFixedPhrase)
            id++
        }
    }
}