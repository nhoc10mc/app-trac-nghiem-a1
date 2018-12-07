package com.example.administrator.myapplication.slide;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.question.Question;
import com.example.administrator.myapplication.score.ScoreController;

import java.util.ArrayList;

public class TextDoneActivity extends AppCompatActivity {
    ArrayList<Question> arrQuesBegin = new ArrayList<Question>();
    int numNoAns = 0;
    int numTrue = 0;
    int numFlase = 0;
    int totalscore = 0;
    ScoreController scoreController;
    TextView tvTrue,tvfale,tvNotans, tvtotalscore;
    Button btnSave,btnAgain,btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_done);
        scoreController = new ScoreController(TextDoneActivity.this);
        Intent intent = getIntent();
        arrQuesBegin = (ArrayList<Question>) intent.getExtras().getSerializable("arrQuest");
        begin();
        checkResult();
        totalscore = numTrue*10;
        tvNotans.setText(""+numNoAns);
        tvfale.setText(""+numFlase);
        tvTrue.setText(""+numTrue);
        tvtotalscore.setText(""+totalscore);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(TextDoneActivity.this);
                builder.setIcon(R.drawable.exit);
                builder.setTitle("Thong bao");
                builder.setMessage("Ban co muon thoat hay khong?");
                builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                   builder.show();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TextDoneActivity.this);
                LayoutInflater inflater = TextDoneActivity.this.getLayoutInflater();
                View view = inflater.inflate(R.layout.allert_dialog_save_score,null);
                builder.setView(view);
                final EditText edtName = (EditText)view.findViewById(R.id.edtName);
                //final EditText edtRoom = (EditText)view.findViewById(R.id.edtRoom);
                final TextView tvScore = (TextView) view.findViewById(R.id.tvScore);
                final int numTotal = numTrue*10;
                tvScore.setText(numTotal+"diem");
                //builder.setTitle("Luu diem thi");
                builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = edtName.getText().toString();
                        //String room = edtRoom.getText().toString();
                        String loaide ="";
                        if(ScreenSlideActivity.telse.equals("yes")){
                            loaide="Trac nghiem A1";
                        }else {
                            loaide="Trac nghiem oto";
                        }
                        scoreController.insertScore(name,numTotal,loaide,ScreenSlideActivity.num_exam,ScreenSlideActivity.capdo);
                        Toast.makeText(TextDoneActivity.this,"Luu diem thanh cong",Toast.LENGTH_LONG).show();
                        finish();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog b = builder.create();
                b.show();

            }
        });
        /*btnAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
                finish();
                Intent intent2 = new Intent(TextDoneActivity.this, ScreenSlideActivity.class);

                intent2.putExtra("arrQuest",arrQuesBegin);
                intent2.putExtra("text","no");
                startActivity(intent2);
            }
        });*/
    }
    public void refresh(){
        for(int i=0;i<arrQuesBegin.size(); i++){
            arrQuesBegin.get(i).setTraloi("");
        }
    }

    public void begin(){
        tvfale=(TextView) findViewById(R.id.tvFalse);
        tvTrue=(TextView) findViewById(R.id.tvTrue);
        tvNotans=(TextView) findViewById(R.id.tvNotAns);
        tvtotalscore=(TextView) findViewById(R.id.tvTotalPoin);

        btnExit = (Button)findViewById(R.id.btnExit);
        btnSave = (Button)findViewById(R.id.btnSavePoint);
    }

    public void checkResult(){
        for (int i=0;i< arrQuesBegin.size();i++){
            if(arrQuesBegin.get(i).getTraloi().equals("")==true){
                numNoAns++;
            }else if(arrQuesBegin.get(i).getResult().equals(arrQuesBegin.get(i).getTraloi())==true){
                numTrue++;
            }else numFlase++;
        }
    }
}
