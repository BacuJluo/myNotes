package com.example.notes;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            NotesFragment notesFragment = NotesFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.notes_list, notesFragment).commit();
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                Notes defaultNotes = new Notes(0);
                DescriptionsFragment descriptionsFragment = DescriptionsFragment.newInstance(defaultNotes);
                getSupportFragmentManager().beginTransaction().replace(R.id.notes_discription_land,descriptionsFragment).commit();
            }
            }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Fragment backStackFragment = getSupportFragmentManager()
                .findFragmentById(R.id.notes_list);
        if (backStackFragment != null && backStackFragment instanceof DescriptionsFragment) {
            onBackPressed();
        }
    }

}
