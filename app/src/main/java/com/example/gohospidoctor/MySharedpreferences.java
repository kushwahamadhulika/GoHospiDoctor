package com.example.gohospidoctor;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Response;

public class MySharedpreferences {
    private static final MySharedpreferences ourInstance = new MySharedpreferences();

    public static MySharedpreferences getInstance() {
        return ourInstance;
    }

    private MySharedpreferences() {
    }

    public SharedPreferences sharedPreferencesInstance(Context mContext) {
        return mContext.getSharedPreferences("GIGS", Context.MODE_PRIVATE);
    }


    public void save(Context mContext, Response.Listener<String> listener, String key, String value) {
        mContext.getSharedPreferences("GIGS", Context.MODE_PRIVATE).edit().putString(key, value).apply();
    }

    public void remove(Context mContext, String key) {
        mContext.getSharedPreferences("GIGS", Context.MODE_PRIVATE).edit().remove(key).commit();
    }


    public String get(Context mContext, String key) {
        return mContext.getSharedPreferences("GIGS", Context.MODE_PRIVATE).getString(key, null);
    }
    public void removeAll(Context mContext) {
        mContext.getSharedPreferences("GIGS", Context.MODE_PRIVATE).edit().clear().commit();
    }
}

