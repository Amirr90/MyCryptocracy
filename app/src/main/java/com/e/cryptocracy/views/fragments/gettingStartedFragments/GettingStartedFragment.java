package com.e.cryptocracy.views.fragments.gettingStartedFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.e.cryptocracy.databinding.FragmentGettingStartedBinding;
import com.e.cryptocracy.modals.WelcomeModel;
import com.e.cryptocracy.utility.AppUtils;

import java.util.List;


public class GettingStartedFragment extends Fragment {

    FragmentGettingStartedBinding binding;
    int page;

    public static GettingStartedFragment newInstance(int page) {
        GettingStartedFragment fragmentFirst = new GettingStartedFragment();
        Bundle args = new Bundle();
        args.putInt("page", page);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGettingStartedBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if (null == getArguments())
            return;
        page = getArguments().getInt("page", 0);
        List<WelcomeModel> models = AppUtils.getWelcomeList();
        binding.setWelcomeModel(models.get(page));
    }
}