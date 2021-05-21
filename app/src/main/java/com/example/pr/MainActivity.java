package com.example.pr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pr.API.model.Login;
import com.example.pr.API.model.User;
import com.example.pr.API.service.UserClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {



    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("http://192.168.0.102:5000/auth/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    UserClient userClient = retrofit.create(UserClient.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button2).setOnClickListener((view) -> {login();});
        findViewById(R.id.ArrowButtonlog).setOnClickListener((view) -> {onBackPressed();});
        findViewById(R.id.textView2).setOnClickListener((view) -> {goRegistration();});


    }

    private void login(){
        final EditText username = (EditText)findViewById(R.id.editTextTextPersonName);
        final EditText password = (EditText)findViewById(R.id.editTextTextPassword);

        Login login = new Login(username.getText().toString(), password.getText().toString());
        Call<User> call = userClient.login(login);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Intent intent = new Intent(MainActivity.this, NaviActivity.class);
                    intent.putExtra("username", response.body().getUsername());
                    intent.putExtra("token", response.body().getToken());
                    startActivity(intent);
                }
                else{
                    Toast toast = Toast.makeText(MainActivity.this, "Неправильный логин или пароль!",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Ошибка подключения",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void goRegistration() {
        Intent intent = new Intent(MainActivity.this, RegistrActivity.class);
        startActivity(intent);
    }
}