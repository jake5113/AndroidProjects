package com.jake5113.ex86retrofitimageupload;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

// 이미지 파일은 택배상자[MultipartBody.Part]에 넣어서 전송함
// @Part 어노테이션을 사용. 단, @Multipart 어노테이션과 함께
public interface RetrofitService {
    @Multipart
    @POST("Retrofit/fileUpload.php")
    Call<String> uploadImage(@Part MultipartBody.Part file);
}
