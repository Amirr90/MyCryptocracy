package com.e.cryptocracy.utility;

import android.view.View;

public interface UpdateFavouriteCoinsListener {
    void onSuccess(Object obj, View view);

    void onFailed(String msg, View view);
}