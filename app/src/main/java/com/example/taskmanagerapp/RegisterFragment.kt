package com.example.taskmanagerapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.taskmanagerapp.databinding.FragmentRegisterBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configure register button
        binding.btnRegister.setOnClickListener {
            if (validateFields()) {
                // Show loading animation
                showLoading(true)

                // Simulate registration process
                CoroutineScope(Dispatchers.Main).launch {
                    delay(2000) // Simulating network delay
                    showLoading(false)
                    findNavController().navigate(R.id.action_registerFragment_to_taskListFragment)
                }
            }
        }
    }

    private fun validateFields(): Boolean {
        val name = binding.etName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (name.isEmpty()) {
            binding.tilName.error = "El nombre es obligatorio"
            return false
        }

        if (email.isEmpty()) {
            binding.tilEmail.error = "El correo es obligatorio"
            return false
        }

        if (password.isEmpty()) {
            binding.tilPassword.error = "La contraseña es obligatoria"
            return false
        }

        // Clear errors
        binding.tilName.error = null
        binding.tilEmail.error = null
        binding.tilPassword.error = null
        return true
    }

    private fun showLoading(show: Boolean) {
        if (show) {
            binding.loadingAnimation.visibility = View.VISIBLE
            binding.btnRegister.isEnabled = false
        } else {
            binding.loadingAnimation.visibility = View.GONE
            binding.btnRegister.isEnabled = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}