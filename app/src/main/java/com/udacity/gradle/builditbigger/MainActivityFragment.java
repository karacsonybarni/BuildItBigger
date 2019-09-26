package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.udacity.android.joker.JokerActivity;
import com.udacity.gradle.builditbigger.endpointstask.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.endpointstask.EndpointsResultListener;

import java.util.Objects;

public class MainActivityFragment extends Fragment implements EndpointsResultListener {

    private EndpointsAsyncTask jokerTask;
    private String joke;
    private boolean shouldShowJoke;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =
                inflater.inflate(R.layout.flavor_specific_fragment, container, false);
        initJokeButton(root);
        return root;
    }

    private void initJokeButton(View root) {
        Button jokeButton = root.findViewById(R.id.jokeButton);
        jokeButton.setOnClickListener(v -> onJokeButtonClicked());
    }

    protected void onJokeButtonClicked() {
        if (jokerTask == null || jokerTask.isCompleted()) {
            jokerTask = new EndpointsAsyncTask(this);
            jokerTask.execute();
        }
    }

    @Override
    public void onResult(String result) {
        joke = result;
        if (shouldShowJoke) {
            showJoke();
        }
    }

    void showJoke() {
        if (joke == null) {
            shouldShowJoke = true;
        } else {
            startJokerActivity();
            joke = null;
            shouldShowJoke = false;
        }
    }

    private void startJokerActivity() {
        Context context = Objects.requireNonNull(getContext());
        Intent intent = new Intent(context, JokerActivity.class);
        intent.putExtra(JokerActivity.JOKE_EXTRA, joke);
        context.startActivity(intent);
    }
}
