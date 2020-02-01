package com.example.tracomlab.ConnectionToRest.RetrofitInterface;

import com.example.tracomlab.ConnectionToRest.RetrofitModel.Ufs_Otp;
import com.example.tracomlab.ConnectionToRest.RetrofitModel.Ufs_User_Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

public interface Ufs_Otp_Interface {
    @PUT("/get/otp")
    Call<List<Ufs_Otp>> getFullList(@Body Ufs_Otp otp);
}
