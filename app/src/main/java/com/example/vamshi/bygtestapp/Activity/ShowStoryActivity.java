package com.example.vamshi.bygtestapp.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.vamshi.bygtestapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ShowStoryActivity extends AppCompatActivity {

    @Bind(R.id.Story_title)
    TextView mStoryTitle;

    @Bind(R.id.webview)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_story);
        ButterKnife.bind(this);

        mStoryTitle.setText(getIntent().getStringExtra("Title"));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(getIntent().getStringExtra("URL"));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
