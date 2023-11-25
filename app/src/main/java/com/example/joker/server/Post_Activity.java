package com.example.joker.server;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Post_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Intent intent=getIntent();
        String title_post=intent.getStringExtra("post_title");
        String content_post=intent.getStringExtra("post_content");
        String date_post=intent.getStringExtra("post_time");

        ImageView post_img_view=(ImageView)findViewById(R.id.post_img);

        int id_post=intent.getIntExtra("post_id",0);

        switch (id_post){
            case 0:
                post_img_view.setImageResource(R.drawable.post_pic_1);
                break;
            case 1:
                post_img_view.setImageResource(R.drawable.post_pic_2);
                break;
            case 2:
                post_img_view.setImageResource(R.drawable.post_pic_3);
                break;
            case 3:
                post_img_view.setImageResource(R.drawable.post_pic_4);
                break;
            case 4:
                post_img_view.setImageResource(R.drawable.post_pic_5);
                break;
            case 5:
                post_img_view.setImageResource(R.drawable.post_pic_6);
                break;
        }

        TextView post_tit=(TextView)findViewById(R.id.txt_tit);
        post_tit.setText(title_post);

        TextView post_con=(TextView)findViewById(R.id.txt_con);
        post_con.setText(content_post);

        TextView post_time=(TextView)findViewById(R.id.txt_date);
        post_time.setText(date_post);
    }
}
