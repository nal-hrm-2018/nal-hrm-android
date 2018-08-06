package com.dal.hrm_management.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dal.hrm_management.R;
import com.dal.hrm_management.models.absence.Absence;

import java.util.List;

/**
 * Created by Luu Ngoc Lan on 02-Aug-18.
 */

public class AbsenceManagerForPoAdapter extends RecyclerView.Adapter<AbsenceManagerForPoAdapter.MyViewHolder>  {
    private List<Absence> absenceList;
    private int rowLayout;
    private Context context;

    public AbsenceManagerForPoAdapter(List<Absence> absenceList, int rowLayout, Context context) {
        this.absenceList = absenceList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tv_nameEmployee.setText(absenceList.get(position).getName());
        holder.tv_nameProject.setText(absenceList.get(position).getNameProject());
        holder.tv_reason.setText(absenceList.get(position).getReason());
        holder.tv_from.setText(absenceList.get(position).getFrom());
        holder.tv_to.setText(absenceList.get(position).getTo());
//        holder.tv_note.setText(absenceList.get(position).getNote());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(view.getId()==R.id.imgBtn_accept){
                    holder.tv_status.setText("Đã đông ý");
                    holder.tv_status.setVisibility(View.VISIBLE);
                    holder.ll_button.setVisibility(View.GONE);
                } else {
                    showDialogReason();
                }
            }

            private void showDialogReason() {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                final EditText input = new EditText(context);
                input.setInputType(InputType.TYPE_CLASS_TEXT );
                input.setHint("Write your reason here");
                builder.setView(input);
                builder.setTitle("Why you deny?");
                builder.setCancelable(false);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        holder.tv_status.setText("Đã từ chối");
                        holder.tv_status.setVisibility(View.VISIBLE);
                        holder.ll_button.setVisibility(View.GONE);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.create().show();
            }
        });
    }



    @Override
    public int getItemCount() {
        return absenceList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        LinearLayout absences_layout;
        TextView tv_nameEmployee;
        TextView tv_nameProject;
        TextView tv_reason;
        TextView tv_from;
        TextView tv_to;
        TextView tv_note;
        TextView tv_status;
        LinearLayout ll_button;
        ImageButton imgBtn_cancel;
        ImageButton imgBtn_accept;
        ItemClickListener itemClickListener;

        public MyViewHolder(View itemView) {
            super(itemView);
            absences_layout = (LinearLayout) itemView.findViewById(R.id.absences_layout);
            tv_nameEmployee = (TextView) itemView.findViewById(R.id.tv_nameEmployee);
            tv_nameProject = (TextView) itemView.findViewById(R.id.tv_nameProject);
            tv_reason = (TextView) itemView.findViewById(R.id.tv_reason);
            tv_from = (TextView) itemView.findViewById(R.id.tv_from);
            tv_to = (TextView) itemView.findViewById(R.id.tv_to);
            tv_status = (TextView) itemView.findViewById(R.id.tv_status);
            //tv_note = (TextView) itemView.findViewById(R.id.tv_note);
            ll_button = (LinearLayout) itemView.findViewById(R.id.ll_button);
            imgBtn_accept = (ImageButton) itemView.findViewById(R.id.imgBtn_accept);
            imgBtn_cancel = (ImageButton) itemView.findViewById(R.id.imgBtn_cancel);
            imgBtn_accept.setOnClickListener(this);
            imgBtn_cancel.setOnClickListener(this);
        }
        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),false);
        }
    }
}
