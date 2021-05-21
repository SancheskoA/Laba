package com.example.pr.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.pr.MapActivity;
import com.example.pr.R;
import com.example.pr.WelcomeActivity;

public class HomeFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        root.findViewById(R.id.button6).setOnClickListener((view) -> {goMap();});

        return root;
    }

    private void goMap(){
        Intent intent = new Intent(getActivity(), MapActivity.class);
        startActivity(intent);
    }
}