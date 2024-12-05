package com.example.pmpm_tarea3_ijg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.pmpm_tarea3_ijg.databinding.FragmentAjustesBinding;
import com.firebase.ui.auth.AuthUI;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AjustesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AjustesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREFS_NAME = "prefSwitch";
    private static final String KEY_SWITCH_STATE = "switchState";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentAjustesBinding binding;
    private Context contexto;
    public AjustesFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static AjustesFragment newInstance(String param1, String param2) {
        AjustesFragment fragment = new AjustesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.contexto = context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAjustesBinding.inflate(inflater, container, false);

        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthUI.getInstance()
                        .signOut(requireContext())
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                // Cierre de sesión exitoso
                                // Redirige a la actividad de inicio de sesión o a donde quieras
                                Intent intent = new Intent(requireContext(), FirebaseUIActivity.class);
                                startActivity(intent);
                                requireActivity().finish(); // Cierra la actividad actual
                            } else {
                                // Manejar errores
                                Toast.makeText(requireContext(), "Error al cerrar sesión", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


        sharedPreferences = contexto.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        binding.switch1.setChecked(loadSwitchState());
        binding.switch1.setOnCheckedChangeListener((compoundButton, b) ->
                onSwitchChanged(b)
        );


        return binding.getRoot();

    }



    public void onSwitchChanged(boolean isCkecked){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_SWITCH_STATE, isCkecked);
        editor.apply();
    }
    private boolean loadSwitchState() {
        return sharedPreferences.getBoolean(KEY_SWITCH_STATE, false);
        // false es el valor por defecto
    }

}

