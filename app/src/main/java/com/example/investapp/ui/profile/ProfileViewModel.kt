package com.example.investapp.ui.profile
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class ProfileViewModel : ViewModel() {
    private val _userProfile = MutableLiveData<UserProfile?>()
    val userProfile: LiveData<UserProfile?> = _userProfile

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun fetchUserProfile(id:String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {

                val profile = RetrofitClient.apiService.getUserProfile(id)
                _userProfile.value = profile
            } catch (e: Exception) {
                _error.value = "Error fetching profile: ${e.message}"
                _userProfile.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }
}