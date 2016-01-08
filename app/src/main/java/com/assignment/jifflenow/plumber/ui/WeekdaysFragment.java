package com.assignment.jifflenow.plumber.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.assignment.jifflenow.R;
import com.assignment.jifflenow.common.ui.RecyclerViewFragment;
import com.assignment.jifflenow.plumber.PlumberManager;
import com.assignment.jifflenow.plumber.dto.Appointment;
import com.assignment.jifflenow.plumber.dto.Days;
import com.assignment.jifflenow.plumber.ui.adapter.WeekdaysAdapter;
import com.assignment.jifflenow.utils.Utility;
import com.assignment.jifflenow.utils.VolleyHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link RecyclerViewFragment} subclass.
 */
public class WeekdaysFragment extends RecyclerViewFragment implements View.OnClickListener {

    private static final String APPOINTMENT_TAG = "appointment_tag";
    private ItemClickListener mItemClickListener;
    private Map<String, List<Appointment>> mAppointments;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mItemClickListener = (ItemClickListener) context;
        } catch (ClassCastException e) {
            throw new RuntimeException(context.getClass().getName() + "Must implement " + ItemClickListener.class.getName());
        }
    }

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
        downloadAppointments();
    }

    /**
     */
    private void downloadAppointments() {
        PlumberManager.getInstance().downloadAppointments(getActivity(), getString(R.string.appointment_url), new Response.Listener<Days>() {

            @Override
            public void onResponse(Days response) {
                if (getActivity() == null)
                    return;
                mAppointments = response.days;
                hideProgressBar();
                List<String> days = new ArrayList<String>(response.days.keySet());
                WeekdaysAdapter adapter = new WeekdaysAdapter(getActivity(), days, WeekdaysFragment.this);
                mRecyclerView.setAdapter(adapter);
                showEmptyView();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if (getActivity() == null)
                    return;
                hideProgressBar();

            }
        }, APPOINTMENT_TAG);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        VolleyHelper.getInstance(getActivity()).cancelRequest(APPOINTMENT_TAG);
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        switch (v.getId()) {
            case R.id.list_item:
                List<String> days = new ArrayList<String>(mAppointments.keySet());
                mItemClickListener.onWeekDayClicked(days.get(position));
                break;
        }

    }
}
