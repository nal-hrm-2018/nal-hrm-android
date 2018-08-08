package com.dal.hrm_management.views.manage_absence;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import com.dal.hrm_management.R;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.Calendar;

public class form_holiday extends AppCompatActivity implements View.OnClickListener {
    private EditText edt_tenNgayNghi,edt_ngayBatDau,edt_ngayKetThuc,edt_ghiChu;
    private MaterialBetterSpinner spinner_loaiNghi;
    private ImageButton imb_ngayBatDau,imb_ngayKetThuc;
    private Button btn_submit;
    private Toolbar toolbar;
    private String[] loaiNghi = getResources().getStringArray(R.array.form_holiday_arr_loaiNghi);

    private int mYear, mMonth, mDay;
    private Calendar calendar;
    private DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_holiday);
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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, loaiNghi);
        spinner_loaiNghi.setAdapter(adapter);
    }

    private void supportToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initView() {
        toolbar = findViewById(R.id.form_holiday_toolbar);
        edt_tenNgayNghi = findViewById(R.id.form_holiday_edt_tenNgayNghi);
        edt_ngayBatDau = findViewById(R.id.form_holiday_edt_ngayBatDau);
        edt_ngayKetThuc = findViewById(R.id.form_holiday_edt_ngayKetThuc);
        edt_ghiChu = findViewById(R.id.form_holiday_edt_ghiChu);
        spinner_loaiNghi = findViewById(R.id.form_holiday_spinner_loaiNghi);
        imb_ngayBatDau = findViewById(R.id.form_holiday_imb_ngayBatDau);
        imb_ngayKetThuc = findViewById(R.id.form_holiday_imb_ngayKetthuc);
        btn_submit = findViewById(R.id.form_holiday_btn_submit);
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
