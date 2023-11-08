package com.example.homework12.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.homework12.Model.Task
import com.example.homework12.databinding.ItemTaskBinding

class TaskAdapter(private val onLongClick: (Task)-> Unit, private val onClick: (Task)-> Unit) : Adapter<TaskAdapter.TaskViewHolder>() {
    private val list= arrayListOf<Task>()
    fun addTasks(tasks: List<Task>){
        list.clear()
        list.addAll(tasks)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(list.get(position))
    }
    inner class TaskViewHolder(private val binding: ItemTaskBinding) : ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.tvTitle.text= task.title
            binding.tvDesc.text=task.desc
            itemView.setOnLongClickListener{
                onLongClick(task)
                true
            }
            itemView.setOnClickListener{
                onClick(task)

            }
        }
    }
}
