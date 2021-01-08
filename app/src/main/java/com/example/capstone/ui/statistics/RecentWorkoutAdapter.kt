package com.example.capstone.ui.statistics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.R
import com.example.capstone.model.Workout
import kotlinx.android.synthetic.main.item_recent_workout.view.*
import java.text.SimpleDateFormat

class RecentWorkoutAdapter (private val workouts: List<Workout>, private val clickListener: (Workout) -> Unit): RecyclerView.Adapter<RecentWorkoutAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun databind(workout: Workout){
            val formatter = SimpleDateFormat("dd MMMM yyyy")
            itemView.tvDate.text = formatter.format(workout.date)
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_recent_workout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val workout = workouts[position]
        holder.databind(workout)
        holder.itemView.setOnClickListener {
            clickListener(workout)
        }
    }

    override fun getItemCount(): Int {
        return workouts.size
    }
}