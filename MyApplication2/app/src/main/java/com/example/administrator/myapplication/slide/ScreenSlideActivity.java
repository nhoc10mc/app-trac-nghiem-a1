package com.example.administrator.myapplication.slide;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.question.Question;
import com.example.administrator.myapplication.question.QuestionController;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class ScreenSlideActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 20;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;
    TextView tvKiemTra,tvTimer,tvXemdiem;
    public int checkAn = 0;
    QuestionController questionController;
    ArrayList<Question> arrQuest;
    CounterClass timer;
    public static int num_exam;
    public static String telse="";
    public static String capdo = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setPageTransformer(true, new DepthPageTransformer());
        final Intent intent = getIntent();
        num_exam = intent.getIntExtra("num_exam",0);
        telse = intent.getStringExtra("text");
        capdo = intent.getStringExtra("mucdo");


        timer = new CounterClass(15*60*1000, 1000);
        questionController = new QuestionController(this);
        arrQuest = new ArrayList<Question>();
        if(telse.equals("yes")==true) {
            arrQuest = questionController.getQuestion(num_exam, "",capdo);
        }else if(telse.equals("yes_oto")==true){
            arrQuest = questionController.getQuestionOto(num_exam,"");
        }else {
            arrQuest = (ArrayList<Question>) intent.getExtras().getSerializable("arrQuest");
        }
        tvKiemTra = (TextView)findViewById(R.id.tvKiemTra);
        tvTimer = (TextView)findViewById(R.id.tvTimer);
        tvXemdiem = (TextView)findViewById(R.id.tvScore);
        tvKiemTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnser();

            }
        });
        tvXemdiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent1 =  new Intent(ScreenSlideActivity.this, TextDoneActivity.class);
                intent1.putExtra("arrQuest",arrQuest);
                startActivity(intent1);
            }
        });
        tvTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        timer.start();
    }

    public ArrayList<Question> getArrQuest() {
        return arrQuest;
    }

    @Override
    public void onBackPressed() {
        //if (mPager.getCurrentItem() == 0) {
            dialogExit();
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.

        //} else {
            // Otherwise, select the previous step.
            //mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        //}
    }
    public void dialogExit(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(ScreenSlideActivity.this);
        builder.setIcon(R.drawable.exit);
        builder.setTitle("Thong bao");
        builder.setMessage("Ban co muon thoat hay khong?");
        builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                timer.cancel();
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

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ScreenSlidePageFragment.screate(position,checkAn);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }
    public void checkAnser (){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.checkanserdialog);
        dialog.setTitle("Danh sach cau tra loi");

        checkanseradapter checkanseradapter = new checkanseradapter(arrQuest,this);
        GridView gvLsAnser = (GridView) dialog.findViewById(R.id.gvLsQuestion);
        gvLsAnser.setAdapter(checkanseradapter);
        gvLsAnser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPager.setCurrentItem(position);
                dialog.dismiss();
            }
        });
        Button btnCencal, btnFinish;
        btnCencal = (Button) dialog.findViewById(R.id.button3);
        btnFinish = (Button) dialog.findViewById(R.id.button4);
        btnCencal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                result();
                dialog.dismiss();

            }
        });
        dialog.show();
    }
    public void result(){
        checkAn=1;
        if(mPager.getCurrentItem()>=4) mPager.setCurrentItem(mPager.getCurrentItem()-4);
        else if(mPager.getCurrentItem()<=4) mPager.setCurrentItem(mPager.getCurrentItem()+4);
        tvXemdiem.setVisibility(View.VISIBLE);
        tvKiemTra.setVisibility(View.GONE);
    }
    public class CounterClass extends CountDownTimer {
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            String countTime = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
            tvTimer.setText(countTime); //SetText cho textview hiện thị thời gian.
        }

        @Override
        public void onFinish() {
            tvTimer.setText("00:00");  //SetText cho textview hiện thị thời gian.
            AlertDialog.Builder builder = new AlertDialog.Builder(ScreenSlideActivity.this);
            builder.setTitle("Thong bao");
            builder.setMessage("HET GIO");
            builder.show();
            result();
        }
    }
}