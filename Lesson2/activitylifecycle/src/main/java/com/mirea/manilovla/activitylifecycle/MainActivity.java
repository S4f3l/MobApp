package com.mirea.manilovla.activitylifecycle;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private EditText editText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

        if (savedInstanceState == null) {
            Log.i(TAG, "onCreate() - приложение запущено впервые");
            Log.i(TAG, "savedInstanceState = null");
        } else {
            Log.i(TAG, "onCreate() - приложение восстановлено из памяти");
            Log.i(TAG, "savedInstanceState != null");

            String savedText = savedInstanceState.getString("saved_text", "");
            if (!savedText.isEmpty()) {
                editText.setText(savedText);
                Log.i(TAG, "Восстановлен текст: " + savedText);
            }
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart() - подготовка к выводу на экран");
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState() - восстановление состояния");

        if (savedInstanceState != null) {
            String savedText = savedInstanceState.getString("saved_text", "");
            if (!savedText.isEmpty()) {
                editText.setText(savedText);
                Log.i(TAG, "Текст восстановлен в onRestoreInstanceState: " + savedText);
            }
        }
    }@Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart() - перезапуск активности");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume() - активность готова к взаимодействию");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause() - активность теряет фокус");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState() - сохранение состояния");

        String currentText = editText.getText().toString();
        outState.putString("saved_text", currentText);
        Log.i(TAG, "Сохранен текст: " + currentText);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop() - активность остановлена");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy() - активность уничтожена");
    }
}