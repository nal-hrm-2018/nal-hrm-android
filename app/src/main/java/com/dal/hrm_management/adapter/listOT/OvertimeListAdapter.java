package com.dal.hrm_management.adapter.listOT;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dal.hrm_management.R;

import java.util.List;

public class OvertimeListAdapter extends RecyclerView.Adapter<OvertimeListAdapter.Holder> {
    private Context context;
    private List<FakeDataForListOvertime> list;

    public OvertimeListAdapter(Context context, List<FakeDataForListOvertime> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_overtime_list,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.tvNameProject.setText(list.get(position).getNameProject());
    }


    @Override
    public int getItemCount() {
        return list == null ? 0:list.size();
    }

    public static class Holder extends RecyclerView.ViewHolder{
        private TextView tvNameProject,tvDate,tvFromTime,tvToTime,tvTotalTime,tvReason,tvStatus;
        private ImageView imvEdit,imvDelete;
        public Holder(View itemView) {
            super(itemView);
            tvNameProject = itemView.findViewById(R.id.tvItemOvertimeList_NameProject);
            tvDate = itemView.findViewById(R.id.tvItemOvertimeList_Date);
            tvFromTime = itemView.findViewById(R.id.tvItemOvertimeList_FromTime);
            tvToTime = itemView.findViewById(R.id.tvItemOvertimeList_ToTime);
            tvTotalTime = itemView.findViewById(R.id.tvItemOvertimeList_TotalTime);
            tvReason = itemView.findViewById(R.id.tvItemOvertimeList_Reason);
            tvStatus = itemView.findViewById(R.id.tvItemOvertimeList_Status);
            imvEdit = itemView.findViewById(R.id.imv_edit);
            imvDelete = itemView.findViewById(R.id.imv_delete);
        }
    }
}
