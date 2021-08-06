package com.e.apiResponse;

import java.util.List;

public class TrendingCoinResponse {
    List<Coins> coins;
    List<Exchange> exchanges;

    public List<Coins> getCoins() {
        return coins;
    }

    public List<Exchange> getExchanges() {
        return exchanges;
    }

    public static class Coins {
        public Item getItem() {
            return item;
        }

        Item item;

        public class Item {
            private String id;
            private long coin_id;
            private String name;
            private String symbol;
            private long market_cap_rank;
            private String thumb;
            private String small;
            private String large;
            private String slug;
            private String position;
            private double price_btc;
            private long score;


            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getId() {
                return id;
            }

            public long getCoin_id() {
                return coin_id;
            }

            public String getName() {
                return name;
            }

            public String getSymbol() {
                return symbol;
            }

            public long getMarket_cap_rank() {
                return market_cap_rank;
            }

            public String getThumb() {
                return thumb;
            }

            public String getSmall() {
                return small;
            }

            public String getLarge() {
                return large;
            }

            public String getSlug() {
                return slug;
            }

            public double getPrice_btc() {
                return price_btc;
            }

            public long getScore() {
                return score;
            }
        }
    }

    public class Exchange {

    }
}
