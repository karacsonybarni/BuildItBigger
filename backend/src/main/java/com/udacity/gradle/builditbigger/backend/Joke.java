package com.udacity.gradle.builditbigger.backend;

/** The object model for the data we are sending through endpoints */
public class Joke {

    private String joke;
    private boolean isValid;

    public void setJoke(String data) {
        joke = data;
        isValid = true;
    }

    public String getJoke() {
        return joke;
    }

    public boolean isValid() {
        return isValid;
    }
}