package com.example.joker.server.Classes;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Joker on 12/29/2020.
 */

public class Gallery_Adapter extends BaseAdapter {

    private Context context;
    private int[] imgs;

    public Gallery_Adapter(Context context, int[] imgs) {
        this.context = context;
        this.imgs = imgs;
    }

    @Override
    public int getCount() {
        return imgs.length ;
    }

    @Override
    public Object getItem(int position) {
        return imgs[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout linearLayout=new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        Gallery.LayoutParams layoutParams=new Gallery.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(layoutParams);

        ImageView imgv=new ImageView(context);
        imgv.setLayoutParams(layoutParams);
        imgv.setImageResource(imgs[position]);
        linearLayout.addView(imgv);

        return linearLayout;
    }
}
