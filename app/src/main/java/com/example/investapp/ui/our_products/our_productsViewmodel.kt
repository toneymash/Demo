import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.investapp.ui.our_products.Product
import com.example.investapp.ui.our_products.ProductRepository
import com.example.investapp.ui.our_products.RetrofitClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class Our_ProductsViewModel(private val repository: ProductRepository) : ViewModel() {
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchProducts(maxRetries: Int = 3) {
        viewModelScope.launch {
            _isLoading.value = true
            var retryCount = 0
            while (retryCount < maxRetries) {
                try {
                    val fetchedProducts = repository.fetchProducts()
                    _products.value = fetchedProducts

                    break
                } catch (e: Exception) {
                    when (e) {
                        is HttpException -> {
                            if (e.code() == 504) {
                                retryCount++
                                if (retryCount == maxRetries) {
                                    _error.value = "Server timeout after $maxRetries attempts. Please try again later."
                                } else {
                                    delay(1000 * retryCount.toLong()) // Exponential backoff
                                    continue
                                }
                            } else {
                                _error.value = "An error occurred: ${e.message()}"
                                break
                            }
                        }
                        is IOException -> {
                            _error.value = "Network error. Please check your connection."
                            break
                        }
                        else -> {
                            _error.value = "An unexpected error occurred: ${e.message}"
                            break
                        }
                    }
                } finally {
                    _isLoading.value = false
                }
            }
        }
    }
}
