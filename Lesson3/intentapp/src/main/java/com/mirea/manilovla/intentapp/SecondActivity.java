package com.mirea.manilovla.intentapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        TextView tvResult = findViewById(R.id.tvResult);

        String time = getIntent().getStringExtra("TIME_KEY");
        int square = getIntent().getIntExtra("SQUARE_KEY", 0);

        String result = "КВАДРАТ ЗНАЧЕНИЯ МОЕГО НОМЕРА ПО СПИСКУ В ГРУППЕ СОСТАВЛЯЕТ " + square + ", а текущее время " + time;
        tvResult.setText(result);
    }
}