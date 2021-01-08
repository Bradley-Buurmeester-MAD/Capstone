package com.example.capstone.ui.statistics

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.capstone.model.Workout
import com.example.capstone.repository.WorkoutRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class StatisticsViewModel(application: Application) : AndroidViewModel(application) {
    private val workoutRepository = WorkoutRepository(application.applicationContext)

    val workouts: LiveData<List<Workout>> = workoutRepository.getAllWorkouts()
    val bestWorkout: LiveData<Workout> = workoutRepository.getBestWorkout()
}