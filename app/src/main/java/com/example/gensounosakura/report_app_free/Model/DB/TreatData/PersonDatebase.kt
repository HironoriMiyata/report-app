package com.example.gensounosakura.report_app_free.Model.DB.TreatData

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.gensounosakura.report_app_free.Model.DB.Dao.PersonDao
import com.example.gensounosakura.report_app_free.Model.DB.Dao.WorkDao
import com.example.gensounosakura.report_app_free.Model.DB.Data.PersonData
import com.example.gensounosakura.report_app_free.Model.DB.Data.WorkData

@Database(entities = [PersonData::class], version = 1,exportSchema = false)
abstract class PersonDatebase : RoomDatabase() {
    abstract fun personDao(): PersonDao

    companion object {
        private var instance:PersonDatebase? = null

        fun getInstance(context: Context): PersonDatebase? {
            if (instance == null) {
                synchronized(PersonDatebase::class) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        PersonDatebase::class.java, "person_DB.db").allowMainThreadQueries()
                        .build()
                }
            }
            return instance
        }
        fun destroyInstance() {
            instance = null
        }
    }

}
