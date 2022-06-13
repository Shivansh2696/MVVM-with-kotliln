package com.shivansh.officetask.repositories

import androidx.lifecycle.LiveData
import com.example.officetask.roomDB.User
import com.example.officetask.roomDB.UserDao

class UserDatabaseRepo(private val userDao: UserDao) {

    suspend fun getUser(email : String,password:String) : User {
        return userDao.getUser(email,password)
    }

     fun getUser(email: String?) : LiveData<User> {
        return userDao.getUser(email)
    }

    suspend fun insertUser(user: User){
        userDao.insertUser(user)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }
}