package com.example.imtihonfribase.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {

    @[Provides Singleton]
    fun provideFireStore(): FirebaseFirestore = Firebase.firestore

    @[Provides Singleton]
    fun provideFirebaseStorage(): FirebaseStorage = Firebase.storage
}