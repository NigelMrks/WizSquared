package com.nigel.marks.wizsquared.data.model

data class SelectorBox(
    val title: String = "title_text",
    val numberOfChoices: Int = 1,
    val listOfChoicesOne: List<String> = listOf("choice_first"),
    val listOfChoicesTwo: List<String> = listOf("choice_first"),
    val listOfChoicesThree: List<String> = listOf("choice_first"),
    val listOfChoicesFour: List<String> = listOf("choice_first"),
    val key: String = "key_to_map"
)