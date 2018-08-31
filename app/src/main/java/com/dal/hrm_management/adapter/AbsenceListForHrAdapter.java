package com.dal.hrm_management.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.dal.hrm_management.R;
import com.dal.hrm_management.models.manageAbsence.hr.ListAbsenceForHr;
import com.dal.hrm_management.utils.VariableUltils;
import com.dal.hrm_management.views.absence.FormAbsenceActivity;
import com.dal.hrm_management.views.absenceEmployee.DetailAbsenceEmployeeActivity;

import java.util.List;

/**
 * Created by Luu Ngoc Lan on 06-Aug-18.
 */

public class AbsenceListForHrAdapter extends RecyclerView.Adapter<AbsenceListForHrAdapter.MyViewHolder> implements Filterable {
    private List<ListAbsenceForHr> absenceList;
    private int rowLayout;
    private Context context;
    private int id_employee;

    public AbsenceListForHrAdapter(List<ListAbsenceForHr> absenceList, int rowLayout, Context context) {
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
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_nameEmployee.setText(absenceList.get(position).getNameEmployee());
        holder.tv_type.setText(absenceList.get(position).getAbsenceType().getNameAbsenceType().replace("_"," "));
        holder.tv_from.setText(absenceList.get(position).getFromDate());
        holder.edt_Resaon.setText(absenceList.get(position).getReason());
        holder.tv_TimeAbsence.setText(absenceList.get(position).getAbsenceTime().getDescription());
        holder.tv_to.setText(absenceList.get(position).getToDate());
        holder.imvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("position", String.valueOf(position));
                ListAbsenceForHr absence = absenceList.get(position);
                Intent intent = new Intent(context, FormAbsenceActivity.class);
                intent.putExtra(VariableUltils.KEY_PUT_EXTRA_EDIT_ABSENCE,absence);
                ((Activity)context).startActivityForResult(intent,VariableUltils.REQUEST_CODE);
            }
        });
        holder.imvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Warning");
                builder.setMessage("Delete absence of" + absenceList.get(position).getNameEmployee()+" ?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Call api delete
                    }
                });
                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                /**
                 * get id employee at position clicked and put intent to get infor detail absence of employee
                 */
                id_employee = absenceList.get(position).getIdEmployee();
                Intent intent = new Intent(context.getApplicationContext(), DetailAbsenceEmployeeActivity.class);
                intent.putExtra("id_employee",id_employee);
                intent.putExtra("name_employee",absenceList.get(position).getNameEmployee());
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
        EditText edt_Resaon;
        TextView tv_TimeAbsence;
        private ImageView imvEdit;
        private ImageView imvDelete;
        ItemClickListener itemClickListener;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_nameEmployee = (TextView) itemView.findViewById(R.id.tv_nameEmployee);
            tv_type = (TextView) itemView.findViewById(R.id.tv_type);
            tv_from = (TextView) itemView.findViewById(R.id.tv_from);
            tv_to = (TextView) itemView.findViewById(R.id.tv_to);
            imvEdit = itemView.findViewById(R.id.imvItemListAbsenceHr_Edit);
            imvDelete = itemView.findViewById(R.id.imvItemListAbsenceHr_Delete);
            edt_Resaon = itemView.findViewById(R.id.edtItemListAbsenceHr_Reason);
            tv_TimeAbsence = itemView.findViewById(R.id.tvItemListAbsenceHr_TimeAbsence);
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
