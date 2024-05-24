package com.example.productapi_fakestore_eliza.remote_data;

import com.example.productapi_fakestore_eliza.models.ModelM;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("products")
    Call<List<ModelM>> getStoreProducts();
}
