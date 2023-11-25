package com.example.joker.server.Classes;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joker on 12/27/2020.
 */

public class API_Service {
    private Context context;

    public API_Service(Context context) {
        this.context = context;
    }

    public void Get_Data_From_Server(final Received_Data received_data){
        final List<Post> posts=new ArrayList<>();
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, "http://192.168.1.106/SqlServer/post.php", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i <response.length() ; i++) {
                    try {
                        Post post=new Post();
                        JSONObject jsonObject=response.getJSONObject(i);
                        post.setTitle(jsonObject.getString("title"));
                        post.setId(jsonObject.getInt("id"));
                        post.setContent(jsonObject.getString("content"));
                        post.setTime(jsonObject.getString("date"));
                        post.setPost_img(jsonObject.getString("imgpost"));

                        posts.add(post);

                        received_data.Received(posts);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error Connecting to Server", Toast.LENGTH_SHORT).show();
            }
        });

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(jsonArrayRequest);

    }

    public interface Received_Data{
        void Received(List<Post> get_post);
    }
}
