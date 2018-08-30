package com.dal.hrm_management.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dal.hrm_management.R;
import com.dal.hrm_management.models.fakeData.Absence;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luu Ngoc Lan on 02-Aug-18.
 */

public class AbsenceManagerForPoAdapter extends RecyclerView.Adapter<AbsenceManagerForPoAdapter.MyViewHolder> implements Filterable {
    private List<Absence> absenceList;
    private Context context;
    private List<Absence> absenceListFilter;
    private AbsenceAdapterListener listener;

    public AbsenceManagerForPoAdapter(List<Absence> absenceList, Context context, AbsenceAdapterListener listener) {
        this.absenceList = absenceList;
        this.context = context;
        this.absenceListFilter = absenceList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_absence_manager_of_po, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Absence absence = absenceListFilter.get(position);
        holder.tv_nameEmployee.setText(absence.getName());
        holder.tv_nameProject.setText(absence.getNameProject());
        holder.tv_reason.setText(absence.getReason());
        holder.tv_from.setText(absence.getFrom());
        holder.tv_to.setText(absence.getTo());
        holder.setIsRecyclable(false);

    }


    @Override
    public int getItemCount() {
        return absenceListFilter.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    absenceListFilter = absenceList;
                } else {
                    List<Absence> filteredList = new ArrayList<>();
                    for (Absence row : absenceList) {
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())||row.getNameProject().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    absenceListFilter = filteredList;
                };
                FilterResults filterResults = new FilterResults();
                filterResults.values = absenceListFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                absenceListFilter = (ArrayList<Absence>) results.values;
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
        ItemClickListener itemClickListener;
        public MyViewHolder(View itemView) {
            super(itemView);
            absences_layout = (LinearLayout) itemView.findViewById(R.id.absences_layout);
            tv_nameEmployee = (TextView) itemView.findViewById(R.id.tv_nameEmployee);
            tv_nameProject = (TextView) itemView.findViewById(R.id.tv_nameProject);
            tv_reason = (TextView) itemView.findViewById(R.id.tv_reason);
            tv_from = (TextView) itemView.findViewById(R.id.tv_from);
            tv_to = (TextView) itemView.findViewById(R.id.tv_to);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);
        }
    }

    public interface AbsenceAdapterListener {
        void onAbsenceSelected(Absence absence);
    }
}
