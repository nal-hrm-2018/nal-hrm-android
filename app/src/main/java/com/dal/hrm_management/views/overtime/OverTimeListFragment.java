package com.dal.hrm_management.views.overtime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dal.hrm_management.R;
import com.dal.hrm_management.adapter.listOvertime.OvertimeListAdapter;
import com.dal.hrm_management.models.overtimePersonal.DataOvertime;
import com.dal.hrm_management.models.overtimePersonal.Overtime;
import com.dal.hrm_management.presenters.overtimePersonal.OvertimePersonalPresenter;
import com.dal.hrm_management.views.overtime.formOvertime.FormOvertimeActivity;

import java.util.ArrayList;
import java.util.List;

import static com.dal.hrm_management.utils.VariableUltils.REQUEST_CODE_ADD_OVERTIME;
import static com.dal.hrm_management.utils.VariableUltils.REQUEST_CODE_EDIT_OVERTIME_PERSONAL;

public class OverTimeListFragment extends Fragment implements View.OnClickListener, IOvertimeListFragment {
    //In layout
    private TextView tvNomarlDay, tvDayOff, tvHoliday, tvNothingToShow;
    private ProgressBar prgShowMore;
    private FloatingActionButton fabAdd;
    private RecyclerView rvOvertime;
    private DataOvertime dataOvertime;
    private List<Overtime> overTimeList;
    private OvertimeListAdapter adapter;
    private LinearLayout layoutSumary;
    private LinearLayoutManager layoutManager;
    private OvertimePersonalPresenter overtimePersonalPresenter;
    private int currentPage = 1;
    private int pageSize = 5;
    private int totalOvertime = 0;
    private View view;

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            int visibleItemCount = layoutManager.getChildCount();
            Log.d("visibleItemCount", String.valueOf(visibleItemCount));
            int totalItemCount = layoutManager.getItemCount();
            Log.d("totalItemCount", String.valueOf(totalItemCount));
            int firstitem = layoutManager.findFirstVisibleItemPosition();
            Log.d("firstitem", String.valueOf(firstitem));
            if (firstitem + visibleItemCount >= totalItemCount && currentPage * pageSize < totalOvertime) {
                currentPage++;
                prgShowMore.setVisibility(View.VISIBLE);
                Log.d("current page: ", String.valueOf(currentPage));
                overtimePersonalPresenter.getOvertimePersonal(currentPage, pageSize);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_overtime_list_for_personel, container, false);
        init();
        initPresenter();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE_ADD_OVERTIME ) {
                reloadPage();
            }
        }
    }

    private void reloadPage() {
        adapter = null;
        overTimeList =null;
        currentPage = 1;
        overtimePersonalPresenter.getOvertimePersonal(currentPage, pageSize);
    }

    private void initPresenter() {
        overtimePersonalPresenter = new OvertimePersonalPresenter(this);
        overtimePersonalPresenter.getOvertimePersonal(currentPage, pageSize);
    }

    private void init() {
        tvNothingToShow = view.findViewById(R.id.tvOvertimeListFra_NothingToShow);
        tvNomarlDay = view.findViewById(R.id.tvOvertimeListFra_NormalDay);
        tvDayOff = view.findViewById(R.id.tvOvertimeListFra_DayOff);
        tvHoliday = view.findViewById(R.id.tvOvertimeListFra_Holiday);
        fabAdd = view.findViewById(R.id.fabOvertimeListFra_add);
        rvOvertime = view.findViewById(R.id.rvOvertimeListFra_List);
        prgShowMore = view.findViewById(R.id.prgOvertimeListFra_ShowMore);
        layoutSumary = view.findViewById(R.id.layoutOvertimeListFra_Sumary);
        layoutManager = new LinearLayoutManager(getActivity());
        fabAdd.setOnClickListener(this);
        overTimeList = new ArrayList<>();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fabOvertimeListFra_add:
                Intent intent = new Intent(getActivity(), FormOvertimeActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_OVERTIME);
                break;
            default:
                break;
        }
    }

    @Override
    public void getOvertimeSuccess(DataOvertime dataOvertime) {
        this.dataOvertime = dataOvertime;
        loadData(dataOvertime);
        totalOvertime = dataOvertime.getListDTO().getTotal();
        List<Overtime> list = dataOvertime.getListDTO().getList(); //get list record overtime in model
        if (list.size() != 0) {
            overTimeList.addAll(dataOvertime.getListDTO().getList());
            if (adapter == null) {
                layoutSumary.setVisibility(View.VISIBLE);
                adapter = new OvertimeListAdapter(getActivity(), overTimeList);
            } else {
                adapter.notifyDataSetChanged();
            }
            prgShowMore.setVisibility(View.GONE);
            tvNothingToShow.setVisibility(View.GONE);
            rvOvertime.setLayoutManager(layoutManager);
            rvOvertime.setAdapter(adapter);
            rvOvertime.addOnScrollListener(recyclerViewOnScrollListener);

        } else {
            tvNothingToShow.setText("No overtime to show!");
            tvNothingToShow.setVisibility(View.VISIBLE);
            prgShowMore.setVisibility(View.GONE);
        }
    }

    @Override
    public void getOvertimeFailure() {
        prgShowMore.setVisibility(View.VISIBLE);
    }

    private void loadData(DataOvertime dataOvertime) {
        //Set value for Dayoff, NormalDay, Holiday
        tvNomarlDay.setText(String.valueOf(dataOvertime.getNormal()));
        tvDayOff.setText(String.valueOf(dataOvertime.getDayOff()));
        tvHoliday.setText(String.valueOf(dataOvertime.getHoliday()));
    }
}
