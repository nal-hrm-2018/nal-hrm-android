package com.dal.hrm_management.models;

/**
 * Created by Luu Ngoc Lan on 27-Jul-18.
 */

public class BarEntry {
    private float AxisX;
    private float AxisY;

    public BarEntry(float axisX, float axisY) {
        AxisX = axisX;
        AxisY = axisY;
    }

    public float getAxisX() {
        return AxisX;
    }

    public void setAxisX(float axisX) {
        AxisX = axisX;
    }

    public float getAxisY() {
        return AxisY;
    }

    public void setAxisY(float axisY) {
        AxisY = axisY;
    }
}
