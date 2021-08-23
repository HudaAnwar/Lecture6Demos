package com.huda.lecture6demos.model;



import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers( {
            "Content-Type:application/json",
            "Authorization:Key=AAAAU0dnL7g:APA91bEBhE6xX3ZH2MTu2ViRB4SPzXMFuV4u5Amx97vbmCP_kh6-GFqCGt_4OSmj8BKuog3J5WkTuApymJ0Py5LpOPT4-v6DWx_aheO1GnPQ_RWEedSkU_Wyjq-cA1gPkFy6WmSrWxMt"
    })
    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender sender);
}
