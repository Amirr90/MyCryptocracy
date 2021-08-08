package com.e.cryptocracy.modals;

public class TrendingItem {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCoin_id() {
        return coin_id;
    }

    public void setCoin_id(long coin_id) {
        this.coin_id = coin_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public long getMarket_cap_rank() {
        return market_cap_rank;
    }

    public void setMarket_cap_rank(long market_cap_rank) {
        this.market_cap_rank = market_cap_rank;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getPrice_btc() {
        return price_btc;
    }

    public void setPrice_btc(double price_btc) {
        this.price_btc = price_btc;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }
}
