package com.example.capstone.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.capstone.model.Workout
import java.util.*

@Dao
interface WorkoutDao {

    @Query("SELECT * FROM workoutTable WHERE calories IS NOT NULL ORDER BY date desc")
    fun getAllWorkouts(): LiveData<List<Workout>>

    @Query("SELECT * FROM workoutTable WHERE date BETWEEN :mondayDate AND :sundayDate")
    fun getAllWorkoutsFromWeek(mondayDate: Date, sundayDate: Date): LiveData<List<Workout>>

    @Query("SELECT name, date, sets, calories, duration, bpm, MAX(calories) FROM workoutTable")
    fun getBestWorkout(): LiveData<Workout>

    @Insert
    fun insertWorkout(workout: Workout)
}