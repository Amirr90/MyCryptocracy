package com.e.cryptocracy.views.fragments.homeScreenFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;

import com.e.cryptocracy.adapters.FavouriteCoinAdapter;
import com.e.cryptocracy.addservices.AdMob;
import com.e.cryptocracy.databinding.FragmentFavouriteBinding;
import com.e.cryptocracy.utility.AppConstant;
import com.e.cryptocracy.utility.AppUtils;
import com.e.cryptocracy.viewModal.AppViewModal;
import com.e.cryptocracy.viewModal.ViewModelProviderFactory;
import com.e.cryptocracy.views.activity.AppHomeScreen;
import com.google.firebase.firestore.CollectionReference;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class FavouriteFragment extends DaggerFragment {

    private static final String TAG = "FavouriteFragment";

    FragmentFavouriteBinding binding;

    AppViewModal appViewModal;
    @Inject
    ViewModelProviderFactory providerFactory;

    FavouriteCoinAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavouriteBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        appViewModal = ViewModelProviders.of(this, providerFactory).get(AppViewModal.class);

        adapter = new FavouriteCoinAdapter(Navigation.findNavController(view));
        binding.recCoinHome.setItemAnimator(new DefaultItemAnimator());
        binding.recCoinHome.setAdapter(adapter);

        new AdMob(requireActivity(), binding.adViewContainer);


        appViewModal.fetchFavCoins().observe(getViewLifecycleOwner(), coinModals -> {
            binding.progressBar10.setVisibility(View.GONE);
            adapter.submitList(coinModals);
        });

        loadFavCoinsData();


        binding.imageView3.setOnClickListener(AppHomeScreen.getInstance());


        binding.swiperefreshFav.setOnRefreshListener(() -> loadFavCoinsData());
    }


    private void loadFavCoinsData() {
        CollectionReference FavRef = AppUtils.getFireStoreReference().collection("users")
                .document(AppUtils.getUid())
                .collection(AppConstant.FAVOURITE);


        FavRef.get().addOnSuccessListener(queryDocumentSnapshots -> {
            if (queryDocumentSnapshots != null && !queryDocumentSnapshots.isEmpty()) {
                StringBuilder builder = new StringBuilder();
                //get All fav Coins Ids using FireStore
                for (int a = 0; a < queryDocumentSnapshots.size(); a++) {
                    builder.append(queryDocumentSnapshots.getDocuments().get(a).getId()).append(",");
                }
                Log.d(TAG, "onSuccess: " + builder.toString());
                appViewModal.setFavIds(builder.toString());
                if (binding.swiperefreshFav.isRefreshing())
                    binding.swiperefreshFav.setRefreshing(false);
            }
        });

    }

}