package com.kp.pembekalan.ally.interfaces;

import com.kp.pembekalan.ally.models.FavoriteResponse;
import com.kp.pembekalan.ally.models.ImageResponse;
import com.kp.pembekalan.ally.models.KatalogResponse;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Greace Situmorang on 6/19/2019.
 */

public interface APIService {
    @POST("/api/train")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part file, @Part("file") RequestBody name);
    @GET("/catalog")
    Call<KatalogResponse> getKatalog();
    @GET("/favorit")
    Call<FavoriteResponse> getFavorite();
}