package com.example.investapp.ui.accounts

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.investapp.databinding.FragmentAccountsBinding
import com.example.investapp.ui.Bank.BankActivity
import com.example.investapp.ui.Withdraw.WithdrawActivity
import com.example.investapp.ui.mpesa.BuyActivity

class accountsFragment : Fragment() {
    private lateinit var binding: FragmentAccountsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonTopUp.setOnClickListener {
            val intent = Intent(requireContext(), BuyActivity::class.java)
            startActivity(intent)
        }
        binding.withdraw.setOnClickListener {
            val intent = Intent(requireContext(), WithdrawActivity::class.java)
            startActivity(intent)

        }
    }}

