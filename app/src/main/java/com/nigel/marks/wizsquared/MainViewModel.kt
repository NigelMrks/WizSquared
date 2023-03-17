package com.nigel.marks.wizsquared

import androidx.lifecycle.ViewModel
import com.nigel.marks.wizsquared.data.Repository
import com.nigel.marks.wizsquared.data.model.CharacterCreationTempSave

class MainViewModel : ViewModel() {
    val repository = Repository()
    var characterTempSave: CharacterCreationTempSave = CharacterCreationTempSave()

    fun resetCharacterTempSave() {
        characterTempSave = CharacterCreationTempSave()
    }
    fun getAbilityScores(type: String): List<Int> {
        var scores: List<Int> = when (type) {
            "roll" -> characterTempSave.rolledAbilities
            "standard" -> listOf(15, 14, 13, 12, 10, 8)
            else -> listOf()
        }
        return scores
    }
    fun rollForStats(): List<Int> {
        val attributes: MutableList<Int> = mutableListOf()
        val roll: MutableList<Int> = mutableListOf()
        var rollTotal: Int
        var die1: Int
        var die2: Int
        var die3: Int
        var die4: Int
        for (i in 1..6) {
            die1 = (1..6).random()
            die2 = (1..6).random()
            die3 = (1..6).random()
            die4 = (1..6).random()
            roll.addAll(listOf(die1, die2, die3, die4))
            roll.remove(roll.min())
            rollTotal = roll[0] + roll[1] + roll[2]
            roll.clear()
            attributes.add(rollTotal)
        }
        attributes.sortDescending()
        return attributes
    }

}