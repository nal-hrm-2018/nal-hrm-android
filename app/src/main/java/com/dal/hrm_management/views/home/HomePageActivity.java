package com.dal.hrm_management.views.home;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
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
import android.widget.Toast;

import com.dal.hrm_management.R;
import com.dal.hrm_management.api.ApiImageClient;
import com.dal.hrm_management.api.RetrofitImageAPI;
import com.dal.hrm_management.models.login.MenuModel;
import com.dal.hrm_management.models.profile.Permission;
import com.dal.hrm_management.models.profile.Profile;
import com.dal.hrm_management.presenters.home.HomePresenter;
import com.dal.hrm_management.presenters.login.LoginPresenter;
import com.dal.hrm_management.utils.PermissionManager;
import com.dal.hrm_management.views.dashboard.DashboardFragment;
import com.dal.hrm_management.views.manage_absence.po.AbsenceManagerForPOFragment;
import com.dal.hrm_management.utils.VariableUltils;
import com.dal.hrm_management.views.absence.AbsenceViewFragment;
import com.dal.hrm_management.views.list_employee.ListEmployee;
import com.dal.hrm_management.views.login.LoginActivity;
import com.dal.hrm_management.views.manage_absence.Hr.ListAbsence.AbsenceListHRFragment;
import com.dal.hrm_management.views.manage_absence.Hr.holiday.HolidayHRFragment;
import com.dal.hrm_management.views.overtime.OverTimeList;
import com.dal.hrm_management.views.profile.ViewProfileActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Credentials;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, iHomeActivity {

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

    HomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mapMVP();
        initUI();
        getDataFromJson();
        initNavigationMenu();
        addEvent();
        //Chuyển fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AbsenceViewFragment()).commit();
        getSupportActionBar().setTitle(R.string.menu_absence);

    }

    private void getDataFromJson() {
        homePresenter.getProfile(LoginPresenter.token);
    }

    private void mapMVP() {
        homePresenter = new HomePresenter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.app_bar_menu, menu);
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
        imv_avatar = (ImageView) navHeader.findViewById(R.id.imv_avatar);
        navigation_menu.setSelected(true);
        tv_nameProfile = (TextView) navHeader.findViewById(R.id.tv_nameProfile);
        tv_emailProfile = (TextView) navHeader.findViewById(R.id.tv_emailProfile);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        expandableListView = findViewById(R.id.expandableListView);
    }

    private void loadNavHeader(Profile data) {
        tv_nameProfile.setText(data.getNameEmployee());
        tv_emailProfile.setText(data.getEmail());
        RetrofitImageAPI retrofitImageAPI = ApiImageClient.getImageClient().create(RetrofitImageAPI.class);
        String auth = Credentials.basic("hrm_testing", "hrm_testing");
        Call<ResponseBody> call;
        if (data.getAvatar() != null) {
            call = retrofitImageAPI.getImageDetails(auth, data.getAvatar().toString());
        } else {
            call = retrofitImageAPI.getImageDetails(auth, "default_avatar.png");
        }
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                        imv_avatar.setImageBitmap(bitmap);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_main);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        //không gọi tới super.onBackPressed() để disable nút back về login activity

    }

    private void populateExpandableList(final Profile data) {
        expandableListAdapter = new com.dal.hrm_management.adapter.ExpandableListAdapter(this, headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                if (headerList.get(groupPosition).isGroup) {
                    if (!headerList.get(groupPosition).hasChildren) {
                        if (headerList.get(groupPosition).id.equals(getString(R.string.menu_id_overtime))){
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new OverTimeList()).commit();
                            getSupportActionBar().setTitle(R.string.menu_overtime);
                        }else if (headerList.get(groupPosition).id.equals(getString(R.string.menu_id_dashboard))) {
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashboardFragment()).commit();
                            getSupportActionBar().setTitle(R.string.menu_dashboard);
                        } else if (headerList.get(groupPosition).id.equals(getString(R.string.menu_project))) {
                            getSupportActionBar().setTitle(R.string.menu_project);
                        } else if (headerList.get(groupPosition).id.equals(getString(R.string.menu_id_absence))) {
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AbsenceViewFragment()).commit();
                            getSupportActionBar().setTitle(R.string.menu_absence);
                        } else if (headerList.get(groupPosition).menuName.equals(getString(R.string.menu_logout))) {
                            showDialogLogout();
                        } Log.e("GROUP", headerList.get(groupPosition).menuName);
                        onBackPressed();
                    }
                } return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                if (childList.get(headerList.get(groupPosition)) != null) {
                    MenuModel model = childList.get(headerList.get(groupPosition)).get(childPosition);
                    Log.d("GROUP", model.menuName);
                    if (model.id.equals(getString(R.string.menu_id_manage_employee))) {
                        getSupportActionBar().setTitle(getString(R.string.menu_employee));
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListEmployee()).commit();
                    } else if ((model.id.equals(getString(R.string.menu_id_manage_absence)))) {
                        getSupportActionBar().setTitle(getString(R.string.menu_absence_empl));
                        if (data.getRole().getNameRole().equals("HR")) {
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AbsenceListHRFragment()).commit();
                        } else if (data.getRole().getNameRole().equals("PO")) {
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AbsenceManagerForPOFragment()).commit();
                        }
                    } else if (model.id.equals(getString(R.string.menu_id_manage_holiday))) {
                        getSupportActionBar().setTitle("Holiday");
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HolidayHRFragment()).commit();
                    }
                    Log.e("GROUP", model.menuName);
                    onBackPressed();
                }
                return false;
            }
        });
    }

    private void showDialogLogout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to log out?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PermissionManager.listPermissions.clear();
                Intent intent = new Intent(HomePageActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    private void prepareMenuData(Profile data) {
//        MenuModel menuModel = new MenuModel(getString(R.string.menu_id_dashboard), getString(R.string.menu_dashboard), true, false, getResources().getDrawable(R.drawable.ic_dashboard));
//        headerList.add(menuModel);
//        if (!menuModel.hasChildren) {
//            childList.put(menuModel, null);
//        }
        MenuModel menuModel = new MenuModel(getString(R.string.menu_id_overtime), getString(R.string.menu_overtime), true, false, getResources().getDrawable(R.drawable.ic_overtime));
        headerList.add(menuModel);
        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }
        menuModel = new MenuModel(getString(R.string.menu_id_absence), getString(R.string.menu_absence), true, false, getResources().getDrawable(R.drawable.ic_absence));
        headerList.add(menuModel);
        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }
        if (data.getRole().getNameRole().equals("HR") || data.getRole().getNameRole().equals("PO") || data.getRole().getNameRole().equals("SM")) {
            menuModel = new MenuModel(getString(R.string.menu_id_manage), getString(R.string.menu_manage), true, true, getResources().getDrawable(R.drawable.ic_manage));
            headerList.add(menuModel);

            List<MenuModel> childModelsList = new ArrayList<>();
            MenuModel chilModel;
            if (PermissionManager.isPermited(PermissionManager.listPermissions, "view_list_employee")) {
                chilModel = new MenuModel(getString(R.string.menu_id_manage_employee), getString(R.string.menu_employee), false, false, null);
                childModelsList.add(chilModel);
            }
            if (PermissionManager.isPermited(PermissionManager.listPermissions, "view_employee_absence_history") || PermissionManager.isPermited(PermissionManager.listPermissions, "view_project_absence_history")) {
                chilModel = new MenuModel(getString(R.string.menu_id_manage_absence), getString(R.string.menu_absence_empl), false, false, null);
                childModelsList.add(chilModel);
            }
            if (data.getRole().getNameRole().equals("HR")) {
                chilModel = new MenuModel(getString(R.string.menu_id_manage_holiday), "Holiday", false, false, null);
                childModelsList.add(chilModel);
            }

            if (menuModel.hasChildren) {
                childList.put(menuModel, childModelsList);
            }
        }
        menuModel = new MenuModel(getString(R.string.menu_id_logout), getString(R.string.menu_logout), true, false, getResources().getDrawable(R.drawable.ic_logout));
        headerList.add(menuModel);
        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }

    }

    private void getPermission(Profile data) {
        if (data.getPermissions() != null) {
            List<Permission> listPermissionModels = data.getPermissions();
            for (Permission permission : listPermissionModels) {
                PermissionManager.listPermissions.add(permission.getNamePermission());
            }
        }
    }

    private void initNavigationMenu() {
        mDrawerLayout = findViewById(R.id.drawer_main);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Edit absence success
        if (requestCode == VariableUltils.REQUEST_CODE && resultCode == Activity.RESULT_OK){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AbsenceListHRFragment()).commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void Success() {
        loadNavHeader(HomePresenter.profile);
        getPermission(HomePresenter.profile);
        prepareMenuData(HomePresenter.profile);
        populateExpandableList(HomePresenter.profile);
        Log.d("Home", "thanh cong");
    }

    @Override
    public void Failure() {
        Toast.makeText(this, "error server", Toast.LENGTH_LONG).show();
        prepareMenuData();
        populateExpandableList();
    }

    /**
     * Đăng nhập fail không có profile
     */
    private void populateExpandableList() {
        expandableListAdapter = new com.dal.hrm_management.adapter.ExpandableListAdapter(this, headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                if (headerList.get(groupPosition).isGroup) {
                    if (!headerList.get(groupPosition).hasChildren) {
                        if (headerList.get(groupPosition).menuName.equals(getString(R.string.menu_logout))) {
                            PermissionManager.listPermissions.clear();
                            Intent intent = new Intent(HomePageActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                        onBackPressed();
                    }
                }
                return false;
            }
        });
    }

    /**
     * Khi đã vào home mà không nhận được dữ liệu profile thì chỉ cần hiển thị đăng xuất
     */
    private void prepareMenuData() {
        MenuModel menuModel = new MenuModel(getString(R.string.menu_id_logout), getString(R.string.menu_logout), false,false, getResources().getDrawable(R.drawable.ic_logout));
        headerList.add(menuModel);
        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }
    }

}
