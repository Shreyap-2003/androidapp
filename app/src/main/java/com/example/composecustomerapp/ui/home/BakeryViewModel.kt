package com.example.composecustomerapp.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.composecustomerapp.ui.components.SubCategory

data class BakeryUiState(
    val subCategories: List<SubCategory> = emptyList(),
    val isLoading: Boolean = false
)

class BakeryViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(BakeryUiState())
    val uiState: StateFlow<BakeryUiState> = _uiState.asStateFlow()

    init {
        loadBakeryData()
    }

    private fun loadBakeryData() {
        _uiState.value = BakeryUiState(
            subCategories = listOf(
                SubCategory(
                    "Cookies",
                    "Gooey chocolate chips, buttery shortbreads, and crunchy delights.",
                    "https://images.unsplash.com/photo-1499636136210-6f4ee915583e?auto=format&fit=crop&q=80&w=600"
                ),
                SubCategory(
                    "Cakes",
                    "Artisanal celebration cakes and personal pastry slices.",
                    "https://images.unsplash.com/photo-1578985545062-69928b1d9587?auto=format&fit=crop&q=80&w=600"
                ),
                SubCategory(
                    "Rusks & Wafers",
                    "Perfect crunchy companions for your morning tea or coffee.",
                    "https://images.unsplash.com/photo-1590080875515-8a3a8dc5735e?auto=format&fit=crop&q=80&w=600"
                )
            )
        )
    }
}
