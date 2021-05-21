package com.example.pr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.pr.API.model.Papers;
import com.example.pr.API.service.UserClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyPaperActivity extends AppCompatActivity {

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("http://192.168.0.102:5000/auth/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    UserClient userClient = retrofit.create(UserClient.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_paper);
        findViewById(R.id.imageButton6).setOnClickListener((view) -> {onBackPressed();});

        Bundle arguments = getIntent().getExtras();
        String  token = arguments.get("token").toString();
        token = "Bearer " + token;

        TextView text =(TextView) findViewById(R.id.textView13);


        Call<Papers[]> call = userClient.paper(token);

        call.enqueue(new Callback<Papers[]>() {
            @Override
            public void onResponse(Call<Papers[]> call, Response<Papers[]> response) {
                if(response.isSuccessful()){
                    for (Papers Paper : response.body()) {
                        text.append(Paper.getName() + "\n");
                        text.append("Рецепт: " + Paper.getRecipe() + "\n\n");
                    }

                }
                else{
                    text.setText("Что-то не так");
                }
            }
            @Override
            public void onFailure(Call<Papers[]> call, Throwable t) {
                text.setText("Ошибка подключения");
            }
        });
    }
}