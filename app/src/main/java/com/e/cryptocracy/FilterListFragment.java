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
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;

import com.e.cryptocracy.adapters.FilterAdapter;
import com.e.cryptocracy.apiInterface.onAdapterClick;
import com.e.cryptocracy.databinding.FragmentFilterListBinding;
import com.e.cryptocracy.modals.CoinCategoryModal;
import com.e.cryptocracy.modals.FilterModel;
import com.e.cryptocracy.utility.AppConstant;
import com.e.cryptocracy.utility.AppUtils;
import com.e.cryptocracy.viewModal.AppViewModal;
import com.e.cryptocracy.viewModal.ViewModelProviderFactory;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

import static com.e.cryptocracy.utility.AppUtils.getFilterList;


public class FilterListFragment extends BottomSheetDialogFragment implements onAdapterClick {


    private static final String TAG = "FilterListFragment";
    FragmentFilterListBinding binding;

    AppViewModal appViewModal;
    @Inject
    ViewModelProviderFactory providerFactory;

    FilterAdapter filterAdapter;
    String key;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        AndroidSupportInjection.inject(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFilterListBinding.inflate(getLayoutInflater());
        setStyle(DialogFragment.STYLE_NORMAL, R.style.ThemeOverlay_Demo_BottomSheetDialog);

        appViewModal = ViewModelProviders.of(this, providerFactory).get(AppViewModal.class);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (null == getArguments())
            dismiss();

        key = getArguments().getString(AppConstant.KEY_FILTER);

        binding.recFilter.setItemAnimator(new DefaultItemAnimator());
        binding.recFilter.setHasFixedSize(true);
        filterAdapter = new FilterAdapter(this);
        binding.recFilter.setAdapter(filterAdapter);

        if (getArguments().getString(AppConstant.KEY_FILTER).equalsIgnoreCase(getString(R.string.category))) {
            binding.progressBar.setVisibility(View.VISIBLE);
            appViewModal.getAllCategory().observe(getViewLifecycleOwner(), coinCategoryModals -> {
                List<FilterModel> list = new ArrayList<>();
                for (CoinCategoryModal modal : coinCategoryModals)
                    list.add(new FilterModel(modal.getCategory_id(), modal.getName()));
                filterAdapter.submitList(list);
                binding.progressBar.setVisibility(View.GONE);
            });
        } else {
            filterAdapter.submitList(getFilterList(getArguments().getString(AppConstant.KEY_FILTER), binding));
        }


        binding.tvClearFilter.setOnClickListener(v -> {
            if (key.equalsIgnoreCase(getString(R.string.category)))
                AppUtils.setString(AppConstant.CATEGORY, "", requireActivity());


            NavHostFragment.findNavController(requireParentFragment()).getPreviousBackStackEntry().getSavedStateHandle().set(AppConstant.COIN_LIST_FILTER_KEY, key);
            NavHostFragment.findNavController(requireParentFragment()).popBackStack();
            dismiss();
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        updateThought();
    }

    private void updateThought() {
        String[] thoughts = requireActivity().getResources().getStringArray(R.array.tips);
        final int position = new Random().nextInt((thoughts.length - 1) + 1);
        binding.tvThought.setText(thoughts[position]);
    }


    @Override
    public void onClickItem(Object obj) {

        FilterModel filterModel = (FilterModel) obj;
        Log.d(TAG, "onClickItem: " + filterModel.getSortKeys());

        if (key.equalsIgnoreCase(getString(R.string.category)))
            AppUtils.setString(AppConstant.CATEGORY, filterModel.getSortKeys(), requireActivity());

        else if (key.equalsIgnoreCase(getString(R.string.sort_type)) || key.equalsIgnoreCase(getString(R.string.price)))
            AppUtils.setString(AppConstant.ORDER_BY, filterModel.getSortKeys(), requireActivity());


        NavHostFragment.findNavController(requireParentFragment()).getPreviousBackStackEntry().getSavedStateHandle().set(AppConstant.COIN_LIST_FILTER_KEY, key);
        NavHostFragment.findNavController(requireParentFragment()).popBackStack();
        dismiss();
    }
}