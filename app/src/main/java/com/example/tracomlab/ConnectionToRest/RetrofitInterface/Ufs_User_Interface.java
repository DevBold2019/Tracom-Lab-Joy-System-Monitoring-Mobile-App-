package com.example.tracomlab.ConnectionToRest.RetrofitInterface;

import com.example.tracomlab.ConnectionToRest.RetrofitModel.Ufs_User_Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Ufs_User_Interface {
    @GET("lab/contact_list")
    Call<List<Ufs_User_Model>> getFullList();
}
