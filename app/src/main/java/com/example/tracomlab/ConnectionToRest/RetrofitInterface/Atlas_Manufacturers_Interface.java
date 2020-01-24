package com.example.tracomlab.ConnectionToRest.RetrofitInterface;

import com.example.tracomlab.ConnectionToRest.RetrofitModel.Atlas_Manufacturers;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Atlas_Manufacturers_Interface {

    @GET("/list/atlasManufacturers")
    Call<List<Atlas_Manufacturers>> getFullList();

}
