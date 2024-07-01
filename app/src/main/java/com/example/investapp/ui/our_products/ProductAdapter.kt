package com.example.investapp.ui.our_products

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.investapp.R
import com.example.investapp.databinding.ActivityMoredetailsBinding

class ProductAdapter(private var products: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productname: TextView = view.findViewById(R.id.productname)
        val productAbbreviation: TextView = view.findViewById(R.id.productAbbreviation)
        val productFundType: TextView = view.findViewById(R.id.productFundType)
        val productCurrency: TextView = view.findViewById(R.id.productCurrency)
        val productMoreDetails: TextView = view.findViewById(R.id.productMoreDetails)
        val moreDetailsButton: Button = view.findViewById(R.id.moreDetailsButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.productname.text = product.name
        holder.productAbbreviation.text = product.abbreviation
        holder.productFundType.text = product.fundType
        holder.productCurrency.text = product.currency
        holder.productMoreDetails.text = product.moreDetails

        // In ProductAdapter.kt
        holder.moreDetailsButton.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, ActivityMoredetailsBinding::class.java)
           intent.putExtra("PRODUCT_ID", product.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = products.size

    fun updateProducts(newProducts: List<Product>) {
        products = newProducts
        notifyDataSetChanged()
    }
}