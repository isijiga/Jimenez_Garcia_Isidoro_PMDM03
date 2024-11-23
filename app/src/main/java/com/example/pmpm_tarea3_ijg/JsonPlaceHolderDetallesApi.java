package com.example.pmpm_tarea3_ijg;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderDetallesApi {

    @GET("pokemon/{id}")
    Call<JsonRespuestaDetalle> getPokemonList(@Path("id") int id);
}
