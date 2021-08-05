package com.e.cryptocracy.main;


import com.e.cryptocracy.views.fragments.homeScreenFragments.CoinListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuilderModule {
    @ContributesAndroidInjector
    abstract CoinListFragment coinListFragment();
}
