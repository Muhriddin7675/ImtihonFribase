package com.example.imtihonfribase.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ProductData(
    val docId: String,
    val categoryName: String,
    val image: String,
    val price: String,
    val typeName: String,
    val type_id: Int
) : Parcelable