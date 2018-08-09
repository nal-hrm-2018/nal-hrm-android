package com.dal.hrm_management.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    private Spinner spn_year, spn_month;
    private TextView tv_time;
    private Button btn_filter;

    public AbsenceListHRFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_absence_list_hr, container, false);
        setHasOptionsMenu(true);
        initUI(view);
        initData();
        setEvent(view);
        fakeData();
        getDataFromApi();
        setDataIntoView();
        return view;
    }

    private void initData() {
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        String[] year_arr = new String[year - 2015 + 1];
        for (int i = 2015, count = 0; i < year + 1; i++, count++) {
            year_arr[count] = i+"";
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, year_arr);
        spn_year.setAdapter(adapter);
        ArrayAdapter<CharSequence> adapter_month = ArrayAdapter.createFromResource(getActivity(), R.array.month_arr, android.R.layout.simple_spinner_item);
        adapter_month.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_month.setAdapter(adapter_month);
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
           }
    private void initUI(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        spn_month = (Spinner) view.findViewById(R.id.spn_month);
        spn_year = (Spinner) view.findViewById(R.id.spn_year);
    }

}


