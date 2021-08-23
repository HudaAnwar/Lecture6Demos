package com.huda.lecture6demos.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FCMRetrofit {
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://fcm.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private static final APIService apiService = retrofit.create(APIService.class);

    public static APIService getApiService() {
        return apiService;
    }
}
