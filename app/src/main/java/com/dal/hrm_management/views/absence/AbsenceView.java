package com.dal.hrm_management.views.absence;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toolbar;

import com.dal.hrm_management.R;
import com.dal.hrm_management.adapter.AbsenceAdapter;
import com.dal.hrm_management.models.fakeData.AbsenceModel;
import com.dal.hrm_management.presenters.absence.AbsencePresenter;

import java.util.ArrayList;
import java.util.List;

public class AbsenceView extends Fragment implements IAbsenceView {
    RecyclerView rv_absence;
    AbsencePresenter absencePresenter;
    MenuItem addAbsence;
    public List<AbsenceModel> arr = new ArrayList<AbsenceModel>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View root =  inflater.inflate(R.layout.fragment_absence,container,false);
        rv_absence = root.findViewById(R.id.fragment_absence_rv_list);
        absencePresenter = new AbsencePresenter(this);
        absencePresenter.initData();
        rv_absence.setHasFixedSize(false);
        rv_absence.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        rv_absence.setAdapter(new AbsenceAdapter(absencePresenter.arr,getActivity()));

        return  root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.app_bar_menu_absence,menu);
        addAbsence = menu.findItem(R.id.action_add_absence);
        addAbsence.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getActivity(),Form_absence.class);
                startActivity(intent);
                return false;
            }
        });

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
