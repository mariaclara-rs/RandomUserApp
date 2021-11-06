package com.example.randomuser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UsuService {
    @GET("api/")
    Call <Results> carregarUsu(
            @Query("inc") String data,
            @Query("nat") String nat,
            @Query("gender") String gender,
            @Query("noinfo") String noinfo
    );
}