package com.example.one_hotel.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.one_hotel.R;
import com.example.one_hotel.databinding.ActivityMainBinding;
import com.example.one_hotel.preferences.LoginPreferences;
import com.example.one_hotel.preferences.PaymentPreferences;
import com.example.one_hotel.preferences.ProfilePreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
{
    private static final String[] LIST_KOTATUJUAN = new String[]
    {
        "Jakarta",
        "Surabaya",
        "Medan",
        "Bandung",
        "Makassar",
        "Semarang",
        "Palembang",
        "Batam",
        "Pekanbaru",
        "Malang"
    };

    private static final String[] LIST_JUMLAHKAMAR = new String[]
    {
        "1 Kamar",
        "2 Kamar",
        "3 Kamar",
        "4 Kamar",
        "5 Kamar",
        "6 Kamar",
        "7 Kamar",
        "8 Kamar",
        "9 Kamar",
        "10 Kamar"
    };

    private ActivityMainBinding binding;
    private AutoCompleteTextView actvKotaTujuan;
    private AutoCompleteTextView actvCheckIn;
    private AutoCompleteTextView actvCheckOut;
    private AutoCompleteTextView actvDurasi;
    private AutoCompleteTextView actvJumlahKamar;
    private Button btnCariHotel;
    private Button btnLogout;
    private Button btnProfile;
    private Intent intent;
    private LoginPreferences loginPreferences;
    private ProfilePreferences profilePreferences;
    private PaymentPreferences paymentPreferences;
    private ArrayAdapter<String> adapterKotaTujuan;
    private ArrayAdapter<String> adapterJumlahKamar;
    private Calendar calendar;
    private SimpleDateFormat simpleDateFormat;
    private Date dateCheckIn;
    private Date dateCheckOut;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setMainActivity(this);

        loginPreferences = new LoginPreferences(this);
        profilePreferences = new ProfilePreferences(this);
        paymentPreferences = new PaymentPreferences(this);

        actvKotaTujuan = binding.actvKotaTujuan;
        actvCheckIn = binding.actvCheckIn;
        actvCheckOut = binding.actvCheckOut;
        actvDurasi = binding.actvDurasi;
        actvJumlahKamar = binding.actvJumlahKamar;
        btnCariHotel = binding.btnCariHotel;
        btnLogout = binding.btnLogout;
        btnProfile = binding.btnProfile;

        checkLogin();
        checkProfile();

        adapterKotaTujuan = new ArrayAdapter<>(this, R.layout.item_list, LIST_KOTATUJUAN);
        actvKotaTujuan.setAdapter(adapterKotaTujuan);

        adapterJumlahKamar = new ArrayAdapter<>(this, R.layout.item_list, LIST_JUMLAHKAMAR);
        actvJumlahKamar.setAdapter(adapterJumlahKamar);

        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        actvCheckIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Calendar newCalendar = Calendar.getInstance();

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, (datePicker, year, month, day) ->
                {
                    calendar = Calendar.getInstance();
                    calendar.set(year,month,day);
                    actvCheckIn.setText(simpleDateFormat.format(calendar.getTime()));
                    dateCheckIn = calendar.getTime();
                    setDurasi(1);
                }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            }
        });

        actvCheckOut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Calendar newCalendar = Calendar.getInstance();

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, (datePicker, year, month, day) ->
                {
                    calendar = Calendar.getInstance();
                    calendar.set(year,month,day);
                    actvCheckOut.setText(simpleDateFormat.format(calendar.getTime()));
                    dateCheckOut = calendar.getTime();
                    setDurasi(0);
                }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            }
        });

        btnCariHotel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String kotaTujuan = actvKotaTujuan.getText().toString();
                String checkIn = actvCheckIn.getText().toString();
                String checkOut = actvCheckOut.getText().toString();
                String durasi = actvDurasi.getText().toString();
                String jumlahKamar = actvJumlahKamar.getText().toString();

                if(kotaTujuan.isEmpty() || checkIn.isEmpty() || checkOut.isEmpty() || durasi.isEmpty() || jumlahKamar.isEmpty())
                {
                    if(kotaTujuan.isEmpty())
                    {
                        actvKotaTujuan.setError("Kota Tujuan Belum Dipilih !");
                    }
                    else
                    {
                        actvKotaTujuan.setError(null);
                    }

                    if(checkIn.isEmpty())
                    {
                        actvCheckIn.setError("Tanggal Check In Belum Dipilih !");
                    }
                    else
                    {
                        actvCheckIn.setError(null);
                    }

                    if(checkOut.isEmpty())
                    {
                        actvCheckOut.setError("Tanggal Check Out Belum Dipilih !");
                    }
                    else
                    {
                        actvCheckOut.setError(null);
                    }

                    if(durasi.isEmpty())
                    {
                        actvDurasi.setError("Tanggal Check In dan Check Out Belum Dipilih !");
                    }
                    else
                    {
                        actvDurasi.setError(null);
                    }

                    if(jumlahKamar.isEmpty())
                    {
                        actvJumlahKamar.setError("Jumlah Kamar Belum Dipilih !");
                    }
                    else
                    {
                        actvJumlahKamar.setError(null);
                    }
                }
                else
                {
//                    paymentPreferences.setPayment();

                    intent = new Intent(MainActivity.this, HotelActivity.class);
                    intent.putExtra("CheckIn",actvCheckIn.getText().toString());
                    intent.putExtra("CheckOut",actvCheckOut.getText().toString());
                    intent.putExtra("Durasi",actvDurasi.getText().toString());
                    intent.putExtra("JumlahKamar",actvJumlahKamar.getText().toString());
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                loginPreferences.deleteLogin();
                intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    private void checkLogin()
    {
        if(!loginPreferences.checkLogin())
        {
            intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void checkProfile()
    {
        if(!profilePreferences.checkProfile())
        {
            intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void setDurasi(int check)
    {
        if(dateCheckIn != null && dateCheckOut != null)
        {
            long diff = dateCheckOut.getTime() - dateCheckIn.getTime();
            long seconds = diff / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;
            long days = hours / 24;

            actvDurasi.setText(String.valueOf(days + check));
        }
    }
}