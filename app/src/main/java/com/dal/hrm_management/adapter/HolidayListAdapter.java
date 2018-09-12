package com.dal.hrm_management.adapter;


import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dal.hrm_management.R;
import com.dal.hrm_management.models.holiday.Holiday;

import java.util.List;

public class HolidayListAdapter extends RecyclerView.Adapter<HolidayListAdapter.MyViewHolder> {
    private List<Holiday> arr;
    private Context context;

    public HolidayListAdapter(Context context, List<Holiday> arr) {
        this.context = context;
        this.arr = arr;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_holiday, parent, false);
        return new HolidayListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tv_nameHoliday.setText(arr.get(position).getNameHoliday());
        holder.tv_typeHoliday.setText(arr.get(position).getTypeHoliday());
        holder.tv_note.setText(arr.get(position).getNote());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(final View view, int position, boolean isLongClick) {
                PopupMenu popup = new PopupMenu(view.getContext(), view);
                popup.getMenuInflater().inflate(R.menu.popup_menu_holiday_list, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.action_edit_holiday:
                                
                            case R.id.action_remove:
                                break;
                        }
                        return true;
                    }
                });

                popup.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arr == null ? 0 : arr.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_nameHoliday;
        private TextView tv_typeHoliday;
        private TextView tv_date;
        private TextView tv_year;
        private TextView tv_note;
        private ItemClickListener itemClickListener;


        public MyViewHolder(View itemView) {
            super(itemView);
            tv_nameHoliday = itemView.findViewById(R.id.tv_nameHoliday);
            tv_typeHoliday = itemView.findViewById(R.id.tv_typeHoliday);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_year = itemView.findViewById(R.id.tv_year);
            tv_note = itemView.findViewById(R.id.tv_note);
            itemView.setOnClickListener(this);
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
