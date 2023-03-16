package com.jake5113.ex87retrofitmarketapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitHelper {
    public static Retrofit getRetrofitInstance() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://jake5113.dothome.co.kr");
        builder.addConverterFactory(ScalarsConverterFactory.create()); // 순서 중요!!
        builder.addConverterFactory(GsonConverterFactory.create());
        return builder.build();
    }
}
