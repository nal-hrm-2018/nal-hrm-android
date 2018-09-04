package com.dal.hrm_management.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
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
import com.dal.hrm_management.presenters.manageAbsence.Hr.ManageAbsenceHrPresenter;
import com.dal.hrm_management.utils.VariableUltils;
import com.dal.hrm_management.views.absence.FormAbsenceActivity;
import com.dal.hrm_management.views.absenceEmployee.DetailAbsenceEmployeeActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Luu Ngoc Lan on 06-Aug-18.
 */

public class AbsenceListForHrAdapter extends RecyclerView.Adapter<AbsenceListForHrAdapter.MyViewHolder> implements Filterable{
    private List<ListAbsenceForHr> absenceList;
    private List<ListAbsenceForHr> absenceListFiltered;
    private int rowLayout;
    private Context context;
    private int id_employee;
    private ManageAbsenceHrPresenter manageAbsenceHrPresenter;

    public AbsenceListForHrAdapter
            (List<ListAbsenceForHr> absenceList, int rowLayout, Context context,
             ManageAbsenceHrPresenter manageAbsenceHrPresenter) {
        this.absenceList = absenceList;
        this.absenceListFiltered = absenceList;
        this.rowLayout = rowLayout;
        this.context = context;
        this.manageAbsenceHrPresenter = manageAbsenceHrPresenter;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new AbsenceListForHrAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final ListAbsenceForHr absence = absenceListFiltered.get(position);
        holder.tv_nameEmployee.setText(absence.getNameEmployee());
        holder.setIsRecyclable(true);
        holder.tv_type.setText(absence.getAbsenceType().getNameAbsenceType().replace("_"," "));
        holder.tv_from.setText(absence.getFromDate());
        if(absence.getReason()!= null){
            holder.edt_Resaon.setText(absence.getReason());
        } else {
            holder.edt_Resaon.setText(R.string.infor_null);
        }
        holder.tv_TimeAbsence.setText(absence.getAbsenceTime().getDescription());
        holder.tv_to.setText(absence.getToDate());
        holder.imvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("position", String.valueOf(position));
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
                builder.setMessage("Delete absence of " + absence.getNameEmployee()+" ?");
                builder.setCancelable(false);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Call api delete
                        manageAbsenceHrPresenter.deleteAbsence(absence.getIdAbsences());

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
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
                id_employee = absence.getIdEmployee();
                Intent intent = new Intent(context.getApplicationContext(), DetailAbsenceEmployeeActivity.class);
                intent.putExtra("id_employee",id_employee);
                intent.putExtra("name_employee",absence.getNameEmployee());
                context.startActivity(intent);
            }
        });
        //Xét điều kiện nếu như form đó đã quá 1 tháng - chưa check đúng đâu
        try {
            Calendar c = Calendar.getInstance();
            Date dTuNgay = new SimpleDateFormat("yyyy-MM-dd").parse(absenceList.get(position).getFromDate());
            c.setTime(dTuNgay);
            c.add(Calendar.DATE,30);
            dTuNgay = c.getTime();
            Date dHienTai = new Date();
            if (dTuNgay.compareTo(dHienTai) < 0){
                //Tu ngay + 30 > dHienTai
                holder.imvEdit.setVisibility(View.GONE);
                holder.imvDelete.setVisibility(View.GONE);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return absenceListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    absenceListFiltered = absenceList;
                } else {
                    List<ListAbsenceForHr> filteredList = new ArrayList<>();
                    for (ListAbsenceForHr row : absenceList) {
                        if (row.getNameEmployee().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    absenceListFiltered = filteredList;
                }
                ;
                FilterResults filterResults = new FilterResults();
                filterResults.values = absenceListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                absenceListFiltered = (ArrayList<ListAbsenceForHr>) results.values;
                notifyDataSetChanged();
            }
        };

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tv_nameEmployee;
        TextView tv_type;
        TextView tv_from;
        TextView tv_to;
        TextView edt_Resaon;
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
}
