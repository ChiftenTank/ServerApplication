package com.example.joker.server;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.joker.server.Classes.API_Service;
import com.example.joker.server.Classes.Post;

import java.util.List;

public class Post_RecyclerView_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post__recycler_view);

        API_Service api_service=new API_Service(this);
        api_service.Get_Data_From_Server(new API_Service.Received_Data() {
            @Override
            public void Received(List<Post> get_post) {
                RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(Post_RecyclerView_Activity.this,LinearLayoutManager.VERTICAL,false));
                Post_Adapter post_adapter=new Post_Adapter(Post_RecyclerView_Activity.this,get_post);
                recyclerView.setAdapter(post_adapter);
            }
        });

    }
}
