package com.e.cryptocracy.utility;

import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.e.cryptocracy.R;
import com.e.cryptocracy.adapters.AppViewHolder;
import com.e.cryptocracy.databinding.CoinDetailViewBinding;
import com.e.cryptocracy.databinding.FragmentCoinDetailBinding;
import com.e.cryptocracy.modals.FilterModel;
import com.e.cryptocracy.modals.MarketDataModel;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class SetCoinDetailsData {
    private static final String TAG = "SetCoinDetailsData";
    FragmentCoinDetailBinding binding;
    JSONObject mJSONObject;
    double price;
    JSONObject marketData;
    MarketDataModel marketDataModel;

    public SetCoinDetailsData(FragmentCoinDetailBinding binding, JSONObject mJSONObject) {
        this.binding = binding;
        this.mJSONObject = mJSONObject;

        try {

            marketData = mJSONObject.getJSONObject("market_data");
            Gson gson = new Gson();
            marketDataModel = gson.fromJson(marketData.toString(), MarketDataModel.class);
            setData();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(TAG, "SetCoinDetailsData: " + e.getLocalizedMessage());
        }
    }

    private void setData() throws JSONException {
        setUpCoinDetailsRec(marketData);

        String currency = AppUtils.getString(AppConstant.CURRENCY, App.context).toLowerCase();
        price = (Double) marketData.getJSONObject("current_price").get(currency);

        binding.setMarketData(marketDataModel);
        binding.tcCoinPrice.setText(AppUtils.getCurrencyFormat(price));
        double price_change_percentage_24h = (Double) marketData.get("price_change_percentage_24h");
        binding.tvPriceChange24h.setTextColor(price_change_percentage_24h > 0 ? App.context.getResources().getColor(R.color.green_dark) : App.context.getResources().getColor(R.color.red_dark));
        binding.tvPriceChange24h.setText(String.valueOf((Double) marketData.get("price_change_percentage_24h")));
        binding.textInputLayPrice.setHint(AppUtils.getCurrencyFormat(price));
        binding.textInputLay.setHint(binding.tvName.getText().toString());
        binding.textPriceConverted.setText(AppUtils.getCurrencyFormat(price));

        binding.etCoinQty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (null != s && !s.toString().isEmpty()) {
                    double qty = Double.parseDouble(binding.etCoinQty.getText().toString());
                    double finalPrice = price * qty;
                    binding.textPriceConverted.setText(AppUtils.getCurrencyFormat(finalPrice));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void setUpCoinDetailsRec(JSONObject market_data) throws JSONException {
        binding.recCoinDetails.setItemAnimator(new DefaultItemAnimator());
        binding.recCoinDetails.setHasFixedSize(true);
        binding.recCoinDetails.setAdapter(new CoinDetailsAdapter(AppUtils.getCoinDetailsData(market_data, marketDataModel)));
    }


    private class CoinDetailsAdapter extends RecyclerView.Adapter<AppViewHolder> {
        List<FilterModel> graphFilterKeysList;
        public CoinDetailsAdapter(List<FilterModel> graphFilterKeysList) {
            this.graphFilterKeysList = graphFilterKeysList;
        }

        @NonNull
        @Override
        public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            CoinDetailViewBinding coinDetailViewBinding = CoinDetailViewBinding.inflate(AppUtils.getInflater(parent), parent, false);
            return new AppViewHolder(coinDetailViewBinding);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
            holder.coinDetailViewBinding.setFilterModel(graphFilterKeysList.get(position));

        }

        @Override
        public int getItemCount() {
            return null == graphFilterKeysList ? 0 : graphFilterKeysList.size();
        }
    }
}

