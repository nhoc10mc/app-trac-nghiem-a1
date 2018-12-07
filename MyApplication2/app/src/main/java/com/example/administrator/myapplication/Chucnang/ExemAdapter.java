package com.example.administrator.myapplication.Chucnang;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

import java.util.ArrayList;

public class ExemAdapter extends ArrayAdapter<Exam> {

    public ExemAdapter(Context context, ArrayList<Exam> exam) {
        super(context, 0,exam);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_gridview, parent,false);
        }
        TextView txvName = (TextView) convertView.findViewById(R.id.textView5);
        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.imageView2);

        Exam p = getItem(position);
        if(p!=null){
            txvName.setText(""+p.getName());
            imgIcon.setImageResource(R.drawable.iconsach);
        }
        return convertView;
    }
}
