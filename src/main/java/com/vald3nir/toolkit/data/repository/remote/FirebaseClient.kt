package com.vald3nir.toolkit.data.repository.remote

import android.app.Activity
import android.net.Uri
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.vald3nir.toolkit.data.dto.BaseDTO


// =================================================================================================
// Authentication
// =================================================================================================

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

// =================================================================================================
// File Upload
// =================================================================================================

fun uploadFile(
    data: ByteArray,
    filePath: String,
    onSuccess: () -> Unit,
    onError: (e: Exception?) -> Unit
) {
    val storageRef: StorageReference = FirebaseStorage.getInstance().reference
    val mountainsRef = storageRef.child(filePath)
    mountainsRef.putBytes(data)
        .addOnSuccessListener { onSuccess.invoke() }
        .addOnFailureListener { e -> onError.invoke(e) }
}

fun loadFile(
    filePath: String,
    onSuccess: (uri: Uri) -> Unit,
    onError: (e: Exception?) -> Unit
) {
    FirebaseStorage.getInstance().getReferenceFromUrl(filePath).downloadUrl
        .addOnSuccessListener { onSuccess.invoke(it) }
        .addOnFailureListener { e -> onError.invoke(e) }
}

// =================================================================================================
// Data Storage
// =================================================================================================

fun updateData(
    rootPath: String,
    collectionPath: String,
    baseDTO: BaseDTO,
    onSuccess: () -> Unit,
    onError: (e: Exception?) -> Unit
) {
    val db = Firebase.firestore
    db.collection(rootPath)
        .document(baseDTO.uid)
        .collection(collectionPath)
        .add(baseDTO.toMap())
        .addOnSuccessListener { _ -> onSuccess.invoke() }
        .addOnFailureListener { e -> onError.invoke(e) }
}

fun updateData(
    collectionPath: String,
    baseDTO: BaseDTO,
    onSuccess: () -> Unit,
    onError: (e: Exception?) -> Unit
) {
    val db = Firebase.firestore
    db.collection(collectionPath)
        .document(baseDTO.uid)
        .set(baseDTO.toMap())
        .addOnSuccessListener { _ -> onSuccess.invoke() }
        .addOnFailureListener { e -> onError.invoke(e) }
}

fun deleteData(
    collectionPath: String,
    baseDTO: BaseDTO,
    onSuccess: () -> Unit,
    onError: (e: Exception?) -> Unit
) {
    val db = Firebase.firestore
    db.collection(collectionPath)
        .document(baseDTO.uid)
        .delete()
        .addOnSuccessListener { _ -> onSuccess.invoke() }
        .addOnFailureListener { e -> onError.invoke(e) }
}

fun <T> loadCollection(
    collectionPath: String,
    type: Class<T>,
    onSuccess: (items: MutableList<T>) -> Unit,
    onError: (e: Exception?) -> Unit
) {
    val items: MutableList<T> = mutableListOf()
    val db = Firebase.firestore
    db.collection(collectionPath).get()
        .addOnSuccessListener {
            it.documents.map { document ->
                val data: T? = document.toObject(type)
                data?.let { it -> items.add(it) }
            }
            onSuccess.invoke(items)
        }
        .addOnFailureListener { e -> onError.invoke(e) }
}


fun <T> loadCollection(
    rootPath: String,
    documentPath: String,
    collectionPath: String,
    type: Class<T>,
    onSuccess: (items: MutableList<T>) -> Unit,
    onError: (e: Exception?) -> Unit
) {
    val items: MutableList<T> = mutableListOf()
    val db = Firebase.firestore
    db.collection(rootPath)
        .document(documentPath)
        .collection(collectionPath)
        .get().addOnSuccessListener {
            it.documents.map { document ->
                val data: T? = document.toObject(type)
                data?.let { it -> items.add(it) }
            }
            onSuccess.invoke(items)
        }
        .addOnFailureListener { e -> onError.invoke(e) }
}

