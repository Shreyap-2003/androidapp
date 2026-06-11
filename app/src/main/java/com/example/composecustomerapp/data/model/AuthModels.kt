package com.example.composecustomerapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val phoneNumber: String,
    val password: String
)

@Serializable
data class LoginResponse(
    val status: String,
    val name: String? = null,
    val customerId: Int? = null,
    val message: String? = null,
    val userType: String? = null
)
