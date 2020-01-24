package com.example.tracomlab.ConnectionToRest.RetrofitInterface;

import com.example.tracomlab.ConnectionToRest.RetrofitModel.Atlas_Repair;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Atlas_Repair_Interface {

    @GET("/list/atlasRepair")
    Call<List<Atlas_Repair>> getFullList();
}
