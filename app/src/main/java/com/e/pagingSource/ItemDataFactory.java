package com.e.pagingSource;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.e.cryptocracy.apiInterface.Api;
import com.e.cryptocracy.modals.CoinModal;

public class ItemDataFactory extends DataSource.Factory {
    MutableLiveData<PageKeyedDataSource<Integer, CoinModal>> liveData = new MutableLiveData<>();

    Api api;

    public ItemDataFactory(Api api) {
        this.api = api;
    }

    @Override
    public DataSource create() {
        ItemDataSource itemDataSource = new ItemDataSource(api);
        liveData.postValue(itemDataSource);
        return null;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, CoinModal>> getLiveData() {
        return liveData;
    }
}
