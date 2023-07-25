package com.example.freegamesapp.data.db.games

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.freegamesapp.data.db.games.dao.GameDao
import com.example.freegamesapp.data.entities.GameDb

@Database(
    entities = [GameDb::class],
    version = 1,
    exportSchema = false
)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
}