package com.example.composecustomerapp.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.composecustomerapp.ui.components.Product

data class RusksWafersUiState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false
)

class RusksWafersViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(RusksWafersUiState())
    val uiState: StateFlow<RusksWafersUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        _uiState.value = RusksWafersUiState(
            products = listOf(
                Product(
                    id = "rw1",
                    name = "Parle Real Elaichi Rusk",
                    price = 54,
                    imageUrl = "https://images.unsplash.com/photo-1590080875515-8a3a8dc5735e?auto=format&fit=crop&q=80&w=400",
                    unit = "400 g"
                ),
                Product(
                    id = "rw2",
                    name = "Britannia Strawberry Flavoured Wafers",
                    price = 27,
                    imageUrl = "https://images.unsplash.com/photo-1590080875515-8a3a8dc5735e?auto=format&fit=crop&q=80&w=400", // Using a placeholder
                    unit = "75 g"
                ),
                Product(
                    id = "rw3",
                    name = "Dukes Waffy Choco Wafer Roll",
                    price = 58,
                    imageUrl = "https://images.unsplash.com/photo-1590080875515-8a3a8dc5735e?auto=format&fit=crop&q=80&w=400", // Using a placeholder
                    unit = "250 g"
                )
            )
        )
    }
}
