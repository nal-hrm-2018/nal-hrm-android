package com.dal.hrm_management.views.manageAbsence.Hr.holiday;


import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dal.hrm_management.R;
import com.dal.hrm_management.adapter.HolidayListAdapter;
import com.dal.hrm_management.presenters.holiday.holiday.HolidayPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HolidayHRFragment extends Fragment implements IHolidayFragment, View.OnClickListener {

    private RecyclerView recyclerView;
    private HolidayListAdapter adapter;
    private HolidayPresenter holidayPresenter;
    private FloatingActionButton btnAdd;
    public HolidayHRFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_holiday_hr, container, false);
        setHasOptionsMenu(true);
        initUI(view);
        mapMVP();
        return view;
    }

    private void mapMVP() {
        holidayPresenter = new HolidayPresenter(this);
        holidayPresenter.getHoliday();
    }



    private void initUI(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_holiday);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        btnAdd = view.findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void getHolidaySuccess(List<com.dal.hrm_management.models.holiday.Holiday> data) {
        adapter = new HolidayListAdapter(getActivity(), data);
        recyclerView.setAdapter(adapter);
        Toast.makeText(getContext(),"success",Toast.LENGTH_LONG).show();
    }

    @Override
    public void getHolidayFailure() {
        Toast.makeText(getContext(),"failure",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                Intent intent = new Intent(getActivity().getApplication(), FormHolidayActivity.class);
                startActivityForResult(intent,200);
                break;
        }
    }
}
