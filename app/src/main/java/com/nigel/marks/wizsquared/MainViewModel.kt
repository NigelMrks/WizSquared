package com.nigel.marks.wizsquared

import android.app.Application
import androidx.lifecycle.*
import com.nigel.marks.wizsquared.data.Repository
import com.nigel.marks.wizsquared.data.db.getDatabase
import com.nigel.marks.wizsquared.data.entities.PlayerCharacter
import com.nigel.marks.wizsquared.data.entities.Spell
import com.nigel.marks.wizsquared.data.model.CharacterCreationTempSave
import com.nigel.marks.wizsquared.data.model.EqOptionsList
import com.nigel.marks.wizsquared.data.model.Equipment
import com.nigel.marks.wizsquared.data.model.Feature
import com.nigel.marks.wizsquared.data.remote.SpellAPI
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    val repository = Repository(database, SpellAPI)
    val spellList: LiveData<List<Spell>> = repository.spellList
    var characterTempSave: CharacterCreationTempSave = CharacterCreationTempSave()

    fun resetCharacterTempSave() {
        characterTempSave = CharacterCreationTempSave()
    }
    fun getAbilityScores(type: String): List<Int> {
        val scores: List<Int> = when (type) {
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
        val classEquipmentList = mutableListOf<Equipment>()
        val saveMap: MutableMap<String,String>
        val eqList: List<EqOptionsList>
        val choices: Int
        when(characterTempSave.selectedClass){
            1 -> {//Selected Class = Barbarian
                saveMap = repository.classMapBarbarian
                eqList = repository.eqBarbarian
                choices = 2
            }
            2 -> { // Selected Class = Bard
                saveMap = repository.classMapBard
                eqList = repository.eqBard
                choices = 3
            }
            3 -> { //Selected Class = Cleric
                saveMap = repository.classMapCleric
                eqList = repository.eqCleric
                choices = 4
            }
            4 -> { //Selected Class = Druid
                saveMap = repository.classMapDruid
                eqList = repository.eqDruid
                choices = 2
            }
            5 -> { //Selected Class = Fighter
                saveMap = repository.classMapFighter
                eqList = repository.eqFighter
                choices = 4
            }
            6 -> { //Selected Class = Monk
                saveMap = repository.classMapMonk
                eqList = repository.eqMonk
                choices = 2
            }
            7 -> { //Selected Class = Paladin
                saveMap = repository.classMapPaladin
                eqList = repository.eqPaladin
                choices = 3
            }
            8 -> { //Selected Class = Ranger
                saveMap = repository.classMapRanger
                eqList = repository.eqRanger
                choices = 3
            }
            9 -> { //Selected Class = Rogue
                saveMap = repository.classMapRogue
                eqList = repository.eqRogue
                choices = 3
            }
            10 -> { //Selected Class = Sorcerer
                saveMap = repository.classMapSorcerer
                eqList = repository.eqSorcerer
                choices = 3
            }
            11 -> { //Selected Class = Warlock
                saveMap = repository.classMapWarlock
                eqList = repository.eqWarlock
                choices = 3
            }
            12 -> { //Selected Class = Wizard
                saveMap = repository.classMapWizard
                eqList = repository.eqWizard
                choices = 3
            }
            else -> { //Selected Class = No Class Selected
                saveMap = mutableMapOf()
                eqList = listOf()
                choices = 0
            }
        }
        if (choices != 0) {
            for (i in (0..choices)) {
                if (i != choices) {
                    val key = "equipment:$i"
                    classEquipmentList.addAll(
                        when (saveMap[key]) {
                            "0" -> eqList[i].a
                            "1" -> eqList[i].b
                            "2" -> eqList[i].c
                            else -> eqList[i].d
                        }
                    )
                }
                else classEquipmentList.addAll(eqList[i].a)
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

    fun loadSpells() {
        viewModelScope.launch {
            repository.loadSpellApi()
        }
    }

    fun getSpells(selClass: String, toLevel: Int): LiveData<List<Spell>> {
        return database.spellDao.filterSpells(selClass, toLevel)
    }

    fun compileCharacterTempSave(): PlayerCharacter {
        //Reusable Lists
        val classList = listOf(
            "barbarian", "bard", "cleric", "druid",
            "fighter", "monk", "paladin", "ranger",
            "rogue", "sorcerer", "warlock", "wizard"
        )
        val raceList = listOf(
            "Dragonborn","Dwarf","Elf","Gnome","Half-Elf",
            "Half-Orc","Halfling","Human","Tiefling","Custom"
        )

        //Create new Instance of PlayerCharacter
        val newCharacter = PlayerCharacter()

        //Race
        if (characterTempSave.selectedRace != 0) {
            newCharacter.race = raceList[characterTempSave.selectedRace-1]
        }

        //Class
        var selClass = ""
        if (characterTempSave.selectedClass != 0) {
            selClass = classList[characterTempSave.selectedClass-1]
        }
        if (newCharacter.characterLevels.contains(selClass)) {
            newCharacter.characterLevels[selClass] = 1
        }

        //Scores (Initial)
        for (score in characterTempSave.rolledAbilities) {
            when (characterTempSave.abilityScoresRef[characterTempSave.rolledAbilities.indexOf(score)]) {
                0 -> newCharacter.strength += score
                1 -> newCharacter.dexterity += score
                2 -> newCharacter.constitution += score
                3 -> newCharacter.intelligence += score
                4 -> newCharacter.wisdom += score
                5 -> newCharacter.charisma += score
            }
        }

        //Hit Dice Type
        when (selClass) {
            "barbarian" -> newCharacter.hitDiceType = 12
            in "fighter", "paladin", "ranger" -> newCharacter.hitDiceType = 10
            in "sorcerer", "wizard", -> newCharacter.hitDiceType = 6
            else -> newCharacter.hitDiceType = 8
        }

        //Hit Points
        newCharacter.hitPointsMax = newCharacter.hitDiceType + (newCharacter.constitution-10)/2
        newCharacter.hitPointsCurrent = newCharacter.hitPointsMax

        //SavingThrow Proficiencies
        var savThrowProfFirst = ""
        var savThrowProfSecond = ""
        when (selClass) {
            in "barbarian", "fighter", "monk", "ranger" -> savThrowProfFirst = "strength"
            in "bard", "rogue" -> savThrowProfFirst = "dexterity"
            "sorcerer" -> savThrowProfFirst = "constitution"
            in "druid", "wizard" -> savThrowProfFirst = "intelligence"
            in "cleric", "paladin", "warlock" -> savThrowProfFirst = "wisdom"
        }
        when (selClass) {
            in "monk", "ranger" -> savThrowProfSecond = "dexterity"
            in "barbarian", "fighter" -> savThrowProfSecond = "constitution"
            "rogue" -> savThrowProfSecond = "dexterity"
            in "druid", "wizard" -> savThrowProfSecond = "wisdom"
            in "bard", "cleric", "paladin", "sorcerer", "warlock" -> savThrowProfSecond = "charisma"
        }
        if (newCharacter.savingThrows.keys.contains(savThrowProfFirst)) {
            newCharacter.savingThrows[savThrowProfFirst] = true
        }
        if (newCharacter.savingThrows.keys.contains(savThrowProfSecond)) {
            newCharacter.savingThrows[savThrowProfSecond] = true
        }

        //SpellCastingAbility
        newCharacter.spellCastingAbility = when (selClass) {
            "wizard" -> "INT"
            in "cleric","druid","ranger" -> "WIS"
            in "bard","paladin","sorcerer","warlock" -> "CHA"
            else -> "None"
        }

        //Money and Equipment
        if (characterTempSave.startingWealthRolled.isNotEmpty() && !characterTempSave.isUsingClassEquipment) {
            newCharacter.money["Gold"] = characterTempSave.startingWealthRolled.last()
        }
        if (characterTempSave.isUsingClassEquipment) {
            characterTempSave.listOfClassEquipment.value?.let { newCharacter.equipment.addAll(it) }
            characterTempSave.backgroundEquipment.value?.let { newCharacter.equipment.addAll(it) }
        }

        //Bio & Info
        if (characterTempSave.bio["name"]!=null) newCharacter.characterName = characterTempSave.bio["name"].toString()
        if (characterTempSave.bio["age"]!=null) newCharacter.age = characterTempSave.bio["age"].toString()
        if (characterTempSave.bio["height"]!=null) newCharacter.height = characterTempSave.bio["height"].toString()
        if (characterTempSave.bio["weight"]!=null) newCharacter.weight = characterTempSave.bio["weight"].toString()
        if (characterTempSave.bio["eyes"]!=null) newCharacter.eyes = characterTempSave.bio["eyes"].toString()
        if (characterTempSave.bio["hair"]!=null) newCharacter.hair = characterTempSave.bio["hair"].toString()
        if (characterTempSave.bio["skin"]!=null) newCharacter.skin = characterTempSave.bio["skin"].toString()


        return newCharacter
    }

    private fun parseRaceTraits(newCharacter: PlayerCharacter){
        val raceList = listOf(
            "Dragonborn","Dwarf","Elf","Gnome","Half-Elf",
            "Half-Orc","Halfling","Human","Tiefling"
        )
        val saveMaps = repository.raceSaveMaps
        val keyList = listOf(
            "alignment","tool_prof","skill:0","skill:1","language"
        )

        val saveMap = saveMaps[raceList.indexOf(newCharacter.race)]

        //Subrace
        if (saveMap["subrace"]!=null && saveMap["subrace"] != "Choose") {
            newCharacter.subrace = saveMap["subrace"]!!
        }

        //ASI Bonus
        when (newCharacter.race) {
            raceList[0] -> {
                newCharacter.strength += 2
                newCharacter.charisma += 1
            }
            raceList[1] -> {
                newCharacter.constitution += 2
                when (newCharacter.subrace) {
                    "Hill" -> newCharacter.wisdom += 1
                    "Mountain" -> newCharacter.strength += 2
                }
            }
            raceList[2] -> {
                newCharacter.dexterity += 2
                when (newCharacter.subrace) {
                    "Dark" -> newCharacter.charisma += 1
                    "High" -> newCharacter.intelligence += 1
                    "Wood" -> newCharacter.wisdom += 1
                }
            }
            raceList[3] -> {
                newCharacter.intelligence += 2
                when (newCharacter.subrace) {
                    "Forest" -> newCharacter.dexterity += 1
                    "Rock" -> newCharacter.constitution += 1
                }
            }
            raceList[4] -> {
                newCharacter.charisma += 2
                when (saveMap["asi:0"]) {
                    "Strength" -> newCharacter.strength += 1
                    "Dexterity" -> newCharacter.dexterity += 1
                    "Constitution" -> newCharacter.constitution += 1
                    "Intelligence" -> newCharacter.intelligence += 1
                    "Charisma" -> newCharacter.charisma += 1
                }
                when (saveMap["asi:1"]) {
                    "Strength" -> newCharacter.strength += 1
                    "Dexterity" -> newCharacter.dexterity += 1
                    "Constitution" -> newCharacter.constitution += 1
                    "Intelligence" -> newCharacter.intelligence += 1
                    "Charisma" -> newCharacter.charisma += 1
                }
            }
            raceList[5] -> {
                newCharacter.strength += 2
                newCharacter.constitution += 1
            }
            raceList[6] -> {
                newCharacter.dexterity += 2
                when (newCharacter.subrace) {
                    "Lightfoot" -> newCharacter.charisma += 1
                    "Stout" -> newCharacter.constitution += 1
                }
            }
            raceList[7] -> {
                newCharacter.strength += 1
                newCharacter.dexterity += 1
                newCharacter.constitution += 1
                newCharacter.intelligence += 1
                newCharacter.wisdom += 1
                newCharacter.charisma += 1
            }
            raceList[8] -> {
                newCharacter.charisma += 2
                newCharacter.intelligence += 1
            }
        }

        //Remaining SaveMap Keys
        for (key in saveMap.keys) {
            when (key) {
                "alignment" -> newCharacter.alignment = saveMap[key].toString()
                "tool_prof" -> newCharacter.toolProficiencies.add(saveMap[key].toString())
                "language" -> newCharacter.languages.add(saveMap[key].toString())
                in "skill:0","skill:1" -> {
                    if (newCharacter.skillProficiencies.keys.contains(saveMap[key])) {
                        newCharacter.skillProficiencies[saveMap[key].toString()] = false
                    }
                }
            }
        }

        //Race Baseline Languages
        newCharacter.languages.add("Common")
        when (newCharacter.race) {
            raceList[0] -> {
                newCharacter.languages.add("Draconic")
            }
            raceList[1] -> {
                newCharacter.languages.add("Dwarvish")
            }
            raceList[2] -> {
                newCharacter.languages.add("Elven")
            }
            raceList[3] -> {
                newCharacter.languages.add("Gnomish")
            }
            raceList[4] -> {
                newCharacter.languages.add("Elven")
            }
            raceList[5] -> {
                newCharacter.languages.add("Orc")
            }
            raceList[6] -> {
                newCharacter.languages.add("Halfling")
            }
            raceList[8] -> {
                newCharacter.languages.add("Abyssal")
            }
        }

        //RaceFeatures
        when (newCharacter.race) {
            raceList[0] -> {
                var damageType = ""
                var breathWeapon = ""
                when (newCharacter.subrace) {
                    "Black" -> {
                        damageType = "Acid"
                        breathWeapon = "5' by 30' line (DEX save)"
                    }
                    "Blue" -> {
                        damageType = "Lightning"
                        breathWeapon = "5' by 30' line (DEX save)"
                    }
                    "Brass" -> {
                        damageType = "Fire"
                        breathWeapon = "5' by 30' line (DEX save)"
                    }
                    "Bronze" -> {
                        damageType = "Lightning"
                        breathWeapon = "5' by 30' line (DEX save)"
                    }
                    "Copper" -> {
                        damageType = "Acid"
                        breathWeapon = "5' by 30' line (DEX save)"
                    }
                    "Gold" -> {
                        damageType = "Fire"
                        breathWeapon = "15' cone (DEX save)"
                    }
                    "Green" -> {
                        damageType = "Poison"
                        breathWeapon = "15' cone (CON save)"
                    }
                    "Red" -> {
                        damageType = "Fire"
                        breathWeapon = "15' cone (DEX save)"
                    }
                    "Silver" -> {
                        damageType = "Cold"
                        breathWeapon = "15' cone (CON save)"
                    }
                    "White" -> {
                        damageType = "Cold"
                        breathWeapon = "15' cone (CON save)"
                    }
                }
                if (newCharacter.subrace != "") {
                    newCharacter.featureList.addAll(
                        listOf(
                            Feature(
                                name = "Breath Weapon",
                                source = "Race: Dragonborn",
                                desc = "Deal $damageType damage in a $breathWeapon." +
                                        "The DC of this saving throw is 8 + your Constitution " +
                                        "modifier + your proficiency bonus. A creature takes 2d6 " +
                                        "damage on a failed save, and half as much damage on a " +
                                        "successful one. The damage increase to 3d6 at 6th level, " +
                                        "4d6 at 11th, and 5d6 at 16th level. After using your breath " +
                                        "weapon, you cannot use it again until you complete a short " +
                                        "or long rest."
                            ),
                            Feature(
                                name = "Damage Resistance",
                                source = "Race: Dragonborn",
                                desc = "You have resistance to $damageType damage."
                            )
                        )
                    )
                }
            }
            raceList[1] -> {
                newCharacter.featureList.addAll(
                    listOf(
                        Feature(
                            name = "Dwarven Resilience",
                            source = "Race: Dwarf",
                            desc = "You have advantage on saving throws against poison, and " +
                                    "you have resistance against poison damage."
                        ),
                        Feature(
                            name = "Dwarven Combat Training",
                            source = "Race: Dwarf",
                            desc = "You have proficiency with the battleaxe, handaxe, light " +
                                    "hammer, and warhammer."
                        ),
                        Feature(
                            name = "Stonecunning",
                            source = "Race: Dwarf",
                            desc = "Whenever you make an Intelligence (History) check related " +
                                    "to the origin of stonework, you are considered proficient " +
                                    "in the History skill and add double your proficiency bonus " +
                                    "to the check, instead of your normal proficiency bonus."
                        )
                    )
                )
                when (newCharacter.subrace) {
                    "Hill" -> newCharacter.featureList.add(
                        Feature(
                            name = "Dwarven Toughness",
                            source = "Race: Hill Dwarf",
                            desc = "Your hit point maximum increases by 1, and it increases by 1 " +
                                "every time you gain a level."
                        )
                    )
                    "Mountain" -> newCharacter.featureList.add(
                        Feature(
                            name = "Dwarven Armor Training",
                            source = "Race: Mountain Dwarf",
                            desc = "You have proficiency with light and medium armor."
                        )
                    )
                }
            }
            raceList[2] -> {
                newCharacter.featureList.addAll(
                    listOf(
                        Feature(
                            name = "Fey Ancestry",
                            source = "Race: Elf",
                            desc = "You have advantage on saving throws against being charmed, " +
                                    "and magic can't put you to sleep."
                        ),
                        Feature(
                            name = "Trance",
                            source = "Race: Elf",
                            desc = "Elves do not sleep. Instead they meditate deeply, remaining " +
                                    "semi-conscious, for 4 hours a day. The Common word for " +
                                    "this meditation is trance. While meditating, you dream " +
                                    "after a fashion; such dreams are actually mental exercises " +
                                    "that have become reflexive after years of practice. After " +
                                    "resting in this way, you gain the same benefit a human " +
                                    "would from 8 hours of sleep."
                        ),
                        Feature(
                            name = "Darkvision",
                            source = "Race: Elf",
                            desc = "Accustomed to twilit forests and the night sky, you have " +
                                    "superior vision in dark and dim conditions. You can see in " +
                                    "dim light within 60 feet of you as if it were bright light, " +
                                    "and in darkness as if it were dim light. You can't discern " +
                                    "color in darkness, only shades of gray."
                        )
                    )
                )
                newCharacter.skillProficiencies["Perception"] = true
                when (newCharacter.subrace) {
                    "Dark" -> newCharacter.featureList.addAll(
                        listOf(
                            Feature(
                                name = "Superior Darkvision",
                                source = "Race: Dark Elf",
                                desc = "Your darkvision has a range of 120 feet, instead of 60."
                            ),
                            Feature(
                                name = "Sunlight Sensitivity",
                                source = "Race: Dark Elf",
                                desc = "You have disadvantage on attack rolls and Wisdom " +
                                        "(Perception) checks that rely on sight when you, " +
                                        "the target of the attack, or whatever you are " +
                                        "trying to perceive is in direct sunlight."
                            ),
                            Feature(
                                name = "Drow Magic",
                                source = "Race: Dark Elf",
                                desc = "You know the Dancing Lights cantrip. When you reach " +
                                        "3rd level, you can cast the Faerie Fire spell once " +
                                        "with this trait and regain the ability to do so when " +
                                        "you finish a long rest. When you reach 5th level, you " +
                                        "can cast the Darkness spell onceand regain the ability " +
                                        "to do so when you finish a long rest. Charisma is your " +
                                        "spellcasting ability for these spells."
                            ),
                            Feature(
                                name = "Drow Weapon Training",
                                source = "Race: Dark Elf",
                                desc = "You have proficiency with rapiers, shortswords, and " +
                                        "hand crossbows."
                            )
                        )
                    )
                    "High" -> newCharacter.featureList.addAll(
                        listOf(
                            Feature(
                                name = "Cantrip",
                                source = "Race: High Elf",
                                desc = "You know one cantrip of your choice from the Wizard " +
                                        "spell list. Intelligence is your spellcasting " +
                                        "ability for it."
                            ),
                            Feature(
                                name = "Elf Weapon Training",
                                source = "Race: High Elf",
                                desc = "You have proficiency with the longsword, shortsword, " +
                                        "shortbow, and longbow."
                            ),
                            Feature(
                                name = "Extra Language",
                                source = "Race: High Elf",
                                desc = "You can read, speak, and write one additional language " +
                                        "of your choice."
                            ),
                        )
                    )
                    "Wood" -> newCharacter.featureList.addAll(
                        listOf(
                            Feature(
                                name = "Elf Weapon Training",
                                source = "Race: Wood Elf",
                                desc = "You have proficiency with the longsword, shortsword, " +
                                        "shortbow, and longbow."
                            ),
                            Feature(
                                name = "Mask of the Wild",
                                source = "Race: Wood Elf",
                                desc = "You can attempt to hide even when you are only lightly " +
                                        "obscured by foliage, heavy rain, falling snow, mist, " +
                                        "and other natural phenomena."
                            )
                        )
                    )
                }
            }
        }
    }



}