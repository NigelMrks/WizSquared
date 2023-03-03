package com.nigel.marks.wizsquared.data.model

data class SelectorBox(
    val title: String,
    val hasChoice: Boolean,
    val choices: List<String>,
    val hasMultipleDesc: Boolean,
    val descriptions: List<String>,
    val hasDesc: Boolean = true
)
