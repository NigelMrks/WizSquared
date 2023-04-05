package com.nigel.marks.wizsquared.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nigel.marks.wizsquared.data.entities.Spell

@Database(entities = [Spell::class], version = 3)
abstract class SpellDatabase : RoomDatabase() {

    abstract val spellDao: SpellDao
}

private lateinit var INSTANCE: SpellDatabase

fun getDatabase(context: Context): SpellDatabase {
    synchronized(SpellDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                SpellDatabase::class.java,
                "spell_list"
            ).build()
        }
    }
    return INSTANCE
}