package com.example.officetask.roomDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "User_Table")
data class User(
    @PrimaryKey
    val email : String,

    @ColumnInfo(name = "Image")
    val image : String,

    @ColumnInfo(name = "Name")
    val name : String,

    @ColumnInfo(name = "Password")
    val password : String,

    @ColumnInfo(name = "Phone")
    val phone : String
) : Serializable

