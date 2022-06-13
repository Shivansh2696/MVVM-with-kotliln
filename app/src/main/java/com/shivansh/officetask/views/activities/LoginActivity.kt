package com.shivansh.officetask.views.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.officetask.roomDB.AppDatabase
import com.example.officetask.roomDB.User
import com.shivansh.officetask.R
import com.shivansh.officetask.databinding.ActivityMainBinding
import com.shivansh.officetask.repositories.UserDatabaseRepo
import com.shivansh.officetask.viewModelFactories.MainActivityViewModelFactory
import com.shivansh.officetask.viewModels.LoginActivityViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel : LoginActivityViewModel

    /*
    Checking user Login in shared preferences, if user was already loggedIn then data in sharedPreference for isLogin
    should be true, if true then user will be directly transfer to the HomeActivity otherwise user have to LogIn or signUp
     */
    override fun onStart() {
        super.onStart()
        val sharedPreferences: SharedPreferences = getSharedPreferences("loginData", Context.MODE_PRIVATE)
        val isLogin = sharedPreferences.getBoolean("isLogin", false)
        val userEmail = sharedPreferences.getString("userEmail","")
        /*
        Taking Already LoggedIn user data from sharedPreferences and transferring it to the next Activity
         */
        if (isLogin) {
            val intent = Intent(this, HomeActivity::class.java).apply {
                putExtra("userEmail",userEmail)
            }
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val dao = AppDatabase.getInstance(applicationContext).userDao()
        val repository = UserDatabaseRepo(dao)

        viewModel = ViewModelProvider(this,MainActivityViewModelFactory(repository))[LoginActivityViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        /*
        if userLiveData have data it means, users detail are valid and transferring user to the next Activity
         */
        viewModel.userLiveData.observe(this, Observer {
            if(it != null){
                saveLogin(it)
                val intent = Intent(this, HomeActivity::class.java).apply {
                    putExtra("userEmail",it.email)
                }
                startActivity(intent)
                this.finish()
            }
            else{
                Toast.makeText(this,"User Not Found, Please SignUp First",Toast.LENGTH_SHORT).show()
            }
        })

    /*
    SignUp button Click Response
     */
        viewModel.onSignUpResponse.observe(this, Observer {
            if(it) startActivity(Intent(this,SignUpActivity::class.java))})
    }

    /*
    Saving data in sharedPreferences after Login
     */
    private fun saveLogin(user : User) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("loginData", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor? = sharedPreferences.edit()
        editor?.putBoolean("isLogin", true)
        editor?.putString("userEmail",user.email)
        editor?.apply()
    }
}