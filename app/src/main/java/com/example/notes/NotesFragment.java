package com.example.notes;

import static com.example.notes.DescriptionsFragment.KEY_NOTES;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;

public class NotesFragment extends Fragment {

    public static final String PLANS_FOR_THE_WEEK = "plans";
    private Notes notesCurrent;

    public static NotesFragment newInstance(Notes notes) {
        NotesFragment fragment = new NotesFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(PLANS_FOR_THE_WEEK, notes);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            notesCurrent = getArguments().getParcelable(PLANS_FOR_THE_WEEK);
        }
        initList(view);
    }

    private void initList(View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        String[] notes = getResources().getStringArray(R.array.note_discription);
        for ( int i = 0; i < notes.length; i++ ){
            String note = notes[i];
            TextView tv = new TextView(getContext());
            tv.setText(note);
            tv.setTextSize(30f);
            linearLayout.addView(tv);
            final int fi = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notesCurrent = new Notes(fi);
                    shouldShowRequestPermissionRationale(notesCurrent);
                }

                private void shouldShowRequestPermissionRationale(Notes currentNotes) {
                }
            });
        }
    }
}