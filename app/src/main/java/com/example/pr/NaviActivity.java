package com.example.pr;

import android.app.ActionBar;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavArgument;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.IOException;

public class NaviActivity extends AppCompatActivity {
    NavController navController;
    Bundle bundle = new Bundle();
    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi);

        Bundle arguments = getIntent().getExtras();
        String username = arguments.get("username").toString();
        String token = arguments.get("token").toString();

        bundle.putString("username", username);
        bundle.putString("token", token);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navView.setOnNavigationItemSelectedListener(getBottomNavigationListener());
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        dbHelper = new DBHelper(this);

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(dbHelper.KEY_USERNAME, username);
        database.insert(dbHelper.TABLE_CONTACTS,null , contentValues);

    }

    @NonNull
    private BottomNavigationView.OnNavigationItemSelectedListener getBottomNavigationListener() {
        return (item) -> {
          switch (item.getItemId()) {
              case R.id.navigation_home:
                  navController.navigate(R.id.navigation_home, bundle);
                  break;
              case R.id.navigation_dashboard:
                  navController.navigate(R.id.navigation_dashboard, bundle);
                  break;
              case R.id.navigation_notifications:
                  navController.navigate(R.id.navigation_notifications, bundle);
                  break;
          }
          return true;
        };
    }



}