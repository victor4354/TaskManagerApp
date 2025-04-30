package com.yourdomain.tasklist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.yourdomain.tasklist.databinding.FragmentTaskDetailBinding
import com.yourdomain.tasklist.ui.viewmodels.TaskViewModel

class TaskDetailFragment : Fragment() {
    private var _binding: FragmentTaskDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TaskViewModel by viewModels({ requireActivity() })
    private val args: TaskDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val taskId = args.taskId
        viewModel.tasks.observe(viewLifecycleOwner) { list ->
            list.find { it.id == taskId }?.let { task ->
                binding.tvDetailTitle.text = task.title
                binding.tvDetailDescription.text = task.description
                binding.cbDetailCompleted.isChecked = task.isCompleted
            }
        }
        binding.btnUpdateTask.setOnClickListener {
            val isChecked = binding.cbDetailCompleted.isChecked
            viewModel.tasks.value?.find { it.id == taskId }?.apply {
                isCompleted = isChecked
                viewModel.updateTask(this)
            }
        }
        viewModel.loadTasks()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}