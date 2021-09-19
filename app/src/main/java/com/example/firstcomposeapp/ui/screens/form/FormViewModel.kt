package com.example.firstcomposeapp.ui.screens.form

import android.util.Patterns
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class FormViewModel : ViewModel() {

    val emailAddress = MutableLiveData("")
    val phone = MutableLiveData("")

    val valid = MediatorLiveData<Boolean>().apply {
        var isEmailValid = false
        var isPhoneValid = false
        fun combineValues() {
            value = isEmailValid && isPhoneValid
        }

        addSource(emailAddress) {
            isEmailValid = it.isValidEmail()
            combineValues()
        }
        addSource(phone) {
            isPhoneValid = it.isValidPhone()
            combineValues()
        }
    }

    private fun String.isValidEmail(): Boolean {
        return length > 0 && Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    private fun String.isValidPhone(): Boolean {
        return length > 0 && Pattern.compile("^(5|05)[0-9]{8}").matcher(this).matches()
    }
}