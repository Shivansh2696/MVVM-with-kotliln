package com.example.officetask.roomDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: User)

    @Query("select * from User_Table where email =:email and password =:password")
    suspend fun getUser(email : String, password : String) : User

    @Query("select * from User_Table where email =:email")
    fun getUser(email: String?) : LiveData<User>

    @Update
    suspend fun updateUser(user: User)
}