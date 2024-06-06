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

    // Function to get the text with items appended
    private fun getTextWithItems(): String {
        val itemsText = _items.value?.joinToString(separator = "\n") { "\"$it\"" }
        return "$INITIAL_TEXT\n$itemsText"
    }

    init {
        // Update the text LiveData with the combined text and items
        _text.value = getTextWithItems()
    }
}
