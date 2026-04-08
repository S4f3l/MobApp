package com.mirea.manilovla.favoritebook1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class ShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        EditText etBook = findViewById(R.id.etBook);
        EditText etQuote = findViewById(R.id.etQuote);
        Button btnSend = findViewById(R.id.btnSend);

        btnSend.setOnClickListener(v -> {
            String book = etBook.getText().toString().trim();
            String quote = etQuote.getText().toString().trim();

            Intent data = new Intent();
            data.putExtra(MainActivity.USER_BOOK_KEY, book);
            data.putExtra(MainActivity.USER_QUOTE_KEY, quote);
            setResult(RESULT_OK, data);
            finish();
        });
    }
}
