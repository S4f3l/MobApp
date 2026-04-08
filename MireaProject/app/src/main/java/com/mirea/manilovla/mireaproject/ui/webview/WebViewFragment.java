package com.mirea.manilovla.mireaproject.ui.webview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import com.mirea.manilovla.mireaproject.R;

public class WebViewFragment extends Fragment {
    private WebView webView;
    private EditText etUrl;
    private Button btnGo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web_view, container, false);

        webView = view.findViewById(R.id.webView);
        etUrl = view.findViewById(R.id.etUrl);
        btnGo = view.findViewById(R.id.btnGo);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.mirea.ru");

        btnGo.setOnClickListener(v -> {
            String url = etUrl.getText().toString();
            if (!url.startsWith("http")) {
                url = "https://" + url;
            }
            webView.loadUrl(url);
        });

        return view;
    }
}