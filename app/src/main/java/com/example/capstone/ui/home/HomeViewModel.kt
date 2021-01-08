package com.example.capstone.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.capstone.model.Workout
import com.example.capstone.repository.WorkoutRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val workoutRepository = WorkoutRepository(application.applicationContext)

    val workouts: LiveData<List<Workout>> = getCurrentWeekendsWorkouts()

    val newWorkoutName = MutableLiveData<String>()

    fun setNewWorkoutName (name: String) {
        newWorkoutName.value = name
    }

    fun insertWorkout(workout: Workout){
        ioScope.launch {
            workoutRepository.insertWorkout(workout)
        }
    }

    /**
     * Gets current weeks workouts beginning at monday and ending at sunday
     * @return list of this weeks workouts
     */
    private fun getCurrentWeekendsWorkouts(): LiveData<List<Workout>> {
        //get current date
        val date = Calendar.getInstance()

        //set time to first second of the day
        date.set(Calendar.HOUR, 0)
        date.clear(Calendar.MINUTE)
        date.clear(Calendar.SECOND)
        date.clear(Calendar.MILLISECOND)

        //get first date of the week
        date.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        val firstDayOfWeek: Date = date.time

        //set time to last second of the day
        date.set(Calendar.HOUR, 23)
        date.set(Calendar.MINUTE, 59)
        date.set(Calendar.SECOND, 59)
        date.set(Calendar.MILLISECOND, 59)

        //get last date of the week
        date.set(Calendar.WEEK_OF_YEAR, date.get(Calendar.WEEK_OF_YEAR) + 1)
        date.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
        val lastDayOfWeek: Date = date.time

        return workoutRepository.getAllWorkoutsFromWeek(firstDayOfWeek, lastDayOfWeek)
    }

}