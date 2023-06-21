package com.sample.simpsonsviewer.api

import com.sample.simpsonsviewer.models.DuckDuckGoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DuckDuckGoService {
    @GET("/")
    suspend fun getCharacters(@Query("q", encoded = true) query: String, @Query("format") format: String): DuckDuckGoResponse
}