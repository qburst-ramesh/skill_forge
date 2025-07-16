package com.qburst.bind.skillforge.common

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.exceptions.NoCredentialException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.qburst.bind.skillforge.quiz.presentation.components.GoogleUser

// AndroidOAuth.kt

actual class GoogleAuthManager(
    private val context: Context,
    private val credentialManager: CredentialManager
) {
    private val webClientId =
        "53236228923-bfqvbuk9o9po2dorrlmgb8g4t6htuk55.apps.googleusercontent.com"

    actual suspend fun signIn(): Result<GoogleUser?> {
        try {
            val googleIdOption = GetGoogleIdOption.Builder()
                .setServerClientId(webClientId)
                .setAutoSelectEnabled(false)
                .setFilterByAuthorizedAccounts(false)
                .build()
            val request = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()

            val result = credentialManager.getCredential(context, request)
            val credential = result.credential
            if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                val idToken = googleIdTokenCredential.idToken
                return Result.success(
                    GoogleUser(
                        token = idToken,
                        name = googleIdTokenCredential.displayName,
                        phoneNumber = googleIdTokenCredential.phoneNumber,
                        profilePictureUrl = googleIdTokenCredential.profilePictureUri.toString()
                    )
                )
            }
            return Result.failure("No Google ID token found".toThrowable())
        } catch (e: NoCredentialException) {
            e.printStackTrace()
            return Result.failure("No email found in your device".toThrowable())
        } catch (e: GetCredentialException) {
            e.printStackTrace()
            return Result.failure("GetCredentialException".toThrowable())
        } catch (e: Exception) {
            return Result.failure(e.message.toString().toThrowable())
        }
    }
}

private fun String.toThrowable(): Throwable {
    return Exception(this)
}
