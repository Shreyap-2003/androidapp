package com.example.composecustomerapp.ui.profile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ProfileUiState(
    val fullName: String = "Shreya Shetty",
    val firstName: String = "Shreya",
    val phoneNumber: String = "9876543210",
    val role: String = "CUSTOMER",
    val location: String = "Bangalore, India",
    val coordinates: String = "12.6, 77.59",
    val membershipType: String = "Premium Member",
    val membershipSince: String = "Member since Feb 2024",
    val status: String = "ACTIVE ACCOUNT",
    val profileImageUrl: String = "https://images.unsplash.com/photo-1494790108377-be9c29b29330?auto=format&fit=crop&q=80&w=200",
    val isLoading: Boolean = false
)

class ProfileViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun logout(onLogoutComplete: () -> Unit) {
        // Handle session clearing logic
        onLogoutComplete()
    }
}
