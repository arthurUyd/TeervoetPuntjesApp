package com.example.teervoetpuntjesapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BadgeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: dbBadge)

    @Update
    suspend fun update(item: dbBadge)

    @Delete
    suspend fun delete(item: dbBadge)

    @Query("select * from badge where titel = :titel")
    fun getBadge(titel: String): Flow<dbBadge>

    @Query("select * from badge")
    fun getAllBadges(): Flow<List<dbBadge>>
}