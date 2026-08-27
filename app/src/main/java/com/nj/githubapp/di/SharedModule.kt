package com.nj.githubapp.di

import com.nj.githubapp.data.remote.GitDataSource
import com.nj.githubapp.data.repository.GitRepositoryImpl
import com.nj.githubapp.domain.repository.GitRepository
import dagger.Binds
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
abstract class SharedModule {

    @Binds
    @Singleton
    abstract fun provideRepository(imp: GitRepositoryImpl): GitRepository

    companion object {
        @Provides
        @Singleton
        fun providesGson(): GsonConverterFactory = GsonConverterFactory.create()

        @Provides
        @Singleton
        fun providesLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        @Provides
        @Singleton
        fun providesOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

        @Provides
        @Singleton
        fun providesGitDataSource(
            okHttpClient: OkHttpClient,
            gsonConverterFactory: GsonConverterFactory
        ): GitDataSource =
            Retrofit.Builder()
                .addConverterFactory(gsonConverterFactory)
                .client(okHttpClient)
                .baseUrl("https://api.github.com/")
                .build()
                .create(GitDataSource::class.java)
    }
}
