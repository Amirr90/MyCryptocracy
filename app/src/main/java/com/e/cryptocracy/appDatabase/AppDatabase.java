package com.e.cryptocracy.appDatabase;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.e.cryptocracy.appDao.AppDao;
import com.e.cryptocracy.modals.CoinCategoryModal;
import com.e.cryptocracy.modals.CoinModal;
import com.e.cryptocracy.modals.SearchedCoinModal;

@Database(entities = {CoinModal.class, CoinCategoryModal.class, SearchedCoinModal.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    static AppDatabase appDatabase;

    public static synchronized AppDatabase getDatabase(Application application) {
        if (appDatabase == null)
            appDatabase = Room.databaseBuilder(application, AppDatabase.class, "myDatabase")
                    .fallbackToDestructiveMigration()
                    //.addCallback(callback)
                    .allowMainThreadQueries()
                    .build();

        return appDatabase;
    }

    public abstract AppDao getAppDao();

}