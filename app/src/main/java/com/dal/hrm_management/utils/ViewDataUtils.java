package com.dal.hrm_management.utils;

import android.widget.TextView;

import com.dal.hrm_management.R;

public class ViewDataUtils {

    /**
     *  check data have value or null, set default "--" value if data null
     * @param view
     * @param data
     */
    public static void setDataToView(TextView view, Object data){
        if(data!=null) {
            view.setText(data.toString());
        } else {
            view.setText(R.string.infor_null);
        }
    }

    /**
     * check date not null, after: date yyyy-mm-dd will convert to dd-mm-yyyy
     * @param view
     * @param data
     */
    public static void setDataDateToView(TextView view, Object data){
        if(data!=null) {
            view.setText(StringUtils.yyyy_mm_ddTodd_mm_yyyy(data.toString()));
        } else {
            view.setText(R.string.infor_null);
        }
    }

    /**
     *
     * @param view
     * @param data
     */
    public static void setDataTimeWithHH_MM(TextView view, Object data) {
        if (data != null) {
            view.setText(StringUtils.formatTimehh_mm(data.toString()));
        } else {
            view.setText(R.string.infor_null);
        }
    }

}
