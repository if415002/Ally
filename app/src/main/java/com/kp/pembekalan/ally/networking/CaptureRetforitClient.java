package com.kp.pembekalan.ally.networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Greace Situmorang on 6/19/2019.
 */

public class CaptureRetforitClient {
    public static String BASE_UEL = "http://0.0.0.0:5003/api/train";

    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl( CaptureRetforitClient.BASE_UEL)
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
    }
}
