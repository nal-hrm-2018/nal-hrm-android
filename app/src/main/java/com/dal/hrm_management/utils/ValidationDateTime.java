package com.dal.hrm_management.utils;

public class ValidationDateTime {
    public static boolean namNhuan(int year){
        return year%4==0 && year%100!=0;
    }
    public static boolean checkDay(int day,int month,int year){
        boolean nhuan = namNhuan(year);
        int max_day=30;
        if (month > 12 || month < 1) return false;
        switch (month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                max_day =31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                max_day =30;
            case 2:
                if (nhuan) max_day =29;
                else max_day=28;
                break;
            default:max_day=30;
        }
        if (day < 0 || day > max_day ) return false;
        return true;
    }
    public static boolean checkTime(int hour,int minute){
        return hour>=0 && hour <24 && minute>=0 && minute<60;
    }
}
