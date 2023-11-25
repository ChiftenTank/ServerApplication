package com.example.joker.server;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.joker.server.Classes.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login_Activity extends AppCompatActivity {

    EditText edt_email,edt_pass;
    TextView txt_link;
    ProgressBar progressBar;
    Button btn_log;
    private static String URL_LOGIN="http://192.168.1.102/SqlServer/login.php";
    SessionManager session_manager;
    public String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session_manager=new SessionManager(this);

        edt_email=(EditText)findViewById(R.id.edt_mail);
        edt_pass=(EditText)findViewById(R.id.edt_pass);
        txt_link=(TextView) findViewById(R.id.txt_link);
        progressBar=(ProgressBar) findViewById(R.id.progress_load);
        btn_log=(Button)findViewById(R.id.btn_log);

        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String xml_email=edt_email.getText().toString().trim();
                String xml_password=edt_pass.getText().toString().trim();


                if (xml_email.isEmpty() | xml_password.isEmpty()){
                    edt_email.setError("Please Enter Your E-Mail");
                    edt_pass.setError("Please Enter Your Password");
                }else{
                    Login(xml_email,xml_password);
                }
            }
        });
        txt_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_Activity.this,Register_Activity.class));
            }
        });
    }

    public void Login(final String email, final String password) {
        progressBar.setVisibility(View.VISIBLE);
        btn_log.setVisibility(View.GONE);


        StringRequest stringRequest=new StringRequest(Request.Method.POST,URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String suc=jsonObject.getString("Success");
                    JSONArray jsonArray=jsonObject.getJSONArray("Login");

                    if (suc.equals("1")){
                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject object=jsonArray.getJSONObject(i);
                            String s_name=object.getString("name").trim();
                            String s_email=object.getString("email").trim();

                            session_manager.create_session(s_name,s_email,id);

                            Intent intent=new Intent(Login_Activity.this,Home_Activity.class);
                            intent.putExtra("user_name",s_name);
                            intent.putExtra("user_email",s_email);
                            startActivity(intent);
                            progressBar.setVisibility(View.GONE);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    progressBar.setVisibility(View.GONE);
                    btn_log.setVisibility(View.VISIBLE);
                    Toast.makeText(Login_Activity.this, "Error Connecting to SQL"+e.toString(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                btn_log.setVisibility(View.VISIBLE);
                Toast.makeText(Login_Activity.this, "Error Connecting to Server"+error.toString(), Toast.LENGTH_LONG).show();
            }
        }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> user=new HashMap<>();
                user.put("edt_mail",email);
                user.put("edt_pass",password);
                return user;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}
