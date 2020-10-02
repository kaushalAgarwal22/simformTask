package com.example.weatherapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class User {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    @ColumnInfo(name = "name")
    var name: String? = null

    @ColumnInfo(name = "gender")
    var gender: String? = null


    @ColumnInfo(name = "dob")
    var dob: String? = null

    @ColumnInfo(name = "email")
    var email: String? = null

    @ColumnInfo(name = "phone")
    var phone: String? = null

    @ColumnInfo(name = "cell")
    var cell: String? = null

    @ColumnInfo(name = "address")
    var address: String? = null

    @ColumnInfo(name = "image")
    var image: String? = null

}