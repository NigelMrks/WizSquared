package com.nigel.marks.wizsquared.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Spell(
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val desc: String,
    val higher_level: String,
    val range: String,
    val components: String,
    val material: String,
    val ritual: String,
    val duration: String,
    val concentration: String,
    val casting_time: String,
    val level_int: Int,
    val school: String,
    val dnd_class: String,
)
