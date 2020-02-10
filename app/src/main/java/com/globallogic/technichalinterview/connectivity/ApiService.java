package com.globallogic.technichalinterview.connectivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/list")
    Call<List<Notebook>> getNotebooks();

}
