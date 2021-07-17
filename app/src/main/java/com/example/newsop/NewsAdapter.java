package com.example.newsop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<ViewHold> {

    private ArrayList<News> marr = new ArrayList<>();
    private NewsAct n;
    private String s;
    public NewsAdapter(NewsAct n) {

        super();
        this.n = n;
    }

    @NonNull
    @Override
    public ViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_layout,parent,false);
        ViewHold vh = new ViewHold(v);
        v.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        n.onClickNews(s);
                    }
                }
        );
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHold holder, int position) {
        holder.getTit().setText(marr.get(position).getTitle());
        holder.getCon().setText(marr.get(position).getCont());
        Glide.with(holder.itemView.getContext()).load(marr.get(position).getPhoto()).into(holder.getIg());
        s = marr.get(position).getUrl();
    }

    @Override
    public int getItemCount() {
        return marr.size();
    }

    public void getMe(ArrayList<News> arr) {

        marr.clear();

        marr.addAll(arr);

        notifyDataSetChanged();
    }
}

class ViewHold extends RecyclerView.ViewHolder {

    TextView v;
    TextView co;
    ImageView ig;
    public ViewHold(@NonNull View itemView) {
        super(itemView);
        v = (TextView) itemView.findViewById(R.id.tit);
        co = (TextView) itemView.findViewById(R.id.cn);
        ig = (ImageView) itemView.findViewById(R.id.im);
    }


    public TextView getTit() {
        return v;
    }
    public TextView getCon(){return co;}
    public ImageView getIg() {return ig;}
}


interface NewsAct {

    void onClickNews(String s);
}