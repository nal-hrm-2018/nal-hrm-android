package com.dal.hrm_management.adapter.dashboard;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dal.hrm_management.R;
import com.dal.hrm_management.models.dashboard.notification.Notification;
import com.dal.hrm_management.utils.Constant;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.Holder> {
    private Context context;
    private List<Notification> list;

    public NotificationAdapter(Context context, List<Notification> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dashboard_notification, parent, false);
        return new NotificationAdapter.Holder(itemView);
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        String title = list.get(position).getTitle().substring(0, Constant.NOTIFICATION_LENGTH);
        if (list.get(position).getTitle().length() > Constant.NOTIFICATION_LENGTH) {
            holder.tvTitle.setText(title + " ...");
        }else{
            holder.tvTitle.setText(title);
        }
        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(list.get(position).getTitle());
                builder.setMessage(list.get(position).getContent());
                builder.setCancelable(true);
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private ImageButton btnDetail;
        public Holder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvItemDashboardNotification_Title);
            btnDetail = itemView.findViewById(R.id.btnItemDashboardNotification_Detail);

        }
    }
}
