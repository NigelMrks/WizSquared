package com.nigel.marks.wizsquared.data

import com.nigel.marks.wizsquared.data.model.RaceSelector

class Repository {

    var raceNoneSelected: List<RaceSelector> = listOf(
        RaceSelector(
            "Racial Traits",
            false,
            listOf(),
            false,
            listOf(
                "The description of each race includes racial Traits that are Common to Members " +
                        "of that race. The following entries appear among the Traits of most races."
            )
        ),
        RaceSelector(
            "Ability Score Increase",
            false,
            listOf(),
            false,
            listOf(
                "Every race increases one or more of a character’s Ability Scores."
            )
        ),
        RaceSelector(
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
        RaceSelector(
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
        RaceSelector(
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
        RaceSelector(
            "Speed",
            false,
            listOf(),
            false,
            listOf(
                "Your speed determines how far you can move when traveling ( “Adventuring”) and " +
                        "Fighting (“Combat”)."
            )
        ),
        RaceSelector(
            "Languages",
            false,
            listOf(),
            false,
            listOf(
                "By virtue of your race, your character can speak, read, and write certain Languages."
            )
        ),
        RaceSelector(
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

}