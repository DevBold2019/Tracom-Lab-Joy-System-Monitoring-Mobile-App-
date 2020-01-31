package com.example.tracomlab.ConnectionToRest.RetrofitInterface;

import com.example.tracomlab.ConnectionToRest.RetrofitModel.Atlas_Parts;
import com.example.tracomlab.ConnectionToRest.RetrofitModel.Atlas_Shipped;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Atlas_Shipped_Interface {

    @GET("/list/atlasShipped")
    Call<List<Atlas_Shipped>> getFullList();
}
