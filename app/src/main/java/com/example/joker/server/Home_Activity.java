package com.example.joker.server;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
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

public class Home_Activity extends AppCompatActivity {

    EditText txt_fn,txt_em;
    Button btn_logout,btn_upload;
    private static String URL_Detail="http://192.168.1.102/SqlServer/user_detail.php";
    private static String URL_Edit="http://192.168.1.102/SqlServer/user_edit.php";
    SessionManager sessionManager;
    public String id;
    private Menu action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sessionManager=new SessionManager(this);
        sessionManager.login_checking();

        txt_fn=(EditText) findViewById(R.id.edt_fn) ;
        txt_em=(EditText) findViewById(R.id.edt_em) ;
        btn_logout=(Button) findViewById(R.id.btn_logout) ;
        btn_upload=(Button) findViewById(R.id.btn_upl) ;

//        Intent i=getIntent();
//        String get_un=i.getStringExtra("user_name");
//        String get_ue=i.getStringExtra("user_email");
//        id=i.getStringExtra("id");
////
//        txt_fn.setText(get_un);
//        txt_em.setText(get_ue);

        HashMap<String,String> user_i=new HashMap<>(sessionManager.get_user_info());
        id=user_i.get(sessionManager.ID);
//        String user_nm=user_i.get(sessionManager.UserName);
//        String user_mail=user_i.get(sessionManager.Email);
//
//        txt_fn.setText(user_nm);
//        txt_em.setText(user_mail);

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home_Activity.this,Image_Activity.class);
                startActivity(intent);
            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });

        txt_fn.setFocusableInTouchMode(false);
        txt_em.setFocusableInTouchMode(false);
        txt_fn.setFocusable(false);
        txt_em.setFocusable(false);
    }

    private void get_user_detail(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST,URL_Detail, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String suc_pm=jsonObject.getString("Success");
                    JSONArray jsonArray=jsonObject.getJSONArray("read");
                    if (suc_pm.equals("1")){

                        for (int i=0; i<jsonArray.length();i++){
                            JSONObject object=jsonArray.getJSONObject(i);
                            String servername=object.getString("username").trim();
                            String serveremail=object.getString("useremail").trim();

                            txt_fn.setText(servername);
                            txt_em.setText(serveremail);

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Home_Activity.this, "Error1", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Home_Activity.this, "Error Connecting to server"+error.toString(), Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String,String> params=new HashMap();
                params.put("user_id", id);
                return (params);
                }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    protected void onResume() {
        super.onResume();
        get_user_detail();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_action,menu);
        action=menu;
        action.findItem(R.id.menu_save).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       switch (item.getItemId()){
           case R.id.menu_edt:
               txt_fn.setFocusableInTouchMode(true);
               txt_em.setFocusableInTouchMode(true);

               InputMethodManager imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
               imm.showSoftInput(txt_fn,InputMethodManager.SHOW_IMPLICIT);
               action.findItem(R.id.menu_edt).setVisible(false);
               action.findItem(R.id.menu_save).setVisible(true);
               return true;
           case R.id.menu_save:

               SaveEditDetail();

               action.findItem(R.id.menu_edt).setVisible(true);
               action.findItem(R.id.menu_save).setVisible(false);
               txt_fn.setFocusableInTouchMode(false);
               txt_em.setFocusableInTouchMode(false);
               txt_fn.setFocusable(false);
               txt_em.setFocusable(false);
               return true;
               default:
               return super.onOptionsItemSelected(item);}
    }

    private void SaveEditDetail(){
        final String user_name=this.txt_fn.getText().toString().trim();
        final String user_mail=this.txt_em.getText().toString().trim();
        final String uid=id;

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_Edit, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String suc_pm=jsonObject.getString("Success");
                    if (suc_pm.equals("1"))
                    {
                        Toast.makeText(Home_Activity.this, "Successfully Done", Toast.LENGTH_LONG).show();
                        sessionManager.create_session(user_name,user_mail,uid);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Home_Activity.this, "Error Connecting to SQL", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Home_Activity.this, "Error2", Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map=new HashMap<>();
                map.put("edt_fn",user_name);
                map.put("edt_em",user_mail);
                map.put("user_id",uid);
                return map;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
