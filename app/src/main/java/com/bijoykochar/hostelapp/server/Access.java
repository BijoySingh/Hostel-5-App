package com.bijoykochar.hostelapp.server;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bijoykochar.hostelapp.fragments.RefreshBasedFragment;
import com.bijoykochar.hostelapp.items.AccessItem;
import com.bijoykochar.hostelapp.utils.Functions;
import com.bijoykochar.hostelapp.utils.ListCreator;
import com.bijoykochar.hostelapp.utils.Preferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * The network call handler
 * Created by bijoy on 10/24/15.
 */
public class Access {

    Context context;

    private Access(Context context) {
        this.context = context;
    }

    public static Access getInstance(Context context) {
        return new Access(context);
    }

    public void getDataFromApi(final AccessItem access,final RefreshBasedFragment fragment) {
        StringRequest jsonRequest = new StringRequest
                (Request.Method.GET, access.url, new Response
                        .Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        fragment.stopRefreshIndicator();
                        if (!isError(response)) {
                            Functions.offlineDataWriter(context, access.filename, response);
                        }
                        fragment.refreshFileList();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        fragment.stopRefreshIndicator();
                        error.printStackTrace();
                    }
                });

        Volley.newRequestQueue(context).add(jsonRequest);
    }

    public void getDataFromAuthApi(final AccessItem access,final RefreshBasedFragment fragment) {
        Preferences preferences = Preferences.getInstance(context);

        if (!preferences.isLoggedIn()) {
            return;
        }

        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.POST, access.url, authenticationData(), new Response
                        .Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonResponse) {
                        String response = jsonResponse.toString();
                        Log.d(Access.class.getSimpleName(), response);
                        fragment.stopRefreshIndicator();
                        if (!isError(response)) {
                            Functions.offlineDataWriter(context, access.filename, response);
                        }
                        fragment.refreshFileList();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        fragment.stopRefreshIndicator();
                        error.printStackTrace();
                    }
                });

        Volley.newRequestQueue(context).add(jsonRequest);
    }

    public Boolean isError(String response) {
        try {
            JSONObject json = new JSONObject(response);
            return json.has("error");
        } catch (JSONException e) {
            Log.e(Access.class.getSimpleName(), e.getMessage(), e);
            return true;
        }
    }

    public JSONObject authenticationData() {
        Preferences preferences = Preferences.getInstance(context);

        String rollNo = preferences.getRollNumber();
        String token = preferences.getToken();
        Map<String, String> authenticationMap = new HashMap<>();
        authenticationMap.put("rollno", rollNo);
        authenticationMap.put("token", token);

        return new JSONObject(authenticationMap);
    }
}
