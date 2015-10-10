package com.cloudtv.hahong.cycleview.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.cloudtv.hahong.cycleview.R;
import com.android.volley.toolbox.ImageLoader;
import com.cloudtv.hahong.cycleview.volley.VolleyUtils;

/**
 * ImageView创建工厂
 */
public class ViewFactory {

	/**
	 * 获取ImageView视图的同时加载显示url
	 * 
	 * @param text
	 * @return
	 */
	public static ImageView getImageView(Context context, String url) {
		ImageView imageView = (ImageView)LayoutInflater.from(context).inflate(
				R.layout.view_banner, null);
        VolleyUtils.loadImage(url, imageView);
		return imageView;
	}
}
