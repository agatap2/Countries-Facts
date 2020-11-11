package com.agatakobusinska.countriesfacts.main.di

import android.content.Context
import com.agatakobusinska.countriesfacts.main.model.remote.CountryRemoteDataSource
import com.agatakobusinska.countriesfacts.main.model.remote.CountryService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi): Retrofit {
        return Retrofit.Builder().baseUrl("https://restcountries.eu/rest/v2/")
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
    }

    @Provides
    fun provideCountryService(retrofit: Retrofit): CountryService =
        retrofit.create(CountryService::class.java)

    @Singleton
    @Provides
    fun provideCountryRemoteDataSource(countryService: CountryService) =
        CountryRemoteDataSource(countryService)

    @Singleton
    @Provides
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(Cache(context.cacheDir, 5 * 1024 * 1014))
            .build()
    }
}