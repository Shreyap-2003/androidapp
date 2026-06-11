package com.example.composecustomerapp.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.composecustomerapp.ui.components.Product

data class CookiesUiState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false
)

class CookiesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CookiesUiState())
    val uiState: StateFlow<CookiesUiState> = _uiState.asStateFlow()

    init {
        loadCookiesData()
    }

    private fun loadCookiesData() {
        _uiState.value = CookiesUiState(
            products = listOf(
                Product(
                    id = "c1",
                    name = "Hide & Seek Chocochip Cookies",
                    price = 30,
                    imageUrl = "https://images.unsplash.com/photo-1558961363-fa8fdf82db35?auto=format&fit=crop&q=80&w=400",
                    tag = "FASTEST"
                ),
                Product(
                    id = "c2",
                    name = "Sunfeast Dark Fantasy Choco fill...",
                    price = 40,
                    imageUrl = "https://images.unsplash.com/photo-1499636136210-6f4ee915583e?auto=format&fit=crop&q=80&w=400"
                ),
                Product(
                    id = "c3",
                    name = "Unibic Fruit & Nut Cookies",
                    price = 70,
                    imageUrl = "https://images.unsplash.com/photo-1509440159596-0249088772ff?auto=format&fit=crop&q=80&w=400"
                )
            )
        )
    }
}
