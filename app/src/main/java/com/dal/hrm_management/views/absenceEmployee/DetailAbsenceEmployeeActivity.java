package com.dal.hrm_management.views.absenceEmployee;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dal.hrm_management.R;
import com.dal.hrm_management.models.absence.Absence;
import com.dal.hrm_management.presenters.absenceEmployee.DetailAbsenceEmployeePresenter;

import java.util.List;

public class DetailAbsenceEmployeeActivity extends AppCompatActivity implements IDetailAbsenceEmployeeActivity, View.OnClickListener {

    private RecyclerView rv_absence;
    private ImageButton imBtn_show;
    private DetailAbsenceEmployeePresenter detailAbsenceEmployeePresenter;
    private List<Absence> absenceModelList;
    private CollapsingToolbarLayout collapsingtoolbar;
    private TextView tv_message_nothing;
    private TextView tv_allowAbsence, tv_remainingAbsenceDays, tv_unpaidLeave, tv_annualLeave, tv_marriageLeave, tv_maternityLeave, tv_bereavementLeave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_absence_employee);
        initUi();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initUi() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Name's employee");
//        rv_absence = (RecyclerView) findViewById(R.id.rv_absence);
//        rv_absence.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
//        rv_absence.setAdapter(new AbsenceAdapter(absenceModelList,this));
        tv_message_nothing = (TextView) findViewById(R.id.tv_message_nothing);
        imBtn_show = (ImageButton) findViewById(R.id.imBtn_show);
        imBtn_show.setOnClickListener(this);
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
}
