package com.example.pmpm_tarea3_ijg;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pmpm_tarea3_ijg.databinding.FragmentPokemonCapturadosBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CapturadosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CapturadosFragment extends Fragment {

    private FragmentPokemonCapturadosBinding binding;
    FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mFirebaseAuth.getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    ArrayList<PokemonCapturado> datosRecibidos = new ArrayList<>();
    ArrayList<PokemonCapturado> pokemonCartera = new ArrayList<>();
    private AdaptadorPokeCap adaptador;

    public CapturadosFragment() {
        // Required empty public constructor
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

        binding = FragmentPokemonCapturadosBinding.inflate(getLayoutInflater());
        getdata();
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

                                Slot slot = new Slot(Integer.parseInt(tipoMapa.get("slot").toString()),new TypePokemon(tipoDetalleMapa.get("name").
                                        toString()));
                                PokemonCapturado pokemon =
                                new PokemonCapturado(
                                        Integer.parseInt(pokemonMapa.get("height").toString())
                                        ,pokemonMapa.get("name").toString()
                                        ,Integer.parseInt(pokemonMapa.get("order").toString())
                                        ,new Sprite(spriteMapa.get("front_default").toString())
                                        ,Integer.parseInt(pokemonMapa.get("weight").toString())
                                        ,slot);

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