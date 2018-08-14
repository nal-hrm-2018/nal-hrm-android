package com.dal.hrm_management.adapter.listEmployee;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dal.hrm_management.R;
import com.dal.hrm_management.adapter.ItemClickListener;
import com.dal.hrm_management.models.listEmployee.ListEmployees;
import com.dal.hrm_management.views.employee.ViewInforEmployeeActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListEmployeeAdapter extends RecyclerView.Adapter<ListEmployeeAdapter.DataViewHolder> {
    private List<ListEmployees> arr;
    private Context context;

    public ListEmployeeAdapter(Context context, List<ListEmployees> arr) {
        this.context = context;
        this.arr = arr;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_emp_working, parent, false);
        return new ListEmployeeAdapter.DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DataViewHolder holder, int position) {
        holder.tv_hoVaTen.setText(arr.get(position).getNameEmployee());
        holder.tv_email.setText(arr.get(position).getEmail());
        holder.tv_chucVu.setText(arr.get(position).getRole().getNameRole());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(final View view, int position, boolean isLongClick) {
                PopupMenu popup = new PopupMenu(view.getContext(), view);
                popup.getMenuInflater().inflate(R.menu.popup_menu_employee_list, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(context.getApplicationContext(), "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        switch (item.getItemId()){
                            case R.id.action_viewProfile:
                                Intent intent = new Intent(view.getContext().getApplicationContext(), ViewInforEmployeeActivity.class);
                                context.startActivity(intent);
                            case R.id.action_editProfile:
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

    public static class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CircleImageView imv_avaEmp;
        private TextView tv_hoVaTen;
        private TextView tv_chucVu;
        private TextView tv_email;
        private ImageView imv_tinhTrangLamViec;
        private ItemClickListener itemClickListener;


        public DataViewHolder(View itemView) {
            super(itemView);
            imv_avaEmp = itemView.findViewById(R.id.imv_avaEmp);
            tv_hoVaTen = itemView.findViewById(R.id.tv_hoVaTen);
            tv_chucVu = itemView.findViewById(R.id.tv_chucVu);
            tv_email = itemView.findViewById(R.id.tv_email);
            imv_tinhTrangLamViec = itemView.findViewById(R.id.imv_tinhTrangLamViec);
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
