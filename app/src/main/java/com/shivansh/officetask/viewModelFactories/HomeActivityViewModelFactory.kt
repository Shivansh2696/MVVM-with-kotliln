package com.shivansh.officetask.viewModelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shivansh.officetask.repositories.UserDatabaseRepo
import com.shivansh.officetask.viewModels.HomeActivityViewModel

class HomeActivityViewModelFactory(private val repository: UserDatabaseRepo,
                                   private val email: String?
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeActivityViewModel(repository,email) as T
    }
}