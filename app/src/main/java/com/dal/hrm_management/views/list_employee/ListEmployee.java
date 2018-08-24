package com.dal.hrm_management.views.list_employee;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dal.hrm_management.R;
import com.dal.hrm_management.adapter.listEmployee.ListEmployeeAdapter;
import com.dal.hrm_management.models.listEmployee.Employee;
import com.dal.hrm_management.presenters.ListEmployee.ListEmployeePresenter;
import com.dal.hrm_management.utils.PermissionManager;

import java.util.ArrayList;
import java.util.List;

public class ListEmployee extends Fragment implements IListEmployee, ListEmployeeAdapter.EmployeesAdapterListener {
    private static final String TAG = ListEmployee.class.getName();
    ListEmployeePresenter listEmployeePresenter;
    private RecyclerView rv_listEmp;
    private ProgressBar list_emp_pb_loadEmp;
    private TextView fra_tv_nothingToShow;
    private List<Employee> listEmployees = new ArrayList<Employee>(); //Chứa danh sách nhân viên lưu vào trong bộ nhớ local
    private SearchView searchView;
    //current page
    private int current_page =1;
    private int pageSize = 15;
    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
    ListEmployeeAdapter adapter;
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
            if (firstitem+visibleItemCount >= totalItemCount && current_page < ListEmployeePresenter.total_page_employee){
                current_page++;
                list_emp_pb_loadEmp.setVisibility(View.VISIBLE);
                listEmployeePresenter.getListEmployee(current_page,pageSize);

            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        getActivity().setTitle(getString(R.string.list_employee_manage_title));
        mapMVP();
        listEmployeePresenter.getListEmployee(current_page,pageSize);
        View view = inflater.inflate(R.layout.fragment_list_emp, container, false);
        rv_listEmp = view.findViewById(R.id.rv_listEmp);
        fra_tv_nothingToShow = view.findViewById(R.id.fra_tv_nothingToShow);
        list_emp_pb_loadEmp = view.findViewById(R.id.list_emp_pb_loadEmp);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void mapMVP() {
        listEmployeePresenter = new ListEmployeePresenter(this);
    }


    @Override
    public void Success(List<Employee> listEmployee) {
        list_emp_pb_loadEmp.setVisibility(View.GONE);
        listEmployees.addAll(listEmployee);
        if (adapter == null){
            adapter = new ListEmployeeAdapter(getActivity(), listEmployees,this);
        }else{
            adapter.notifyDataSetChanged();
        }
        rv_listEmp.setLayoutManager(layoutManager);
        rv_listEmp.setAdapter(adapter);
        rv_listEmp.addOnScrollListener(recyclerViewOnScrollListener);
    }

    @Override
    public void getListEmployeeFailure() {
        Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT).show();
        fra_tv_nothingToShow.setVisibility(View.VISIBLE);
        list_emp_pb_loadEmp.setVisibility(View.GONE);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.list_employee, menu);
        if(!PermissionManager.isPermited(PermissionManager.listPermissions,"search_employee")){
            menu.findItem(R.id.action_search).setVisible(false);
        }
        if(!PermissionManager.isPermited(PermissionManager.listPermissions,"export_list_employee")){
            menu.findItem(R.id.action_export).setVisible(false);
        }
        if(!PermissionManager.isPermited(PermissionManager.listPermissions,"add_new_employee")){
            menu.findItem(R.id.action_add_new).setVisible(false);
        }
        if (!PermissionManager.isPermited(PermissionManager.listPermissions, "import_employee_file")) {
            menu.findItem(R.id.action_import).setVisible(false);
        }
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_export:
                Toast.makeText(getActivity(), "Exporting....!!!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_import:
                Toast.makeText(getActivity(), "Import....!!!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_add_new:
                Toast.makeText(getActivity(), "Add new....!!!", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onEmployeeSelected(ListEmployee employee) {

    }
}
