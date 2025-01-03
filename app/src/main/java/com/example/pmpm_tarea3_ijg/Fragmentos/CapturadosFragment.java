package com.example.pmpm_tarea3_ijg.Fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pmpm_tarea3_ijg.MapeoClases.Sprite;
import com.example.pmpm_tarea3_ijg.databinding.FragmentPokemonCapturadosBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.pmpm_tarea3_ijg.Data.AdaptadorPokeCap;
import com.example.pmpm_tarea3_ijg.MapeoClases.PokemonCapturado;
import com.example.pmpm_tarea3_ijg.MapeoClases.Slot;
import com.example.pmpm_tarea3_ijg.MapeoClases.TypePokemon;

public class CapturadosFragment extends Fragment {
    private FragmentPokemonCapturadosBinding binding;
    FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mFirebaseAuth.getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<PokemonCapturado> datosRecibidos = new ArrayList<>();
    ArrayList<PokemonCapturado> pokemonCartera = new ArrayList<>();
    private AdaptadorPokeCap adaptador;
    public CapturadosFragment() {
    }

    public static CapturadosFragment newInstance(String param1, String param2) {
        CapturadosFragment fragment = new CapturadosFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DividerItemDecoration mDividerItemDecoration =
                new DividerItemDecoration(requireContext(),
                        1);
        binding = FragmentPokemonCapturadosBinding.inflate(getLayoutInflater());
        getdata();
        binding.recyclerViewCapturados.addItemDecoration(mDividerItemDecoration);
        binding.recyclerViewCapturados.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewCapturados.setAdapter(adaptador);
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

    public void getdata() {
        db.collection("users").document(user.getUid()).collection("pokemonCapturados").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                Map<String, Object> pokemonMapa = d.getData();
                                Map<String, Object> spriteMapa = (Map<String, Object>) pokemonMapa.get("sprites");
                                Map<String, Object> tipoMapa = (Map<String, Object>) pokemonMapa.get("types");
                                Map<String, Object> tipoDetalleMapa = (Map<String, Object>) tipoMapa.get("type");
                                Slot slot = new Slot(Integer.parseInt(tipoMapa.get("slot").toString()), new TypePokemon(tipoDetalleMapa.get("name").
                                        toString()));
                                PokemonCapturado pokemon =
                                        new PokemonCapturado(
                                                Integer.parseInt(pokemonMapa.get("height").toString())
                                                , pokemonMapa.get("name").toString().toUpperCase()
                                                , Integer.parseInt(pokemonMapa.get("order").toString())
                                                , new Sprite(spriteMapa.get("front_default").toString())
                                                , Integer.parseInt(pokemonMapa.get("weight").toString())
                                                , slot
                                                , d.getId());
                                /*        new PokemonCapturado(
                                                Integer.parseInt(d.get("height").toString()),
                                                d.get("name").toString(),
                                                Integer.parseInt(d.get("order").toString()),
                                                null,
                                                Integer.parseInt(d.get("weight").toString()));
                                */
                                pokemonCartera.add(pokemon);
                            }
                            adaptador.notifyDataSetChanged();
                        }
                    }
                });
        adaptador = new AdaptadorPokeCap(pokemonCartera, this.getContext());
    }
}