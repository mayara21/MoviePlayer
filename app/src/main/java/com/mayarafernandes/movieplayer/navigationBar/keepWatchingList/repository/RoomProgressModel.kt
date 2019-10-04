package com.mayarafernandes.movieplayer.navigationBar.keepWatchingList.repository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_progress")
data class RoomProgressModel (
    @PrimaryKey val id: String = "",
    @ColumnInfo val progress: Int = 0
    )