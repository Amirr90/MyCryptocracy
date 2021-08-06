package com.e.cryptocracy.main;


import com.e.cryptocracy.ChangeCurrencyFragment;
import com.e.cryptocracy.FilterListFragment;
import com.e.cryptocracy.views.fragments.homeScreenFragments.CoinListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuilderModule {
    @ContributesAndroidInjector
    abstract CoinListFragment coinListFragment();

    @ContributesAndroidInjector
    abstract FilterListFragment filterListFragment();

    @ContributesAndroidInjector
    abstract ChangeCurrencyFragment changeCurrencyFragment();
}
