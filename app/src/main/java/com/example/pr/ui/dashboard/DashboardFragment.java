package com.example.pr.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.pr.API.model.Papers;
import com.example.pr.API.model.User;
import com.example.pr.API.service.UserClient;
import com.example.pr.AppendActivity;
import com.example.pr.MainActivity;
import com.example.pr.NaviActivity;
import com.example.pr.R;
import com.example.pr.RegistrActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("http://192.168.0.102:5000/auth/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    UserClient userClient = retrofit.create(UserClient.class);

    Bundle arguments;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        TextView text =(TextView) root.findViewById(R.id.textViewpapers);

        arguments = getArguments();

        root.findViewById(R.id.imageButton9).setOnClickListener((view) -> {goNewPaper();});


        Call<Papers[]> call = userClient.papers();

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


        return root;
    }

    private void goNewPaper() {
        String token = arguments.get("token").toString();
        Intent intent = new Intent(getActivity(), AppendActivity.class);
        intent.putExtra("token", token);
        startActivity(intent);
    }






}