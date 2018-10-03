package com.dal.hrm_management.adapter.listProject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dal.hrm_management.R;
import com.dal.hrm_management.models.projectCompany.Project;
import com.dal.hrm_management.utils.StringUtils;
import com.dal.hrm_management.utils.ViewDataUtils;

import java.util.List;


public class ProjectCompanyRunningAdapter extends RecyclerView.Adapter<ProjectCompanyRunningAdapter.ProjectEmployeeViewHolder> {
    private List<Project> projects;
    private int rowLayout;
    private Context context;

    public ProjectCompanyRunningAdapter(List<Project> projects, int rowLayout, Context context) {
        this.projects = projects;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public ProjectCompanyRunningAdapter.ProjectEmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ProjectEmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProjectEmployeeViewHolder holder, int position) {
        final Project project = projects.get(position);
        ViewDataUtils.setDataToView(holder.tv_nameProject, project.getNameProject());
        String status = project.getStatus().getNameStatus();
        if (status.toLowerCase().equals("kick off")) {
            holder.tv_status.setBackgroundColor(context.getResources().getColor(R.color.color_green));
        } else if (status.toLowerCase().equals("in-progress")) {
            holder.tv_status.setBackgroundColor(context.getResources().getColor(R.color.color_violet));
        } else {
            holder.tv_status.setBackgroundColor(context.getResources().getColor(R.color.color_violet_2));
        }
        ViewDataUtils.setDataToView(holder.tv_status, StringUtils.toUpperCaseFirstChar(status));
        ViewDataUtils.setDataToView(holder.tv_namePO, project.getNamePO());
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
        TextView tv_namePO;
        TextView tv_status;

        public ProjectEmployeeViewHolder(View itemView) {
            super(itemView);
            projects_layout = (LinearLayout) itemView.findViewById(R.id.projects_layout);
            tv_nameProject = (TextView) itemView.findViewById(R.id.tv_nameProject);
            tv_namePO = (TextView) itemView.findViewById(R.id.tv_namePO);
            tv_status = (TextView) itemView.findViewById(R.id.tv_status);
        }
    }
}
