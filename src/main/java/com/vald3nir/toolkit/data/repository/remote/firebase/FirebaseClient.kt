package com.vald3nir.toolkit.data.repository.remote.firebase

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.vald3nir.toolkit.data.dto.BaseDTO

class FirebaseClient {

    fun updateData(
        rootPath: String,
        document: String,
        collection: String,
        baseDTO: BaseDTO,
        onSuccess: () -> Unit,
        onError: (e: Exception?) -> Unit
    ) {
        val db = Firebase.firestore
        db.collection(rootPath)
            .document(document)
            .collection(collection)
            .document(baseDTO.uid)
            .set(baseDTO.toMap())
            .addOnSuccessListener { _ -> onSuccess.invoke() }
            .addOnFailureListener { e -> onError.invoke(e) }
    }

    fun deleteData(
        rootPath: String,
        document: String,
        collection: String,
        baseDTO: BaseDTO,
        onSuccess: () -> Unit,
        onError: (e: Exception?) -> Unit
    ) {
        val db = Firebase.firestore
        db.collection(rootPath)
            .document(document)
            .collection(collection)
            .document(baseDTO.uid)
            .delete()
            .addOnSuccessListener { _ -> onSuccess.invoke() }
            .addOnFailureListener { e -> onError.invoke(e) }
    }

    fun <T> loadCollection(
        rootPath: String,
        document: String,
        collection: String,
        type: Class<T>,
        onSuccess: (items: MutableList<T>) -> Unit,
        onError: (e: Exception?) -> Unit
    ) {
        val items: MutableList<T> = mutableListOf()
        val db = Firebase.firestore
        db.collection(rootPath)
            .document(document)
            .collection(collection)
            .get()
            .addOnSuccessListener {
                it.documents.map { document ->
                    val data: T? = document.toObject(type)
                    data?.let { it -> items.add(it) }
                }
                onSuccess.invoke(items)
            }
            .addOnFailureListener { e -> onError.invoke(e) }
    }

}
