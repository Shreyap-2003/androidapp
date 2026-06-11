package com.example.composecustomerapp.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.composecustomerapp.ui.components.Product

data class FruitsUiState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false
)

class FruitsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(FruitsUiState())
    val uiState: StateFlow<FruitsUiState> = _uiState.asStateFlow()

    init {
        loadFruitsData()
    }

    private fun loadFruitsData() {
        _uiState.value = FruitsUiState(
            products = listOf(
                Product(
                    id = "f1",
                    name = "Apple",
                    price = 45,
                    imageUrl = "https://images.unsplash.com/photo-1610832958506-aa56368176cf?auto=format&fit=crop&q=80&w=400",
                    unit = "1 kg"
                ),
                Product(
                    id = "f2",
                    name = "Banana",
                    price = 35,
                    imageUrl = "https://images.unsplash.com/photo-1603833665858-e61d17a86224?auto=format&fit=crop&q=80&w=400",
                    unit = "12 pcs"
                ),
                Product(
                    id = "f3",
                    name = "Orange",
                    price = 40,
                    imageUrl = "https://images.unsplash.com/photo-1547514701-42782101795e?auto=format&fit=crop&q=80&w=400",
                    unit = "500 g"
                )
            )
        )
    }
}
