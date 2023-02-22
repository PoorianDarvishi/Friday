package com.example.register

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.*
import com.example.register.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {
    private val registerViewModel: RegisterView by activityViewModels()

    @SuppressLint("CommitPrefEdits")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val dataSave =
//            context?.getSharedPreferences("PREFERENCE_NAME", AppCompatActivity.MODE_PRIVATE)
//        val editData = dataSave!!.edit()
        FragmentMainBinding.bind(view).apply {
            registerView = registerViewModel
            lifecycleOwner = this@MainFragment
//            val textViewsInfo =
//                listOf(fullNameInfo, usernameInfo, emailInfo, passwordInfo, genderInfo)
            val textViews = listOf(fullName, username, email, password)
            btnRegister.setOnClickListener {
                registerViewModel.fullName.value = fullName.text.toString()
                registerViewModel.username.value = username.text.toString()
                registerViewModel.email.value = email.text.toString()
                registerViewModel.password.value = password.text.toString()
                registerViewModel.reTypePassword.value = reTypePassword.text.toString()
                if (genderGroup.checkedRadioButtonId != -1) {
                    val idGender = genderGroup.checkedRadioButtonId
                    val gender = genderGroup.findViewById<RadioButton>(idGender)
                    registerViewModel.gender.value = gender.tag.toString()
                }
//                for (textView in textViews) {
//                    val tag = textView.tag.toString()
//                    val text = textView.text.toString()
//                    if (text != "") {
//                        editData.putString(tag, text)
//                    }
            }
            btnShowInfo.setOnClickListener {
                if (registerViewModel.checking()) {
                    parentFragmentManager.commit {
                        setReorderingAllowed(true)
                        replace<InfoFragment>(R.id.fragment_container_view)
                    }
                }else{
                    Toast.makeText(requireContext(),"Your info is wrong",Toast.LENGTH_SHORT).show()
                }
//                for (textView in textViewsInfo) {
//                    val tag = textView.tag.toString()
//                    val text = textView.text.toString()
//                    textView.text = dataSave.getString(tag, text)
//                }
            }
        }
    }
}