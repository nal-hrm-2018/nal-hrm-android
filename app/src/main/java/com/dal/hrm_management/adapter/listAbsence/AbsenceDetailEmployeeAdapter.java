package com.dal.hrm_management.adapter.listAbsence;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.dal.hrm_management.R;
import com.dal.hrm_management.adapter.ItemClickListener;
import com.dal.hrm_management.models.manageAbsence.hr.absenceEmployee.Absence;
import com.dal.hrm_management.presenters.absenceEmployee.DetailAbsenceEmployeePresenter;
import com.dal.hrm_management.utils.PermissionManager;
import com.dal.hrm_management.utils.StringUtils;
import com.dal.hrm_management.utils.VariableUltils;
import com.dal.hrm_management.views.absence.FormAbsenceActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AbsenceDetailEmployeeAdapter extends RecyclerView.Adapter<AbsenceDetailEmployeeAdapter.MyViewHolder> implements Filterable {
    private List<Absence> absenceList;
    private List<Absence> absenceListFiltered;
    private Context context;
    private DetailAbsenceEmployeePresenter detailAbsenceEmployeePresenter;

    public AbsenceDetailEmployeeAdapter(List<Absence> absenceList, Context context, DetailAbsenceEmployeePresenter detailAbsenceEmployeePresenter) {
        this.absenceList = absenceList;
        this.absenceListFiltered = absenceList;
        this.context = context;
        this.detailAbsenceEmployeePresenter = detailAbsenceEmployeePresenter;
    }

    @Override
    public AbsenceDetailEmployeeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_absence_detail_employee, parent, false);
        return new AbsenceDetailEmployeeAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Absence absence = absenceListFiltered.get(position);
        holder.setIsRecyclable(false);
        holder.tv_type.setText(StringUtils.toUpperCaseFirstChar(absence.getAbsenceType().getNameAbsenceType().replace("_", " ")));
        holder.tv_from.setText(StringUtils.yyyy_mm_ddTodd_mm_yyyy(absence.getFromDate()));
        if (absence.getReason() != null) {
            holder.tv_reason.setText(absence.getReason());
        } else {
            holder.tv_reason.setText(context.getResources().getString(R.string.infor_null));
        }
        if (absence.getDescription() == null || absence.getDescription().trim().length()==0){
            holder.tv_note.setText(R.string.infor_null);
        }else{
            holder.tv_note.setText(absence.getDescription());
        }
        /**
         *  Delete "absence" pre-fix and upper case the first leter result
         *  example: absence morning --> Morning
         */
        String timeAbsence = StringUtils.toUpperCaseFirstChar(absence.getAbsenceTime().getDescription().substring(7));
        if (timeAbsence.equals("All day")) {
            holder.tv_TimeAbsence.setBackgroundColor(context.getResources().getColor(R.color.color_violet_2));
        } else if (timeAbsence.equals("Morning")) {
            holder.tv_TimeAbsence.setBackgroundColor(context.getResources().getColor(R.color.color_violet));
        } else {
            holder.tv_TimeAbsence.setBackgroundColor(context.getResources().getColor(R.color.color_violet_1));
        }
        holder.tv_TimeAbsence.setText(timeAbsence);
        holder.tv_to.setText(StringUtils.yyyy_mm_ddTodd_mm_yyyy(absence.getToDate()));

//        //TAM THOI GONE 2 nut edit va del trong detail absence employee
//        holder.imvEdit.setVisibility(View.GONE);
//        holder.imvDelete.setVisibility(View.GONE);
        //Check permission edit of HR
        if (!PermissionManager.isPermited(PermissionManager.listPermissions, "edit_absence_employee")) {
            holder.imvEdit.setVisibility(View.GONE);
        } else {
            holder.imvEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, FormAbsenceActivity.class);
                    intent.putExtra(VariableUltils.KEY_PUT_EXTRA_EDIT_ABSENCE_IN_DETAIL, absence);
                    ((Activity) context).startActivityForResult(intent, VariableUltils.REQUEST_CODE);
                }
            });
        }

        //Check permission del of HR
        if (!PermissionManager.isPermited(PermissionManager.listPermissions, "cancel_emplyee_absence_history")) {
            holder.imvDelete.setVisibility(View.GONE);
        } else {
            holder.imvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Warning");
                    builder.setMessage("Are you sure to delete this absence?");
                    builder.setCancelable(false);

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //Call api delete
                            detailAbsenceEmployeePresenter.deleteAbsence(absence.getIdAbsences());

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
        }

        //Xét điều kiện nếu như form đó đã quá 1 tháng - chưa check đúng đâu
        try {
            Calendar c = Calendar.getInstance();
            String[] split = absenceList.get(position).getFromDate().split("-");
            int from_day = Integer.parseInt(split[2]);
            int from_month = Integer.parseInt(split[1]);
            int from_year = Integer.parseInt(split[0]);
            Date dTuNgay = new Date(from_day, from_month, from_year);
            c.setTime(dTuNgay);
            c.add(Calendar.DATE, 30);
            dTuNgay = c.getTime();
            Date dHienTai = new Date();
            split = absenceList.get(position).getCreatedAt().split("-");
            int to_day = Integer.parseInt(split[2]);
            int to_month = Integer.parseInt(split[1]);
            int to_year = Integer.parseInt(split[0]);
            Date dNgayTao = new Date(to_day, to_month, to_year);
            c.setTime(dNgayTao);
            c.add(Calendar.DATE, 30);
            dNgayTao = c.getTime();
            if (dTuNgay.compareTo(dHienTai) < 0 && dNgayTao.compareTo(dHienTai) < 0) {
                //Tu ngay + 30 > dHienTai
                holder.imvEdit.setVisibility(View.GONE);
                holder.imvDelete.setVisibility(View.GONE);
            }
        } catch (Exception e) {
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
                    List<Absence> filteredList = new ArrayList<>();
                    for (Absence row : absenceList) {
                        if (row.getFromDate().toLowerCase().contains(charString.toLowerCase()) || row.getToDate().toLowerCase().contains(charString.toLowerCase())) {
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
                absenceListFiltered = (ArrayList<Absence>) results.values;
                notifyDataSetChanged();
            }
        };

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_type;
        TextView tv_from;
        TextView tv_to;
        TextView tv_reason;
        TextView tv_TimeAbsence;
        TextView tv_note;
        private ImageView imvEdit;
        private ImageView imvDelete;
        ItemClickListener itemClickListener;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_type = (TextView) itemView.findViewById(R.id.tv_type);
            tv_from = (TextView) itemView.findViewById(R.id.tv_from);
            tv_to = (TextView) itemView.findViewById(R.id.tv_to);
            imvEdit = itemView.findViewById(R.id.imv_edit);
            imvDelete = itemView.findViewById(R.id.imv_delete);
            tv_reason = itemView.findViewById(R.id.tv_reason);
            tv_TimeAbsence = itemView.findViewById(R.id.tv_timeAbsence);
            tv_note = itemView.findViewById(R.id.tv_note);
        }

    }
}