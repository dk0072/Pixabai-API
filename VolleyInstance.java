package com.nogravity.pixabaytest;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyInstance {

    RequestQueue requestQueue;
    static VolleyInstance volleyInstance;

    public VolleyInstance(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized VolleyInstance getInstance(Context context){
        if(volleyInstance==null)
            volleyInstance = new VolleyInstance(context);
        return volleyInstance;
    }

    public RequestQueue getRequestQueue(){
        return requestQueue;
    }
}
