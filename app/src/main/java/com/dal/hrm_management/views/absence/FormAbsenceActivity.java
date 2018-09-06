package com.dal.hrm_management.views.absence;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.dal.hrm_management.R;
import com.dal.hrm_management.models.absence.Absence;
import com.dal.hrm_management.models.absence.addAbsence.TypeAbsence;
import com.dal.hrm_management.models.manageAbsence.hr.ListAbsenceForHr;
import com.dal.hrm_management.presenters.absence.AbsencePresenter;
import com.dal.hrm_management.utils.ValidationDateTime;
import com.dal.hrm_management.utils.VariableUltils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.RequestBody;

public class FormAbsenceActivity extends AppCompatActivity implements View.OnClickListener, IAbsenceFormActivity {
    private final String TAG = FormAbsenceActivity.class.getSimpleName();
    String[] THOIGIANNGHI = {"All day", "Morning", "Afternoon"};
    private ImageButton imbFrom,imbTo;
    Button btnSubmit;
    EditText edt_denNgay,edt_tuNgay;
    EditText edtReason, edtNote;
    private int mYear, mMonth, mDay;
    AbsencePresenter absencePresenter;
    Spinner spnloaiNghi, spnthoiGianNghi;
    private CheckBox cb_nghiBoSung;
    Calendar c = Calendar.getInstance();
    //List type absence
    List<TypeAbsence> listAbsence;
    public enum StatusAbsences{enum_Add,enum_Edit;};
    StatusAbsences statusAbsences = StatusAbsences.enum_Add;
    private Integer idAbsence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_absence);
        mapMVP();
        initView();
        initData();

    }

    private void getExtra() {
        //Nếu như add form absence thì absence khác null còn ko thì absence = null nếu null thì xét trường hợp khác
        Absence absence = (Absence) getIntent().getSerializableExtra(VariableUltils.KEY_PUT_ABSENCE_INTENT);
        if (absence !=null){
            setTitle(getResources().getString(R.string.text_title_edit_absence));
            edt_tuNgay.setText(absence.getFromDate());
            edt_denNgay.setText(absence.getToDate());
            spnloaiNghi.setSelection(absence.getAbsenceType().getIdAbsenceType()-1);
            //array start with index = 0
            spnthoiGianNghi.setSelection(absence.getAbsenceTime().getIdAbsenceTime()-1);
            edtReason.setText(absence.getReason());
            edtNote.setText(absence.getDescription());
            edt_tuNgay.setOnClickListener(null);
            edt_denNgay.setOnClickListener(null);
            spnloaiNghi.setEnabled(false);
            spnthoiGianNghi.setEnabled(false);
            edtReason.setEnabled(false);
            edtNote.setEnabled(false);
            btnSubmit.setVisibility(View.GONE);
        }

        else{
            ListAbsenceForHr absenceForHr = (ListAbsenceForHr) getIntent().getSerializableExtra(VariableUltils.KEY_PUT_EXTRA_EDIT_ABSENCE);
            if (absenceForHr !=null){
                setTitle(absenceForHr.getNameEmployee());
                String[] split = absenceForHr.getFromDate().split("-");
                String ngay = split[2] + split[1]+split[0];
                Log.d(TAG,"-------------------------------------");
                Log.d(TAG,"ngay" +absenceForHr.getFromDate());
                Log.d(TAG,"ngay" +ngay);
                edt_tuNgay.setText(ngay);
                split = absenceForHr.getToDate().split("-");
                ngay = split[2] + split[1]+split[0];
                edt_denNgay.setText(ngay);
                spnloaiNghi.setSelection(absenceForHr.getAbsenceType().getIdAbsenceType()-1);
                //array start with index = 0
                spnthoiGianNghi.setSelection(absenceForHr.getAbsenceTime().getIdAbsenceTime()-1);
                edtReason.setText(absenceForHr.getReason());
                edtNote.setText(absenceForHr.getDescription());
                idAbsence = absenceForHr.getIdAbsences();
                statusAbsences = StatusAbsences.enum_Edit;
            }
        }

    }

    private void mapMVP() {
        absencePresenter = new AbsencePresenter(this);
    }

    private void initData() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle(getString(R.string.absence_add));
        absencePresenter.getTypeAbsence();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, THOIGIANNGHI);
        spnthoiGianNghi.setAdapter(adapter);
        getSetCurrentDay();

    }

    private void getSetCurrentDay() {
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        String syear="",smonth="",sday="";
        syear = String.valueOf(mYear);
        if (mMonth < 9) smonth="0";
        smonth += String.valueOf(mMonth+1);
        if (mDay < 9) sday="0";
        sday += String.valueOf(mDay);
        edt_tuNgay.setText(sday+smonth+syear);
        edt_denNgay.setText(sday+smonth+syear);
    }

    private void initView() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        imbFrom = findViewById(R.id.imbFormAbsenceAct_From);
        imbTo = findViewById(R.id.imbFormAbsenceAct_To);
        imbFrom.setOnClickListener(this);
        imbTo.setOnClickListener(this);
        edt_tuNgay = findViewById(R.id.form_absence_edt_tuNgay);

        edt_denNgay = findViewById(R.id.form_absence_edt_denNgay);
        spnloaiNghi = (Spinner) findViewById(R.id.form_absence_spinner_loaiNghi);
        spnthoiGianNghi = (Spinner) findViewById(R.id.form_absence_spinner_thoiGianNghi);
        btnSubmit = findViewById(R.id.form_absence_btn_submit);
        btnSubmit.setOnClickListener(this);
        edtReason = findViewById(R.id.form_absence_edt_lydo);
        edtNote = findViewById(R.id.form_absence_edt_note);
        cb_nghiBoSung = findViewById(R.id.form_absence_cb_nghiBoSung);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, String.valueOf(view.getId()));
        switch (view.getId()) {
            case R.id.form_absence_btn_submit:
                View check = checkValidateForm();
                if (check != null) {
                    check.requestFocus();
                } else {
                    String absenceType = spnloaiNghi.getSelectedItem().toString();
                    absenceType = absenceType.replace(" ", "_");

                    int absenceTypeId = getAbsenceTypeId(absenceType);
                    String absenceTime = spnthoiGianNghi.getSelectedItem().toString();
                    int absenceTimeId = getAbsenceTimeId(absenceTime);
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("absenceTypeId", absenceTypeId);
                        String[] split = edt_tuNgay.getText().toString().split("-");
                        String tungay = split[2]+"-"+split[1]+"-"+split[0];
                        jsonObject.put("fromDate", tungay);
                        split = edt_denNgay.getText().toString().split("-");
                        String denngay = split[2]+"-"+split[1]+"-"+split[0];
                        jsonObject.put("toDate", denngay);
                        jsonObject.put("reason", edtReason.getText().toString());
                        jsonObject.put("description", edtNote.getText().toString());
                        jsonObject.put("absenceTimeId", absenceTimeId);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG,"Json Object body " + jsonObject.toString());
                    RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
                    if (statusAbsences == StatusAbsences.enum_Add) {
                        absencePresenter.addAbsence(body);
                    }else absencePresenter.editAbsence(body,idAbsence);
                }
                break;
            case R.id.imbFormAbsenceAct_From:
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String syear="",smonth="",sday="";
                        syear = String.valueOf(year);
                        if (monthOfYear < 9) smonth="0";
                        smonth += String.valueOf(monthOfYear+1);
                        if (dayOfMonth < 9) sday="0";
                        sday += String.valueOf(dayOfMonth);
                        edt_tuNgay.setText(sday+smonth+syear);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;
            case R.id.imbFormAbsenceAct_To:
                DatePickerDialog datePickerDialog1 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String syear="",smonth="",sday="";
                        syear = String.valueOf(year);
                        if (monthOfYear < 9) smonth="0";
                        smonth += String.valueOf(monthOfYear+1);
                        if (dayOfMonth < 9) sday="0";
                        sday += String.valueOf(dayOfMonth);
                        edt_denNgay.setText(sday+smonth+syear);

                    }
                }, mYear, mMonth, mDay);
                datePickerDialog1.show();
                break;
        }
    }

    private View checkValidateForm() {
        edt_tuNgay.setError(null);
        edt_denNgay.setError(null);
        edtReason.setError(null);
        edtReason.setError(null);
        View focusView = null;
        //Valid từ ngày
        try {
            String[] split = edt_tuNgay.getText().toString().split("-");
            int day = Integer.parseInt(split[0]);
            int month = Integer.parseInt(split[1]);
            int year = Integer.parseInt(split[2]);
            if (!ValidationDateTime.checkDay(day, month, year)) {
                edt_tuNgay.setError(getString(R.string.error_invalid_date));
                return edt_tuNgay;
            }
        }catch (Exception ex){
            ex.printStackTrace();
            edt_tuNgay.setError(getString(R.string.error_invalid_date));
            return edt_tuNgay;
        }
        //Valid đến ngày
        try {
            String[] split = edt_denNgay.getText().toString().split("-");
            int day = Integer.parseInt(split[0]);
            int month = Integer.parseInt(split[1]);
            int year = Integer.parseInt(split[2]);
            if (!ValidationDateTime.checkDay(day, month, year)) {
                edt_denNgay.setError(getString(R.string.error_invalid_date));
                return edt_denNgay;
            }
        }catch (Exception ex){
            ex.printStackTrace();
            edt_denNgay.setError(getString(R.string.error_invalid_date));
            return edt_denNgay;
        }
        //Từ ngày không được lớn hơn đến ngày
        try {
            String[] split = edt_tuNgay.getText().toString().split("-");
            int day = Integer.parseInt(split[0]);
            int month = Integer.parseInt(split[1]);
            int year = Integer.parseInt(split[2]);
            String[] split1 = edt_denNgay.getText().toString().split("-");
            int day1 = Integer.parseInt(split1[0]);
            int month1 = Integer.parseInt(split1[1]);
            int year1 = Integer.parseInt(split1[2]);
            if (!ValidationDateTime.compareDate(day1,month1,year1,day,month,year)) {
                edt_denNgay.setError(getResources().getString(R.string.error_from_not_greater_than_to));
                Toast.makeText(this,getResources().getString(R.string.error_from_not_greater_than_to),Toast.LENGTH_SHORT).show();
                return edt_denNgay;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Reason ko được bỏ trống
        if (edtReason.getText().toString().trim().length() <=0){
            edtReason.setError(getString(R.string.error_field_is_not_empty));
            return edtReason;
        }
        return null;
    }

    private int getAbsenceTimeId(String absenceTime) {
        switch (absenceTime) {
            case "All day":
                return 1;
            case "Morning":
                return 2;
            default:
                return 3;
        }
    }

    private int getAbsenceTypeId(String absenceType) {
        for (TypeAbsence type : listAbsence) {
            if (type.getNameAbsenceType().equalsIgnoreCase(absenceType))
                return type.getIdAbsenceType();
        }
        return 1;
    }

    @Override
    public void Success() {

        Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
        setResult(Activity.RESULT_OK);
        finish();
    }

    @Override
    public void Failure() {
        Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadTypeAbsenceSuccess(List list) {
        listAbsence = list;
        ArrayAdapter<TypeAbsence> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, listAbsence);
        spnloaiNghi.setAdapter(adapter);
        getExtra();

    }

    @Override
    public void loadTypeAbsenceFailure() {
        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void EditAbsenceSuccess() {
        Toast.makeText(getApplicationContext(),"Edit Success",Toast.LENGTH_SHORT).show();
        setResult(Activity.RESULT_OK);
        finish();
    }

    @Override
    public void EditAbsenceFailure() {
        Toast.makeText(getApplicationContext(),"Edit Failure",Toast.LENGTH_SHORT).show();
    }
}
