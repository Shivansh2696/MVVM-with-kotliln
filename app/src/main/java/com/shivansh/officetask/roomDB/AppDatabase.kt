package com.example.officetask.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao

    companion object{
        private var INSTANCE : AppDatabase? = null


        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(context: Context) : AppDatabase {
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room
                        .databaseBuilder(context.applicationContext,AppDatabase::class.java,"userDatabase")
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}