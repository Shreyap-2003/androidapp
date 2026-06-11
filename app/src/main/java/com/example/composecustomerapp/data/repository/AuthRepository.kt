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
        println("AuthDebug: Repository login called for $phoneNumber")
        return try {
            val response = authApi.login(LoginRequest(phoneNumber, password))
            println("AuthDebug: Response received. Code: ${response.code()}")
            if (response.isSuccessful) {
                val body = response.body()
                println("AuthDebug: Response body: $body")
                if (body != null && body.status == "SUCCESS") {
                    val token = response.headers()["x-auth"] ?: response.headers()["Authorization"]
                    if (token != null) {
                        println("AuthDebug: Token found, saving to DataStore")
                        tokenManager.saveAuthToken(token)
                    } else {
                        println("AuthDebug: No x-auth or Authorization token in headers")
                    }
                    Result.success(body)
                } else {
                    println("AuthDebug: Body null or status not SUCCESS")
                    Result.failure(Exception(body?.message ?: "Login failed"))
                }
            } else {
                println("AuthDebug: HTTP Error: ${response.code()}")
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            println("AuthDebug: Exception in repository: ${e.message}")
            Result.failure(e)
        }
    }

    suspend fun logout() {
        tokenManager.clearAuthToken()
    }
}
