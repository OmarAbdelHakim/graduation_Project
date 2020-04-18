package com.example.mybus3_4_2020.Customer.Interface;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.mybus3_4_2020.R;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder {

public TextView txt_child , txt_item , txt_anotherchild ;
public RelativeLayout button ;
public ExpandableLinearLayout expandableLinearLayout , linearLayout ;
    ItemClickListner itemClickListner;

public void setItemClickListner(ItemClickListner itemClickListner) {
    this.itemClickListner = itemClickListner;
}


public ItemViewHolder(@NonNull View itemView , boolean isExpandable) {
    super(itemView);


    if(isExpandable )
    {
        txt_item = itemView.findViewById(R.id.txt_item_text);
        txt_child = itemView.findViewById(R.id.txt_child_item_text);
        txt_anotherchild = itemView.findViewById(R.id.txt_child_item_text_second);
        button = itemView.findViewById(R.id.button);

        expandableLinearLayout = itemView.findViewById(R.id.expandableLayout);
        linearLayout = itemView.findViewById(R.id.SecondexpandableLayout);


        // gded









    }
    else
    {
        txt_item = itemView.findViewById(R.id.txt_item_text);

    }


    itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            itemClickListner.onClick(v , getAdapterPosition());
        }
    });



}


}
