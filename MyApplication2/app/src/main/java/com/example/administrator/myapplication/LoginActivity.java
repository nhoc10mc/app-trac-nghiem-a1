package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    EditText edt1,edt2;
    Button bt1;
    TextView txt;
    String urlGetData = "http://192.168.1.2/androidwepservice/login.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt1 = findViewById(R.id.editText);
        edt2 = findViewById(R.id.editText3);
        bt1 = findViewById(R.id.button);
        txt = findViewById(R.id.textView2);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String name=edt1.getText().toString();
                //String password=edt2.getText().toString();
                if(edt1.getText().length() !=0 && edt2.getText().length()!=0){
                    GetData(urlGetData);
                }else{
                    Toast.makeText(LoginActivity.this,"Moi ban nhap du thong tin",Toast.LENGTH_SHORT).show();
                }

            }
        });
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });



    }
    /*@Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==100&&resultCode==101){
            edt1.setText(data.getStringExtra("name"));
            edt2.setText(data.getStringExtra("pass"));
        }
    }*/
    private void GetData(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //Toast.makeText(LoginActivity.this, response.toString(), Toast.LENGTH_SHORT).show();

               for (int i = 0 ;i < response.length();i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        if(edt1.getText().toString().trim().equals(object.getString("TenDangNhap"))&&edt2.getText().toString().trim().equals(object.getString("MatKhau"))){
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            break;
                        }/*else{
                            Toast.makeText(LoginActivity.this,"Ban da nhap sai roi",Toast.LENGTH_SHORT).show();
                        }*/



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this,"Loi",Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
}
