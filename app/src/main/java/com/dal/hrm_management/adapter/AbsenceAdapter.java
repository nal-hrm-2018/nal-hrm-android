package com.dal.hrm_management.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dal.hrm_management.R;
import com.dal.hrm_management.models.absence.Absence;

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
        holder.tv_type.setText(absences.get(position).getAbsenceType().getDescription());
        holder.tv_startAt.setText(absences.get(position).getFromDate());
        holder.tv_endAt.setText(absences.get(position).getToDate());
        holder.item_absence_tv_reason.setText(absences.get(position).getReason());
        holder.tv_thoigianNghi.setText(absences.get(position).getAbsenceTime().getDescription());
    }


    @Override
    public int getItemCount() {
        Log.d("size", String.valueOf(absences.size()));
        return absences == null ? 0 : absences.size();
    }

    /**
     * Profile ViewHolder class.
     */
    public static class DataViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_type;
        private TextView tv_startAt;
        private TextView tv_endAt;
        private TextView tv_thoigianNghi;
        private TextView item_absence_tv_reason;

        public DataViewHolder(View itemView) {
            super(itemView);
            tv_type = itemView.findViewById(R.id.tv_type);
            tv_startAt = itemView.findViewById(R.id.tv_startAt);
            tv_endAt = itemView.findViewById(R.id.tv_endAt);
            tv_thoigianNghi = itemView.findViewById(R.id.tv_thoigianNghi);
            item_absence_tv_reason = itemView.findViewById(R.id.item_absence_tv_reason);
        }
    }
}