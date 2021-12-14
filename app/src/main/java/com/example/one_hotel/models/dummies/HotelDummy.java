package com.example.one_hotel.models.dummies;

import com.example.one_hotel.R;
import com.example.one_hotel.models.HotelModel;

import java.util.ArrayList;

public class HotelDummy
{
    private static int foto1 = R.drawable.foto1;
    private static String nama1 = "Alila Purnama";
    private static int jumlahBintang1 = 4;
    private static String lokasi1 = "Pulau Komodo";
    private static double harga1 = 37000000;

    private static int foto2 = R.drawable.foto2;
    private static String nama2 = "Four Season Resort Sayan";
    private static int jumlahBintang2 = 5;
    private static String lokasi2 = "Bali";
    private static double harga2 = 11650000;

    private static int foto3 = R.drawable.foto3;
    private static String nama3 = "Mandapa A Ritz Carlton Reserve";
    private static int jumlahBintang3 = 4;
    private static String lokasi3 = "Bali";
    private static double harga3 = 9000000;

    private static int foto4 = R.drawable.foto4;
    private static String nama4 = "Amanjiwo Resort Borobudur";
    private static int jumlahBintang4 = 3;
    private static String lokasi4 = "Amanjiwo";
    private static double harga4 = 9000000;

    private static int foto5 = R.drawable.foto5;
    private static String nama5 = "DoubleTree by Hilton";
    private static int jumlahBintang5 = 5;
    private static String lokasi5 = "Jakarta";
    private static double harga5 = 1184500;

    private static int foto6 = R.drawable.foto6;
    private static String nama6 = "The Oberoi";
    private static int jumlahBintang6 = 4;
    private static String lokasi6 = "Lombok";
    private static double harga6 = 12000000;

    private static int foto7 = R.drawable.foto7;
    private static String nama7 = "Intercontinental";
    private static int jumlahBintang7 = 2;
    private static String lokasi7 = "Bali";
    private static double harga7 = 500000;

    private static int foto8 = R.drawable.foto8;
    private static String nama8 = "Bawah Reserve";
    private static int jumlahBintang8 = 4;
    private static String lokasi8 = "Pulau Bawah";
    private static double harga8 = 4000000;

    private static int foto9 = R.drawable.foto9;
    private static String nama9 = "Nihi Sumba";
    private static int jumlahBintang9 = 5;
    private static String lokasi9 = "Pulau Sumba";
    private static double harga9 = 100000000;

    private static int foto10 = R.drawable.hotel;
    private static String nama10 = "Bimaz";
    private static int jumlahBintang10 = 1;
    private static String lokasi10 = "Pulau Bimaz";
    private static double harga10 = 123123123;

    public ArrayList<HotelModel> listHotel;
    public HotelDummy()
    {
        listHotel = new ArrayList<>();

        listHotel.add(hotel1);
        listHotel.add(hotel2);
        listHotel.add(hotel3);
        listHotel.add(hotel4);
        listHotel.add(hotel5);
        listHotel.add(hotel6);
        listHotel.add(hotel7);
        listHotel.add(hotel8);
        listHotel.add(hotel9);
        listHotel.add(hotel10);
    }

    public static final HotelModel hotel1 = new HotelModel(foto1, nama1, jumlahBintang1, lokasi1, harga1);
    public static final HotelModel hotel2 = new HotelModel(foto2, nama2, jumlahBintang2, lokasi2, harga2);
    public static final HotelModel hotel3 = new HotelModel(foto3, nama3, jumlahBintang3, lokasi3, harga3);
    public static final HotelModel hotel4 = new HotelModel(foto4, nama4, jumlahBintang4, lokasi4, harga4);
    public static final HotelModel hotel5 = new HotelModel(foto5, nama5, jumlahBintang5, lokasi5, harga5);
    public static final HotelModel hotel6 = new HotelModel(foto6, nama6, jumlahBintang6, lokasi6, harga6);
    public static final HotelModel hotel7 = new HotelModel(foto7, nama7, jumlahBintang7, lokasi7, harga7);
    public static final HotelModel hotel8 = new HotelModel(foto8, nama8, jumlahBintang8, lokasi8, harga8);
    public static final HotelModel hotel9 = new HotelModel(foto9, nama9, jumlahBintang9, lokasi9, harga9);
    public static final HotelModel hotel10 = new HotelModel(foto10, nama10, jumlahBintang10, lokasi10, harga10);
}
