package com.my.lesson9;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.notes.FragmentMainActivity;
import com.example.notes.R;

public class MyDialogFragmentCustom extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.dialog_custom, null);
        AlertDialog alertDialog = new AlertDialog.Builder(requireContext())
                .setTitle("Введите имя")
                .setView(view)
                .show();
        view.findViewById(R.id.button_custom_view).setOnClickListener(v -> {
            EditText editText = view.findViewById(R.id.editText_custom_view);
            ((FragmentMainActivity) getActivity()).onDialogResult(editText.getText().toString());
            alertDialog.dismiss();
        });
        return alertDialog;
    }

}
