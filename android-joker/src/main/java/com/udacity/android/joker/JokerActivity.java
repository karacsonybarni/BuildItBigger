package com.udacity.android.joker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class JokerActivity extends AppCompatActivity {

    public static final String JOKE_EXTRA = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joker);
        showJoke();
    }

    private void showJoke() {
        TextView jokeView = findViewById(R.id.joke);
        jokeView.setText(getIntent().getStringExtra(JOKE_EXTRA));
    }
}
