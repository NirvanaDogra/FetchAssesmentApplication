package com.fetchassignment.displaylist.di

import com.fetchassignment.displaylist.repository.HiringService
import com.fetchassignment.displaylist.usecase.DisplayListUseCase
import com.fetchassignment.displaylist.usecase.DisplayListUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DisplayListUseCaseModule {
    @Provides
    @Singleton
    fun bindsDisplayListUseCase(
        service: HiringService
    ): DisplayListUseCase {
        return DisplayListUseCaseImpl(service)
    }
}