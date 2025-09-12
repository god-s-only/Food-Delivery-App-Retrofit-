package com.example.foodieapp.data.auth

import android.content.Context
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.example.foodieapp.GoogleServiceClientID
import com.example.foodieapp.data.model.GoogleAccount
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential

class GoogleAuthUIProvider {
    suspend fun signIn(
        activityContext: Context,
        credentialManager: CredentialManager
    ): GoogleAccount{
        val creds = credentialManager.getCredential(
            activityContext,
            getCredentialsRequest()
        ).credential

        return handleCredentials(creds)
    }

    fun handleCredentials(credentials: Credential): GoogleAccount{
        when{
            credentials is CustomCredential && credentials.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL -> {
                val googleIdTokenCredential = credentials as GoogleIdTokenCredential
                return GoogleAccount(
                    token = googleIdTokenCredential.idToken,
                    displayName = googleIdTokenCredential.displayName ?: "",
                    profileImageUrl = googleIdTokenCredential.profilePictureUri.toString()
                )
            }
            else -> {
                throw IllegalArgumentException("Invalid credential type")
            }
        }
    }

    private fun getCredentialsRequest(): GetCredentialRequest{
        return GetCredentialRequest.Builder()
            .addCredentialOption(
                GetSignInWithGoogleOption.Builder(
                    GoogleServiceClientID
                ).build()
            ).build()
    }
}