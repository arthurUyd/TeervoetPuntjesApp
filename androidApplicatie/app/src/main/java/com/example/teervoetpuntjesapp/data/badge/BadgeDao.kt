package com.example.teervoetpuntjesapp.data.badge

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BadgeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(badge: List<BadgeEntity>)

//    @Update
//    suspend fun update(badge: BadgeEntity)
//
//    @Delete
//    suspend fun delete(badge: BadgeEntity)

    @Query("select * from badges order by id asc")
    fun getAllBadges(): Flow<List<BadgeEntity>>

    @Query("select * from badges where id = :id")
    fun getBadge(id: Int): Flow<BadgeEntity>
}
