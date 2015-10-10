package com.cloudtv.hahong.cycleview.volley;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.cloudtv.hahong.cycleview.AppControlApplication;
import com.cloudtv.hahong.cycleview.R;
import com.cloudtv.hahong.cycleview.config.Config;

import org.json.JSONObject;

import java.util.Map;


public class VolleyUtils {

    public static final int VOLLEY_REQUEST_TIMEOUT = 15000;

    private static VolleyUtils sInstance = null;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static String TAG = "VolleyUtils";
    private VolleyUtils() {
        mRequestQueue = Volley.newRequestQueue(AppControlApplication.getAppContext());
        mImageLoader = new ImageLoader(mRequestQueue, BitmapCache.getInstance());

    }

    public static VolleyUtils getInstance() {
        if (sInstance == null) {
            sInstance = new VolleyUtils();
        }
        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    public static void loadImage(String url, ImageView view) {
        ImageListener listener = ImageLoader.getImageListener(view, R.drawable.icon_empty, R.drawable.icon_empty);

        VolleyUtils.getInstance().getImageLoader().get(url, listener);


    }

    public static void loadImage(String url, ImageListener listener) {
        VolleyUtils.getInstance().getImageLoader().get(url, listener);
    }

    public static void loadImage(String url, NetworkImageView view) {
        view.setTag(url);
        view.setImageUrl(url, VolleyUtils.getInstance().getImageLoader());
    }

    public static ImageListener getRoundedImageListener(final Resources resources, final ImageView view) {
        return new ImageLoader.ImageListener() {

            @Override
            public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                Bitmap bitmap = imageContainer.getBitmap();
                if (bitmap != null && resources != null) {
                    RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(resources, bitmap);
                    drawable.setCornerRadius(bitmap.getWidth() / 2);
                    drawable.setAntiAlias(true);
                    if (view != null) {
                        view.setImageDrawable(drawable);
                    }
                }
            }

            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        };
    }

    public static JsonObjectRequest getJSON(String url, final VolleyResponseListener listener, final int requestCode) {
        RequestQueue requestQueue = VolleyUtils.getInstance().getRequestQueue();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (Config.VOLLEY_DEBUG) {
                            Log.d("getJSON", "response =" + response);
                        }

                        if (listener != null) {
                            listener.onDataSuccessResponse(response, requestCode);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (Config.VOLLEY_DEBUG) {
                    Log.d("getJSON", "error = " + error.getMessage());
                }

                if (listener != null) {
                    listener.onDataErrorResponse(error, requestCode);
                }
            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(VOLLEY_REQUEST_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        if (listener != null) {
            listener.onDataStartGetRequest(jsonObjectRequest);
        }

        requestQueue.add(jsonObjectRequest);

        return jsonObjectRequest;
    }

    public static VolleyPostRequest postJSON(String url, final VolleyResponseListener listener, final Map<String, String> params, final int requestCode) {
        RequestQueue requestQueue = VolleyUtils.getInstance().getRequestQueue();
        final VolleyPostRequest request = new VolleyPostRequest(Request.Method.POST, url, params,
                new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject response) {
                        if (Config.VOLLEY_DEBUG) {
                            Log.d("postJSON", "success =" + response);
                        }

                        if (listener != null) {
                            listener.onDataSuccessResponse(response, requestCode);
                        }
                    }
                }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                if (Config.VOLLEY_DEBUG) {
                    Log.d("postJSON", "error = " + error.getMessage());
                }

                if (listener != null) {
                    listener.onDataErrorResponse(error, requestCode);
                }
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(VOLLEY_REQUEST_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        if (listener != null) {
            listener.onDataStartPostRequest(request);
        }

        requestQueue.add(request);

        return request;
    }

    public static VolleyPostRequest postJSON(String url, final Map<String, String> params) {
        RequestQueue requestQueue = VolleyUtils.getInstance().getRequestQueue();
        final VolleyPostRequest request = new VolleyPostRequest(Request.Method.POST, url, params,
                new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject response) {
                        if (Config.VOLLEY_DEBUG) {
                            Log.d("postJSON", "success =" + response);
                        }
                    }
                }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                if (Config.VOLLEY_DEBUG) {
                    Log.d("postJSON", "error = " + error.getMessage());
                }
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(VOLLEY_REQUEST_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);

        return request;
    }

}
