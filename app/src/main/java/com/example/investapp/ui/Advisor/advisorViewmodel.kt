package com.example.investapp.ui.Advisor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class advisorViewmodel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is advisor Fragment"
    }
    val text: LiveData<String> = _text
}


