package com.example.one_hotel.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.one_hotel.models.PaymentModel;

public class PaymentPreferences 
{
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public static final String IS_PAYMENT = "isPayment";
    public static final String KEY_HOTEL = "hotel";
    public static final String KEY_NAMA = "nama";
    public static final String KEY_TANGGAL = "tanggal";
    public static final String KEY_DURASI = "durasi";
    public static final String KEY_JUMLAHKAMAR = "jumlahKamar";
    public static final String KEY_HARGA = "harga";
    public static final String KEY_TOTAL = "total";

    public PaymentPreferences(Context context)
    {
        this.context = context;

        preferences = context.getSharedPreferences("paymentPreferences",Context.MODE_PRIVATE);
        editor = preferences.edit();
    }
    
    public void setPayment(String hotel, String nama, String tanggal, String durasi, String jumlahKamar, String harga, String total)
    {
        editor.putBoolean(IS_PAYMENT,true);
        editor.putString(KEY_HOTEL,hotel);
        editor.putString(KEY_NAMA,nama);
        editor.putString(KEY_TANGGAL,tanggal);
        editor.putString(KEY_DURASI,String.valueOf(durasi));
        editor.putString(KEY_JUMLAHKAMAR,String.valueOf(jumlahKamar));
        editor.putString(KEY_HARGA,String.valueOf(harga));
        editor.putString(KEY_TOTAL,String.valueOf(total));
        editor.commit();
    }

    public PaymentModel getPayment()
    {
        String hotel;
        String nama;
        String tanggal;
        String durasi;
        String jumlahKamar;
        String harga;
        String total;

        hotel = preferences.getString(KEY_HOTEL,null);
        nama = preferences.getString(KEY_NAMA,null);
        tanggal = preferences.getString(KEY_TANGGAL,null);
        durasi = preferences.getString(KEY_DURASI,null);
        jumlahKamar = preferences.getString(KEY_JUMLAHKAMAR,null);
        harga = preferences.getString(KEY_HARGA,null);
        total = preferences.getString(KEY_TOTAL,null);

        return new PaymentModel(hotel,nama,tanggal,durasi,jumlahKamar,harga,total);
    }

    public boolean checkPayment()
    {
        return preferences.getBoolean(IS_PAYMENT,false);
    }

    public void deletePayment()
    {
        editor.clear();
        editor.commit();
    }
}
