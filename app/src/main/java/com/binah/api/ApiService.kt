package com.binah.api

import com.binah.data.DefaultData.Companion.QUESTIONS
import com.binah.data.ObjectsQueryResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {
    //Query for all questions
    @GET(QUESTIONS)
    fun getQuestions(@QueryMap params: MutableMap<String, String>): Single<ObjectsQueryResult>

}