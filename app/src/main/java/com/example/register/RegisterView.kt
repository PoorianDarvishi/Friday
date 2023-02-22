package com.example.register

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterView : ViewModel() {
    val fullName = MutableLiveData("")
    val username = MutableLiveData("")
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val reTypePassword = MutableLiveData("")
    val gender = MutableLiveData("")
    private val listEditText = listOf(fullName, username, email, password, reTypePassword, gender)
    fun checking(): Boolean {
        var result = true
        for (text in listEditText) {
            if (text.value == "") {
                result = false
                Log.d("TAG", "checking 1: $result")
            }
        }
        if (".com" !in email.value!! || "@" !in email.value!!) {
            Log.d("TAG", "checking 2: $result")
            result = false
        }
        if (password.value.toString() != reTypePassword.value.toString()) {
            result = false
        }
        return result
    }
}