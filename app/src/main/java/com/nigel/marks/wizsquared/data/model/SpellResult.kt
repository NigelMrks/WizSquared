package com.nigel.marks.wizsquared.data.model

import com.nigel.marks.wizsquared.data.entities.Spell

data class SpellResult (
    val count: Int,
    val next: String?,
    val results: List<Spell>
)
