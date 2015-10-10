package com.cloudtv.hahong.cycleview.volley;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.cloudtv.hahong.cycleview.volley.VolleyPostRequest;

import org.json.JSONObject;

public interface VolleyResponseListener {

    public void onDataStartGetRequest(JsonObjectRequest request);
    public void onDataStartPostRequest(VolleyPostRequest request);
    public void onDataSuccessResponse(JSONObject response, int requestCode);
    public void onDataErrorResponse(VolleyError error, int requestCode);

}
