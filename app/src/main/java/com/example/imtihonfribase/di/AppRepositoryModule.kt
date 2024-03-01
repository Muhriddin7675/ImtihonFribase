package com.example.imtihonfribase.di

import com.example.imtihonfribase.domain.AppRepository
import com.example.imtihonfribase.domain.impl.AppRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AppRepositoryModule {
    @Binds
    fun getRepository(impl: AppRepositoryImpl): AppRepository
}