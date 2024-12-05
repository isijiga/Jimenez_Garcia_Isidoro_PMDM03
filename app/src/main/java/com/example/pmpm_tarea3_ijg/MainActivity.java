package com.example.pmpm_tarea3_ijg;

import static android.content.ContentValues.TAG;

import android.app.usage.NetworkStats;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.pmpm_tarea3_ijg.databinding.ActivityMainBinding;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    NavController navController = null;
    FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser user;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = mFirebaseAuth.getCurrentUser();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Fragment navHostFragment = getSupportFragmentManager().
                findFragmentById(R.id.my_nav_host_fragment);

        if (navHostFragment != null) {
            navController = NavHostFragment.findNavController(navHostFragment);
            NavigationUI.setupWithNavController(binding.bottonnavigationView, navController);


        }

        binding.bottonnavigationView.setOnItemSelectedListener(this::onNavItemSelected);

        comprobarBBDD(user.getUid());
        bienvenida(user.getUid());

    }

    private void comprobarBBDD(String uid) {
        Map<String, String> userData = new HashMap<>();
        userData.put("nombre", user.getDisplayName());
        userData.put("Mail", user.getEmail());

        db.collection("users").document(uid).set(userData);




            }

    private void bienvenida(String uid) {
        Toast toast = Toast.makeText(this, "Bienvenido "+user.getDisplayName(), Toast.LENGTH_SHORT);
        toast.show();
    }

    private boolean onNavItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.capturados_home) {
            navController.navigate(R.id.pokemonCapturados_home);
            return true;
        }
        if (menuItem.getItemId() == R.id.pokedex_home) {
            navController.navigate(R.id.pokedexFragment);
            return true;
        }
        if (menuItem.getItemId() == R.id.ajustes_menu) {
            navController.navigate(R.id.ajustesFragment);
            return true;
        }
        return true;
    }



}