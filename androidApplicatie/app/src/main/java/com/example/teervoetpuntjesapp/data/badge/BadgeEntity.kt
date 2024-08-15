package com.example.teervoetpuntjesapp.data.badge

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.teervoetpuntjesapp.Model.Badge

@Entity(tableName = "badges")
data class BadgeEntity(
    @PrimaryKey
    val id: Int = 0,
    val titel: String = "",
    val image_url: String = "",
) {

    companion object {
        fun asDomainBadge(badgeEntity: BadgeEntity): Badge {
            return Badge(
                badgeEntity.id,
                badgeEntity.titel,
                badgeEntity.image_url,
            )
        }
    }
}
