package com.example.mybus3_4_2020.PresneceSystems;

import android.view.View;
import android.widget.TextView;

import com.example.mybus3_4_2020.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListOnlineViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
    public TextView txt_Email;

    ItemClickListner itemClickListner;

    public ListOnlineViewHolder(@NonNull View itemView) {
        super(itemView);
        txt_Email = itemView.findViewById(R.id.txt_email);

    }

    public void setItemClickListner(ItemClickListner itemClickListner) {
        this.itemClickListner = itemClickListner;
    }

    @Override
    public void onClick(View v) {
        itemClickListner.onClick(v , getAdapterPosition());

    }
}
