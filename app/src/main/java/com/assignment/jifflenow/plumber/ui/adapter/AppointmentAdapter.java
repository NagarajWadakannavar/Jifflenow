package com.assignment.jifflenow.plumber.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.assignment.jifflenow.R;
import com.assignment.jifflenow.plumber.dto.Appointment;

import java.util.List;

/**
 * Created by nagaraj on 7/1/16.
 */
public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {

    private final LayoutInflater mInflater;
    private final List<Appointment> mAppointmentList;

    public AppointmentAdapter(Context ctx, List<Appointment> weekDaysList) {
        mAppointmentList = weekDaysList;
        mInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mName, mAddress, mTime;


        public ViewHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.name);
            mAddress = (TextView) itemView.findViewById(R.id.address);
            mTime = (TextView) itemView.findViewById(R.id.time);

        }


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.adapter_appointment_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Appointment appointment = mAppointmentList.get(position);
        holder.mName.setText(appointment.name);
        holder.mAddress.setText(appointment.address);
        holder.mTime.setText(appointment.time);


    }

    @Override
    public int getItemCount() {
        return mAppointmentList.size();
    }
}