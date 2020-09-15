package com.resende.todolist.data.source.remote

import com.resende.todolist.data.model.AccessTokenResponse
import retrofit2.http.*


interface GithubOauthAccessService {
    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    suspend fun getAccessToken(
        @Field("code") code: String?,
        @Field("client_id") client_id: String,
        @Field("client_secret") client_secret: String
    ): AccessTokenResponse
}