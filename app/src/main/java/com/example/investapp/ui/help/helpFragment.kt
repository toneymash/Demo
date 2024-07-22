package com.example.investapp.ui.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.investapp.databinding.FragmentHelpBinding

class HelpFragment : Fragment() {
    private var _binding: FragmentHelpBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HelpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHelpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSubmitButton()
        observeViewModel()
    }

    private fun setupSubmitButton() {
        binding.submitButton.setOnClickListener {
            if (validateForm()) {
                submitForm()
            }
        }
    }

    private fun validateForm(): Boolean {
        val name = binding.nameEditText.text.toString().trim()
        val email = binding.emailEditText.text.toString().trim()
        val phoneNumber = binding.phoneEditText.text.toString().trim()
        val message = binding.messageEditText.text.toString().trim()

        if (name.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || message.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun submitForm() {
        val formData = FormData(
            name = binding.nameEditText.text.toString().trim(),
            email = binding.emailEditText.text.toString().trim(),
            phoneNumber = binding.phoneEditText.text.toString().trim(),
            message = binding.messageEditText.text.toString().trim()
        )
        viewModel.submitForm(formData)
    }

    private fun observeViewModel() {
        viewModel.submissionStatus.observe(viewLifecycleOwner) { status ->
            Toast.makeText(requireContext(), status, Toast.LENGTH_SHORT).show()
            if (status == "Form submitted successfully") {
                clearForm()
            }
        }
    }

    private fun clearForm() {
        binding.nameEditText.text.clear()
        binding.emailEditText.text.clear()
        binding.phoneEditText.text.clear()
        binding.messageEditText.text.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}