package com.my.lesson9;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.notes.FragmentMainActivity;
import com.example.notes.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class MyBottomSheetDialogFragment extends BottomSheetDialogFragment {

    private OnDialogListener dialogListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_custom, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dialogListener = ((FragmentMainActivity) getActivity());
        initView(view);

    }

    void initView(View view){
        view.findViewById(R.id.button_custom_view).setOnClickListener(v -> {
            EditText editText = view.findViewById(R.id.editText_custom_view);
            dialogListener.onDialogResult(editText.getText().toString());
            dismiss();
        });
    }


}
