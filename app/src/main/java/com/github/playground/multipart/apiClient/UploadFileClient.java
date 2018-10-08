package com.github.playground.multipart.apiClient;

import com.github.playground.multipart.constants.ApiUrls;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UploadFileClient {

    @Multipart
    @POST(ApiUrls.UPLOAD_FILE_URL)
    Call<Boolean> uploadFile(@Part MultipartBody.Part policyImageBody);

}
