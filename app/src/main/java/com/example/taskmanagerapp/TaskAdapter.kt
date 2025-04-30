package com.yourdomain.tasklist.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yourdomain.tasklist.data.model.Task
import com.yourdomain.tasklist.databinding.ItemTaskBinding

class TaskAdapter(
    private val onTaskClick: (String) -> Unit,
    private val onCheckChanged: (Task, Boolean) -> Unit
) : ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.tvTaskTitle.text = task.title
            binding.tvTaskDescription.text = task.description
            binding.cbTaskCompleted.isChecked = task.isCompleted

            binding.root.setOnClickListener {
                onTaskClick(task.id)
            }
            binding.cbTaskCompleted.setOnCheckedChangeListener { _, isChecked ->
                onCheckChanged(task, isChecked)
            }
        }
    }

    private class TaskDiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(old: Task, new: Task) = old.id == new.id
        override fun areContentsTheSame(old: Task, new: Task) = old == new
    }
}