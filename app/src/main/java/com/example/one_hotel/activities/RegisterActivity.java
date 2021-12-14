package com.example.one_hotel.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.one_hotel.R;
import com.example.one_hotel.databinding.ActivityRegisterBinding;
import com.example.one_hotel.preferences.LoginPreferences;
import com.example.one_hotel.preferences.ProfilePreferences;
import com.example.one_hotel.preferences.RegisterPreferences;

public class RegisterActivity extends AppCompatActivity
{
    private ActivityRegisterBinding binding;
    private EditText etEmail;
    private EditText etFullname;
    private EditText etUsername;
    private EditText etPassword;
    private ImageButton ibtnVisibility;
    private Button btnRegister;
    private CheckBox cbPrivasi;
    private Button btnLogin;
    private boolean eye = false;
    private boolean privasi = false;
    private Intent intent;
    private RegisterPreferences registerPreferences;
    private ProfilePreferences profilePreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_register);
        binding.setRegisterActivity(this);

        registerPreferences = new RegisterPreferences(this);
        profilePreferences = new ProfilePreferences(this);

        etEmail = binding.etEmail;
        etFullname = binding.etFullname;
        etUsername = binding.etUsername;
        etPassword = binding.etPassword;
        ibtnVisibility = binding.ibtnVisibility;
        btnRegister = binding.btnRegister;
        cbPrivasi = binding.cbPrivasi;
        btnLogin = binding.btnLogin;

        checkRegister();

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

        btnRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String email = etEmail.getText().toString();
                String fullname = etFullname.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if(email.isEmpty() || fullname.isEmpty() || username.isEmpty() || password.isEmpty())
                {
                    if(email.isEmpty())
                    {
                        etEmail.setError("Email Kosong !");
                    }

                    if(fullname.isEmpty())
                    {
                        etFullname.setError("Fullname Kosong !");
                    }

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
                    if(privasi)
                    {
                        Toast.makeText(getApplicationContext(), "Register Berhasil !", Toast.LENGTH_SHORT).show();

                        registerPreferences.setRegister(email,fullname,username,password);
                        profilePreferences.setProfile(email,fullname,username,password);

                        intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Anda Belum Menyetujui Privasi !", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        cbPrivasi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if(compoundButton.isChecked())
                {
                    privasi = true;
                }
                else
                {
                    privasi = false;
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void checkRegister()
    {
        if(registerPreferences.checkRegister())
        {
            intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}