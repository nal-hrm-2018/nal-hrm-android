package com.dal.hrm_management.views.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.dal.hrm_management.R;
import com.dal.hrm_management.models.MenuModel;
import com.dal.hrm_management.views.absence.AbsenceView;
import com.dal.hrm_management.views.list_employee.ListEmployeeActivity;
import com.dal.hrm_management.views.login.LoginActivity;
import com.dal.hrm_management.views.profile.ViewProfileActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<MenuModel> headerList = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();

    private NavigationView navigation_menu;
    private View navHeader;
    private ImageView imv_avatar;
    private TextView tv_nameProfile, tv_emailProfile;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initUI();
        addEvent();
        prepareMenuData();
        populateExpandableList();
        initNavigationMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.app_bar_menu,menu);
        return true;
    }

    private void addEvent() {
        navHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initUI() {
        navigation_menu = (NavigationView) findViewById(R.id.navigation_menu);
        navigation_menu.setNavigationItemSelectedListener(this);
        navHeader = navigation_menu.getHeaderView(0);
        imv_avatar = navHeader.findViewById(R.id.imv_avatar);

        tv_nameProfile = navHeader.findViewById(R.id.tv_emailProfile_header);
        tv_emailProfile = navHeader.findViewById(R.id.tv_emailProfile_header);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        expandableListView = findViewById(R.id.expandableListView);
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
        expandableListAdapter = new com.dal.hrm_management.adapter.ExpandableListAdapter(this, headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                if (headerList.get(groupPosition).isGroup) {
                    if (!headerList.get(groupPosition).hasChildren) {

                        if (headerList.get(groupPosition).menuName.equals(getString(R.string.menu_dashboard))){
                            getSupportActionBar().setTitle(R.string.menu_dashboard);
                        }else if (headerList.get(groupPosition).menuName.equals(getString(R.string.menu_project))){
                            getSupportActionBar().setTitle(R.string.menu_project);
                        }else if (headerList.get(groupPosition).menuName.equals(getString(R.string.menu_absence))){
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AbsenceView()).commit();
                            getSupportActionBar().setTitle(R.string.menu_absence);
                        }else if (headerList.get(groupPosition).menuName.equals(getString(R.string.menu_logout))){
                            Intent intent = new Intent(HomePage.this,LoginActivity.class);
                            startActivity(intent);
                        }
                        Log.d("GROUP",headerList.get(groupPosition).menuName);
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
                    if (model.menuName.equals(getString(R.string.menu_employee))){
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container,new ListEmployeeActivity()).commit();
                    }


                Log.e("GROUP", model.menuName);
                    onBackPressed();
                }
                return false;
            }
        });
    }

    private void prepareMenuData() {

        MenuModel menuModel = new MenuModel(getString(R.string.menu_dashboard), true, false, getDrawable(R.drawable.ic_dashboard));
        headerList.add(menuModel);
        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }


        menuModel = new MenuModel(getString(R.string.menu_project), true, false, getDrawable(R.drawable.ic_project));

        headerList.add(menuModel);
        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }

        menuModel = new MenuModel(getString(R.string.menu_absence), true, false, getDrawable(R.drawable.ic_absence));
        headerList.add(menuModel);
        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }

        menuModel = new MenuModel(getString(R.string.menu_manage), true, true, getDrawable(R.drawable.ic_manage));
        headerList.add(menuModel);

        List<MenuModel> childModelsList = new ArrayList<>();
        MenuModel chilModel = new MenuModel(getString(R.string.menu_employee), false, false, getDrawable(R.drawable.ic_employee));
        childModelsList.add(chilModel);

        chilModel = new MenuModel(getString(R.string.menu_absence_empl), false, false, getDrawable(R.drawable.ic_absence));
        childModelsList.add(chilModel);

        if (menuModel.hasChildren) {
            Log.d("API123", "here");
            childList.put(menuModel, childModelsList);

        }
        menuModel = new MenuModel(getString(R.string.menu_logout), true, false, getDrawable(R.drawable.ic_logout));
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
