package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

public class FragmentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_notes);

    if (savedInstanceState == null){
        NotesFragment notesFragment = new NotesFragment(); // FIXME фабричный метод
        getSupportFragmentManager().beginTransaction().replace(R.id.myNotes,  notesFragment).commit();
        if (getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
            NotesInfoFragment notesInfoFragment = new NotesInfoFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.myNotesLandscapeSecond, notesInfoFragment).commit();
        }
    }

    }

}