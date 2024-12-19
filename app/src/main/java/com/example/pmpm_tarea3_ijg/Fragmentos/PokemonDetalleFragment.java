package com.example.pmpm_tarea3_ijg.Fragmentos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pmpm_tarea3_ijg.MainActivity;
import com.example.pmpm_tarea3_ijg.R;
import com.example.pmpm_tarea3_ijg.databinding.FragmentPokemonDetalleBinding;
import com.squareup.picasso.Picasso;

public class PokemonDetalleFragment extends Fragment {

    FragmentPokemonDetalleBinding binding;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PokemonDetalleFragment() {
        // Required empty public constructor
    }

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

        ((MainActivity) getActivity()).esconderBarra();

        super.onCreate(savedInstanceState);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPokemonDetalleBinding.inflate(inflater, container, false);
        Bundle b = getArguments();

        if (b != null) {

            binding.detalleTextview.setText(b.getString("nombre").toUpperCase());
            binding.detalleType.setText(b.getString("type").toUpperCase());
            Picasso.get().load(b.getString("url")).into(binding.detalleImageview);
            binding.detallePeso.setText(b.getInt("peso") + "");
            binding.detalleAltura.setText(b.getInt("altura") + "");
        }


        binding.toolbar.setNavigationIcon(R.drawable.ic_back);
        binding.toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
                ((MainActivity) getActivity()).mostrarBarra();
            }
        });
        return binding.getRoot();
    }
}