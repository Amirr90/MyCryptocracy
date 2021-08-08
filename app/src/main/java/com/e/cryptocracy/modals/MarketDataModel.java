package com.e.cryptocracy.modals;

public class MarketDataModel {
    Double price_change_24h;
    Double price_change_percentage_24h;
    Double price_change_percentage_7d;
    Double price_change_percentage_14d;
    Double price_change_percentage_30d;
    Double price_change_percentage_60d;
    Double price_change_percentage_200d;
    Double price_change_percentage_1y;
    Double market_cap_change_24h;
    Double market_cap_change_percentage_24h;
    Double total_supply;
    Double max_supply;
    Double circulating_supply;
    String last_updated;
    double scale;

    public MarketDataModel() {
        scale = Math.pow(10, 2);
    }

    public Double getPrice_change_24h() {
        return price_change_24h;
    }

    public void setPrice_change_24h(Double price_change_24h) {
        this.price_change_24h = price_change_24h;
    }

    public Double getPrice_change_percentage_24h() {

        return Math.round(scale * price_change_percentage_24h) / scale;
    }

    public void setPrice_change_percentage_24h(Double price_change_percentage_24h) {
        this.price_change_percentage_24h = price_change_percentage_24h;
    }

    public Double getPrice_change_percentage_7d() {
        return Math.round(scale * price_change_percentage_7d) / scale;
    }

    public void setPrice_change_percentage_7d(Double price_change_percentage_7d) {
        this.price_change_percentage_7d = price_change_percentage_7d;
    }

    public Double getPrice_change_percentage_14d() {
        return Math.round(scale * price_change_percentage_14d) / scale;
    }

    public void setPrice_change_percentage_14d(Double price_change_percentage_14d) {
        this.price_change_percentage_14d = price_change_percentage_14d;
    }

    public Double getPrice_change_percentage_30d() {
        return Math.round(scale * price_change_percentage_30d) / scale;
    }

    public void setPrice_change_percentage_30d(Double price_change_percentage_30d) {
        this.price_change_percentage_30d = price_change_percentage_30d;
    }

    public Double getPrice_change_percentage_60d() {
        return Math.round(scale * price_change_percentage_60d) / scale;
    }

    public void setPrice_change_percentage_60d(Double price_change_percentage_60d) {
        this.price_change_percentage_60d = price_change_percentage_60d;
    }

    public Double getPrice_change_percentage_200d() {
        return Math.round(scale * price_change_percentage_200d) / scale;
    }

    public void setPrice_change_percentage_200d(Double price_change_percentage_200d) {
        this.price_change_percentage_200d = price_change_percentage_200d;
    }

    public double getPrice_change_percentage_1y() {
        return Math.round(scale * price_change_percentage_1y) / scale;

    }

    public void setPrice_change_percentage_1y(Double price_change_percentage_1y) {
        this.price_change_percentage_1y = price_change_percentage_1y;
    }

    public Double getMarket_cap_change_24h() {
        double scale = Math.pow(10, 2);
        return Math.round(scale * market_cap_change_24h) / scale;
    }

    public void setMarket_cap_change_24h(Double market_cap_change_24h) {
        this.market_cap_change_24h = market_cap_change_24h;
    }

    public Double getMarket_cap_change_percentage_24h() {
        return Math.round(scale * market_cap_change_percentage_24h) / scale;
    }

    public void setMarket_cap_change_percentage_24h(Double market_cap_change_percentage_24h) {
        this.market_cap_change_percentage_24h = market_cap_change_percentage_24h;
    }

    public Double getTotal_supply() {
        return total_supply;
    }

    public void setTotal_supply(Double total_supply) {
        this.total_supply = total_supply;
    }

    public Double getMax_supply() {
        return max_supply;
    }

    public void setMax_supply(Double max_supply) {
        this.max_supply = max_supply;
    }

    public Double getCirculating_supply() {
        return circulating_supply;
    }

    public void setCirculating_supply(Double circulating_supply) {
        this.circulating_supply = circulating_supply;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }
}
