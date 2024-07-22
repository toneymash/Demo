package com.example.investapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProfileViewModel(private val  profile: ProfileRepository) : ViewModel() {
    private val _userProfile = MutableLiveData<UserProfile?>()
    val userProfile: LiveData<UserProfile?> = _userProfile

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun fetchUserProfile(id: String, token: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val profile = profile.fetchedProfile(id, token)
                if (profile != null) {

                    _userProfile.value = profile
                } else {

                    _error.value = "No token found"
                    _userProfile.value = null
                }
            } catch (e: Exception) {
                _error.value = "Error fetching profile: ${e.message}"
                _userProfile.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }
}
