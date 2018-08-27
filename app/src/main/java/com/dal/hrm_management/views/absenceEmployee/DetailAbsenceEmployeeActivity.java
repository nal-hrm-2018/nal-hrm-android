package com.dal.hrm_management.views.absenceEmployee;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import com.dal.hrm_management.R;
import com.dal.hrm_management.adapter.AbsenceAdapter;
import com.dal.hrm_management.models.absence.Absence;
import com.dal.hrm_management.presenters.absenceEmployee.DetailAbsenceEmployeePresenter;

import java.util.List;

public class DetailAbsenceEmployeeActivity extends AppCompatActivity implements IDetailAbsenceEmployeeActivity {

    private RecyclerView rv_absence;
    private ImageButton imBtn_show;
    private DetailAbsenceEmployeePresenter detailAbsenceEmployeePresenter;
    private List<Absence> absenceModelList;
    private CollapsingToolbarLayout collapsingtoolbar;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_absence_employee);
        mapMVP();
        initUi();
        setEvent();
    }

    private void setEvent() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });
    }

    private void mapMVP() {
        detailAbsenceEmployeePresenter = new DetailAbsenceEmployeePresenter(this);
        detailAbsenceEmployeePresenter.initData();
    }

    private void initUi() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        collapsingtoolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsingtoolbar);
        collapsingtoolbar.setTitle("Lưu Ngọc Lan");
        rv_absence = (RecyclerView) findViewById(R.id.rv_absence);
        rv_absence.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rv_absence.setAdapter(new AbsenceAdapter(absenceModelList,this));
    }

    private void showDialogInforAbsence() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inforAbsence = inflater.inflate(R.layout.dialog_infor_absence, null, false);
        initViewDialog(inforAbsence);
        new AlertDialog.Builder(this).setView(inforAbsence).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).show();
    }

    private void initViewDialog(View view) {

    }
}
