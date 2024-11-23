package com.example.pmpm_tarea3_ijg;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pmpm_tarea3_ijg.databinding.ActivityLoginBinding;
import com.firebase.ui.auth.AuthUI;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button2.setOnClickListener(view -> {







        });

        }
    }




