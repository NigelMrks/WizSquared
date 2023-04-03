package com.nigel.marks.wizsquared.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nigel.marks.wizsquared.data.entities.PlayerCharacter

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: PlayerCharacter)

    @Update
    suspend fun update(character: PlayerCharacter)

    @Query("SELECT * FROM player_characters")
    fun getAll(): LiveData<List<PlayerCharacter>>

    @Query("DELETE FROM player_characters")
    suspend fun deleteAll()
}