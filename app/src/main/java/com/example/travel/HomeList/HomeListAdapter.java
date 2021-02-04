package com.example.travel.HomeList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travel.R;

import java.util.ArrayList;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.homelistHolder> {
    ArrayList<HomeListModel> HomeListModel;
    onhomelisner            monhomelisner;

    public HomeListAdapter(ArrayList<HomeListModel> HomeListModel, onhomelisner monhomelisner) {
        this.HomeListModel = HomeListModel;
        this.monhomelisner=monhomelisner;
    }

    @NonNull
    @Override
    public homelistHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.home_list_card,parent,false);
        homelistHolder homelistHolder=new homelistHolder(view,monhomelisner);

        return homelistHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull homelistHolder holder, int position) {
        HomeListModel d= HomeListModel.get(position);
        holder.catname.setText(d.getCatogry());
        holder.catimg.setImageResource(d.getIcon());
    }

    @Override
    public int getItemCount() {
        return HomeListModel.size();
    }

    class homelistHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView catname;
        ImageView catimg;
        onhomelisner onhomelisner;
        public homelistHolder(@NonNull View itemView,onhomelisner onhomelisner) {
            super(itemView);
            catname=itemView.findViewById(R.id.book_name);
            catimg=itemView.findViewById(R.id.book_img);
            this.onhomelisner=onhomelisner;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onhomelisner.onhomeclik(getAdapterPosition());

        }
    }
    public interface onhomelisner
    {
        void onhomeclik(int pos);
    }
}
