package com.example.investapp.ui.settings
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.investapp.R
import com.example.investapp.databinding.FragmentSettingsBinding


class settingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inflateUI()
    }
    private fun inflateUI(){
//        binding.tvProfile.text = View.VISIBLE
    }

}