package com.jake5113.ex93kotlinopenapinaversearch

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitService {
    @GET("/v1/search/shop.json?display=100")
    fun searchDataByString(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientSecret: String,
        @Query("query") query: String
    ): Call<String>

    // 헤더값이 고정적이라면.. 굳이 매번 파라미터로 받지 말고
    @Headers("X-Naver-Client-Id: alsP6H9I23jfN3YuEmkr", "X-Naver-Client-Secret: CRAYHNBXmn")
    @GET("/v1/search/shop.json?display=100")
    fun searchData(@Query("query") query: String): Call<NaverSearchApiResponse>

}