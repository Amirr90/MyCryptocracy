package com.e.cryptocracy.views.fragments.homeScreenFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;

import com.e.cryptocracy.R;
import com.e.cryptocracy.adapters.CoinAdapter;
import com.e.cryptocracy.adapters.GraphFilterKeysAdapter;
import com.e.cryptocracy.apiInterface.onAdapterClick;
import com.e.cryptocracy.databinding.FragmentCoinListBinding;
import com.e.cryptocracy.utility.AppConstant;
import com.e.cryptocracy.utility.AppUtils;
import com.e.cryptocracy.viewModal.AppViewModal;
import com.e.cryptocracy.viewModal.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


public class CoinListFragment extends DaggerFragment implements onAdapterClick {
    private static final String TAG = "CoinListFragment";


    FragmentCoinListBinding binding;
    NavController navController;
    AppViewModal appViewModal;

    @Inject
    ViewModelProviderFactory providerFactory;
    CoinAdapter coinAdapter;
    String page = "1";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCoinListBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        navController = Navigation.findNavController(view);

        coinAdapter = new CoinAdapter();
        binding.recCoinHome.setAdapter(coinAdapter);

        appViewModal = ViewModelProviders.of(this, providerFactory).get(AppViewModal.class);
       /* binding.recCoinHome.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                // binding.constraintLayout.setVisibility(newState == 0 ? View.VISIBLE : View.GONE);
            }
        });*/

        binding.ivSearch.setOnClickListener(v -> {
            navController.navigate(R.id.action_coinListFragment_to_changeCurrencyFragment);
        });

        binding.setSortClickListener(name -> {
            Bundle bundle = new Bundle();
            bundle.putString(AppConstant.KEY_FILTER, name);
            if (name.equalsIgnoreCase(getString(R.string.currency)))
                navController.navigate(R.id.action_coinListFragment_to_changeCurrencyFragment);
            else
                navController.navigate(R.id.action_coinListFragment_to_filterListFragment, bundle);
        });

        listenCoinData(page = "1");
        binding.swiperefresh.setOnRefreshListener(() -> listenCoinData(page = "1"));
    }


    @Override
    public void onResume() {
        super.onResume();

        setUpGraphFilterKeysRec();
    }

    private void setUpGraphFilterKeysRec() {
        binding.recFilter.setItemAnimator(new DefaultItemAnimator());
        binding.recFilter.setHasFixedSize(true);
        binding.recFilter.setAdapter(new GraphFilterKeysAdapter(AppUtils.coinFilterKeys(), this));
    }

    private void listenCoinData(String page) {
        appViewModal.getAllCoins(page).observe(getViewLifecycleOwner(), coinModals -> {
            coinAdapter.submitList(coinModals);
            if (binding.swiperefresh.isRefreshing())
                binding.swiperefresh.setRefreshing(false);
        });

    }

    @Override
    public void onClickItem(Object obj) {
        String key = (String) obj;
        Bundle bundle = new Bundle();
        bundle.putString(AppConstant.KEY_FILTER, key);
        if (key.equalsIgnoreCase(getString(R.string.currency)))
            navController.navigate(R.id.action_coinListFragment_to_changeCurrencyFragment);
        else
            navController.navigate(R.id.action_coinListFragment_to_filterListFragment, bundle);
    }

    public interface SortItemCLick {
        void onClick(String name);

    }

}