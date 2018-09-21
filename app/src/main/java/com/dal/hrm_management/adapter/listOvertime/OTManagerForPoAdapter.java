package com.dal.hrm_management.adapter.listOvertime;

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
import com.dal.hrm_management.models.manageOvertime.po.viewlist.ItemOverTimePO;

import java.util.ArrayList;
import java.util.List;


public class OTManagerForPoAdapter extends RecyclerView.Adapter<OTManagerForPoAdapter.MyViewHolder> {
    private List<ItemOverTimePO> overtimeList;
    private Context context;
    private List<ItemOverTimePO> overtimeListFiltered;



    public OTManagerForPoAdapter(List<ItemOverTimePO> listOvertime, Context context) {
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
        holder.tvNameEmp.setText(overtimeList.get(position).getNameEmployee());
        holder.tvNameProject.setText(overtimeList.get(position).getNameProject());
        holder.tvTypeDate.setText(overtimeList.get(position).getDayTypes().getNameDayType());
        holder.tvDate.setText(overtimeList.get(position).getDate());
        holder.tvFrom.setText(overtimeList.get(position).getStartTime());
        holder.tvTo.setText(overtimeList.get(position).getEndTime());
        holder.tvReason.setText(overtimeList.get(position).getReason());
        holder.tvNumberTime.setText(String.valueOf(overtimeList.get(position).getTotalTime()));
        holder.tvAcceptTime.setText(String.valueOf(overtimeList.get(position).getCorrectTotalTime()));
        holder.imbAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.ll_button.setVisibility(View.GONE);
            }
        });
        holder.imbReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogReason();
                holder.ll_button.setVisibility(View.GONE);
            }
        });

//        final OverTime overTime = overtimeListFiltered.get(position);




    }

    public void showDialogReason() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_input_accept_time, null, false);
        final EditText edt_acceptTime = (EditText) view.findViewById(R.id.edt_acceptTime);
        builder.setView(view);
        builder.setTitle("Do you want to reject this form?");
        builder.setCancelable(false);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

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
        TextView tvNameProject,tvNameEmp,tvTypeDate,tvDate,tvFrom,tvTo,tvReason,tvNumberTime,tvAcceptTime;
        ImageButton imbAccept,imbReject;
        LinearLayout ll_button;

        public MyViewHolder(View itemView) {
            super(itemView);
            absences_layout = (LinearLayout) itemView.findViewById(R.id.absences_layout);
            tvNameProject = itemView.findViewById(R.id.tvItemListOTOfPO_NameProject);
            tvNameEmp = itemView.findViewById(R.id.tvItemListOTOfPO_NameEmp);
            tvTypeDate = itemView.findViewById(R.id.tvItemListOTOfPO_TypeDay);
            tvDate = itemView.findViewById(R.id.tvItemListOTOfPO_DateOT);
            tvFrom = itemView.findViewById(R.id.tvItemListOTOfPO_From);
            tvTo = itemView.findViewById(R.id.tvItemListOTOfPO_To);
            tvReason = itemView.findViewById(R.id.tvItemListOTOfPO_Reason);
            tvNumberTime = itemView.findViewById(R.id.tvItemListOTOfPO_NumberTime);
            tvAcceptTime = itemView.findViewById(R.id.tvItemListOTOfPO_AcceptTime);

            ll_button = (LinearLayout) itemView.findViewById(R.id.ll_button);
            imbAccept =  itemView.findViewById(R.id.imbItemListOTOfPO_Accept);
            imbReject =  itemView.findViewById(R.id.imbItemListOTOfPO_Reject);

        }
    }
}
