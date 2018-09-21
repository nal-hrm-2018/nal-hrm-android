package com.dal.hrm_management.adapter.listOvertime;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dal.hrm_management.R;
import com.dal.hrm_management.models.manageOvertime.hr.viewList.Overtime;

import java.util.List;

public class OvertimeManageForHrAdapter extends RecyclerView.Adapter<OvertimeManageForHrAdapter.Holder> {
    private Context context;
    private List<Overtime> listOvertime;


    public OvertimeManageForHrAdapter(Context context, List<Overtime> list) {
        this.context = context;
        this.listOvertime = list;

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_overtime_list_hr, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.tvNameEmp.setText(listOvertime.get(position).getNameEmployee());
        if (listOvertime.get(position).getNameProject() != null) {
            holder.tvNameProject.setText(listOvertime.get(position).getNameProject());
        } else {
            holder.tvNameProject.setText(R.string.infor_null);
        }
        holder.tvFromTime.setText(listOvertime.get(position).getStartTime());
        holder.tvToTime.setText(listOvertime.get(position).getEndTime());
        holder.tvDate.setText(listOvertime.get(position).getDate());
        holder.tvReason.setText(listOvertime.get(position).getReason());
        if (listOvertime.get(position).getOvertimeStatuses() != null) {
            holder.tvStatus.setText(listOvertime.get(position).getOvertimeStatuses().getName());
        } else {
            holder.tvStatus.setText(R.string.infor_null);
        }
        holder.tvTypeDay.setText(listOvertime.get(position).getDayTypes().getNameDayType());

        holder.tvTotalTime.setText(listOvertime.get(position).getTotalTime() + "");
    }


    @Override
    public int getItemCount() {
        return listOvertime == null ? 0 : listOvertime.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        private TextView tvNameProject, tvDate, tvFromTime, tvToTime, tvTotalTime, tvReason, tvStatus, tvTypeDay,tvNameEmp;
        private ImageView imvEdit, imvDelete;
        private LinearLayout llButton;
        public Holder(View itemView) {
            super(itemView);
            tvNameEmp = itemView.findViewById(R.id.tvItemOvertimeListHR_NameEmp);
            tvNameProject = itemView.findViewById(R.id.tvItemOvertimeListHR_NameProject);
            tvDate = itemView.findViewById(R.id.tvItemOvertimeListHR_Date);
            tvFromTime = itemView.findViewById(R.id.tvItemOvertimeListHR_FromTime);
            tvToTime = itemView.findViewById(R.id.tvItemOvertimeListHR_ToTime);
            tvTotalTime = itemView.findViewById(R.id.tvItemOvertimeListHR_TotalTime);
            tvReason = itemView.findViewById(R.id.tvItemOvertimeListHR_Reason);
            tvStatus = itemView.findViewById(R.id.tvItemOvertimeListHR_Status);
            tvTypeDay = itemView.findViewById(R.id.tvItemOvertimeListHR_Type);
            imvEdit = itemView.findViewById(R.id.imgItemOvertimeListHR_edit);
            imvDelete = itemView.findViewById(R.id.imgItemOvertimeListHR_delete);
            llButton = itemView.findViewById(R.id.llButton);
            llButton.setVisibility(View.GONE);
        }
    }
}
