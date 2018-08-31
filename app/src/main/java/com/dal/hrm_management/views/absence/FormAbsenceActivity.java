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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dal.hrm_management.R;
import com.dal.hrm_management.models.absence.Absence;
import com.dal.hrm_management.models.absence.addAbsence.TypeAbsence;
import com.dal.hrm_management.models.manageAbsence.hr.ListAbsenceForHr;
import com.dal.hrm_management.presenters.absence.AbsencePresenter;
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
    Button btnSubmit;
    EditText edt_denNgay,edt_tuNgay;
    EditText edtReason, edtNote;
    private int mYear, mMonth, mDay;
    AbsencePresenter absencePresenter;
    Spinner loaiNghi, thoiGianNghi;
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
            loaiNghi.setSelection(absence.getAbsenceType().getIdAbsenceType()-1);
            //array start with index = 0
            thoiGianNghi.setSelection(absence.getAbsenceTime().getIdAbsenceTime()-1);
            edtReason.setText(absence.getReason());
            edtNote.setText(absence.getDescription());
            edt_tuNgay.setOnClickListener(null);
            edt_denNgay.setOnClickListener(null);
            loaiNghi.setEnabled(false);
            thoiGianNghi.setEnabled(false);
            edtReason.setEnabled(false);
            edtNote.setEnabled(false);
            btnSubmit.setVisibility(View.GONE);
        }

        else{
            ListAbsenceForHr absenceForHr = (ListAbsenceForHr) getIntent().getSerializableExtra(VariableUltils.KEY_PUT_EXTRA_EDIT_ABSENCE);
            if (absenceForHr !=null){
                setTitle(absenceForHr.getNameEmployee());
                edt_tuNgay.setText(absenceForHr.getFromDate());
                edt_denNgay.setText(absenceForHr.getToDate());
                loaiNghi.setSelection(absenceForHr.getAbsenceType().getIdAbsenceType()-1);
                //array start with index = 0
                thoiGianNghi.setSelection(absenceForHr.getAbsenceTime().getIdAbsenceTime()-1);
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
        thoiGianNghi.setAdapter(adapter);

        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        edt_tuNgay.setText(mYear + "-" + (mMonth + 1) + "-" + mDay);
        edt_denNgay.setText(mYear + "-" + (mMonth + 1) + "-" + mDay);
    }

    private void initView() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        edt_tuNgay = findViewById(R.id.form_absence_edt_tuNgay);
        edt_tuNgay.setOnClickListener(this);
        edt_denNgay = findViewById(R.id.form_absence_edt_denNgay);
        edt_denNgay.setOnClickListener(this);
        loaiNghi = (Spinner) findViewById(R.id.form_absence_spinner_loaiNghi);
        thoiGianNghi = (Spinner) findViewById(R.id.form_absence_spinner_thoiGianNghi);
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
                    String absenceType = loaiNghi.getSelectedItem().toString();
                    absenceType = absenceType.replace(" ", "_");

                    int absenceTypeId = getAbsenceTypeId(absenceType);
                    Log.d(TAG + " absenceTypeId: ", String.valueOf(absenceTypeId));
                    String absenceTime = thoiGianNghi.getSelectedItem().toString();
                    int absenceTimeId = getAbsenceTimeId(absenceTime);
                    Log.d(TAG + " absenceTimeId: ", String.valueOf(absenceTimeId));
                    Log.d(TAG + " startDate: ", edt_tuNgay.getText().toString());
                    Log.d(TAG + " EndDate: ", edt_denNgay.getText().toString());
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("absenceTypeId", absenceTypeId);
                        jsonObject.put("fromDate", edt_tuNgay.getText().toString());
                        jsonObject.put("toDate", edt_denNgay.getText().toString());
                        jsonObject.put("reason", edtReason.getText().toString());
                        jsonObject.put("description", edtNote.getText().toString());
                        jsonObject.put("absenceTimeId", absenceTimeId);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
                    if (statusAbsences == StatusAbsences.enum_Add) {
                        absencePresenter.addAbsence(body);
                    }else absencePresenter.editAbsence(body,idAbsence);
                }
                break;
            case R.id.form_absence_edt_tuNgay:
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        edt_tuNgay.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;
            case R.id.form_absence_edt_denNgay:


                DatePickerDialog datePickerDialog1 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        edt_denNgay.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                    }
                }, mYear, mMonth, mDay);
                datePickerDialog1.show();
                break;
        }
    }

    private View checkValidateForm() {
        View focusView = null;

        //edt tu ngay
        if (TextUtils.isEmpty(edt_tuNgay.getText().toString())) {
            edt_tuNgay.setError(getResources().getString(R.string.error_field_is_not_empty));
            focusView = edt_tuNgay;
        } else if (TextUtils.isEmpty(edt_denNgay.getText().toString())) {
            edt_denNgay.setError(getResources().getString(R.string.error_field_is_not_empty));
            focusView = edt_denNgay;
        }else if (TextUtils.isEmpty(edtReason.getText().toString().trim())){
            edtReason.setError(getResources().getString(R.string.error_field_is_not_empty));
            focusView = edtReason;
        }
        //từ ngày không lớn hơn đến ngày
        else{
            try {
                Date tuNgay = new SimpleDateFormat("yyyy-MM-dd").parse(edt_tuNgay.getText().toString());
                Date denNgay = new SimpleDateFormat("yyyy-MM-dd").parse(edt_denNgay.getText().toString());
                if (tuNgay.compareTo(denNgay) > 0){
                    edt_denNgay.setError(getResources().getString(R.string.error_from_not_greater_than_to));
                    focusView = edt_denNgay;
                    Toast.makeText(this,getResources().getString(R.string.error_from_not_greater_than_to),Toast.LENGTH_SHORT).show();

                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        //Đang thiếu validate
        return focusView;
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
        loaiNghi.setAdapter(adapter);
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
