package com.e.cryptocracy.modals;

public class CoinTradingModel {

    String base;
    String target;
    String trust_score;
    String timestamp;
    String last_traded_at;
    String last_fetch_at;
    String coin_id;
    String target_coin_id;
    String trade_url;
    Market market;
    Double last;
    Double volume;

    public Double getLast() {
        return last;
    }

    public void setLast(Double last) {
        this.last = last;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTrust_score() {
        return trust_score;
    }

    public void setTrust_score(String trust_score) {
        this.trust_score = trust_score;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getLast_traded_at() {
        return last_traded_at;
    }

    public void setLast_traded_at(String last_traded_at) {
        this.last_traded_at = last_traded_at;
    }

    public String getLast_fetch_at() {
        return last_fetch_at;
    }

    public void setLast_fetch_at(String last_fetch_at) {
        this.last_fetch_at = last_fetch_at;
    }

    public String getCoin_id() {
        return coin_id;
    }

    public void setCoin_id(String coin_id) {
        this.coin_id = coin_id;
    }

    public String getTarget_coin_id() {
        return target_coin_id;
    }

    public void setTarget_coin_id(String target_coin_id) {
        this.target_coin_id = target_coin_id;
    }

    public String getTrade_url() {
        return trade_url;
    }

    public void setTrade_url(String trade_url) {
        this.trade_url = trade_url;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public class Market {
        String name;
        String identifier;
        String has_trading_incentive;
        String logo;

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIdentifier() {
            return identifier;
        }

        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }

        public String getHas_trading_incentive() {
            return has_trading_incentive;
        }

        public void setHas_trading_incentive(String has_trading_incentive) {
            this.has_trading_incentive = has_trading_incentive;
        }
    }

}
