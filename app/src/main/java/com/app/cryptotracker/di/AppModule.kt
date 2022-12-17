package com.app.cryptotracker.di

import com.app.cryptotracker.common.Constants
import com.app.cryptotracker.data.remote.Api
import com.app.cryptotracker.data.repository.CoinRepositoryImpl
import com.app.cryptotracker.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi() : Api {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: Api) : CoinRepository {
        return CoinRepositoryImpl(api)
    }
}