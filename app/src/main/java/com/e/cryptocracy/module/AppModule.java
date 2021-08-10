package com.e.cryptocracy.module;

import androidx.room.Room;

import com.e.cryptocracy.apiInterface.Api;
import com.e.cryptocracy.appDatabase.AppDatabase;
import com.e.cryptocracy.utility.App;
import com.e.cryptocracy.utility.AppConstant;
import com.e.cryptocracy.utility.AppUrl;
import com.e.cryptocracy.utility.AppUtils;
import com.highsoft.highcharts.common.hichartsclasses.HIExporting;
import com.highsoft.highcharts.common.hichartsclasses.HILabels;
import com.highsoft.highcharts.common.hichartsclasses.HILine;
import com.highsoft.highcharts.common.hichartsclasses.HIOptions;
import com.highsoft.highcharts.common.hichartsclasses.HIPlotOptions;
import com.highsoft.highcharts.common.hichartsclasses.HITitle;
import com.highsoft.highcharts.common.hichartsclasses.HIXAxis;
import com.highsoft.highcharts.common.hichartsclasses.HIYAxis;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Singleton
    @Provides
    public AppDatabase provideAppDatabase() {
        return Room.databaseBuilder(App.context, AppDatabase.class, "user_table")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    OkHttpClient.Builder provideHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(1);
        httpClient.addInterceptor(logging);
        httpClient.dispatcher(dispatcher);
        return httpClient;
    }


    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient.Builder builder) {
        return new Retrofit.Builder()
                .baseUrl(AppUrl.coinGeckoUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
    }

    @Singleton
    @Provides
    Api provideApi(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }

    @Singleton
    @Provides
    HIPlotOptions provideHIPlotOptions() {
        return new HIPlotOptions();
    }

    @Singleton
    @Provides
    HIExporting provideHIExporting() {
        return new HIExporting();
    }

    @Singleton
    @Provides
    HIOptions provideHIOptions(HIPlotOptions plotOptions, HIExporting hiExporting) {

        HIOptions options = new HIOptions();
        HIXAxis xAxis = new HIXAxis();
        xAxis.setType("datetime");
        xAxis.setLabels(new HILabels());
        xAxis.getLabels().setOverflow("justify");
        options.setXAxis(new ArrayList<HIXAxis>() {{
            add(xAxis);
        }});

        final HIYAxis yAxis = new HIYAxis();
        yAxis.setTitle(new HITitle());
        options.setYAxis(new ArrayList<HIYAxis>() {{
            add(yAxis);
        }});
        yAxis.setTitle(new HITitle());
        yAxis.getTitle().setText("Price Chart (" + AppUtils.getString(AppConstant.CURRENCY, App.context) + ")");
        yAxis.setMinorGridLineWidth(0);
        yAxis.setGridLineWidth(0);
        yAxis.setAlternateGridColor(null);


        plotOptions.setLine(new HILine());
        plotOptions.getLine().setEnableMouseTracking(true);
        options.setPlotOptions(plotOptions);
        hiExporting.setEnabled(false);
        options.setExporting(hiExporting);
        return options;

    }


}
