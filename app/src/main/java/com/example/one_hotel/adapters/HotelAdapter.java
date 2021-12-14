package com.example.one_hotel.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.one_hotel.R;
import com.example.one_hotel.activities.HotelActivity;
import com.example.one_hotel.activities.MainActivity;
import com.example.one_hotel.activities.PaymentActivity;
import com.example.one_hotel.databinding.ItemHotelBinding;
import com.example.one_hotel.models.HotelModel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> implements Filterable
{
    private ItemHotelBinding binding;
    private HotelModel hotel;
    private List<HotelModel> listHotel;
    private Context context;
    private Intent intent;

    public HotelAdapter(List<HotelModel> listHotel, Context context)
    {
        this.listHotel = listHotel;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private FrameLayout flRoot;
        private ImageView ivFoto;
        private TextView tvNama;
        private ImageView ivBintang1;
        private ImageView ivBintang2;
        private ImageView ivBintang3;
        private ImageView ivBintang4;
        private ImageView ivBintang5;
        private TextView tvLokasi;
        private TextView tvHarga;

        public ViewHolder(@NonNull ItemHotelBinding binding)
        {
            super(binding.getRoot());

            flRoot = binding.flRoot;
            ivFoto = binding.ivFoto;
            tvNama = binding.tvNama;
            ivBintang1 = binding.ivBintang1;
            ivBintang2 = binding.ivBintang2;
            ivBintang3 = binding.ivBintang3;
            ivBintang4 = binding.ivBintang4;
            ivBintang5 = binding.ivBintang5;
            tvLokasi = binding.tvLokasi;
            tvHarga = binding.tvHarga;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(context);

        binding = DataBindingUtil.inflate(inflater, R.layout.item_hotel ,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelAdapter.ViewHolder holder, int position)
    {
        hotel = listHotel.get(position);

        holder.ivFoto.setImageResource(hotel.getFoto());
        holder.tvNama.setText(hotel.getNama());
        holder.tvLokasi.setText(hotel.getLokasi());
        holder.tvHarga.setText("Rp. " + new DecimalFormat("############").format(hotel.getHarga()));

        for(int a = 0 ; a < hotel.getJumlahBintang() ; a++)
        {
            if(a == 0)
            {
                holder.ivBintang1.setVisibility(View.VISIBLE);
            }
            else if(a == 1)
            {
                holder.ivBintang2.setVisibility(View.VISIBLE);
            }
            else if(a == 2)
            {
                holder.ivBintang3.setVisibility(View.VISIBLE);
            }
            else if(a == 3)
            {
                holder.ivBintang4.setVisibility(View.VISIBLE);
            }
            else
            {
                holder.ivBintang5.setVisibility(View.VISIBLE);
            }
        }

        holder.flRoot.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int position = holder.getAdapterPosition();
                hotel = listHotel.get(position);

                if(context instanceof HotelActivity)
                {
                    ((HotelActivity) context).setPayment(hotel.getNama(),hotel.getHarga());
                }
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return listHotel.size();
    }

    @Override
    public Filter getFilter()
    {
        return new Filter()
        {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence)
            {
                String charSequenceString = charSequence.toString();
                List<HotelModel> filtered = new ArrayList<>();

                if (charSequenceString.isEmpty())
                {
                    filtered.addAll(listHotel);
                }
                else
                {
                    for (HotelModel hotel : listHotel)
                    {
                        if (hotel.getNama().toLowerCase().contains(charSequenceString.toLowerCase()))
                        {
                            filtered.add(hotel);
                        }
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filtered;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults)
            {
                listHotel.clear();
                listHotel.addAll((List<HotelModel>) filterResults.values);

                notifyDataSetChanged();
            }
        };
    }
}