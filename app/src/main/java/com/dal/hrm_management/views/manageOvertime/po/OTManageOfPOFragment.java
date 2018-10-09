package com.dal.hrm_management.views.manageOvertime.po;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dal.hrm_management.R;
import com.dal.hrm_management.adapter.listOvertime.OTManagerForPoAdapter;
import com.dal.hrm_management.models.manageAbsence.po.listProjectInProgress.DataProject;
import com.dal.hrm_management.models.manageAbsence.po.listProjectInProgress.Project;
import com.dal.hrm_management.models.manageOvertime.po.viewlist.Data;
import com.dal.hrm_management.models.manageOvertime.po.viewlist.OverTime;
import com.dal.hrm_management.presenters.managerOvertime.po.OverTimeManageOfPoPresenter;
import com.dal.hrm_management.presenters.managerOvertime.status.UpdateStatusPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OTManageOfPOFragment extends Fragment implements IOTManageOfPOFragment {
    private RecyclerView rv_overtime;
    private ProgressBar prgShowMore;
    private TextView tvNothing;
    private Spinner spnProjects;
    private OTManagerForPoAdapter adapter;
    private OverTimeManageOfPoPresenter overTimeManageOfPoPresenter;
    private SearchView searchView;
    private View viewInflater;
    private int current_page = 1;
    private int pageSize = 10;
    private int totalOvertimes;
    private int totalProjects ;
    private String idProjectSelected;
    private List<Project> projectList;
    private List<String> idProjectList;
    private List<String> nameProjectList;
    private List<OverTime> listOvertime;
    private LinearLayoutManager layoutManager;
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
            int totalItemCount = layoutManager.getItemCount();
            int firstitem = layoutManager.findFirstVisibleItemPosition();
            if (firstitem + visibleItemCount >= totalItemCount && current_page * pageSize < totalOvertimes) {
                current_page++;
                prgShowMore.setVisibility(View.VISIBLE);
                overTimeManageOfPoPresenter.getListOverTimeForPO(idProjectSelected, current_page, pageSize);
            }
        }
    };

    public OTManageOfPOFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewInflater = inflater.inflate(R.layout.fragment_otmanage_of_po, container, false);
        init();
        initPresenter();
        getDataProjectInProgress();
        setEvent(viewInflater);
        return viewInflater;
    }

    private void getDataProjectInProgress() {
        overTimeManageOfPoPresenter.getListProjectInProgressOfPO(current_page, pageSize);
    }

    private void init() {
        rv_overtime = viewInflater.findViewById(R.id.rvOTManagePOFra_list);
        layoutManager = new LinearLayoutManager(getContext());
        rv_overtime.setLayoutManager(layoutManager);
        rv_overtime.addOnScrollListener(recyclerViewOnScrollListener);
        prgShowMore = viewInflater.findViewById(R.id.prgOTManagePOFra_showMore);
        tvNothing = viewInflater.findViewById(R.id.tvOTManagePOFra_Nothing);
        spnProjects = viewInflater.findViewById(R.id.spnProjects);
        listOvertime = new ArrayList<>();
        idProjectList = new ArrayList<>();
        nameProjectList = new ArrayList<>();
        projectList = new ArrayList<>();
        srlReload = viewInflater.findViewById(R.id.srlOvertimeManageOfPOFra_reload);
        srlReload.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlReload.setRefreshing(true);
                rv_overtime.setVisibility(View.GONE);
                reloadPage();
            }
        });
    }


    private void initPresenter() {
        overTimeManageOfPoPresenter = new OverTimeManageOfPoPresenter(this);
        updateStatusPresenter = new UpdateStatusPresenter(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.search, menu);
        super.onCreateOptionsMenu(menu, inflater);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                adapter.getFilter().filter(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String query) {
//                adapter.getFilter().filter(query);
//                return false;
//            }
//        });
    }

    private void setEvent(View view) {
        spnProjects.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               getAllOvertimeWithValueSelected();
            }

            public void onNothingSelected(AdapterView<?> parent) {
               getAllOvertimeWithValueSelected();
            }

            private void getAllOvertimeWithValueSelected() {
                if (spnProjects.getSelectedItem().equals("- No Project -")) {
                    prgShowMore.setVisibility(View.GONE);
                    rv_overtime.setVisibility(View.GONE);
                } else {
                    idProjectSelected = idProjectList.get(spnProjects.getSelectedItemPosition());
                    //call presenter to get list overtime of project
                    overTimeManageOfPoPresenter.getListOverTimeForPO(idProjectSelected, current_page, pageSize);
                }
            }
        });
    }

    @Override
    public void getListProjectInProgressSuccess(DataProject data) {
        ArrayAdapter<String> adapter;
        this.totalProjects = data.getTotal();
        if (totalProjects != 0) {
            projectList = data.getProject();
            for (Project project : projectList) {
                idProjectList.add(project.getIdProject());
                nameProjectList.add(project.getNameProject());
            }
        } else {
            idProjectList.add("- No Project -");
            tvNothing.setText("Haven't no overtime!");
            tvNothing.setVisibility(View.VISIBLE);
        }
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, nameProjectList);
        spnProjects.setAdapter(adapter);
    }

    @Override
    public void getListProjectInProgressFailed() {

    }

    @Override
    public void getListOTSucess(Data data) {
        this.totalOvertimes = data.getTotal();
        if (totalOvertimes != 0) {
            this.listOvertime.clear();
            this.listOvertime = data.getList();
            adapter = new OTManagerForPoAdapter(listOvertime, getActivity(), updateStatusPresenter);
            rv_overtime.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            tvNothing.setVisibility(View.GONE);
        } else {
            this.listOvertime.clear();
            tvNothing.setText("No overtime in this project!");
            tvNothing.setVisibility(View.VISIBLE);
            prgShowMore.setVisibility(View.GONE);
            rv_overtime.setVisibility(View.GONE);
        }
        prgShowMore.setVisibility(View.GONE);
        rv_overtime.setVisibility(View.VISIBLE);
        if (current_page == 1) srlReload.setRefreshing(false);
//        totalOvertimes = data.getTotal();
//        listOvertime.addAll(data.getList());
//        adapter = new OTManagerForPoAdapter(listOvertime, getContext());
//        rv_overtime.setAdapter(adapter);
//        prgShowMore.setVisibility(View.GONE);
//        tvNothing.setVisibility(View.GONE);
    }

    @Override
    public void getListOTFailure() {
        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
        prgShowMore.setVisibility(View.GONE);
        tvNothing.setVisibility(View.GONE);
        rv_overtime.setVisibility(View.GONE);
    }

    @Override
    public void getlistOTError(Throwable t) {
        Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void updateStatusOvertimeSuccess(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        reloadPage();
    }

    @Override
    public void updateStatusOvertimeFailure() {
        Toast.makeText(getContext(), "Update status failure!", Toast.LENGTH_SHORT).show();
    }

    private void reloadPage() {
        adapter = null;
        listOvertime.clear();
        current_page = 1;
        overTimeManageOfPoPresenter.getListOverTimeForPO(idProjectSelected, current_page, pageSize);
    }

}
