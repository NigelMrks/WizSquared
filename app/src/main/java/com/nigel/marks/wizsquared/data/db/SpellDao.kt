package com.nigel.marks.wizsquared.data.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.nigel.marks.wizsquared.data.entities.Spell

@Dao
interface SpellDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(spell: Spell)

    @Update
    suspend fun update(spell: Spell)

    @Query("SELECT * FROM Spell")
    fun getAll(): LiveData<List<Spell>>

    @Query("SELECT * FROM Spell WHERE dnd_class LIKE '%' || :selClass || '%' AND level_int <= :toLevel ORDER BY level_int")
    fun filterSpells(selClass: String?, toLevel: Int): LiveData<List<Spell>>

    @Query("DELETE FROM Spell")
    suspend fun deleteAll()
}