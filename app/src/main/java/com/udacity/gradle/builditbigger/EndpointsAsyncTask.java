package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.udacity.android.joker.JokerActivity;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;
import java.lang.ref.WeakReference;

class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {

    private static MyApi myApiService;
    private WeakReference<Context> context;

    EndpointsAsyncTask(Context context) {
        this.context = new WeakReference<>(context);
    }

    @Override
    protected final String doInBackground(Void... params) {
        if (myApiService == null) {
            myApiService = buildApi();
        }

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    private MyApi buildApi() {
        MyApi.Builder builder = new MyApi.Builder(new NetHttpTransport(),
                new AndroidJsonFactory(), null)
                .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(
                            AbstractGoogleClientRequest<?> abstractGoogleClientRequest) {
                        abstractGoogleClientRequest.setDisableGZipContent(true);
                    }
                });
        return builder.build();
    }

    @Override
    protected void onPostExecute(String result) {
        Context context = this.context.get();
        Intent intent = new Intent(context, JokerActivity.class);
        intent.putExtra(JokerActivity.JOKE_EXTRA, result);
        context.startActivity(intent);
    }
}
