package com.dal.hrm_management.views.absenceEmployee;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dal.hrm_management.R;
import com.dal.hrm_management.adapter.listAbsence.AbsenceDetailEmployeeAdapter;
import com.dal.hrm_management.models.manageAbsence.hr.absenceEmployee.Absence;
import com.dal.hrm_management.models.manageAbsence.hr.absenceEmployee.Data;
import com.dal.hrm_management.presenters.absenceEmployee.DetailAbsenceEmployeePresenter;
import com.dal.hrm_management.utils.StringUtils;
import com.dal.hrm_management.utils.VariableUltils;

import java.util.ArrayList;
import java.util.List;

public class DetailAbsenceEmployeeActivity extends AppCompatActivity implements IDetailAbsenceEmployeeActivity {

    private RecyclerView rv_absence;
    private DetailAbsenceEmployeePresenter detailAbsenceEmployeePresenter;
    private Data dataAbsence;
    private List<Absence> absenceList;
    private int current_page = 1;
    private int pageSize = 15;
    private int id_employee;
    private String name_employee;
    private TextView tv_message_nothing;
    private RelativeLayout layout;
    private ProgressBar progressBar;
    private TextView tvThisyear, tvLastYear, tvRemainTotal, tvAnnual, tvUnpaid, tvSick, tvMaternity, tvMarriage, tvBereavement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_absence_employee);
        getDataFromIntent();
        initPresenter();
        init();
    }

    private void getDataFromIntent() {
        id_employee = getIntent().getIntExtra("id_employee", -1);
        name_employee = getIntent().getStringExtra("name_employee");
    }

    private void initPresenter() {
        if (id_employee >= 0) {
            detailAbsenceEmployeePresenter = new DetailAbsenceEmployeePresenter(this);
            detailAbsenceEmployeePresenter.getDetailAbsenceEmployee(id_employee, current_page, pageSize);
        } else {
            Toast.makeText(this, "Bring data failed!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle(name_employee);
        absenceList = new ArrayList<>();
        tv_message_nothing = (TextView) findViewById(R.id.tv_message_nothing);
        rv_absence = (RecyclerView) findViewById(R.id.rv_absence);
        rv_absence.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv_absence.setAdapter(new AbsenceDetailEmployeeAdapter(absenceList, this, detailAbsenceEmployeePresenter));
        layout = findViewById(R.id.layoutDetailAbsenceEmpAct_header);
        layout.setVisibility(View.GONE);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        tvThisyear = findViewById(R.id.tvDetailAbsenceEmpAct_Thisyear);
        tvRemainTotal = findViewById(R.id.tvDetailAbsenceEmpAct_RemainTotal);
        tvLastYear = findViewById(R.id.tvDetailAbsenceEmpAct_lastYear);
        tvAnnual = findViewById(R.id.tvDetailAbsenceEmpAct_Annual);
        tvSick = findViewById(R.id.tvAbsenceFra_SickSalary);
        tvUnpaid = findViewById(R.id.tvDetailAbsenceEmpAct_NoSalary);
        tvMaternity = findViewById(R.id.tvDetailAbsenceEmpAct_Maternity);
        tvMarriage = findViewById(R.id.tvDetailAbsenceEmpAct_Marriage);
        tvBereavement = findViewById(R.id.tvDetailAbsenceEmpAct_Bereave);

    }


    @Override
    public void getDetailAbsenceEmployeeSuccess(Data dataAbsence) {
        this.dataAbsence = dataAbsence;
        absenceList = dataAbsence.getListAbsence().getAbsence();
        if (absenceList != null) {
            if (absenceList.size() != 0) {
                rv_absence.setAdapter(new AbsenceDetailEmployeeAdapter(absenceList, this, detailAbsenceEmployeePresenter));
                tv_message_nothing.setVisibility(View.GONE);
                layout.setVisibility(View.VISIBLE);
                loadData(dataAbsence);
            } else {
                rv_absence.setVisibility(View.GONE);
                tv_message_nothing.setText("No absence to show!");
                tv_message_nothing.setVisibility(View.VISIBLE);
            }
        }
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void getDetailAbsenceEmployeeFailed() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void deleteAbsenceSuccess() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        reloadPage();
    }

    @Override
    public void deleteAbsenceFailure() {
        Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show();
    }


    private void loadData(Data dataAbsence) {
        double remainTotal = dataAbsence.getTotalRemain();
        if (StringUtils.formatDisplayNumberDouble(remainTotal+"").length()==4){
            tvRemainTotal.setTextSize(48);
        }
        tvThisyear.setText(StringUtils.formatDisplayNumberDouble(dataAbsence.getAllowAbsence() + ""));
        tvLastYear.setText(StringUtils.formatDisplayNumberDouble(dataAbsence.getRemainingAbsenceDays() + ""));
        tvRemainTotal.setText(StringUtils.formatDisplayNumberDouble(remainTotal + ""));
        tvUnpaid.setText(StringUtils.formatDisplayNumberDouble(dataAbsence.getUnpaidLeave() + ""));
        tvAnnual.setText(StringUtils.formatDisplayNumberDouble(dataAbsence.getAnnualLeave() + ""));
        tvMarriage.setText(StringUtils.formatDisplayNumberDouble(dataAbsence.getMarriageLeave() + ""));
        tvBereavement.setText(StringUtils.formatDisplayNumberDouble(dataAbsence.getBereavementLeave() + ""));
        tvMaternity.setText(StringUtils.formatDisplayNumberDouble(dataAbsence.getMaternityLeave() + ""));
        tvSick.setText(StringUtils.formatDisplayNumberDouble(dataAbsence.getSickLeave() + ""));
    }

    private void reloadPage() {
        current_page = 1;

        absenceList.clear();
        detailAbsenceEmployeePresenter.getDetailAbsenceEmployee(id_employee, current_page, pageSize);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VariableUltils.REQUEST_CODE_ADD_ABSENCE && resultCode == Activity.RESULT_OK) {
            reloadPage();
        }
    }

}
