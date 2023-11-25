package com.example.joker.server;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.joker.server.Classes.Singleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Image_Activity extends AppCompatActivity implements View.OnClickListener {

    private ImageView image;
    private EditText name;
    private Button btn_choose,btn_upload;

    private int IMG_REQUEST=1;
    private Bitmap bitmap;
    private static String UPLOADURL="http://192.168.1.102/SqlServer/upload.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        image= (ImageView) findViewById(R.id.image);
        name= (EditText) findViewById(R.id.name);
        btn_choose= (Button) findViewById(R.id.btn_choose);
        btn_upload= (Button) findViewById(R.id.btn_upload);

        btn_choose.setOnClickListener(this);
        btn_upload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.btn_choose):
                select_btn_choose();
                break;
            case (R.id.btn_upload):
                select_btn_upload();
                break;
        default:
        }

    }

    private void select_btn_upload() {
        StringRequest request=new StringRequest(Request.Method.POST, UPLOADURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object=new JSONObject(response);
                    String resp=object.getString("respond");
                    Toast.makeText(Image_Activity.this,resp, Toast.LENGTH_LONG).show();
                    image.setVisibility(View.GONE);
                    name.setVisibility(View.GONE);
                    name.setText("");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("user_name",name.getText().toString().trim());
                params.put("user_img",image_to_Bitmap(bitmap));
                return params;
            }
        };
        Singleton.getInstanceSingleton(Image_Activity.this).add_to_requestqueue(request);

    }

    private String image_to_Bitmap(Bitmap bmap) {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte [] img_byte=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(img_byte,Base64.DEFAULT);

    }

    private void select_btn_choose() {
        Intent i=new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i,IMG_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            Uri path=data.getData();
            try {

                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                image.setImageBitmap(bitmap);
                image.setVisibility(View.VISIBLE);
                name.setVisibility(View.VISIBLE);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
