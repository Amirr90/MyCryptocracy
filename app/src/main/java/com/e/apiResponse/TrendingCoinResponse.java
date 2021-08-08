package com.e.apiResponse;

import com.e.cryptocracy.modals.TrendingItem;

import java.util.List;

public class TrendingCoinResponse {
    List<TrendingCoins> coins;
    List<Exchange> exchanges;

    public List<TrendingCoins> getCoins() {
        return coins;
    }

    public List<Exchange> getExchanges() {
        return exchanges;
    }

    public static class TrendingCoins {
        TrendingItem item;
        private String id;

        public TrendingItem getItem() {
            return item;
        }

        public String getId() {
            return id;
        }

        public void setItem(TrendingItem item) {
            this.item = item;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public class Exchange {

    }
}
