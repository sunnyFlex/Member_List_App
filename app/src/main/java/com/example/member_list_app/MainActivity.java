package com.example.member_list_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView idText = findViewById(R.id.idText);
        TextView passwordText = findViewById(R.id.passwordText);
        TextView welcomeText  = findViewById(R.id.main_text_welcome);

        Intent intent = getIntent();

        String userID = intent.getStringExtra("userID");
        String userPassword = intent.getStringExtra("userPassword");
        String welcome = intent.getStringExtra("userID" + "님 환영합니다.");

        idText.setText(userID);
        passwordText.setText(userPassword);
        welcomeText.setText(welcome);

    }
}