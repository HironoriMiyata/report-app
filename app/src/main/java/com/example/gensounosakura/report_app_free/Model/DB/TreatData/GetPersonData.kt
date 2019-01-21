package com.example.gensounosakura.report_app_free.Model.DB.TreatData

import android.content.Context

class GetPersonData(context: Context){
    val db = PersonDatebase.getInstance(context)!!
    fun getId():List<Int>{
        val idList = db.personDao().getId()
        idList.add(0)
        return idList
    }
    fun getLastId():Int{
        val idList = getId()
        return idList.get(0)
    }

    fun getName():List<String>{
        val nameList = db.personDao().getName()
        nameList.add("name")
        return nameList
    }

    fun getLastName():String{
        return  getName().get(0)
    }

    fun getAddr():List<String>{
        val addrList = db.personDao().getAddr()
        addrList.add("メール")
        return addrList
    }

    fun getLastAddr():String{
        return getAddr().get(0)
    }

    fun getHeadFixedPhrase():List<String>{
        val headList =db.personDao().getHeaderFixedPhrase()
        headList.add("")
        return headList
    }

    fun getLastHeadFixedPhrase():String{
        return getHeadFixedPhrase().get(0)
    }

    fun getFooterFixedPhrase():List<String>{
        val footerList = db.personDao().getFooterFixedPhrase()
        footerList.add("")
        return footerList
    }

    fun getLastFooterFixedPhrase():String{
        return getFooterFixedPhrase().get(0)
    }

}