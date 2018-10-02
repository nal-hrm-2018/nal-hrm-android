package com.dal.hrm_management.views.overtime.formOvertime;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.dal.hrm_management.R;
import com.dal.hrm_management.models.holiday.Holiday;
import com.dal.hrm_management.models.listProjectEmpJoining.Project;
import com.dal.hrm_management.models.overtimePersonal.Overtime;
import com.dal.hrm_management.presenters.holiday.holiday.HolidayPresenter;
import com.dal.hrm_management.presenters.overtimePersonal.formOvertime.FormOvertimePresenter;
import com.dal.hrm_management.utils.StringUtils;
import com.dal.hrm_management.utils.ValidationDateTime;
import com.dal.hrm_management.utils.VariableUltils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.List;

import okhttp3.RequestBody;

import static com.dal.hrm_management.utils.Constant.START_HOUR_OT;
import static com.dal.hrm_management.utils.Constant.START_MINUTES_OT;

public class FormOvertimeActivity extends AppCompatActivity implements View.OnClickListener, IFormOvertimeActivity {
    private final String TAG = FormOvertimeActivity.class.getSimpleName();
    private EditText edtDate, edtFromTime, edtToTime, edtTotalTime, edtReason;
    private ImageButton imbDate, imbFromTime, imbToTime;
    private TextView tvProject;
    private Button btnSubmit;
    private Spinner spnProject;
    private ProgressDialog progressDialog;
    private LinearLayout layoutProject;
    private Calendar c = Calendar.getInstance();
    private int mYear, mMonth, mDay, hour, minute;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private TextView tvError;
    //Extra
    private Overtime edit_overTime;
    private boolean isEditOvertime; // isEditOvertime = true --> Chuc nang edit nguoc lai: chuc nang add
    //api
    private FormOvertimePresenter formOvertimePresenter;
    private HolidayPresenter holidayPresenter;
    private List<Holiday> holidayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_overtime);
        init();
        getListProject();
        getExtra();
    }

    private void getListProject() {
        formOvertimePresenter.getListProject();
    }

    private void getExtra() {
        if (getIntent().getExtras() != null) {
            edit_overTime = (Overtime) getIntent().getExtras().getSerializable(VariableUltils.KEY_PUT_EXTRA_EDIT_OVERTIME_PERSONAL);
            if (edit_overTime != null) {
                //dung chuc nang edit
                isEditOvertime = true;
                Log.d(TAG, "Edit overtime personal");
                //Apply Data
                btnSubmit.setText("Update");
                setTitle("Edit overtime");
                //Apply data
                //todo: show list project
                if (edit_overTime.getNameProject() == null) {
                    layoutProject.setVisibility(View.GONE);
                }
                String date = edit_overTime.getDate();
                edtDate.setText(StringUtils.convertDateFromServerToEditText(date));
                String fromTime = edit_overTime.getStartTime();
                edtFromTime.setText(StringUtils.convertTimeFromServerToEditText(fromTime));
                String toTime = edit_overTime.getEndTime();
                edtToTime.setText(StringUtils.convertTimeFromServerToEditText(toTime));
                edtTotalTime.setText(String.valueOf(edit_overTime.getTotalTime()));
                edtReason.setText(String.valueOf(edit_overTime.getReason()));
            }
        } else {
            isEditOvertime = false;
            Log.d(TAG, "Add overtime personal");
        }

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
        tvProject = findViewById(R.id.tvFormOvertimeAct_Project);
        layoutProject = findViewById(R.id.layoutFormOvertimeAct_Project);
        imbDate.setOnClickListener(this);
        imbFromTime.setOnClickListener(this);
        imbToTime.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        tvError = findViewById(R.id.tvFormOvertimeAct_error);
        getCurrentDateTime();
        formOvertimePresenter = new FormOvertimePresenter(this);
        holidayPresenter = new HolidayPresenter(this);
        holidayPresenter.getHoliday();

        //progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Processing");
        progressDialog.setCanceledOnTouchOutside(false);


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
        switch (view.getId()) {
            case R.id.btnFormOvertimeAct_Submit:
                View v = checkValidate();
                if (v == null) {
                    //create json body
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("reason", edtReason.getText().toString());
                        jsonObject.put("date", StringUtils.convertDateEditTextToServer(edtDate.getText().toString()));
                        jsonObject.put("startTime", StringUtils.convertTimehh_mmTohh_mm_ss(edtFromTime.getText().toString()));
                        jsonObject.put("endTime", StringUtils.convertTimehh_mmTohh_mm_ss(edtToTime.getText().toString()));
                        jsonObject.put("totalTime", Double.parseDouble(edtTotalTime.getText().toString()));
                        if (layoutProject.getVisibility() != View.GONE) {
                            jsonObject.put("idProject", ((Project) spnProject.getSelectedItem()).getIdProject());
                        }
                        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
                        if (isEditOvertime) {
                            formOvertimePresenter.editOvertime(body, edit_overTime.getId());
                        } else {
                            formOvertimePresenter.addOvertime(body);
                        }
                        progressDialog.show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    v.requestFocus();
                }
                break;
            case R.id.imbFormOvertimeAct_Date:
                datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String year = "", month = "", day = "";
                        year = String.valueOf(i);
                        if (i1 < 9) month = "0";
                        month += String.valueOf(i1 + 1);
                        if (i2 <= 9) day = "0";
                        day += String.valueOf(i2);
                        edtDate.setText(day + month + year);
                        Log.d(TAG, "edtDate: " + edtDate.getText().toString());
                        edtDate.requestFocus();
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;
            case R.id.imbFormOvertimeAct_FromTime:
                timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String sh = "", sm = "";
                        if (selectedHour <= 9) sh = "0";
                        sh += String.valueOf(selectedHour);
                        if (selectedMinute <= 9) sm = "0";
                        sm += String.valueOf(selectedMinute);
                        edtFromTime.setText(sh + sm);
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
                        String sh = "", sm = "";
                        if (selectedHour <= 9) sh = "0";
                        sh += String.valueOf(selectedHour);
                        if (selectedMinute <= 9) sm = "0";
                        sm += String.valueOf(selectedMinute);
                        edtToTime.setText(sh + sm);
                        edtToTime.requestFocus();
                    }
                }, hour, minute, true);//Yes 24 hour time
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();
                break;

            default:
                break;
        }
    }

    private View checkValidate() {
        tvError.setVisibility(View.GONE);
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
        } catch (Exception ex) {
            ex.printStackTrace();
            edtDate.setError(getString(R.string.error_invalid_date));
            return edtDate;
        }
        //check day
        boolean isHoliday = checkHoliday();
        int maxMinutes = 0,fromMinutes=0,toMinutes=0;

        //Check time
        try {
            String[] split = edtFromTime.getText().toString().split(":");
            int hour = Integer.parseInt(split[0]);
            int minute = Integer.parseInt(split[1]);
            if (!ValidationDateTime.checkTime(hour, minute)) {
                edtFromTime.setError(getString(R.string.error_invalid_time));
                return edtFromTime;
            }
            if (!isHoliday){
                if (hour < START_HOUR_OT){
                    tvError.setVisibility(View.VISIBLE);
                    tvError.setText(getString(R.string.error_overtime_hour_start) +START_HOUR_OT +":"+START_MINUTES_OT);
                    edtFromTime.setError(getString(R.string.error_invalid_time));
                    return edtFromTime;
                }else{
                    if (hour == START_HOUR_OT && minute < START_MINUTES_OT){
                        tvError.setVisibility(View.VISIBLE);
                        tvError.setText(getString(R.string.error_overtime_hour_start) +START_HOUR_OT +":"+START_MINUTES_OT);
                        edtFromTime.setError(getString(R.string.error_invalid_time));
                        return edtFromTime;
                    }
                }
            }
            fromMinutes = hour*60+minute;
        } catch (Exception ex) {
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
            toMinutes = hour*60+minute;
            if (fromMinutes > toMinutes){
                edtToTime.setError(getString(R.string.error_invalid_time));
                return edtToTime;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            edtToTime.setError(getString(R.string.error_invalid_time));
            return edtToTime;
        }
        //Check actual
        try {
            maxMinutes = toMinutes -fromMinutes;
            double total = Double.parseDouble(edtTotalTime.getText().toString());
            if (total > 24 || total < 0 || maxMinutes < total*60) {
                edtTotalTime.setError(getString(R.string.error_invalid_time));
                return edtTotalTime;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            edtTotalTime.setError(getString(R.string.error_invalid_time));
            return edtTotalTime;
        }
        return null;
    }

    private boolean checkHoliday() {
        for (Holiday holiday:holidayList
             ) {
            if (holiday.getDateHoliday().equals(StringUtils.convertDateEditTextToServer(edtDate.getText().toString()))){
                return true;
            }
        }
        return false;
    }

    @Override
    public void getListProjectSuccess(List<Project> data) {
        if (data.size() > 0) {
            ArrayAdapter<Project> adapter = new ArrayAdapter<Project>(this, R.layout.support_simple_spinner_dropdown_item, data);
            spnProject.setAdapter(adapter);
        } else {
            //emp is not in any project
            layoutProject.setVisibility(View.GONE);
        }
    }

    @Override
    public void getListProjectFailure() {
        progressDialog.dismiss();
        Toast.makeText(this, "Failure to get project", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void addOvertimeSuccess() {
        progressDialog.dismiss();
        Toast.makeText(this, "Add overtime success", Toast.LENGTH_SHORT).show();
        setResult(Activity.RESULT_OK);
        finish();
    }

    @Override
    public void addOvertimeFailure() {
        progressDialog.dismiss();
        Toast.makeText(this, "Server error", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void addOvertimeFailure(String message) {
        progressDialog.dismiss();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void editOvertimeSuccess(String msg) {
        progressDialog.dismiss();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        setResult(Activity.RESULT_OK);
        finish();
    }

    @Override
    public void editOvertimeFailure() {
        progressDialog.dismiss();
        Toast.makeText(this, "Edit overtime failure!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getHolidaySuccess(List<Holiday> data) {
        holidayList = data;
    }
}
