package com.example.notes;

import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.zip.Inflater;

public class DescriptionsFragment extends Fragment {

    public static final String KEY_NOTES = "notes";
    private Notes notes;


    public static DescriptionsFragment newInstance(Notes notes) {
        DescriptionsFragment fragment = new DescriptionsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_NOTES, notes);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_descriptions,menu);//Через инфлэйтер надули меню, но попросили ниже убрать не нужный нам пункт
        menu.findItem(R.id.action_about_fragment).setVisible(false);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            // Ищем по Id свой пункт в меню, и работаем с ним
            case (R.id.action_toast):{
                Toast.makeText(requireContext(),"Toast", Toast.LENGTH_LONG).show();
                return true;
            }
//
        }
        return super.onOptionsItemSelected(item);
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
        setHasOptionsMenu(true); // !!!!!У фрагмента есть свое меню (комментарий урока)
        if (getArguments() != null) {
            notes = getArguments().getParcelable(KEY_NOTES);
        }
        //Вызываем ФрагментМенеджер ребенка, и открываем его фрагмент.
        getChildFragmentManager().beginTransaction().replace(R.id.container_child,DescriptionsChildFragment.newInstance(notes)).addToBackStack("").commit();


    }



}