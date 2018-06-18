package com.example.kajol.myapplication.network;

import com.example.kajol.myapplication.AppConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kajol on 3/22/2017.
 */
public class ApiClient {
    private static Retrofit retrofit = null;

    private static OkHttpClient okHttpClient1 = new OkHttpClient().newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build();

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(AppConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())// use for creating gson
                    .client(okHttpClient1)
                    .build();
        }
        return retrofit;
    }
}
