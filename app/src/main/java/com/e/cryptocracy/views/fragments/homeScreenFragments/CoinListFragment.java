package com.e.cryptocracy.views.fragments.homeScreenFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.e.cryptocracy.R;
import com.e.cryptocracy.databinding.CoinViewBinding;
import com.e.cryptocracy.databinding.FragmentCoinListBinding;
import com.e.cryptocracy.views.utility.AppConstant;


public class CoinListFragment extends Fragment {
    private static final String TAG = "CoinListFragment";


    FragmentCoinListBinding binding;
    NavController navController;

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
        binding.recCoinHome.setAdapter(new DemoAdapter());

       /* binding.recCoinHome.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                // binding.constraintLayout.setVisibility(newState == 0 ? View.VISIBLE : View.GONE);
            }
        });*/

       /* binding.ivSearch.setOnClickListener(v -> {
            binding.editTextTextPersonName.setVisibility(binding.editTextTextPersonName.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            binding.editTextTextPersonName.setAnimation(Animation.getAnimation(R.anim.fade_in));
        });*/

        binding.setSortClickListener(name -> {
            Bundle bundle = new Bundle();
            bundle.putString(AppConstant.KEY_FILTER, name);
            navController.navigate(R.id.action_coinListFragment_to_filterListFragment, bundle);
        });

    }

    private class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.DemoVH> {
        @NonNull
        @Override
        public DemoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            CoinViewBinding coinViewBinding = CoinViewBinding.inflate(layoutInflater, parent, false);
            return new DemoVH(coinViewBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull DemoVH holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 100;
        }

        public class DemoVH extends RecyclerView.ViewHolder {
            CoinViewBinding coinViewBinding;

            public DemoVH(@NonNull CoinViewBinding coinViewBinding) {
                super(coinViewBinding.getRoot());
                this.coinViewBinding = coinViewBinding;
            }
        }
    }

    public interface SortItemCLick {
        void onClick(String name);
    }
}