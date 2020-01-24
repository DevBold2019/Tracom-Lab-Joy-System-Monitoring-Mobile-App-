package com.example.tracomlab.ConnectionToRest.RetrofitInterface;


import com.example.tracomlab.ConnectionToRest.RetrofitModel.Atlas_Delivery;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Atlas_Delivery_Interface {

    @GET("/list/atlasDelivery")
    Call<List<Atlas_Delivery>> getFullList();
}
