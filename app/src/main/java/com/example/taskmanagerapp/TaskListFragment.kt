package com.yourdomain.tasklist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yourdomain.tasklist.R
import com.yourdomain.tasklist.databinding.FragmentTaskListBinding
import com.yourdomain.tasklist.ui.adapters.TaskAdapter
import com.yourdomain.tasklist.ui.viewmodels.TaskViewModel

class TaskListFragment : Fragment() {
    private var _binding: FragmentTaskListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TaskViewModel by viewModels({ requireActivity() })
    private lateinit var adapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar el adaptador
        adapter = TaskAdapter(
            onTaskClick = { taskId ->
                findNavController().navigate(
                    R.id.action_taskList_to_taskDetail,
                    Bundle().apply { putString("taskId", taskId) }
                )
            },
            onCheckChanged = { task, isChecked ->
                task.isCompleted = isChecked
                viewModel.updateTask(task)
            }
        )

        binding.recyclerViewTasks.adapter = adapter
        binding.fabAddTask.setOnClickListener {
            findNavController().navigate(R.id.action_taskList_to_addTask)
        }

        // Observar cambios en las tareas
        viewModel.tasks.observe(viewLifecycleOwner) { tasks ->
            adapter.submitList(tasks)
        }

        // Cargar tareas
        viewModel.loadTasks()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}