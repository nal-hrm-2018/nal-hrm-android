package com.dal.hrm_management.views.manage_absence.Hr.holiday;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.dal.hrm_management.R;

import java.util.Calendar;

public class FormHolidayActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edt_tenNgayNghi,edt_ngayBatDau,edt_ngayKetThuc,edt_ghiChu;
    private Spinner spinner_loaiNghi;
    private ImageButton imb_ngayBatDau,imb_ngayKetThuc;
    private Button btn_submit;
    private Toolbar toolbar;

    private int mYear, mMonth, mDay;
    private Calendar calendar;
    private DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_holiday);
        setTitle(getString(R.string.holiday_new));
        initView();
        supportToolbar();
        setDataSpinner();
        setEvent();
    }

    private void setEvent() {
        calendar =Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        imb_ngayBatDau.setOnClickListener(this);
        imb_ngayKetThuc.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void setDataSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.form_holiday_arr_loaiNghi, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_loaiNghi.setAdapter(adapter);
    }

    private void supportToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.form_holiday_toolbar);
        edt_tenNgayNghi =(EditText) findViewById(R.id.form_holiday_edt_tenNgayNghi);
        edt_ngayBatDau =(EditText) findViewById(R.id.form_holiday_edt_ngayBatDau);
        edt_ngayKetThuc =(EditText) findViewById(R.id.form_holiday_edt_ngayKetThuc);
        edt_ghiChu =(EditText) findViewById(R.id.form_holiday_edt_ghiChu);
        spinner_loaiNghi =(Spinner) findViewById(R.id.form_holiday_spinner_loaiNghi);
        imb_ngayBatDau = (ImageButton) findViewById(R.id.form_holiday_imb_ngayBatDau);
        imb_ngayKetThuc = (ImageButton)findViewById(R.id.form_holiday_imb_ngayKetthuc);
        btn_submit =(Button) findViewById(R.id.form_holiday_btn_submit);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.form_holiday_imb_ngayBatDau:
                datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                edt_ngayBatDau.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;
            case R.id.form_holiday_imb_ngayKetthuc:
                datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                edt_ngayKetThuc.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;
        }
    }
}
