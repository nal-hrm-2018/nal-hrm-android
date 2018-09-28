package com.dal.hrm_management.views.manageAbsence.po;


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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.dal.hrm_management.R;
import com.dal.hrm_management.adapter.listAbsence.AbsenceManagerForPoAdapter;
import com.dal.hrm_management.models.manageAbsence.po.listAbsence.Absence;
import com.dal.hrm_management.models.manageAbsence.po.listAbsence.DataAbsence;
import com.dal.hrm_management.models.manageAbsence.po.listProjectInProgress.DataProject;
import com.dal.hrm_management.models.manageAbsence.po.listProjectInProgress.Project;
import com.dal.hrm_management.presenters.manageAbsence.po.ManageAbsencePOPresenter;
import com.dal.hrm_management.utils.PermissionManager;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AbsenceManagerForPOFragment extends Fragment implements AbsenceManagerForPoAdapter.AbsenceAdapterListener, IAbsenceManagerForPOFragment {

    private RecyclerView recyclerView;
    private AbsenceManagerForPoAdapter adapter;
    private SearchView searchView;
    private Spinner spnProjects;
    private TextView tv_message_nothing;
    private ProgressBar progressBar;
    private List<Project> projectList;
    private List<String> idProjects;
    private List<String> nameProjects;
    private List<Absence> absenceList;
    private ManageAbsencePOPresenter manageAbsencePOPresenter;
    private int currentPage = 1;
    private int pageSize = 15;
    private int totalProject = 0;
    private int totalAbsenceInProject = 0;
    private String idProjectSelected;

    public AbsenceManagerForPOFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_absence_manager_for_po, container, false);
        setHasOptionsMenu(true);
        initPresenter();
        initUi(view);
        initData();
        setEvent(view);
        return view;
    }

    private void initData() {
        projectList = new ArrayList<>();
        absenceList = new ArrayList<>();
        nameProjects = new ArrayList<>();
        idProjects = new ArrayList<>();
    }

    private void initPresenter() {
        manageAbsencePOPresenter = new ManageAbsencePOPresenter(this);
        manageAbsencePOPresenter.getListProject(currentPage, pageSize);
    }

    private void initUi(View view) {
        getActivity().setTitle(getString(R.string.absence_manage_for_po_fragment_title));
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        spnProjects = (Spinner) view.findViewById(R.id.spnProjects);
        tv_message_nothing = (TextView) view.findViewById(R.id.tv_message_nothing);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
    }

    private void setEvent(View view) {
        spnProjects.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idProjectSelected = idProjects.get(spnProjects.getSelectedItemPosition());
                //call presenter to get list absence of project
                manageAbsencePOPresenter.getListAbsence(idProjectSelected);
            }

            public void onNothingSelected(AdapterView<?> parent) {
                idProjectSelected = idProjects.get(spnProjects.getSelectedItemPosition());
                if (idProjectSelected.equals("- No Project -")) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    //call presenter to get list absence of project
                    manageAbsencePOPresenter.getListAbsence(idProjectSelected);
                }
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.absence_po_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

        //set item export no visible--> feature not done
        MenuItem item = menu.findItem(R.id.action_export);
        item.setVisible(false);

        if (!PermissionManager.isPermited(PermissionManager.listPermissions, "export_project_absence_history")) {
            menu.getItem(R.id.action_export).setVisible(false);
        }
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                adapter.getFilter().filter(query);
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_export:
//                Toast.makeText(getActivity(), "Exporting....!!!", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAbsenceSelected(Absence absence) {
    }

    @Override
    public void getListProjectInProgressSuccess(DataProject data) {
        ArrayAdapter<String> adapter;
        this.totalProject = data.getTotal();
        if (totalProject != 0) {
            projectList = data.getProject();
            for (Project project : projectList) {
                nameProjects.add(project.getNameProject());
                idProjects.add(project.getIdProject());
            }
        } else {
            nameProjects.add("- No Project -");
            tv_message_nothing.setText("Haven't no absence!");
            tv_message_nothing.setVisibility(View.VISIBLE);
        }
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, nameProjects);
        spnProjects.setAdapter(adapter);
    }

    @Override
    public void getListProjectInProgressFailed() {
    }

    @Override
    public void getListAbsenceInProjectSuccess(DataAbsence dataAbsence) {
        this.totalAbsenceInProject = dataAbsence.getTotal();
        if (totalAbsenceInProject != 0) {
            this.absenceList.clear();
            this.absenceList = dataAbsence.getAbsence();
            adapter = new AbsenceManagerForPoAdapter(absenceList, getActivity(), this);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            tv_message_nothing.setVisibility(View.GONE);
        } else {
            this.absenceList.clear();
            tv_message_nothing.setText("No absence in this project!");
            tv_message_nothing.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void getListAbsenceInProjectFailed() {
        progressBar.setVisibility(View.GONE);
    }
}
