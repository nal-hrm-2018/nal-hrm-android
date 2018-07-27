package com.dal.hrm_management.views.employee;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
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

import com.dal.hrm_management.R;
import com.dal.hrm_management.adapter.ProjectEmployeeAdapter;
import com.dal.hrm_management.models.ProjectEmployee;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectEmployeeFragment extends Fragment implements SearchView.OnQueryTextListener {

    private RecyclerView recyclerView;
    private List<ProjectEmployee> projects;
    private ProjectEmployeeAdapter adapter;

    public ProjectEmployeeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project_employee, container, false);
        setHasOptionsMenu(true);
        initUi(view);
        fakeData();
        setDataIntoView();
        return view;
    }

    private void setDataIntoView() {
        adapter = new ProjectEmployeeAdapter(projects, R.layout.item_project_employee, getActivity());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void fakeData() {
        projects = new ArrayList<>();
        projects.add(new ProjectEmployee("InBound", "PHP", "22/09/2018", "16/12/2018", "In-progress"));
        projects.add(new ProjectEmployee("InBound", "PHP", "22/09/2018", "16/12/2018", "In-progress"));
        projects.add(new ProjectEmployee("InBound", "PHP", "22/09/2018", "16/12/2018", "In-progress"));
        projects.add(new ProjectEmployee("In", "PHP", "22/09/2018", "16/12/2018", "In-progress"));
        projects.add(new ProjectEmployee("InBound", "PHP", "22/09/2018", "16/12/2018", "Closed"));
        projects.add(new ProjectEmployee("In", "PHP", "22/09/2018", "16/12/2018", "In-progress"));
        projects.add(new ProjectEmployee("InBound", "PHP", "22/09/2018", "16/12/2018", "In-progress"));
        projects.add(new ProjectEmployee("InBound", "PHP", "22/09/2018", "16/12/2018", "Closed"));
        projects.add(new ProjectEmployee("InBound", "PHP", "22/09/2018", "16/12/2018", "In-progress"));
    }

    private void initUi(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        setEvent();
    }

    private void setEvent() {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_searchproject_employee, menu);
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
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }
}
