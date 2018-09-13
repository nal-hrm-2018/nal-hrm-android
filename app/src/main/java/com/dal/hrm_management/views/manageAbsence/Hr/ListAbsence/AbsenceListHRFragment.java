package com.dal.hrm_management.views.manageAbsence.Hr.ListAbsence;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dal.hrm_management.R;
import com.dal.hrm_management.adapter.listAbsence.AbsenceListForHrAdapter;
import com.dal.hrm_management.models.manageAbsence.hr.ListAbsenceForHr;
import com.dal.hrm_management.presenters.manageAbsence.Hr.ManageAbsenceHrPresenter;
import com.dal.hrm_management.utils.VariableUltils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AbsenceListHRFragment extends Fragment implements IAbsenceHRFragment {
    private final String TAG = AbsenceListHRFragment.class.getSimpleName();

    private List<ListAbsenceForHr> absenceList;
    private List<ListAbsenceForHr> absenceListBackup;
    private AbsenceListForHrAdapter adapter;
    private RecyclerView recyclerView;
    private Spinner spn_year, spn_month;
    private TextView tvNothingToShow;
    private ProgressBar progressBar;
    private LinearLayout ll_filter;
    private ManageAbsenceHrPresenter manageAbsenceHrPresenter;
    private int page = 1;
    private final int pageSize = 20;
    private int totalAbsence = 0;
    private SearchView searchView;
    private Button btn_filter;
    LinearLayoutManager layoutManager;
    public boolean isShowFilter = true;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            int visibleItemCount = layoutManager.getChildCount();
            Log.d("visibleItemCount", String.valueOf(visibleItemCount));
            int totalItemCount = layoutManager.getItemCount();
            Log.d("totalItemCount", String.valueOf(totalItemCount));
            int firstitem = layoutManager.findFirstVisibleItemPosition();
            Log.d("firstitem", String.valueOf(firstitem));
            if (firstitem + visibleItemCount >= totalItemCount && page * pageSize < totalAbsence) {
                page++;
                Log.d(TAG + "current page: ", String.valueOf(page));
                progressBar.setVisibility(View.VISIBLE);
                manageAbsenceHrPresenter.getListAbsence(page, pageSize);
            }
        }
    };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_absence_list_hr, container, false);
        setHasOptionsMenu(true);
        initUI(view);
        mapMVP();
        initData();
        setEvent(view);
        return view;
    }

    private void mapMVP() {
        manageAbsenceHrPresenter = new ManageAbsenceHrPresenter(this);
    }

    private void initData() {
        //get api
        absenceList = new ArrayList<>();
        manageAbsenceHrPresenter.getListAbsence(page, pageSize);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        //set adapter filter
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        String[] year_arr = new String[year - 2015 + 1];
        for (int i = 2015, count = 0; i < year + 1; i++, count++) {
            year_arr[count] = i + "";
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, year_arr);
        spn_year.setAdapter(adapter);
        spn_year.setSelection(adapter.getPosition(year + ""));
        ArrayAdapter<CharSequence> adapter_month = ArrayAdapter.createFromResource(getActivity(), R.array.month_arr, android.R.layout.simple_spinner_item);
        adapter_month.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_month.setAdapter(adapter_month);
        spn_month.setSelection(month + 1);
    }


    private void setEvent(View view) {
        btn_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get year and month
                int year = Integer.parseInt((String) spn_year.getSelectedItem());
                int month = spn_month.getSelectedItemPosition();
                manageAbsenceHrPresenter.searchAbsenceWithMonth(month, year, page, pageSize);
            }
        });
    }

    private void initUI(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.prgAbsenceFragHR_ShowMore);
        ll_filter = (LinearLayout) view.findViewById(R.id.ll_filter);
        ll_filter.setVisibility(View.GONE);
        spn_month = (Spinner) view.findViewById(R.id.spn_month);
        spn_year = (Spinner) view.findViewById(R.id.spn_year);
        tvNothingToShow = view.findViewById(R.id.tvAbsenceFragHR_nothingToshow);
        layoutManager = new LinearLayoutManager(getActivity());
        btn_filter = (Button) view.findViewById(R.id.btn_filter);

        swipeRefreshLayout = view.findViewById(R.id.srlAbsenceListHrFra_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                reloadPage();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.absence_hr_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
        //set white color for icon_click
        Drawable icon_click = menu.getItem(2).getIcon();
        icon_click.mutate();
        icon_click.setColorFilter(getResources().getColor(R.color.color_white), PorterDuff.Mode.SRC_IN);
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
            case R.id.action_click:
                if (isShowFilter) {
                    ll_filter.setVisibility(View.VISIBLE);
                } else {
                    ll_filter.setVisibility(View.GONE);
                    //Show default list
                    loadDataListToRecyclerView(absenceListBackup);

                }
                isShowFilter = !isShowFilter;
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VariableUltils.REQUEST_CODE_ADD_ABSENCE && resultCode == Activity.RESULT_OK) {
            reloadPage();
        }
    }

    private void reloadPage() {
        page = 1;
        absenceList.clear();
        manageAbsenceHrPresenter.getListAbsence(page, pageSize);
    }

    @Override
    public void getListAbsenceSuccess(int total, List<ListAbsenceForHr> list) {

        this.absenceListBackup = list;
        totalAbsence = total;
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        loadDataListToRecyclerView(list);

    }

    public void loadDataListToRecyclerView(List<ListAbsenceForHr> list) {
        if (list.size() != 0) {
            absenceList.addAll(list);
            if (adapter == null) {
                adapter = new AbsenceListForHrAdapter(absenceList, R.layout.item_list_absence_of_hr, getActivity(), manageAbsenceHrPresenter);
            } else {
                adapter.notifyDataSetChanged();
            }
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
            if (page == 1) swipeRefreshLayout.setRefreshing(false);
            recyclerView.addOnScrollListener(recyclerViewOnScrollListener);
        } else {
            absenceList.clear();
            tvNothingToShow.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void getListAbsenceFailure() {
        recyclerView.setVisibility(View.GONE);
        tvNothingToShow.setVisibility(View.VISIBLE);
    }

    @Override
    public void deleteAbsenceSuccess() {
        Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
        reloadPage();
    }

    @Override
    public void deleteAbsenceFailure() {
        Toast.makeText(getActivity(), "Failure", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void searchAbsenceSuccess(int total, List<ListAbsenceForHr> list) {
        totalAbsence = total;
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        if (list.size() != 0) {
            absenceList.clear();
            absenceList.addAll(list);
            if (adapter == null) {
                adapter = new AbsenceListForHrAdapter(absenceList, R.layout.item_list_absence_of_hr, getActivity(), manageAbsenceHrPresenter);
            } else {
                adapter.notifyDataSetChanged();
            }
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
            recyclerView.addOnScrollListener(recyclerViewOnScrollListener);
            tvNothingToShow.setVisibility(View.GONE);
        } else {
            absenceList.clear();
            adapter.notifyDataSetChanged();
            tvNothingToShow.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void searchAbsenceFailed() {

    }

}


