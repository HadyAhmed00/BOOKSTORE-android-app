package com.example.travel.ShowList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travel.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShowListAdapter extends RecyclerView.Adapter<ShowListAdapter.ShowListHolder> {
    ArrayList<ShowListModel> ShowListModel;
    private onlesner monlesner;
    public ShowListAdapter(ArrayList<ShowListModel> ShowListModel, onlesner onlesner) {
        this.ShowListModel = ShowListModel;
        monlesner=onlesner;
    }

    @NonNull
    @Override
    public ShowListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_list_card,parent,false);
        ShowListHolder ShowListHolder=new ShowListHolder(v,monlesner);
        return ShowListHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShowListHolder holder, int position) {
        ShowListModel d= ShowListModel.get(position);
        holder.t.setText(d.getName());



        holder.from_to.setText(d.getOuther_name());

        //holder.book_img.setImageResource(d.img);
        Picasso.get().load(d.getImg_url()).into(holder.book_img);
    }

    @Override
    public int getItemCount() {
        return ShowListModel.size();
    }

    class ShowListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView t;
        TextView from_to;
        ImageView book_img;
        onlesner onlesner;
        public ShowListHolder(@NonNull View itemView,onlesner onlesner) {
            super(itemView);
            t = itemView.findViewById(R.id.book_name);

            from_to=itemView.findViewById(R.id.outher_name);
            book_img= itemView.findViewById(R.id.book_img);
            this.onlesner=onlesner;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onlesner.onlesnerclik(getAdapterPosition());
        }

    }
    public interface onlesner{
        void onlesnerclik(int position);
    }
}
