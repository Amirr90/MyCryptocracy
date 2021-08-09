package com.e.cryptocracy.main;


import com.e.cryptocracy.ChangeCurrencyFragment;
import com.e.cryptocracy.CoinDetailFragment;
import com.e.cryptocracy.CoinNewsFragment;
import com.e.cryptocracy.CoinTradingPlateformFragment;
import com.e.cryptocracy.CoinTwitterFragment;
import com.e.cryptocracy.FilterListFragment;
import com.e.cryptocracy.SearchCoinsFragment;
import com.e.cryptocracy.coinInvestorFragment;
import com.e.cryptocracy.views.fragments.homeScreenFragments.CoinListFragment;
import com.e.cryptocracy.views.fragments.homeScreenFragments.FavouriteFragment;
import com.e.cryptocracy.views.fragments.homeScreenFragments.TrendingCoinFragment;

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

    @ContributesAndroidInjector
    abstract TrendingCoinFragment trendingCoinFragment();

    @ContributesAndroidInjector
    abstract CoinDetailFragment coinDetailFragment();

    @ContributesAndroidInjector
    abstract CoinNewsFragment coinNewsFragment();

    @ContributesAndroidInjector
    abstract CoinTradingPlateformFragment coinTradingPlateformFragment();

    @ContributesAndroidInjector
    abstract CoinTwitterFragment coinTwitterFragment();


    @ContributesAndroidInjector
    abstract FavouriteFragment favouriteFragment();

    @ContributesAndroidInjector
    abstract SearchCoinsFragment searchCoinsFragment();


    @ContributesAndroidInjector
    abstract coinInvestorFragment coinInvestorFragment();
}
