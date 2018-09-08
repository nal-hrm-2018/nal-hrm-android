package com.dal.hrm_management.adapter.listAbsence;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dal.hrm_management.R;
import com.dal.hrm_management.adapter.ItemClickListener;
import com.dal.hrm_management.models.manageAbsence.hr.absenceEmployee.Absence;
import com.dal.hrm_management.utils.StringUtils;

import java.util.List;

public class AbsenceDetailEmployeeAdapter extends RecyclerView.Adapter<AbsenceDetailEmployeeAdapter.DataViewHolder> {
    private List<Absence> absences;
    private Context context;

    public AbsenceDetailEmployeeAdapter(List<Absence> arr, Context context) {
        this.absences = arr;
        this.context = context;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_absence, parent, false);
        return new DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AbsenceDetailEmployeeAdapter.DataViewHolder holder, int position) {
        holder.tv_type.setText(StringUtils.toUpperCaseFirstChar(absences.get(position).getAbsenceType().getDescription()));
        holder.tv_startAt.setText(StringUtils.yyyy_mm_ddTodd_mm_yyyy(absences.get(position).getFromDate()));
        holder.tv_endAt.setText(StringUtils.yyyy_mm_ddTodd_mm_yyyy(absences.get(position).getToDate()));
        holder.item_absence_tv_reason.setText(absences.get(position).getReason());
        /**
         *  Delete "absence" pre-fix and upper case the first leter result
         *  example: absence morning --> Morning
         */
        holder.tv_thoigianNghi.setText(StringUtils.toUpperCaseFirstChar(absences.get(position).getAbsenceTime().getDescription().substring(7)));
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
            }
        });

    }


    @Override
    public int getItemCount() {
        return absences == null ? 0 : absences.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    /**
     * Profile ViewHolder class.
     */
    public static class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_type;
        private TextView tv_startAt;
        private TextView tv_endAt;
        private TextView tv_thoigianNghi;
        private TextView item_absence_tv_reason;
        ItemClickListener itemClickListener;

        public DataViewHolder(View itemView) {
            super(itemView);
            tv_type = itemView.findViewById(R.id.tv_type);
            tv_startAt = itemView.findViewById(R.id.tv_startAt);
            tv_endAt = itemView.findViewById(R.id.tv_endAt);
            tv_thoigianNghi = itemView.findViewById(R.id.tv_thoigianNghi);
            item_absence_tv_reason = itemView.findViewById(R.id.item_absence_tv_reason);
            itemView.setOnClickListener(this);

        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;

        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(), false);
        }
    }
}