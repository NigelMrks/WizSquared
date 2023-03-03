package com.nigel.marks.wizsquared.data

import android.text.Layout.Alignment
import com.nigel.marks.wizsquared.data.model.SelectorBox

class Repository {

    private val alignments: List<String> = listOf(
        "Choose",
        "Lawful Good", "Neutral Good", "Chaotic Good",
        "Lawful Neutral", "Neutral", "Chaotic Neutral",
        "Lawful Evil", "Neutral Evil", "Chaotic Evil"
    )

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
    var raceDragonborn: List<SelectorBox> = listOf(
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
    var raceDwarf: List<SelectorBox> = listOf(
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

}