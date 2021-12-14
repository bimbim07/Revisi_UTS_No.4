package com.example.one_hotel.models;

import androidx.dynamicanimation.animation.SpringAnimation;

public class PaymentModel
{
    private String hotel;
    private String nama;
    private String tanggal;
    private String durasi;
    private String jumlahKamar;
    private String harga;
    private String total;

    public PaymentModel(String hotel, String nama, String tanggal, String durasi, String jumlahKamar, String harga, String total)
    {
        this.hotel = hotel;
        this.nama = nama;
        this.tanggal = tanggal;
        this.durasi = durasi;
        this.jumlahKamar = jumlahKamar;
        this.harga = harga;
        this.total = total;
    }

    public String getHotel()
    {
        return hotel;
    }

    public void setHotel(String hotel)
    {
        this.hotel = hotel;
    }

    public String getNama()
    {
        return nama;
    }

    public void setNama(String nama)
    {
        this.nama = nama;
    }

    public String getTanggal()
    {
        return tanggal;
    }

    public void setTanggal(String tanggal)
    {
        this.tanggal = tanggal;
    }

    public String getDurasi()
    {
        return durasi;
    }

    public void setDurasi(String durasi)
    {
        this.durasi = durasi;
    }

    public String getJumlahKamar()
    {
        return jumlahKamar;
    }

    public void setJumlahKamar(String jumlahKamar)
    {
        this.jumlahKamar = jumlahKamar;
    }

    public String getHarga()
    {
        return harga;
    }

    public void setHarga(String harga)
    {
        this.harga = harga;
    }

    public String getTotal()
    {
        return total;
    }

    public void setTotal(String total)
    {
        this.total = total;
    }
}