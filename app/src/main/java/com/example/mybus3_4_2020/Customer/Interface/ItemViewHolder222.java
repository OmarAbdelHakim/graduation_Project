package com.example.mybus3_4_2020.Customer.Interface;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.mybus3_4_2020.R;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class ItemViewHolder222 extends RecyclerView.ViewHolder {

    public TextView txt_Sub, txt_first , txt_second , txt_third , txt_fourth;
    public RelativeLayout  button2;
    public ExpandableLinearLayout  F_expandableLinearLayout , S_expandableLinearLayout , T_expandableLinearLayout , F4_expandableLinearLayout;

    ItemClickListner itemClickListner2;

    public void setItemClickListner2(ItemClickListner itemClickListner21) {
        this.itemClickListner2 = itemClickListner21;
    }


    public ItemViewHolder222(@NonNull View itemView ,boolean  isExxx) {
        super(itemView);
        if( isExxx){

            txt_Sub = itemView.findViewById(R.id.txt_item_text_222);
            F_expandableLinearLayout = itemView.findViewById(R.id.new_First_expandableLayout);
            S_expandableLinearLayout = itemView.findViewById(R.id.new_Second_expandableLayout);
            T_expandableLinearLayout = itemView.findViewById(R.id.new_Third_expandableLayout);
            F4_expandableLinearLayout = itemView.findViewById(R.id.new_Fourth_expandableLayout);
            button2=itemView.findViewById(R.id.button2);
            txt_first = itemView.findViewById(R.id.txt_child_item_text_First);
            txt_second = itemView.findViewById(R.id.txt_child_item_text_Second);
            txt_third = itemView.findViewById(R.id.txt_child_item_text_Third);
            txt_fourth = itemView.findViewById(R.id.txt_child_item_text_Fourth);



        }
        else
        {
            txt_Sub = itemView.findViewById(R.id.txt_item_text_222);
        }

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListner2.onClick(v , getAdapterPosition());
            }
        });
    }
}
