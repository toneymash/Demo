package com.example.investapp.ui.Advisor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.investapp.databinding.FragmentAdvisorBinding

class AdvisorFragment : Fragment() {
    private lateinit var binding: FragmentAdvisorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAdvisorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inflateUI()
    }

    private fun inflateUI() {
        // Example of setting a text view visibility
        binding.tvName.visibility = View.VISIBLE
    }
}
