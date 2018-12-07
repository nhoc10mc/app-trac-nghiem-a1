package com.example.administrator.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.example.administrator.myapplication.question.QuestionController;

import java.util.ArrayList;

public class BienBaoActivity extends AppCompatActivity {

    String loaiBB;
    ArrayList<BienBaoGT> arrBBGT;
    QuestionController questionController;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private BienBaoGTAdapter bienBaoGTAdapter;
    ImageView imgBBGT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bien_bao);

        imgBBGT = (ImageView) findViewById(R.id.ivbienbaoGT);
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);

        Intent intent = getIntent();
        loaiBB = intent.getStringExtra("loai");
        questionController = new QuestionController(this);
        arrBBGT = new ArrayList<>();
        arrBBGT = questionController.getBienBaoGT(loaiBB);
        bienBaoGTAdapter = new BienBaoGTAdapter(BienBaoActivity.this, R.layout.item_bien_bao_gt, arrBBGT);
        recyclerView.setAdapter(bienBaoGTAdapter);
        for (int i = 0; i<arrBBGT.size(); i++) {
            imgBBGT.setImageResource(getResources().getIdentifier(arrBBGT.get(i).getImage() + "", "drawable", "com.example.administrator.myapplication"));
        }

    }
}
