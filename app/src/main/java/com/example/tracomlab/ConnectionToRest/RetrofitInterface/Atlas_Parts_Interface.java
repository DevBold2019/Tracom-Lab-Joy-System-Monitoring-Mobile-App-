package com.example.tracomlab.ConnectionToRest.RetrofitInterface;

import com.example.tracomlab.ConnectionToRest.RetrofitModel.Atlas_Parts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Atlas_Parts_Interface {

    @GET("/list/atlasParts")
    Call<List<Atlas_Parts>> getFullList();
}
