package com.dal.hrm_management.views;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.dal.hrm_management.R;
import com.dal.hrm_management.adapter.AbsenceListForHrAdapter;
import com.dal.hrm_management.models.absence.Absence;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AbsenceListHRFragment extends Fragment {
    private List<Absence> absenceList;
    private AbsenceListForHrAdapter adapter;
    private RecyclerView recyclerView;
    private TextView tv_time;
    private Button btn_filter;
    private Spinner spinner;
    String[] LOAINGHI = {"Nghỉ phép", "Nghỉ không lương", "Nghỉ bệnh"};
    public AbsenceListHRFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_absence_list_hr, container, false);
        setHasOptionsMenu(true);
        initUI(view);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.support_simple_spinner_dropdown_item, LOAINGHI);
        spinner.setAdapter(adapter);
        setEvent(view);
        fakeData();
        getDataFromApi();
        setDataIntoView();
        return view;
    }

    private void getDataFromApi() {
    }

    private void setDataIntoView() {
        adapter = new AbsenceListForHrAdapter(absenceList, R.layout.item_list_absence_of_hr, getActivity());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void fakeData() {
        absenceList = new ArrayList<>();
        absenceList.add(new Absence("Trương Vô Kị", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ phép"));
        absenceList.add(new Absence("Kudo Shinichi", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ phép"));
        absenceList.add(new Absence("Chicken Stupid", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ phép"));
        absenceList.add(new Absence("Wonder Women", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Đi casting!!!", "Nghỉ phép"));
        absenceList.add(new Absence("Nhóc Maruko", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ trừ lương"));
        absenceList.add(new Absence("Nhóc Maruko", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ phép"));
        absenceList.add(new Absence("Nhóc Maruko", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ phép"));
        absenceList.add(new Absence("Nhóc Maruko", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ phép"));
        absenceList.add(new Absence("Thanos", "Marvel", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Đi casting!!!", "Nghỉ phép"));
        absenceList.add(new Absence("Trương Vô Kị", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ phép"));
        absenceList.add(new Absence("Jackie Chan", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ phép"));
        absenceList.add(new Absence("Thành long", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ phép"));
        absenceList.add(new Absence("Trương Vô Kị", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ phép"));
        absenceList.add(new Absence("Trương Vô Kị", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ phép"));
        absenceList.add(new Absence("Trương Vô Kị", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ không lương"));
        absenceList.add(new Absence("Trương Vô Kị", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ theo bảo hiểm"));
        absenceList.add(new Absence("Trương Vô Kị", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ phép"));
        absenceList.add(new Absence("Trương Vô Kị", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ phép"));
        absenceList.add(new Absence("Trương Vô Kị", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ phép"));
        absenceList.add(new Absence("Trương Vô Kị", "EMC_201", "3/8/2018", "4/8/2018", "Chưa xét", "Muốn đăng kí", "Thích thì nghỉ", "Nghỉ phép"));

    }

    private void setEvent(View view) {
        tv_time.setOnClickListener(new View.OnClickListener() {
            // @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        final int date = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        final int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                tv_time.setTextSize(getResources().getDimension(R.dimen.text_size_medium));
                tv_time.setText( month + "/" + year);
            }
        }, year, month, date);
        datePickerDialog.show();
    }

    private void initUI(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        tv_time = (TextView) view.findViewById(R.id.tv_time);
        spinner = view.findViewById(R.id.testspinner);

    }

}


