package com.example.pmpm_tarea3_ijg.Fragmentos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.pmpm_tarea3_ijg.DialogFragment;
import com.example.pmpm_tarea3_ijg.FirebaseUIActivity;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnSuccessListener;

public class DialogLogOutFragment  extends androidx.fragment.app.DialogFragment {


    public DialogLogOutFragment() {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Context appContext = requireContext().getApplicationContext();
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setMessage("¿Seguro que quieres cerrar sesión?");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if(isAdded()){
                AuthUI.getInstance()
                        .signOut(appContext)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Intent intent = new Intent(appContext,FirebaseUIActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                appContext.startActivity(intent);
                            }
                        });}


            }
        });


        return builder.create();
    }






}
