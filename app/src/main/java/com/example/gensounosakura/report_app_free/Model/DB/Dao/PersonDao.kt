package com.example.gensounosakura.report_app_free.Model.DB.Dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.gensounosakura.report_app_free.Model.DB.Data.PersonData

@Dao
interface PersonDao {
    @Insert
    fun createPersonData(personData:PersonData)

    @Query("SELECT * FROM persondata")
    fun getAll(): LiveData<MutableList<PersonData>>

    @Query("SELECT personId FROM persondata  ORDER BY personId desc")
    fun getId():MutableList<Int>

    @Query("SELECT personName FROM persondata  ORDER BY personId desc")
    fun getName():MutableList<String>

    @Query("SELECT addr FROM persondata  ORDER BY personId desc")
    fun getAddr():MutableList<String>

    @Query("SELECT headerFixedPhrase FROM persondata  ORDER BY personId desc")
    fun getHeaderFixedPhrase():MutableList<String>

    @Query("SELECT footerFixedPhrase FROM persondata  ORDER BY personId desc")
    fun getFooterFixedPhrase():MutableList<String>
}