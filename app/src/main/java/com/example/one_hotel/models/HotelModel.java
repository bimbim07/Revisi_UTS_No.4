package com.example.one_hotel.models;

public class HotelModel
{
    private int foto;
    private String nama;
    private int jumlahBintang;
    private String lokasi;
    private double harga;

    public HotelModel(int foto, String nama, int jumlahBintang, String lokasi, double harga)
    {
        this.foto = foto;
        this.nama = nama;
        this.jumlahBintang = jumlahBintang;
        this.lokasi = lokasi;
        this.harga = harga;
    }

    public int getFoto()
    {
        return foto;
    }

    public void setFoto(int foto)
    {
        this.foto = foto;
    }

    public String getNama()
    {
        return nama;
    }

    public void setNama(String nama)
    {
        this.nama = nama;
    }

    public int getJumlahBintang()
    {
        return jumlahBintang;
    }

    public void setJumlahBintang(int jumlahBintang)
    {
        this.jumlahBintang = jumlahBintang;
    }

    public String getLokasi()
    {
        return lokasi;
    }

    public void setLokasi(String lokasi)
    {
        this.lokasi = lokasi;
    }

    public double getHarga()
    {
        return harga;
    }

    public void setHarga(double harga)
    {
        this.harga = harga;
    }
}