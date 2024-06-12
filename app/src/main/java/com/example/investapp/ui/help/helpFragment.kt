package com.example.investapp.ui.help

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.example.investapp.R
import com.example.investapp.databinding.FragmentHelpBinding


class helpFragment : Fragment() {
    private lateinit var binding: FragmentHelpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHelpBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        inflateUI()

//        val whatsappIcon = findViewById<ImageView>(R.id.whatsappIcon)
        binding.whatsappIcon.setOnClickListener {
            openWhatsApp()
        }
    }
    private fun inflateUI(){
//        binding.tvProfile.text = View.VISIBLE
    }

    private fun openWhatsApp() {
        val phoneNumber = "+254113588127" // Replace with the phone number you want to send the message to
        val message = "Hello, this is a test message!" // Replace with your message
        val url = "https://api.whatsapp.com/send?phone=$phoneNumber&text=${Uri.encode(message)}"

        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            intent.setPackage("com.whatsapp")
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "WhatsApp not installed", Toast.LENGTH_SHORT).show()
        }
    }

}