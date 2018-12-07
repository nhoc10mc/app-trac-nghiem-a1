package com.example.administrator.myapplication;


import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.administrator.myapplication.Chucnang.Exam;
import com.example.administrator.myapplication.Chucnang.ExemAdapter;
import com.example.administrator.myapplication.slide.ScreenSlideActivity;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class KtHardFragment extends Fragment {
    ExemAdapter exemAdapter;
    GridView gridView;
    ArrayList<Exam> arr_exam = new ArrayList<Exam>();

    public KtHardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kt_hard, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gridView=(GridView) getActivity().findViewById(R.id.gvExamHard);
        arr_exam.add(new Exam("De so 1 - Hard"));
        arr_exam.add(new Exam("De so 2 - Hard"));
        arr_exam.add(new Exam("De so 3 - Hard"));
        arr_exam.add(new Exam("De so 4 - Hard"));
        exemAdapter = new ExemAdapter(getActivity(),arr_exam);
        gridView.setAdapter(exemAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ScreenSlideActivity.class);
                intent.putExtra("num_exam",position+1);
                intent.putExtra("text","yes");
                intent.putExtra("mucdo","kho");
                startActivity(intent);
            }
        });
    }
}
