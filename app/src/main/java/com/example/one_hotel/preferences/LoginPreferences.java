package com.example.one_hotel.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.one_hotel.models.LoginModel;

public class LoginPreferences
{
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public static final String IS_LOGIN = "isLogin";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";

    public LoginPreferences(Context context)
    {
        this.context = context;

        preferences = context.getSharedPreferences("loginPreferences",Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void setLogin(String username, String password)
    {
        editor.putBoolean(IS_LOGIN,true);
        editor.putString(KEY_USERNAME,username);
        editor.putString(KEY_PASSWORD,password);
        editor.commit();
    }

    public LoginModel getLogin()
    {
        String username;
        String password;

        username = preferences.getString(KEY_USERNAME,null);
        password = preferences.getString(KEY_PASSWORD,null);

        return new LoginModel(username,password);
    }

    public boolean checkLogin()
    {
        return preferences.getBoolean(IS_LOGIN,false);
    }

    public void deleteLogin()
    {
        editor.clear();
        editor.commit();
    }
}
