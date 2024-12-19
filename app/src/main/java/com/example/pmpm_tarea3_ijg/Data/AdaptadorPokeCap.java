package com.example.pmpm_tarea3_ijg.Data;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmpm_tarea3_ijg.Fragmentos.PokemonDetalleFragment;
import com.example.pmpm_tarea3_ijg.MainActivity;
import com.example.pmpm_tarea3_ijg.MapeoClases.PokemonCapturado;
import com.example.pmpm_tarea3_ijg.R;
import com.example.pmpm_tarea3_ijg.databinding.CardViewBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorPokeCap extends RecyclerView.Adapter<AdaptadorPokeCap.ViewHolder> {


    private SharedPreferences sharedPreferences;
    private ArrayList<PokemonCapturado> listaPokemon;
    private Context context;
    private CardViewBinding binding;


    public AdaptadorPokeCap(ArrayList<PokemonCapturado> listaPokemon, Context context) {
        this.context = context;
        this.listaPokemon = listaPokemon;
    }

    @NonNull
    @Override
    public AdaptadorPokeCap.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = CardViewBinding.inflate(
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
        private CardViewBinding binding;

        public ViewHolder(CardViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


            sharedPreferences = context.getSharedPreferences("prefSwitch", Context.MODE_PRIVATE);
            boolean switchState = sharedPreferences.getBoolean("switchState", false);

            if (switchState) {
                binding.bottomDelete.setVisibility(View.VISIBLE);
            } else {
                binding.bottomDelete.setVisibility(View.GONE);
            }


        }

        public void bind(PokemonCapturado pokemon) {
            Bundle bundle = new Bundle();
            binding.cvtextNombre.setText(pokemon.getName());
            binding.cvtextCod.setText(pokemon.getOrder() + "");
            Picasso.get().load(pokemon.getSprites().getFront_default()).into(binding.cvimageView);
            binding.cvtype.setText(pokemon.getTypes().getType().getName());

            switch (pokemon.getTypes().getType().getName()) {
                case "grass":
                    binding.cvtypeImage.setImageResource(R.drawable.grass);
                    break;
                case "normal":
                    binding.cvtypeImage.setImageResource(R.drawable.normal);
                    break;
                case "fire":
                    binding.cvtypeImage.setImageResource(R.drawable.fire);
                    break;
                case "water":
                    binding.cvtypeImage.setImageResource(R.drawable.water);
                    break;
                case "electric":
                    binding.cvtypeImage.setImageResource(R.drawable.electric);
                    break;
                case "ground":
                    binding.cvtypeImage.setImageResource(R.drawable.ground);
                    break;
                case "rock":
                    binding.cvtypeImage.setImageResource(R.drawable.rock);
                    break;
                case "fairy":
                    binding.cvtypeImage.setImageResource(R.drawable.fairy);
                    break;
                case "poison":
                    binding.cvtypeImage.setImageResource(R.drawable.poison);
                    break;
                case "bug":
                    binding.cvtypeImage.setImageResource(R.drawable.bug);
                    break;
                case "dragon":
                    binding.cvtypeImage.setImageResource(R.drawable.dragon);
                    break;
                case "psychic":
                    binding.cvtypeImage.setImageResource(R.drawable.psychic);
                    break;
                case "flying":
                    binding.cvtypeImage.setImageResource(R.drawable.flying);
                    break;
                case "fighting":
                    binding.cvtypeImage.setImageResource(R.drawable.fight);
                    break;

                case "ghost":
                    binding.cvtypeImage.setImageResource(R.drawable.ghost);
                    break;

                case "ice":
                    binding.cvtypeImage.setImageResource(R.drawable.ice);
                    break;
                case "steel":
                    binding.cvtypeImage.setImageResource(R.drawable.steel);
                    break;
                case "dark":
                    binding.cvtypeImage.setImageResource(R.drawable.dark);
                    break;


            }
            ;


            binding.executePendingBindings();


            binding.cvpokemon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    FragmentManager fm = ((MainActivity) context).getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    PokemonDetalleFragment pokemonDetalleFragment = new PokemonDetalleFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("nombre", pokemon.getName());
                    bundle.putString("type", pokemon.getTypes().getType().getName());
                    bundle.putInt("peso", pokemon.getWeight());
                    bundle.putInt("altura", pokemon.getHeight());
                    bundle.putString("url", pokemon.getSprites().getFront_default());

                    pokemonDetalleFragment.setArguments(bundle);

                    ft.replace(R.id.my_nav_host_fragment, pokemonDetalleFragment)
                            .addToBackStack(null)
                            .commit();


                }
            });

            binding.bottomDelete.setOnClickListener(new View.OnClickListener() {
                private FirebaseFirestore db = FirebaseFirestore.getInstance();
                FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mFirebaseAuth.getCurrentUser();

                @Override
                public void onClick(View view) {
                    /*enlazar con la base de datos y borrar el pokemon*/

                    String usuario = user.getUid();

                    db.collection("users").
                            document(usuario).
                            collection("pokemonCapturados").
                            document(pokemon.getUid()).
                            delete()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast toast = Toast.makeText(context
                                            , "Pokemon borrado", Toast.LENGTH_SHORT);
                                    toast.show();
                                    listaPokemon.remove(pokemon);
                                    notifyDataSetChanged();
                                }

                            })
                            .addOnFailureListener(new OnFailureListener() {

                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast toast = Toast.makeText(context
                                            , "Error al borrar", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                            })
                    ;


                }
            });
        }


    }
}
