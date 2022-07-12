package com.vald3nir.toolkit.data.repository.remote.firebase

import android.app.Activity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseAuthenticator {

    fun disconnect() {
        Firebase.auth.signOut()
    }

    fun getUserID(): String? {
        return Firebase.auth.currentUser?.uid
    }

    fun checkUserLogged(): Boolean {
        return Firebase.auth.currentUser != null
    }

    fun createUserWithEmailAndPassword(
        activity: Activity,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (e: Exception?) -> Unit
    ) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) {
                if (it.isSuccessful) {
                    onSuccess.invoke()
                } else {
                    onError.invoke(it.exception)
                }
            }
            .addOnFailureListener { e -> onError.invoke(e) }
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