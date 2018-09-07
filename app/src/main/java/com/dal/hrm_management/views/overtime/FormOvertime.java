package com.dal.hrm_management.views.overtime;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.dal.hrm_management.R;
import com.dal.hrm_management.utils.ValidationDateTime;
import com.szagurskii.patternedtextwatcher.PatternedTextWatcher;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FormOvertime extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = FormOvertime.class.getSimpleName();
    private EditText edtDate,edtFromTime,edtToTime,edtTotalTime,edtReason;
    private ImageButton imbDate,imbFromTime,imbToTime;
    private Button btnSubmit;
    private Spinner spnProject;

    private Calendar c = Calendar.getInstance();
    private int mYear,mMonth,mDay,hour,minute;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_overtime);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        spnProject = findViewById(R.id.spnFormOvertimeAct_Project);
        edtDate = findViewById(R.id.edtFormOvertimeAct_Date);
        edtFromTime = findViewById(R.id.edtFormOvertimeAct_FromTime);
        edtToTime = findViewById(R.id.edtFormOvertimeAct_ToTime);
        edtTotalTime = findViewById(R.id.edtFormOvertimeAct_TotalTime);
        edtReason = findViewById(R.id.edtFormOvertimeAct_Reason);
        btnSubmit = findViewById(R.id.btnFormOvertimeAct_Submit);
        imbDate = findViewById(R.id.imbFormOvertimeAct_Date);
        imbFromTime = findViewById(R.id.imbFormOvertimeAct_FromTime);
        imbToTime = findViewById(R.id.imbFormOvertimeAct_ToTime);

        imbDate.setOnClickListener(this);
        imbFromTime.setOnClickListener(this);
        imbToTime.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

        getCurrentDateTime();
    }

    private void getCurrentDateTime() {
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnFormOvertimeAct_Submit:
                View v = checkValidate();
                if (v ==null){
                    //call api
                    Log.d(TAG,"pass validation");
                }else{
                    v.requestFocus();
                }
                break;
            case R.id.imbFormOvertimeAct_Date:
                datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String year="",month="",day="";
                        year = String.valueOf(i);
                        if (i1 < 9) month="0";
                        month += String.valueOf(i1+1);
                        if (i2 <= 9) day="0";
                        day += String.valueOf(i2);
                        edtDate.setText(day+month+year);
                        Log.d(TAG,"edtDate: " + edtDate.getText().toString());
                        edtDate.requestFocus();
                    }
                },mYear,mMonth,mDay);
                datePickerDialog.show();
                break;
            case R.id.imbFormOvertimeAct_FromTime:
                timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String sh="",sm="";
                        if (selectedHour <= 9) sh="0";
                        sh +=String.valueOf(selectedHour);
                        if (selectedMinute <=9) sm="0";
                        sm +=String.valueOf(selectedMinute);
                        edtFromTime.setText( sh + sm);
                        edtFromTime.requestFocus();
                    }
                }, hour, minute, true);//Yes 24 hour time
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();
                break;
            case R.id.imbFormOvertimeAct_ToTime:
                timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String sh="",sm="";
                        if (selectedHour <= 9) sh="0";
                        sh +=String.valueOf(selectedHour);
                        if (selectedMinute <=9) sm="0";
                        sm +=String.valueOf(selectedMinute);
                        edtToTime.setText( sh + sm);
                        edtToTime.requestFocus();
                    }
                }, hour, minute, true);//Yes 24 hour time
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();
                break;
                default:break;
        }
    }

    private View checkValidate() {
        edtDate.setError(null);
        edtFromTime.setError(null);
        edtToTime.setError(null);
        edtTotalTime.setError(null);
        //Check date
        try {
            String[] split = edtDate.getText().toString().split("-");
            int day = Integer.parseInt(split[0]);
            int month = Integer.parseInt(split[1]);
            int year = Integer.parseInt(split[2]);
            if (!ValidationDateTime.checkDay(day, month, year)) {
                edtDate.setError(getString(R.string.error_invalid_date));
                return edtDate;
            }
        }catch (Exception ex){
            ex.printStackTrace();
            edtDate.setError(getString(R.string.error_invalid_date));
            return edtDate;
        }
        //Check time
        try {
            String[] split = edtFromTime.getText().toString().split(":");
            int hour = Integer.parseInt(split[0]);
            int minute = Integer.parseInt(split[1]);
            if (!ValidationDateTime.checkTime(hour,minute)){
                edtFromTime.setError(getString(R.string.error_invalid_time));
                return edtFromTime;
            }
        }catch (Exception ex) {
            ex.printStackTrace();
            edtFromTime.setError(getString(R.string.error_invalid_time));
            return edtFromTime;
        }


        try {
            String[] split = edtToTime.getText().toString().split(":");
            hour = Integer.parseInt(split[0]);
            minute = Integer.parseInt(split[1]);
            if (!ValidationDateTime.checkTime(hour, minute)) {
                edtToTime.setError(getString(R.string.error_invalid_time));
                return edtToTime;
            }
        }catch (Exception ex) {
            ex.printStackTrace();
            edtToTime.setError(getString(R.string.error_invalid_time));
            return edtToTime;
        }
        //Check actual
        try {
            double total = Double.parseDouble(edtTotalTime.getText().toString());
            if (total > 24 && total < 0) {
                edtTotalTime.setError(getString(R.string.error_invalid_time));
                return edtTotalTime;
            }
        }catch (Exception ex) {
            ex.printStackTrace();
            edtTotalTime.setError(getString(R.string.error_invalid_time));
            return edtTotalTime;
        }
        return null;
    }
}
