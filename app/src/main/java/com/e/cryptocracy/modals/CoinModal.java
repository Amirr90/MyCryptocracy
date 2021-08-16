package com.e.cryptocracy.modals;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "coin_table")
public class CoinModal {
    @PrimaryKey
    @NotNull
    String id;
    private String symbol;
    private String image;
    private double current_price;
    private double market_cap;
    private double total_volume;
    private double price_change_percentage_24h;
    private String price_change_24h;
    private String name;
    private long market_cap_rank;
    private double high_24h;
    private double low_24h;
    Boolean addView;

    public Boolean getAddView() {
        return addView;
    }

    public void setAddView(Boolean addView) {
        this.addView = addView;
    }

    @NotNull
    public String getId() {
        return id;
    }

    public void setId(@NotNull String id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(double current_price) {
        this.current_price = current_price;
    }

    public double getMarket_cap() {
        return market_cap;
    }

    public void setMarket_cap(double market_cap) {
        this.market_cap = market_cap;
    }

    public double getTotal_volume() {
        return total_volume;
    }

    public void setTotal_volume(double total_volume) {
        this.total_volume = total_volume;
    }

    public double getPrice_change_percentage_24h() {
        double scale = Math.pow(10, 2);
        return Math.round(scale * price_change_percentage_24h) / scale;
    }

    public void setPrice_change_percentage_24h(double price_change_percentage_24h) {
        this.price_change_percentage_24h = price_change_percentage_24h;
    }

    public String getPrice_change_24h() {
        return price_change_24h;
    }

    public void setPrice_change_24h(String price_change_24h) {
        this.price_change_24h = price_change_24h;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMarket_cap_rank() {
        return market_cap_rank;
    }

    public void setMarket_cap_rank(long market_cap_rank) {
        this.market_cap_rank = market_cap_rank;
    }

    public double getHigh_24h() {
        return high_24h;
    }

    public void setHigh_24h(double high_24h) {
        this.high_24h = high_24h;
    }

    public double getLow_24h() {
        return low_24h;
    }

    public void setLow_24h(double low_24h) {
        this.low_24h = low_24h;
    }
}
