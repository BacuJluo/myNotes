package com.example.notes;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class FragmentMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity_main);

        if (savedInstanceState == null) {
            NotesFragment notesFragment = NotesFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.notes_list, notesFragment)
                    .commit();
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Notes defaultNotes = new Notes(0);
                DescriptionsFragment descriptionsFragment = DescriptionsFragment.newInstance(defaultNotes);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.notes_discription_land, descriptionsFragment)
                        .commit();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case (R.id.action_about_fragment):{
                getSupportFragmentManager().beginTransaction().replace(R.id.notes_list, new AboutFragment()).addToBackStack("").commit();

                return true;
            }
            case (R.id.action_exit):{
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Fragment backStackFragment = getSupportFragmentManager()
                .findFragmentById(R.id.notes_list);
        if (backStackFragment != null && backStackFragment instanceof DescriptionsFragment) {
            getSupportFragmentManager().popBackStack();
        }
    }

}
