package com.dal.hrm_management.adapter.listProject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dal.hrm_management.R;
import com.dal.hrm_management.models.listProjectEmpJoining.Project;
import com.dal.hrm_management.utils.StringUtils;
import com.dal.hrm_management.utils.ViewDataUtils;

import java.util.List;


public class ProjectEmployeeJoiningAdapter extends RecyclerView.Adapter<ProjectEmployeeJoiningAdapter.ProjectEmployeeViewHolder> {
    private List<Project> projects;
    private int rowLayout;
    private Context context;

    public ProjectEmployeeJoiningAdapter(List<Project> projects, int rowLayout, Context context) {
        this.projects = projects;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public ProjectEmployeeJoiningAdapter.ProjectEmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ProjectEmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProjectEmployeeViewHolder holder, int position) {
        final Project project = projects.get(position);
        ViewDataUtils.setDataToView(holder.tv_nameProject, project.getNameProject());
//        if (project.getRole() != null) {
//            String role = projects.get(position).getProcesses().getRole().getNameRole();
//            if (role.toUpperCase().equals(Constant.ROLE_SM_AL)) {
//                holder.tv_role.setBackgroundColor(context.getResources().getColor(R.color.color_red));
//            } else if (role.toUpperCase().equals(Constant.ROLE_PO)) {
//                holder.tv_role.setBackgroundColor(context.getResources().getColor(R.color.color_orange));
//            } else if (role.toUpperCase().equals(Constant.ROLE_DEV)) {
//                holder.tv_role.setBackgroundColor(context.getResources().getColor(R.color.color_green));
//            }
//            holder.tv_role.setText(role);
//        } else holder.tv_role.setText(R.string.infor_null);
        String status = project.getStatus().getNameStatus();
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
        TextView tv_status;

        public ProjectEmployeeViewHolder(View itemView) {
            super(itemView);
            projects_layout = (LinearLayout) itemView.findViewById(R.id.projects_layout);
            tv_nameProject = (TextView) itemView.findViewById(R.id.tv_nameProject);
            tv_role = (TextView) itemView.findViewById(R.id.tv_roleEmployee);
            tv_status = (TextView) itemView.findViewById(R.id.tv_status);
            tv_role.setVisibility(View.GONE);
        }
    }
}
