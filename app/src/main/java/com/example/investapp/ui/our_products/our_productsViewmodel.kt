package com.example.investapp.ui.our_products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class our_productsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is our products Fragment"
    }
    val text: LiveData<String> = _text
}

