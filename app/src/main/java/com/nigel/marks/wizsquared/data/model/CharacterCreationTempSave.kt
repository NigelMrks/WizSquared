package com.nigel.marks.wizsquared.data.model

import androidx.lifecycle.MutableLiveData

data class CharacterCreationTempSave(
    var selectedRace: Int = 0,
    var selectedClass: Int = 0,
    var abilityMethod: Int = 0,
    var rolledAbilities: List<Int> = listOf(),
    var abilityScores: MutableList<Int> = mutableListOf(0,1,2,3,4,5),
    var backgroundEquipment: MutableLiveData<MutableList<Equipment>> = MutableLiveData(mutableListOf()),
    var backgroundName: String = "",
    var bgSkillOne: Int = 0,
    var bgSkillTwo: Int = 0,
    var bgLangOne: Int = 0,
    var bgLangTwo: Int = 0,
    var listOfClassEquipment: MutableLiveData<MutableList<Equipment>> = MutableLiveData(mutableListOf()),
    var startingWealthRolled: List<Int> = listOf(),
    var isUsingClassEquipment: Boolean = true,
)
