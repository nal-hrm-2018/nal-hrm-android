package com.dal.hrm_management.views.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dal.hrm_management.R;
import com.dal.hrm_management.adapter.dashboard.NotificationAdapter;
import com.dal.hrm_management.adapter.listProject.ProjectCompanyRunningAdapter;
import com.dal.hrm_management.adapter.listProject.ProjectEmployeeJoiningAdapter;
import com.dal.hrm_management.models.absence.DataAbsence;

import com.dal.hrm_management.models.dashboard.employee.Data;

import com.dal.hrm_management.models.dashboard.eventInMonth.DataEvent;

import com.dal.hrm_management.models.dashboard.notification.Notification;
import com.dal.hrm_management.models.listProjectEmpJoining.Project;
import com.dal.hrm_management.models.overtimePersonal.DataOvertime;
import com.dal.hrm_management.presenters.absence.AbsencePresenter;
import com.dal.hrm_management.presenters.dashboard.DashboardEmployeePresenter;
import com.dal.hrm_management.presenters.login.LoginPresenter;
import com.dal.hrm_management.presenters.overtimePersonal.OvertimePersonalPresenter;
import com.dal.hrm_management.presenters.dashboard.DashboardPresenter;
import com.dal.hrm_management.utils.Constant;
import com.dal.hrm_management.utils.ViewDataUtils;
import com.dal.hrm_management.views.absence.FormAbsenceActivity;
import com.dal.hrm_management.views.overtime.formOvertime.FormOvertimeActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;


public class DashboardFragment extends Fragment implements IDashboardFragment{
    private final String TAG = DashboardFragment.class.getSimpleName();

    private View view;
    private PieChart mChart;
    private TextView tv_totalRemainingAbsence;
    private TextView tv_normalOvertime;
    private TextView tv_weekendOvertime;
    private TextView tv_holidayOvertime;
    private TextView tv_newEmployees;
    private TextView tv_birthdays;
    private TextView tv_employeesQuit;
    private TextView tv_titleEventInmonth;
    private TextView tvNotificationNothing;
    private ImageButton btn_addAbsence;
    private ImageButton btn_addOvertime;
    private RecyclerView rv_notifications, rv_joiningProjects, rv_projectsCompanyRunning;
    private LinearLayout ll_generalInformation, ll_joingProjects, ll_projects;
    private List<Project> joiningProjectList;

    private ProjectEmployeeJoiningAdapter adapter;
    private DashboardEmployeePresenter dashboardEmployeePresenter;
    private List<com.dal.hrm_management.models.projectCompany.Project> projectCompanyRunningList;
    private ProjectEmployeeJoiningAdapter projectEmployeeJoiningAdapter;
    private ProjectCompanyRunningAdapter projectCompanyRunningAdapter;
    private DashboardPresenter dashboardPresenter;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        initView(view);
        checkRoleToDisplayInfor();
        getData();
        setEvent();



