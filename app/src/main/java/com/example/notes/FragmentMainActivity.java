package com.example.notes;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;


public class FragmentMainActivity extends AppCompatActivity {

    private static final String CHANEL_ID = "1";

    public void onDialogResult(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity_main);

        if (savedInstanceState == null) {
            NotesFragment notesFragment = NotesFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.notes_list, notesFragment)
                    .commit();
            /*LessonNinthFragments lessonNinthFragments = LessonNinthFragments.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.notes_list, lessonNinthFragments)
                    .commit();*/
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Notes defaultNotes = new Notes(0);
                DescriptionsFragment descriptionsFragment = DescriptionsFragment.newInstance(defaultNotes);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.notes_discription_land, descriptionsFragment)
                        .commit();
            }
        }
        //Установили свой Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
                showAlertDialog();
                return true;
            }
            case (R.id.action_toast):{
                Toast.makeText(this,"Toast", Toast.LENGTH_LONG).show();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);

    }

    private void showPushNotification() {
        //СОздаем Пуш уведомления в приложении
        NotificationManager notificationManager =(NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(CHANEL_ID, "CHANEL1", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("Это канал для чего нибудь");
            notificationManager.createNotificationChannel(notificationChannel);
        }
        //
        Notification notification = new NotificationCompat.Builder(this,CHANEL_ID)
                .setContentTitle("Уведомление")
                .setContentText("Спасибо что посетили нас")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(Notification.PRIORITY_HIGH)
                .build();

        notificationManager.notify(1, notification);
    }

    void showAlertDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Выйти из приложения?")
                .setMessage("Вы действительно хотите выйти из приложения?")
                .setPositiveButton("Да", (dialog, which) -> {
                    showToast("Да");
                    showPushNotification();
                    finish();
                })
                .setNegativeButton("Нет", (dialog, which) -> {
                    showToast("Нет");
                })
                .setNeutralButton("Не знаю", (dialog, which) -> {
                    showToast("Не знаю");
                }).show();
    }

    void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
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
