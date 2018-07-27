package com.dal.hrm_management.views.employee;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.dal.hrm_management.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ValueChartEmployeeFragment extends Fragment implements OnChartValueSelectedListener {

    private Spinner spinner;
    private BarChart barChart;
    private PieChart pieChart;
    private List<BarEntry> entries;

    public ValueChartEmployeeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(false);
        View view = inflater.inflate(R.layout.fragment_value_chart_employee, container, false);
        initUI(view);
        initDataBarChart();
        addEventUi();
        return view;
    }

    private void addEventUi() {
        barChart.setOnChartValueSelectedListener(this);
    }

    private void initDataBarChart() {
        entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 30f));
        entries.add(new BarEntry(1f, 80f));
        entries.add(new BarEntry(2f, 60f));
        entries.add(new BarEntry(3f, 50f));
        // gap of 2f
        entries.add(new BarEntry(5f, 70f));
        entries.add(new BarEntry(6f, 60f));
        entries.add(new BarEntry(7f, 60f));
        entries.add(new BarEntry(8f, 120f));
        entries.add(new BarEntry(9f, 50f));
        entries.add(new BarEntry(10f, 50f));
        entries.add(new BarEntry(11f, 50f));
        entries.add(new BarEntry(12f, 60f));
        BarDataSet set = new BarDataSet(entries, "Value ");
        BarData data = new BarData(set);
        data.setBarWidth(0.75f); // set custom bar width
        barChart.setData(data);
        barChart.setDrawValueAboveBar(true);
        barChart.setFitBars(true); // make the x-axis fit exactly all bars
        barChart.invalidate(); // refresh
        barChart.setMaxVisibleValueCount(60);
        barChart.setPinchZoom(false);

        //set XAxis
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.RED);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);


        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setTextSize(12f); // set the text size
        yAxis.setAxisMinimum(0f); // start at zero
        yAxis.setAxisMaximum(150f); // the axis maximum is 100
        yAxis.setTextColor(Color.RED);
        yAxis.setGranularity(1f); // interval 1
        yAxis.setLabelCount(6, true); // force 6 labels
    }

    private void initUI(View view) {
        spinner = (Spinner) view.findViewById(R.id.spinner);
        barChart = (BarChart) view.findViewById(R.id.chart);
        pieChart = (PieChart) view.findViewById(R.id.pieChart);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_search);
        item.setVisible(false);
    }
}

