package com.e.cryptocracy;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.e.cryptocracy.databinding.FragmentCoinDetailBinding;
import com.e.cryptocracy.module.Management;
import com.e.cryptocracy.utility.AppConstant;
import com.e.cryptocracy.utility.GraphData;
import com.e.cryptocracy.utility.SetCoinDetailsData;
import com.e.cryptocracy.viewModal.AppViewModal;
import com.e.cryptocracy.viewModal.ViewModelProviderFactory;
import com.e.cryptocracy.views.activity.AppHomeScreen;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


public class CoinDetailFragment extends DaggerFragment {
    FragmentCoinDetailBinding binding;
    NavController navController;

    String coinId, coinName, symbol;

    AppViewModal appViewModal;
    @Inject
    ViewModelProviderFactory providerFactory;
    @Inject
    Management management;
    String days = "1";
    String image;

    private static final String TAG = "CoinDetailFragment";
    JSONObject mJSONObject;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCoinDetailBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        if (getArguments() == null)
            navController.navigateUp();

        coinId = getArguments().getString(AppConstant.COIN_ID);
        symbol = getArguments().getString(AppConstant.SYMBOL);
        coinName = getArguments().getString(AppConstant.NAME);

        binding.tvSymbol.setText(symbol);
        binding.tvName.setText(coinName);


        binding.ivBack.setOnClickListener(AppHomeScreen.getInstance());
        appViewModal = ViewModelProviders.of(this, providerFactory).get(AppViewModal.class);


        appViewModal.getCoinDetail(coinId).observe(getViewLifecycleOwner(), o -> {
            binding.progressBar6.setVisibility(View.GONE);
            Gson gson = new Gson();
            String json = gson.toJson(o);
            try {
                mJSONObject = new JSONObject(json);
                Log.d(TAG, "onChanged: " + mJSONObject.getJSONObject("links"));

                String desc = mJSONObject.getJSONObject("description").getString("en");
                binding.tvDesc.setText(desc);

                image = mJSONObject.getJSONObject("image").getString("large");
                binding.setImage(image);

                //to set Coin Data
                new SetCoinDetailsData(binding, mJSONObject);

                //to set Coin Graph
                GraphData graphData = new GraphData(management.getHiOptions(),
                        appViewModal,
                        getViewLifecycleOwner());
                graphData.setBinding(binding);
                graphData.setmJSONObject(mJSONObject);
                graphData.setRec();
                graphData.setCoinId(coinId);
                graphData.init(days = "1");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        });


    }


    @Override
    public void onStart() {
        super.onStart();
        binding.bottomNavCoinDetail.setOnNavigationItemSelectedListener(item -> {
            Bundle bundle = new Bundle();
            bundle.putString(AppConstant.COIN_ID, coinId);
            bundle.putString(AppConstant.NAME, binding.tvName.getText().toString());
            bundle.putString(AppConstant.SYMBOL, binding.tvSymbol.getText().toString());
            bundle.putString(AppConstant.IMAGE, null == image ? "" : image);
            navController.navigate(item.getItemId(), bundle);
            return true;
        });
    }
}