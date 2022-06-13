package com.shivansh.officetask.views.fragments.drawerNavigationFragments

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.shivansh.officetask.databinding.FragmentChangeLanguageBinding
import java.util.*

class ChangeLanguageFragment : Fragment() {
    private lateinit var binding : FragmentChangeLanguageBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = FragmentChangeLanguageBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lnHindi.setOnClickListener{
            setLocale("hi")
            Toast.makeText(context,"App Language Changed In Hindi",Toast.LENGTH_SHORT).show()
        }

        binding.lnEnglish.setOnClickListener{
            setLocale("en")
            Toast.makeText(context,"App Language Changed In English",Toast.LENGTH_SHORT).show()
        }
    }

    private fun setLocale(language : String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val configuration = resources.configuration
        configuration.setLocale(locale)

        resources.updateConfiguration(configuration,resources.displayMetrics)
    }
}