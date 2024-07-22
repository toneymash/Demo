// NewsViewModel.kt
package com.example.investapp.ui.appnews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val repository = NewsRepository(RetrofitClient.newsApiService)

    private val _news = MutableStateFlow<List<NewsItem>>(emptyList())
    val news: StateFlow<List<NewsItem>> = _news

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchNews() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                _news.value = repository.getNews()
            } catch (e: Exception) {
                _error.value = "Failed to fetch news: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
