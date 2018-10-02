package com.dal.hrm_management.adapter.listProjectEmployee;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dal.hrm_management.R;
import com.dal.hrm_management.models.listProjectEmpAttend.ProjectAndProcess;
import com.dal.hrm_management.utils.Constant;
import com.dal.hrm_management.utils.StringUtils;

import java.util.ArrayList;

public class ProjectEmployeeAdapter extends RecyclerView.Adapter<ProjectEmployeeAdapter.ProjectEmployeeViewHolder> {
    private ArrayList<ProjectAndProcess> projects;
    private int rowLayout;
    private Context context;

    public ProjectEmployeeAdapter(ArrayList<ProjectAndProcess> projects, int rowLayout, Context context) {
        this.projects = projects;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public ProjectEmployeeAdapter.ProjectEmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ProjectEmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProjectEmployeeViewHolder holder, int position) {
        holder.tv_nameProject.setText(projects.get(position).getProject().getNameProject());
        if (projects.get(position).getProcesses().getRole() != null) {
            String role = projects.get(position).getProcesses().getRole().getNameRole();
            if (role.toUpperCase().equals(Constant.ROLE_SM_AL)) {
                holder.tv_role.setBackgroundColor(context.getResources().getColor(R.color.color_red));
            } else if (role.toUpperCase().equals(Constant.ROLE_PO)) {
                holder.tv_role.setBackgroundColor(context.getResources().getColor(R.color.color_orange));
            } else if (role.toUpperCase().equals(Constant.ROLE_DEV)) {
                holder.tv_role.setBackgroundColor(context.getResources().getColor(R.color.color_green));
            }
            holder.tv_role.setText(role);
        } else holder.tv_role.setText(R.string.infor_null);
        if (projects.get(position).getProcesses().getStartDate() != null) {
            holder.tv_dayStart.setText(StringUtils.yyyy_mm_ddTodd_mm_yyyy(projects.get(position).getProcesses().getStartDate()));
        } else {
            holder.tv_dayStart.setText(R.string.infor_null);
        }
        if (projects.get(position).getProcesses().getEndDate() != null) {
            holder.tv_dayEnd.setText(StringUtils.yyyy_mm_ddTodd_mm_yyyy(projects.get(position).getProcesses().getEndDate()));
        } else {
            holder.tv_dayEnd.setText(R.string.infor_null);
        }
        String status = projects.get(position).getProject().getStatus().getNameStatus();
        if (status.toLowerCase().equals("kick off")) {
            holder.tv_status.setBackgroundColor(context.getResources().getColor(R.color.color_green));
        } else if (status.toLowerCase().equals("in-progress")) {
            holder.tv_status.setBackgroundColor(context.getResources().getColor(R.color.color_violet));
        } else {
            holder.tv_status.setBackgroundColor(context.getResources().getColor(R.color.color_violet_2));
        }
        holder.tv_status.setText(StringUtils.toUpperCaseFirstChar(status));
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ProjectEmployeeViewHolder extends RecyclerView.ViewHolder {
        LinearLayout projects_layout;
        TextView tv_nameProject;
        TextView tv_role;
        TextView tv_dayStart;
        TextView tv_dayEnd;
        TextView tv_status;

        public ProjectEmployeeViewHolder(View itemView) {
            super(itemView);
            projects_layout = (LinearLayout) itemView.findViewById(R.id.projects_layout);
            tv_nameProject = (TextView) itemView.findViewById(R.id.tv_nameProject);
            tv_role = (TextView) itemView.findViewById(R.id.tv_roleEmployee);
            tv_dayStart = (TextView) itemView.findViewById(R.id.tv_dayStart);
            tv_dayEnd = (TextView) itemView.findViewById(R.id.tv_dayEnd);
            tv_status = (TextView) itemView.findViewById(R.id.tv_status);
        }
    }
}
