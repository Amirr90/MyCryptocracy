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
import androidx.recyclerview.widget.DefaultItemAnimator;

import com.e.cryptocracy.R;
import com.e.cryptocracy.adapters.CoinAdapter;
import com.e.cryptocracy.adapters.GraphFilterKeysAdapter;
import com.e.cryptocracy.addservices.AdMob;
import com.e.cryptocracy.apiInterface.onAdapterClick;
import com.e.cryptocracy.databinding.FragmentCoinListBinding;
import com.e.cryptocracy.utility.App;
import com.e.cryptocracy.utility.AppConstant;
import com.e.cryptocracy.utility.AppUtils;
import com.e.cryptocracy.viewModal.AppViewModal;
import com.e.cryptocracy.viewModal.ViewModelProviderFactory;
import com.google.firebase.firestore.CollectionReference;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

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

        coinAdapter = new CoinAdapter(navController);
        binding.recCoinHome.setAdapter(coinAdapter);

        new AdMob(requireActivity(), binding.adViewContainer);

        appViewModal = ViewModelProviders.of(this, providerFactory).get(AppViewModal.class);

        receiveBackStackData();

        binding.topAppBar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.logout) {
               AppUtils.logout(requireActivity());
            } else navController.navigate(item.getItemId());
            return true;
        });
        binding.setSortClickListener(name -> {
            Bundle bundle = new Bundle();
            bundle.putString(AppConstant.KEY_FILTER, name);
            if (name.equalsIgnoreCase(getString(R.string.currency)))
                navController.navigate(R.id.action_coinListFragment_to_changeCurrencyFragment);
            else
                navController.navigate(R.id.action_coinListFragment_to_filterListFragment, bundle);
        });

        // appViewModal.setItemPagedList();
        appViewModal.itemPagedList.observe(getViewLifecycleOwner(), coinModals -> {

            coinAdapter.submitList(coinModals);
        });


        loadFavCoinsData();

    }

    private void receiveBackStackData() {
        navController.getCurrentBackStackEntry().getSavedStateHandle()
                .getLiveData(AppConstant.COIN_LIST_FILTER_KEY)
                .observe(getViewLifecycleOwner(), value -> {
                    Log.d(TAG, "receiveBackStackData: " + value);
                    listenCoinData();
                });


    }


    @Override
    public void onResume() {
        super.onResume();
        setCoinFilterKeys();
        if (AppUtils.getString(AppConstant.CURRENCY, App.context).contentEquals(""))
            navController.navigate(R.id.action_coinListFragment_to_changeCurrencyFragment);


    }

    private void setCoinFilterKeys() {
        binding.recFilter.setItemAnimator(new DefaultItemAnimator());
        binding.recFilter.setHasFixedSize(true);
        binding.recFilter.setAdapter(new GraphFilterKeysAdapter(AppUtils.coinFilterKeys(), this));
    }

    private void listenCoinData() {
        appViewModal.setItemPagedList();
    }

    @Override
    public void onClickItem(Object obj) {
        String key = (String) obj;
        Bundle bundle = new Bundle();
        bundle.putString(AppConstant.KEY_FILTER, key);
        String currency = AppUtils.getString(AppConstant.CURRENCY, requireActivity());
        if (key.equalsIgnoreCase(getString(R.string.currency) + "(" + currency + ")"))
            navController.navigate(R.id.action_coinListFragment_to_changeCurrencyFragment);
        else
            navController.navigate(R.id.action_coinListFragment_to_filterListFragment, bundle);
    }

    public interface SortItemCLick {
        void onClick(String name);

    }
    private void loadFavCoinsData() {
        CollectionReference FavRef = AppUtils.getFireStoreReference().collection("users")
                .document(AppUtils.getUid())
                .collection(AppConstant.FAVOURITE);


        FavRef.get().addOnSuccessListener(queryDocumentSnapshots -> {
            if (queryDocumentSnapshots != null && !queryDocumentSnapshots.isEmpty()) {
                List<String> favList = new ArrayList<>();
                for (int a = 0; a < queryDocumentSnapshots.size(); a++) {
                    favList.add(queryDocumentSnapshots.getDocuments().get(a).getId());
                }
                Gson gson = new Gson();
                String list = gson.toJson(favList);
                AppUtils.setString(AppConstant.FAVOURITE_COINS, list, requireActivity());
            }
        });
    }

}