package com.dal.hrm_management.views.absence;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dal.hrm_management.R;
import com.dal.hrm_management.adapter.AbsenceAdapter;
import com.dal.hrm_management.models.absence.Absence;
import com.dal.hrm_management.models.absence.DataAbsence;
import com.dal.hrm_management.presenters.absence.AbsencePresenter;

import java.util.List;

public class AbsenceViewFragment extends Fragment implements IAbsenceViewActivity, View.OnClickListener {
    private RecyclerView rv_absence;
    private AbsencePresenter absencePresenter;
    private MenuItem addAbsence;
    private DataAbsence dataAbsence;
    private List<Absence> absenceList;
    private int current_page = 1;
    private int pageSize = 15;
    private LinearLayoutManager layoutManager;
    private AbsenceAdapter adapter;
    private ImageButton imBtn_show;
    private TextView tv_message_nothing;
    private TextView tv_allowAbsence, tv_remainingAbsenceDays, tv_unpaidLeave, tv_annualLeave, tv_marriageLeave, tv_maternityLeave, tv_bereavementLeave;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View root = inflater.inflate(R.layout.fragment_absence, container, false);
        initPresenter();
        initUI(root);
        return root;
    }

    private void initPresenter() {
        absencePresenter = new AbsencePresenter(this);
        absencePresenter.getDataAbsence(current_page, pageSize);
    }

    private void initUI(View root) {
        layoutManager = new LinearLayoutManager(getActivity());
        rv_absence = root.findViewById(R.id.rv_absence);
        rv_absence.setHasFixedSize(false);
        rv_absence.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        tv_message_nothing = (TextView) root.findViewById(R.id.tv_message_nothing);
        imBtn_show = (ImageButton) root.findViewById(R.id.imBtn_show);
        imBtn_show.setOnClickListener(this);

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.app_bar_menu_absence, menu);
        addAbsence = menu.findItem(R.id.action_add_absence);
        addAbsence.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getActivity(), Form_absence.class);
                startActivity(intent);
                return false;
            }
        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void getAbsencePersonalSuccess(DataAbsence dataAbsence) {
        this.dataAbsence = dataAbsence;
        absenceList = dataAbsence.getListAbsence().getAbsence();
        if (absenceList != null) {
            if(absenceList.size()!=0){
                rv_absence.setAdapter(new AbsenceAdapter(absenceList, getActivity()));
                tv_message_nothing.setVisibility(View.GONE);
            } else {
                tv_message_nothing.setText("No absence to show!");
                tv_message_nothing.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void getAbsencePersonalFailed() {

    }

    private void showDialogInforAbsence(DataAbsence dataAbsence) {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inforAbsence = inflater.inflate(R.layout.dialog_infor_absence, null, false);
        initViewDialog(inforAbsence);
        loadData(dataAbsence);
        new AlertDialog.Builder(getActivity()).setView(inforAbsence).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).show();
    }

    private void loadData(DataAbsence dataAbsence) {
        tv_allowAbsence.setText(dataAbsence.getAllowAbsence() + "");
        tv_remainingAbsenceDays.setText(dataAbsence.getRemainingAbsenceDays() + "");
        tv_unpaidLeave.setText(dataAbsence.getUnpaidLeave() + "");
        tv_annualLeave.setText(dataAbsence.getAnnualLeave() + "");
        tv_marriageLeave.setText(dataAbsence.getMarriageLeave() + "");
        tv_bereavementLeave.setText(dataAbsence.getBereavementLeave() + "");
        tv_maternityLeave.setText(dataAbsence.getMaternityLeave() + "");
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
        switch (v.getId()) {
            case R.id.imBtn_show:
                showDialogInforAbsence(dataAbsence);
        }
    }
}
