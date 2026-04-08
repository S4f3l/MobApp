package com.mirea.manilovla.favoritebook;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String USER_BOOK_KEY = "USER_BOOK";
    public static final String USER_QUOTE_KEY = "USER_QUOTE";

    private TextView tvResult;
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);
        Button btnOpen = findViewById(R.id.btnOpen);

        btnOpen.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ShareActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String book = data.getStringExtra(USER_BOOK_KEY);
            String quote = data.getStringExtra(USER_QUOTE_KEY);
            tvResult.setText("Название Вашей любимой книги: " + book + ". Цитата: " + quote);
        }
    }
}