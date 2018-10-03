package com.dal.hrm_management.views.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dal.hrm_management.R;
import com.dal.hrm_management.adapter.listProject.ProjectCompanyRunningAdapter;
import com.dal.hrm_management.adapter.listProject.ProjectEmployeeJoiningAdapter;
import com.dal.hrm_management.models.absence.DataAbsence;
import com.dal.hrm_management.models.eventInMonth.DataEvent;
import com.dal.hrm_management.models.listProjectEmpJoining.Project;
import com.dal.hrm_management.models.overtimePersonal.DataOvertime;
import com.dal.hrm_management.presenters.absence.AbsencePresenter;
import com.dal.hrm_management.presenters.login.LoginPresenter;
import com.dal.hrm_management.presenters.overtimePersonal.OvertimePersonalPresenter;
import com.dal.hrm_management.presenters.dashboard.DashboardPresenter;
import com.dal.hrm_management.utils.Constant;
import com.dal.hrm_management.utils.ViewDataUtils;
import com.dal.hrm_management.views.absence.FormAbsenceActivity;
import com.dal.hrm_management.views.overtime.formOvertime.FormOvertimeActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment implements OnChartValueSelectedListener, IDashboardFragment {
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
    private ImageButton btn_addAbsence;
    private ImageButton btn_addOvertime;
    private RecyclerView rv_notifications, rv_joiningProjects, rv_projectsCompanyRunning;
    private LinearLayout ll_generalInformation, ll_joingProjects, ll_projects;
    private List<Project> joiningProjectList;
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
        //Cho phép cầm chart xoay xoay
        mChart.setRotationEnabled(true);
//        //Xét description cho chart
//        Description description = new Description();
//        description.setText("Employees information");
//        description.setTextSize(15);
//        mChart.setDescription(description);

        mChart.getDescription().setEnabled(false);
        mChart.setTransparentCircleRadius(65f);
        mChart.setHoleRadius(35f);
        mChart.setTransparentCircleAlpha(0);
        mChart.setCenterText("Employees");
        mChart.setCenterTextSize(10);
//        mChart.setDrawEntryLabels(true);
        mChart.animateX(1000);
        addDataSet(mChart);
        mChart.setOnChartValueSelectedListener(this);
        mChart.getLegend().setEnabled(false);
//        mChart1 = (PieChart) view.findViewById(R.id.piechart2);
//        //Cho phép cầm chart xoay xoay
//        mChart1.setRotationEnabled(true);
//        //Xét description cho chart
//        description = new Description();
//        description.setText("Report sale in the 1st quarter");
//        description.setTextSize(10f);
//        mChart1.setDescription(description);
//
//        mChart1.setHoleRadius(0f);
//        mChart1.setTransparentCircleAlpha(0);
//        mChart1.setCenterText("PieChart");
//        mChart1.setCenterTextSize(5);
////        mChart1.setDrawEntryLabels(true);
//
//        addDataSet(mChart1);
//
//        mChart1.setOnChartValueSelectedListener(this);
        return view;
    }

    private void getData() {
        dashboardPresenter = new DashboardPresenter(this);
        AbsencePresenter absencePresenter = new AbsencePresenter(this);
        absencePresenter.getDataAbsence(1, 1); //vì chỉ lấy data là total remaining
        OvertimePersonalPresenter overtimePersonalPresenter = new OvertimePersonalPresenter(this);
        overtimePersonalPresenter.getOvertimePersonal(1, 1);
        if (!LoginPresenter.position.toUpperCase().equals(Constant.ROLE_BO)) {
            dashboardPresenter.getJoiningProject(1, 1);
        } else {
            dashboardPresenter.getInforEventInThisMonth();
        }
        if (LoginPresenter.position.toUpperCase().equals(Constant.ROLE_PO)) {
            dashboardPresenter.getProjectCompanyRunning();
        }
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

    private void addDataSet(PieChart pieChart) {
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();
        float[] yData = {73, 10, 10, 2};
        String[] xData = {"Official", "Probation", "Internship", "Part-time"};

        for (int i = 0; i < yData.length; i++) {
            yEntrys.add(new PieEntry(yData[i], i));
        }
        for (int i = 0; i < xData.length; i++) {
            xEntrys.add(xData[i]);
        }
        //Creating a Dataset
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(73f, 0));
        entries.add(new PieEntry(10f, 1));
        entries.add(new PieEntry(10f, 2));
        entries.add(new PieEntry(2f, 3));
        //pass the argument as an ArrayList
        PieDataSet dataSet = new PieDataSet(entries, "");
        //Define X-axis label
        ArrayList<String> labels = new ArrayList<>();
        labels.add("Official");
        labels.add("Probation");
        labels.add("Internship");
        labels.add("Part-time");
        //Set the data
//        PieDataSet pieDataSet=new PieDataSet(yEntrys,"Employee Sales");
//
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        dataSet.setSliceSpace(2);
        dataSet.setValueTextSize(12);
        //Them chu thich bieu do
        Legend legend = pieChart.getLegend();
//        legend.setForm(Legend.LegendForm.SQUARE);
        legend.setExtra(ColorTemplate.JOYFUL_COLORS, xData);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);

        PieData pieData = new PieData(dataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Toast.makeText(getContext(), "Value: "
                + e.getY()
                + ", index: "
                + h.getX()
                + ", DataSet index: "
                + h.getDataSetIndex(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected() {

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
}
