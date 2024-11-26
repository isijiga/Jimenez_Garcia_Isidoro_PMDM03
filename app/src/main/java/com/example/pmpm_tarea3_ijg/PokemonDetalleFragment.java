package com.example.pmpm_tarea3_ijg;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pmpm_tarea3_ijg.databinding.FragmentPokemonDetalleBinding;
import com.squareup.picasso.Picasso;

import java.util.function.ObjIntConsumer;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PokemonDetalleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PokemonDetalleFragment extends Fragment {
    String nombre;
    FragmentPokemonDetalleBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PokemonDetalleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PokemonDetalleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PokemonDetalleFragment newInstance(String param1, String param2) {
        PokemonDetalleFragment fragment = new PokemonDetalleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentPokemonDetalleBinding.inflate(inflater,container,false);
        Bundle b = getArguments();

        if (b != null){
            String nombre = b.getString("nombre");
            binding.detalleTextview.setText(nombre);


        }


        return binding.getRoot();
    }
}