package com.example.one_hotel.activities;

import static com.example.one_hotel.notification.MyApplication.CHANNEL_1_ID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.one_hotel.R;
import com.example.one_hotel.databinding.ActivityPaymentBinding;
import com.example.one_hotel.preferences.PaymentPreferences;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;

public class PaymentActivity extends AppCompatActivity
{
    private ActivityPaymentBinding binding;
    private TextInputEditText tietHotel;
    private TextInputEditText tietNama;
    private TextInputEditText tietTanggal;
    private TextInputEditText tietDurasi;
    private TextInputEditText tietJumlahKamar;
    private TextInputEditText tietHargaperHari;
    private TextInputEditText tietTotal;
    private Button btnKonfirmasi;
    private PaymentPreferences paymentPreferences;
    private Intent intent;
    private NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_payment);
        binding.setPaymentActivity(this);

        tietHotel = binding.tietHotel;
        tietNama = binding.tietNama;
        tietTanggal = binding.tietTanggal;
        tietDurasi = binding.tietDurasi;
        tietJumlahKamar = binding.tietJumlahKamar;
        tietHargaperHari = binding.tietHargaperHari;
        tietTotal = binding.tietTotal;
        btnKonfirmasi = binding.btnKonfirmasi;

        paymentPreferences = new PaymentPreferences(this);
        notificationManagerCompat = NotificationManagerCompat.from(this);

        String hotel = paymentPreferences.getPayment().getHotel();
        String nama = paymentPreferences.getPayment().getNama();
        String tanggal = paymentPreferences.getPayment().getTanggal();
        String durasi = paymentPreferences.getPayment().getDurasi();
        String jumlahKamar = paymentPreferences.getPayment().getJumlahKamar();
        String harga = paymentPreferences.getPayment().getHarga();
        String total = paymentPreferences.getPayment().getTotal();

        tietHotel.setText(hotel);
        tietNama.setText(nama);
        tietTanggal.setText(tanggal);
        tietDurasi.setText(durasi);
        tietJumlahKamar.setText(jumlahKamar);
        tietHargaperHari.setText("Rp. " + new DecimalFormat("############").format(Double.parseDouble(harga)));
        tietTotal.setText("Rp. " + new DecimalFormat("############").format(Double.parseDouble(total)));

        btnKonfirmasi.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                NotificationCompat.Builder notification = new NotificationCompat.Builder(view.getContext(),CHANNEL_1_ID);
                notification.setSmallIcon(R.drawable.ic_baseline_my_location_24)
                        .setContentTitle("Selamat !")
                        .setContentText("Anda Telah Memilih Hotel " + hotel + " Untuk Menginap")
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setColor(Color.RED);

                notificationManagerCompat.notify(1,notification.build());

                intent = new Intent(PaymentActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        intent = new Intent(PaymentActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}