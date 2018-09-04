package com.dal.hrm_management.views.absence;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dal.hrm_management.R;
import com.dal.hrm_management.adapter.AbsenceAdapter;
import com.dal.hrm_management.models.absence.Absence;
import com.dal.hrm_management.models.absence.DataAbsence;
import com.dal.hrm_management.presenters.absence.AbsencePresenter;
import com.dal.hrm_management.utils.VariableUltils;

import java.util.ArrayList;
import java.util.List;

public class AbsenceViewFragment extends Fragment implements IAbsenceViewActivity, View.OnClickListener {
    private final String TAG = AbsenceViewFragment.class.getSimpleName();
    private RecyclerView rv_absence;
    private AbsencePresenter absencePresenter;
    private MenuItem addAbsence;
    private List<Absence> absenceList = new ArrayList<>();
    private DataAbsence dataAbsence;
    private int current_page = 1;
    private int pageSize = 15;
    private int total = 0;
    private LinearLayoutManager layoutManager;
    private AbsenceAdapter adapter;
    private ImageButton btn_add;
    private TextView tv_message_nothing;
    private ProgressBar progressBar;
    private TextView tv_allowAbsence, tv_remainingAbsenceDays, tv_unpaidLeave, tv_annualLeave, tv_marriageLeave, tv_maternityLeave, tv_bereavementLeave;
    //set scroll recycler view
    private RecyclerView.OnScrollListener recyOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            Log.d(TAG,"Scroll OK");
            int visibleItemCount = layoutManager.getChildCount();
            Log.d("visibleItemCount", String.valueOf(visibleItemCount));
            int totalItemCount = layoutManager.getItemCount();
            Log.d("totalItemCount", String.valueOf(totalItemCount));
            int firstitem = layoutManager.findFirstVisibleItemPosition();
            Log.d("firstitem", String.valueOf(firstitem));
            if (firstitem+visibleItemCount >= totalItemCount && current_page*pageSize < total){
                current_page++;
                Log.d(TAG + "current page: ", String.valueOf(current_page));
                progressBar.setVisibility(View.VISIBLE);
                absencePresenter.getDataAbsence(current_page, pageSize);
            }
        }
    };
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
        progressBar = root.findViewById(R.id.prgAbsenceFrag_ShowMore);
        rv_absence.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        tv_message_nothing = (TextView) root.findViewById(R.id.tv_message_nothing);
        btn_add = (ImageButton) root.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VariableUltils.REQUEST_CODE && resultCode == Activity.RESULT_OK){
            current_page =1;
            absenceList.clear();
            absencePresenter.getDataAbsence(current_page, pageSize);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void getAbsencePersonalSuccess(DataAbsence dataAbsence) {
        this.dataAbsence = dataAbsence;
        total = dataAbsence.getListAbsence().getTotal(); //get total record absence
        List<Absence> list = dataAbsence.getListAbsence().getAbsence(); //get list record absence in model
        if (list.size() !=0){
            absenceList.addAll(dataAbsence.getListAbsence().getAbsence()); //add all list record in array
            if (adapter == null){
                adapter = new AbsenceAdapter(absenceList, getActivity());
            }else{
                adapter.notifyDataSetChanged();
            }
            progressBar.setVisibility(View.GONE);
            tv_message_nothing.setVisibility(View.GONE);
            rv_absence.setLayoutManager(layoutManager);
            rv_absence.setAdapter(adapter);
            rv_absence.addOnScrollListener(recyOnScrollListener);

        }else{
            tv_message_nothing.setText("No absence to show!");
            tv_message_nothing.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void getAbsencePersonalFailed() {
        Toast.makeText(getActivity(),"Error",Toast.LENGTH_SHORT).show();
        tv_message_nothing.setText("No absence to show!");
        tv_message_nothing.setVisibility(View.VISIBLE);
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
            case R.id.btn_add:
                Intent intent = new Intent(getActivity(), FormAbsenceActivity.class);
                startActivityForResult(intent, VariableUltils.REQUEST_CODE);
        }
    }
}
