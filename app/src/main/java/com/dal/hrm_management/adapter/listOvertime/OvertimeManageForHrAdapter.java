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
import com.dal.hrm_management.models.manageOvertime.hr.viewList.Overtime;
import com.dal.hrm_management.presenters.home.HomePresenter;
import com.dal.hrm_management.presenters.managerOvertime.status.UpdateStatusPresenter;
import com.dal.hrm_management.utils.Constant;
import com.dal.hrm_management.utils.DateTimeUtils;
import com.dal.hrm_management.utils.StringUtils;
import com.dal.hrm_management.utils.ViewDataUtils;
import com.dal.hrm_management.views.manageOvertime.hr.IOvertimeManageOfHrFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.RequestBody;

public class OvertimeManageForHrAdapter extends RecyclerView.Adapter<OvertimeManageForHrAdapter.Holder> {
    private Context context;
    private List<Overtime> listOvertime;
    private UpdateStatusPresenter updateStatusPresenter;

    public OvertimeManageForHrAdapter(Context context, List<Overtime> list, UpdateStatusPresenter updateStatusPresenter) {
        this.context = context;
        this.listOvertime = list;
        this.updateStatusPresenter = updateStatusPresenter;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_overtime_list_hr, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.setIsRecyclable(false);
        final Overtime overtime = listOvertime.get(position);
        holder.tvNameEmp.setText(overtime.getNameEmployee());
        ViewDataUtils.setDataToView(holder.tvNameProject, overtime.getNameProject());
        ViewDataUtils.setDataTimeWithHH_MM(holder.tvFromTime, overtime.getStartTime());
        ViewDataUtils.setDataTimeWithHH_MM(holder.tvToTime, overtime.getEndTime());
        ViewDataUtils.setDataDateToView(holder.tvDate, overtime.getDate());
        ViewDataUtils.setDataToView(holder.tvReason, overtime.getReason());
        if (overtime.getOvertimeStatuses() != null) {
            String status = overtime.getOvertimeStatuses().getName();
            //check if member = CEO --> display all overtime and permit CEO accept and reject
            if (HomePresenter.profile.getEmail().equalsIgnoreCase(Constant.EMAIL_CEO)) {
                if (status.equalsIgnoreCase("Not yet") || status.equalsIgnoreCase("Reviewing")) {
                    //Hide accept time textview
                    holder.rl_acceptedTime.setVisibility(View.GONE);
                    holder.tvStatus.setVisibility(View.GONE);
                    holder.ll_reasonReject.setVisibility(View.GONE);
                    holder.ll_button.setVisibility(View.VISIBLE);

                } else {
                    holder.ll_button.setVisibility(View.GONE);
                }
            } else {
                holder.ll_button.setVisibility(View.GONE);
            }
            if (status.toLowerCase().equals("rejected")) {
                ViewDataUtils.setDataToView(holder.tvReasonReject, overtime.getReasonReject());
                holder.tvStatus.setTextColor(context.getResources().getColor(R.color.color_red));
            } else {
                holder.ll_reasonReject.setVisibility(View.GONE);
                holder.tvStatus.setTextColor(context.getResources().getColor(R.color.color_green));
            }
            holder.tvStatus.setText(status);
        } else {
            holder.tvStatus.setText(R.string.infor_null);
        }
        if (overtime.getDayTypes() != null) {
            ViewDataUtils.setDataToView(holder.tvTypeDay, StringUtils.toUpperCaseFirstChar(overtime.getDayTypes().getNameDayType()));
        }
        ViewDataUtils.setDataToView(holder.tvAcceptedTime, overtime.getCorrectTotalTime());
        ViewDataUtils.setDataToView(holder.tvTotalTime, overtime.getTotalTime());
        setEventToButton(overtime,holder, position);
    }

