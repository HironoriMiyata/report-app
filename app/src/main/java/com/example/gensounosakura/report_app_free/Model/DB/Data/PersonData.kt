package com.example.gensounosakura.report_app_free.Model.DB.Data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class PersonData {
    @PrimaryKey
    var personId = 0
    var personName = ""
    var addr = ""
    var headerFixedPhrase = ""
    var footerFixedPhrase = ""
}

