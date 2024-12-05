package com.example.androidassignment.di

import com.example.androidassignment.data.remote.AuthInterceptor
import com.example.androidassignment.data.remote.MainAPI
import com.example.androidassignment.data.repository.MainRepositoryImpl
import com.example.androidassignment.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(authInterceptor: AuthInterceptor): Retrofit {

        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val okhttpClient = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("http://165.232.191.15/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClient)
            .build()
    }

    @Provides
    fun provideMainAPI(retrofit: Retrofit): MainAPI {
        return retrofit.create(MainAPI::class.java)
    }

    @Provides
    fun provideMainRepository(mainAPI: MainAPI): MainRepository {
        return MainRepositoryImpl(mainAPI)
    }
}