package com.example.freegamesapp.data.db.games.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.freegamesapp.data.entities.GameDb

@Dao
interface GameDao {

    @Query("SELECT * FROM games")
    fun getGames(): List<GameDb>

    @Query("SELECT * FROM games WHERE id = :gameId")
    fun getGameById(gameId: Int): GameDb

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGames(games: List<GameDb>)
}