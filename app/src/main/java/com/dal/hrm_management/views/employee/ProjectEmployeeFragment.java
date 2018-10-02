package com.dal.hrm_management.views.employee;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dal.hrm_management.R;
import com.dal.hrm_management.adapter.listProjectEmployee.ProjectEmployeeAdapter;
import com.dal.hrm_management.models.listProjectEmpAttend.ProjectAndProcess;
import com.dal.hrm_management.presenters.listProject.ListProjectEmpAttendPresenter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectEmployeeFragment extends Fragment
        implements SearchView.OnQueryTextListener,IListProjectEmployeeAttendFragment {

    private RecyclerView recyclerView;
    private ArrayList<ProjectAndProcess> projects = new ArrayList<>();
    private ProjectEmployeeAdapter adapter;
    private TextView tv_message_nothing;
    private ListProjectEmpAttendPresenter presenter;

    public ProjectEmployeeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project_employee, container, false);
        setHasOptionsMenu(true);
        initUi(view);
        mapMVP();
        //get Extra
        int id_employee = getActivity().getIntent().getIntExtra("id_employee", -1);
        presenter.getListProjectEmployeeAttend(id_employee);
        return view;
    }

    private void mapMVP() {
        presenter = new ListProjectEmpAttendPresenter(this);
    }

    private void initUi(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        tv_message_nothing = (TextView) view.findViewById(R.id.tv_message_nothing);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search, menu);
        super.onCreateOptionsMenu(menu, inflater);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }


    @Override
    public void Success(ArrayList<ProjectAndProcess> projectAndProcess) {
        if(projectAndProcess.size()!=0){
            projects.addAll(projectAndProcess);
            adapter = new ProjectEmployeeAdapter(projects,R.layout.item_project_employee, getActivity());
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            tv_message_nothing.setText("No Project to show!");
            tv_message_nothing.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void Failure() {

    }

    @Override
    public void PermissionDeny() {

    }
}
