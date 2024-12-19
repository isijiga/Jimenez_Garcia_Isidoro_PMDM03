package com.example.pmpm_tarea3_ijg.Json;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    @GET("pokemon/")
    Call<JsonRespuesta> getPokemonList(
            @Query("offset") int num,
            @Query("limit") int limit);

}


