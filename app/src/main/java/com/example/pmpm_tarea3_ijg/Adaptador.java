package com.example.pmpm_tarea3_ijg;

import static androidx.databinding.adapters.TextViewBindingAdapter.setText;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmpm_tarea3_ijg.databinding.FragmentPokemonCapturadosBinding;
import com.example.pmpm_tarea3_ijg.databinding.ItemViewBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder>{
    private List<Pokemon> listaName;
    private Retrofit retrofit;
    com.example.pmpm_tarea3_ijg.databinding.FragmentPokemonCapturadosBinding bindingPC;
    ArrayList<PokemonCapturado> pokemonElegidos = new ArrayList<>();
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

        private void obtenerdatos(String url) {

            int numeroPagina = Integer.parseInt(url.substring(34,url.length()-1));

            retrofit = new  Retrofit.Builder().
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
                        Toast toast = Toast.makeText(itemView.getContext(), "Error_onResponse"+response.code(), Toast.LENGTH_SHORT);
                        toast.show();

                    }
                    JsonRespuestaDetalle jsonRespuestaDetalle = response.body();

                            PokemonCapturado item = new PokemonCapturado(jsonRespuestaDetalle.getHeight(),
                                    jsonRespuestaDetalle.getName(),
                                    jsonRespuestaDetalle.getOrder(),
                                    jsonRespuestaDetalle.getSprites(),
                                    jsonRespuestaDetalle.getWeight());

                            pokemonElegidos.add(item);

                    /*En este punto hay que implementarla manera de subirlo a la base de datos
                    *
                    * */

                    Toast toast = Toast.makeText(itemView.getContext(), pokemonElegidos.size()+"", Toast.LENGTH_SHORT);
                    toast.show();
                }

                @Override
                public void onFailure(Call<JsonRespuestaDetalle> call, Throwable t) {
                    Toast toast = Toast.makeText(itemView.getContext(), "Error_onFailure " + t.getMessage(), Toast.LENGTH_LONG);
                }
            });
        }

        public void bind(Pokemon pokemon) {
            binding.textView.setText(pokemon.getName());
        }

    }
}




