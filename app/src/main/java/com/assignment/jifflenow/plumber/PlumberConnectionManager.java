package com.assignment.jifflenow.plumber;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.assignment.jifflenow.plumber.dto.Appointments;
import com.assignment.jifflenow.plumber.dto.Days;
import com.assignment.jifflenow.utils.GsonRequest;
import com.assignment.jifflenow.utils.VolleyHelper;

/**
 * Created by nagaraj on 5/1/16.
 */
public class PlumberConnectionManager {
    public static void downloadAppointments(Context ctx,String url, Response.Listener<Days> successListener,
                                            Response.ErrorListener errorListener, String requestTag) {
        GsonRequest.Builder builder = new GsonRequest.Builder<>(Request.Method.GET, Days.class, url, successListener, errorListener);
        VolleyHelper.getInstance(ctx).addToRequestQueue(builder.build().setTag(requestTag));
    }
}
