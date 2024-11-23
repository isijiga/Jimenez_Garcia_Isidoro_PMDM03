package com.example.pmpm_tarea3_ijg;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FirebaseUIActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    public static final int RC_SIGN_IN = 1;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseFirestore db;

    List<AuthUI.IdpConfig> proveedores = Arrays.asList(

            new AuthUI.IdpConfig.EmailBuilder().build(),
            new AuthUI.IdpConfig.GoogleBuilder().build()
    );

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        db = FirebaseFirestore.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_launcher);
        mFirebaseAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    Intent i = new Intent(FirebaseUIActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();

                } else {
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setAvailableProviders(proveedores)
                                    .build(),
                            RC_SIGN_IN);

                }


            }


        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        // we are calling our auth
        // listener method on app resume.
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // here we are calling remove auth
        // listener method on stop.
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }

}


