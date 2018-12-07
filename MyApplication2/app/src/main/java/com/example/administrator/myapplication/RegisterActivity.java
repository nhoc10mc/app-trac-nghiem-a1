package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText edt2,edt5,edt7,edt8,edt9;
    Button btn2;
    TextView txt4;
    String urlInsert ="http://192.168.1.2/androidwepservice/insert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edt2 = findViewById(R.id.editText2);
        edt5 = findViewById(R.id.editText5);
        edt7 = findViewById(R.id.editText7);
        edt8 = findViewById(R.id.editText8);
        edt9 = findViewById(R.id.editText9);
        btn2 = findViewById(R.id.button2);
        txt4 = findViewById(R.id.textView4);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edt2.getText().toString();
                String pass = edt7.getText().toString();
                String conform = edt8.getText().toString();
                String email = edt9.getText().toString();
                String sdt = edt5.getText().toString();

                /*if(pass.compareToIgnoreCase(conform)==0){
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    intent.putExtra("name",name);
                    intent.putExtra("pass",pass);
                    setResult(101,intent);
                    finish();
                    //Toast.makeText(RegisterActivity.this,"RegisTered !!!",Toast.LENGTH_SHORT).show();
                }*/
                if(name.isEmpty()||pass.isEmpty()||conform.isEmpty()||email.isEmpty()||sdt.isEmpty()){
                    Toast.makeText(RegisterActivity.this,"Vui long nhap du thong tin",Toast.LENGTH_SHORT).show();

                }else if(pass.compareToIgnoreCase(conform)!=0){
                    Toast.makeText(RegisterActivity.this,"Mat khau khong trung khop",Toast.LENGTH_SHORT).show();
                }else{
                    ThemNguoiDung(urlInsert);
                }
            }
        });
        txt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    private void ThemNguoiDung(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest =  new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success")){
                    Toast.makeText(RegisterActivity.this,"Them thanh cong",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                }else {
                    Toast.makeText(RegisterActivity.this,"Loi them",Toast.LENGTH_SHORT).show();

                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this,"Xay ra loi",Toast.LENGTH_SHORT).show();
                        Log.d("AAA","Loi\n"+error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("tendangnhapND",edt2.getText().toString().trim());
                params.put("emailND",edt9.getText().toString().trim());
                params.put("sdtND",edt5.getText().toString().trim());
                params.put("matkhauND",edt7.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
