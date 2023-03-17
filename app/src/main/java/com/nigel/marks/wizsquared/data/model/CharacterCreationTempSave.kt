package com.nigel.marks.wizsquared.data.model

data class CharacterCreationTempSave(
    var selectedRace: Int = 0,
    var selectedClass: Int = 0,
    var abilityMethod: Int = 0,
    var rolledAbilities: List<Int> = listOf(),
    var abilityScores: MutableList<Int> = mutableListOf(0,1,2,3,4,5)
)
