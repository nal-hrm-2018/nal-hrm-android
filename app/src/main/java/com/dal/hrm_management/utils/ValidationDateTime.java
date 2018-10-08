package com.dal.hrm_management.utils;

public class ValidationDateTime {
    public static boolean namNhuan(int year) {
        return year % 4 == 0 && year % 100 != 0;
    }

    public static boolean checkDay(int day, int month, int year) {
        boolean nhuan = namNhuan(year);
        int max_day = 30;
        if (month > 12 || month < 1) return false;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                max_day = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                max_day = 30;
                break;
            case 2:
                if (nhuan) max_day = 29;
                else max_day = 28;
                break;
            default:
                max_day = 30;
        }
        if (day < 0 || day > max_day) return false;
        return true;
    }

    public static boolean checkTime(int hour, int minute) {
        return hour >= 0 && hour < 24 && minute >= 0 && minute < 60;
    }

    /**
     * Date1 >= date2 : return true;
     *
     * @param day1
     * @param month1
     * @param year1
     * @param day2
     * @param month1
     * @param year2
     * @return
     */
    public static boolean compareDate(int day1, int month1, int year1, int day2, int month2, int year2) {
        if (year1 > year2) {
            return true;
        } else if (year1 == year2) {
            if (month1 > month2) {
                return true;
            } else if (month1 == month2) {
                if (day1 >= day2) {
                    return true;
                }
                return false;
            } else return false;
        } else {
            return false;
        }
    }

    /**
     * @param beginTime with format hh:mm:ss. Ex: 18:48:00
     * @param endTime   with format hh:mm:ss. Ex: 23:48:00
     * @return  5.0
     */
    public static Double getHoursDistanceFromTimeBeginToEnd(String beginTime, String endTime) {
        int minutesBegin = convertTimeToMinutes(beginTime);
        int minutesEnd = convertTimeToMinutes(endTime);
        return (minutesEnd-minutesBegin)*1.0/60;
    }

    /**
     * convert time to minutes
     * @param time format hh:mm:ss
     * @return h*60+m
     */
    public static int convertTimeToMinutes(String time) {
        return Integer.parseInt(time.split(":")[0])*60+Integer.parseInt(time.split(":")[1]);
    }
}
