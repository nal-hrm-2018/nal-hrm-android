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
import android.widget.TextView;
import android.widget.Toast;

import com.dal.hrm_management.R;
import com.dal.hrm_management.models.overtimePersonal.Overtime;
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
        if (listOvertime.get(position).getProject() != null) {
            holder.tvNameProject.setText(listOvertime.get(position).getProject().getNameProject());
        }
        holder.tvFromTime.setText(listOvertime.get(position).getStartTime());
        holder.tvToTime.setText(listOvertime.get(position).getEndTime());
        holder.tvDate.setText(listOvertime.get(position).getDate());
        holder.tvReason.setText(listOvertime.get(position).getReason());



        holder.tvTypeDay.setText(listOvertime.get(position).getOvertimeTypes().getName());
        holder.tvTotalTime.setText(listOvertime.get(position).getTotalTime() + "");
        //get Status
        String status = listOvertime.get(position).getOvertimeStatuses().getName();
        if (status.equalsIgnoreCase("Not yet")){
            Log.d(TAG,"Status : Not yet" );
            //Display
            holder.imvEdit.setVisibility(View.VISIBLE);
            holder.imvDelete.setVisibility(View.VISIBLE);
            holder.tvAccept.setVisibility(View.GONE);
            //allow edit and delete
            holder.imvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG,"click delete");
                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Warning");
                    builder.setMessage("Delete overtime in " + listOvertime.get(position).getProject().getNameProject() + "at " + listOvertime.get(position).getDate());
                    builder.setCancelable(false);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(context,"Click Yes",Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(context,"Click no",Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }
            });
            holder.imvEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG,"click edit");
                    Intent intent = new Intent(context, FormOvertime.class);
                    intent.putExtra(KEY_PUT_EXTRA_EDIT_OVERTIME_PERSONAL,listOvertime.get(position));
                    ((Activity) context).startActivityForResult(intent,REQUEST_CODE_EDIT_OVERTIME_PERSONAL);
                }
            });
        }else{
            Log.d(TAG,"Status : Other" );
            //display
            holder.imvEdit.setVisibility(View.GONE);
            holder.imvDelete.setVisibility(View.GONE);
            holder.tvAccept.setVisibility(View.VISIBLE);
            //don't allow eidt and delete because it's pass review
            holder.imvEdit.setOnClickListener(null);
            holder.imvDelete.setOnClickListener(null);
//            holder.tvAccept.setText(listOvertime.get(position));

        }
        holder.tvStatus.setText(listOvertime.get(position).getOvertimeStatuses().getName());


    }



    @Override
    public int getItemCount() {
        return listOvertime == null ? 0 : listOvertime.size();
    }



    public static class Holder extends RecyclerView.ViewHolder {
        private TextView tvNameProject, tvDate, tvFromTime, tvToTime, tvTotalTime, tvReason, tvStatus, tvTypeDay,tvAccept;
        private ImageView imvEdit, imvDelete;

        public Holder(View itemView) {
            super(itemView);
            tvNameProject = itemView.findViewById(R.id.tvItemOvertimeList_NameProject);
            tvDate = itemView.findViewById(R.id.tvItemOvertimeList_Date);
            tvFromTime = itemView.findViewById(R.id.tvItemOvertimeList_FromTime);
            tvToTime = itemView.findViewById(R.id.tvItemOvertimeList_ToTime);
            tvTotalTime = itemView.findViewById(R.id.tvItemOvertimeList_TotalTime);
            tvReason = itemView.findViewById(R.id.tvItemOvertimeList_Reason);
            tvStatus = itemView.findViewById(R.id.tvItemOvertimeList_Status);
            tvTypeDay = itemView.findViewById(R.id.tv_typeDay);
            imvEdit = itemView.findViewById(R.id.imgItemOvertimeList_edit);
            imvDelete = itemView.findViewById(R.id.imgItemOvertimeList_delete);

            tvAccept = itemView.findViewById(R.id.tvItemOvertimeList_AcceptTime);
        }

    }
}
