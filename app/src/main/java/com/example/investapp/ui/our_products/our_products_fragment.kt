package com.example.investapp.ui.our_products
import Our_ProductsViewModel
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.investapp.R

class Our_ProductsFragment : Fragment() {
    private lateinit var viewModel: Our_ProductsViewModel
    private lateinit var adapter: ProductAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var loadingProgressBar: ProgressBar

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // Initialize the repository and ViewModelFactory
        val repository = ProductRepository(context.applicationContext)
        val viewModelFactory = OurProductsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(Our_ProductsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_our_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.productsRecyclerView)
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)
        loadingProgressBar = view.findViewById(R.id.loadingProgressBar)

        adapter = ProductAdapter(emptyList())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchProducts()
        }

        viewModel.products.observe(viewLifecycleOwner) { products ->
            adapter.updateProducts(products)
            swipeRefreshLayout.isRefreshing = false
            loadingProgressBar.visibility = View.GONE
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            loadingProgressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }

        viewModel.fetchProducts()
    }
}
