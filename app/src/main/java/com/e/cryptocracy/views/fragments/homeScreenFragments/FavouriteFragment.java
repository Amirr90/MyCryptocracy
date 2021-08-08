package com.e.cryptocracy.views.fragments.homeScreenFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.e.cryptocracy.databinding.FragmentFavouriteBinding;
import com.e.cryptocracy.viewModal.AppViewModal;
import com.e.cryptocracy.viewModal.ViewModelProviderFactory;
import com.e.pagingSource.PagingAdapter;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class FavouriteFragment extends DaggerFragment {

    private static final String TAG = "FavouriteFragment";

    FragmentFavouriteBinding binding;

    AppViewModal appViewModal;
    @Inject
    ViewModelProviderFactory providerFactory;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavouriteBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PagingAdapter pagingAdapter = new PagingAdapter();
        binding.recCoinHome.setAdapter(pagingAdapter);
        appViewModal = ViewModelProviders.of(this, providerFactory).get(AppViewModal.class);


     /*   appViewModal.getFavCoins().observe(getViewLifecycleOwner(), coinModals -> {
            pagingAdapter.submitList(coinModals);
            Log.d(TAG, "onViewCreated: " + coinModals.toString());

        });*/

        /*appViewModal.getAllCoins("1").observe(getViewLifecycleOwner(), new Observer<List<CoinModal>>() {
            @Override
            public void onChanged(List<CoinModal> coinModals) {
                Log.d(TAG, "onChanged: "+coinModals.size());
            }
        });*/
    }
}