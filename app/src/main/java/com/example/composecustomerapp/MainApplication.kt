package com.example.composecustomerapp

import android.app.Application
import com.example.composecustomerapp.data.local.TokenManager
import com.example.composecustomerapp.data.remote.RetrofitClient
import com.example.composecustomerapp.data.repository.AuthRepository

class MainApplication : Application() {
    lateinit var tokenManager: TokenManager
    lateinit var authRepository: AuthRepository

    override fun onCreate() {
        super.onCreate()
        tokenManager = TokenManager(this)
        val authApi = RetrofitClient.createAuthApi(tokenManager)
        authRepository = AuthRepository(authApi, tokenManager)
    }
}
