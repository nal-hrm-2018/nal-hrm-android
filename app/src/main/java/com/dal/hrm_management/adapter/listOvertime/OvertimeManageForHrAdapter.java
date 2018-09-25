package com.dal.hrm_management.adapter.listOvertime;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dal.hrm_management.R;
import com.dal.hrm_management.models.manageOvertime.hr.viewList.Overtime;
import com.dal.hrm_management.utils.ViewDataUtils;

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
        final Overtime overtime = listOvertime.get(position);
        holder.tvNameEmp.setText(overtime.getNameEmployee());
        ViewDataUtils.setDataToView(holder.tvNameProject,overtime.getNameProject());
        ViewDataUtils.setDataTimeWithHH_MM(holder.tvFromTime,overtime.getStartTime());
        ViewDataUtils.setDataTimeWithHH_MM(holder.tvToTime,overtime.getEndTime());
        ViewDataUtils.setDataDateToView(holder.tvDate,overtime.getDate());
        ViewDataUtils.setDataToView(holder.tvReason,overtime.getReason());
        if (overtime.getOvertimeStatuses() != null) {
            String status = overtime.getOvertimeStatuses().getName();
            if(status.toLowerCase().equals("rejected")){
                ViewDataUtils.setDataToView(holder.tvReasonReject,overtime.getReasonReject());
            } else {
                holder.ll_reasonReject.setVisibility(View.GONE);
            }
            holder.tvStatus.setText(status);
        } else {
            holder.tvStatus.setText(R.string.infor_null);
        }
        if(overtime.getDayTypes()!=null){
            ViewDataUtils.setDataToView(holder.tvTypeDay,overtime.getDayTypes().getNameDayType());
        }
        ViewDataUtils.setDataToView(holder.tvAcceptedTime,overtime.getCorrectTotalTime());
        ViewDataUtils.setDataToView(holder.tvTotalTime,overtime.getTotalTime());
    }

    @Override
    public int getItemCount() {
        return listOvertime == null ? 0 : listOvertime.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        private TextView tvNameProject, tvDate, tvFromTime, tvToTime, tvTotalTime, tvAcceptedTime, tvReason, tvStatus, tvTypeDay,tvNameEmp, tvReasonReject;
        private LinearLayout ll_reasonReject;
        public Holder(View itemView) {
            super(itemView);
            tvNameEmp = itemView.findViewById(R.id.tvItemOvertimeListHR_NameEmp);
            tvNameProject = itemView.findViewById(R.id.tvItemOvertimeListHR_NameProject);
            tvDate = itemView.findViewById(R.id.tvItemOvertimeListHR_Date);
            tvFromTime = itemView.findViewById(R.id.tvItemOvertimeListHR_FromTime);
            tvToTime = itemView.findViewById(R.id.tvItemOvertimeListHR_ToTime);
            tvTotalTime = itemView.findViewById(R.id.tvHr_NumberTime);
            tvReason = itemView.findViewById(R.id.tvItemOvertimeListHR_Reason);
            tvStatus = itemView.findViewById(R.id.tvItemOvertimeListHR_Status);
            tvTypeDay = itemView.findViewById(R.id.tvItemOvertimeListHR_Type);
            tvAcceptedTime = itemView.findViewById(R.id.tvHr_AcceptTime);
            ll_reasonReject = itemView.findViewById(R.id.ll_reasonReject);
            tvReasonReject = itemView.findViewById(R.id.tvHr_ReasonReject);
        }
    }
}
