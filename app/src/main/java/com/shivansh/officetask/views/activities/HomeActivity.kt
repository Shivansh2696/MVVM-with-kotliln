package com.shivansh.officetask.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.officetask.roomDB.AppDatabase
import com.google.android.material.navigation.NavigationView
import com.shivansh.officetask.R
import com.shivansh.officetask.databinding.ActivityHomeBinding
import com.shivansh.officetask.repositories.UserDatabaseRepo
import com.shivansh.officetask.utils.ImageConverter.convertStringTOBitmap
import com.shivansh.officetask.viewModelFactories.HomeActivityViewModelFactory
import com.shivansh.officetask.viewModels.HomeActivityViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController : NavController
    private lateinit var viewModel : HomeActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =DataBindingUtil.setContentView(this,R.layout.activity_home)

        //Taking email From intent
        val email : String? = intent.getStringExtra("userEmail")

        //Creating Dao and Repository for ViewModelFactory
        val userDao = AppDatabase.getInstance(applicationContext).userDao()
        val repository = UserDatabaseRepo(userDao)

        //Initializing ViewModel with ViewModel Factory
        viewModel = ViewModelProvider(this,
            HomeActivityViewModelFactory(repository,email))[HomeActivityViewModel::class.java]

        setSupportActionBar(binding.appbarHome.toolbar)

        val view : NavigationView = findViewById(R.id.nav_view)
        val headerView : View = view.getHeaderView(0)

        val navUsername = headerView.findViewById<View>(R.id.userName) as TextView
        val navEmail = headerView.findViewById<View>(R.id.userEmail) as TextView
        val navPhoto = headerView.findViewById<View>(R.id.userImage) as ImageView
        val navEditButton = headerView.findViewById<View>(R.id.EditProfile) as ImageButton

        viewModel.getUser().observe(this, Observer { user ->
            "Name: ${user.name}".also { navUsername.text = it }
            "Email: ${user.email}".also { navEmail.text = it }
            navPhoto.setImageBitmap(convertStringTOBitmap(user.image))


            navEditButton.setOnClickListener{
                val intent = Intent(this,UpdateProfileDialogActivity::class.java).apply {
                    putExtra("emailFromHome",user.email)
                    putExtra("passwordFromHome",user.password)
                }
                startActivity(intent)
            }
        })

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        navController = findNavController(R.id.container)

        appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_entertainment, R.id.nav_settings
                                            ,R.id.nav_language), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.container)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}