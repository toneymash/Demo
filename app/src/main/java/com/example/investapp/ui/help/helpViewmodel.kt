package com.example.investapp.ui.help

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HelpViewModel : ViewModel() {
    private val repository = FormRepository()

    private val _submissionStatus = MutableLiveData<String>()
    val submissionStatus: LiveData<String> = _submissionStatus

    fun submitForm(formData: FormData) {
        viewModelScope.launch {
            try {
                val result = repository.submitForm(formData)
                _submissionStatus.value = result
            } catch (e: Exception) {
                _submissionStatus.value = "Error: ${e.message}"
            }
        }
    }
}