package com.dal.hrm_management.views.manageOvertime.po;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dal.hrm_management.R;
import com.dal.hrm_management.adapter.listOvertime.OTManagerForPoAdapter;
import com.dal.hrm_management.models.manageOvertime.po.viewlist.Data;
import com.dal.hrm_management.models.manageOvertime.po.viewlist.ItemOverTimePO;
import com.dal.hrm_management.presenters.managerOvertime.po.OverTimeManageOfPoPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OTManageOfPOFragment extends Fragment implements IOTManageOfPOFragment {
    private RecyclerView rv_overtime;
    private ProgressBar prgShowMore;
    private TextView tvNothing;

    private OTManagerForPoAdapter adapter;
    private OverTimeManageOfPoPresenter overTimeManageOfPoPresenter;
    private SearchView searchView;

    View viewinflate;

    private int current_page =1;
    private int pageSize =20;
    private int total;
    List<ItemOverTimePO> listOvertime;
    private LinearLayoutManager layoutManager;
    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstitem = layoutManager.findFirstVisibleItemPosition();
            if (firstitem+visibleItemCount >= totalItemCount && current_page*pageSize < total){
                current_page++;
                prgShowMore.setVisibility(View.VISIBLE);
                overTimeManageOfPoPresenter.getListOverTimeForPO("PRO_003",current_page,pageSize);

            }
        }
    };



    public OTManageOfPOFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewinflate = inflater.inflate(R.layout.fragment_otmanage_of_po, container, false);
        init();
        initPresenter();
        callApi();
        return viewinflate;
    }

    private void callApi() {
        overTimeManageOfPoPresenter.getListOverTimeForPO("PRO_0003",current_page,pageSize);
    }

    private void init() {
        rv_overtime = viewinflate.findViewById(R.id.rvOTManagePOFra_list);
        layoutManager = new LinearLayoutManager(getContext());
        rv_overtime.setLayoutManager(layoutManager);
        prgShowMore = viewinflate.findViewById(R.id.prgOTManagePOFra_showMore);
        tvNothing = viewinflate.findViewById(R.id.tvOTManagePOFra_Nothing);
        listOvertime = new ArrayList<>();
    }


    private void initPresenter() {
        overTimeManageOfPoPresenter = new OverTimeManageOfPoPresenter(this);
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
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                adapter.getFilter().filter(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String query) {
//                adapter.getFilter().filter(query);
//                return false;
//            }
//        });
    }

    @Override
    public void getListOTSucess(Data data) {
        total = data.getTotal();
        listOvertime.addAll(data.getList());
        adapter = new OTManagerForPoAdapter(listOvertime,getContext());
        rv_overtime.setAdapter(adapter);
        prgShowMore.setVisibility(View.GONE);
        tvNothing.setVisibility(View.GONE);
    }

    @Override
    public void getListOTFailure() {
        Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT).show();
        prgShowMore.setVisibility(View.GONE);
        tvNothing.setVisibility(View.GONE);
    }

    @Override
    public void getlistOTError(Throwable t) {
        Toast.makeText(getContext(),t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
    }
}
