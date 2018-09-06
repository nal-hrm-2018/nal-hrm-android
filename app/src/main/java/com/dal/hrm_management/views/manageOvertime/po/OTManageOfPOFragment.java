package com.dal.hrm_management.views.manageOvertime.po;


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
import android.view.View;
import android.view.ViewGroup;

import com.dal.hrm_management.R;
import com.dal.hrm_management.adapter.listOT.OTManagerForPoAdapter;
import com.dal.hrm_management.models.fakeData.OverTime;
import com.dal.hrm_management.presenters.managerOvertime.po.OverTimeManageOfPoPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OTManageOfPOFragment extends Fragment implements IOTManageOfPOFragment {
    private RecyclerView rv_overtime;
    private List<OverTime> overTimeList;
    private OTManagerForPoAdapter adapter;
    private OverTimeManageOfPoPresenter overTimeManageOfPoPresenter;
    private SearchView searchView;

    public OTManageOfPOFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_otmanage_of_po, container, false);
        initData();
        initUi(view);
        initPresenter();
        return view;
    }

    private void initData() {
        overTimeList = new ArrayList<>();
        overTimeList.add(new OverTime("Nguyễn Văn A","ECM-1","12-9-2018","8:10PM","10:00PM","1","1","waiting","OT cho kịp tiến độ","Normal day"));
        overTimeList.add(new OverTime("Nguyễn Văn A","ECM-1","12-9-2018","8:10PM","10:00PM","1","1","waiting","OT cho kịp tiến độ","Day off"));
        overTimeList.add(new OverTime("Nguyễn Văn A","ECM-1","12-9-2018","8:10PM","10:00PM","1","1","waiting","OT làm cho cả tháng sau", "Day off"));
        overTimeList.add(new OverTime("Nguyễn Văn A","ECM-1","12-9-2018","8:10PM","10:00PM","1","1","waiting","OT làm cho cả tháng sau","Holiday"));
        overTimeList.add(new OverTime("Nguyễn Văn A","ECM-1","12-9-2018","8:10PM","10:00PM","1","1","waiting","OT cho kịp tiến độ","Holiday"));
        overTimeList.add(new OverTime("Nguyễn Văn A","ECM-1","12-9-2018","8:10PM","10:00PM","1","1","waiting","OT cho kịp tiến độ","Normal day"));
        overTimeList.add(new OverTime("Nguyễn Văn A","ECM-1","12-9-2018","8:10PM","10:00PM","1","1","waiting","OT làm cho cả tháng sau","Normal day"));
        overTimeList.add(new OverTime("Nguyễn Văn A","ECM-1","12-9-2018","8:10PM","10:00PM","1","1","waiting","OT cho kịp tiến độ","Day off"));
        overTimeList.add(new OverTime("Nguyễn Văn A","ECM-1","12-9-2018","8:10PM","10:00PM","1","1","waiting","OT cho kịp tiến độ","Holiday"));
    }

    private void initUi(View view) {
        rv_overtime = (RecyclerView) view.findViewById(R.id.rv_overtime);
        rv_overtime.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new OTManagerForPoAdapter(overTimeList, getActivity());
        rv_overtime.setAdapter(adapter);
    }

    private void initPresenter() {
        overTimeManageOfPoPresenter = new OverTimeManageOfPoPresenter(this);
    }

    @Override
    public void getData(List<OverTime> list) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.search, menu);
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
}
