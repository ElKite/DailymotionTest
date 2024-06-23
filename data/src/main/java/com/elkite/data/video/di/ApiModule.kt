package com.elkite.data.video.di

import androidx.paging.PagingSource
import com.elkite.data.video.network.VideoDailymotionApi
import com.elkite.data.video.network.VideoPagingSource
import com.elkite.data.video.network.model.VideoJson
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
class ApiModule {

    private val apiUrl: String = "https://api.dailymotion.com/"

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideDailymotionApi(retrofit: Retrofit): VideoDailymotionApi {
        return retrofit.create(VideoDailymotionApi::class.java)
    }

    @Singleton
    @Provides
    fun provideVideoPagingSource(
        api: VideoDailymotionApi
    ): PagingSource<Int, VideoJson> {
        return VideoPagingSource(api)
    }
}