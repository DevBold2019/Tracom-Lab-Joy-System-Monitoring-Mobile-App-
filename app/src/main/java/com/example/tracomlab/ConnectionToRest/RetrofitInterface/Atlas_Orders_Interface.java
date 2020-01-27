package com.example.tracomlab.ConnectionToRest.RetrofitInterface;

import com.example.tracomlab.ConnectionToRest.RetrofitModel.Atlas_Orders;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Atlas_Orders_Interface {

    @GET("/list/atlasOrders")
    Call<List<Atlas_Orders>> getFullList();

    @PUT("/list/{order_id}")
    Call<Atlas_Orders> approveList(@Body Atlas_Orders orders,@Path("order_id")Long order_id);
}
