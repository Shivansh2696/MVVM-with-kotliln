package com.shivansh.officetask.viewModels

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.officetask.models.ErrorModel
import com.example.officetask.roomDB.User
import com.shivansh.officetask.repositories.UserDatabaseRepo
import com.shivansh.officetask.utils.Valid
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateProfileViewModel(private val repository : UserDatabaseRepo) : ViewModel() {

    val name : MutableLiveData<String> = MutableLiveData()
    val phone : MutableLiveData<String> = MutableLiveData()
    var userError : MutableLiveData<ErrorModel> = MutableLiveData()
    val onClickUpdateResponse : MutableLiveData<Boolean> = MutableLiveData()
    val onClickImageResponse : MutableLiveData<Boolean> = MutableLiveData()

    init {
        userError.value= ErrorModel()
        onClickUpdateResponse.value = false
    }

    fun onClickUpdate(){
        onClickUpdateResponse.value = validate()
    }

    fun onClickImage(){
        onClickImageResponse.value = true
    }

    fun updateUser(user : User){
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateUser(user)
        }
    }

    fun validate() : Boolean{
        val errorModel  = ErrorModel()

        val isValidName = TextUtils.isEmpty(name.value)
        val isValidPhone = Valid.isValidPhone(phone.value.toString())

        if(isValidName){
            errorModel.nameErrorMessage = "Please Enter Name Here"
        }
        if(!isValidPhone){
            errorModel.phoneErrorMessage = "Please Enter Valid Phone Number"
        }

        userError.value = errorModel
        return !isValidName && isValidPhone
    }
}