package com.example.joker.server;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.example.joker.server.Classes.Gallery_Adapter;

public class Gallery_Activity extends AppCompatActivity implements ViewSwitcher.ViewFactory{

    ImageSwitcher switcher;
    Gallery gallery;
    int[] imgs={R.drawable.gallery_pic1,R.drawable.gallery_pic2,R.drawable.gallery_pic3,R.drawable.gallery_pic4,
            R.drawable.gallery_pic5, R.drawable.gallery_pic6,R.drawable.gallery_pic7,R.drawable.gallery_pic8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        switcher= (ImageSwitcher) findViewById(R.id.imgSwitcher);
        gallery= (Gallery) findViewById(R.id.gallery);

        gallery.setAdapter(new Gallery_Adapter(this,imgs));
        switcher.setFactory(this);

        gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switcher.setImageResource(imgs[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public View makeView() {
        ImageView imageView=new ImageView(this);
        imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        return imageView;
    }
}
