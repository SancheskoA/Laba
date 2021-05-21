package com.example.pr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pr.API.model.Login;
import com.example.pr.API.model.Papers;
import com.example.pr.API.service.UserClient;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppendActivity extends AppCompatActivity {

    private String token;
    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("http://192.168.0.102:5000/auth/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    UserClient userClient = retrofit.create(UserClient.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle arguments = getIntent().getExtras();
        token = arguments.get("token").toString();
        token = "Bearer " + token;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_append);
        findViewById(R.id.imageButton7).setOnClickListener((view) -> {onBackPressed();});
        findViewById(R.id.button7).setOnClickListener((view) -> {createPaper();});


    }

    private void createPaper(){

        final EditText name = (EditText)findViewById(R.id.editTextTextPersonName7);
        final EditText recipe = (EditText)findViewById(R.id.editTextTextPersonName8);
        Papers papers = new Papers(name.getText().toString(), recipe.getText().toString());

        Call<ResponseBody> call = userClient.createPaper(token, papers);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        Toast.makeText(AppendActivity.this, response.body().string(),Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{

                    Toast.makeText(AppendActivity.this, "Пользователь не авторизован",Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(AppendActivity.this, "Ошибка подключения",Toast.LENGTH_SHORT).show();
            }
        });
    }


}