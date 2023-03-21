package com.nigel.marks.wizsquared.data.model

data class SelectorBox(
    val title: String = "",
    val hasChoice: Boolean = false,
    val choicesOne: List<String> = listOf(),
    val hasMultipleDesc: Boolean = false,
    val descriptions: List<String> = listOf(),
    val hasDesc: Boolean = true,
    val numberOfChoices: Int = 1,
    val choicesAreSame: Boolean = false,
    var choicesTwo: List<String> = listOf("Choose"),
    var choicesThree: List<String> = listOf("Choose"),
    var choicesFour: List<String> = listOf("Choose"),
    var selectionSave: SelectionSave = SelectionSave(title)
)
