package com.example.administrator.myapplication.slide;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.question.Question;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScreenSlidePageFragment extends Fragment {
    ArrayList<Question> arr_Que;
    public static final String ARG_PAKE = "page";
    public static final String ARG_CHECKANSWER = "checkAnswer";
    private int mPageNumber;
    public int checkan;
    TextView tvNum, tvQuetsion;
    RadioGroup radioGroup;
    RadioButton radA,radB,radC,radD;
    ImageView imgicon;

    public ScreenSlidePageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);
        tvNum = (TextView) rootView.findViewById(R.id.tvNum);
        tvQuetsion = (TextView) rootView.findViewById(R.id.tvQuestion);
        radA = (RadioButton) rootView.findViewById(R.id.radA);
        radB = (RadioButton) rootView.findViewById(R.id.radB);
        radC = (RadioButton) rootView.findViewById(R.id.radC);
        radD = (RadioButton) rootView.findViewById(R.id.radD);
        imgicon = (ImageView) rootView.findViewById(R.id.ivIcon);
        radioGroup = (RadioGroup) rootView.findViewById(R.id.radGroup);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arr_Que = new ArrayList<Question>();
        ScreenSlideActivity slideActivity = (ScreenSlideActivity) getActivity();
        arr_Que = slideActivity.getArrQuest();
        mPageNumber = getArguments().getInt(ARG_PAKE);
        checkan = getArguments().getInt(ARG_CHECKANSWER);
    }

    public static ScreenSlidePageFragment screate(int pageNumber,int checkanser){
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAKE,pageNumber);
        args.putInt(ARG_CHECKANSWER,checkanser);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvNum.setText("Cau "+ (mPageNumber+1));
        tvQuetsion.setText(arr_Que.get(mPageNumber).getQuestion());
        radA.setText(getItem(mPageNumber).getAns_a());
        radB.setText(getItem(mPageNumber).getAns_b());
        radC.setText(getItem(mPageNumber).getAns_c());
        radD.setText(getItem(mPageNumber).getAns_d());
        imgicon.setImageResource(getResources().getIdentifier(getItem(mPageNumber).getImage()+"","drawable","com.example.administrator.myapplication"));

        if(checkan != 0){
            radA.setClickable(false);
            radB.setClickable(false);
            radC.setClickable(false);
            radD.setClickable(false);
            getCheckAns(getItem(mPageNumber).getResult().toString());
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                arr_Que.get(mPageNumber).choicnID = checkedId;
                arr_Que.get(mPageNumber).setTraloi(getChoiceFromID(checkedId));

                //Toast.makeText(getActivity(),"Day la dap an"+ checkedId, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public Question getItem(int posotion){
        return arr_Que.get(posotion);
    }
    private String getChoiceFromID(int ID){
        if(ID == R.id.radA){
            return "A";
        }else if (ID == R.id.radB){
            return  "B";
        }else if(ID == R.id.radC){
            return "C";
        }else if(ID == R.id.radD){
            return "D";
        }else return "";

    }
    private void getCheckAns(String ans){
        if(ans.equals("A")==true){
            radA.setBackgroundColor(Color.RED);
        }else if(ans.equals("B")==true){
            radB.setBackgroundColor(Color.RED);
        }else if(ans.equals("C")==true){
            radC.setBackgroundColor(Color.RED);
        }else if(ans.equals("D")==true){
            radD.setBackgroundColor(Color.RED);
        }else ;
    }
}
