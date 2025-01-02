package com.example.pmpm_tarea3_ijg.Data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmpm_tarea3_ijg.Json.JsonPlaceHolderDetallesApi;
import com.example.pmpm_tarea3_ijg.Json.JsonRespuestaDetalle;
import com.example.pmpm_tarea3_ijg.databinding.ItemViewBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import com.example.pmpm_tarea3_ijg.MapeoClases.Pokemon;
import com.example.pmpm_tarea3_ijg.MapeoClases.PokemonCapturado;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {
    private List<Pokemon> listaName;
    private Retrofit retrofit;
    com.example.pmpm_tarea3_ijg.databinding.FragmentPokemonCapturadosBinding bindingPC;
    ArrayList<PokemonCapturado> pokemonElegidos = new ArrayList<>();
    private FirebaseFirestore db;
    FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mFirebaseAuth.getCurrentUser();
    ;

    public Adaptador(List<Pokemon> listaName) {
        this.listaName = listaName;
    }
    @NonNull
    @Override
    public Adaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemViewBinding binding = ItemViewBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador.ViewHolder holder, int position) {
        holder.bind(listaName.get(position));
    }

    @Override
    public int getItemCount() {
        return listaName.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemViewBinding binding;
        public ViewHolder(ItemViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getBindingAdapterPosition();
                    /*obtengo la url*/
                    String url = listaName.get(position).getUrl();
                    obtenerdatos(url);
                }
            });
        }

        /*metodo para obtener los datos de la api*/
        private void obtenerdatos(String url) {
            db = FirebaseFirestore.getInstance();
            int numeroPagina = Integer.parseInt(url.substring(34, url.length() - 1));
            retrofit = new Retrofit.Builder().
                    baseUrl("https://pokeapi.co/api/v2/").
                    addConverterFactory(GsonConverterFactory.create()).
                    build();
            JsonPlaceHolderDetallesApi pokemonInterface = retrofit.
                    create(JsonPlaceHolderDetallesApi.class);
            Call<JsonRespuestaDetalle> ListCall = pokemonInterface.getPokemonList(numeroPagina);
            ListCall.enqueue(new Callback<JsonRespuestaDetalle>() {
                @Override
                public void onResponse(Call<JsonRespuestaDetalle> call, Response<JsonRespuestaDetalle> response) {
                    if (!response.isSuccessful()) {
                        Toast toast = Toast.makeText(itemView.getContext(), "Error_onResponse" + response.code(), Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    JsonRespuestaDetalle jsonRespuestaDetalle = response.body();
                    PokemonCapturado item = new PokemonCapturado(
                            jsonRespuestaDetalle.getHeight(),
                            jsonRespuestaDetalle.getName(),
                            jsonRespuestaDetalle.getOrder(),
                            jsonRespuestaDetalle.getSprites(),
                            jsonRespuestaDetalle.getWeight(),
                            jsonRespuestaDetalle.getTypes(), null);
                    pokemonElegidos.add(item);
                    inyectardBBDD(item, itemView.getContext());

                }
                @Override
                public void onFailure(Call<JsonRespuestaDetalle> call, Throwable t) {
                    Toast toast = Toast.makeText(itemView.getContext(), "Error_onFailure " + t.getMessage(), Toast.LENGTH_LONG);
                }
            });
        }

        private void inyectardBBDD(PokemonCapturado item, Context context) {
            db.collection("users").document(user.getUid()).
                    collection("pokemonCapturados").add(item).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast toast = Toast.makeText(context
                                    , "Pokemon " + item.getName() + " capturado", Toast.LENGTH_SHORT);
                            toast.show();

                        }
                    })
            ;
        }

        public void bind(Pokemon pokemon) {
            binding.textView.setText(pokemon.getName());
        }
    }
}