        return view;
    }

    private void pieChart(Data data) {
        mChart.setUsePercentValues(true);
        //Cho phép cầm chart xoay xoay
        mChart.setRotationEnabled(true);
        mChart.setUsePercentValues(false);
        //Không hiển thị description
        mChart.getDescription().setEnabled(false);
        mChart.setTransparentCircleRadius(65f);
        mChart.setHoleRadius(35f);
        mChart.setTransparentCircleAlpha(0);
        mChart.setCenterTextSize(10);
        mChart.setCenterText(data.getTotalEmployee() + "Employees");
        mChart.setCenterTextSize(10);
        //Hiệu ứng chuyển cảnh cho đẹp
        mChart.animateX(1000);
        addDataSet(mChart,data);
    }

    private void getData() {
        dashboardPresenter = new DashboardPresenter(this);
        AbsencePresenter absencePresenter = new AbsencePresenter(this);
        absencePresenter.getDataAbsence(1, 1); //vì chỉ lấy data là total remaining
        OvertimePersonalPresenter overtimePersonalPresenter = new OvertimePersonalPresenter(this);
        //piechart
        dashboardEmployeePresenter = new DashboardEmployeePresenter(this);
        dashboardEmployeePresenter.getDashboardDEmployee();

        overtimePersonalPresenter.getOvertimePersonal(1, 1);
        if (!LoginPresenter.position.toUpperCase().equals(Constant.ROLE_BO)) {
            dashboardPresenter.getJoiningProject(1, 1);
        } else {
            dashboardPresenter.getInforEventInThisMonth();
        }
        if (LoginPresenter.position.toUpperCase().equals(Constant.ROLE_PO)) {
            dashboardPresenter.getProjectCompanyRunning();
        }

        //notification
        dashboardPresenter.getNotification();

    }

    private void setEvent() {
        btn_addAbsence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), FormAbsenceActivity.class));
            }
        });

        btn_addOvertime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), FormOvertimeActivity.class));
            }
        });
    }

    private void initView(View view) {
        tv_totalRemainingAbsence = view.findViewById(R.id.tv_totalRemainingAbsence);
        tv_normalOvertime = view.findViewById(R.id.tv_normalOvertime);
        tv_weekendOvertime = view.findViewById(R.id.tv_weekendOvertime);
        tv_holidayOvertime = view.findViewById(R.id.tv_holidayOvertime);
        tv_newEmployees = view.findViewById(R.id.tv_newEmployees);
        tv_birthdays = view.findViewById(R.id.tv_birthdays);
        tv_employeesQuit = view.findViewById(R.id.tv_employeesQuit);
        tv_titleEventInmonth = view.findViewById(R.id.tv_titleEventInmonth);
        tvNotificationNothing = view.findViewById(R.id.tvDashBoardNotification_nothing);
        rv_notifications = view.findViewById(R.id.rv_notifications);
        rv_joiningProjects = view.findViewById(R.id.rv_joiningProjects);
        rv_projectsCompanyRunning = view.findViewById(R.id.rv_projects);
        ll_generalInformation = view.findViewById(R.id.ll_generalInformation);
        ll_joingProjects = view.findViewById(R.id.ll_joingProjects);
        ll_projects = view.findViewById(R.id.ll_projects);
        btn_addAbsence = view.findViewById(R.id.btn_addAbsence);
        btn_addOvertime = view.findViewById(R.id.btn_addOvertime);
        mChart = (PieChart) view.findViewById(R.id.piechart1);
        rv_joiningProjects.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rv_projectsCompanyRunning.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rv_notifications.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    private void checkRoleToDisplayInfor() {
        if (LoginPresenter.position.toUpperCase().equals(Constant.ROLE_PO)) {
            ll_projects.setVisibility(View.VISIBLE);
            ll_generalInformation.setVisibility(View.GONE);
        }
        if (LoginPresenter.position.toUpperCase().equals(Constant.ROLE_DEV)) {
            ll_projects.setVisibility(View.GONE);
            ll_generalInformation.setVisibility(View.GONE);
        }
        if (LoginPresenter.position.toUpperCase().equals(Constant.ROLE_BO)) {
            ll_generalInformation.setVisibility(View.VISIBLE);
            ll_projects.setVisibility(View.GONE);
            ll_joingProjects.setVisibility(View.GONE);
        }
    }
    private void addDataSet(PieChart pieChart, Data data) {
        ArrayList<PieEntry> entries = new ArrayList<>();
        if (data.getOfficial() > 0){
            entries.add(new PieEntry(data.getOfficial(),"Offical"));
        }
        if (data.getProbation() > 0){
            entries.add(new PieEntry(data.getProbation(),"Probation"));

        }
        if (data.getPartTime() > 0){
            entries.add(new PieEntry(data.getPartTime(),"Parttime"));
        }
        if (data.getInternship() != 0) {
            entries.add(new PieEntry(data.getInternship(),"Internship"));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Type Employee");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setValueTextSize(10);

        //Them chu thich bieu do
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.SQUARE);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);

        PieData pieData = new PieData(dataSet);
        pieChart.setEntryLabelTextSize(0);
        pieChart.setData(pieData);

    }

    @Override
    public void getInforAbsenceSuccess(DataAbsence dataAbsence) {
        Double totalRemaining = dataAbsence.getTotalRemain();
        ViewDataUtils.setDataToView(tv_totalRemainingAbsence, totalRemaining);
    }

    @Override
    public void getInforAbsenceFailure() {
        tv_totalRemainingAbsence.setText(R.string.infor_null);
    }

    @Override
    public void getInforOvertimeSuccess(DataOvertime dataOvertime) {
        ViewDataUtils.setDataToView(tv_normalOvertime, dataOvertime.getNormal());
        ViewDataUtils.setDataToView(tv_weekendOvertime, dataOvertime.getDayOff());
        ViewDataUtils.setDataToView(tv_holidayOvertime, dataOvertime.getHoliday());
    }

    @Override
    public void getInforOvertimrFailure() {
        tv_normalOvertime.setText(R.string.infor_null);
        tv_weekendOvertime.setText(R.string.infor_null);
        tv_holidayOvertime.setText(R.string.infor_null);
    }

    @Override
    public void getInforJoiningProjectSuccess(List<Project> projectList) {
        joiningProjectList = new ArrayList<>();
        joiningProjectList.addAll(projectList);
        projectEmployeeJoiningAdapter = new ProjectEmployeeJoiningAdapter(joiningProjectList, R.layout.item_project_joining_dashboard, getContext());
        rv_joiningProjects.setAdapter(projectEmployeeJoiningAdapter);
        projectEmployeeJoiningAdapter.notifyDataSetChanged();
    }

    @Override
    public void getInforJoiningProjectFailure() {

    }

    @Override
    public void getInforProjectsCompanySuccess(List<com.dal.hrm_management.models.projectCompany.Project> projectCompanyList) {
        projectCompanyRunningList = new ArrayList<>();
        projectCompanyRunningList.addAll(projectCompanyList);
        projectCompanyRunningAdapter = new ProjectCompanyRunningAdapter(projectCompanyRunningList, R.layout.item_project_running_in_company_dashboard, getContext());
        rv_projectsCompanyRunning.setAdapter(projectCompanyRunningAdapter);
        projectCompanyRunningAdapter.notifyDataSetChanged();
    }

    @Override
    public void getInforProjectsCompanyFailure() {

    }

    @Override
    public void getInforEventInThisMonthSuccess(DataEvent dataEvent) {
        if (dataEvent != null) {
            loadDataEventInthisMonth(dataEvent);
        }
    }

    private void loadDataEventInthisMonth(DataEvent dataEvent) {
        ViewDataUtils.setDataToView(tv_newEmployees, dataEvent.getNewEmployees());
        ViewDataUtils.setDataToView(tv_birthdays, dataEvent.getBirthdays());
        ViewDataUtils.setDataToView(tv_employeesQuit, dataEvent.getEmployeesQuit());
    }

    @Override
    public void getInforEventInThisMonthFailure() {
        setDefaultView();
    }

    private void setDefaultView() {
        tv_newEmployees.setText(R.string.infor_null);
        tv_employeesQuit.setText(R.string.infor_null);
        tv_birthdays.setText(R.string.infor_null);
    }

    @Override
    public void getDashboardEmployeeSuccess(Data data) {
        Log.d(TAG,"get employee dashboard success");
        pieChart(data);
    }

    @Override
    public void getDashboardEmployeeFailure() {
        Log.d(TAG,"get employee dashboard failure");
    }

    @Override
    public void getDashboardNotificationSuccess(List<Notification> data) {
        if (data.size() > 0) {
            NotificationAdapter notificationAdapter = new NotificationAdapter(getContext(), data);
            rv_notifications.setLayoutManager(new LinearLayoutManager(getContext()));
            rv_notifications.setAdapter(notificationAdapter);
            tvNotificationNothing.setVisibility(View.GONE);
        }else {
            rv_notifications.setVisibility(View.GONE);
            tvNotificationNothing.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void getDashboardNotificationFailure(String message) {
        Toast.makeText(getContext(),"get notification" + message,Toast.LENGTH_SHORT).show();
    }
}
