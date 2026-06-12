package com.example.composecustomerapp.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.composecustomerapp.MainApplication
import com.example.composecustomerapp.data.local.TokenManager
import com.example.composecustomerapp.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

data class ProfileUiState(
    val fullName: String = "User",
    val firstName: String = "User",
    val phoneNumber: String = "",
    val role: String = "CUSTOMER",
    val location: String = "Bangalore, India",
    val coordinates: String = "12.6, 77.59",
    val membershipType: String = "Premium Member",
    val membershipSince: String = "Member since Feb 2024",
    val status: String = "ACTIVE ACCOUNT",
    val profileImageUrl: String = "https://images.unsplash.com/photo-1494790108377-be9c29b29330?auto=format&fit=crop&q=80&w=200",
    val isLoading: Boolean = false
)

class ProfileViewModel(
    private val authRepository: AuthRepository,
    private val tokenManager: TokenManager
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        fetchUserDetails()
    }

    private fun fetchUserDetails() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val userIdStr = tokenManager.userId.first()
            val userId = userIdStr?.toIntOrNull()
            
            if (userId != null) {
                val result = authRepository.getUser(userId)
                result.onSuccess { user ->
                    val name = user.name ?: "User"
                    _uiState.update { 
                        it.copy(
                            fullName = name,
                            firstName = name.split(" ").firstOrNull() ?: name,
                            phoneNumber = user.phoneNumber ?: "",
                            role = user.userType ?: "CUSTOMER",
                            isLoading = false
                        )
                    }
                }.onFailure {
                    _uiState.update { it.copy(isLoading = false) }
                }
            } else {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun logout(onLogoutComplete: () -> Unit) {
        viewModelScope.launch {
            authRepository.logout()
            onLogoutComplete()
        }
    }

    private fun MutableStateFlow<ProfileUiState>.update(function: (ProfileUiState) -> ProfileUiState) {
        this.value = function(this.value)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MainApplication)
                ProfileViewModel(application.authRepository, application.tokenManager)
            }
        }
    }
}
