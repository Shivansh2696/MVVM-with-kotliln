package com.shivansh.officetask.viewModelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shivansh.officetask.repositories.UserDatabaseRepo
import com.shivansh.officetask.viewModels.UpdateProfileViewModel

class UpdateProfileViewModelFactory(private val repository : UserDatabaseRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UpdateProfileViewModel(repository) as T
    }
}