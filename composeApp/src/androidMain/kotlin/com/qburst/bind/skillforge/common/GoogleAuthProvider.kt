package com.qburst.bind.skillforge.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.CredentialManager

actual class GoogleAuthProvider() {

    @Composable
    actual fun getUiProvider(): GoogleAuthManager {
        val activityContext = LocalContext.current
        return GoogleAuthManager(
            context = activityContext,
            credentialManager = CredentialManager.create(context = activityContext)
        )
    }

    actual suspend fun signOut() {

    }
}