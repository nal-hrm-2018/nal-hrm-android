package com.dal.hrm_management.views;


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
import android.widget.Toast;

import com.dal.hrm_management.R;
import com.dal.hrm_management.adapter.AbsenceManagerForPoAdapter;
import com.dal.hrm_management.models.absence.Absence;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AbsenceManagerForPOFragment extends Fragment implements AbsenceManagerForPoAdapter.AbsenceAdapterListener {

    private RecyclerView recyclerView;
    private List<Absence> absenceList;
    private AbsenceManagerForPoAdapter adapter;
    private SearchView searchView;

    public AbsenceManagerForPOFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_absence_manager_for_po, container, false);
        setHasOptionsMenu(true);
        initUi(view);
        setEvent(view);
        fakeData();
        getDataFromApi();
        setDataIntoView();
        return view;
    }

    private void getDataFromApi() {
        //ToDo: get data from api
    }

    private void setDataIntoView() {
        adapter = new AbsenceManagerForPoAdapter(absenceList, getActivity(), this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initUi(View view) {
        getActivity().setTitle(getString(R.string.absence_manage_for_po_fragment_title));
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    private void setEvent(View view) {

    }

    private void fakeData() {
        absenceList = new ArrayList<>();
        absenceList.add(new Absence("Trương Vô Kị", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ phép"));
        absenceList.add(new Absence("Kudo Shinichi", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ phép"));
        absenceList.add(new Absence("Chicken Stupid", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ phép"));
        absenceList.add(new Absence("Wonder Women", "Marvel", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Đi casting!!!", "Nghỉ phép"));
        absenceList.add(new Absence("Nhóc Maruko", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ phép"));
        absenceList.add(new Absence("Nhóc Maruko", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ phép"));
        absenceList.add(new Absence("Nhóc Maruko", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ phép"));
        absenceList.add(new Absence("Nhóc Maruko", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ phép"));
        absenceList.add(new Absence("Thanos", "Marvel", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Đi casting!!!", "Nghỉ phép"));
        absenceList.add(new Absence("Trương Vô Kị", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ phép"));
        absenceList.add(new Absence("Trương Vô Kị", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ phép"));
        absenceList.add(new Absence("Trương Vô Kị", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ phép"));
        absenceList.add(new Absence("Trương Vô Kị", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ phép"));
        absenceList.add(new Absence("Trương Vô Kị", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ phép"));
        absenceList.add(new Absence("Trương Vô Kị", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ phép"));
        absenceList.add(new Absence("Trương Vô Kị", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ phép"));
        absenceList.add(new Absence("Trương Vô Kị", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ phép"));
        absenceList.add(new Absence("Trương Vô Kị", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ phép"));
        absenceList.add(new Absence("Trương Vô Kị", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ phép"));
        absenceList.add(new Absence("Trương Vô Kị", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ phép"));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.absence_po_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                adapter.getFilter().filter(query);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_export:
                Toast.makeText(getActivity(), "Exporting....!!!", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAbsenceSelected(Absence absence) {
Toast.makeText(getActivity(),"Selected: "+absence.getName(),Toast.LENGTH_SHORT).show();
    }
}
