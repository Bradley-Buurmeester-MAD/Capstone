package com.example.capstone.ui.home.add_workout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.capstone.R
import com.example.capstone.model.Workout
import com.example.capstone.ui.home.HomeViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_choose_time.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter

/**
 * A simple [Fragment] subclass.
 * Use the [ChooseTimeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChooseTimeFragment : Fragment() {
    private lateinit var dateString: String
    private lateinit var timeString: String

    private val viewModel: HomeViewModel by activityViewModels()

    private lateinit var newName: String
    private lateinit var newDate: Date

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_time, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get new workout name from previous fragment
        viewModel.newWorkoutName.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            newName = it
        })

        //update UI
        dateString = getString(R.string.textview_chosen_date, "")
        timeString = getString(R.string.textview_chosen_time, "")
        btnPickTime.isEnabled = false
        btnConfirmDateAndTime.isEnabled = false

        updateUI()

        // Set on click listener for the add date and time buttons
        btnPickDate.setOnClickListener {
            createMaterialDatePicker()
        }

        btnPickTime.setOnClickListener {
            createMaterialTimePicker()
        }

        /**
         * Add on click listener for confirm button
         * Making it pop to home fragment
         * Adding the newly made workout
          */
        btnConfirmDateAndTime.setOnClickListener {
            viewModel.insertWorkout(Workout(newName, newDate))
            findNavController().popBackStack(R.id.navigation_home, false)
        }
    }

    /**
     * Creates Material date picker
     */
    private fun createMaterialDatePicker(){
        val builder = MaterialDatePicker.Builder.datePicker()
        val materialDatePicker = builder.build()

        materialDatePicker.show(parentFragmentManager, "Date_Picker")

        // Reset everything
        dateString = getString(R.string.textview_chosen_date, "")
        timeString = getString(R.string.textview_chosen_time, "")
        btnPickTime.isEnabled = false
        btnConfirmDateAndTime.isEnabled = false

        materialDatePicker.addOnPositiveButtonClickListener {
            newDate = Date(it)

            var formatter = SimpleDateFormat("dd-MM-yyyy")

            dateString = getString(R.string.textview_chosen_date, formatter.format(newDate))

            btnPickTime.isEnabled = true
            updateUI()
        }
    }

    /**
     * Create material time picker
     */
    private fun createMaterialTimePicker() {
        val builder = MaterialTimePicker.Builder()
        builder.setTimeFormat(TimeFormat.CLOCK_24H)

        val materialTimePicker = builder.build()

        materialTimePicker.show(parentFragmentManager, "Time_Picker")

        materialTimePicker.addOnPositiveButtonClickListener {
            newDate.hours = materialTimePicker.hour
            newDate.minutes = materialTimePicker.minute

            var formatter = SimpleDateFormat("HH:mm")

            timeString = getString(R.string.textview_chosen_time, formatter.format(newDate))

            btnConfirmDateAndTime.isEnabled = true
            updateUI()
        }

    }

    /**
     * updates UI according to the given values from the pickers
     */
    private fun updateUI() {
        tvCurrentDate.text = dateString
        tvCurrentTime.text = timeString
    }
}