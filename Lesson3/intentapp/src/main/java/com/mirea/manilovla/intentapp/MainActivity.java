package com.mirea.manilovla.intentapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvTime = findViewById(R.id.tvTime);
        Button btnSend = findViewById(R.id.btnSend);

        long dateInMillis = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String currentTime = sdf.format(new Date(dateInMillis));

        tvTime.setText("Текущее время: " + currentTime);

        int myNumber = 9;
        int squareValue = myNumber * myNumber;

        btnSend.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("TIME_KEY", currentTime);
                intent.putExtra("SQUARE_KEY", squareValue);
                startActivity(intent);
            }
        });
    }
}