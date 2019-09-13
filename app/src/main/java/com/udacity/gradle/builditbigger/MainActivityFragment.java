package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.flavor_specific_fragment, container, false);
        initJokeButton(root);
        return root;
    }

    private void initJokeButton(View root) {
        Button jokeButton = root.findViewById(R.id.jokeButton);
        EndpointsAsyncTask jokerTask = new EndpointsAsyncTask(getContext());
        jokeButton.setOnClickListener(v -> jokerTask.execute());
    }
}
