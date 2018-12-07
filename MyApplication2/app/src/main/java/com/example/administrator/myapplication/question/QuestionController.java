package com.example.administrator.myapplication.question;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.myapplication.BienBaoGT;

import java.util.ArrayList;

public class QuestionController {
    private DBHelper dbHelper;

    public QuestionController(Context context) {
        dbHelper = new DBHelper(context);
    }
    public ArrayList<Question> getQuestion(int num_xam, String subject, String dokho){
        ArrayList<Question> lsData = new ArrayList<Question>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tracnghiem WHERE num_exam ='"+ num_xam +"' and dokho ='"+ dokho +"' Order BY RANDOM()",null);
        cursor.moveToFirst();
        do {
            Question item;
            item = new Question(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getInt(7),cursor.getString(8),"");
        lsData.add(item);
        }while (cursor.moveToNext());
        return lsData;
    }
    // Lay danh sach cau hoi theo cau hoi...
    public Cursor getSearchQuestion(String key){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tracnghiem WHERE question LIKE '%"+ key +"%'",null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public ArrayList<Question> getQuestionOto(int num_xam, String subject){
        ArrayList<Question> lsData = new ArrayList<Question>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tracnghiemoto WHERE num_exam ='"+ num_xam +"' Order BY RANDOM()",null);
        cursor.moveToFirst();
        do {
            Question item;
            item = new Question(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getInt(7),cursor.getString(8),"");
            lsData.add(item);
        }while (cursor.moveToNext());
        return lsData;
    }

    public  ArrayList<BienBaoGT> getBienBaoGT(String loai){
        ArrayList<BienBaoGT> lsData = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM bienbao WHERE loai = '"+ loai +"'", null);
        cursor.moveToFirst();
        do{
            BienBaoGT item;
            item = new BienBaoGT(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getString(3));
            lsData.add(item);
        }while (cursor.moveToNext());
        return lsData;
    }
}
