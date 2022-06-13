package com.shivansh.officetask.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.officetask.models.ErrorModel
import com.example.officetask.roomDB.User
import com.shivansh.officetask.repositories.UserDatabaseRepo
import com.shivansh.officetask.utils.Valid
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivityViewModel(private val repository : UserDatabaseRepo) : ViewModel() {
    val errorModel : MutableLiveData<ErrorModel> = MutableLiveData()
    var email: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()
    var userLiveData : MutableLiveData<User>  = MutableLiveData()
    val onSignUpResponse : MutableLiveData<Boolean> = MutableLiveData()

    init {
        errorModel.value = ErrorModel()
        onSignUpResponse.value = false
    }

    fun onClickLogin(){
        if (validate()){
            getUser(email.value.toString(), password.value.toString())
        }
    }

    private fun getUser(email: String, password: String){
        CoroutineScope(Dispatchers.IO).launch {
            val user : User = repository.getUser(email, password)
            withContext(Dispatchers.Main) {
                userLiveData.value = user
            }
        }
    }

    private fun validate() : Boolean{
        val error : ErrorModel =ErrorModel()
        val isValidEmail = Valid.isValidEmail(email.value.toString())
        val isValidPassword = Valid.isValidPassword(password.value.toString())

        if(!isValidEmail){
           error.emailErrorMessage = "Please Enter Valid Email"
        }
        if(!isValidPassword){
            error.passwordErrorMessage = "Please Enter Valid Password"
        }
        errorModel.value = error

        return  isValidEmail && isValidPassword
    }

    fun onClickSignUp(){
        onSignUpResponse.value = true
    }
}