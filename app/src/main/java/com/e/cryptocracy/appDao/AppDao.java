package com.e.cryptocracy.appDao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.e.cryptocracy.modals.CoinModal;

import java.util.List;

@Dao
public interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addCoins(CoinModal user);

    @Query("select * from coin_table")
    LiveData<List<CoinModal>> getAllCoins();

    @Update
    void updateCoins(CoinModal user);

    @Delete
    void deleteCoins(CoinModal user);

    @Query("delete from coin_table")
    void deleteAllCoins();
}
