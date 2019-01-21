package com.example.gensounosakura.report_app_free.Model.DB.Dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.gensounosakura.report_app_free.Model.DB.Data.WorkData

@Dao
interface WorkDao {
    @Insert
    fun createWorkData(workData:WorkData)

    @Query("SELECT * FROM workdata")
    fun getAll(): LiveData<MutableList<WorkData>>


}