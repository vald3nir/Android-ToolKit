package com.vald3nir.toolkit.data.repository.remote.firebase

import android.app.Activity
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseAuthenticator {

    fun disconnect() {
        Firebase.auth.signOut()
    }

    fun loadCurrentUser(): FirebaseUser? {
        return Firebase.auth.currentUser
    }

    fun signInWithEmailAndPassword(
        activity: Activity,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (e: Exception?) -> Unit
    ) {
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) {
                if (it.isSuccessful) {
                    onSuccess.invoke()
                } else {
                    onError.invoke(it.exception)
                }
            }
            .addOnFailureListener { e -> onError.invoke(e) }
    }
}