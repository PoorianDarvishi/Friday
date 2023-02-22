package com.example.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.register.databinding.FragmentInfoBinding


class InfoFragment : Fragment(R.layout.fragment_info) {
    val viewModel : RegisterView by activityViewModels()
    lateinit var binding: FragmentInfoBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInfoBinding.bind(view)
        binding.registerView = viewModel
        binding.lifecycleOwner = this
        binding.btnHideInfo.setOnClickListener{
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace<MainFragment>(R.id.fragment_container_view)
            }
        }
    }
}