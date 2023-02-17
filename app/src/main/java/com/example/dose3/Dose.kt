package com.example.dose3

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class Dose : ViewModel() {
    private lateinit var statusPlayer: StatusPlayer
    private lateinit var statusPlayerFlag: StatusPlayer
    val player = MutableLiveData<String>()
    var playerFlag = ""
    private val listChoose = arrayListOf("", "", "", "", "", "", "", "", "")
    val listLiveChoose = MutableLiveData<ArrayList<String>>()
    private val lisPlayerX = mutableListOf<Int>()
    private val lisPlayerO = mutableListOf<Int>()
    private val listWinner = listOf(
        listOf(1, 2, 3),
        listOf(4, 5, 6),
        listOf(7, 8, 9),
        listOf(1, 4, 7),
        listOf(2, 5, 8),
        listOf(3, 6, 9),
        listOf(1, 5, 9),
        listOf(3, 5, 7),
    )

    fun setUser(move: String) {
        player.value = move
        playerFlag = player.value.toString()
        statusPlayer = if (move == "X") {
            StatusPlayer.X
        } else {
            StatusPlayer.O
        }
        statusPlayerFlag = statusPlayer
    }

    private fun changeStatus() {
        statusPlayer = if (statusPlayer == StatusPlayer.X) StatusPlayer.O
        else StatusPlayer.X
    }

    private fun statusGameX(): StatusGame {
        if (statusRow()) return StatusGame.ROW
        for (list in listWinner) {
            var count = 0
            for (int in list)
                if (int in lisPlayerX) {
                    count++
                }
            if (count == 3) {
                return if (statusPlayerFlag == StatusPlayer.X) StatusGame.WIN else StatusGame.LOOS
            }
        }
        return StatusGame.NONE
    }

    private fun statusGameO(): StatusGame {
        if (statusRow()) return StatusGame.ROW
        for (list in listWinner) {
            var count = 0
            for (int in list)
                if (int in lisPlayerO) {
                    count++
                }
            if (count == 3) {
                return if (statusPlayerFlag == StatusPlayer.O) StatusGame.WIN else StatusGame.LOOS
            }
        }
        return StatusGame.NONE
    }

    private fun statusRow(): Boolean {
        return (lisPlayerX.size + lisPlayerO.size) == 9
    }

    fun choose(number: Int): StatusGame {
        val status: StatusGame
        if (statusPlayer == StatusPlayer.X) {
            lisPlayerX.add(number+1)
            listChoose[number] = "X"
            status = statusGameX()
        } else {
            lisPlayerO.add(number+1)
            listChoose[number] = "O"
            status = statusGameO()
        }
        listLiveChoose.value = listChoose
        changeStatus()
        when (status) {
            StatusGame.WIN -> player.value = "you Win"
            StatusGame.ROW -> player.value = "Row"
            StatusGame.LOOS -> player.value = "you loos"
            else -> {}
        }
        return status
    }

    fun reset() {
        lisPlayerX.clear()
        lisPlayerO.clear()
        player.value = playerFlag
        statusPlayer = if (playerFlag == "X"){
            StatusPlayer.X
        }else{
            StatusPlayer.O
        }
        for (i in listChoose.indices) listChoose[i] = ""
        listLiveChoose.value = listChoose
    }
}


