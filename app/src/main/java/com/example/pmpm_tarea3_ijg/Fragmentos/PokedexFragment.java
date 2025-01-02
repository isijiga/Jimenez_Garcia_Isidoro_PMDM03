package com.example.pmpm_tarea3_ijg.Fragmentos;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.pmpm_tarea3_ijg.Json.JsonPlaceHolderApi;
import com.example.pmpm_tarea3_ijg.Json.JsonRespuesta;
import com.example.pmpm_tarea3_ijg.databinding.FragmentPokedexBinding;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.example.pmpm_tarea3_ijg.Data.Adaptador;
import com.example.pmpm_tarea3_ijg.MapeoClases.Pokemon;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokedexFragment extends Fragment {
    List<Pokemon> pokemonAdapter;
    private FragmentPokedexBinding binding;
    private Retrofit retrofit;
    private List<Pokemon> pokemonList;
    int maximo = 1152;
    int aleatorio;
    public PokedexFragment() {
    }
    public static PokedexFragment newInstance(String param1, String param2) {
        PokedexFragment fragment = new PokedexFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentPokedexBinding.inflate(getLayoutInflater());
        getData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void getData() {
        aleatorio = ((int) (Math.random() * maximo));
        pokemonList = new ArrayList<>();
        pokemonAdapter = new ArrayList<>();
        retrofit = new Retrofit.Builder().
                baseUrl("https://pokeapi.co/api/v2/").
                addConverterFactory(GsonConverterFactory.create()).
                build();
        JsonPlaceHolderApi pokemonInterface = retrofit.create(JsonPlaceHolderApi.class);
        Call<JsonRespuesta> ListCall = pokemonInterface.getPokemonList(aleatorio,150);
        ListCall.enqueue(new Callback<JsonRespuesta>() {
            @Override
            public void onResponse(Call<JsonRespuesta> call, Response<JsonRespuesta> response) {
                if (!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getContext(), "Error_onResponse", Toast.LENGTH_SHORT);
                    toast.show();
                }
                JsonRespuesta jsonRespuesta = response.body();
                ArrayList<Pokemon> data = new ArrayList<>(Arrays.asList(jsonRespuesta.getResults()));
                Adaptador adapter = new Adaptador(data);
                binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<JsonRespuesta> call, Throwable t) {
                Toast toast = Toast.makeText(getContext(), "Error_onFailure " + t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}