    /**
     * set onclick listener for accept and reject overtime
     *
     * @param holder
     */
    private void setEventToButton(final Overtime overtime, final Holder holder, final int position) {
        holder.btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Send status = accepted to server
                updateStatusToServer(position, 3, "", 0.0);
            }
        });
        holder.btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogReason(position);
            }

            public void showDialogReason(final int position) {
                final Double timeLimit = DateTimeUtils.getHoursDistanceFromTimeBeginToEnd(overtime.getStartTime(),overtime.getEndTime());
                View view = LayoutInflater.from(context).inflate(R.layout.dialog_input_accept_time, null, false);
                final EditText edt_acceptTime = view.findViewById(R.id.edt_acceptTime);
                final EditText edt_reasonReject = view.findViewById(R.id.edt_reasonReject);
                final android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(context)
                        .setView(view)
                        .setTitle("Please enter accept time and reason reject?")
                        .setPositiveButton("OK",null)
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();

                Button btnPositive = dialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE);
                btnPositive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (checkValidateAcceptTime()==null) {
                            updateStatusToServer(position, 4, edt_reasonReject.getText() + "", Double.parseDouble(edt_acceptTime.getText() + ""));
                            dialog.dismiss();
                        }
                    }
                    private View checkValidateAcceptTime() {
                        edt_acceptTime.setError(null);
                        double acceptTimeEnter = Double.parseDouble(edt_acceptTime.getText().toString());
                        if (acceptTimeEnter > timeLimit){
                            edt_acceptTime.setError(context.getString(R.string.error_message_enter_accept_time)+" "+timeLimit);
                            return edt_acceptTime;
                        }
                        return  null;
                    }
                });
            }
        });
    }



    /**
     * update status to server
     */
    private void updateStatusToServer(int position, int idStatus, String reasonReject, Double correctTotalTime) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("overtimeStatusId", idStatus + "");
            jsonObject.put("reasonReject", reasonReject);
            jsonObject.put("correctTotalTime", correctTotalTime + "");
            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
            updateStatusPresenter.updateStatusOvertime(listOvertime.get(position).getId(), body);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return listOvertime == null ? 0 : listOvertime.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        private TextView tvNameProject, tvDate, tvFromTime, tvToTime, tvTotalTime, tvAcceptedTime, tvReason, tvStatus, tvTypeDay, tvNameEmp, tvReasonReject;
        private LinearLayout ll_reasonReject, ll_button;
        private RelativeLayout rl_acceptedTime;
        private ImageButton btnAccept, btnReject;

        public Holder(View itemView) {
            super(itemView);
            tvNameEmp = itemView.findViewById(R.id.tvItemOvertimeListHR_NameEmp);
            tvNameProject = itemView.findViewById(R.id.tvItemOvertimeListHR_NameProject);
            tvDate = itemView.findViewById(R.id.tvItemOvertimeListHR_Date);
            tvFromTime = itemView.findViewById(R.id.tvItemOvertimeListHR_FromTime);
            tvToTime = itemView.findViewById(R.id.tvItemOvertimeListHR_ToTime);
            tvTotalTime = itemView.findViewById(R.id.tvHr_NumberTime);
            tvReason = itemView.findViewById(R.id.tvItemOvertimeListHR_Reason);
            tvStatus = itemView.findViewById(R.id.tvItemOvertimeListHR_Status);
            tvTypeDay = itemView.findViewById(R.id.tvItemOvertimeListHR_Type);
            tvAcceptedTime = itemView.findViewById(R.id.tvHr_AcceptTime);
            ll_reasonReject = itemView.findViewById(R.id.ll_reasonReject);
            tvReasonReject = itemView.findViewById(R.id.tvHr_ReasonReject);
            ll_button = itemView.findViewById(R.id.ll_button);
            btnAccept = itemView.findViewById(R.id.imbItemListOTOfHr_Accept);
            btnReject = itemView.findViewById(R.id.imbItemListOTOfPO_Reject);
            rl_acceptedTime = itemView.findViewById(R.id.rl_acceptTime);
        }
    }
}
