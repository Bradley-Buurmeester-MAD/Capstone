package com.example.capstone.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.capstone.R
import com.example.capstone.model.Workout
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    private val workouts = arrayListOf<Workout>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeAddWorkoutResult()

        btnAddWorkout.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_chooseWorkoutFragment)
        }
    }

    private fun observeAddWorkoutResult() {
        viewModel.workouts.observe(viewLifecycleOwner, Observer { workoutList ->
            this.workouts.clear()
            this.workouts.addAll(workoutList)

            updateUI()
        })
    }

    /**
     * Updates UI to see this weeks workout planning
     */
    private fun updateUI() {
        for (workout in workouts){
            // Get workouts date in calendar form
            val calendar = Calendar.getInstance()
            calendar.time = workout.date

            // Formating date in hours Example( HH:mm -> 10:00 )
            val formatter = SimpleDateFormat("HH:mm")
            val date = formatter.format(workout.date)

            // Switch checking which day it is and updating UI accordingly
            when (calendar.get(Calendar.DAY_OF_WEEK)) {
                Calendar.MONDAY -> {
                    tvMondayWorkoutName.text = workout.name
                    tvMondayWorkoutDate.text = date
                }
                Calendar.TUESDAY -> {
                    tvTuesdayWorkoutName.text = workout.name
                    tvTuesdayWorkoutDate.text = date
                }
                Calendar.WEDNESDAY -> {
                    tvWednesdayWorkoutName.text = workout.name
                    tvWednesdayWorkoutDate.text = date
                }
                Calendar.THURSDAY -> {
                    tvThursdayWorkoutName.text = workout.name
                    tvThursdayWorkoutDate.text = date
                }
                Calendar.FRIDAY -> {
                    tvFridayWorkoutName.text = workout.name
                    tvFridayWorkoutDate.text = date
                }
                Calendar.SATURDAY -> {
                    tvSaturdayWorkoutName.text = workout.name
                    tvSaturdayWorkoutDate.text = date
                }
                Calendar.SUNDAY -> {
                    tvSundayWorkoutName.text = workout.name
                    tvSundayWorkoutDate.text = date
                }

            }
        }
    }

}