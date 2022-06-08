package com.vald3nir.toolkit.data.repository

import android.net.Uri
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.vald3nir.toolkit.data.dto.BaseDTO

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

