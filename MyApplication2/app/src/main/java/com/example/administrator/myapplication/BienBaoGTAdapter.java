package com.example.administrator.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BienBaoGTAdapter extends RecyclerView.Adapter<BienBaoGTAdapter.ViewHolder>{
    Context context;
    int layout;
    ArrayList<BienBaoGT> arrayList;

    public BienBaoGTAdapter(Context context, int layout, ArrayList<BienBaoGT> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(layout, viewGroup,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        BienBaoGT bienBaoGT = arrayList.get(i);
        viewHolder.txtBienBao.setText(bienBaoGT.getYnghia());
        //viewHolder.ivBienBao.setImageResource();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView ivBienBao;
        public TextView txtBienBao;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivBienBao = itemView.findViewById(R.id.ivbienbaoGT);
            txtBienBao = itemView.findViewById(R.id.txtNDBB);
        }
    }
}
