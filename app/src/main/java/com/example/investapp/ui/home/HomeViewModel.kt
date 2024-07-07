package com.example.investapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    // Define constants
    companion object {
        private const val INITIAL_TEXT = "This is home Fragment"
    }

    // LiveData for text
    private val _text = MutableLiveData<String>().apply {
        value = INITIAL_TEXT
    }
    val text: LiveData<String> = _text

    // LiveData for the list of items
    private val _items = MutableLiveData<List<String>>().apply {
        value = listOf("Item 1", "Item 2", "Item 3", "Item 4")
    }
    val items: LiveData<List<String>> = _items

    // Data class to hold both text and items
    data class HomeData(val text: String, val items: List<String>)

    // LiveData for HomeData
    private val _homeData = MutableLiveData<HomeData>()
    val homeData: LiveData<HomeData> = _homeData

    init {
        updateHomeData()
    }

    // Function to update HomeData
    private fun updateHomeData() {
        _homeData.value = HomeData(_text.value ?: "", _items.value ?: emptyList())
    }

    // Function to update text
    fun updateText(newText: String) {
        _text.value = newText
        updateHomeData()
    }

    // Function to update items
    fun updateItems(newItems: List<String>) {
        _items.value = newItems
        updateHomeData()
    }
}