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

class SignUpViewModel(private val repository: UserDatabaseRepo) : ViewModel() {

    val name : MutableLiveData<String> = MutableLiveData()
    val email : MutableLiveData<String> = MutableLiveData()
    val password : MutableLiveData<String> = MutableLiveData()
    val phone : MutableLiveData<String> = MutableLiveData()
    val confirmPassword : MutableLiveData<String> = MutableLiveData()

    var userError : MutableLiveData<ErrorModel> = MutableLiveData()
    val signUpClickResponse : MutableLiveData<Boolean> = MutableLiveData()
    val onClickImageResponse : MutableLiveData<Boolean> = MutableLiveData()

    init {
        userError.value= ErrorModel()
        signUpClickResponse.value = false
        onClickImageResponse.value = false
    }

    fun onClickSignUp(){
        signUpClickResponse.value = validate()
    }

    fun onClickImage(){
        onClickImageResponse.value = true
    }

    fun insertUser(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.insertUser(user)
        }
    }

    private fun validate() : Boolean{
        val errorModel  = ErrorModel()

        val isValidName = TextUtils.isEmpty(name.value)
        val isValidEmail = Valid.isValidEmail(email.value.toString())
        val isValidPassword = Valid.isValidPassword(password.value.toString())
        val isValidPhone = Valid.isValidPhone(phone.value.toString())
        val isValidConfirmPassword = Valid.isConfirmPassword(password.value.toString(),confirmPassword.value.toString())

        if(isValidName){
            errorModel.nameErrorMessage = "Please Enter Name Here"
        }

        if(!isValidEmail){
            errorModel.emailErrorMessage = "Please Enter Valid Email"
        }
        if(!isValidPassword){
            errorModel.passwordErrorMessage = "Please Enter Valid Password"
        }
        if(!isValidPhone){
            errorModel.phoneErrorMessage = "Please Enter Valid Phone Number"
        }
        if(!isValidConfirmPassword){
            errorModel.confirmPasswordErrorMessage = "Please Confirm Password"
        }

        userError.value = errorModel
        return !isValidName && isValidEmail && isValidPassword && isValidPhone && isValidConfirmPassword
    }


}