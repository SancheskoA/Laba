package com.example.pr.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.pr.AppendActivity;
import com.example.pr.HistoryActivity;
import com.example.pr.MyPaperActivity;
import com.example.pr.R;
import com.example.pr.WelcomeActivity;

public class NotificationsFragment extends Fragment {

    private String token;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Bundle arguments = getArguments();
        String username = arguments.get("username").toString();
        token = arguments.get("token").toString();

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        TextView text =(TextView) root.findViewById(R.id.textView8);
        text.append(" " + username);

        root.findViewById(R.id.button8).setOnClickListener((view) -> {myPaper();});
        root.findViewById(R.id.button11).setOnClickListener((view) -> {goWelcome();});
        root.findViewById(R.id.button5).setOnClickListener((view) -> {goRemove();});



        return root;
    }

    private void myPaper() {
        Intent intent = new Intent(getActivity(), MyPaperActivity.class);
        intent.putExtra("token", token);
        startActivity(intent);
    }

    private void goRemove(){
        Intent intent = new Intent(getActivity(), HistoryActivity.class);
        startActivity(intent);
    }

    private void goWelcome(){
        Intent intent = new Intent(getActivity(), WelcomeActivity.class);
        startActivity(intent);
    }
}