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
    val uploadTask = mountainsRef.putBytes(data)
    uploadTask
        .addOnFailureListener { onError.invoke(it) }
        .addOnSuccessListener { onSuccess.invoke() }
}

fun loadFile(
    filePath: String,
    onSuccess: (uri: Uri) -> Unit,
    onError: (e: Exception?) -> Unit
) {
    FirebaseStorage.getInstance().getReferenceFromUrl(filePath).downloadUrl
        .addOnSuccessListener { onSuccess.invoke(it) }
        .addOnFailureListener { onError.invoke(it) }
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

