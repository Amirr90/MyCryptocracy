package com.e.cryptocracy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.e.cryptocracy.databinding.FilterViewBinding;
import com.e.cryptocracy.databinding.FragmentFilterListBinding;
import com.e.cryptocracy.modals.FilterModel;
import com.e.cryptocracy.utility.AppConstant;
import com.e.cryptocracy.utility.AppUtils;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;
import java.util.Random;


public class FilterListFragment extends BottomSheetDialogFragment {


    FragmentFilterListBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFilterListBinding.inflate(getLayoutInflater());
        setStyle(DialogFragment.STYLE_NORMAL, R.style.ThemeOverlay_Demo_BottomSheetDialog);

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (null == getArguments())
            dismiss();

      /*  binding.recFilter.addItemDecoration(new
                DividerItemDecoration(requireActivity(),
                DividerItemDecoration.VERTICAL));*/
        binding.recFilter.setItemAnimator(new DefaultItemAnimator());
        binding.recFilter.setHasFixedSize(true);
        binding.recFilter.setAdapter(new DemoAdapter(AppUtils.getFilterList(getArguments().getString(AppConstant.KEY_FILTER), binding)));
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

    private class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.DemoVH> {
        List<FilterModel> modelList;

        public DemoAdapter(List<FilterModel> modelList) {
            this.modelList = modelList;
        }

        @NonNull
        @Override
        public DemoAdapter.DemoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            FilterViewBinding coinViewBinding = FilterViewBinding.inflate(layoutInflater, parent, false);
            return new DemoAdapter.DemoVH(coinViewBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull DemoAdapter.DemoVH holder, int position) {
            holder.coinViewBinding.setFilterModel(modelList.get(position));
            holder.coinViewBinding.getRoot().setOnClickListener(v -> dismiss());
        }

        @Override
        public int getItemCount() {
            return modelList.size();
        }

        public class DemoVH extends RecyclerView.ViewHolder {
            FilterViewBinding coinViewBinding;

            public DemoVH(@NonNull FilterViewBinding coinViewBinding) {
                super(coinViewBinding.getRoot());
                this.coinViewBinding = coinViewBinding;
            }
        }
    }
}