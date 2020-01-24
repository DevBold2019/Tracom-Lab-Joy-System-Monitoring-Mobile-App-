package com.example.tracomlab.ConnectionToRest.RetrofitInterface;

import com.example.tracomlab.ConnectionToRest.RetrofitModel.Atlas_Customers;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Atlas_Customers_Interface {

    @GET("/list/atlasCustomers")
    Call<List<Atlas_Customers>> getFullList();

}
