package com.e.cryptocracy.modals;

import java.util.ArrayList;

public class GraphModel {
    Number[][] prices = new Number[][]{};
    ArrayList<ArrayList<Float>> market_caps = new ArrayList<>();
    ArrayList<ArrayList<Float>> total_volumes = new ArrayList<>();

    public Number[][] getPrices() {
        return prices;
    }

    public ArrayList<ArrayList<Float>> getMarket_caps() {
        return market_caps;
    }

    public ArrayList<ArrayList<Float>> getTotal_volumes() {
        return total_volumes;
    }
}
