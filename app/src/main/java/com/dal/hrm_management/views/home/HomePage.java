package com.dal.hrm_management.views.home;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.dal.hrm_management.R;
import com.dal.hrm_management.models.MenuModel;
import com.dal.hrm_management.views.list_employee.ListEmployeeActivity;
import com.dal.hrm_management.views.login.LoginActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HomePage extends AppCompatActivity {
    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<MenuModel> headerList = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        expandableListView = findViewById(R.id.expandableListView);
        prepareMenuData();
        populateExpandableList();
        initNavigationMenu();
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_main);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void populateExpandableList() {
        expandableListAdapter = new com.dal.hrm_management.adapter.ExpandableListAdapter(this,headerList,childList);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                if (headerList.get(groupPosition).isGroup) {
                    if (!headerList.get(groupPosition).hasChildren) {
                        if (headerList.get(groupPosition).menuName
                                .equals(getString(R.string.Dashboard))){

                        }else if (headerList.get(groupPosition).menuName
                                .equals(getString(R.string.Project))){

                        }else if (headerList.get(groupPosition).menuName
                                .equals(getString(R.string.Absence))){

                        }else if (headerList.get(groupPosition).menuName
                                .equals(getString(R.string.Logout))){
                            Intent intent = new Intent(HomePage.this,LoginActivity.class);
                            startActivity(intent);
                        }

                        Log.e("GROUP",headerList.get(groupPosition).menuName);
                        onBackPressed();
                    }
                }
                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                if (childList.get(headerList.get(groupPosition)) != null) {
                    MenuModel model = childList.get(headerList.get(groupPosition)).get(childPosition);
                    Log.d("GROUP",model.menuName);
                    if (model.menuName.equals(getString(R.string.Employee))){
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container,new ListEmployeeActivity()).commit();
                    }else if (model.menuName.equals(getString(R.string.child_absence))){

                    }
                    onBackPressed();
                }
                return false;
            }
        });
    }

    private void prepareMenuData() {
        MenuModel menuModel =
                new MenuModel(getString(R.string.Dashboard),
                        true,
                        false,
                        getDrawable(R.drawable.ic_dashboard));
        headerList.add(menuModel);
        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }

        menuModel =
                new MenuModel(getString(R.string.Project),
                        true,
                        false,
                        getDrawable(R.drawable.ic_project));
        headerList.add(menuModel);
        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }

        menuModel =
                new MenuModel(getString(R.string.Absence),
                        true,
                        false,
                        getDrawable(R.drawable.ic_absence));
        headerList.add(menuModel);
        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }

        menuModel =
                new MenuModel(getString(R.string.Manage),
                        true,
                        true,
                        getDrawable(R.drawable.ic_manage));
        headerList.add(menuModel);

        List<MenuModel> childModelsList = new ArrayList<>();
        MenuModel chilModel =
                new MenuModel(getString(R.string.Employee),
                        false,
                        false,
                        getDrawable(R.drawable.ic_employee));
        childModelsList.add(chilModel);

        chilModel =
                new MenuModel(getString(R.string.child_absence),
                        false,
                        false,
                        getDrawable(R.drawable.ic_absence));
        childModelsList.add(chilModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);

        }
        menuModel =
                new MenuModel(getString(R.string.Logout),
                        true,
                        false,
                        getDrawable(R.drawable.ic_logout));
        headerList.add(menuModel);
        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }
    }
    private void initNavigationMenu() {
        mDrawerLayout = findViewById(R.id.drawer_main);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
    }
}
