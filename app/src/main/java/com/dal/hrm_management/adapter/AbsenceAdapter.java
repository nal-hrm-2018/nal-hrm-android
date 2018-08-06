package com.dal.hrm_management.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dal.hrm_management.R;
import com.dal.hrm_management.models.fakeData.AbsenceModel;

import java.util.List;

public class AbsenceAdapter  extends RecyclerView.Adapter<AbsenceAdapter.DataViewHolder> {
    private List<AbsenceModel> arr;
    private Context context;

    public AbsenceAdapter(List<AbsenceModel> arr, Context context) {
        this.arr = arr;
        this.context = context;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_absence, parent, false);
        return new DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AbsenceAdapter.DataViewHolder holder, int position) {
        holder.tv_type.setText(arr.get(position).getType());
        holder.tv_startAt.setText(arr.get(position).getDateStart());
        holder.tv_endat.setText(arr.get(position).getDateEnd());
        holder.tv_status.setText(arr.get(position).getStatus());

    }



    @Override
    public int getItemCount() {
        Log.d("size", String.valueOf(arr.size()));
        return arr == null ? 0 : arr.size();
    }

    /**
     * Data ViewHolder class.
     */
    public static class DataViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_type;
        private TextView tv_startAt;
        private TextView tv_endat;
        private TextView tv_status;

        public DataViewHolder(View itemView) {
            super(itemView);
            tv_type = itemView.findViewById(R.id.item_absence_tv_type);
            tv_startAt = itemView.findViewById(R.id.item_absence_tv_startat);
            tv_endat = itemView.findViewById(R.id.item_absence_tv_startend);
            tv_status = itemView.findViewById(R.id.item_absence_tv_status);
        }
    }
}