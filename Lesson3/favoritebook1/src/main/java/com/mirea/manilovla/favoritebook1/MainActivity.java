package com.mirea.manilovla.favoritebook1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String USER_BOOK_KEY = "USER_BOOK";
    public static final String USER_QUOTE_KEY = "USER_QUOTE";

    private TextView tvResult;
    private ActivityResultLauncher<Intent> shareLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);
        Button btnOpen = findViewById(R.id.btnOpen);

        shareLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            Intent data = result.getData();
                            String book = data.getStringExtra(USER_BOOK_KEY);
                            String quote = data.getStringExtra(USER_QUOTE_KEY);
                            tvResult.setText("Название Вашей любимой книги: " + book + ". Цитата: " + quote);
                        }
                    }
                }
        );

        btnOpen.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ShareActivity.class);
            shareLauncher.launch(intent);
        });
    }
}