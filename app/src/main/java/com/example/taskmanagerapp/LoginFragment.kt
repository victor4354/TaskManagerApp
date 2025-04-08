package com.example.taskmanagerapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.taskmanagerapp.databinding.FragmentLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configure login button
        binding.btnLogin.setOnClickListener {
            if (validateFields()) {
                // Show loading animation
                showLoading(true)

                // Simulate login process
                CoroutineScope(Dispatchers.Main).launch {
                    delay(2000) // Simulating network delay
                    showLoading(false)
                    findNavController().navigate(R.id.action_loginFragment_to_taskListFragment)
                }
            }
        }

        // Configure register text
        binding.tvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        // Configure reset password text
        binding.tvResetPassword.setOnClickListener {
            Toast.makeText(context, "Función no implementada", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateFields(): Boolean {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (email.isEmpty()) {
            binding.tilEmail.error = "El correo es obligatorio"
            return false
        }

        if (password.isEmpty()) {
            binding.tilPassword.error = "La contraseña es obligatoria"
            return false
        }

        // Clear errors
        binding.tilEmail.error = null
        binding.tilPassword.error = null
        return true
    }

    private fun showLoading(show: Boolean) {
        if (show) {
            binding.loadingAnimation.visibility = View.VISIBLE
            binding.btnLogin.isEnabled = false
        } else {
            binding.loadingAnimation.visibility = View.GONE
            binding.btnLogin.isEnabled = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}