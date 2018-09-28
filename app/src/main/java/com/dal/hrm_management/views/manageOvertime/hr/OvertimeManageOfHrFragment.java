package com.dal.hrm_management.views.manageOvertime.hr;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dal.hrm_management.R;
import com.dal.hrm_management.adapter.listOvertime.OvertimeManageForHrAdapter;
import com.dal.hrm_management.models.manageOvertime.hr.viewList.DataOvertime;
import com.dal.hrm_management.models.manageOvertime.hr.viewList.Overtime;
import com.dal.hrm_management.presenters.managerOvertime.hr.ManageOvertimeOfHRPresenter;
import com.dal.hrm_management.presenters.managerOvertime.status.UpdateStatusPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OvertimeManageOfHrFragment extends Fragment implements IOvertimeManageOfHrFragment {

    private ManageOvertimeOfHRPresenter manageOvertimeOfHRPresenter;
    private DataOvertime dataOvertime;
    private List<Overtime> overtimeList;
    private OvertimeManageForHrAdapter adapter;
    private RecyclerView rvOvertime;
    private ProgressBar progressBar;
    private TextView tvOvertimeNothingtoShow;
    private LinearLayoutManager layoutManager;
    private int currentPage = 1;
    private int pageSize = 5;
    private int totalOvertime = 0;
    private UpdateStatusPresenter updateStatusPresenter;
    private SwipeRefreshLayout srlReload;


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
                progressBar.setVisibility(View.VISIBLE);
                Log.d("current page: ", String.valueOf(currentPage));
                manageOvertimeOfHRPresenter.getListOvertime(currentPage, pageSize);
            }
        }
    };

    public OvertimeManageOfHrFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_overtime_manage_of_hr, container, false);
        init(view);
        initPresenter();
        return view;
    }

    private void initPresenter() {
        updateStatusPresenter = new UpdateStatusPresenter(this);
        manageOvertimeOfHRPresenter = new ManageOvertimeOfHRPresenter(this);
        manageOvertimeOfHRPresenter.getListOvertime(currentPage, pageSize);
    }

    private void init(View view) {
        progressBar = view.findViewById(R.id.progressBar);
        tvOvertimeNothingtoShow = view.findViewById(R.id.tvOvertimeNothingToShow);
        rvOvertime = view.findViewById(R.id.rvOvertime);
        layoutManager = new LinearLayoutManager(getContext());
        overtimeList = new ArrayList<>();
        srlReload = view.findViewById(R.id.srlOvertimeManageOfHRFra_reload);
        srlReload.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reloadPage();
                rvOvertime.setVisibility(View.GONE);
                srlReload.setRefreshing(true);
            }
        });
    }

    @Override
    public void getOvertimeListSuccess(DataOvertime dataOvertime) {
        this.dataOvertime = dataOvertime;
        totalOvertime = dataOvertime.getTotal();
        List<Overtime> list = dataOvertime.getList(); //get list record overtime in model
        if (list.size() != 0) {
            overtimeList.addAll(list);
            if (adapter == null) {
                adapter = new OvertimeManageForHrAdapter(getActivity(), overtimeList, updateStatusPresenter);
            } else {
                adapter.notifyDataSetChanged();
            }
            if (currentPage == 1) srlReload.setRefreshing(false);
            rvOvertime.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            tvOvertimeNothingtoShow.setVisibility(View.GONE);
            rvOvertime.setLayoutManager(layoutManager);
            rvOvertime.setAdapter(adapter);
            rvOvertime.addOnScrollListener(recyclerViewOnScrollListener);

        } else {
            tvOvertimeNothingtoShow.setText("No overtime to show!");
            tvOvertimeNothingtoShow.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void getOvertimeListFailure() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateStatusSuccess(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        reloadPage();
    }

    @Override
    public void updateStatusFailure() {
        Toast.makeText(getActivity(), "Update status failure!", Toast.LENGTH_SHORT).show();
    }

    private void reloadPage() {
        adapter = null;
        overtimeList.clear();
        currentPage = 1;
        manageOvertimeOfHRPresenter.getListOvertime(currentPage, pageSize);
    }

}
