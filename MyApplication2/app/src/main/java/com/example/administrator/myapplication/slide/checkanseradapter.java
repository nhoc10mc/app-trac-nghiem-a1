package com.example.administrator.myapplication.slide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.question.Question;

import java.util.ArrayList;

public class checkanseradapter extends BaseAdapter{
    ArrayList lsdata;
    LayoutInflater inflater;

    public checkanseradapter(ArrayList lsdata, Context context) {
        this.lsdata = lsdata;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return lsdata.size();
    }

    @Override
    public Object getItem(int position) {
        return lsdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Question data = (Question) getItem(position);
        ViewHoder hoder;
        if(convertView == null){
            hoder = new ViewHoder();
            convertView = inflater.inflate(R.layout.itemgridviewlistanser,null);
            hoder.tvNumAser = (TextView) convertView.findViewById(R.id.textView6);
            hoder.tvCourAnser = (TextView) convertView.findViewById(R.id.textView7);
            convertView.setTag(hoder);
        }
        else {
            hoder = (ViewHoder) convertView.getTag();
        }
        int i = position +1;
        hoder.tvNumAser.setText("Cau"+i+":");
        hoder.tvCourAnser.setText(data.getTraloi());
        return convertView;

    }
    private static class ViewHoder{
        TextView tvNumAser,tvCourAnser;
    }
}
