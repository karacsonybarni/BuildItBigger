package com.udacity.gradle.builditbigger.endpointstask;

import android.os.AsyncTask;

import androidx.annotation.VisibleForTesting;

import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.udacity.gradle.builditbigger.backend.myApi.model.Joke;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {

    private static MyApi myApiService;
    private EndpointsResultListener listener;
    private boolean isCompleted;
    private boolean isResultValid;

    public EndpointsAsyncTask(EndpointsResultListener listener) {
        this.listener = listener;
    }

    @Override
    protected final String doInBackground(Void... params) {
        if (myApiService == null) {
            myApiService = buildApi();
        }

        try {
            Joke joke = myApiService.getJoke().execute();
            isResultValid = joke.getValid();
            return joke.getJoke();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    private MyApi buildApi() {
        MyApi.Builder builder = new MyApi.Builder(new NetHttpTransport(),
                new AndroidJsonFactory(), null)
                .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                .setGoogleClientRequestInitializer(
                        abstractGoogleClientRequest ->
                                abstractGoogleClientRequest.setDisableGZipContent(true));
        return builder.build();
    }

    @Override
    protected void onPostExecute(String result) {
        listener.onResult(result);
        isCompleted = true;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    @VisibleForTesting
    protected boolean isResultValid() {
        return isResultValid;
    }
}
