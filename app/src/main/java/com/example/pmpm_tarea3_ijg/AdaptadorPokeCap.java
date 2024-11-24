package com.example.pmpm_tarea3_ijg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmpm_tarea3_ijg.databinding.CardViewBinding;

import java.util.ArrayList;

public class AdaptadorPokeCap extends RecyclerView.Adapter<AdaptadorPokeCap.ViewHolder> {

    private ArrayList<PokemonCapturado> listaPokemon;
    private Context context;

    public AdaptadorPokeCap(ArrayList<PokemonCapturado> listaPokemon, Context context) {
        this.context = context;
        this.listaPokemon = listaPokemon;
    }

    @NonNull
    @Override
    public AdaptadorPokeCap.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardViewBinding binding = CardViewBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPokeCap.ViewHolder holder, int position) {
        PokemonCapturado pokemon = listaPokemon.get(position);
        holder.bind(pokemon);
    }

    @Override
    public int getItemCount() {
        return listaPokemon.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private final CardViewBinding binding2;
        public ViewHolder(CardViewBinding binding) {
            super(binding.getRoot());
            this.binding2 = binding;}

        public void bind (PokemonCapturado pokemon) {
            binding2.cvtextNombre.setText(pokemon.getName());
            binding2.cvtextCod.setText(pokemon.getOrder()+"");
            /*binding.cvImageView.setImageResource(pokemon.getSprites().getFront_default());*/
            binding2.executePendingBindings();
        }
    }
}
