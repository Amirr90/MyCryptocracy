package com.e.cryptocracy.views.fragments.homeScreenFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.e.cryptocracy.R;
import com.e.cryptocracy.adapters.TrendingCoinsAdapter;
import com.e.cryptocracy.addservices.AdMob;
import com.e.cryptocracy.apiInterface.onAdapterClick;
import com.e.cryptocracy.databinding.FragmentTrendingCoinBinding;
import com.e.cryptocracy.modals.TrendingItem;
import com.e.cryptocracy.utility.App;
import com.e.cryptocracy.utility.AppConstant;
import com.e.cryptocracy.utility.AppUtils;
import com.e.cryptocracy.viewModal.AppViewModal;
import com.e.cryptocracy.viewModal.ViewModelProviderFactory;
import com.e.cryptocracy.views.activity.AppHomeScreen;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


public class TrendingCoinFragment extends DaggerFragment implements onAdapterClick {

    NavController navController;
    AppViewModal appViewModal;
    @Inject
    ViewModelProviderFactory providerFactory;
    TrendingCoinsAdapter trendingCoinsAdapter;
    FragmentTrendingCoinBinding binding;
    private static final String TAG = "TrendingCoinFragment";


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTrendingCoinBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        new AdMob(requireActivity(), binding.adViewContainer);
        trendingCoinsAdapter = new TrendingCoinsAdapter(this);
        binding.recTrendingCoins.setAdapter(trendingCoinsAdapter);
        appViewModal = ViewModelProviders.of(this, providerFactory).get(AppViewModal.class);

        fetchTrendingData();

        binding.swiperefresh.setOnRefreshListener(this::fetchTrendingData);

        binding.imageView3.setOnClickListener(AppHomeScreen.getInstance());

        binding.noTrendingCoins.setOnClickListener(v -> navController.navigate(R.id.coinListFragment));
    }

    private void fetchTrendingData() {
        appViewModal.getExchangeRates().observe(getViewLifecycleOwner(), o -> {
            Gson gson = new Gson();
            String json = gson.toJson(o);
            try {
                JSONObject mJSONObject = new JSONObject(json);
                String selectedCurrency = AppUtils.getString(AppConstant.CURRENCY, requireActivity()).toLowerCase();
                double currencyValue = (Double) mJSONObject.getJSONObject("rates").getJSONObject(selectedCurrency).get("value");
                AppUtils.setString(AppConstant.CURRENCY_PRICE_IN_SELECTED, "" + currencyValue, requireActivity());
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d(TAG, "onChanged: " + e.getLocalizedMessage());
            }


        });

        appViewModal.getTrendingCoins().observe(getViewLifecycleOwner(), trendingCoins -> {
            trendingCoinsAdapter.submitList(trendingCoins);
            binding.progressBar2.setVisibility(View.GONE);
            Log.d(TAG, "fetchTrendingData: " + trendingCoins.size());

            if (binding.swiperefresh.isRefreshing()) {
                binding.swiperefresh.setRefreshing(false);
                Toast.makeText(App.context, "Refreshed", Toast.LENGTH_SHORT).show();
            }

            binding.noTrendingCoinsLay.setVisibility(trendingCoins.isEmpty() ? View.VISIBLE : View.GONE);
            binding.recTrendingCoins.setVisibility(trendingCoins.isEmpty() ? View.GONE : View.VISIBLE);
        });
    }

    @Override
    public void onClickItem(Object obj) {
        TrendingItem trendingItem = (TrendingItem) obj;
        Bundle bundle = new Bundle();
        bundle.putString(AppConstant.COIN_ID, trendingItem.getId());
        bundle.putString(AppConstant.NAME, trendingItem.getName());
        bundle.putString(AppConstant.SYMBOL, trendingItem.getSymbol());
        navController.navigate(R.id.action_trendingCoinFragment_to_coinDetailFragment, bundle);
    }
}