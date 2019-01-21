package com.example.gensounosakura.report_app_free.Model.DB.TreatData

import android.content.Context
import com.example.gensounosakura.report_app_free.Model.DB.Data.PersonData


class SetPersonData(context: Context){
    val db = PersonDatebase.getInstance(context)!!
    fun setPersonData(id:Int,name:String,addr:String,headerFixedPhrase:String,footerFixedPhrase:String){
        val personData = PersonData()
        personData.personId =  id
        personData.personName = name
        personData.addr = addr
        personData.headerFixedPhrase = headerFixedPhrase
        personData.footerFixedPhrase = footerFixedPhrase
        db.personDao().createPersonData(personData)
    }

}