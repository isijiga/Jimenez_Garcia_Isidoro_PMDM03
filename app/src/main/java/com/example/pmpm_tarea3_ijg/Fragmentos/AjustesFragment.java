package com.example.pmpm_tarea3_ijg.Fragmentos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.pmpm_tarea3_ijg.DialogFragment;
import com.example.pmpm_tarea3_ijg.FirebaseUIActivity;
import com.example.pmpm_tarea3_ijg.databinding.FragmentAjustesBinding;
import com.firebase.ui.auth.AuthUI;
import java.util.Locale;

public class AjustesFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREFS_NAME = "prefSwitch";
    private static final String KEY_SWITCH_STATE = "switchState";
    private static final String KEY_SWITCHLANG_STATE = "switchLangState";


    private FragmentAjustesBinding binding;
    private Context contexto;

    public AjustesFragment() {

    }


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
        binding.switchLanguage.setChecked(loadSwitchLangState());
        binding.switchLanguage.setOnCheckedChangeListener((compoundButton, b) ->
                onSwitchLangChanged(b)
        );
        binding.switch1.setChecked(loadSwitchState());
        binding.switch1.setOnCheckedChangeListener((compoundButton, b) ->
                onSwitchChanged(b)
        );

        binding.buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogo = new DialogFragment();
                dialogo.show(getActivity().getSupportFragmentManager(), "Acerca de...");
            }
        });

        return binding.getRoot();

    }

    private void cambiarIdioma(String idioma) {
        Locale locale = new Locale(idioma);
        Locale.setDefault(locale);
        Configuration conf = getResources().getConfiguration();
        conf.setLocale(locale);
        getResources().updateConfiguration(conf, getResources().getDisplayMetrics());
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("idioma", idioma);
        editor.apply();
        getActivity().recreate();
        Toast.makeText(contexto, idioma == "es" ? "Idioma cambiado a Español" : "Idioma cambiado a Ingles", Toast.LENGTH_SHORT).show();

    }

    private void onSwitchLangChanged(boolean b) {

        if (b) {
            cambiarIdioma("es");

        } else {
            cambiarIdioma("en");
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_SWITCHLANG_STATE, b);
        editor.apply();
    }

    private boolean loadSwitchLangState() {
        return sharedPreferences.getBoolean(KEY_SWITCHLANG_STATE, false);
    }


    public void onSwitchChanged(boolean isCkecked) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_SWITCH_STATE, isCkecked);
        editor.apply();
    }

    private boolean loadSwitchState() {
        return sharedPreferences.getBoolean(KEY_SWITCH_STATE, false);
        // false es el valor por defecto
    }

}

