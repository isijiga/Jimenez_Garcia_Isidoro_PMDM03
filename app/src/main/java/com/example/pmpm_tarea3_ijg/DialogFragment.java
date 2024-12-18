package com.example.pmpm_tarea3_ijg;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DialogFragment extends androidx.fragment.app.DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Autor: Isidoro Jiménez Garcia\n"+"Versión 1.0.0\n");
        builder.setIcon(R.drawable.logo);

        return builder.create();
    }
}
