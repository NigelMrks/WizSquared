package com.nigel.marks.wizsquared.data

import com.nigel.marks.wizsquared.data.model.Equipment
import com.nigel.marks.wizsquared.data.model.SelectorBox

class Repository {
    //List of Alignments as Strings
    private val alignments: List<String> = listOf(
        "Choose",
        "Lawful Good", "Neutral Good", "Chaotic Good",
        "Lawful Neutral", "Neutral", "Chaotic Neutral",
        "Lawful Evil", "Neutral Evil", "Chaotic Evil"
    )

    //EquipmentLists
    var eqBarbarian: List<List<Equipment>> = listOf(
        listOf(
            Equipment("Greataxe",1),
            Equipment("Any Martial Melee Weapon",1)
        ),
        listOf(
            Equipment("Handaxe",2),
            Equipment("Any Simple Weapon",1)
        ),
        listOf(
            Equipment("Explorer's Pack",1),
            Equipment("Javelin",4)
        )
    )

    //Starting Wealth
    var noClassWealth: Pair<Int,Boolean> = Pair(0,false)
    var barbarianWealth: Pair<Int,Boolean> = Pair(2,true)
    var bardWealth: Pair<Int,Boolean> = Pair(5,true)
    var clericWealth: Pair<Int,Boolean> = Pair(5,true)
    var druidWealth: Pair<Int,Boolean> = Pair(2,true)
    var fighterWealth: Pair<Int,Boolean> = Pair(5,true)
    var monkWealth: Pair<Int,Boolean> = Pair(5,false)
    var paladinWealth: Pair<Int,Boolean> = Pair(5,true)
    var rangerWealth: Pair<Int,Boolean> = Pair(5,true)
    var rogueWealth: Pair<Int,Boolean> = Pair(4,true)
    var sorcererWealth: Pair<Int,Boolean> = Pair(3,true)
    var warlockWealth: Pair<Int,Boolean> = Pair(4,true)
    var wizardWealth: Pair<Int,Boolean> = Pair(4,true)
    var classWealthList: List<Pair<Int,Boolean>> = listOf(
        noClassWealth,barbarianWealth,bardWealth,clericWealth,druidWealth,fighterWealth,monkWealth,
        paladinWealth,rangerWealth,rogueWealth,sorcererWealth,warlockWealth,wizardWealth
    )

    //Lists of Race Attributes for Character Creation as SelectorBox Objects
    var raceNoneSelected: List<SelectorBox> = listOf(
        SelectorBox(
            "Racial Traits",
            false,
            listOf(),
            false,
            listOf(
                "The description of each race includes racial Traits that are Common to Members " +
                        "of that race. The following entries appear among the Traits of most races."
            )
        ),
        SelectorBox(
            "Ability Score Increase",
            false,
            listOf(),
            false,
            listOf(
                "Every race increases one or more of a character’s Ability Scores."
            )
        ),
        SelectorBox(
            "Age",
            false,
            listOf(),
            false,
            listOf(
                "The age entry notes the age when a member of the race is considered an adult, " +
                        "as well as the race’s expected lifespan. This information can help you " +
                        "decide how old your character is at the start of the game. You can " +
                        "choose any age for your character, which could provide an explanation " +
                        "for some of your Ability Scores. For example, if you play a young or " +
                        "very old character, your age could explain a particularly low Strength " +
                        "or Constitution score, while advanced age could account for a high " +
                        "Intelligence or Wisdom."
            )
        ),
        SelectorBox(
            "Alignment",
            false,
            listOf(),
            false,
            listOf(
                "Most races have tendencies toward certain alignments, described in this entry. " +
                        "These are not binding for player Characters, but considering why your " +
                        "dwarf is chaotic, for example, in defiance of lawful dwarf Society can " +
                        "help you better define your character."
            )
        ),
        SelectorBox(
            "Size",
            false,
            listOf(),
            false,
            listOf(
                "Characters of most races are Medium, a size category including Creatures that " +
                        "are roughly 4 to 8 feet tall. Members of a few races are Small (between " +
                        "2 and 4 feet tall), which means that certain rules of the game affect " +
                        "them differently. The most important of these rules is that Small " +
                        "Characters have trouble Wielding heavy Weapons, as explained in “Equipment.”"
            )
        ),
        SelectorBox(
            "Speed",
            false,
            listOf(),
            false,
            listOf(
                "Your speed determines how far you can move when traveling ( “Adventuring”) and " +
                        "Fighting (“Combat”)."
            )
        ),
        SelectorBox(
            "Languages",
            false,
            listOf(),
            false,
            listOf(
                "By virtue of your race, your character can speak, read, and write certain Languages."
            )
        ),
        SelectorBox(
            "Subraces",
            false,
            listOf(),
            false,
            listOf(
                "Some races have Subraces. Members of a subrace have the Traits of the parent " +
                        "race in addition to the Traits specified for their subrace. Relationships " +
                        "among Subraces vary significantly from race to race and world to world."
            )
        )
    )
    var raceDragonborn: List<SelectorBox> = listOf()
    var raceDwarf: List<SelectorBox> = listOf()

