package com.example.pr.API.service;

import com.example.pr.API.model.Login;
import com.example.pr.API.model.Papers;
import com.example.pr.API.model.Registration;
import com.example.pr.API.model.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserClient {

    @POST("login")
    Call<User> login (@Body Login Login);

    @POST("registration")
    Call<ResponseBody> Registration (@Body Registration Registration);

    @GET("check")
    Call<ResponseBody> check();

    @GET("papers")
    Call<Papers[]> papers();

    @POST("paper")
    Call<ResponseBody> createPaper (@Header("Authorization") String authToken,@Body Papers Papers);

    @GET("paper")
    Call<Papers[]> paper(@Header("Authorization") String authToken);


}
