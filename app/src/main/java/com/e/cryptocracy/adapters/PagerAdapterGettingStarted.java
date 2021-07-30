package com.e.cryptocracy.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.e.cryptocracy.views.fragments.gettingStartedFragments.GettingStartedFragment;

public class PagerAdapterGettingStarted extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 3;


    public PagerAdapterGettingStarted(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        return GettingStartedFragment.newInstance(position);
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }

}

