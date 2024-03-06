package com.example.teervoetpuntjesapp.data.badge

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.teervoetpuntjesapp.Model.Badge

@Entity(tableName = "badges")
data class BadgeEntity (
    @PrimaryKey
    val id: Int = 0,
    val titel: String = "",
    val image_url: String = ""
)

fun BadgeEntity.asDomainBadge(): Badge {
    return Badge(
        this.id,
        this.titel,
        this.image_url
    )
}

fun Badge.asBadgeEntity(): BadgeEntity{
    return BadgeEntity(
        this.id,
        this.titel,
        this.image_url
    )
}

