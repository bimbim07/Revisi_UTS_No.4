package com.example.one_hotel.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.one_hotel.models.ProfileModel;

public class ProfilePreferences 
{
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public static final String IS_PROFILE = "isProfile";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_FULLNAME = "fullname";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";

    public ProfilePreferences(Context context)
    {
        this.context = context;

        preferences = context.getSharedPreferences("profilePreferences",Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void setProfile(String email, String fullname, String username, String password)
    {
        editor.putBoolean(IS_PROFILE,true);
        editor.putString(KEY_EMAIL,email);
        editor.putString(KEY_FULLNAME,fullname);
        editor.putString(KEY_USERNAME,username);
        editor.putString(KEY_PASSWORD,password);
        editor.commit();
    }

    public ProfileModel getProfile()
    {
        String email;
        String fullname;
        String username;
        String password;

        email = preferences.getString(KEY_EMAIL,null);
        fullname = preferences.getString(KEY_FULLNAME,null);
        username = preferences.getString(KEY_USERNAME,null);
        password = preferences.getString(KEY_PASSWORD,null);

        return new ProfileModel(email,fullname,username,password);
    }

    public boolean checkProfile()
    {
        return preferences.getBoolean(IS_PROFILE,false);
    }

    public void deleteProfile()
    {
        editor.clear();
        editor.commit();
    }
}
