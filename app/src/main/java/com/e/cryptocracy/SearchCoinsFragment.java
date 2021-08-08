package com.e.cryptocracy;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;

import com.e.cryptocracy.adapters.SearchCoinAdapter;
import com.e.cryptocracy.apiInterface.onAdapterClick;
import com.e.cryptocracy.databinding.FragmentSearchCoinsBinding;
import com.e.cryptocracy.modals.SearchedCoinModal;
import com.e.cryptocracy.utility.AppConstant;
import com.e.cryptocracy.viewModal.AppViewModal;
import com.e.cryptocracy.viewModal.ViewModelProviderFactory;
import com.e.cryptocracy.views.activity.AppHomeScreen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


public class SearchCoinsFragment extends DaggerFragment implements onAdapterClick {


    FragmentSearchCoinsBinding binding;
    List<SearchedCoinModal> allCoinsList;
    AppViewModal appViewModal;
    @Inject
    ViewModelProviderFactory providerFactory;

    SearchCoinAdapter adapter;
    NavController navController;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchCoinsBinding.inflate(getLayoutInflater());
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        navController= Navigation.findNavController(view);
        adapter = new SearchCoinAdapter(this);
        binding.recSearchedCoin.setItemAnimator(new DefaultItemAnimator());
        binding.recSearchedCoin.setAdapter(adapter);
        binding.imageView5.setOnClickListener(AppHomeScreen.getInstance());

        binding.etSearchCurrency.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (null != s) {
                    filter(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        appViewModal = ViewModelProviders.of(this, providerFactory).get(AppViewModal.class);
        fetchAllCoins();

        binding.allCoinsSwipeRefresh.setOnRefreshListener(this::fetchAllCoins);


    }

    private void fetchAllCoins() {
        if (binding.allCoinsSwipeRefresh.isRefreshing())
            binding.allCoinsSwipeRefresh.setRefreshing(false);
        appViewModal.getAllCoins().observe(getViewLifecycleOwner(), searchedCoinModals -> {
            allCoinsList = searchedCoinModals;
            Collections.shuffle(allCoinsList);
            adapter.submitList(allCoinsList);
            binding.progressBar4.setVisibility(View.GONE);
        });
    }

    private void filter(String key) {
        if (allCoinsList != null) {
            adapter.submitList(new ArrayList<>());
            List<SearchedCoinModal> filterList = new ArrayList<>();
            for (SearchedCoinModal modal : allCoinsList) {
                if (
                        modal.getName().toLowerCase().startsWith(key)
                        || modal.getName().toLowerCase().equalsIgnoreCase(key)
                        || modal.getSymbol().toLowerCase().equalsIgnoreCase(key)
                ) {
                    filterList.add(modal);
                }
            }
            adapter.submitList(filterList);
        }
    }

    @Override
    public void onClickItem(Object obj) {
        SearchedCoinModal trendingItem = (SearchedCoinModal) obj;
        Bundle bundle = new Bundle();
        bundle.putString(AppConstant.COIN_ID, trendingItem.getId());
        bundle.putString(AppConstant.NAME, trendingItem.getName());
        bundle.putString(AppConstant.SYMBOL, trendingItem.getSymbol());
        navController.navigate(R.id.action_searchCoinsFragment_to_coinDetailFragment, bundle);
    }
}