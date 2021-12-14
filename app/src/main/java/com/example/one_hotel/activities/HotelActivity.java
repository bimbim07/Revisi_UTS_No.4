package com.example.one_hotel.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.one_hotel.R;
import com.example.one_hotel.adapters.HotelAdapter;
import com.example.one_hotel.databinding.ActivityHotelBinding;
import com.example.one_hotel.models.HotelModel;
import com.example.one_hotel.models.dummies.HotelDummy;
import com.example.one_hotel.preferences.PaymentPreferences;
import com.example.one_hotel.preferences.ProfilePreferences;

import java.util.ArrayList;
import java.util.List;

public class HotelActivity extends AppCompatActivity
{
    private ActivityHotelBinding binding;

    private TextView tvCheckInOut;
    private Button btnGantiPencarian;
    private Button btnLokasi;
    private SearchView svHotel;
    private RecyclerView rvHotel;
    private Intent intent;
    private HotelAdapter adapter;
    private List<HotelModel> listHotel;
    private PaymentPreferences paymentPreferences;
    private ProfilePreferences profilePreferences;
    String checkIn;
    String checkOut;
    String durasi;
    String jumlahKamar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_hotel);
        binding.setHotelActivity(this);

        tvCheckInOut = binding.tvCheckInOut;
        btnGantiPencarian = binding.btnGantiPencarian;
        btnLokasi = binding.btnLokasi;
        svHotel = binding.svHotel;
        rvHotel = binding.rvHotel;

        profilePreferences = new ProfilePreferences(this);
        paymentPreferences = new PaymentPreferences(this);

        checkIn = getIntent().getStringExtra("CheckIn");
        checkOut = getIntent().getStringExtra("CheckOut");
        durasi = getIntent().getStringExtra("Durasi");
        jumlahKamar = getIntent().getStringExtra("JumlahKamar");



        tvCheckInOut.setText(checkIn + " - " + checkOut);

        listHotel = new HotelDummy().listHotel;
        adapter = new HotelAdapter(listHotel,this);

        rvHotel.setLayoutManager(new LinearLayoutManager(this));
        rvHotel.setAdapter(adapter);

        btnGantiPencarian.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                intent = new Intent(HotelActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnLokasi.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                intent = new Intent(HotelActivity.this, LocationActivity.class);
                startActivity(intent);
            }
        });

        svHotel.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String s)
            {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s)
            {
                if(s.length() == 0)
                {
                    listHotel = new HotelDummy().listHotel;
                    adapter = new HotelAdapter(listHotel,getApplicationContext());

                    rvHotel.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rvHotel.setAdapter(adapter);
                }
                else
                {
                    adapter.getFilter().filter(s);
                }

                return false;
            }
        });
    }

    public void setPayment(String hotel, double harga)
    {
        String nama = profilePreferences.getProfile().getFullname();

        double total = Double.parseDouble(durasi) * harga;

        paymentPreferences.setPayment(hotel,nama,checkIn + " - " + checkOut,durasi,jumlahKamar,String.valueOf(harga),String.valueOf(total));

        intent = new Intent(HotelActivity.this, PaymentActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        intent = new Intent(HotelActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}