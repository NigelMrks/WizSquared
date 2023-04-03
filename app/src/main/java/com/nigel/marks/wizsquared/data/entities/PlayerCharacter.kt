package com.nigel.marks.wizsquared.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nigel.marks.wizsquared.data.model.*

@Entity(tableName = "player_characters")
data class PlayerCharacter(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    //Main Information
    var characterName: String,
    val characterLevels: Map<String,Int> = mapOf(
        "barbarian" to 0,
        "bard" to 0,
        "cleric" to 0,
        "druid" to 0,
        "fighter" to 0,
        "monk" to 0,
        "paladin" to 0,
        "ranger" to 0,
        "rogue" to 0,
        "sorcerer" to 0,
        "warlock" to 0,
        "wizard" to 0
    ),
    var race: String,
    var background: String,
    var hitPointsMax: Int,
    var hitPointsCurrent: Int,
    var speed: Int = 30,
    //Abilities & Skills
    //Abilities
    var strength: Int,
    var dexterity: Int,
    var constitution: Int,
    var intelligence: Int,
    var wisdom: Int,
    var charisma: Int,
    //Skill Proficiencies
    val savingThrows: Map<String,Boolean> = mapOf(
        "strength" to false,
        "dexterity" to false,
        "constitution" to false,
        "intelligence" to false,
        "wisdom" to false,
        "charisma" to false,
    ),
    val skillProficiencies: Map<String,Boolean> = mapOf(
        //STR Profs
        "Athletics" to false,
        //DEX Profs
        "Acrobatics" to false,
        "Sleight of Hand" to false,
        "Stealth" to false,
        //INT Profs
        "Arcana" to false,
        "History" to false,
        "Investigation" to false,
        "Nature" to false,
        "Religion" to false,
        //WIS Profs
        "Animal Handling" to false,
        "Insight" to false,
        "Medicine" to false,
        "Perception" to false,
        "Survival" to false,
        //CHA Profs
        "Deception" to false,
        "Intimidation" to false,
        "Performance" to false,
        "Persuasion" to false
    ),
    //Combat
    val attackList: List<Attack> = listOf(),
    //Spells
    var spellCastingAbility: String = "None",
    val spellSlotsMax: List<Int> = listOf(0,0,0,0,0,0,0,0,0,0),
    val spellSlotsCurrent: List<Int> = listOf(0,0,0,0,0,0,0,0,0,0),
    var spellList: MutableList<Spell> = mutableListOf(),
    //Features & Traits
    val featureList: MutableList<Feature> = mutableListOf(),
    //Inventory
    val money: Map<String,Int> = mapOf("Copper" to 0, "Silver" to 0,"Electrum" to 0, "Gold" to 0, "Platinum" to 0),
    val equipment: MutableList<Equipment> = mutableListOf(),
    //Resources & Tools
    var resources: MutableList<Resource> = mutableListOf(),
    var toolProficiencies: MutableList<String> = mutableListOf(),
    var languages: MutableList<String> = mutableListOf(),
    //Health & Death
    var temporaryHp: Int = 0,
    var hitDiceType: Int,
    var hitDiceCurrent: Int = 1,
    val deathSaves: List<Boolean> = listOf(false,false,false,false,false,false), //3x Successes + 3x Failures
    //Bio
    var experiencePoints: Int = 0,
    var personalityTraits: String = "",
    var ideals: String = "",
    var bonds: String = "",
    var flaws: String = "",
    //Profile
    var age: String = "",
    var height: String = "",
    var weight: String = "",
    var eyes: String = "",
    var hair: String = "",
    var skin: String = "",
)