package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Objects;


public class FlavorSpecificFragment extends MainActivityFragment {

    private View rootView;
    private InterstitialAd interstitialAd;

    public FlavorSpecificFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        initAds();
        return rootView;
    }

    private void initAds() {
        AdRequest adRequest =
                new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        initAdView(adRequest);
        initInterstitialAd(adRequest);
    }

    private void initAdView(AdRequest adRequest) {
        AdView adView = rootView.findViewById(R.id.adView);
        adView.loadAd(adRequest);
    }

    private void initInterstitialAd(AdRequest adRequest) {
        interstitialAd = new InterstitialAd(Objects.requireNonNull(getContext()));
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        interstitialAd.loadAd(adRequest);
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                showJoke();
            }
        });
    }

    @Override
    protected void onJokeButtonClicked() {
        super.onJokeButtonClicked();
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            showJoke();
        }
    }
}