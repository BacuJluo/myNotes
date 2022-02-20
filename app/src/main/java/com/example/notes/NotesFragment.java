package com.example.notes;


import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NotesFragment extends Fragment {

    public static final String PLANS_FOR_THE_WEEK = "plans";
    private Notes notesCurrent;

    public static NotesFragment newInstance() {
        NotesFragment fragment = new NotesFragment();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(PLANS_FOR_THE_WEEK, notesCurrent);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            notesCurrent = getArguments().getParcelable(PLANS_FOR_THE_WEEK);
        }else{
             notesCurrent = new Notes(0);
        }if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            showLand();
        }
        initList(view);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_descriptions,menu);
        menu.findItem(R.id.action_toast);
    }

    private void initList(View view) {
        String[] notes = getResources().getStringArray(R.array.notes);
        for ( int i = 0; i < notes.length; i++ ){
            String note = notes[i];
            TextView tv = new TextView(getContext());
            tv.setText(note);
            tv.setTextSize(30f);
            ((LinearLayout) view).addView(tv);
            final int fi = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     notesCurrent = new Notes(fi);
                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        showLand();
                    }else{
                        showPort();
                    }

                }

            });
        }
    }

    private void showLand() {
        DescriptionsFragment descriptionsFragment = DescriptionsFragment.newInstance(notesCurrent);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.notes_discription_land, descriptionsFragment).commit();
    }

    private void showPort() {
        DescriptionsFragment descriptionsFragment = DescriptionsFragment.newInstance(notesCurrent);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.notes_list, descriptionsFragment).addToBackStack("").commit();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}