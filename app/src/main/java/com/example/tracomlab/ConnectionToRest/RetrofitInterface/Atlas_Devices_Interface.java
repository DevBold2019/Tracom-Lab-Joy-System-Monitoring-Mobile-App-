package com.example.tracomlab.ConnectionToRest.RetrofitInterface;


import com.example.tracomlab.ConnectionToRest.RetrofitModel.Atlas_Devices;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Atlas_Devices_Interface {

    @GET("/list/atlasDevive")
    Call<List<Atlas_Devices>> getFullList();

    @GET("/list/atlasDevive")
    Call<List<Atlas_Devices>> getListWithPaging();
}
