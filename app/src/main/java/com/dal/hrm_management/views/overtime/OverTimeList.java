package com.dal.hrm_management.views.overtime;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dal.hrm_management.R;
import com.dal.hrm_management.adapter.listOT.FakeDataForListOvertime;
import com.dal.hrm_management.adapter.listOT.OvertimeListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OverTimeList extends Fragment{
    //In layout
    private TextView tvNomarlDay,tvDayOff,tvHoliday,tvNothingToShow;
    private ProgressBar prgShowMore;
    private FloatingActionButton fabAdd;
    private RecyclerView rvOvertime;

    private RecyclerView.LayoutManager layoutManager;

    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_overtime_list_for_personel, container, false);
        init();
        fakeData();
        return view;
    }

    private void fakeData() {
        rvOvertime.setVisibility(View.VISIBLE);
        prgShowMore.setVisibility(View.GONE);
        List<FakeDataForListOvertime> list = new ArrayList<FakeDataForListOvertime>();
        for (int i = 0 ; i < 5;i++){
            FakeDataForListOvertime data =
                    new FakeDataForListOvertime(UUID.randomUUID().toString(),
                            UUID.randomUUID().toString(),
                            UUID.randomUUID().toString(),
                            UUID.randomUUID().toString(),
                            UUID.randomUUID().toString(),
                            UUID.randomUUID().toString(),
                            UUID.randomUUID().toString(),
                            UUID.randomUUID().toString());
            list.add(data);
        }
        OvertimeListAdapter adapter = new OvertimeListAdapter(getContext(),list);
        rvOvertime.setLayoutManager(layoutManager);
        rvOvertime.setAdapter(adapter);
    }

    private void init() {
        tvNothingToShow = view.findViewById(R.id.tvOvertimeListFra_NothingToShow);
        tvNomarlDay = view.findViewById(R.id.tvOvertimeListFra_NormalDay);
        tvDayOff = view.findViewById(R.id.tvOvertimeListFra_DayOff);
        tvHoliday = view.findViewById(R.id.tvOvertimeListFra_Holiday);
        fabAdd = view.findViewById(R.id.fabOvertimeListFra_add);
        rvOvertime = view.findViewById(R.id.rvOvertimeListFra_List);
        prgShowMore = view.findViewById(R.id.prgOvertimeListFra_ShowMore);

        layoutManager = new LinearLayoutManager(getActivity());
    }
}
