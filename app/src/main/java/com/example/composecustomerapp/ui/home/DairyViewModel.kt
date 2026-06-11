package com.example.composecustomerapp.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.composecustomerapp.ui.components.Product

data class DairyUiState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false
)

class DairyViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DairyUiState())
    val uiState: StateFlow<DairyUiState> = _uiState.asStateFlow()

    init {
        loadDairyData()
    }

    private fun loadDairyData() {
        _uiState.value = DairyUiState(
            products = listOf(
                Product(
                    "p1",
                    "Milk",
                    27,
                    "https://images.unsplash.com/photo-1550583724-125581fe2f8a?auto=format&fit=crop&q=80&w=400",
                    "FRESH DAILY"
                ),
                Product(
                    "p2",
                    "Cheese",
                    40,
                    "https://images.unsplash.com/photo-1486297678162-ad2a19b05840?auto=format&fit=crop&q=80&w=400"
                ),
                Product(
                    "p3",
                    "Paneer",
                    80,
                    "https://images.unsplash.com/photo-1565557623262-b51c2513a641?auto=format&fit=crop&q=80&w=400"
                )
            )
        )
    }
}
