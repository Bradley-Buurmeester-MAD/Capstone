package com.example.capstone.ui.home.add_workout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.R
import com.example.capstone.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_choose_workout.*

/**
 * A simple [Fragment] subclass.
 * Use the [ChooseWorkoutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChooseWorkoutFragment : Fragment() {
    private val workouts = arrayListOf<String>()
    private val workoutAdapter = AddWorkoutAdapter(workouts, { workout: String -> onAddWorkout(workout) })

    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_workout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addWorkouts()
        initView()
    }

    /**
     * Add list of workouts to fragment
     */
    fun addWorkouts() {
        workouts.clear()
        workouts.add("Legs")
        workouts.add("Arms")
        workouts.add("Shoulders")
    }

    /**
     * initialize recycle view
     */
    fun initView() {
        rvAddWorkout.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvAddWorkout.adapter = workoutAdapter
        rvAddWorkout.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    /**
     * functions for clicking on a workout
     * sends new workout name to the viewmodel and navigates to next fragment
     */
    fun onAddWorkout(workout: String) {
        viewModel.setNewWorkoutName(workout)
        findNavController().navigate(R.id.action_chooseWorkoutFragment_to_chooseTimeFragment)
    }

}