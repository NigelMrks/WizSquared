package com.nigel.marks.wizsquared.data.model

import androidx.lifecycle.MutableLiveData
import com.nigel.marks.wizsquared.data.entities.Spell

data class CharacterCreationTempSave(
    var selectedRace: Int = 0,
    var selectedClass: Int = 0,
    var abilityMethod: Int = 0,
    var rolledAbilities: List<Int> = listOf(),
    var abilityScoresRef: MutableList<Int> = mutableListOf(0,1,2,3,4,5),
    var backgroundEquipment: MutableLiveData<MutableList<Equipment>> = MutableLiveData(mutableListOf()),
    var backgroundName: String = "",
    var bgSkillOne: Int = 0,
    var bgSkillTwo: Int = 0,
    var bgLangOne: Int = 0,
    var bgLangTwo: Int = 0,
    var listOfClassEquipment: MutableLiveData<MutableList<Equipment>> = MutableLiveData(mutableListOf()),
    var startingWealthRolled: List<Int> = listOf(),
    var isUsingClassEquipment: Boolean = true,
    var hasSpells: Boolean = false,
    var cantrips: MutableLiveData<MutableList<Spell>> = MutableLiveData(mutableListOf()),
    var spells: MutableLiveData<MutableList<Spell>> = MutableLiveData(mutableListOf()),
    var bio: MutableMap<String,String> = mutableMapOf(
        "name" to "",
        "age" to "",
        "height" to "",
        "weight" to "",
        "eyes" to "",
        "hair" to "",
        "skin" to ""
    )
)
