package com.assignment.jifflenow.plumber;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.assignment.jifflenow.plumber.dto.Appointment;
import com.assignment.jifflenow.plumber.dto.Days;
import com.assignment.jifflenow.utils.GsonRequest;
import com.assignment.jifflenow.utils.VolleyHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nagaraj on 5/1/16.
 */
public class PlumberManager {

    private static PlumberManager sInstance;
    private Map<String, List<Appointment>> mAppointments;

    public static synchronized PlumberManager getInstance() {
        if (sInstance == null)
            sInstance = new PlumberManager();
        return sInstance;
    }

    private PlumberManager() {
        mAppointments = new HashMap<>();
    }

    public void downloadAppointments(Context ctx, String url, final Response.Listener<Days> successListener,
                                     final Response.ErrorListener errorListener, String requestTag) {

        PlumberConnectionManager.downloadAppointments(ctx, url, new Response.Listener<Days>() {
            @Override
            public void onResponse(Days response) {
                mAppointments = response.days;
                successListener.onResponse(response);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                errorListener.onErrorResponse(error);

            }
        }, requestTag);
    }

    public List<Appointment> getAppointMents(String day) {
        return mAppointments.get(day);
    }

    public static void clear() {
        sInstance = null;
    }
}
