package com.dal.hrm_management.adapter.listAbsence;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dal.hrm_management.R;
import com.dal.hrm_management.models.absence.Absence;
import com.dal.hrm_management.utils.StringUtils;

import java.util.List;

public class AbsenceAdapter extends RecyclerView.Adapter<AbsenceAdapter.DataViewHolder> {
    private List<Absence> absences;
    private Context context;

    public AbsenceAdapter(List<Absence> arr, Context context) {
        this.absences = arr;
        this.context = context;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_absence, parent, false);
        return new DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AbsenceAdapter.DataViewHolder holder, int position) {
        holder.tv_type.setText(StringUtils.toUpperCaseFirstChar(absences.get(position).getAbsenceType().getDescription()));
        holder.tv_startAt.setText(StringUtils.yyyy_mm_ddTodd_mm_yyyy(absences.get(position).getFromDate()));
        holder.tv_endAt.setText(StringUtils.yyyy_mm_ddTodd_mm_yyyy(absences.get(position).getToDate()));
        holder.item_absence_tv_reason.setText(absences.get(position).getReason());
        holder.tv_numberDay.setText(absences.get(position).getNumberDayAbsence()+"");
        /**
         *  Delete "absence" pre-fix and upper case the first leter result
         *  example: absence morning --> Morning
         */
        String timeAbsence = StringUtils.toUpperCaseFirstChar(absences.get(position).getAbsenceTime().getDescription().substring(7));
        if (timeAbsence.equals("All day")) {
            holder.tv_thoigianNghi.setBackgroundColor(context.getResources().getColor(R.color.color_violet_2));
        } else if (timeAbsence.equals("Morning")) {
            holder.tv_thoigianNghi.setBackgroundColor(context.getResources().getColor(R.color.color_violet));
        } else {
            holder.tv_thoigianNghi.setBackgroundColor(context.getResources().getColor(R.color.color_violet_1));
        }
        holder.tv_thoigianNghi.setText(timeAbsence);
        if (absences.get(position).getDescription() == null || absences.get(position).getDescription().trim().length()==0){
            holder.item_absence_tv_note.setText(R.string.infor_null);
        }else{
            holder.item_absence_tv_note.setText(absences.get(position).getDescription());
        }
    }


    @Override
    public int getItemCount() {
        return absences == null ? 0 : absences.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public static class DataViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_type;
        private TextView tv_startAt;
        private TextView tv_endAt;
        private TextView tv_thoigianNghi;
        private TextView item_absence_tv_reason;
        private TextView item_absence_tv_note;
        private TextView tv_numberDay;

        public DataViewHolder(View itemView) {
            super(itemView);
            tv_type = itemView.findViewById(R.id.tv_type);
            tv_startAt = itemView.findViewById(R.id.tv_startAt);
            tv_endAt = itemView.findViewById(R.id.tv_endAt);
            tv_thoigianNghi = itemView.findViewById(R.id.tv_thoigianNghi);
            item_absence_tv_reason = itemView.findViewById(R.id.item_absence_tv_reason);
            item_absence_tv_note = itemView.findViewById(R.id.item_absence_tv_note);
            tv_numberDay = itemView.findViewById(R.id.tv_numberDay);
        }
    }
}