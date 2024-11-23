package com.example.pmpm_tarea3_ijg;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pmpm_tarea3_ijg.databinding.FragmentPokemonCapturadosBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CapturadosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CapturadosFragment extends Fragment {



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
        if (getArguments() != null) {


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_capturados, container, false);
    }
}