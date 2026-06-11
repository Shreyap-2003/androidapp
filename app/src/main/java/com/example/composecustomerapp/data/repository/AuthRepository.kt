package com.example.composecustomerapp.data.repository

import com.example.composecustomerapp.data.local.TokenManager
import com.example.composecustomerapp.data.model.LoginRequest
import com.example.composecustomerapp.data.model.LoginResponse
import com.example.composecustomerapp.data.remote.AuthApi

class AuthRepository(
    private val authApi: AuthApi,
    private val tokenManager: TokenManager
) {
    suspend fun login(phoneNumber: String, password: String): Result<LoginResponse> {
        return try {
            val response = authApi.login(LoginRequest(phoneNumber, password))
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null && body.status == "SUCCESS") {
                    // Extract token from header "x-auth"
                    val token = response.headers()["x-auth"]
                    if (token != null) {
                        tokenManager.saveAuthToken(token)
                    }
                    Result.success(body)
                } else {
                    Result.failure(Exception(body?.message ?: "Login failed"))
                }
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun logout() {
        tokenManager.clearAuthToken()
    }
}
