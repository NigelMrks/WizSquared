package com.nigel.marks.wizsquared.data.model

data class RaceSelector(
    val Title: String,
    val hasChoice: Boolean,
    val choices: List<String>,
    val hasMultipleDesc: Boolean,
    val descriptions: List<String>
)
