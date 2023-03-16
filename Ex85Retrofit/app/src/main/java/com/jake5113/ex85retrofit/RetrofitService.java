package com.jake5113.ex85retrofit;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

// 인터페이스는 클래스와 비슷하지만 멤버로 추상메소드(이름만 있는 메소드)만 만들 수 있다.
public interface RetrofitService {
    // 원하는 작업을 위한 코드를 쓰는게 아니라 요구사항을 명세

    // 1. 단순하게 GET 방식으로 json 파일을 읽어오기
    @GET("Retrofit/board.json")
    Call<Item> getBoardJson();

    // 2. 경로의 이름을 위 1번처럼 고정하지 않고 사용자에게 파라미터로 전달 받아 지정할 수 있음. [@Path]
    @GET("{aaa}/{bbb}")
    Call<Item> getBoardJsonByPath(@Path("aaa") String path, @Path("bbb") String fileName);

    // 3. GET 방식으로 서버에 값 전달 [ @Query ]
    @GET("Retrofit/getTest.php")
    Call<Item> getMethodTest(@Query("name") String name, @Query("msg") String message);

    // 4. GET 방식으로 값을 보낼때 Map collection 을 이용하여 한방에 값을 전달하기
    @GET("Retrofit/getTest.php")
    Call<Item> getMethodTest2(@QueryMap Map<String, String> datas);

    // 5. POST 방식으로 값 전달 [ @Body ] - 객체를 전달하면 자동으로 객체멤버변수를 JSON 문자열로 변환하여 서버로 전송해 줌.
    @POST("Retrofit/postTest.php")
    Call<Item> postMethodTest(@Body Item item);

    // 6. POST 방식으로 값 하나씩 전달 [ @Field ]
    // 단, @Field 를 사용하려면 반드시 @FormUrlEncoded 와 함께 지정
    @FormUrlEncoded
    @POST("Retrofit/postTest2.php")
    Call<Item> postMethodTest2(@Field("name") String name, @Field("msg") String message);

    // 7. GET 방식으로 JSON array 값 읽어와서 ArrayList<Item> 로 곧바로 파싱
    @GET("Retrofit/boardArray.json")
    Call<ArrayList<Item>> getBoardArray();

    // 8. GSON 을 통해 자동으로 Item 객체로 파싱하지 않고 그냥 문자열로 응답결과 받아보기
    @GET("Retrofit/board.json")
    Call<String> getJsonString();
}
