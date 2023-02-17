package com.example.dose3

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class ChoosePlayer: ViewModel() {
    val randomOrContinue = MutableLiveData("Random")
    var rotation = MutableLiveData<Float>()
    val player = MutableLiveData<String>()
    fun choosePlayer() {
        randomOrContinue.value = "Continue"
        val num = listOf(1, 2).random()
        if (num == 1) {
            player.value = "X"
            rotation.value = -29F
        } else {
            player.value = "O"
            rotation.value = 42F
        }
    }

}