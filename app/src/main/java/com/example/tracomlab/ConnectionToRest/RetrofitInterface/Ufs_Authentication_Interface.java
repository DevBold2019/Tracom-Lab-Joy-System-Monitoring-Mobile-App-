package com.example.tracomlab.ConnectionToRest.RetrofitInterface;

import com.example.tracomlab.ConnectionToRest.RetrofitModel.Ufs_Authentication_Model;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Ufs_Authentication_Interface {
    @POST("oauth/token")
    @FormUrlEncoded
    Call<Ufs_Authentication_Model> getAuth(
            @Field("grant_type") String grantType,
            @Field("username") String username,
            @Field("password") String password
    );
}
