package com.assignment.jifflenow.common.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.assignment.jifflenow.R;
import com.assignment.jifflenow.plumber.dto.Appointment;

import java.util.List;

/**
 * A simple {@link BaseFragment} subclass.
 */
public abstract class RecyclerViewFragment extends BaseFragment {

    public interface ItemClickListener {
        void onWeekDayClicked(String day);
    }


    protected RecyclerView mRecyclerView;
    private TextView mEmptyView;
    private ProgressBar mProgressBar;


    public abstract RecyclerView.LayoutManager getLayoutManager();


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        mEmptyView = (TextView) view.findViewById(R.id.empty_view);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(getLayoutManager());
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    protected void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }


    protected void showEmptyView() {
        if (mRecyclerView.getAdapter() != null && mRecyclerView.getAdapter().getItemCount() == 0) {
            mEmptyView.setVisibility(View.VISIBLE);
        }
    }


}
