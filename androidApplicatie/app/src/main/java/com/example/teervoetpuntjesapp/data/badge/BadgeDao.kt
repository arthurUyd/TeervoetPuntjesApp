package com.example.teervoetpuntjesapp.data.badge

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) interface voor badges in de Teervoetpuntjes applicatie.
 *
 * Deze interface definieert methodes voor interactie met de badge-tabel in de Room database.
 */
@Dao
interface BadgeDao {

    /**
     * Voegt een lijst met BadgeEntity objecten toe aan de room database.
     *
     * Indien er reeds een badge met dezelfde ID bestaat, wordt deze genegeerd
     * (OnConflictStrategy.IGNORE).
     *
     * @param badge De lijst met BadgeEntity objecten om toe te voegen.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(badge: List<BadgeEntity>)

    /**
     * Haalt alle badges op uit de database.
     *
     * @return Flow<List<BadgeEntity>> Een Flow met alle badges uit de room database.
     */
    @Query("select * from badges order by id asc")
    fun getAllBadges(): Flow<List<BadgeEntity>>

    /**
     * Haalt een badge op met een specifiek ID.
     *
     * @param id De ID van de badge die opgehaald moet worden.
     * @return Flow<BadgeEntity> Een Flow met de gevraagde badge of null indien de badge niet gevonden is.
     */
    @Query("select * from badges where id = :id")
    fun getBadge(id: Int): Flow<BadgeEntity>
}
