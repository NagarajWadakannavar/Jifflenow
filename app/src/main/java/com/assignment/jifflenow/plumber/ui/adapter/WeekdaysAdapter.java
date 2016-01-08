package com.assignment.jifflenow.plumber.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.assignment.jifflenow.R;

import java.util.List;

/**
 * Created by nagaraj on 7/1/16.
 */
public class WeekdaysAdapter extends RecyclerView.Adapter<WeekdaysAdapter.ViewHolder> {

    private final LayoutInflater mInflater;
    private final List<String> mDaysList;
    private final View.OnClickListener mItemClickListener;

    public WeekdaysAdapter(Context ctx, List<String> weekDaysList, View.OnClickListener itemClickListener) {
        mDaysList = weekDaysList;
        mInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mItemClickListener = itemClickListener;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView mTextview;
        private final View.OnClickListener mItemClickListener;
        private final List<String> mDaysList;


        public ViewHolder(View itemView, List<String> weekDaysList, View.OnClickListener itemClickListener) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTextview = (TextView) itemView.findViewById(R.id.title);
            mItemClickListener = itemClickListener;
            mDaysList = weekDaysList;

        }

        @Override
        public void onClick(View v) {
            v.setTag(getAdapterPosition());
            mItemClickListener.onClick(v);

        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.adapter_week_days_item, parent, false);
        return new ViewHolder(view, mDaysList, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextview.setText(mDaysList.get(position));

    }

    @Override
    public int getItemCount() {
        return mDaysList.size();
    }
}
