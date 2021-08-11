package com.e.cryptocracy.addservices;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.widget.FrameLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class AdMob {
    private static final String TAG = "AdMob";

    Activity activity;
    AdView adView;
    FrameLayout adContainerView;
    AdRequest adRequest;


    public AdMob(Activity activity, FrameLayout adContainerView) {
        this.activity = activity;
        this.adContainerView = adContainerView;
        initAds();
    }


    private void initAds() {
        MobileAds.initialize(activity, initializationStatus -> {
            Log.d(TAG, "onInitializationComplete: ");
        });
        setUpBannerAd(Ads.BANNER_ADD_ID);

    }

    private void setUpBannerAd(String bannerAdId) {
        adView = new AdView(activity);
        adView.setAdUnitId(bannerAdId);
        adContainerView.addView(adView);

        loadBanner();
    }


    private void loadBanner() {
        adRequest = new AdRequest.Builder().build();


        AdSize adSize = getAdSize();
        // Step 4 - Set the adaptive ad size on the ad view.
        adView.setAdSize(adSize);


        // Step 5 - Start loading the ad in the background.
        adView.loadAd(adRequest);


    }

    private AdSize getAdSize() {
        // Step 2 - Determine the screen width (less decorations) to use for the ad width.
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;

        int adWidth = (int) (widthPixels / density);

        // Step 3 - Get adaptive ad size and return for setting on the ad view.
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth);
    }
}
