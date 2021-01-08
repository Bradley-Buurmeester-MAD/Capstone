package com.example.capstone.ui.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.R
import com.example.capstone.model.Workout
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.dialog.MaterialDialogs
import kotlinx.android.synthetic.main.fragment_statistics.*
import java.text.SimpleDateFormat

class StatisticsFragment : Fragment() {
    private val workouts = arrayListOf<Workout>()
    private val workoutAdapter = RecentWorkoutAdapter(workouts, { workout: Workout -> openRecentWorkout(workout) })

    private val viewModel: StatisticsViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeAddWorkoutResults()
        initView()
    }

    /**
     * Initialize recycle view
     */
    private fun initView() {
        rvRecentWorkouts.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvRecentWorkouts.adapter = workoutAdapter
        rvRecentWorkouts.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    /**
     * Add obeserver for best workout and all finished past workouts
     */
    private fun observeAddWorkoutResults() {
        viewModel.workouts.observe(viewLifecycleOwner, Observer { workoutList ->
            this.workouts.clear()
            this.workouts.addAll(workoutList)
            workoutAdapter.notifyDataSetChanged()
        })

        viewModel.bestWorkout.observe(viewLifecycleOwner, Observer { bestWorkout ->
            val formatter = SimpleDateFormat("dd MMMM yyyy")
            tvBestWorkoutDate.text = formatter.format(bestWorkout.date)
            tvBestWorkoutCalories.text = bestWorkout.caloriesBurned.toString()

            // Add on click listener that open dialog with workouts info
            cvBestWorkout.setOnClickListener {
                openRecentWorkout(bestWorkout)
            }
        })
    }

    /**
     * opens an material dialog with all the info of your finished workouts
     */
    private fun openRecentWorkout(workout: Workout) {
        val dateFormatter = SimpleDateFormat("dd MMMM yyyy")
        val timeFormatter = SimpleDateFormat("HH:mm")

        val materialDialogBuilder = MaterialAlertDialogBuilder(requireContext())
        materialDialogBuilder.setTitle(dateFormatter.format(workout.date))
        materialDialogBuilder.setMessage(
                    getString(R.string.dialog_message_name, workout.name) + System.lineSeparator() +
                    getString(R.string.dialog_message_calories, workout.caloriesBurned) + System.lineSeparator() +
                    getString(R.string.dialog_message_sets, workout.setsDone) + System.lineSeparator() +
                    getString(R.string.dialog_message_duration, timeFormatter.format(workout.duration)) + System.lineSeparator() +
                    getString(R.string.dialog_message_bpm, workout.bpm)
        )
        materialDialogBuilder.setNeutralButton(R.string.button_ok, null)
        materialDialogBuilder.show()
    }


}