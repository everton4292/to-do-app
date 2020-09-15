package com.resende.todolist.di.module


import com.resende.todolist.BuildConfig
import com.resende.todolist.data.repository.TodoRepositoryImp
import com.resende.todolist.data.source.local.TodoDao
import com.resende.todolist.data.source.remote.GithubOauthAccessService
import com.resende.todolist.domain.repository.TodoRepository
import com.resende.todolist.presentation.greetings.GreetingRepository
import com.resende.todolist.presentation.greetings.GreetingRepositoryImpl
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val NetworkModule = module {
    single { createGithubService(get()) }
    single { createRetrofit(get(), BuildConfig.BASE_URL) }
    single { createOkHttpClient() }
}

fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .build()
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun createGithubService(retrofit: Retrofit): GithubOauthAccessService {
    return retrofit.create(GithubOauthAccessService::class.java)
}

fun createTodoRepository(todoDao: TodoDao): TodoRepository {
    return TodoRepositoryImp(todoDao)
}

fun createGreetingRepository(githubOauthAccessService: GithubOauthAccessService): GreetingRepository {
    return GreetingRepositoryImpl(githubOauthAccessService)
}