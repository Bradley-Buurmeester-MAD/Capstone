package com.example.capstone.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.capstone.dao.WorkoutDao
import com.example.capstone.model.Converters
import com.example.capstone.model.Workout

@Database(entities = [Workout::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class WorkoutDatabase: RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao

    companion object {
        private const val DATABASE_NAME = "WORKOUT_DATABASE"

        @Volatile
        private var workoutDatabaseInstance: WorkoutDatabase? = null

        fun getDatabase(context: Context): WorkoutDatabase? {
            if (workoutDatabaseInstance == null){
                synchronized(WorkoutDatabase::class.java) {
                    if (workoutDatabaseInstance == null) {
                        workoutDatabaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            WorkoutDatabase::class.java,
                            DATABASE_NAME
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }

            return workoutDatabaseInstance
        }

    }
}