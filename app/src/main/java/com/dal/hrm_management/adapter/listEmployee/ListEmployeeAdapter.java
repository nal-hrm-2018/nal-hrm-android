package com.dal.hrm_management.adapter.listEmployee;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.dal.hrm_management.R;
import com.dal.hrm_management.adapter.ItemClickListener;
import com.dal.hrm_management.api.ApiImageClient;
import com.dal.hrm_management.api.RetrofitImageAPI;
import com.dal.hrm_management.models.listEmployee.Employee;
import com.dal.hrm_management.utils.PermissionManager;
import com.dal.hrm_management.views.employee.EditProfileEmployeeActivity;
import com.dal.hrm_management.views.employee.ViewInforEmployeeActivity;
import com.dal.hrm_management.views.listEmployee.ListEmployee;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Credentials;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListEmployeeAdapter extends RecyclerView.Adapter<ListEmployeeAdapter.DataViewHolder> implements Filterable {
    private List<Employee> arr;
    private Context context;
    private List<Employee> employeesListFiltered;
    private EmployeesAdapterListener listener;

    public ListEmployeeAdapter(Context context, List<Employee> arr, EmployeesAdapterListener listener) {
        this.context = context;
        this.arr = arr;
        this.employeesListFiltered = arr;
        this.listener = listener;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        switch (viewType) {
            case 1:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_emp_working, parent, false);
                break;
            case 2:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_emp_endworking, parent, false);
                break;
            default:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_emp_working, parent, false);
                break;

        }

        return new ListEmployeeAdapter.DataViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position) {
        if (employeesListFiltered.get(position).getEndWorkDate() == null) {
            return 2;
        }
        return 1;
    }

    @Override
    public void onBindViewHolder(final DataViewHolder holder, int position) {
        final Employee employee = employeesListFiltered.get(position);
        holder.setIsRecyclable(false);
        RetrofitImageAPI retrofitImageAPI = ApiImageClient.getImageClient().create(RetrofitImageAPI.class);
        String auth = Credentials.basic("hrm_testing", "hrm_testing");
        Call<ResponseBody> call;
        if (employee.getAvatar() != null) {
            call = retrofitImageAPI.getImageDetails(auth, employee.getAvatar().toString());
        } else {
            call = retrofitImageAPI.getImageDetails(auth, "default_avatar.png");
        }
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                        holder.imv_avaEmp.setImageBitmap(bitmap);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
        holder.tv_hoVaTen.setText(employee.getNameEmployee());
        holder.tv_email.setText(employee.getEmail());
        String role = employee.getRole().getNameRole();
        if (role.toLowerCase().equals("hr")) {
            holder.tv_chucVu.setBackgroundColor(context.getResources().getColor(R.color.color_red));
            holder.tv_chucVu.setTextColor(context.getResources().getColor(R.color.color_white));
        } else if (role.toLowerCase().equals("po")) {
            holder.tv_chucVu.setBackgroundColor(context.getResources().getColor(R.color.color_orange));
            holder.tv_chucVu.setTextColor(context.getResources().getColor(R.color.color_white));
        } else if (role.toLowerCase().equals("dev")) {
            holder.tv_chucVu.setBackgroundColor(context.getResources().getColor(R.color.color_green));
            holder.tv_chucVu.setTextColor(context.getResources().getColor(R.color.color_white));
        } else if (role.toLowerCase().equals("accountant")) {
            holder.tv_chucVu.setBackgroundColor(context.getResources().getColor(R.color.color_gray));
            holder.tv_chucVu.setTextColor(context.getResources().getColor(android.R.color.black));
        }
        holder.tv_chucVu.setText(employee.getRole().getNameRole());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(final View view, final int position, boolean isLongClick) {
                PopupMenu popup = new PopupMenu(view.getContext(), view);
                popup.getMenuInflater().inflate(R.menu.popup_menu_employee_list, popup.getMenu());
                if (!PermissionManager.isPermited(PermissionManager.listPermissions, "view_employee_basic")) {
                    popup.getMenu().findItem(R.id.action_viewProfile).setVisible(false);
                }
                if (!PermissionManager.isPermited(PermissionManager.listPermissions, "edit_employee_basic")) {
                    popup.getMenu().findItem(R.id.action_editProfile).setVisible(false);
                }
                if (!PermissionManager.isPermited(PermissionManager.listPermissions, "reset_password_employee")) {
                    popup.getMenu().findItem(R.id.action_resetPass).setVisible(false);
                }
                if (!PermissionManager.isPermited(PermissionManager.listPermissions, "delete_employee")) {
                    popup.getMenu().findItem(R.id.action_remove).setVisible(false);
                }
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_viewProfile:
                                Intent intent = new Intent(context, ViewInforEmployeeActivity.class);
                                int id = employee.getIdEmployee();
                                intent.putExtra("id_employee", id);
                                context.startActivity(intent);
                                break;
                            case R.id.action_editProfile:
                                Intent editIntent = new Intent(view.getContext().getApplicationContext(), EditProfileEmployeeActivity.class);
                                int id_1 = employee.getIdEmployee();
                                editIntent.putExtra("id_employee_edit", id_1);
                                context.startActivity(editIntent);
                                break;
                            case R.id.action_resetPass:
                                break;
                            case R.id.action_remove:
                                break;
                        }
                        return true;
                    }
                });
                popup.setGravity(Gravity.DISPLAY_CLIP_HORIZONTAL);
                popup.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return employeesListFiltered == null ? 0 : employeesListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    employeesListFiltered = arr;
                } else {
                    List<Employee> filteredList = new ArrayList<>();
                    for (Employee employee : arr) {
                        if (employee.getNameEmployee().toLowerCase().contains(charString.toLowerCase())
                                ||employee.getRole().getNameRole().toLowerCase().contains(charString.toLowerCase())
                                ||employee.getEmail().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(employee);
                        }
                    }
                    employeesListFiltered = filteredList;
                }
                ;
                FilterResults filterResults = new FilterResults();
                filterResults.values = employeesListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                employeesListFiltered = (List<Employee>) results.values;
                notifyDataSetChanged();
            }
        };
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

    public interface EmployeesAdapterListener {
        void onEmployeeSelected(ListEmployee employee);
    }
}
