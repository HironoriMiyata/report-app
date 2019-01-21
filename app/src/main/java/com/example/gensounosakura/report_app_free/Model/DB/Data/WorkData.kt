package com.example.gensounosakura.report_app_free.Model.DB.Data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class WorkData {
    @PrimaryKey
    var workId = 0
    var worksName = ""
    var goStringTime = ""
    var goUniTime = 0.0
    var startStringTime = ""
    var startUniTime = 0.0
    var endStringTime = ""
    var endUniTime = 0.0
    var gohomeStringTime = ""
    var gohomeUniTime = 0.0
    var log  = ""
}