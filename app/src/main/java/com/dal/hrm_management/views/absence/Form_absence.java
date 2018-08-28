package com.dal.hrm_management.views.absence;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dal.hrm_management.R;
import com.dal.hrm_management.presenters.absence.AbsencePresenter;
import com.dal.hrm_management.presenters.absence.IAbsencePresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.RequestBody;

public class Form_absence extends AppCompatActivity implements View.OnClickListener,IAbsenceFormActivity{
    private final String TAG = Form_absence.class.getSimpleName();
    String[] LOAINGHI = {"annual leave", "unpaid leave", "maternity leave","marriage leave","bereavement leave","subtract salary","insurance date"};
    String[] THOIGIANNGHI = {"All day","Morning","Afternoon"};
    Toolbar toolbar;
    Button btnSubmit;
    TextView edt_tuNgay,edt_denNgay;
    EditText edtReason,edtNote;
    private int mYear, mMonth, mDay;
    AbsencePresenter absencePresenter;
    Spinner loaiNghi,thoiGianNghi;
    //time
    Calendar c = Calendar.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_absence);
        initView();
        mapMVP();
        initData();
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
        loaiNghi.setGravity(View.TEXT_ALIGNMENT_CENTER);
        loaiNghi.setAdapter(adapter);
        adapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, THOIGIANNGHI);
        thoiGianNghi.setGravity(View.TEXT_ALIGNMENT_CENTER);
        thoiGianNghi.setAdapter(adapter);
        //init time

        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        edt_tuNgay.setText(mYear+ "-"+ (mMonth + 1)  + "-" +mDay);
        edt_denNgay.setText(mYear+ "-"+ (mMonth + 1)  + "-" +mDay);
    }

    private void initView() {
        toolbar =(Toolbar) findViewById(R.id.form_absence_toolbar);
        edt_tuNgay = findViewById(R.id.form_absence_edt_tuNgay);
        edt_tuNgay.setOnClickListener(this);
        edt_denNgay =findViewById(R.id.form_absence_edt_denNgay);
        edt_denNgay.setOnClickListener(this);
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
                View check = checkValidateForm();
                if (check != null){
                    check.requestFocus();
                }else {
                    String absenceType = loaiNghi.getSelectedItem().toString();
                    absenceType.replace(" ", "_");
                    int absenceTypeId = getAbsenceTypeId(absenceType);
                    Log.d(TAG + " absenceTypeId: ", String.valueOf(absenceTypeId));
                    String absenceTime = thoiGianNghi.getSelectedItem().toString();
                    int absenceTimeId = getAbsenceTimeId(absenceTime);
                    Log.d(TAG + " absenceTimeId: ", String.valueOf(absenceTimeId));
                    Log.d(TAG + " startDate: ", edt_tuNgay.getText().toString());
                    Log.d(TAG + " EndDate: ", edt_denNgay.getText().toString());
                    JSONObject jsonObject = new JSONObject();
                    try {
                        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
                        jsonObject.put("absenceTypeId", absenceTypeId);
                        jsonObject.put("fromDate", edt_tuNgay.getText().toString());
                        jsonObject.put("toDate", edt_denNgay.getText().toString());
                        jsonObject.put("reason", edtReason.getText().toString());
                        jsonObject.put("description", edtNote.getText().toString());
                        jsonObject.put("absenceTimeId", absenceTimeId);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    RequestBody body = RequestBody.create(
                            okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
                    absencePresenter.addAbsence(body);
                }
                break;
            case R.id.form_absence_edt_tuNgay:
                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                edt_tuNgay.setText(year+ "-"+ (monthOfYear + 1)  + "-" +dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;
            case R.id.form_absence_edt_denNgay:


                DatePickerDialog datePickerDialog1 = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                edt_denNgay.setText(year+ "-"+ (monthOfYear + 1)  + "-" +dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog1.show();
                break;
        }
    }

    private View checkValidateForm() {
        View focusView = null;
        //edt tu ngay
        if (TextUtils.isEmpty(edt_tuNgay.getText().toString())){
            edt_tuNgay.setError(getResources().getString(R.string.error_field_is_not_empty));
            focusView = edt_tuNgay;
        } else if (TextUtils.isEmpty(edt_denNgay.getText().toString())){
            edt_denNgay.setError(getResources().getString(R.string.error_field_is_not_empty));
            focusView = edt_denNgay;
        }
        return focusView;
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
        Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void Failure() {
        Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_SHORT).show();
    }
}
