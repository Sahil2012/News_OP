package com.example.newsop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CatAdap extends RecyclerView.Adapter<CatAdap.ViewHold>{

    Context context;
    ClickCat li;
    ArrayList<CAT> arr;

    public CatAdap(ArrayList<CAT> arr,Context context,ClickCat li){
        this.arr = arr;
        this.context = context;
        this.li = li;
    }

    @NonNull
    @Override
    public ViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_item,parent,false);
        return new ViewHold(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHold holder, int position) {
//        holder.ig.setImageDrawable(arr.get(position).getDr());
        holder.ig.setImageResource(arr.get(position).getDr());
        holder.tv.setText(arr.get(position).getStr());
        holder.cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                li.clickCat(arr.get(position).getStr().trim().toLowerCase());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    class ViewHold extends RecyclerView.ViewHolder {

        ImageView ig;
        TextView tv;
        CardView cd;
        public ViewHold(@NonNull View itemView) {
            super(itemView);
            ig = itemView.findViewById(R.id.catImg);
            tv = itemView.findViewById(R.id.catName);
            cd = itemView.findViewById(R.id.cardCat);
        }
    }
}


interface ClickCat {
    void clickCat(String s);
}