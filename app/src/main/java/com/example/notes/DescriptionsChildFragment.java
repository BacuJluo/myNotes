package com.example.notes;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

public class DescriptionsChildFragment extends Fragment {

    public static final String KEY_NOTES = "notes";
    private Notes notes;


    public static DescriptionsChildFragment newInstance(Notes notes) {
        DescriptionsChildFragment fragment = new DescriptionsChildFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_NOTES, notes);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes_description_child, container, false);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_NOTES, notes);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ;
        if (getArguments() != null) {
            notes = getArguments().getParcelable(KEY_NOTES);
        }

        TextView tv = view.findViewById(R.id.notes_discription);
        String[] note = getResources().getStringArray(R.array.note_name);
        tv.setText(note[notes.getIndex()]);

        view.findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //???????????????? popBackStack() (?????????????? ????????????????) ???????????? onBackPressed()
                requireActivity().getSupportFragmentManager().popBackStack();
                //getParentFragmentManager().popBackStack();
                //TODO ?????????? ???????????????????????? ?????????? ???? ???????? ?????????????? ????????????, ???? ?????????? ????????????..
            }
        });

        //???????????????? ???????????? PopupMenu
        tv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popupMenu = new PopupMenu(requireContext(),v);
                requireActivity().getMenuInflater().inflate(R.menu.popup,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            // ???????? ???? Id ???????? ?????????? ?? ????????, ?? ???????????????? ?? ??????
                            case (R.id.action_popup_clear):{
                                //TODO ???????????????? ?????????????? ??????????????
                                 showAlertDialog(tv);
                                return true;
                            }
                            case (R.id.action_popup_exit):{
                                requireActivity().finish();
                                return true;
                            }
                        }
                        return false;
                    }
                });
                popupMenu.show();
                return false;
            }
        });
    }

    void showAlertDialog(TextView tv) {
        new AlertDialog.Builder(requireContext())
                .setTitle("????????????????")
                .setMessage("???? ?????????????????????????? ???????????? ???????????????? ???????????????")
                .setPositiveButton("????", (dialog, which) -> {
                    tv.setAlpha(0);
                })
                .setNegativeButton("??????", (dialog, which) -> {
                    showSnackBar(tv);
                }).show();
    }

//    void showToast(String message){
//        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show();
//    }

    void showSnackBar(View view){
        Snackbar.make(view, "?????????? ???????????????? ??????????????", Snackbar.LENGTH_LONG).show();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }



}