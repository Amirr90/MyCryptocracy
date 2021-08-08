package com.e.cryptocracy.utility;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.DefaultItemAnimator;

import com.e.cryptocracy.adapters.GraphFilterKeysAdapter;
import com.e.cryptocracy.apiInterface.onAdapterClick;
import com.e.cryptocracy.modals.GraphModel;
import com.e.cryptocracy.viewModal.AppViewModal;
import com.highsoft.highcharts.common.hichartsclasses.HICSSObject;
import com.highsoft.highcharts.common.hichartsclasses.HIHover;
import com.highsoft.highcharts.common.hichartsclasses.HILabel;
import com.highsoft.highcharts.common.hichartsclasses.HILabels;
import com.highsoft.highcharts.common.hichartsclasses.HIMarker;
import com.highsoft.highcharts.common.hichartsclasses.HINavigation;
import com.highsoft.highcharts.common.hichartsclasses.HIOptions;
import com.highsoft.highcharts.common.hichartsclasses.HIPlotBands;
import com.highsoft.highcharts.common.hichartsclasses.HIPlotOptions;
import com.highsoft.highcharts.common.hichartsclasses.HISpline;
import com.highsoft.highcharts.common.hichartsclasses.HIStates;
import com.highsoft.highcharts.common.hichartsclasses.HISubtitle;
import com.highsoft.highcharts.common.hichartsclasses.HITitle;
import com.highsoft.highcharts.common.hichartsclasses.HITooltip;
import com.highsoft.highcharts.common.hichartsclasses.HIXAxis;
import com.highsoft.highcharts.common.hichartsclasses.HIYAxis;
import com.highsoft.highcharts.core.HIChartView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class GraphData extends GraphVariable implements onAdapterClick {
    private static final String TAG = "GraphData";


    public GraphData(HIOptions options, AppViewModal appViewModal, LifecycleOwner viewLifecycleOwner) {
        this.options = options;
        this.appViewModal = appViewModal;
        this.lifecycleOwner = viewLifecycleOwner;
    }


    public void setRec() {
        setUpGraphFilterKeysRec();
        chartView = binding.chartView;
        HISpline series1 = new HISpline();
        series1.setName(binding.tvName.getText().toString());
        series1.setData(new ArrayList<>());
        options.setSeries(new ArrayList<>(Arrays.asList(series1)));
        chartView.setOptions(options);
    }

    public void init(String days) {
        binding.progressBar6.setVisibility(View.VISIBLE);
        appViewModal.getCoinGraphData(coinId, days).observe(lifecycleOwner, graphModel -> {
            binding.progressBar6.setVisibility(View.GONE);
            setGraphData(graphModel);
        });


    }

    private void setGraphData(GraphModel graphData) {
        HISpline series1 = new HISpline();
        series1.setName(binding.tvName.getText().toString());
        Number[] series1_data = new Number[graphData.getPrices().size()];
        for (int a = 0; a < graphData.getPrices().size(); a++) {
            series1_data[a] = graphData.getPrices().get(a).get(1);
        }
        series1.setData(new ArrayList<>(Arrays.asList(series1_data)));


        options.setSeries(new ArrayList<>(Arrays.asList(series1)));

        chartView.setOptions(options);
        Log.d(TAG, "addData: ");
    }


    private void setUpGraphFilterKeysRec() {
        binding.recFilter.setItemAnimator(new DefaultItemAnimator());
        binding.recFilter.setHasFixedSize(true);
        binding.recFilter.setAdapter(new GraphFilterKeysAdapter(AppUtils.getGraphFilterKeysList(), this));
    }

    private void setUpDemoGraphView() {
        HIChartView chartView = binding.chartView;
        //chartView.theme = "dark-unica";

        chartView.plugins = new ArrayList<>(Arrays.asList("series-label"));

        HIOptions options = new HIOptions();

        HITitle title = new HITitle();
        title.setText("Wind speed during two days");
        options.setTitle(title);

        HISubtitle subtitle = new HISubtitle();
        subtitle.setText("May 31 and and June 1, 2015 at two locations in Vik i Sogn, Norway");
        options.setSubtitle(subtitle);

        HIXAxis xAxis = new HIXAxis();
        xAxis.setType("datetime");
        xAxis.setLabels(new HILabels());
        xAxis.getLabels().setOverflow("justify");
        options.setXAxis(new ArrayList<HIXAxis>() {{
            add(xAxis);
        }});

        HIYAxis yAxis = new HIYAxis();
        yAxis.setTitle(new HITitle());
        yAxis.getTitle().setText("Wind speed (m/s)");
        yAxis.setMinorGridLineWidth(0);
        yAxis.setGridLineWidth(0);
        yAxis.setAlternateGridColor(null);

        HIPlotBands plotBands1 = new HIPlotBands();
        plotBands1.setFrom(0.3);
        plotBands1.setTo(1.5);
        //plotBands1.setColor(HIColor.initWithRGBA(68, 170, 213, 0.1));
        plotBands1.setLabel(new HILabel());
        plotBands1.getLabel().setText("Light air");
        plotBands1.getLabel().setStyle(new HICSSObject());
        plotBands1.getLabel().getStyle().setColor("606060");

        HIPlotBands plotBands2 = new HIPlotBands();
        plotBands2.setFrom(1.5);
        plotBands2.setTo(3.3);
        // plotBands2.setColor(HIColor.initWithRGBA(0, 0, 0, 0));
        plotBands2.setLabel(new HILabel());
        plotBands2.getLabel().setText("Light breeze");
        plotBands2.getLabel().setStyle(new HICSSObject());
        plotBands2.getLabel().getStyle().setColor("#606060");

        HIPlotBands plotBands3 = new HIPlotBands();
        plotBands3.setFrom(3.3);
        plotBands3.setTo(5.5);
        //plotBands3.setColor(HIColor.initWithRGBA(68, 170, 213, 0.1));
        plotBands3.setLabel(new HILabel());
        plotBands3.getLabel().setText("Gentle breeze");
        plotBands3.getLabel().setStyle(new HICSSObject());
        plotBands3.getLabel().getStyle().setColor("#606060");

        HIPlotBands plotBands4 = new HIPlotBands();
        plotBands4.setFrom(5.5);
        plotBands4.setTo(8);
        //plotBands4.setColor(HIColor.initWithRGBA(0, 0, 0, 0));
        plotBands4.setLabel(new HILabel());
        plotBands4.getLabel().setText("Moderate breeze");
        plotBands4.getLabel().setStyle(new HICSSObject());
        plotBands4.getLabel().getStyle().setColor("#606060");

        HIPlotBands plotBands5 = new HIPlotBands();
        plotBands5.setFrom(8);
        plotBands5.setTo(11);
        //plotBands5.setColor(HIColor.initWithRGBA(68, 170, 213, 0.1));
        plotBands5.setLabel(new HILabel());
        plotBands5.getLabel().setText("Fresh breeze");
        plotBands5.getLabel().setStyle(new HICSSObject());
        plotBands5.getLabel().getStyle().setColor("#606060");

        HIPlotBands plotBands6 = new HIPlotBands();
        plotBands6.setFrom(11);
        plotBands6.setTo(14);
        //plotBands6.setColor(HIColor.initWithRGBA(0, 0, 0, 0));
        plotBands6.setLabel(new HILabel());
        plotBands6.getLabel().setText("Strong breeze");
        plotBands6.getLabel().setStyle(new HICSSObject());
        plotBands6.getLabel().getStyle().setColor("#606060");

        HIPlotBands plotBands7 = new HIPlotBands();
        plotBands7.setFrom(14);
        plotBands7.setTo(15);
        // plotBands7.setColor(HIColor.initWithRGBA(68, 170, 213, 0.1));
        plotBands6.setLabel(new HILabel());
        plotBands6.getLabel().setText("High wind");
        plotBands6.getLabel().setStyle(new HICSSObject());
        plotBands6.getLabel().getStyle().setColor("#606060");

        HIPlotBands[] plotBandsList = new HIPlotBands[]{plotBands1, plotBands2, plotBands3, plotBands4, plotBands5, plotBands6, plotBands7};
        yAxis.setPlotBands(new ArrayList<>(Arrays.asList(plotBandsList)));
        options.setYAxis(new ArrayList<HIYAxis>() {{
            add(yAxis);
        }});

        HITooltip tooltip = new HITooltip();
        tooltip.setValueSuffix(" m/s");
        options.setTooltip(tooltip);

        HIPlotOptions plotOptions = new HIPlotOptions();
        plotOptions.setSpline(new HISpline());
        plotOptions.getSpline().setLineWidth(4);
        plotOptions.getSpline().setStates(new HIStates());
        plotOptions.getSpline().getStates().setHover(new HIHover());
        plotOptions.getSpline().getStates().getHover().setLineWidth(5);
        plotOptions.getSpline().setMarker(new HIMarker());
        plotOptions.getSpline().getMarker().setEnabled(false);
        plotOptions.getSpline().setPointInterval(3600000);
        plotOptions.getSpline().setPointStart(Date.UTC(2015, 4, 31, 0, 0, 0));
        options.setPlotOptions(plotOptions);

        HISpline series1 = new HISpline();
        series1.setName("Hestavollane");
        Number[] series1_data = new Number[]{0.2, 0.8, 0.8, 0.8, 1, 1.3, 1.5, 2.9, 1.9, 2.6, 1.6, 3, 4, 3.6, 4.5, 4.2, 4.5, 4.5, 4, 3.1, 2.7, 4, 2.7, 2.3, 2.3, 4.1, 7.7, 7.1, 5.6, 6.1, 5.8, 8.6, 7.2, 9, 10.9, 11.5, 11.6, 11.1, 12, 12.3, 10.7, 9.4, 9.8, 9.6, 9.8, 9.5, 8.5, 7.4, 7.6};
        series1.setData(new ArrayList<>(Arrays.asList(series1_data)));

        HISpline series2 = new HISpline();
        series2.setName("Vik");
        Number[] series2_data = new Number[]{0, 0, 0.6, 0.9, 0.8, 0.2, 0, 0, 0, 0.1, 0.6, 0.7, 0.8, 0.6, 0.2, 0, 0.1, 0.3, 0.3, 0, 0.1, 0, 0, 0, 0.2, 0.1, 0, 0.3, 0, 0.1, 0.2, 0.1, 0.3, 0.3, 0, 3.1, 3.1, 2.5, 1.5, 1.9, 2.1, 1, 2.3, 1.9, 1.2, 0.7, 1.3, 0.4, 0.3};
        series2.setData(new ArrayList<>(Arrays.asList(series2_data)));

        options.setSeries(new ArrayList<>(Arrays.asList(series1, series2)));

        HINavigation navigation = new HINavigation();
        navigation.setMenuItemStyle(new HICSSObject());
        navigation.getMenuItemStyle().setFontSize("10px");
        options.setNavigation(navigation);

        chartView.setOptions(options);
    }

    @Override
    public void onClickItem(Object obj) {
        String days = (String) obj;
        String key;
        if (days.equalsIgnoreCase(AppConstant.SORT_7D)) {
            key = "7";
        } else if (days.equalsIgnoreCase(AppConstant.SORT_14D)) {
            key = "14";
        } else if (days.equalsIgnoreCase(AppConstant.SORT_30D)) {
            key = "30";
        } else if (days.equalsIgnoreCase(AppConstant.SORT_6MONTH)) {
            key = "180";
        } else if (days.equalsIgnoreCase(AppConstant.SORT_1Y)) {
            key = "365";
        } else if (days.equalsIgnoreCase(AppConstant.SORT_ALL_TIME)) {
            key = "max";
        } else key = "1";
        init(key);
    }
}
