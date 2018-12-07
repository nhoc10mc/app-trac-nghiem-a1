package com.example.administrator.myapplication.score;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

public class ScoreAdapter extends CursorAdapter{

    public ScoreAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_score, parent,false);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textScore = (TextView)view.findViewById(R.id.tvScore);
        TextView textName = (TextView)view.findViewById(R.id.tvNameStudent);
        TextView textLoaide = (TextView)view.findViewById(R.id.txtLoaide);
        TextView textMade = (TextView)view.findViewById(R.id.txtMade);
        TextView textCapdo = (TextView)view.findViewById(R.id.txtCapdo);
        textName.setText(cursor.getString(1));
        textScore.setText(cursor.getInt(2)+"");
        textLoaide.setText(cursor.getString(4));
        textMade.setText("Ma de:"+cursor.getInt(5));
        textCapdo.setText(cursor.getString(6));
    }
}
