package com.aran.submissioncompose.di

import com.aran.submissioncompose.data.FruitRepository

object Injection {
    fun provideRepository(): FruitRepository {
        return FruitRepository.getInstance()
    }
}