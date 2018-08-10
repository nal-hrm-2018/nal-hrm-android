package com.dal.hrm_management.views.list_employee;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import android.widget.Toast;

import com.dal.hrm_management.R;
import com.dal.hrm_management.adapter.listEmployee.ListEmployeeAdapter;
import com.dal.hrm_management.models.listEmployee.ListEmployees;
import com.dal.hrm_management.presenters.ListEmployee.ListEmployeePresenter;

import java.util.List;

public class ListEmployee extends Fragment implements IListEmployee {
    private static final String TAG = ListEmployee.class.getName();
    ListEmployeePresenter listEmployeePresenter;
    private RecyclerView rv_listEmp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        getActivity().setTitle(getString(R.string.list_employee_manage_title));
        mapMVP();
        listEmployeePresenter.getListEmployee();
        View view = inflater.inflate(R.layout.fragment_list_emp, container, false);
        rv_listEmp = view.findViewById(R.id.rv_listEmp);
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
    public void Success(List<ListEmployees> listEmployee) {
        ListEmployeeAdapter adapter = new ListEmployeeAdapter(getActivity(), listEmployee);
        rv_listEmp.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rv_listEmp.setAdapter(adapter);
    }

    @Override
    public void getListEmployeeFailure() {
        Log.d(TAG, "failure");

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.list_employee, menu);

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
}
