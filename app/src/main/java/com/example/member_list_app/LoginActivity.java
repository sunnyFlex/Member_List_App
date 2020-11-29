package com.example.member_list_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       final EditText idText = findViewById(R.id.idText);
       final EditText passwordText = findViewById(R.id.passwordText);
       final Button loginButton = findViewById(R.id.loginButton);

       final TextView registerButton = findViewById(R.id.registerButton);


       loginButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               final String userID = idText.getText().toString();
               final String userPassword = passwordText.getText().toString();

               // DB서버에서 응답을 받아올 Response.Listener 이벤트 정의
               Response.Listener<String> responseListener = new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       try {
                           JSONObject jsonResponse = new JSONObject(response);
                           boolean success = jsonResponse.getBoolean("success");
                           if (success) {
                               String userID = jsonResponse.getString("userid");
                               String userPassword = jsonResponse.getString("userPassword");

                               // 로그인 성공시 main화면으로 전환
                               Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                               // intent에 정보를 put해서 전달
                               intent.putExtra("userID", userID);
                               intent.putExtra("userPassword", userPassword);
                               LoginActivity.this.startActivity(intent);

                           }else{
                               AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                               builder.setMessage("로그인에 실패했습니다. \n 정보를 확인해 주세요.")
                                       .setNegativeButton("다시 시도", null)
                                       .create()
                                       .show();

                           }
                       } catch (JSONException e) {
                           e.printStackTrace();
                       }
                   }
               };
               // 로그인 요청하기
               LoginRequest loginRequest = new LoginRequest(userID, userPassword, responseListener);
               RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
               queue.add(loginRequest);
           }
       });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);



            }
        });
    }
}