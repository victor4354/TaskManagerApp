package com.yourdomain.tasklist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yourdomain.tasklist.data.model.Task
import com.yourdomain.tasklist.databinding.FragmentAddTaskBinding
import com.yourdomain.tasklist.ui.viewmodels.TaskViewModel

class AddTaskFragment : Fragment() {
    private var _binding: FragmentAddTaskBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TaskViewModel by viewModels({ requireActivity() })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSaveTask.setOnClickListener {
            val title = binding.etTaskTitle.text.toString().trim()
            if (title.isEmpty()) {
                binding.tilTaskTitle.error = "El título es obligatorio"
            } else {
                val desc = binding.etTaskDescription.text.toString().trim()
                val task = Task(
                    title = title,
                    description = desc,
                    isCompleted = false,
                    userId = viewModel.currentUserId()
                )
                viewModel.addTask(task)
                Toast.makeText(requireContext(), "Tarea agregada", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}