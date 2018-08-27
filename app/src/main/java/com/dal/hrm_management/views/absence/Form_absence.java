package com.dal.hrm_management.views.absence;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.dal.hrm_management.R;
import com.dal.hrm_management.models.absence.addAbsence.AbsenceForm;
import com.dal.hrm_management.presenters.absence.AbsencePresenter;
import com.dal.hrm_management.presenters.absence.IAbsencePresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class Form_absence extends AppCompatActivity implements View.OnClickListener,IAbsenceFormActivity{
    private final String TAG = Form_absence.class.getSimpleName();
    String[] LOAINGHI = {"annual leave", "unpaid leave", "maternity leave","marriage leave","bereavement leave","subtract salary","insurance date"};
    String[] THOIGIANNGHI = {"All day","Morning","Afternoon"};
    Toolbar toolbar;
    Button btnSubmit;
    EditText edt_tuNgay,edt_denNgay,edtReason,edtNote;
    ImageButton imb_tuNgay,imb_denNgay;
    private int mYear, mMonth, mDay;
    AbsencePresenter absencePresenter;
    Spinner loaiNghi,thoiGianNghi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_absence);
        initView();
        mapMVP();
        initData();
        imb_tuNgay.setOnClickListener(this);
        imb_denNgay.setOnClickListener(this);
    }

    private void mapMVP() {
        absencePresenter = new AbsencePresenter(this);
    }

    private void initData() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle(getString(R.string.absence_add));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, LOAINGHI);
        loaiNghi.setAdapter(adapter);
        adapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, THOIGIANNGHI);
        thoiGianNghi.setAdapter(adapter);
    }


    private void initView() {
        toolbar =(Toolbar) findViewById(R.id.form_absence_toolbar);
        edt_tuNgay =(EditText) findViewById(R.id.form_absence_edt_tuNgay);
        edt_denNgay =(EditText) findViewById(R.id.form_absence_edt_denNgay);
        imb_tuNgay =(ImageButton) findViewById(R.id.form_absence_imb_tuNgay);
        imb_denNgay =(ImageButton) findViewById(R.id.form_absence_imb_denNgay);
        loaiNghi =(Spinner) findViewById(R.id.form_absence_spinner_loaiNghi);
        thoiGianNghi =(Spinner) findViewById(R.id.form_absence_spinner_thoiGianNghi);
        btnSubmit = findViewById(R.id.form_absence_btn_submit);
        btnSubmit.setOnClickListener(this);
        edtReason = findViewById(R.id.form_absence_edt_lydo);
        edtNote =findViewById(R.id.form_absence_edt_note);
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
        Log.d(TAG, String.valueOf(view.getId()));
        switch (view.getId()){
            case R.id.form_absence_btn_submit:
                String absenceType = loaiNghi.getSelectedItem().toString();
                absenceType.replace(" ","_");
                int absenceTypeId = getAbsenceTypeId(absenceType);
                Log.d(TAG + " absenceTypeId: ", String.valueOf(absenceTypeId));
                String absenceTime = thoiGianNghi.getSelectedItem().toString();
                int absenceTimeId = getAbsenceTimeId(absenceTime);
                Log.d(TAG + " absenceTimeId: ", String.valueOf(absenceTimeId));
                Log.d(TAG + " startDate: ",edt_tuNgay.getText().toString());
                Log.d(TAG + " EndDate: ",edt_denNgay.getText().toString());
                AbsenceForm absenceForm = new AbsenceForm(absenceTypeId,
                        edt_tuNgay.getText().toString(),
                        edt_denNgay.getText().toString(),
                        edtReason.getText().toString(),
                        edtNote.getText().toString(),
                        absenceTimeId);
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("absenceTypeId",absenceTypeId);
                    jsonObject.put("fromDate",edt_tuNgay.getText().toString());
                    jsonObject.put("toDate",edt_denNgay.getText().toString());
                    jsonObject.put("reason",edtReason.getText().toString());
                    jsonObject.put("description",edtNote.getText().toString());
                    jsonObject.put("absenceTimeId",absenceTimeId);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                absencePresenter.addAbsence(jsonObject.toString());
                break;
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

    private int getAbsenceTimeId(String absenceTime) {
        switch (absenceTime){
            case "All day": return 1;
            case "Morning": return 2;
            default: return 3;
        }
    }

    private int getAbsenceTypeId(String absenceType) {
        switch (absenceType){
            case "annual_leave": return 1;
            case "unpaid_leave": return 2;
            case "maternity_leave": return 3;
            case "marriage_leave": return 4;
            case "bereavement_leave": return 5;
            case "subtract_salary_date": return 6;
            case "insurance_date": return 7;
        }
        return 1;
    }

    @Override
    public void Success() {

    }

    @Override
    public void Failure() {

    }
}
