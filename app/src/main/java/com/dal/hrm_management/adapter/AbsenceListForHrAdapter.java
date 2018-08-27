package com.dal.hrm_management.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.dal.hrm_management.views.absenceEmployee.DetailAbsenceEmployeeActivity;
import com.dal.hrm_management.R;
import com.dal.hrm_management.models.fakeData.Absence;

import java.util.List;

/**
 * Created by Luu Ngoc Lan on 06-Aug-18.
 */

public class AbsenceListForHrAdapter extends RecyclerView.Adapter<AbsenceListForHrAdapter.MyViewHolder> implements Filterable {
    private List<Absence> absenceList;
    private int rowLayout;
    private Context context;

    public AbsenceListForHrAdapter(List<Absence> absenceList, int rowLayout, Context context) {
        this.absenceList = absenceList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new AbsenceListForHrAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_nameEmployee.setText(absenceList.get(position).getName());
        holder.tv_type.setText(absenceList.get(position).getType());
        holder.tv_from.setText(absenceList.get(position).getFrom());
        holder.tv_to.setText(absenceList.get(position).getTo());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                //TODO: get id employee to get detail employee absence
                Toast.makeText(context.getApplicationContext(), "Position: "+position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context.getApplicationContext(), DetailAbsenceEmployeeActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return absenceList.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tv_nameEmployee;
        TextView tv_type;
        TextView tv_from;
        TextView tv_to;
        ItemClickListener itemClickListener;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_nameEmployee = (TextView) itemView.findViewById(R.id.tv_nameEmployee);
            tv_type = (TextView) itemView.findViewById(R.id.tv_type);
            tv_from = (TextView) itemView.findViewById(R.id.tv_from);
            tv_to = (TextView) itemView.findViewById(R.id.tv_to);
            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);
        }
    }
    public interface AbsenceAdapterListener {
        void onItemClick(View view, int position);
    }
}
