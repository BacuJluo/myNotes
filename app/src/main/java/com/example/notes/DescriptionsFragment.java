package com.example.notes;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DescriptionsFragment extends Fragment {

    public static final String KEY_NOTES = "notes";
    private Notes notes;

    public static DescriptionsFragment newInstance(Notes notes) {
        DescriptionsFragment fragment = new DescriptionsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_NOTES, notes);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes_description, container, false);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_NOTES, notes);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null){
            notes = savedInstanceState.getParcelable(KEY_NOTES);
        }else {
            notes = new Notes(0);
        }
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            showLand();
        }
        initView(view);
    }

    private void initView(View view) {
        String[] note = getResources().getStringArray(R.array.notes);
        for (int i = 0; i< note.length; i++){
            String noteName = note[i];
            TextView textView = new TextView(getContext());
            textView.setTextSize(35f);
            textView.setText(noteName);
            ((LinearLayout) view).addView(textView);
            final int finalI = i;
            textView.setOnClickListener(view1 -> {
                notes = new Notes(finalI);
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    showLand();
                } else {
                    showPort();
                }
            });
        }
    }

    private void showLand() {
        NotesFragment notesFragment = NotesFragment.newInstance(notes);
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.notes_discription, notesFragment).addToBackStack("").commit();
    }

    private void showPort() {
        NotesFragment notesFragment = NotesFragment.newInstance(notes);
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.list_notes, notesFragment).addToBackStack("").commit();
    }
}