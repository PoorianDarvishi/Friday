package com.example.dose3

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import com.example.dose3.databinding.FragmentHomePageBinding

class HomePageFragment : Fragment(R.layout.fragment_home_page) {
    private val choosePlayerView: ChoosePlayer by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        FragmentHomePageBinding.bind(view).apply {
            choosePlayer = choosePlayerView
            lifecycleOwner = this@HomePageFragment
            buttonRandom.setOnClickListener {
                if (choosePlayerView.randomOrContinue.value == "Random") {
                    choosePlayerView.choosePlayer()
                } else {
                    parentFragmentManager.commit {
                        setReorderingAllowed(true)
                        replace<GamePageFragment>(
                            R.id.fragment_container_view,
                            args = bundleOf("Player" to choosePlayerView.player.value)
                        )
                    }
                }
            }
        }
        super.onViewCreated(view, savedInstanceState)

    }


}