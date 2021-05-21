package com.example.pr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pr.API.model.Login;
import com.example.pr.API.model.Registration;
import com.example.pr.API.model.User;
import com.example.pr.API.service.UserClient;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrActivity extends AppCompatActivity {

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("http://192.168.0.102:5000/auth/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    UserClient userClient = retrofit.create(UserClient.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registr);
        findViewById(R.id.imageButton5).setOnClickListener((view) -> {onBackPressed();});
        findViewById(R.id.textView5).setOnClickListener((view) -> {goLogin();});
        findViewById(R.id.button).setOnClickListener((view) -> {registration();});

    }

    private void goLogin() {
        Intent intent = new Intent(RegistrActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void registration(){
        final EditText username = (EditText)findViewById(R.id.editTextTextPersonName3);
        final EditText email = (EditText)findViewById(R.id.editTextTextPersonName4);
        final EditText password = (EditText)findViewById(R.id.editTextTextPassword3);

        Registration registration = new Registration(username.getText().toString(), password.getText().toString(), email.getText().toString());
        Call<ResponseBody> call = userClient.Registration(registration);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){

                    try {
                        Toast.makeText(RegistrActivity.this, response.body().string(),Toast.LENGTH_SHORT).show();
                        goLogin();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    Toast toast = null;
                    toast = Toast.makeText(RegistrActivity.this, "Ошибка при регистрации",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(RegistrActivity.this, "Ошибка подключения",Toast.LENGTH_SHORT).show();
            }
        });

    }

}