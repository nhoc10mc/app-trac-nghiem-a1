package com.example.administrator.myapplication;


import android.database.Cursor;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.example.administrator.myapplication.question.QuestionController;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    ListView lvQuestion;
    QuestionController questionController;
    QuestionAdapter adapter;
    EditText edttxt;

    public SearchFragment() {
        // Required empty public constructor
    }
    public void begin(){
        lvQuestion = (ListView) getActivity().findViewById(R.id.lvQuestion);
        edttxt = (EditText) getActivity().findViewById(R.id.edtSearch);
        questionController = new QuestionController(getActivity());
        listCursor(questionController.getSearchQuestion(edttxt.getText().toString()));
    }
    public void listCursor(Cursor cursor){
        adapter = new QuestionAdapter(getActivity(),cursor,true);
        lvQuestion.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        begin();
        edttxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listCursor(questionController.getSearchQuestion(edttxt.getText().toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
