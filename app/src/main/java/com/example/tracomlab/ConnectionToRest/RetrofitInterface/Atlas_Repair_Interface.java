package com.example.tracomlab.ConnectionToRest.RetrofitInterface;

import com.example.tracomlab.ConnectionToRest.RetrofitModel.Atlas_Repair;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Atlas_Repair_Interface {

    @GET("/list/atlasRepair")
    Call<List<Atlas_Repair>> getFullList();

    @GET("/list/{serial_number}")
    Call<List<Atlas_Repair>> getFullListSearched(@Path("serial_number") Long serial_number);

}
