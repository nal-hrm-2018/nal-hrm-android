package com.dal.hrm_management.adapter.dashboard;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dal.hrm_management.R;
import com.dal.hrm_management.adapter.ItemClickListener;
import com.dal.hrm_management.models.dashboard.notification.Notification;
import com.dal.hrm_management.utils.Constant;
import com.dal.hrm_management.utils.StringUtils;

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

        if (list.get(position).getTitle().length() > Constant.NOTIFICATION_LENGTH) {
            String title = list.get(position).getTitle().substring(0, Constant.NOTIFICATION_LENGTH);
            holder.tvTitle.setText(title + " ...");
        } else {
            holder.tvTitle.setText(list.get(position).getTitle());
        }
        holder.tvEndDate.setText("[" + StringUtils.yyyy_mm_ddTodd_mm_yyyy(list.get(position).getEndDate()) + "]");
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                View view1 = LayoutInflater.from(context).inflate(R.layout.dialog_notification, null, false);
                TextView title = view1.findViewById(R.id.tvItemNotification_title);
                TextView content = view1.findViewById(R.id.tvItemNotification_content);
                TextView team = view1.findViewById(R.id.tvItemNotification_team);
                TextView endDate = view1.findViewById(R.id.tvItemNotification_endDate);
                title.setText(list.get(position).getTitle());
                team.setText(Constant.TEAM_DEFAULT);
                endDate.setText("[" + StringUtils.yyyy_mm_ddTodd_mm_yyyy(list.get(position).getEndDate()) + "]");
                content.setMovementMethod(new ScrollingMovementMethod());
                title.setMovementMethod(new ScrollingMovementMethod());
                content.setText(list.get(position).getContent());
                AlertDialog builder = new AlertDialog.Builder(context)
                        .setView(view1)
                        .setCancelable(true)
                        .show();

//                builder.setView(view);
//                builder.setCancelable(true);
//                builder.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvTitle;
        private TextView tvEndDate;
        private ItemClickListener itemClickListener;

        public Holder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvItemDashboardNotification_Title);
            tvEndDate = itemView.findViewById(R.id.tvItemDashboardNotification_endDate);
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
