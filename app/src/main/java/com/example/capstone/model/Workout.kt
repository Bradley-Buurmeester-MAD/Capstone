package com.example.capstone.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Duration
import java.util.*

@Entity(tableName = "workoutTable")
data class Workout(

        @ColumnInfo(name = "name")
        var name: String,

        @PrimaryKey
        @ColumnInfo(name = "date")
        var date: Date,

        @ColumnInfo(name = "sets")
        var setsDone: Int? = null,

        @ColumnInfo(name = "calories")
        var caloriesBurned: Int? = null,

        @ColumnInfo(name = "duration")
        var duration: Date? = null,

        @ColumnInfo(name = "bpm")
        var bpm: Double? = null,
)
