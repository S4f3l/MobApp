package com.mirea.manilovla.dialog;

import android.os.Bundle;import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void onClickShowDialog(View view) {
        MyDialogFragment dialogFragment = new MyDialogFragment();
    }
    public void onOkClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"Иду дальше\"!",
                Toast.LENGTH_LONG).show();
    }
    public void onCancelClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"Нет\"!",
                Toast.LENGTH_LONG).show();
    }
    public void onNeutralClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"На паузе\"!",
                Toast.LENGTH_LONG).show();
    }
    public void onClickTimePicker(View view) {
        MyTimeDialogFragment timeDialog = new MyTimeDialogFragment();
        timeDialog.show(getSupportFragmentManager(), "timePicker");
    }

    // Обработчик для кнопки выбора даты
    public void onClickDatePicker(View view) {
        MyDateDialogFragment dateDialog = new MyDateDialogFragment();
        dateDialog.show(getSupportFragmentManager(), "datePicker");
    }

    // Обработчик для кнопки прогресса
    public void onClickProgressDialog(View view) {
        MyProgressDialogFragment progressDialog = new MyProgressDialogFragment();
        progressDialog.show(getSupportFragmentManager(), "progressDialog");
    }

    // Метод, вызываемый из TimePickerFragment при выборе времени
    public void onTimeSelected(int hourOfDay, int minute) {
        String time = String.format("%02d:%02d", hourOfDay, minute);
        textViewResult.setText("Выбрано время: " + time);

        // Показываем Snackbar с результатом
        Snackbar.make(findViewById(android.R.id.content),
                "Выбрано время: " + time,
                Snackbar.LENGTH_SHORT).show();
    }

    // Метод, вызываемый из DatePickerFragment при выборе даты
    public void onDateSelected(int year, int month, int day) {
        // month начинается с 0 (январь), поэтому добавляем 1
        String date = String.format("%02d.%02d.%d", day, month + 1, year);
        textViewResult.setText("Выбрана дата: " + date);

        // Показываем Snackbar с результатом
        Snackbar.make(findViewById(android.R.id.content),
                "Выбрана дата: " + date,
                Snackbar.LENGTH_SHORT).show();
    }
}