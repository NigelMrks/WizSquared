package com.nigel.marks.wizsquared.data.model

data class CharacterCreationTempSave(
    var selectedRace: Int = 0,
    var selectedClass: Int = 0,
    var abilityMethod: Int = 0,
    var rolledAbilities: List<Int> = listOf()
)
