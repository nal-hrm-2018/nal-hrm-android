package com.dal.hrm_management.utils;

/**
 * Created by Luu Ngoc Lan on 04-Sep-18.
 */

public class StringUtils {
    /**
     * convert string date with format yyyy-mm-dd to dd-mm-yyyy
     *
     * @param st
     * @return
     */
    public static String yyyy_mm_ddTodd_mm_yyyy(String st) {

        return st.split("[-/]")[0].length() == 4
                ? st.split("[-/]")[2] + "-" + st.split("[-/]")[1] + "-" + st.split("[-/]")[0]
                : st;
    }

    public static String toUpperCaseFirstChar(String str){
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
