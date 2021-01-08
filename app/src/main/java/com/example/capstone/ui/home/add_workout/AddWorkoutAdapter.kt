package com.example.capstone.ui.home.add_workout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.R
import kotlinx.android.synthetic.main.item_choose_workout.view.*

class AddWorkoutAdapter(private val workouts: List<String>, private val clickListener: (String) -> Unit): RecyclerView.Adapter<AddWorkoutAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun databind(workout: String, clickListener: (String) -> Unit){
            itemView.tvWorkoutName.text = workout
            itemView.btnAddWorkoutItemView.setOnClickListener {
                clickListener(workout)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_choose_workout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(workouts[position], clickListener)
    }

    override fun getItemCount(): Int {
        return workouts.size
    }
}