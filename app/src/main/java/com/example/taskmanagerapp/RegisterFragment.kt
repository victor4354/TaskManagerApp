package com.yourdomain.tasklist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yourdomain.tasklist.R  // Added missing R import
import com.yourdomain.tasklist.databinding.FragmentRegisterBinding
import com.yourdomain.tasklist.ui.communicator.FragmentCommunicator
import com.yourdomain.tasklist.ui.viewmodels.RegisterViewModel

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RegisterViewModel by viewModels()
    private lateinit var communicator: FragmentCommunicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        communicator = activity as FragmentCommunicator

        binding.btnRegister.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val pass = binding.etPassword.text.toString().trim()
            if (name.isBlank() || email.isBlank() || pass.isBlank()) {
                if (name.isBlank()) binding.tilName.error = getString(R.string.empty_field_error)
                if (email.isBlank()) binding.tilEmail.error = getString(R.string.empty_field_error)
                if (pass.isBlank()) binding.tilPassword.error = getString(R.string.empty_field_error)
            } else {
                viewModel.register(name, email, pass)
            }
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) communicator.showLoader() else communicator.hideLoader()
        }
        viewModel.registerResult.observe(viewLifecycleOwner) { success ->
            if (success) {
                Toast.makeText(context, R.string.register_success, Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            } else {
                Toast.makeText(context, R.string.register_error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}