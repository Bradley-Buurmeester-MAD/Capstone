package com.example.capstone.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.capstone.dao.WorkoutDao
import com.example.capstone.database.WorkoutDatabase
import com.example.capstone.model.Workout
import java.util.*

class WorkoutRepository(context: Context) {
    private var workoutDao: WorkoutDao?

    init {
        val workoutRoomDatabase = WorkoutDatabase.getDatabase(context)
        workoutDao = workoutRoomDatabase?.workoutDao()
    }

    fun getAllWorkouts(): LiveData<List<Workout>> {
        return workoutDao?.getAllWorkouts() ?: MutableLiveData(emptyList())
    }

    fun getAllWorkoutsFromWeek(mondayDate: Date, sundayDate: Date): LiveData<List<Workout>> {
        return workoutDao?.getAllWorkoutsFromWeek(mondayDate, sundayDate) ?: MutableLiveData(emptyList())
    }

    fun getBestWorkout(): LiveData<Workout> {
        return workoutDao?.getBestWorkout() ?: MutableLiveData(null)
    }

    fun insertWorkout(workout: Workout) {
        workoutDao?.insertWorkout(workout)
    }


}