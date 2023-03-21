package com.nigel.marks.wizsquared

import androidx.lifecycle.ViewModel
import com.nigel.marks.wizsquared.data.Repository
import com.nigel.marks.wizsquared.data.model.CharacterCreationTempSave
import com.nigel.marks.wizsquared.data.model.Equipment
import com.nigel.marks.wizsquared.data.model.SelectionSave

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

    fun getClassEquipment() {
        var classEquipmentList = mutableListOf<Equipment>()
        var savedEq: SelectionSave
        when(characterTempSave.selectedClass){
            1 -> { //Selected Class = Barbarian
                savedEq = repository.classBarbarian.last().selectionSave
                classEquipmentList.add(repository.eqBarbarian[0][savedEq.selectionOne])
                classEquipmentList.add(repository.eqBarbarian[1][savedEq.selectionTwo])
                classEquipmentList.addAll(repository.eqBarbarian[2])
            }
        }
        characterTempSave.listOfClassEquipment.value = classEquipmentList
    }

    fun getClassWealth(): Pair<Int,Boolean> {
        return repository.classWealthList[characterTempSave.selectedClass]
    }
    fun rollWealth(classWealth: Pair<Int,Boolean>){
        val wealthRoll = mutableListOf<Int>()
        for (i in 1..classWealth.first) {
            wealthRoll.add((1..4).random())
        }
        var total = wealthRoll.sum()
        if (classWealth.second) total *= 10
        wealthRoll.add(total)
        characterTempSave.startingWealthRolled = wealthRoll
    }
    fun clearWealth(){
        characterTempSave.isUsingClassEquipment = true
        characterTempSave.startingWealthRolled = listOf()
    }

}