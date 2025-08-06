package com.qburst.bind.skillforge.common

import android.content.Context
import androidx.credentials.ClearCredentialStateRequest
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

    val googleIdOption = GetGoogleIdOption.Builder()
        .setServerClientId(webClientId)
        .setAutoSelectEnabled(false)
        .setFilterByAuthorizedAccounts(false)
        .build()
    val request = GetCredentialRequest.Builder()
        .addCredentialOption(googleIdOption)
        .build()

    actual suspend fun signIn(): Result<GoogleUser?> {
        /*return Result.success(
            GoogleUser(
                token = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImYxMDMzODYwNzE2ZTNhMmFhYjM4MGYwMGRiZTM5YTcxMTQ4NDZiYTEiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiI1MzIzNjIyODkyMy1lZWthZ2ZtdTVsYXMyMDVzZjNiOGxtYmhodmFsbHI3OC5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsImF1ZCI6IjUzMjM2MjI4OTIzLWJmcXZidWs5bzlwbzJkb3JybG1nYjhnNHQ2aHR1azU1LmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwic3ViIjoiMTA1NzYzNDU1NzQ2ODIzMjczMTMxIiwiaGQiOiJxYnVyc3QuY29tIiwiZW1haWwiOiJyYW1lc2guYXJhdmluZEBxYnVyc3QuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsIm5hbWUiOiJSYW1lc2ggQXJhdmluZCIsInBpY3R1cmUiOiJodHRwczovL2xoMy5nb29nbGV1c2VyY29udGVudC5jb20vYS9BQ2c4b2NLYy1Td3NQeHdPd0llU3Bfc3pVbGRjZlI4amMtRW8zVHZ2U1RKNEd3bGtsVkN6WjVXcD1zOTYtYyIsImdpdmVuX25hbWUiOiJSYW1lc2giLCJmYW1pbHlfbmFtZSI6IkFyYXZpbmQiLCJpYXQiOjE3NTI3NDE1ODgsImV4cCI6MTc1Mjc0NTE4OH0.U65L6TXvX-SBaQz7TyloNAynHhvOttoHduK9nfJMpBTfUGbb09VbYmcHLDK5hq831GBWrh0ZKuJ-rF95VqbPm0hjqdHrvufcSvq9Lcs7lcjnk4AsUtx2loXz32AW9-ODhdhpNgEBtidbImSSG_pyXypWatWf7c3JFV_j7cqmdQ0spZUUsLmYquRdJOQZ3Nrh2GcRChS2pAsOhGzugothS2UHQfQmRn9PKdoayr2HQEV02OxhF1iJ_HFHZaHqcI3xHLxFBZV1XBICd8hIeCxpmmD75xzWjaCU-a9R0VN5JZr3ooWfrJADROlR45zcXfurqqm4iKjrAZf5u2uMU5P8TQ",
                name = "",
                phoneNumber = "",
                profilePictureUrl = ""
            )
        )*/
        try {
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

    actual suspend fun signOut() {
        credentialManager.clearCredentialState(request = ClearCredentialStateRequest())
    }
}

private fun String.toThrowable(): Throwable {
    return Exception(this)
}
