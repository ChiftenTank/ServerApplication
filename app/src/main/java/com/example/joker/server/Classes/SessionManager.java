package com.example.joker.server.Classes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.joker.server.Home_Activity;
import com.example.joker.server.Login_Activity;

import java.util.HashMap;

/**
 * Created by Joker on 12/8/2020.
 */

public class SessionManager {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public Context context;
    int Private_Mode=0;
    public String UserName="FamilyName";
    public String Email="E-Mail";
    private String Pref_Share_Name="Login";
    private String Logging="is Login";
    public String ID;

    public SessionManager(Context context) {
        this.context = context;

        sharedPreferences=context.getSharedPreferences(Pref_Share_Name,Private_Mode);
        editor=sharedPreferences.edit();
    }

    public void create_session(String user_nf,String user_mail,String id){
        editor.putBoolean(Logging,true);
        editor.putString(UserName,user_nf);
        editor.putString(Email,user_mail);
        editor.putString(ID,id);
        editor.apply();
    }

    public boolean is_log(){
       return sharedPreferences.getBoolean(Logging,false);
    }

    public void login_checking(){
        if (!this.is_log()){
            Intent intent=new Intent(context, Login_Activity.class);
            context.startActivity(intent);
            ((Home_Activity)context).finish();
        }
    }

    public HashMap<String, String> get_user_info(){
        HashMap<String,String> user=new HashMap<>();
        user.put(UserName,sharedPreferences.getString(UserName,null));
        user.put(Email,sharedPreferences.getString(Email,null));
        user.put(ID,sharedPreferences.getString(ID,null));
        return user;
    }

    public void logout(){
        editor.clear();
        editor.commit();
        Intent i=new Intent(context, Login_Activity.class);
        context.startActivity(i);
        ((Home_Activity)context).finish();
    }
}
