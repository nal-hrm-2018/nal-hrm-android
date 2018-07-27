package com.dal.hrm_management.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dal.hrm_management.R;
import com.dal.hrm_management.models.ProjectEmployee;

import java.util.List;

/**
 * Created by Luu Ngoc Lan on 27-Jul-18.
 */

public class ProjectEmployeeAdapter extends RecyclerView.Adapter<ProjectEmployeeAdapter.ProjectEmployeeViewHolder>  {
    private List<ProjectEmployee> projects;
    private int rowLayout;
    private Context context;

    public ProjectEmployeeAdapter(List<ProjectEmployee> projects, int rowLayout, Context context) {
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
        holder.tv_nameProject.setText(projects.get(position).getNameProject());
        holder.tv_role.setText(projects.get(position).getRole());
        holder.tv_dayStart.setText(projects.get(position).getStartDay());
        holder.tv_dayEnd.setText(projects.get(position).getEndDay());
        holder.tv_status.setText(projects.get(position).getStatus());
        if (projects.get(position).getStatus().equals("Closed"))
            holder.imv_status.setColorFilter(R.color.colorAccent);
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
        ImageView imv_status;
        TextView tv_status;

        public ProjectEmployeeViewHolder(View itemView) {
            super(itemView);
            projects_layout = (LinearLayout) itemView.findViewById(R.id.projects_layout);
            tv_nameProject = (TextView) itemView.findViewById(R.id.tv_nameProject);
            tv_role = (TextView) itemView.findViewById(R.id.tv_roleEmployee);
            tv_dayStart = (TextView) itemView.findViewById(R.id.tv_dayStart);
            tv_dayEnd = (TextView) itemView.findViewById(R.id.tv_dayEnd);
            imv_status = (ImageView) itemView.findViewById(R.id.imv_status);
            tv_status = (TextView) itemView.findViewById(R.id.tv_status);
        }
    }
}
