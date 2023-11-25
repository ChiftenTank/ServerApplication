package com.example.joker.server.Classes;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Joker on 12/30/2020.
 */

public class Singleton  {
    private static Singleton msingleton;
    private RequestQueue requestQueue;
    private static Context mcContext;

    public Singleton(Context context) {
        this.mcContext = context;
        requestQueue=get_requestQueue();
    }

    private RequestQueue get_requestQueue() {
        if (requestQueue==null)
            Volley.newRequestQueue(mcContext.getApplicationContext());
        return requestQueue;
    }

    public static synchronized Singleton getInstanceSingleton(Context context) {
        if (msingleton==null)
            msingleton=new Singleton(context);
        return msingleton;
    }

    public <T> void add_to_requestqueue(Request<T> t_request){
        get_requestQueue().add(t_request);
    }
}
