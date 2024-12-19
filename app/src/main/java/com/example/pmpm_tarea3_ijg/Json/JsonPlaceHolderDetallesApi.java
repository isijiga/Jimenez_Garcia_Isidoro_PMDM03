package com.example.pmpm_tarea3_ijg.Json;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceHolderDetallesApi {

    @GET("pokemon/{id}")
    Call<JsonRespuestaDetalle> getPokemonList(@Path("id") int id);
}
