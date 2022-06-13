package com.shivansh.officetask.dialogs

import android.app.Activity
import android.content.res.Configuration
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.shivansh.officetask.databinding.ChooseLanguageDialogBinding
import java.util.*

class ChooseLanguageDialog(activity : Activity) : BottomSheetDialog(activity) {

    private lateinit var binding : ChooseLanguageDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ChooseLanguageDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lnEnglish.setOnClickListener{
                setLocale("hi")
        }
    }

    private fun setLocale(language : String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val configuration = Configuration()
        configuration.locale = locale
        context.resources.updateConfiguration(configuration,context.resources.displayMetrics)
    }
}