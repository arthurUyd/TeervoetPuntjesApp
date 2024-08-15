package com.example.teervoetpuntjesapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.teervoetpuntjesapp.data.badge.BadgeDao
import com.example.teervoetpuntjesapp.data.badge.BadgeEntity
import com.example.teervoetpuntjesapp.data.puntje.PuntjeDao
import com.example.teervoetpuntjesapp.data.puntje.PuntjeEntity

@Database(entities = [BadgeEntity::class, PuntjeEntity::class], version = 1, exportSchema = false)
abstract class TeervoetAppDatabase : RoomDatabase() {
    abstract fun badgeDao(): BadgeDao
    abstract fun puntjeDao(): PuntjeDao

    companion object {
        @Volatile
        private var Instance: TeervoetAppDatabase? = null

        fun getDatabase(context: Context): TeervoetAppDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, TeervoetAppDatabase::class.java, "Teervoet_database")
                    .build().also { Instance = it }
            }
        }
    }
}
