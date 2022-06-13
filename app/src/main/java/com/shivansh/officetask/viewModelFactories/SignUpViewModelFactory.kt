package com.example.officetask.views.signUpActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shivansh.officetask.repositories.UserDatabaseRepo
import com.shivansh.officetask.viewModels.SignUpViewModel

class SignUpViewModelFactory(private val repository: UserDatabaseRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignUpViewModel(repository) as T
    }
}