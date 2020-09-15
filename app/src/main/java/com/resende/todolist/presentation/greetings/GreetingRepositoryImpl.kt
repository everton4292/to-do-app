package com.resende.todolist.presentation.greetings

import com.resende.todolist.data.model.AccessTokenResponse
import com.resende.todolist.data.source.remote.GithubOauthAccessService

interface GreetingRepository {
    suspend fun getAccessToken(code: String, clientId : String, clientSecret: String) : AccessTokenResponse
}

class GreetingRepositoryImpl(private val githubOauthAccessService: GithubOauthAccessService) : GreetingRepository{

    override suspend fun getAccessToken(code: String, clientId : String, clientSecret: String): AccessTokenResponse {
        return githubOauthAccessService.getAccessToken(code, clientId, clientSecret)
    }
}


