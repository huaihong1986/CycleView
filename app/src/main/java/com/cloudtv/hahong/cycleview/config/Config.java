package com.cloudtv.hahong.cycleview.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Config {

	public static String sWebHost;

    public static boolean VOLLEY_DEBUG = true;

    public static final String API_CATALOG_PRODUCT_LIST_REQUEST = "/getCatalogProductList";
    public static final String API_MAIN_LIST_REQUEST = "/getMainList";

    public static final String KEY_SHARED_PREFERENCES = "APP_CONTROL_KEY";
	public static final String KEY_USER_INFO = "userInfo";
    public static final String KEY_CATALOG_PRODUCT_LIST_INFO = "catalog_product_list_info";
    public static final String KEY_MAIN_LIST_INFO = "main_list_info";
	public static final String KEY_SCAN_DEVICE_ID = "scanDeviceId";

	private static int sScanDeviceId = -1;
	
	public static void saveUserInfo(Context context, String json) {
		SharedPreferences preferences = context.getSharedPreferences(KEY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
		Editor et = preferences.edit();
		et.putString(KEY_USER_INFO, json);
		et.apply();
	}

    public static String getUserInfo(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(KEY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        return preferences.getString(KEY_USER_INFO, "");
    }

    public static void saveCatalogProductListInfo(Context context, String json) {
        SharedPreferences preferences = context.getSharedPreferences(KEY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        Editor et = preferences.edit();
        et.putString(KEY_CATALOG_PRODUCT_LIST_INFO, json);
        et.apply();
    }

    public static String getCatalogProductListInfo(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(KEY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        return preferences.getString(KEY_CATALOG_PRODUCT_LIST_INFO, "");
    }

    public static void saveMainListInfo(Context context, String json) {
        SharedPreferences preferences = context.getSharedPreferences(KEY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        Editor et = preferences.edit();
        et.putString(KEY_MAIN_LIST_INFO, json);
        et.apply();
    }

    public static String getMainListInfo(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(KEY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        return preferences.getString(KEY_MAIN_LIST_INFO, "");
    }

	public static void saveScanSettings(Context context, int id) {
		SharedPreferences preferences = context.getSharedPreferences(KEY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
		Editor et = preferences.edit();
		et.putInt(KEY_SCAN_DEVICE_ID, id);
		sScanDeviceId = id;
		et.apply();
	}
	
	public static int getScanSettings(Context context) {
        if (sScanDeviceId == -1) {
            SharedPreferences preferences = context.getSharedPreferences(KEY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
            sScanDeviceId = preferences.getInt(KEY_SCAN_DEVICE_ID, 0);
        }

        return sScanDeviceId;
	}

    public static void clearSharedPreferences(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(KEY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        Editor et = preferences.edit();
        et.clear();
        et.apply();
    }

}
