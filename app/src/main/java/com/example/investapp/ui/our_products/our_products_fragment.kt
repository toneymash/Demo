package com.example.investapp.ui.our_products

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.investapp.R
import com.example.investapp.databinding.FragmentOurProductsBinding
import com.example.investapp.ui.MoneyMarketActivity
import com.example.investapp.ui.RealEstateActivity
import com.example.investapp.ui.RetirementActivity
import com.example.investapp.ui.productsActivity


class our_products : Fragment() {
    private lateinit var binding: FragmentOurProductsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOurProductsBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }
    private fun setupClickListeners() {
        binding.bondsItem
            .setOnClickListener {
            val intent = Intent(activity, productsActivity::class.java)
            startActivity(intent)
        }

      binding.retirementItem.setOnClickListener {
          val intent = Intent(activity, RetirementActivity::class.java)
            startActivity(intent)
      }

     binding.moneyMarketItem.setOnClickListener {
            val intent = Intent(activity, MoneyMarketActivity::class.java)
            startActivity(intent)
    }

      binding.realEstateItem.setOnClickListener {
         val intent = Intent(activity, RealEstateActivity::class.java)
           startActivity(intent)
        }
    }



    private fun inflateUI(){

//        val intent = Intent(activity, ProductsActivity::class.java)
//        startActivity(intent)
////        binding.tvProfile.text = View.VISIBLE
    }

}