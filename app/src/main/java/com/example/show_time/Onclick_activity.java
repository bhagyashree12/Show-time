package com.example.show_time;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import static com.example.show_time.MainActivity.EXTRA_MOVIE_GENRE;
import static com.example.show_time.MainActivity.EXTRA_MOVIE_IMAGE;
import static com.example.show_time.MainActivity.EXTRA_MOVIE_INFO;
import static com.example.show_time.MainActivity.EXTRA_MOVIE_NAME;
import static com.example.show_time.MainActivity.EXTRA_MOVIE_RATING;
import static com.example.show_time.MainActivity.EXTRA_MOVIE_TRAILOR;


public class Onclick_activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onclick_layout);

        getSupportActionBar().hide();//to hide the actionbar in the onclick activity

        Intent intent = getIntent();

        String imageUrl = intent.getStringExtra(EXTRA_MOVIE_IMAGE);
        String movie_name = intent.getStringExtra(EXTRA_MOVIE_NAME);
        String movie_info = intent.getStringExtra(EXTRA_MOVIE_INFO);
        String movie_genre = intent.getStringExtra(EXTRA_MOVIE_GENRE);
        String movie_rating = intent.getStringExtra(EXTRA_MOVIE_RATING);
        String movie_trailor = intent.getStringExtra(EXTRA_MOVIE_TRAILOR);




        ImageView imageView = findViewById(R.id.movie_image);
        TextView name = findViewById(R.id.movie_name);
        TextView info = findViewById(R.id.movie_info);
        TextView genre = findViewById(R.id.movie_genre);
        TextView rating = findViewById(R.id.movie_rating);
        WebView trailor = findViewById(R.id.movie_trailor);


        Picasso.get().load(imageUrl).fit().centerInside().into(imageView);
        name.setText(movie_name);
        info.setText(movie_info);
        genre.setText("Genre : " + movie_genre);
        rating.setText("Rating : " + movie_rating);


//        trailor.loadUrl(movie_trailor);
//        trailor.setWebChromeClient(new WebChromeClient());


        trailor.getSettings().setJavaScriptEnabled(true);
        trailor.getSettings().setPluginState(WebSettings.PluginState.ON);
        trailor.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        trailor.getSettings().setSupportMultipleWindows(true);
        trailor.getSettings().setSupportZoom(true);
        trailor.getSettings().setBuiltInZoomControls(true);
        trailor.getSettings().setAllowFileAccess(true);
        trailor.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                setProgressBarIndeterminateVisibility(true);
                super.onPageFinished(view, url);
            }
        });
        trailor.loadUrl(movie_trailor);
        trailor.setWebChromeClient(new WebChromeClient());



    }


}

