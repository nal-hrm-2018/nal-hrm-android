package com.dal.hrm_management.views.absenceEmployee;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dal.hrm_management.R;
import com.dal.hrm_management.adapter.listAbsence.AbsenceDetailEmployeeAdapter;
import com.dal.hrm_management.models.manageAbsence.hr.absenceEmployee.Absence;
import com.dal.hrm_management.models.manageAbsence.hr.absenceEmployee.Data;
import com.dal.hrm_management.presenters.absenceEmployee.DetailAbsenceEmployeePresenter;

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
    private CollapsingToolbarLayout collapsingtoolbar;
    private TextView tv_message_nothing;
    private RelativeLayout layout;
    private TextView tvTotal,tvRemain,tvAnnual,tvNoSalary,tvMaternity,tvMarriage,tvBereavement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_absence_employee);
        getDataFromIntent();
        initPresenter();
        init();
    }

    private void getDataFromIntent() {
        id_employee = getIntent().getIntExtra("id_employee",-1);
        name_employee = getIntent().getStringExtra("name_employee");
    }

    private void initPresenter() {
        if(id_employee>=0){
            detailAbsenceEmployeePresenter = new DetailAbsenceEmployeePresenter(this);
            detailAbsenceEmployeePresenter.getDetailAbsenceEmployee(id_employee,current_page,pageSize);
        } else {
            Toast.makeText(this,"Bring data failed!",Toast.LENGTH_SHORT).show();
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
        rv_absence.setAdapter(new AbsenceDetailEmployeeAdapter(absenceList, this));

        layout = findViewById(R.id.layoutDetailAbsenceEmpAct_header);
        layout.setVisibility(View.GONE);


        tvTotal = findViewById(R.id.tvDetailAbsenceEmpAct_Total);
        tvRemain = findViewById(R.id.tvDetailAbsenceEmpAct_Remain);
        tvAnnual = findViewById(R.id.tvDetailAbsenceEmpAct_Annual);
        tvNoSalary = findViewById(R.id.tvDetailAbsenceEmpAct_NoSalary);
        tvMaternity = findViewById(R.id.tvDetailAbsenceEmpAct_Maternity);
        tvMarriage = findViewById(R.id.tvDetailAbsenceEmpAct_Marriage);
        tvBereavement = findViewById(R.id.tvDetailAbsenceEmpAct_Bereave);

    }


    @Override
    public void getDetailAbsenceEmployeeSuccess(Data dataAbsence) {
        this.dataAbsence = dataAbsence;
        absenceList = dataAbsence.getListAbsence().getAbsence();
        if (absenceList != null) {
            if(absenceList.size()!=0){
                rv_absence.setAdapter(new AbsenceDetailEmployeeAdapter(absenceList, this));
                tv_message_nothing.setVisibility(View.GONE);
                layout.setVisibility(View.VISIBLE);
                loadData(dataAbsence);
            } else {
                tv_message_nothing.setText("No absence to show!");
                tv_message_nothing.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void getDetailAbsenceEmployeeFailed() {

    }
    private void loadData(Data dataAbsence) {
        tvTotal.setText(dataAbsence.getAllowAbsence() + "");
        tvRemain.setText(dataAbsence.getRemainingAbsenceDays() + "");
        tvNoSalary.setText(dataAbsence.getUnpaidLeave() + "");
        tvAnnual.setText(dataAbsence.getAnnualLeave() + "");
        tvMarriage.setText(dataAbsence.getMarriageLeave() + "");
        tvBereavement.setText(dataAbsence.getBereavementLeave() + "");
        tvMaternity.setText(dataAbsence.getMaternityLeave() + "");
    }
}
