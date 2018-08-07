package com.dal.hrm_management.views.absence;

import android.app.DatePickerDialog;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import com.dal.hrm_management.R;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class Form_absence extends AppCompatActivity implements View.OnClickListener{
    String[] LOAINGHI = {"Nghỉ phép", "Nghỉ không lương", "Nghỉ bệnh"};
    String[] THOIGIANNGHI = {"Cả ngày","Sáng","Chiều"};
    Toolbar toolbar;
    EditText edt_tuNgay,edt_denNgay;
    ImageButton imb_tuNgay,imb_denNgay;
    private int mYear, mMonth, mDay;
    MaterialBetterSpinner loaiNghi,thoiGianNghi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_absence);
        initView();
        initData();
        imb_tuNgay.setOnClickListener(this);
        imb_denNgay.setOnClickListener(this);



    }

    private void initData() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, LOAINGHI);
        loaiNghi.setAdapter(adapter);
        adapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, THOIGIANNGHI);
        thoiGianNghi.setAdapter(adapter);
    }


    private void initView() {
        toolbar = findViewById(R.id.form_absence_toolbar);
        edt_tuNgay = findViewById(R.id.form_absence_edt_tuNgay);
        edt_denNgay = findViewById(R.id.form_absence_edt_denNgay);
        imb_tuNgay = findViewById(R.id.form_absence_imb_tuNgay);
        imb_denNgay = findViewById(R.id.form_absence_imb_denNgay);
        loaiNghi = findViewById(R.id.form_absence_spinner_loaiNghi);
        thoiGianNghi = findViewById(R.id.form_absence_spinner_thoiGianNghi);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.form_absence_imb_tuNgay:
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                edt_tuNgay.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;
            case R.id.form_absence_imb_denNgay:
                Calendar c2 = Calendar.getInstance();
                mYear = c2.get(Calendar.YEAR);
                mMonth = c2.get(Calendar.MONTH);
                mDay = c2.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog1 = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                edt_denNgay.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog1.show();
                break;
        }

    }
}
