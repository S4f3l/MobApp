package com.mirea.manilovla.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class MyProgressDialogFragment extends DialogFragment {

    private int progressStatus = 0;
    private TextView textViewProgress;
    private ProgressBar progressBar;
    private Handler handler = new Handler();

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Создаём builder
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        // Инфлейтим кастомный layout для диалога
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_progress, null);

        // Инициализируем элементы
        textViewProgress = view.findViewById(R.id.textViewProgress);
        progressBar = view.findViewById(R.id.progressBar);

        builder.setView(view)
                .setTitle("Загрузка")
                .setNegativeButton("Отмена", (dialog, id) -> {
                    // Отменяем загрузку
                    dialog.cancel();
                });

        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Запускаем симуляцию прогресса
        startProgress();
    }

    private void startProgress() {
        new Thread(() -> {
            while (progressStatus < 100) {
                progressStatus += 1;
                // Обновляем UI
                handler.post(() -> {
                    progressBar.setProgress(progressStatus);
                    textViewProgress.setText(progressStatus + "%");
                });
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // По завершении закрываем диалог
            handler.postDelayed(() -> dismiss(), 500);
        }).start();
    }
}
