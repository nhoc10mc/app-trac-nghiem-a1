package com.example.administrator.myapplication;


import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class BienBaoFragment extends Fragment {

    Button btncam;
    Button btnnguyhiem;

    public BienBaoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bien_bao, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btncam = (Button) getActivity().findViewById(R.id.btnbienbaocam);
        btnnguyhiem = (Button) getActivity().findViewById(R.id.btnbienbaonguyhiem);

        btncam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),BienBaoActivity.class);
                intent.putExtra("loai","cam");
                startActivity(intent);
            }
        });

        btnnguyhiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BienBaoActivity.class);
                intent.putExtra("loai", "nguyhiem");
                startActivity(intent);
            }
        });
    }
}
