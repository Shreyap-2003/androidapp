package com.example.composecustomerapp.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class UserType {
    CUSTOMER, PARTNER
}

data class RegisterUiState(
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val userType: UserType = UserType.CUSTOMER,
    val isLoading: Boolean = false,
    val isRegistered: Boolean = false,
    val error: String? = null
) {
    val canRegister: Boolean get() = firstName.isNotEmpty() && 
            lastName.isNotEmpty() && 
            phoneNumber.length == 10 && 
            password.length >= 5
}

class RegisterViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun onFirstNameChanged(name: String) {
        _uiState.update { it.copy(firstName = name) }
    }

    fun onLastNameChanged(name: String) {
        _uiState.update { it.copy(lastName = name) }
    }

    fun onPhoneNumberChanged(phoneNumber: String) {
        if (phoneNumber.length <= 10 && phoneNumber.all { it.isDigit() }) {
            _uiState.update { it.copy(phoneNumber = phoneNumber) }
        }
    }

    fun onPasswordChanged(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun togglePasswordVisibility() {
        _uiState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

    fun onUserTypeChanged(type: UserType) {
        _uiState.update { it.copy(userType = type) }
    }

    fun createAccount() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            // Simulate account creation
            delay(1000)
            _uiState.update { it.copy(isLoading = false, isRegistered = true) }
        }
    }

    fun resetRegistrationState() {
        _uiState.update { it.copy(isRegistered = false) }
    }
}
