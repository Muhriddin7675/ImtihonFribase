package com.example.imtihonfribase.domain.impl

import com.example.imtihonfribase.data.ProductData
import com.example.imtihonfribase.data.local.pref.MyShared
import com.example.imtihonfribase.domain.AppRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore
) : AppRepository {

    override fun getAllProduct(): Flow<Result<List<ProductData>>> = callbackFlow {
        fireStore.collection("shop")
            .get()
            .addOnSuccessListener { querySnapshot ->
                val list = ArrayList<ProductData>()
                querySnapshot.forEach {
                    val data =
                        ProductData(
                            docId = it.id,
                            categoryName = it.data["categoryName"].toString(),
                            image = it.data["image"].toString(),
                            price = it.data["price"].toString(),
                            typeName = it.data["typeName"].toString(),
                            type_id = it.data["type_id"].toString().toInt()
                        )
                    list.add(data)
                }
                trySend(Result.success(list))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }
        awaitClose()
    }

}