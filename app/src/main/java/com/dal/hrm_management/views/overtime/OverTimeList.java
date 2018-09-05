package com.dal.hrm_management.views.overtime;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dal.hrm_management.R;

public class OverTimeList extends Fragment{
    //In layout
    private TextView tvNomarlDay,tvDayOff,tvHoliday,tvNothingToShow;
    private ProgressBar prgShowMore;
    private FloatingActionButton fabAdd;
    private RecyclerView rvOvertime;

    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_overtime_list_for_personel, container, false);
        init();
        return view;
    }

    private void init() {
        tvNothingToShow = view.findViewById(R.id.tvOvertimeListFra_NothingToShow);
        tvNomarlDay = view.findViewById(R.id.tvOvertimeListFra_NormalDay);
        tvDayOff = view.findViewById(R.id.tvOvertimeListFra_DayOff);
        tvHoliday = view.findViewById(R.id.tvOvertimeListFra_Holiday);
        fabAdd = view.findViewById(R.id.fabOvertimeListFra_add);
        rvOvertime = view.findViewById(R.id.rvOvertimeListFra_List);
        prgShowMore = view.findViewById(R.id.prgOvertimeListFra_ShowMore);
    }
}
