package com.example.teervoetpuntjesapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.teervoetpuntjesapp.data.badge.BadgeDao
import com.example.teervoetpuntjesapp.data.badge.BadgeEntity
import com.example.teervoetpuntjesapp.data.puntje.PuntjeDao
import com.example.teervoetpuntjesapp.data.puntje.PuntjeEntity

/**
 * Room database class voor de Teervoetpuntjes applicatie.
 *
 * Deze klasse representeert de database die gebruikt wordt om badges en puntjes lokaal op te slaan.
 */
@Database(entities = [BadgeEntity::class, PuntjeEntity::class], version = 1, exportSchema = false)
abstract class TeervoetAppDatabase : RoomDatabase() {

    /**
     * Retourneert de BadgeDao interface voor interactie met badge data in de database.
     *
     * @return De BadgeDao interface.
     */
    abstract fun badgeDao(): BadgeDao

    /**
     * Retourneert de PuntjeDao interface voor interactie met puntje data in de database.
     *
     * @return De PuntjeDao interface.
     */
    abstract fun puntjeDao(): PuntjeDao

    companion object {
        @Volatile
        private var Instance: TeervoetAppDatabase? = null

        /**
         * Haalt een instantie op van de TeervoetAppDatabase, of creëert een nieuwe instantie als deze nog niet bestaat.
         *
         * Deze methode gebruikt het Singleton patroon om ervoor te zorgen dat er slechts één instantie
         * van de database bestaat.
         *
         * @param context De Context van de applicatie.
         * @return Een instantie van de TeervoetAppDatabase.
         */
        fun getDatabase(context: Context): TeervoetAppDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, TeervoetAppDatabase::class.java, "Teervoet_database")
                    .build().also { Instance = it }
            }
        }
    }
}