package com.shivansh.officetask.views.activities

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.officetask.roomDB.AppDatabase
import com.example.officetask.roomDB.User
import com.shivansh.officetask.R
import com.shivansh.officetask.databinding.ActivityUpdateProfileDialogBinding
import com.shivansh.officetask.dialogs.ChoosePictureDialog
import com.shivansh.officetask.repositories.UserDatabaseRepo
import com.shivansh.officetask.utils.ImageConverter.convertImageToString
import com.shivansh.officetask.viewModelFactories.UpdateProfileViewModelFactory
import com.shivansh.officetask.viewModels.UpdateProfileViewModel

class UpdateProfileDialogActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUpdateProfileDialogBinding
    private lateinit var viewModel : UpdateProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_update_profile_dialog)
        val dao = AppDatabase.getInstance(applicationContext).userDao()
        val repository = UserDatabaseRepo(dao)
        viewModel = ViewModelProvider(this,UpdateProfileViewModelFactory(repository))[UpdateProfileViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val email :String? = intent.getStringExtra("emailFromHome")
        val password :String? = intent.getStringExtra("passwordFromHome")

        viewModel.onClickUpdateResponse.observe(this, Observer {
            if(it){
                val imageInString = convertImageToString(binding.ProfilePhoto)
                val user = User(email!!,imageInString,binding.etName.text.toString()
                    ,password!!,binding.etPhone.text.toString())
                viewModel.updateUser(user)
                Toast.makeText(this,"Updated Successfully",Toast.LENGTH_SHORT).show()
                this.finish()
            }
        })

        viewModel.onClickImageResponse.observe(this, Observer {
            if(it){
                ChoosePictureDialog(this).show()
            }
        })
    }

    override fun onRequestPermissionsResult(requestCode: Int,permissions: Array<String?>,grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 101) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera()
            } else
                Toast.makeText(this,"Camera Permission is Required To Use Camera", Toast.LENGTH_SHORT).show()
        }
        if (requestCode == 104){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openGallery()
            }else
                Toast.makeText(this,"Storage Permission is required to use Gallery", Toast.LENGTH_SHORT).show()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            103 -> if (resultCode == RESULT_OK) {
                val selectedImage = data?.data
                binding.ProfilePhoto.setImageURI(selectedImage)
            }
            102 -> if (resultCode == RESULT_OK) {
                val bundle = data?.extras
                val bitmapImage = bundle!!["data"] as Bitmap
                binding.ProfilePhoto.setImageBitmap(bitmapImage)
            }
        }
    }

    private fun openCamera() {
        val camera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(camera, 102)
    }

    private fun openGallery(){
        val pickPhoto = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(pickPhoto, 103)
    }

}