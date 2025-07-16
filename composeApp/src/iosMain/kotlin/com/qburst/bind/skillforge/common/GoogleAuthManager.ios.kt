package com.qburst.bind.skillforge.common// iosMain/GoogleAuthManager.kt
import com.qburst.bind.skillforge.quiz.presentation.components.GoogleUser
import platform.UIKit.UIApplication
import platform.UIKit.UIViewController
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

// iOS (iosMain)
actual class GoogleAuthManager(private val viewController: UIViewController) : IGoogleAuthManager {
    override suspend fun signIn() = suspendCoroutine<Result<GoogleUser?>> { continuation ->

        val rootUiView = UIApplication.sharedApplication
            .keyWindow?.rootViewController

        if (rootUiView == null) {
            continuation.resume(null)
        } else {
            GIDSignIn.sharedInstance
                .signInWithPresentingViewController(rootUiView) { gidSignInResult, nsError ->
                    if (nsError != null) {
                        println("Something went wrong during signInWithPresentingViewController $nsError")
                    } else {
                        val idToken = gidSignInResult?.user?.idToken
                        gidSignInResult?.user?.profile
                        if (idToken != null) {
                            registerUserOnFirebase(
                                idToken.tokenString,
                                accessToken = gidSignInResult?.user?.accessToken?.tokenString.toString(),
                                continuation
                            )
                        } else {
                            continuation.resume(null)
                        }
                    }
                }
        }

    }

    override suspend fun signOut() {
        TODO("Not yet implemented")
    }
}