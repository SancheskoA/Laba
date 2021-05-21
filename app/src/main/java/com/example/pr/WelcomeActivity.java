package com.example.pr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        findViewById(R.id.button4).setOnClickListener((view) -> {goLogin();});
        findViewById(R.id.button3).setOnClickListener((view) -> {goRegistration();});


    }

    private void goLogin() {
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void goRegistration() {
        Intent intent = new Intent(WelcomeActivity.this, RegistrActivity.class);
        startActivity(intent);
    }


}