package com.dal.hrm_management.views.absenceEmployee;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.dal.hrm_management.R;
import com.dal.hrm_management.adapter.AbsenceDetailEmployeeAdapter;
import com.dal.hrm_management.models.manageAbsence.hr.absenceEmployee.Absence;
import com.dal.hrm_management.models.manageAbsence.hr.absenceEmployee.Data;
import com.dal.hrm_management.presenters.absenceEmployee.DetailAbsenceEmployeePresenter;

import java.util.ArrayList;
import java.util.List;

public class DetailAbsenceEmployeeActivity extends AppCompatActivity implements IDetailAbsenceEmployeeActivity, View.OnClickListener {

    private RecyclerView rv_absence;
    private ImageButton imBtn_show;
    private DetailAbsenceEmployeePresenter detailAbsenceEmployeePresenter;
    private Data dataAbsence;
    private List<Absence> absenceList;
    private int current_page = 1;
    private int pageSize = 15;
    private int id_employee;
    private String name_employee;
    private CollapsingToolbarLayout collapsingtoolbar;
    private TextView tv_message_nothing;
    private TextView tv_allowAbsence, tv_remainingAbsenceDays, tv_unpaidLeave, tv_annualLeave, tv_marriageLeave, tv_maternityLeave, tv_bereavementLeave;


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
        imBtn_show = (ImageButton) findViewById(R.id.imBtn_show);
        rv_absence = (RecyclerView) findViewById(R.id.rv_absence);
        rv_absence.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv_absence.setAdapter(new AbsenceDetailEmployeeAdapter(absenceList, this));
        imBtn_show.setOnClickListener(this);
    }

    private void showDialogInforAbsence() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inforAbsence = inflater.inflate(R.layout.dialog_infor_absence, null, false);
        initViewDialog(inforAbsence);
        loadData(dataAbsence);
        new AlertDialog.Builder(this).setView(inforAbsence).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).show();
    }

    private void initViewDialog(View inforAbsence) {
        tv_allowAbsence = (TextView) inforAbsence.findViewById(R.id.tv_allowAbsence);
        tv_remainingAbsenceDays = (TextView) inforAbsence.findViewById(R.id.tv_remainingAbsenceDays);
        tv_unpaidLeave = (TextView) inforAbsence.findViewById(R.id.tv_unpaidLeave);
        tv_annualLeave = (TextView) inforAbsence.findViewById(R.id.tv_annualLeave);
        tv_marriageLeave = (TextView) inforAbsence.findViewById(R.id.tv_marriageLeave);
        tv_maternityLeave = (TextView) inforAbsence.findViewById(R.id.tv_maternityLeave);
        tv_bereavementLeave = (TextView) inforAbsence.findViewById(R.id.tv_bereavementLeave);
    }

    @Override
    public void onClick(View v) {
        showDialogInforAbsence();
    }

    @Override
    public void getDetailAbsenceEmployeeSuccess(Data dataAbsence) {
        this.dataAbsence = dataAbsence;
        absenceList = dataAbsence.getListAbsence().getAbsence();
        if (absenceList != null) {
            if(absenceList.size()!=0){
                rv_absence.setAdapter(new AbsenceDetailEmployeeAdapter(absenceList, this));
                tv_message_nothing.setVisibility(View.GONE);
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
        tv_allowAbsence.setText(dataAbsence.getAllowAbsence() + "");
        tv_remainingAbsenceDays.setText(dataAbsence.getRemainingAbsenceDays() + "");
        tv_unpaidLeave.setText(dataAbsence.getUnpaidLeave() + "");
        tv_annualLeave.setText(dataAbsence.getAnnualLeave() + "");
        tv_marriageLeave.setText(dataAbsence.getMarriageLeave() + "");
        tv_bereavementLeave.setText(dataAbsence.getBereavementLeave() + "");
        tv_maternityLeave.setText(dataAbsence.getMaternityLeave() + "");
    }
}
