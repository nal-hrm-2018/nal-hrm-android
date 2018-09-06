package com.dal.hrm_management.adapter.listOT;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dal.hrm_management.R;
import com.dal.hrm_management.adapter.ItemClickListener;
import com.dal.hrm_management.models.fakeData.OverTime;

import java.util.ArrayList;
import java.util.List;


public class OTManagerForPoAdapter extends RecyclerView.Adapter<OTManagerForPoAdapter.MyViewHolder> implements Filterable {
    private List<OverTime> overtimeList;
    private Context context;
    private List<OverTime> overtimeListFiltered;

    public OTManagerForPoAdapter(List<OverTime> absenceList, Context context) {
        this.overtimeList = absenceList;
        this.context = context;
        this.overtimeListFiltered = absenceList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_ot_manager_of_po, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final OverTime overTime = overtimeListFiltered.get(position);
        holder.tv_nameEmployee.setText(overTime.getNameEmployee());
        holder.tv_nameProject.setText(overTime.getNameProject());
        holder.tv_reason.setText(overTime.getReason());
        holder.tv_from.setText(overTime.getFrom());
        holder.tv_to.setText(overTime.getTo());
        String typeDate = overTime.getTypeDate();
        if (typeDate.toLowerCase().equals("holiday")) {
            holder.tv_typeDate.setBackgroundColor(context.getResources().getColor(R.color.color_violet_2));
        } else if (typeDate.toLowerCase().equals("day off")) {
            holder.tv_typeDate.setBackgroundColor(context.getResources().getColor(R.color.color_violet_1));
        }
        holder.tv_typeDate.setText(typeDate);
        if (overTime.getStatus().equals(context.getString(R.string.overtime_status_accepted))) {
            holder.tv_status.setText(context.getString(R.string.overtime_status_accepted));
            holder.tv_status.setTextColor(context.getResources().getColor(R.color.color_green));
            holder.tv_status.setVisibility(View.VISIBLE);
            holder.ll_button.setVisibility(View.GONE);
        }
        if (overTime.getStatus().equals(context.getString(R.string.overtime_status_rejected))) {
            holder.tv_status.setText(context.getString(R.string.overtime_status_rejected));
            holder.tv_status.setTextColor(context.getResources().getColor(R.color.color_red));
            holder.tv_status.setVisibility(View.VISIBLE);
            holder.ll_button.setVisibility(View.GONE);
        }
        holder.setIsRecyclable(false);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (view.getId() == R.id.imgBtn_accept) {
                    holder.tv_status.setText(context.getString(R.string.overtime_status_accepted));
                    holder.tv_status.setTextColor(context.getResources().getColor(R.color.color_green));
                    holder.tv_status.setVisibility(View.VISIBLE);
                    holder.ll_button.setVisibility(View.GONE);
                    overTime.setStatus(context.getString(R.string.overtime_status_accepted));
                } else {
                    showDialogReason();
                }
            }

            private void showDialogReason() {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view = LayoutInflater.from(context).inflate(R.layout.dialog_input_accept_time, null, false);
                final EditText edt_acceptTime = (EditText) view.findViewById(R.id.edt_acceptTime);
                builder.setView(view);
                builder.setTitle("Do you want to reject this form?");
                builder.setCancelable(false);
                builder.setPositiveButton("Reject request", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        holder.tv_status.setText(context.getString(R.string.overtime_status_rejected));
                        holder.tv_status.setTextColor(context.getResources().getColor(R.color.color_red));
                        holder.tv_status.setVisibility(View.VISIBLE);
                        holder.ll_button.setVisibility(View.GONE);
                        overTime.setStatus(context.getString(R.string.overtime_status_rejected));
                        if (edt_acceptTime.getText() == null) {
                            holder.tv_acceptTime.setText("-");
                        } else {
                            holder.tv_acceptTime.setText(edt_acceptTime.getText());
                            overTime.setAcceptTime(edt_acceptTime.getText()+"");
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return overtimeListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    overtimeListFiltered = overtimeList;
                } else {
                    List<OverTime> filteredList = new ArrayList<>();
                    for (OverTime row : overtimeList) {
                        if (row.getNameEmployee().toLowerCase().contains(charString.toLowerCase()) || row.getNameProject().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    overtimeListFiltered = filteredList;
                }
                ;
                FilterResults filterResults = new FilterResults();
                filterResults.values = overtimeListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                overtimeListFiltered = (ArrayList<OverTime>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout absences_layout;
        TextView tv_nameEmployee;
        TextView tv_nameProject;
        TextView tv_reason;
        TextView tv_from;
        TextView tv_to;
        TextView tv_status;
        TextView tv_numberTime;
        TextView tv_acceptTime;
        TextView tv_typeDate;
        LinearLayout ll_button;
        ImageButton imgBtn_reject;
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
            tv_numberTime = (TextView) itemView.findViewById(R.id.tv_numberTime);
            tv_acceptTime = (TextView) itemView.findViewById(R.id.tv_acceptTime);
            tv_typeDate = (TextView) itemView.findViewById(R.id.tv_typeDay);
            tv_status = (TextView) itemView.findViewById(R.id.tv_status);
            ll_button = (LinearLayout) itemView.findViewById(R.id.ll_button);
            imgBtn_accept = (ImageButton) itemView.findViewById(R.id.imgBtn_accept);
            imgBtn_reject = (ImageButton) itemView.findViewById(R.id.imgBtn_cancel);
            imgBtn_accept.setOnClickListener(this);
            imgBtn_reject.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);
        }
    }
}
