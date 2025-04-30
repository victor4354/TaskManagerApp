package com.yourdomain.tasklist.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yourdomain.tasklist.R                           // ← make sure this is present
import com.yourdomain.tasklist.databinding.FragmentLoginBinding
import com.yourdomain.tasklist.ui.ListActivity
import com.yourdomain.tasklist.ui.communicator.FragmentCommunicator
import com.yourdomain.tasklist.ui.viewmodels.LoginViewModel

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var communicator: FragmentCommunicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        communicator = activity as FragmentCommunicator

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val pass = binding.etPassword.text.toString().trim()
            if (email.isBlank() || pass.isBlank()) {
                if (email.isBlank()) binding.tilEmail.error = getString(R.string.empty_field_error)
                if (pass.isBlank()) binding.tilPassword.error = getString(R.string.empty_field_error)
            } else {
                viewModel.login(email, pass)
            }
        }
        binding.tvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_register)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) communicator.showLoader() else communicator.hideLoader()
        }
        viewModel.loginResult.observe(viewLifecycleOwner) { success ->
            if (success) {
                Toast.makeText(context, R.string.login_success, Toast.LENGTH_SHORT).show()
                startActivity(Intent(requireContext(), ListActivity::class.java))
                activity?.finish()
            } else {
                Toast.makeText(context, R.string.login_error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