    //Lists of Class Attributes for Character Creation as SelectorBoxObjects
    var classNoneSelected: List<SelectorBox> = listOf(
        SelectorBox(
            "Class",
            false,
            listOf(),
            false,
            listOf(
                "Your Class will determine the playstyle and initial bulk of abilities of your " +
                        "character. It determines if you are more suited for Martial Combat, " +
                        "Spellcasting or else and determines from what source your Character " +
                        "draws their power. \n \n" +
                        "A character doesn't necessarily only have one class. For further " +
                        "information on this look inti Multi-Classing options."
            )
        ),
        SelectorBox(
            "Subclass",
            false,
            listOf(),
            false,
            listOf(
                "Every class has the choice between multiple subclasses. Subclasses can " +
                        "seperate a character from other members of the same Class by detailing " +
                        "the source of your abilities. From what School of Magic your Wizard has " +
                        "studied intensely to what God your Cleric follows. \n \n" +
                        "Characters gain Subclass Features at specified levels taken in a " +
                        "particular Class and choose their Subclass sometime at level 1,2 or 3 " +
                        "(depending on Class)"
            )
        )
    )
    var classBarbarian: List<SelectorBox> = listOf()

    fun loadLists() {
        //Selector Boxes
        //races
        raceDragonborn = listOf(
            SelectorBox(
                "Ability Score Increase",
                false,
                listOf(),
                false,
                listOf(
                    "Charisma +1, Strength +2"
                )
            ),
            SelectorBox(
                "Alignment",
                true,
                alignments,
                false,
                listOf(""),
                false
            ),
            SelectorBox(
                "Creature Type",
                false,
                listOf(),
                false,
                listOf(
                    "Humanoid"
                )
            ),
            SelectorBox(
                "Size",
                false,
                listOf(),
                false,
                listOf(
                    "Medium"
                )
            ),
            SelectorBox(
                "Speed",
                false,
                listOf(),
                false,
                listOf(
                    "30 feet"
                )
            ),
            SelectorBox(
                "Draconic Ancestry",
                true,
                listOf(
                    "Choose",
                    "Black Dragon Ancestry",
                    "Blue Dragon Ancestry",
                    "Brass Dragon Ancestry",
                    "Bronze Dragon Ancestry",
                    "Copper Dragon Ancestry",
                    "Gold Dragon Ancestry",
                    "Green Dragon Ancestry",
                    "Red Dragon Ancestry",
                    "Silver Dragon Ancestry",
                    "White Dragon Ancestry"
                ),
                true,
                listOf(
                    "You have draconic ancestry. Choose one type of dragon from the Draconic " +
                            "Ancestry table. Your breath weapon and damage resistance are " +
                            "determined by the dragon type, as follows: \n\n" +
                            "None selected.",
                    "You have draconic ancestry. Choose one type of dragon from the Draconic " +
                            "Ancestry table. Your breath weapon and damage resistance are " +
                            "determined by the dragon type, as follows: \n\n" +
                            "Damage Type: Acid. \n" +
                            "Breath Weapon: 5 by 30 ft. line (Dex. save)",
                    "You have draconic ancestry. Choose one type of dragon from the Draconic " +
                            "Ancestry table. Your breath weapon and damage resistance are " +
                            "determined by the dragon type, as follows: \n\n" +
                            "Damage Type: Lightning. \n" +
                            "Breath Weapon: 5 by 30 ft. line (Dex. save)",
                    "You have draconic ancestry. Choose one type of dragon from the Draconic " +
                            "Ancestry table. Your breath weapon and damage resistance are " +
                            "determined by the dragon type, as follows: \n\n" +
                            "Damage Type: Fire. \n" +
                            "Breath Weapon: 5 by 30 ft. line (Dex. save)",
                    "You have draconic ancestry. Choose one type of dragon from the Draconic " +
                            "Ancestry table. Your breath weapon and damage resistance are " +
                            "determined by the dragon type, as follows: \n\n" +
                            "Damage Type: Lightning. \n" +
                            "Breath Weapon: 5 by 30 ft. line (Dex. save)",
                    "You have draconic ancestry. Choose one type of dragon from the Draconic " +
                            "Ancestry table. Your breath weapon and damage resistance are " +
                            "determined by the dragon type, as follows: \n\n" +
                            "Damage Type: Acid. \n" +
                            "Breath Weapon: 5 by 30 ft. line (Dex. save)",
                    "You have draconic ancestry. Choose one type of dragon from the Draconic " +
                            "Ancestry table. Your breath weapon and damage resistance are " +
                            "determined by the dragon type, as follows: \n\n" +
                            "Damage Type: Fire. \n" +
                            "15 ft cone (Con. save)",
                    "You have draconic ancestry. Choose one type of dragon from the Draconic " +
                            "Ancestry table. Your breath weapon and damage resistance are " +
                            "determined by the dragon type, as follows: \n\n" +
                            "Damage Type: Poison. \n" +
                            "15 ft cone (Con. save)",
                    "You have draconic ancestry. Choose one type of dragon from the Draconic " +
                            "Ancestry table. Your breath weapon and damage resistance are " +
                            "determined by the dragon type, as follows: \n\n" +
                            "Damage Type: Fire. \n" +
                            "15 ft cone (Con. save)",
                    "You have draconic ancestry. Choose one type of dragon from the Draconic " +
                            "Ancestry table. Your breath weapon and damage resistance are " +
                            "determined by the dragon type, as follows: \n\n" +
                            "Damage Type: Cold. \n" +
                            "15 ft cone (Con. save)",
                    "You have draconic ancestry. Choose one type of dragon from the Draconic " +
                            "Ancestry table. Your breath weapon and damage resistance are " +
                            "determined by the dragon type, as follows: \n\n" +
                            "Damage Type: Cold. \n" +
                            "15 ft cone (Con. save)",
                )
            ),
            SelectorBox(
                "Languages",
                false,
                listOf(),
                false,
                listOf(
                    "Languages\n" +
                            "You can speak, read, and write Common and Draconic. Draconic is thought " +
                            "to be one of the oldest languages and is often used in the study of " +
                            "magic. The language sounds harsh to most other creatures and includes " +
                            "numerous hard consonants and sibilants.\n" +
                            "\n" +
                            "Language Proficiencies:\n" +
                            "Common, Draconic"
                )
            )
        )
        raceDwarf = listOf(
            SelectorBox(
                "Ability Score Increase",
                false,
                listOf(),
                false,
                listOf(
                    "Constitution +2"
                )
            ),
            SelectorBox(
                "Alignment",
                true,
                alignments,
                false,
                listOf(""),
                false
            ),
            SelectorBox(
                "Creature Type",
                false,
                listOf(),
                false,
                listOf(
                    "Humanoid"
                )
            ),
            SelectorBox(
                "Size",
                false,
                listOf(),
                false,
                listOf(
                    "Medium"
                )
            ),
            SelectorBox(
                "Speed",
                false,
                listOf(),
                false,
                listOf(
                    "25 feet"
                )
            ),
            SelectorBox(
                "Weapon Proficiencies",
                false,
                listOf(),
                false,
                listOf(
                    "Battleaxe, Handaxe, Light Hammer, Warhammer"
                )
            ),
            SelectorBox(
                "Tool Proficiencies",
                true,
                listOf(
                    "Choose",
                    "Smith's Tools",
                    "Brewer's Supplies",
                    "Mason's Tools"
                ),
                false,
                listOf(""),
                false
            ),
            SelectorBox(
                "Languages",
                false,
                listOf(),
                false,
                listOf(
                    "Languages\n" +
                            "You can speak, read, and write Common and Dwarvish. Dwarvish is full of " +
                            "hard consonants and guttural sounds, and those characteristics spill " +
                            "over into whatever other language a dwarf might speak.\n" +
                            "\n" +
                            "Language Proficiencies:\n" +
                            "Common, Dwarvish"
                )
            ),
            SelectorBox(
                "Speed",
                false,
                listOf(),
                false,
                listOf(
                    "Your speed is not reduced by wearing heavy armor."
                )
            ),
            SelectorBox(
                "Stonecunning",
                false,
                listOf(),
                false,
                listOf(
                    "Whenever you make an Intelligence (History) check related to the origin of " +
                            "stonework, you are considered proficient in the History skill and " +
                            "add double your proficiency bonus to the check, instead of your " +
                            "normal proficiency bonus."
                )
            ),
            SelectorBox(
                "Dwarven Resilience",
                false,
                listOf(),
                false,
                listOf(
                    "You have advantage on saving throws against poison, and you have resistance " +
                            "against poison damage"
                )
            ),
            SelectorBox(
                "Darkvision",
                false,
                listOf(),
                false,
                listOf(
                    "Accustomed to life underground, you have superior vision in dark and dim " +
                            "conditions. You can see in dim light within 60 feet of you as if it " +
                            "were bright light, and in darkness as if it were dim light. You can’t " +
                            "discern color in darkness, only shades of gray."
                )
            ),
            SelectorBox(
                "Subrace",
                true,
                listOf(
                    "Choose",
                    "Hill Dwarf"
                ),
                true,
                listOf(
                    "Please choose a Subrace.",
                    "Subrace Ability Score Increase \n" +
                            "Wisdom +1 \n\n" +
                            "Dwarven Toughness \n" +
                            "Your hit point maximum increases by 1, and it increases by 1" +
                            "everytime you gain a level."
                )
            )
        )

        //classes
        classBarbarian = listOf(
            SelectorBox(
                "Hit Points",
                false,
                listOf(),
                false,
                listOf(
                    "Hit Dice: 1d12 per Barbarian level\n" +
                            "Hit Points at 1st Level: 12 + your Constitution modifier\n" +
                            "Hit Points at Higher Levels: 1d12 (or 7) + your Constitution modifier " +
                            "per Barbarian level after 1st"
                )
            ),
            SelectorBox(
                "Weapon Proficiencies",
                false,
                listOf(),
                false,
                listOf(
                    "Simple weapons, Martial weapons"
                )
            ),
            SelectorBox(
                "Armor Proficiencies",
                false,
                listOf(),
                false,
                listOf(
                    "Light Armor, Medium Armor, Shields"
                )
            ),
            SelectorBox(
                "Skill Proficiencies",
                true,
                listOf(
                    "Choose",
                    "Animal Handling",
                    "Athletics",
                    "Intimidation",
                    "Nature",
                    "Perception",
                    "Survival"
                ),
                false,
                listOf(),
                false,
                2,
                true
            ),
            SelectorBox(
                "Rage",
                false,
                listOf(),
                false,
                listOf(
                    "In battle, you fight with primal ferocity. On your turn, you can enter a rage " +
                            "as a bonus action. While raging, you gain the following benefits if you " +
                            "aren’t wearing heavy armor: -You have advantage on Strength checks and " +
                            "Strength saving throws. -When you make a melee weapon attack using " +
                            "Strength, you gain a bonus to the damage roll that increases as you " +
                            "gain levels as a barbarian, as shown in the Rage Damage column of the " +
                            "Barbarian table. -You have resistance to bludgeoning, piercing, and " +
                            "slashing damage. If you are able to cast spells, you can’t cast them or " +
                            "concentrate on them while raging. Your rage lasts for 1 minute. It " +
                            "ends early if you are knocked unconscious or if your turn ends and you " +
                            "haven’t attacked a hostile creature since your last turn or taken " +
                            "damage since then. You can also end your rage on your turn as a bonus " +
                            "action. Once you have raged the number of times shown for your barbarian " +
                            "level in the Rages column of the Barbarian table, you must finish a long " +
                            "rest before you can rage again."
                )
            ),
            SelectorBox(
                "Unarmored Defense",
                false,
                listOf(),
                false,
                listOf(
                    "While you are not wearing any armor, your Armor Class equals 10 + your " +
                            "Dexterity modifier + your Constitution modifier. You can use a shield " +
                            "and still gain this benefit."
                )
            ),
            SelectorBox(
                "Equipment",
                true,
                listOf(
                    "- A greataxe",
                    "- Any martial melee weapon"
                ),
                false,
                listOf(
                    "- An explorer’s pack and four javelins"
                ),
                true,
                2,
                false,
                listOf(
                    "- Two handaxes",
                    "- Any simple weapon"
                )
            )
        )
    }
}