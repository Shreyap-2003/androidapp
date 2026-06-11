package com.example.composecustomerapp.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.composecustomerapp.ui.components.SubCategory

data class InstantFoodsUiState(
    val subCategories: List<SubCategory> = emptyList(),
    val isLoading: Boolean = false
)

class InstantFoodsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(InstantFoodsUiState())
    val uiState: StateFlow<InstantFoodsUiState> = _uiState.asStateFlow()

    init {
        loadInstantFoodsData()
    }

    private fun loadInstantFoodsData() {
        _uiState.value = InstantFoodsUiState(
            subCategories = listOf(
                SubCategory(
                    "Noodles",
                    "RAMEN \u2022 INSTANT \u2022 PASTA",
                    "https://images.unsplash.com/photo-1569718212165-3a8278d5f624?auto=format&fit=crop&q=80&w=600"
                ),
                SubCategory(
                    "Soups",
                    "READY-TO-HEAT \u2022 HEALTHY",
                    "https://images.unsplash.com/photo-1547592166-23ac45744acd?auto=format&fit=crop&q=80&w=600"
                ),
                SubCategory(
                    "Frozen Foods",
                    "QUICK MEALS \u2022 SNACKS",
                    "https://images.unsplash.com/photo-1558961363-fa8fdf82db35?auto=format&fit=crop&q=80&w=600"
                )
            )
        )
    }
}
