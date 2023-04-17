package com.nigel.marks.wizsquared.data.remote

import com.nigel.marks.wizsquared.data.model.SpellResult
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://api.open5e.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface SpellsAPIService {

    @GET("spells/")
    suspend fun getSpellResults(@Query("page") n: Int) : SpellResult
}

object SpellAPI {
    val retrofitService: SpellsAPIService by lazy { retrofit.create(SpellsAPIService::class.java) }
}