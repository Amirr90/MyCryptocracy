package com.e.cryptocracy;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;

import com.e.cryptocracy.adapters.TweetAdapter;
import com.e.cryptocracy.databinding.FragmentCoinTwitterBinding;
import com.e.cryptocracy.utility.AppConstant;
import com.e.cryptocracy.viewModal.AppViewModal;
import com.e.cryptocracy.viewModal.ViewModelProviderFactory;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;


public class CoinTwitterFragment extends BottomSheetDialogFragment {

    private static final String TAG = "CoinTwitterFragment";

    FragmentCoinTwitterBinding binding;
    TweetAdapter adapter;
    AppViewModal appViewModal;
    @Inject
    ViewModelProviderFactory providerFactory;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        AndroidSupportInjection.inject(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCoinTwitterBinding.inflate(getLayoutInflater());
        setStyle(DialogFragment.STYLE_NORMAL, R.style.ThemeOverlay_Demo_BottomSheetDialog);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        appViewModal = ViewModelProviders.of(this, providerFactory).get(AppViewModal.class);

        adapter = new TweetAdapter();
        binding.recCoinNews.setItemAnimator(new DefaultItemAnimator());
        binding.recCoinNews.setAdapter(adapter);


        if (getArguments() == null)
            return;
        String str = getArguments().getString(AppConstant.SYMBOL).toLowerCase() + "-" + getArguments().getString(AppConstant.NAME).toLowerCase().trim();
        appViewModal.coinTweet(str).observe(getViewLifecycleOwner(), o -> {
            binding.progressBar7.setVisibility(View.GONE);
            Gson gson = new Gson();
            String json = gson.toJson(o);
            Log.d(TAG, "onViewCreated: " + json);
        });


    }
}