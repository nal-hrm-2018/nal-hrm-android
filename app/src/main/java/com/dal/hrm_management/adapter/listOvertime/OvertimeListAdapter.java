package com.dal.hrm_management.adapter.listOvertime;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dal.hrm_management.R;
import com.dal.hrm_management.models.overtimePersonal.Overtime;
import com.dal.hrm_management.utils.StringUtils;
import com.dal.hrm_management.views.overtime.FormOvertime;

import java.util.List;

import static com.dal.hrm_management.utils.VariableUltils.KEY_PUT_EXTRA_EDIT_OVERTIME_PERSONAL;
import static com.dal.hrm_management.utils.VariableUltils.REQUEST_CODE_EDIT_OVERTIME_PERSONAL;

public class OvertimeListAdapter extends RecyclerView.Adapter<OvertimeListAdapter.Holder> {
    private final String TAG = OvertimeListAdapter.class.getSimpleName();
    private Context context;
    private List<Overtime> listOvertime;


    public OvertimeListAdapter(Context context, List<Overtime> list) {
        this.context = context;
        this.listOvertime = list;

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_overtime_list, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        if (listOvertime.get(position).getNameProject() != null) {
            holder.tvNameProject.setText(listOvertime.get(position).getNameProject());
        } else {
            holder.tvNameProject.setText(R.string.infor_null);
        }
        if (listOvertime.get(position).getStartTime() != null) {
            holder.tvFromTime.setText(listOvertime.get(position).getStartTime());
        } else {
            holder.tvFromTime.setText(R.string.infor_null);
        }
        if (listOvertime.get(position).getEndTime() != null) {
            holder.tvToTime.setText(listOvertime.get(position).getEndTime());
        } else {
            holder.tvToTime.setText(R.string.infor_null);
        }
        if (listOvertime.get(position).getEndTime() != null) {
            holder.tvDate.setText(listOvertime.get(position).getDate());
        } else {
            holder.tvDate.setText(R.string.infor_null);
        }
        if (listOvertime.get(position).getReason() != null)

        {
            holder.tvReason.setText(listOvertime.get(position).getReason());
        } else

        {
            holder.tvReason.setText(R.string.infor_null);
        }
        if (listOvertime.get(position).getDayTypes() != null)
        {
            holder.tvTypeDay.setText(StringUtils.toUpperCaseFirstChar(listOvertime.get(position).getDayTypes().getNameDayType()));
        } else

        {
            holder.tvTypeDay.setText(R.string.infor_null);
        }
        if(listOvertime.get(position).getTotalTime()!=null) {
            holder.tvTotalTime.setText(listOvertime.get(position).

                    getTotalTime() + "");
        } else {
            holder.tvTotalTime.setText(R.string.infor_null);
        }
        //get Status of overtime
        String status = listOvertime.get(position).getOvertimeStatuses().getName();
        if(status!=null){
            if (status.equalsIgnoreCase("Not yet"))
            {
                //Unvisible accept time
                holder.rl_acceptTime.setVisibility(View.GONE);
                Log.d(TAG, "Status : Not yet");
                //Display button edit and del
                holder.imvEdit.setVisibility(View.VISIBLE);
                holder.imvDelete.setVisibility(View.VISIBLE);
                //allow edit and delete
                holder.imvDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d(TAG, "click delete");
                        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Warning");
                        builder.setMessage("Delete overtime in " + listOvertime.get(position).getNameProject() + "at " + listOvertime.get(position).getDate());
                        builder.setCancelable(false);
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(context, "Click Yes", Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(context, "Click no", Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.show();
                    }
                });
                holder.imvEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d(TAG, "click edit");
                        Intent intent = new Intent(context, FormOvertime.class);
                        intent.putExtra(KEY_PUT_EXTRA_EDIT_OVERTIME_PERSONAL, listOvertime.get(position));
                        ((Activity) context).startActivityForResult(intent, REQUEST_CODE_EDIT_OVERTIME_PERSONAL);
                    }
                });
                holder.ll_reasonReject.setVisibility(View.GONE);
            } else {
                Log.d(TAG, "Status : Other");
                //unvisible edit and del when status non equal not yet
                holder.imvEdit.setVisibility(View.GONE);
                holder.imvDelete.setVisibility(View.GONE);
                //don't allow edit and delete because it's pass review
                holder.imvEdit.setOnClickListener(null);
                holder.imvDelete.setOnClickListener(null);
                //Show accepted time
                holder.rl_acceptTime.setVisibility(View.VISIBLE);
                if(status.equalsIgnoreCase("Reviewing")){
                    holder.rl_acceptTime.setVisibility(View.GONE);
                }
                if(status.equalsIgnoreCase("Rejected")){
                    holder.ll_reasonReject.setVisibility(View.VISIBLE);
                    if(listOvertime.get(position).getReasonReject()!=null) {
                        holder.tv_reasonReject.setText(listOvertime.get(position).getReasonReject());
                    } else {
                        holder.tv_reasonReject.setText(R.string.infor_null);
                    }
                } else {
                    holder.ll_reasonReject.setVisibility(View.GONE);
                }
            }
            holder.tvStatus.setText(status);
            if(listOvertime.get(position).getCorrectTotalTime()!=null){
                holder.tvAcceptTime.setText(listOvertime.get(position).getCorrectTotalTime()+"");
            } else {
                holder.tvAcceptTime.setText(R.string.infor_null);
            }
        } else {
            holder.tvStatus.setText(R.string.infor_null);
        }
    }


    @Override
    public int getItemCount() {
        return listOvertime == null ? 0 : listOvertime.size();
    }


    public static class Holder extends RecyclerView.ViewHolder {
        private TextView tvNameProject, tvDate, tvFromTime, tvToTime, tvTotalTime, tvAcceptTime, tvReason, tv_reasonReject, tvStatus, tvTypeDay;
        private ImageView imvEdit, imvDelete;
        private LinearLayout ll_reasonReject;
        private RelativeLayout rl_acceptTime;

        public Holder(View itemView) {
            super(itemView);
            tvNameProject = itemView.findViewById(R.id.tvItemOvertimeList_NameProject);
            tvDate = itemView.findViewById(R.id.tvItemOvertimeList_Date);
            tvFromTime = itemView.findViewById(R.id.tvItemOvertimeList_FromTime);
            tvToTime = itemView.findViewById(R.id.tvItemOvertimeList_ToTime);
            tvTotalTime = itemView.findViewById(R.id.tvPersonal_NumberTime);
            tvAcceptTime = itemView.findViewById(R.id.tvPersonal_AcceptTime);
            tvReason = itemView.findViewById(R.id.tvItemOvertimeList_Reason);
            tvStatus = itemView.findViewById(R.id.tvItemOvertimeList_Status);
            tvTypeDay = itemView.findViewById(R.id.tv_typeDay);
            imvEdit = itemView.findViewById(R.id.imgItemOvertimeList_edit);
            imvDelete = itemView.findViewById(R.id.imgItemOvertimeList_delete);
            rl_acceptTime = itemView.findViewById(R.id.rl_acceptTime);
            ll_reasonReject = itemView.findViewById(R.id.ll_reasonReject);
            tv_reasonReject = itemView.findViewById(R.id.tvPersonal_ReasonReject);
        }

    }
}
