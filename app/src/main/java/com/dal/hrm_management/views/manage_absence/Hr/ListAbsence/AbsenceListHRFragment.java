package com.dal.hrm_management.views.manage_absence.Hr.ListAbsence;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dal.hrm_management.R;
import com.dal.hrm_management.adapter.AbsenceListForHrAdapter;
import com.dal.hrm_management.models.fakeData.Absence;
import com.dal.hrm_management.models.manageAbsence.hr.ListAbsenceForHr;
import com.dal.hrm_management.presenters.ListEmployee.ListEmployeePresenter;
import com.dal.hrm_management.presenters.manageAbsence.Hr.ManageAbsenceHrPresenter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AbsenceListHRFragment extends Fragment implements IAbsenceHRFragment{
    private final String TAG = AbsenceListHRFragment.class.getSimpleName();

    private List<ListAbsenceForHr> absenceList;
    private AbsenceListForHrAdapter adapter;
    private RecyclerView recyclerView;
    private Spinner spn_year, spn_month;
    private TextView tv_time;
    private TextView tvNothingToShow;
    private ProgressBar progressBar;
    private Button btn_filter;
    private ManageAbsenceHrPresenter manageAbsenceHrPresenter;
    private int page =1;
    private final int pageSize = 20;
    private int totalAbsence=0;
    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
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
            if (firstitem+visibleItemCount >= totalItemCount && page*pageSize < totalAbsence){
                page++;
                Log.d(TAG + "current page: ", String.valueOf(page));
                progressBar.setVisibility(View.VISIBLE);
                manageAbsenceHrPresenter.getListAbsence(page,pageSize);

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
//        fakeData();
//        setDataIntoView();
        return view;
    }

    private void mapMVP() {
        manageAbsenceHrPresenter = new ManageAbsenceHrPresenter(this);
    }

    private void initData() {
        //get api
        absenceList = new ArrayList<>();
        manageAbsenceHrPresenter.getListAbsence(page,pageSize);
        //set adapter filter
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


//    private void setDataIntoView() {
//        adapter = new AbsenceListForHrAdapter(absenceList, R.layout.item_list_absence_of_hr, getActivity());
//        recyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//    }


    private void setEvent(View view) {
           }
    private void initUI(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.prgAbsenceFragHR_ShowMore);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        spn_month = (Spinner) view.findViewById(R.id.spn_month);
        spn_year = (Spinner) view.findViewById(R.id.spn_year);
        tvNothingToShow = view.findViewById(R.id.tvAbsenceFragHR_nothingToshow);
        tvNothingToShow.setVisibility(View.GONE);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.absence_hr_menu,menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_export:
                Toast.makeText(getActivity(),"Exporting....!!!",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getListAbsenceSuccess(int total, List<ListAbsenceForHr> list) {
        totalAbsence = total;
        recyclerView.setVisibility(View.VISIBLE);
        if(list.size() !=0){
            absenceList.addAll(list);
            if (adapter == null){
                adapter = new AbsenceListForHrAdapter(absenceList,R.layout.item_list_absence_of_hr, getActivity());
            }else{
                adapter.notifyDataSetChanged();
            }
            progressBar.setVisibility(View.GONE);
            recyclerView.setAdapter(adapter);
            recyclerView.addOnScrollListener(recyclerViewOnScrollListener);
        }else{
            tvNothingToShow.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void getListAbsenceFailure() {
        recyclerView.setVisibility(View.GONE);
        tvNothingToShow.setVisibility(View.VISIBLE);
    }
}


