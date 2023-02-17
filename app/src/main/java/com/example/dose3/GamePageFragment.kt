package com.example.dose3

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.dose3.databinding.FragmentGamePageBinding

class GamePageFragment: Fragment(R.layout.fragment_game_page) {
    private val doseTool : Dose by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FragmentGamePageBinding.bind(view).apply {
            dose = doseTool
            lifecycleOwner = this@GamePageFragment
            val buttonList = listOf(button1,button2,button3,button4,button5,button6,button7,button8,button9)
            val player = requireArguments().getString("Player")
            fun offClick(){
                buttonList.forEach {
                    it.isClickable = false
                }
            }
            fun onClick(){
                buttonList.forEach {
                    it.isClickable = true
                }
            }
            doseTool.setUser(player!!)
            for (button in buttonList){
                button.setOnClickListener {
                    button.isClickable = false
                    if(doseTool.choose(button.tag.toString().toInt()-1) != StatusGame.NONE) offClick()
                }
            }
            buttonReset.setOnClickListener {
                doseTool.reset()
                onClick()
            }
        }
    }
}