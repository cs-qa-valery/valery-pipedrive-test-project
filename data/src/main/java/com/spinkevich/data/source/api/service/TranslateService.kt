package com.spinkevich.data.source.api.service

import com.spinkevich.data.source.api.response.TranslateResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TranslateService {

    @GET("api/v1/dicservice.json/lookup")
    suspend fun translate(
        @Query("key") key: String,
        @Query("lang") lang: String,
        @Query("text") text: String
    ): TranslateResponse
}