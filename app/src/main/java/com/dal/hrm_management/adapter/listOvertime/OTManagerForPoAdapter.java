package com.dal.hrm_management.adapter.listOvertime;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dal.hrm_management.R;
import com.dal.hrm_management.models.manageOvertime.po.viewlist.OverTime;
import com.dal.hrm_management.utils.StringUtils;
import com.dal.hrm_management.utils.ViewDataUtils;

import java.util.List;


public class OTManagerForPoAdapter extends RecyclerView.Adapter<OTManagerForPoAdapter.MyViewHolder> {
    private List<OverTime> overtimeList;
    private Context context;
    private List<OverTime> overtimeListFiltered;


    public OTManagerForPoAdapter(List<OverTime> listOvertime, Context context) {
        this.overtimeList = listOvertime;
        this.context = context;
        this.overtimeListFiltered = listOvertime;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_ot_manager_of_po, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final OverTime overTime = overtimeList.get(position);
        ViewDataUtils.setDataToView(holder.tvNameEmp, overTime.getNameEmployee());
        ViewDataUtils.setDataToView(holder.tvNameProject, overTime.getNameProject());
        ViewDataUtils.setDataDateToView(holder.tvDate, overTime.getDate());
        if (overTime.getDayTypes() != null) {
            ViewDataUtils.setDataDateToView(holder.tvTypeDate, StringUtils.toUpperCaseFirstChar(overTime.getDayTypes().getNameDayType()));
        } else {
            holder.tvTypeDate.setText(R.string.infor_null);
        }
        ViewDataUtils.setDataTimeWithHH_MM(holder.tvFrom, overTime.getStartTime());
        ViewDataUtils.setDataTimeWithHH_MM(holder.tvTo, overTime.getEndTime());
        ViewDataUtils.setDataToView(holder.tvReason, overTime.getReason());
        ViewDataUtils.setDataToView(holder.tvNumberTime, overTime.getTotalTime());

        if (overTime.getOvertimeStatuses() != null) {
            final String status = overTime.getOvertimeStatuses().getName();
            if (status.equalsIgnoreCase("Not yet") || status.equalsIgnoreCase("Reviewing")) {
                //Hide accept time textview
                holder.rl_acceptedTime.setVisibility(View.GONE);
                holder.tvStatus.setVisibility(View.GONE);
                holder.ll_reasonReject.setVisibility(View.GONE);
                holder.ll_button.setVisibility(View.VISIBLE);

            } else {
                //show status of overtime
                holder.ll_button.setVisibility(View.GONE);
                ViewDataUtils.setDataToView(holder.tvAcceptTime, overTime.getCorrectTotalTime());
                if (status.equalsIgnoreCase("Accepted")) {
                    holder.ll_reasonReject.setVisibility(View.GONE);
                } else {
                    ViewDataUtils.setDataToView(holder.tvReasonReject, overTime.getReasonReject());
                }
            }
            ViewDataUtils.setDataToView(holder.tvStatus, status);
        } else {
            holder.tvStatus.setText(R.string.infor_null);
        }

        holder.imbAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Send status = accepted to server
                updateStatusToServer();
                holder.ll_button.setVisibility(View.GONE);
                holder.ll_reasonReject.setVisibility(View.GONE);
                holder.tvStatus.setVisibility(View.VISIBLE);
                holder.tvStatus.setText("Accepted");
                holder.tvStatus.setTextColor(context.getResources().getColor(R.color.color_green));
                holder.rl_acceptedTime.setVisibility(View.VISIBLE);
            }
        });
        holder.imbReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogReason(holder);
            }
        });
    }

    /**
     * TODO:send json contain status to server
     */
    private void updateStatusToServer() {

    }

    public void showDialogReason(final MyViewHolder holder) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_input_accept_time, null, false);
        final EditText edt_acceptTime = view.findViewById(R.id.edt_acceptTime);
        final EditText edt_reasonReject = view.findViewById(R.id.edt_reasonReject);
        builder.setView(view);
        builder.setTitle("Please enter accept time and reason reject?");
        builder.setCancelable(false);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //update status to server
                updateStatusToServer();
                holder.ll_button.setVisibility(View.GONE);
                holder.tvStatus.setVisibility(View.VISIBLE);
                holder.tvStatus.setText("Rejected");
                holder.tvStatus.setTextColor(context.getResources().getColor(R.color.color_red));
                holder.ll_reasonReject.setVisibility(View.VISIBLE);
                holder.rl_acceptedTime.setVisibility(View.VISIBLE);
                holder.tvReasonReject.setText(edt_reasonReject.getText());
                holder.tvAcceptTime.setText(edt_acceptTime.getText());
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return overtimeListFiltered.size();
    }

//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence charSequence) {
//                String charString = charSequence.toString();
//                if (charString.isEmpty()) {
//                    overtimeListFiltered = overtimeList;
//                } else {
//                    List<OverTime> filteredList = new ArrayList<>();
//                    for (OverTime row : overtimeList) {
//                        if (row.getNameEmployee().toLowerCase().contains(charString.toLowerCase()) || row.getNameProject().toLowerCase().contains(charString.toLowerCase())) {
//                            filteredList.add(row);
//                        }
//                    }
//                    overtimeListFiltered = filteredList;
//                }
//                ;
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = overtimeListFiltered;
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence charSequence, FilterResults results) {
//                overtimeListFiltered = (ArrayList<OverTime>) results.values;
//                notifyDataSetChanged();
//            }
//        };
//    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout absences_layout;
        TextView tvNameProject, tvNameEmp, tvTypeDate, tvDate, tvFrom, tvTo, tvReason, tvNumberTime, tvAcceptTime, tvStatus, tvReasonReject;
        ImageButton imbAccept, imbReject;
        LinearLayout ll_button, ll_reasonReject;
        RelativeLayout rl_acceptedTime;

        public MyViewHolder(View itemView) {
            super(itemView);
            absences_layout = itemView.findViewById(R.id.absences_layout);
            tvNameProject = itemView.findViewById(R.id.tvItemListOTOfPO_NameProject);
            tvNameEmp = itemView.findViewById(R.id.tvItemListOTOfPO_NameEmp);
            tvTypeDate = itemView.findViewById(R.id.tvItemListOTOfPO_TypeDay);
            tvDate = itemView.findViewById(R.id.tvItemListOTOfPO_DateOT);
            tvFrom = itemView.findViewById(R.id.tvItemListOTOfPO_From);
            tvTo = itemView.findViewById(R.id.tvItemListOTOfPO_To);
            tvReason = itemView.findViewById(R.id.tvItemListOTOfPO_Reason);
            tvNumberTime = itemView.findViewById(R.id.tvItemListOTOfPO_NumberTime);
            tvAcceptTime = itemView.findViewById(R.id.tvItemListOTOfPO_AcceptTime);
            tvStatus = itemView.findViewById(R.id.tv_status);
            ll_button = itemView.findViewById(R.id.ll_button);
            imbAccept = itemView.findViewById(R.id.imbItemListOTOfPO_Accept);
            imbReject = itemView.findViewById(R.id.imbItemListOTOfPO_Reject);
            ll_reasonReject = itemView.findViewById(R.id.ll_reasonReject);
            tvReasonReject = itemView.findViewById(R.id.tvPO_ReasonReject);
            rl_acceptedTime = itemView.findViewById(R.id.rl_acceptTime);
            ll_button.setVisibility(View.GONE);
            ll_reasonReject.setVisibility(View.GONE);
        }
    }
}
