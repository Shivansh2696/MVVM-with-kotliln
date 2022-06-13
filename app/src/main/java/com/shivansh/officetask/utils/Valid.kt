package com.shivansh.officetask.utils

import android.util.Patterns
import com.shivansh.officetask.utils.AppConstants.PASSWORD_PATTERN
import java.util.regex.Pattern

 object Valid  {
    val pattern = Pattern.compile(PASSWORD_PATTERN)

     fun isValidEmail(email : String) : Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password : String) : Boolean{
        return pattern.matcher(password).matches()
    }

    fun isValidPhone(phone : String) : Boolean{
        return phone.length == 10
    }
     fun isConfirmPassword(password: String,confirmPassword : String) :Boolean{
         return password == confirmPassword
     }
}