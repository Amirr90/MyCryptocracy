package com.e.cryptocracy.modals;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.e.cryptocracy.utility.TABLE;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = TABLE.SEARCHED_COIN_TABLE)
public class SearchedCoinModal {
    @PrimaryKey
    @NotNull
    public String id;
    public String name;
    public String symbol;

    @NotNull
    public String getId() {
        return id;
    }

    public void setId(@NotNull String id) {
        this.id = id;
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
}
