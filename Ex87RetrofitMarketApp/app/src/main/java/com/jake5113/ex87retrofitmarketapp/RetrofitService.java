package com.jake5113.ex87retrofitmarketapp;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface RetrofitService {

    // POST 방식으로 보내기
    // 이미지 파일용 Part 와 나머지 문자열 데이터들 HashMap 으로 구분하여 전송
    // 즉, 택배박스 2개로..
    @Multipart
    @POST("RetrofitMarket/insertDB.php")
    Call<String> postDataToServer(@PartMap Map<String, String> dataPart, @Part MultipartBody.Part filePart);

    // 서버에서 echo 한 json array 를 읽어와서 자동으로 ArrayList<MarketItem>로 파싱하는 요구명세
    @GET("RetrofitMarket/loadDB.php")
    Call<ArrayList<MarketItem>> loadDataFromServer();

    // 서버 DB에서 특정 아이템을 삭제하도록 요청하는 명세
    @GET("RetrofitMarket/deleteItem.php")
    Call<String> deleteItem(@Query("no") String no);
}
