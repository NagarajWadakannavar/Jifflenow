package com.assignment.jifflenow.plumber.ui;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.assignment.jifflenow.common.ui.RecyclerViewFragment;
import com.assignment.jifflenow.plumber.PlumberManager;
import com.assignment.jifflenow.plumber.dto.Appointment;
import com.assignment.jifflenow.plumber.ui.adapter.AppointmentAdapter;
import com.assignment.jifflenow.utils.Constants;
import com.assignment.jifflenow.utils.Utility;

import java.util.List;

/**
 * A simple {@link RecyclerViewFragment} subclass.
 */
public class AppointmentFragment extends RecyclerViewFragment {


    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        return manager;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!Utility.isAvailable(getActivity())) {
            Utility.showNoNetworkAlert(getActivity());
            return;
        }
        List<Appointment> appointmentList = PlumberManager.getInstance().getAppointMents(getArguments().getString(Constants.BundleKeys.DAY));
        mRecyclerView.setAdapter(new AppointmentAdapter(getActivity(), appointmentList));
        hideProgressBar();
    }

}
