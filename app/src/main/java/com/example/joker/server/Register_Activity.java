package com.example.joker.server;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register_Activity extends AppCompatActivity {

    EditText edtname,edtemail,edtpass,edtcpass;
    Button btnreg;
    ProgressBar progressload;
    private static String URL_Register="http://192.168.1.102/SqlServer/regi.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtname= (EditText) findViewById(R.id.edt_name);
        edtemail= (EditText) findViewById(R.id.edt_email);
        edtpass= (EditText) findViewById(R.id.edt_password);
        edtcpass= (EditText) findViewById(R.id.edt_confp);
        btnreg= (Button) findViewById(R.id.btn_reg);
        progressload=(ProgressBar)findViewById(R.id.prog_load);

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });

    }

    public void Register(){

        progressload.setVisibility(View.VISIBLE);
        btnreg.setVisibility(View.GONE);

        final String lay_name=this.edtname.getText().toString().trim();
        final String lay_email=this.edtemail.getText().toString().trim();
        final String lay_password=this.edtpass.getText().toString().trim();

        StringRequest stringRequest=new StringRequest(Request.Method.POST,URL_Register, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String suc=jsonObject.getString("successfully");
                    if (suc.equals("1")){
                        Toast.makeText(Register_Activity.this, "Register is Completed.", Toast.LENGTH_LONG).show();
                        progressload.setVisibility(View.GONE);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Register_Activity.this, "Error on App", Toast.LENGTH_LONG).show();
                    progressload.setVisibility(View.GONE);
                    btnreg.setVisibility(View.VISIBLE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Register_Activity.this, "Error Connecting to SQL"+error.toString(), Toast.LENGTH_LONG).show();
                progressload.setVisibility(View.GONE);
                btnreg.setVisibility(View.VISIBLE);
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("edt_name",lay_name);
                params.put("edt_email",lay_email);
                params.put("edt_password",lay_password);

                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
