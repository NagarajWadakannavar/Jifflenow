package com.assignment.jifflenow.utils;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nagaraj on 5/1/16.
 */
public class GsonRequest<T> extends Request<T> {
    private final Gson mGson = new Gson();
    private Class<T> mClazz;
    private Map<String, String> mHeaders;
    private Map<String, String> mParams;
    private final Response.Listener<T> mListener;

    private GsonRequest(Builder builder) {
        super(builder.mMethod, builder.mUrl, builder.mErrorListener);
        mClazz = builder.mClazz;
        mHeaders = builder.mHeaders;
        mParams = builder.mParams;
        mListener = builder.mListener;
    }


    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return mHeaders;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mParams;
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    @Override
    public void deliverError(VolleyError error) {
        super.deliverError(error);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(
                    mGson.fromJson(json, mClazz),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    public static class Builder<T> {
        private final Class<T> mClazz;
        private Map<String, String> mHeaders = new HashMap<>();
        private Map<String, String> mParams = new HashMap<>();
        private final Response.Listener<T> mListener;
        private final String mUrl;
        private final Response.ErrorListener mErrorListener;
        private final int mMethod;


        public Builder(int method, Class<T> clazz, String url, Response.Listener<T> listener, Response.ErrorListener errorListener) {
            mMethod = method;
            mClazz = clazz;
            mUrl = url;
            mListener = listener;
            mErrorListener = errorListener;
        }

        public Builder setHeaders(Map<String, String> headers) {
            mHeaders = headers;
            return this;
        }

        public Builder setParams(Map<String, String> params) {
            mParams = params;
            return this;
        }

        public GsonRequest<T> build() {
            return new GsonRequest<>(this);
        }


    }


}
