package com.shivansh.officetask.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.officetask.roomDB.User
import com.shivansh.officetask.repositories.UserDatabaseRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeActivityViewModel(private val repository: UserDatabaseRepo, private val Email: String?) : ViewModel() {

    init {
        getUser()
    }

    fun getUser() : LiveData<User>{
            return repository.getUser(Email)
        }
}