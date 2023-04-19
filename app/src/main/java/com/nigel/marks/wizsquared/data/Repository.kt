package com.nigel.marks.wizsquared.data

import android.media.audiofx.DynamicsProcessing.Eq
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nigel.marks.wizsquared.data.db.SpellDatabase
import com.nigel.marks.wizsquared.data.entities.PlayerCharacter
import com.nigel.marks.wizsquared.data.entities.Spell
import com.nigel.marks.wizsquared.data.model.EqOptionsList
import com.nigel.marks.wizsquared.data.model.Equipment
import com.nigel.marks.wizsquared.data.model.SelectorBox
import com.nigel.marks.wizsquared.data.model.SpellResult
import com.nigel.marks.wizsquared.data.remote.SpellAPI
import com.squareup.moshi.subtypeOf
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class Repository (private val database: SpellDatabase, private val spellApi: SpellAPI){
    //Spell List
    var spellList: LiveData<List<Spell>> = database.spellDao.getAll()

    //Character List (Temporary?)
    var characterList: MutableList<PlayerCharacter> = mutableListOf()

    //List of Alignments as Strings
    private val alignments: List<String> = listOf(
        "Choose",
        "Lawful Good", "Neutral Good", "Chaotic Good",
        "Lawful Neutral", "Neutral", "Chaotic Neutral",
        "Lawful Evil", "Neutral Evil", "Chaotic Evil"
    )
    private val languages: List<String> = listOf(
        "Common","Dwarvish","Elvish","Giant",
        "Gnomish","Goblin","Halfling","Orc",
        "Abyssal","Celestial","Draconic","Deep Speech",
        "Infernal","Primordial","Sylvan","Undercommon"
    )
    private val skills: List<String> = listOf(
        "Choose",
        "Acrobatics", "Animal Handling", "Arcana",
        "Athletics", "Deception", "History",
        "Insight", "Intimidation", "Investigation",
        "Medicine", "Nature", "Perception",
        "Performance", "Persuasion", "Religion",
        "Sleight of Hand", "Stealth", "Survival"
    )

    //EquipmentLists
    val eqBarbarian: List<EqOptionsList> = listOf(
        EqOptionsList(
            a = listOf(
                Equipment("Greataxe",1)
            ),
            b = listOf(
                Equipment("Any Martial Melee Weapon",1)
            )
        ),
        EqOptionsList(
            a = listOf(
                Equipment("Handaxe",2)
            ),
            b = listOf(
                Equipment("Any Simple Weapon",1)
            )
        ),
        EqOptionsList(
            a = listOf(
                Equipment("Explorer's Pack",1),
                Equipment("Javelin",4)
            )
        )
    )
    val eqBard: List<EqOptionsList> = listOf(
        EqOptionsList(
            a = listOf(
                Equipment("Rapier",1)
            ),
            b = listOf(
                Equipment("Longsword",1)
            ),
            c = listOf(
                Equipment("Any Simple Weapon",1)
            )
        ),
        EqOptionsList(
            a = listOf(
                Equipment("Lute",1)
            ),
            b = listOf(
                Equipment("Any Musical Instrument",1)
            )
        ),
        EqOptionsList(
            a = listOf(
                Equipment("Leather Armor",1),
                Equipment("Dagger",1)
            )
        )
    )
    val eqCleric: List<EqOptionsList> = listOf(
        EqOptionsList(
            a = listOf(
                Equipment("Mace",1)
            ),
            b = listOf(
                Equipment("Warhammer",1)
            )
        ),
        EqOptionsList(
            a = listOf(
                Equipment("Scale Mail",1)
            ),
            b = listOf(
                Equipment("Leather Armor",1)
            ),
            c = listOf(
                Equipment("Chain Mail,",1)
            )
        ),
        EqOptionsList(
            a = listOf(
                Equipment("Light Crossbow",1),
                Equipment("Crossbow Bolt",20)
            ),
            b = listOf(
                Equipment("Any Simple Weapon",1)
            )
        ),
        EqOptionsList(
            a = listOf(
                Equipment("Priest's Pack",1)
            ),
            b = listOf(
                Equipment("Explorer's Pack",1)
            )
        ),
        EqOptionsList(
            a = listOf(
                Equipment("Shield",1),
                Equipment("Holy Symbol",1)
            )
        )
    )
    val eqDruid: List<EqOptionsList> = listOf(
        EqOptionsList(
            a = listOf(
                Equipment("Shield",1)
            ),
            b = listOf(
                Equipment("Any Simple Weapon",1)
            )
        ),
        EqOptionsList(
            a = listOf(
                Equipment("Scimitar",1),
            ),
            b = listOf(
                Equipment("Any Simple Weapon",1)
            )
        ),
        EqOptionsList(
            a = listOf(
                Equipment("Leather Armor",1),
                Equipment("Explorer's Pack",1),
                Equipment("Druidic Focus",1)
            )
        )
    )
    val eqFighter: List<EqOptionsList> = listOf(
        EqOptionsList(
            a = listOf(
                Equipment("Chain Mail",1)
            ),
            b = listOf(
                Equipment("Leather Armor",1),
                Equipment("Longbow",1),
                Equipment("Arrow",20)
            )
        ),
        EqOptionsList(
            a = listOf(
                Equipment("Any Martial Weapon",1),
                Equipment("Shield",1)
            ),
            b = listOf(
                Equipment("Any Martial Weapon",2)
            )
        ),
        EqOptionsList(
            a = listOf(
                Equipment("Light Crossbow",1),
                Equipment("Crossbow Bolts",20)
            ),
            b = listOf(
                Equipment("Handaxe",2)
            )
        ),
        EqOptionsList(
            a = listOf(
                Equipment("Dungeoneer's Pack",1)
            ),
            b = listOf(
                Equipment("Explorer's Pack",1)
            )
        ),
        EqOptionsList(
            a = listOf()
        )
    )
    val eqMonk: List<EqOptionsList> = listOf(
        EqOptionsList(
            a = listOf(
                Equipment("Shortsword",1)
            ),
            b = listOf(
                Equipment("Any Simple Weapon",1)
            )
        ),
        EqOptionsList(
            a = listOf(
                Equipment("Dungeoneer's Pack",1)
            ),
            b = listOf(
                Equipment("Explorer's Pack",1)
            )
        ),
        EqOptionsList(
            a = listOf(
                Equipment("Dart",10)
            )
        )
    )
    val eqPaladin: List<EqOptionsList> = listOf(
        EqOptionsList(
            a = listOf(
                Equipment("Any Martial Weapon",1),
                Equipment("Shield",1)
            ),
            b = listOf(
                Equipment("Any Martial Weapon",2)
            )
        ),
        EqOptionsList(
            a = listOf(
                Equipment("Javelin",5)
            ),
            b = listOf(
                Equipment("Any Simple Melee Weapon",1)
            )
        ),
        EqOptionsList(
            a = listOf(
                Equipment("Priest's Pack",1)
            ),
            b = listOf(
                Equipment("Explorer's Pack",1)
            )
        ),
        EqOptionsList(
            a = listOf(
                Equipment("Chain Mail",1),
                Equipment("Holy Symbol",1)
            )
        )
    )
    val eqRanger: List<EqOptionsList> = listOf(
        EqOptionsList(
            a = listOf(
                Equipment("Scale Mail",1)
            ),
            b = listOf(
                Equipment("Leather Armor",1 )
            )
        ),
        EqOptionsList(
            a = listOf(
                Equipment("Shortsword",2)
            ),
            b = listOf(
                Equipment("Any Simple Melee Weapon",2)
            )
        ),
        EqOptionsList(
            a = listOf(
                Equipment("Dungeoneer's Pack",1)
            ),
            b = listOf(
                Equipment("Explorer's Pack",1)
            )
        ),
        EqOptionsList(
            a = listOf(
                Equipment("Longbow",1),
                Equipment("Arrow",20)
            )
        )
    )
    val eqRogue: List<EqOptionsList> = listOf(
        EqOptionsList(
            a = listOf(
                Equipment("Rapier",1)
            ),
            b = listOf(
                Equipment("Shortsword",1)
            )
        ),
        EqOptionsList(
            a = listOf(
                Equipment("Shortbow",1),
                Equipment("Arrow",20)
            ),
            b = listOf(
                Equipment("Shortsword",1)
            )
        ),
        EqOptionsList(
            a = listOf(
                Equipment("Burglar's Pack",1)
            ),
            b = listOf(
                Equipment("Dungeoneer's Pack",1)
            ),
            c = listOf(
                Equipment("Explorer's Pack",1)
            )
        ),
        EqOptionsList(
            a = listOf(
                Equipment("Leather Armor",1),
                Equipment("Dagger",2),
                Equipment("Thieves' Tools",1)
            )
        )
    )
    val eqSorcerer: List<EqOptionsList> = listOf(
        EqOptionsList(
            a = listOf(
                Equipment("Light Crossbow",1),
                Equipment("Arrow",20)
            ),
            b = listOf(
                Equipment("Any Simple Weapon",1)
            )
        ),
        EqOptionsList(
            a = listOf(
                Equipment("Component Pouch",1)
            ),
            b = listOf(
                Equipment("Arcane Focus",1)
            )
        ),
        EqOptionsList(
            a = listOf(
                Equipment("Dungeoneer's Pack",1)
            ),
            b = listOf(
                Equipment("Explorer's Pack",1)
            )
        ),
        EqOptionsList(
            a = listOf(
                Equipment("Dagger",2)
            )
        )
    )
    val eqWarlock: List<EqOptionsList> = listOf(
        EqOptionsList(
            a = listOf(
                Equipment("Light Crossbow",1),
                Equipment("Crossbow Bolts",20)
            ),
            b = listOf(
                Equipment("Any Simple Weapon",1)
            )
        ),
        EqOptionsList(
            a = listOf(
                Equipment("Component Pouch",1)
            ),
            b = listOf(
                Equipment("Arcane Focus",1)
            )
        ),
        EqOptionsList(
            a = listOf(
                Equipment("Scholar's Pack",1)
            ),
            b = listOf(
                Equipment("Dungeoneer's Pack",1)
            )
        ),
        EqOptionsList(
            a = listOf(
                Equipment("Leather Armor",1),
                Equipment("Any Simple Weapon",1),
                Equipment("Dagger",2)
            )
        )
    )
    val eqWizard: List<EqOptionsList> = listOf(
        EqOptionsList(
            a = listOf(
                Equipment("Quarterstaff",1)
            ),
            b = listOf(
                Equipment("Dagger",1)
            )
        ),
        EqOptionsList(
            a = listOf(
                Equipment("Component Pouch",1)
            ),
            b = listOf(
                Equipment("Arcane Focus",1)
            )
        ),
        EqOptionsList(
            a = listOf(
                Equipment("Scholar's Pack",1)
            ),
            b = listOf(
                Equipment("Explorer's Pack",1)
            )
        ),
        EqOptionsList(
            a = listOf(
                Equipment("Spellbook",1)
            )
        )
    )


    //Starting Wealth
    private val noClassWealth: Pair<Int,Boolean> = Pair(0,false)
    private val barbarianWealth: Pair<Int,Boolean> = Pair(2,true)
    private val bardWealth: Pair<Int,Boolean> = Pair(5,true)
    private val clericWealth: Pair<Int,Boolean> = Pair(5,true)
    private val druidWealth: Pair<Int,Boolean> = Pair(2,true)
    private val fighterWealth: Pair<Int,Boolean> = Pair(5,true)
    private val monkWealth: Pair<Int,Boolean> = Pair(5,false)
    private val paladinWealth: Pair<Int,Boolean> = Pair(5,true)
    private val rangerWealth: Pair<Int,Boolean> = Pair(5,true)
    private val rogueWealth: Pair<Int,Boolean> = Pair(4,true)
    private val sorcererWealth: Pair<Int,Boolean> = Pair(3,true)
    private val warlockWealth: Pair<Int,Boolean> = Pair(4,true)
    private val wizardWealth: Pair<Int,Boolean> = Pair(4,true)
    val classWealthList: List<Pair<Int,Boolean>> = listOf(
        noClassWealth,barbarianWealth,bardWealth,clericWealth,druidWealth,fighterWealth,monkWealth,
        paladinWealth,rangerWealth,rogueWealth,sorcererWealth,warlockWealth,wizardWealth
    )

    //Lists of Race Attributes for Character Creation as SelectorBox Objects
    val raceDragonborn: List<SelectorBox> = listOf(
        SelectorBox(
            title = "Alignment",
            listOfChoicesOne = alignments,
            key = "alignment"
        ),
        SelectorBox(
            title = "Draconic Ancestry",
            listOfChoicesOne = listOf(
                "Choose",
                "Black",
                "Blue",
                "Brass",
                "Bronze",
                "Copper",
                "Gold",
                "Green",
                "Red",
                "Silver",
                "White"
            ),
            key = "subrace"
        )
    )
    val raceDwarf: List<SelectorBox> = listOf(
        SelectorBox(
            title = "Alignment",
            listOfChoicesOne = alignments,
            key = "alignment"
        ),
        SelectorBox(
            title = "Tool Proficiency",
            listOfChoicesOne = listOf(
                "Smith's Tools",
                "Brewer's Supplies",
                "Mason's Tools"
            ),
            key = "tool_prof"
        ),
        SelectorBox(
            title = "Subrace",
            listOfChoicesOne = listOf("Hill", "Mountain"),
            key = "subrace"
        )
    )
    val raceElf: List<SelectorBox> = listOf(
        SelectorBox(
            title = "Alignment",
            listOfChoicesOne = alignments,
            key = "alignment"
        ),
        SelectorBox(
            title = "Subrace",
            listOfChoicesOne = listOf("Dark","High","Wood"),
            key = "subrace"
        )
    )
    val raceGnome: List<SelectorBox> = listOf(
        SelectorBox(
            title = "Alignment",
            listOfChoicesOne = alignments,
            key = "alignment"
        ),
        SelectorBox(
            title = "Subrace",
            listOfChoicesOne = listOf("Forest","Rock"),
            key = "subrace"
        )
    )
    val raceHalfElf: List<SelectorBox> = listOf(
        SelectorBox(
            title = "Alignment",
            listOfChoicesOne = alignments,
            key = "alignment"
        ),
        SelectorBox(
            title = "Ability Score Improvement",
            numberOfChoices = 2,
            listOfChoicesOne = listOf(
                "Choose",
                "Strength",
                "Dexterity",
                "Constitution",
                "Intelligence",
                "Wisdom"
            ),
            listOfChoicesTwo = listOf(
                "Choose",
                "Strength",
                "Dexterity",
                "Constitution",
                "Intelligence",
                "Wisdom"
            ),
            key = "asi"
        ),
        SelectorBox(
            title = "Skill Versatility",
            numberOfChoices = 2,
            listOfChoicesOne = skills,
            listOfChoicesTwo = skills,
            key = "skill"
        ),
        SelectorBox(
            title = "Extra Language",
            listOfChoicesOne = languages,
            key = "language"
        )
    )
    val raceHalfOrc: List<SelectorBox> = listOf(
        SelectorBox(
            title = "Alignment",
            listOfChoicesOne = alignments,
            key = "alignment"
        )
    )
    val raceHalfling: List<SelectorBox> = listOf(
        SelectorBox(
            title = "Alignment",
            listOfChoicesOne = alignments,
            key = "alignment"
        ),
        SelectorBox(
            title = "Subrace",
            listOfChoicesOne = listOf("Lightfoot","Stout"),
            key = "subrace"
        )
    )
    val raceHuman: List<SelectorBox> = listOf(
        SelectorBox(
            title = "Alignment",
            listOfChoicesOne = alignments,
            key = "alignment"
        ),
        SelectorBox(
            title = "Extra Language",
            listOfChoicesOne = languages,
            key = "language"
        )
    )
    val raceTiefling: List<SelectorBox> = listOf(
        SelectorBox(
            title = "Alignment",
            listOfChoicesOne = alignments,
            key = "alignment"
        )
    )
    //Maps to Save Selection (Race)
    var raceMapDragonborn: MutableMap<String,String> = mutableMapOf()
    var raceMapDwarf: MutableMap<String,String> = mutableMapOf()
    var raceMapElf: MutableMap<String,String> = mutableMapOf()
    var raceMapGnome: MutableMap<String,String> = mutableMapOf()
    var raceMapHalfElf: MutableMap<String,String> = mutableMapOf()
    var raceMapHalfOrc: MutableMap<String,String> = mutableMapOf()
    var raceMapHalfling: MutableMap<String,String> = mutableMapOf()
    var raceMapHuman: MutableMap<String,String> = mutableMapOf()
    var raceMapTiefling: MutableMap<String,String> = mutableMapOf()
    val raceSaveMaps: List<MutableMap<String,String>> = listOf(
        raceMapDragonborn,raceMapDwarf,raceMapElf,
        raceMapGnome,raceMapHalfElf,raceMapHalfOrc,
        raceMapHalfling,raceMapHuman,raceMapTiefling
    )

    //Lists of Class Attributes for Character Creation as SelectorBoxObjects
    val classBarbarian: List<SelectorBox> = listOf(
        SelectorBox(
            title = "Skill Proficiency",
            numberOfChoices = 2,
            listOfChoicesOne = listOf(
                skills[0],skills[2],skills[4],
                skills[8],skills[11],skills[12],
                skills[18]
            ),
            listOfChoicesTwo = listOf(
                skills[0],skills[2],skills[4],
                skills[8],skills[11],skills[12],
                skills[18]
            ),
            key = "skill"
        ),
        SelectorBox(
            title = "Equipment",
            numberOfChoices = 2,
            listOfChoicesOne = listOf("Greataxe","Any Martial Melee Weapon"),
            listOfChoicesTwo = listOf("2 Handaxes","Any Simple Weapon"),
            key = "equipment"
        )
    )
    val classBard: List<SelectorBox> = listOf(
        SelectorBox(
            title = "Skill Proficiency",
            numberOfChoices = 3,
            listOfChoicesOne = skills,
            listOfChoicesTwo = skills,
            listOfChoicesThree = skills,
            key = "skill"
        ),
        SelectorBox(
            title = "Equipment",
            numberOfChoices = 3,
            listOfChoicesOne = listOf("Rapier","Longsword","Any Simple Weapon"),
            listOfChoicesTwo = listOf("Diplomat's Pack","Entertainer's Pack"),
            listOfChoicesThree = listOf("Lute","Any Musical Instrument"),
            key = "equipment"
        )
    )
    val classCleric: List<SelectorBox> = listOf(
        SelectorBox(
            title = "Skill Proficiency",
            numberOfChoices = 2,
            listOfChoicesOne = listOf(
                skills[0],skills[6],skills[7],
                skills[10],skills[14],skills[15]
            ),
            listOfChoicesTwo = listOf(
                skills[0],skills[6],skills[7],
                skills[10],skills[14],skills[15]
            ),
            key = "skill"
        ),
        SelectorBox(
            title = "Equipment",
            numberOfChoices = 4,
            listOfChoicesOne = listOf("Mace","Warhammer"),
            listOfChoicesTwo = listOf("Scale Mail","Leather Armor","Chain Mail"),
            listOfChoicesThree = listOf("Light Crossbow + 20 bolts","Any Simple Weapon"),
            listOfChoicesFour = listOf("Priest's Pack","Explorer's Pack"),
            key = "equipment"
        ),
        SelectorBox(
            title = "Divine Domain",
            listOfChoicesOne = listOf(
                "Choose","Knowledge","Life","Light",
                "Nature","Tempest","Trickery","War"
            ),
            key = "subclass"
        )
    )
    val classDruid: List<SelectorBox> = listOf(
        SelectorBox(
            title = "Skill Proficiency",
            numberOfChoices = 2,
            listOfChoicesOne = listOf(
                skills[0],skills[2],skills[3],
                skills[7],skills[10],skills[11],
                skills[12],skills[15],skills[18]
            ),
            listOfChoicesTwo = listOf(
                skills[0],skills[2],skills[3],
                skills[7],skills[10],skills[11],
                skills[12],skills[15],skills[18]
            ),
            key = "skill"
        ),
        SelectorBox(
            title = "Equipment",
            numberOfChoices = 2,
            listOfChoicesOne = listOf("Wooden Shield","Any Simple Weapon"),
            listOfChoicesTwo = listOf("Scimitar","Any Simple Melee Weapon"),
            key = "equipment"
        )
    )
    val classFighter: List<SelectorBox> = listOf(
        SelectorBox(
            title = "Skill Proficiency",
            numberOfChoices = 2,
            listOfChoicesOne = listOf(
                skills[0],skills[1],skills[2],
                skills[4],skills[6],skills[7],
                skills[8],skills[12],skills[18]
            ),
            listOfChoicesTwo = listOf(
                skills[0],skills[1],skills[2],
                skills[4],skills[6],skills[7],
                skills[8],skills[12],skills[18]
            ),
            key = "skill"
        ),
        SelectorBox(
            title = "Equipment",
            numberOfChoices = 4,
            listOfChoicesOne = listOf("Chain Mail","Leather Armor + Longbow + 20 Arrows"),
            listOfChoicesTwo = listOf("Any Martial Weapon + Shield","Any 2 Martial Weapons"),
            listOfChoicesThree = listOf("Light Crossbow + 20 Bolts","2 Handaxes"),
            listOfChoicesFour = listOf("Dungeoneer's Pack","Explorer's Pack"),
            key = "equipment"
        ),
        SelectorBox(
            title = "Fighting Style",
            listOfChoicesOne = listOf(
                "Archery","Defense","Dueling",
                "Great Weapon Fighting", "Protection",
                "Two-Weapon Fighting"
            ),
            key = "fighting_style"
        )
    )
    val classMonk: List<SelectorBox> = listOf(
        SelectorBox(
            title = "Skill Proficiency",
            numberOfChoices = 2,
            listOfChoicesOne = listOf(
                skills[0],skills[1],skills[4],
                skills[6],skills[7],skills[15],
                skills[17]
            ),
            listOfChoicesTwo = listOf(
                skills[0],skills[1],skills[4],
                skills[6],skills[7],skills[15],
                skills[17]
            ),
            key = "skill"
        ),
        SelectorBox(
            title = "Equipment",
            numberOfChoices = 2,
            listOfChoicesOne = listOf("Shortsword","Any Simple Weapon"),
            listOfChoicesTwo = listOf("Dungeoneer's Pack","Explorer's Pack"),
            key = "equipment"
        )
    )
    val classPaladin: List<SelectorBox> = listOf(
        SelectorBox(
            title = "Skill Proficiency",
            numberOfChoices = 2,
            listOfChoicesOne = listOf(
                skills[0],skills[4],skills[7],
                skills[8],skills[10],skills[14],
                skills[15],
            ),
            listOfChoicesTwo = listOf(
                skills[0],skills[4],skills[7],
                skills[8],skills[10],skills[14],
                skills[15],
            ),
            key = "skill"
        ),
        SelectorBox(
            title = "Equipment",
            numberOfChoices = 3,
            listOfChoicesOne = listOf("Any Martial Weapon + Shield","Any 2 Martial Weapons"),
            listOfChoicesTwo = listOf("5 Javelins","Any Simple Melee Weapon"),
            listOfChoicesThree = listOf("Priest's Pack","Explorer's Pack"),
            key = "equipment"
        )
    )
    val classRanger: List<SelectorBox> = listOf(
        SelectorBox(
            title = "Skill Proficiency",
            numberOfChoices = 3,
            listOfChoicesOne = listOf(
                skills[0],skills[2],skills[4],
                skills[7],skills[9],skills[11],
                skills[12],skills[17],skills[18]
            ),
            listOfChoicesTwo = listOf(
                skills[0],skills[2],skills[4],
                skills[7],skills[9],skills[11],
                skills[12],skills[17],skills[18]
            ),
            listOfChoicesThree = listOf(
                skills[0],skills[2],skills[4],
                skills[7],skills[9],skills[11],
                skills[12],skills[17],skills[18]
            ),
            key = "skill"
        ),
        SelectorBox(
            title = "Equipment",
            numberOfChoices = 3,
            listOfChoicesOne = listOf("Scale Mail","Leather Armor"),
            listOfChoicesTwo = listOf("2 Shortswords","2 Simple Melee Weapon"),
            listOfChoicesThree = listOf("Dungeoneer's Pack","Explorer's Pack"),
            key = "equipment"
        ),
        SelectorBox(
            title = "Favored Enemy",
            listOfChoicesOne = listOf(
                "Aberrations","Beasts","Celestials","Constructs",
                "Dragons","Elementals","Fey","Fiends","Giants",
                "Monstrosities","Oozes","Plants","Undead","2 Humanoid Races"
            ),
            key = "fav_enemy"
        ),
        SelectorBox(
            title = "Natural Explorer",
            listOfChoicesOne = listOf(
                "Arctic","Coast","Desert","Forest",
                "Grassland","Mountain","Swamp","Underdark"
            ),
            key = "fav_terrain"
        )
    )
    val classRogue: List<SelectorBox> = listOf(
        SelectorBox(
            title = "Skill Proficiency",
            numberOfChoices = 4,
            listOfChoicesOne = listOf(
                skills[0],skills[1],skills[4],
                skills[5],skills[7],skills[8],
                skills[9],skills[12],skills[13],
                skills[14],skills[16],skills[17]
            ),
            listOfChoicesTwo = listOf(
                skills[0],skills[1],skills[4],
                skills[5],skills[7],skills[8],
                skills[9],skills[12],skills[13],
                skills[14],skills[16],skills[17]
            ),
            listOfChoicesThree = listOf(
                skills[0],skills[1],skills[4],
                skills[5],skills[7],skills[8],
                skills[9],skills[12],skills[13],
                skills[14],skills[16],skills[17]
            ),
            listOfChoicesFour = listOf(
                skills[0],skills[1],skills[4],
                skills[5],skills[7],skills[8],
                skills[9],skills[12],skills[13],
                skills[14],skills[16],skills[17]
            ),
            key = "skill"
        ),
        SelectorBox(
            title = "Equipment",
            numberOfChoices = 3,
            listOfChoicesOne = listOf("Rapier","Shortsword"),
            listOfChoicesTwo = listOf("Shortbow + 20 Arrows","Shortsword"),
            listOfChoicesThree = listOf("Burglar's Pack","Dungeoneer's Pack","Explorer's Pack"),
            key = "equipment"
        ),
        SelectorBox(
            title = "Expertise",
            numberOfChoices = 2,
            listOfChoicesOne = listOf(
                skills[0],skills[1],skills[4],
                skills[5],skills[7],skills[8],
                skills[9],skills[12],skills[13],
                skills[14],skills[16],skills[17]
            ),
            listOfChoicesTwo = listOf(
                skills[0],skills[1],skills[4],
                skills[5],skills[7],skills[8],
                skills[9],skills[12],skills[13],
                skills[14],skills[16],skills[17]
            ),
            key = "expertise"
        )
    )
    val classSorcerer: List<SelectorBox> = listOf(
        SelectorBox(
            title = "Skill Proficiency",
            numberOfChoices = 2,
            listOfChoicesOne = listOf(
                skills[0],skills[3],skills[5],
                skills[7],skills[8],skills[14],
                skills[15]
            ),
            listOfChoicesTwo = listOf(
                skills[0],skills[3],skills[5],
                skills[7],skills[8],skills[14],
                skills[15]
            ),
            key = "skill"
        ),
        SelectorBox(
            title = "Equipment",
            numberOfChoices = 3,
            listOfChoicesOne = listOf("Light Crossbow + 20 bolts","Any Simple Weapon"),
            listOfChoicesTwo = listOf("Component Pouch","Arcane Focus"),
            listOfChoicesFour = listOf("Dungeoneer's Pack","Explorer's Pack"),
            key = "equipment"
        ),
        SelectorBox(
            title = "Sorcerous Origin",
            listOfChoicesOne = listOf("Choose", "Draconic Bloodline", "Wild Magic"),
            key = "subclass"
        )
    )
    val classWarlock: List<SelectorBox> = listOf(
        SelectorBox(
            title = "Skill Proficiency",
            numberOfChoices = 2,
            listOfChoicesOne = listOf(
                skills[0],skills[3],skills[5],
                skills[6],skills[8],skills[9],
                skills[11],skills[15]
            ),
            listOfChoicesTwo = listOf(
                skills[0],skills[3],skills[5],
                skills[6],skills[8],skills[9],
                skills[11],skills[15]
            ),
            key = "skill"
        ),
        SelectorBox(
            title = "Equipment",
            numberOfChoices = 3,
            listOfChoicesOne = listOf("Light Crossbow + 20 bolts","Any Simple Weapon"),
            listOfChoicesTwo = listOf("Component's Pouch","Arcane Focus"),
            listOfChoicesThree = listOf("Scholar's Pack","Dungeoneer's Pack"),
            key = "equipment"
        ),
        SelectorBox(
            title = "Otherworldly Patron",
            listOfChoicesOne = listOf("Archfey","Fiend","Great Old One"),
            key = "subclass"
        )
    )
    val classWizard: List<SelectorBox> = listOf(
        SelectorBox(
            title = "Skill Proficiency",
            numberOfChoices = 2,
            listOfChoicesOne = listOf(
                skills[0],skills[3],skills[6],
                skills[7],skills[9],skills[10],
                skills[15]
            ),
            listOfChoicesTwo = listOf(
                skills[0],skills[3],skills[6],
                skills[7],skills[9],skills[10],
                skills[15]
            ),
            key = "skill"
        ),
        SelectorBox(
            title = "Equipment",
            numberOfChoices = 3,
            listOfChoicesOne = listOf("Quarterstaff","Dagger"),
            listOfChoicesTwo = listOf("Component Pouch","Arcane Focus"),
            listOfChoicesThree = listOf("Scholar's Pack","Explorer's Pack"),
            key = "equipment"
        )
    )
    //Maps to Save Selection (Class)
    var classMapBarbarian: MutableMap<String,String> = mutableMapOf()
    var classMapBard: MutableMap<String,String> = mutableMapOf()
    var classMapCleric: MutableMap<String,String> = mutableMapOf()
    var classMapDruid: MutableMap<String,String> = mutableMapOf()
    var classMapFighter: MutableMap<String,String> = mutableMapOf()
    var classMapMonk: MutableMap<String,String> = mutableMapOf()
    var classMapPaladin: MutableMap<String,String> = mutableMapOf()
    var classMapRanger: MutableMap<String,String> = mutableMapOf()
    var classMapRogue: MutableMap<String,String> = mutableMapOf()
    var classMapSorcerer: MutableMap<String,String> = mutableMapOf()
    var classMapWarlock: MutableMap<String,String> = mutableMapOf()
    var classMapWizard: MutableMap<String,String> = mutableMapOf()
    val classSaveMaps: List<MutableMap<String,String>> = listOf(
        classMapBarbarian,classMapBard,classMapCleric,
        classMapDruid,classMapFighter,classMapMonk,
        classMapPaladin,classMapRanger,classMapRogue,
        classMapSorcerer,classMapWarlock,classMapWizard
    )

    fun resetMaps() {
        //races
        raceMapDragonborn["alignment"] = "Choose"
        raceMapDragonborn["subrace"] = "Choose"

        raceMapDwarf["alignment"] = "Choose"
        raceMapDwarf["tool_prof"] = "Smith's Tools"
        raceMapDwarf["subrace"] = "Choose"

        raceMapElf["alignment"] = "Choose"
        raceMapElf["subrace"] = "Choose"

        raceMapGnome["alignment"] = "Choose"
        raceMapGnome["subrace"] = "Choose"

        raceMapHalfElf["alignment"] = "Choose"
        raceMapHalfElf["asi:0"] = "Choose"
        raceMapHalfElf["asi:1"] = "Choose"
        raceMapHalfElf["skill:0"] = "Choose"
        raceMapHalfElf["skill:1"] = "Choose"
        raceMapHalfElf["language"] = "Choose"

        raceMapHalfOrc["alignment"] = "Choose"

        raceMapHalfling["alignment"] = "Choose"
        raceMapHalfling["subrace"] = "Choose"

        raceMapHuman["alignment"] = "Choose"
        raceMapHuman["language"] = "Choose"

        raceMapTiefling["alignment"] = "Choose"

        //classes
        classMapBarbarian["skill:0"] = "Choose"
        classMapBarbarian["skill:1"] = "Choose"
        classMapBarbarian["equipment:0"] = "0"
        classMapBarbarian["equipment:1"] = "0"

        classMapBard["skill:0"] = "Choose"
        classMapBard["skill:1"] = "Choose"
        classMapBard["skill:2"] = "Choose"
        classMapBard["equipment:0"] = "0"
        classMapBard["equipment:1"] = "0"
        classMapBard["equipment:2"] = "0"

        classMapCleric["skill:0"] = "Choose"
        classMapCleric["skill:1"] = "Choose"
        classMapCleric["equipment:0"] = "0"
        classMapCleric["equipment:1"] = "0"
        classMapCleric["equipment:2"] = "0"
        classMapCleric["equipment:3"] = "0"
        classMapCleric["subclass"] = "Choose"

        classMapDruid["skill:0"] = "Choose"
        classMapDruid["skill:1"] = "Choose"
        classMapDruid["equipment:0"] = "0"
        classMapDruid["equipment:1"] = "0"

        classMapFighter["skill:0"] = "Choose"
        classMapFighter["skill:1"] = "Choose"
        classMapFighter["equipment:0"] = "0"
        classMapFighter["equipment:1"] = "0"
        classMapFighter["equipment:2"] = "0"
        classMapFighter["equipment:3"] = "0"
        classMapFighter["fighting_style"] = "Archery"

        classMapMonk["skill:0"] = "Choose"
        classMapMonk["skill:1"] = "Choose"
        classMapMonk["equipment:0"] = "0"
        classMapMonk["equipment:1"] = "0"

        classMapPaladin["skill:0"] = "Choose"
        classMapPaladin["skill:1"] = "Choose"
        classMapPaladin["equipment:0"] = "0"
        classMapPaladin["equipment:1"] = "0"
        classMapPaladin["equipment:2"] = "0"

        classMapRanger["skill:0"] = "Choose"
        classMapRanger["skill:1"] = "Choose"
        classMapRanger["skill:2"] = "Choose"
        classMapRanger["equipment:0"] = "0"
        classMapRanger["equipment:1"] = "0"
        classMapRanger["equipment:2"] = "0"
        classMapRanger["fav_enemy"] = "Aberrations"
        classMapRanger["fav_terrain"] = "Arctic"

        classMapRogue["skill:0"] = "Choose"
        classMapRogue["skill:1"] = "Choose"
        classMapRogue["skill:2"] = "Choose"
        classMapRogue["skill:3"] = "Choose"
        classMapRogue["equipment:0"] = "0"
        classMapRogue["equipment:1"] = "0"
        classMapRogue["equipment:2"] = "0"
        classMapRogue["expertise:0"] = "Choose"
        classMapRogue["expertise:1"] = "Choose"

        classMapSorcerer["skill:0"] = "Choose"
        classMapSorcerer["skill:1"] = "Choose"
        classMapSorcerer["equipment:0"] = "0"
        classMapSorcerer["equipment:1"] = "0"
        classMapSorcerer["equipment:2"] = "0"
        classMapSorcerer["subclass"] = "Choose"

        classMapWarlock["skill:0"] = "Choose"
        classMapWarlock["skill:1"] = "Choose"
        classMapWarlock["equipment:0"] = "0"
        classMapWarlock["equipment:1"] = "0"
        classMapWarlock["equipment:2"] = "0"
        classMapWarlock["subclass"] = "Choose"

        classMapWizard["skill:0"] = "Choose"
        classMapWizard["skill:1"] = "Choose"
        classMapWizard["equipment:0"] = "0"
        classMapWizard["equipment:1"] = "0"
        classMapWizard["equipment:2"] = "0"
    }

    suspend fun loadSpellApi() {
        var i = 1
        var morePages = true
        while (morePages) {
            var result: SpellResult? = null
            result = spellApi.retrofitService.getSpellResults(i)
            Log.d("MainViewModel", result.toString())
            if (result.next != null && result.next != "null") {
                i++
            }
            else morePages = false
            for (spell in result.results) {
                database.spellDao.insert(spell)
            }
        }
        spellList = database.spellDao.getAll()

    }
}