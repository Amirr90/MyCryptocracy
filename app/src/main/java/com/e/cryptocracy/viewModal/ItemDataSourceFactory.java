package com.e.cryptocracy.viewModal;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.e.cryptocracy.modals.CoinModal;

public class ItemDataSourceFactory extends DataSource.Factory {

    private static final String TAG = "ItemDataSourceFactory";
    private final MutableLiveData<PageKeyedDataSource<Integer, CoinModal>> itemLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource create() {
        ItemDataSource itemDataSource = new ItemDataSource();
        itemLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, CoinModal>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }

}
