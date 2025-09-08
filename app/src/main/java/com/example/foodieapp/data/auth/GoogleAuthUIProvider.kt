package com.example.foodieapp.data.auth

import android.content.Context
import androidx.credentials.CredentialManager
import com.example.foodieapp.data.model.GoogleAccount

class GoogleAuthUIProvider {
    suspend fun signIn(
        activityContext: Context,
        credentialManager: CredentialManager
    ): GoogleAccount{
        val creds = credentialManager.getCredential()
    }
}