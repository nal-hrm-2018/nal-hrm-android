package com.dal.hrm_management.views;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.dal.hrm_management.R;
import com.dal.hrm_management.adapter.HolidayListAdapter;
import com.dal.hrm_management.models.holiday.Holiday;
import com.dal.hrm_management.views.manage_absence.FormHolidayActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HolidayHRFragment extends Fragment {

    private RecyclerView recyclerView;
    private HolidayListAdapter adapter;
    private List<Holiday> holidays;
    public HolidayHRFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_holiday_hr, container, false);
        setHasOptionsMenu(true);
        fakeData();
        initUI(view);
        return view;
    }

    private void fakeData() {
        holidays = new ArrayList<>();
        holidays.add(new Holiday("Lễ 2/9","Nghỉ lễ","2/9/2018","2/9/2018","Nghỉ lễ quẩy thôi!"));
        holidays.add(new Holiday("Lễ 2/9","Nghỉ lễ","2/9/2018","2/9/2018","Nghỉ lễ quẩy thôi!"));
        holidays.add(new Holiday("Lễ 2/9","Nghỉ lễ","2/9/2018","2/9/2018","Nghỉ lễ quẩy thôi!"));
        holidays.add(new Holiday("Lễ 2/9","Nghỉ lễ","2/9/2018","2/9/2018","Nghỉ lễ quẩy thôi!"));
        holidays.add(new Holiday("Lễ 2/9","Nghỉ lễ","2/9/2018","2/9/2018","Nghỉ lễ quẩy thôi!"));
        holidays.add(new Holiday("Lễ 2/9","Nghỉ lễ","2/9/2018","2/9/2018","Nghỉ lễ quẩy thôi!"));
        holidays.add(new Holiday("Lễ 2/9","Nghỉ lễ","2/9/2018","2/9/2018","Nghỉ lễ quẩy thôi!"));
        holidays.add(new Holiday("Lễ 2/9","Nghỉ lễ","2/9/2018","2/9/2018","Nghỉ lễ quẩy thôi!"));
        holidays.add(new Holiday("Lễ 2/9","Nghỉ lễ","2/9/2018","2/9/2018","Nghỉ lễ quẩy thôi!"));
    }

    private void initUI(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_holiday);
        adapter = new HolidayListAdapter(getActivity(), holidays);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.holiday_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_add:
                Intent intent = new Intent(getActivity().getApplication(), FormHolidayActivity.class);
                startActivityForResult(intent,200);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
