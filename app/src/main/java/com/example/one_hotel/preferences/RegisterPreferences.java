package com.example.one_hotel.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.one_hotel.models.RegisterModel;

public class RegisterPreferences 
{
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public static final String IS_REGISTER = "isRegister";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_FULLNAME = "fullname";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";

    public RegisterPreferences(Context context)
    {
        this.context = context;

        preferences = context.getSharedPreferences("registerPreferences",Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void setRegister(String email, String fullname, String username, String password)
    {
        editor.putBoolean(IS_REGISTER,true);
        editor.putString(KEY_EMAIL,email);
        editor.putString(KEY_FULLNAME,fullname);
        editor.putString(KEY_USERNAME,username);
        editor.putString(KEY_PASSWORD,password);
        editor.commit();
    }

    public RegisterModel getRegister()
    {
        String email;
        String fullname;
        String username;
        String password;

        email = preferences.getString(KEY_EMAIL,null);
        fullname = preferences.getString(KEY_FULLNAME,null);
        username = preferences.getString(KEY_USERNAME,null);
        password = preferences.getString(KEY_PASSWORD,null);

        return new RegisterModel(email,fullname,username,password);
    }

    public boolean checkRegister()
    {
        return preferences.getBoolean(IS_REGISTER,false);
    }

    public void deleteRegister()
    {
        editor.clear();
        editor.commit();
    }
}