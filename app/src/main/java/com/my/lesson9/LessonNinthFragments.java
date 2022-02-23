package com.my.lesson9;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import com.example.notes.R;
import com.google.android.material.snackbar.Snackbar;



public class LessonNinthFragments extends Fragment {

    public static LessonNinthFragments newInstance() {
        LessonNinthFragments fragment = new LessonNinthFragments();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ninth_lesson, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }



    void initView(View view){
//        view.findViewById(R.id.btnToast).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(requireContext(), "Toast работает", Toast.LENGTH_LONG).show();
//
//            }
//        });

        view.findViewById(R.id.btnToast).setOnClickListener(v -> showToast());
        view.findViewById(R.id.btnSnackBar).setOnClickListener(v -> showSnackBar(view));
        view.findViewById(R.id.btnSnackBarWithAction).setOnClickListener(v -> showSnackBarWithAction(view));
        view.findViewById(R.id.btnAlertDialog).setOnClickListener(v -> showAlertDialog());
        view.findViewById(R.id.btnCustomView).setOnClickListener(v -> showAlertDialogCustom());
        view.findViewById(R.id.btnAlertDialogFragment).setOnClickListener(v -> showDialogFragment());
        view.findViewById(R.id.btnAlertDialogFragmentCustom).setOnClickListener(v -> showDialogFragmentCustom());
        view.findViewById(R.id.btnBottomSheetDialogFragment).setOnClickListener(v -> showBottomSheetDialogFragment());
        view.findViewById(R.id.btnPushNotification).setOnClickListener(v -> showPushNotification());

    }

    public final String CHANEL_ID = "1";

    private void showPushNotification() {
        //СОздаем Пуш уведомления в приложении
        NotificationManager notificationManager =(NotificationManager) requireActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(CHANEL_ID, "CHANEL1", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("Это канал для чего нибудь");
            notificationManager.createNotificationChannel(notificationChannel);
        }
        //
        Notification notification = new NotificationCompat.Builder(requireContext(),CHANEL_ID)
                .setContentTitle("Заголовок")
                .setContentText("Текст пуша")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(Notification.PRIORITY_HIGH)
                .build();

        notificationManager.notify(1, notification);
    }

    private void showBottomSheetDialogFragment() {
        new MyBottomSheetDialogFragment().show(getActivity().getSupportFragmentManager(), "qwerty");
    }

    private void showDialogFragmentCustom() {
        new MyDialogFragmentCustom().show(getActivity().getSupportFragmentManager(), "qwerty");
    }

    void showDialogFragment(){
        new MyDialogFragment().show(getActivity().getSupportFragmentManager(), "qwerty");
     }

    private void showAlertDialogCustom() {
        View view = getLayoutInflater().inflate(R.layout.dialog_custom, null);

        AlertDialog alertDialog = new AlertDialog.Builder(requireContext())
                .setTitle("alert dialog")
                .setMessage("alert dialog message")
                .setView(view)
                .show();
        view.findViewById(R.id.button_custom_view).setOnClickListener(v -> {
            EditText editText = view.findViewById(R.id.editText_custom_view);
            showToast(editText.getText().toString());
            alertDialog.dismiss();
        });
    }

    void showAlertDialog() {
        new AlertDialog.Builder(requireContext())
        .setTitle("alert dialog")
        .setMessage("alert dialog message")
        .setPositiveButton("Да", (dialog, which) -> {
            showToast("Да");
        })
        .setNegativeButton("Нет", (dialog, which) -> {
            showToast("Нет");
        })
        .setNeutralButton("Не знаю", (dialog, which) -> {
            showToast("Не знаю");
        }).show();
    }

    void showToast(){
        Toast.makeText(requireContext(), "Toast работает", Toast.LENGTH_LONG).show();
    }
    void showToast(String message){
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show();
    }
    void showSnackBar(View view){
        Snackbar.make(view, "SnackBar работает", Snackbar.LENGTH_LONG).show();
    }
    void showSnackBarWithAction(View view){
        Snackbar.make(view, "SnackBarWithAction работает", Snackbar.LENGTH_LONG).setAction("Попробовать еще раз", v -> {
            showToast();
        }).show();
    }
}
