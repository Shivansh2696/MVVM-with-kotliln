package com.shivansh.officetask.views.fragments.drawerNavigationFragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.shivansh.officetask.views.activities.LoginActivity

class LogoutFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logout()
    }

    private fun logout() {
        val sharedPreferences : SharedPreferences? =
            context?.getSharedPreferences("loginData", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
        editor?.putBoolean("isLogin",false)?.apply()

        startActivity(Intent(context, LoginActivity::class.java))
        activity?.finish()
    }
}