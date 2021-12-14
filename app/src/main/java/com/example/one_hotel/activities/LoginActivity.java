package com.example.one_hotel.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.one_hotel.R;
import com.example.one_hotel.databinding.ActivityLoginBinding;
import com.example.one_hotel.preferences.LoginPreferences;
import com.example.one_hotel.preferences.ProfilePreferences;
import com.example.one_hotel.preferences.RegisterPreferences;

import java.security.PrivateKey;

public class LoginActivity extends AppCompatActivity
{
    private ActivityLoginBinding binding;
    private EditText etUsername;
    private EditText etPassword;
    private ImageButton ibtnVisibility;
    private Button btnLogin;
    private Button btnRegister;
    private boolean eye = false;
    private Intent intent;
    private RegisterPreferences registerPreferences;
    private LoginPreferences loginPreferences;
    private ProfilePreferences profilePreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        binding.setLoginActivity(this);

        registerPreferences = new RegisterPreferences(this);
        loginPreferences = new LoginPreferences(this);
        profilePreferences = new ProfilePreferences(this);

        etUsername = binding.etUsername;
        etPassword = binding.etPassword;
        ibtnVisibility = binding.ibtnVisibility;
        btnLogin = binding.btnLogin;
        btnRegister = binding.btnRegister;

        checkLogin();

        ibtnVisibility.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(eye)
                {
                    etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    ibtnVisibility.setBackgroundResource(R.drawable.ic_baseline_visibility_off_24);
                    eye = false;
                }
                else
                {
                    etPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                    ibtnVisibility.setBackgroundResource(R.drawable.ic_baseline_visibility_24);
                    eye = true;
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if(username.isEmpty() || password.isEmpty())
                {
                    if(username.isEmpty())
                    {
                        etUsername.setError("Username Kosong !");
                    }

                    if(password.isEmpty())
                    {
                        etPassword.setError("Password Kosong !");
                    }
                }
                else
                {
                    String tempUsername = profilePreferences.getProfile().getUsername();
                    String tempPassword = profilePreferences.getProfile().getPassword();

                    if(tempUsername.equals(username) && tempPassword.equals(password))
                    {
                        Toast.makeText(getApplicationContext(), "Login Berhasil !", Toast.LENGTH_SHORT).show();

                        loginPreferences.setLogin(username,password);

                        intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Username Atau Password Salah !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                registerPreferences.deleteRegister();

                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void checkLogin()
    {
        if(loginPreferences.checkLogin())
        {
            intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}