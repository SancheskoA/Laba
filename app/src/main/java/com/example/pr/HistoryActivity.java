package com.example.pr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class HistoryActivity extends AppCompatActivity {
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        TextView text =(TextView) findViewById(R.id.textViewLogins);

        findViewById(R.id.imageButtonHistory).setOnClickListener((view) -> {onBackPressed();});
        findViewById(R.id.buttonRemove).setOnClickListener((view) -> {removeAll();});

        Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null,
                null, null, null, null, null);

        if(cursor.moveToFirst()){
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_USERNAME);

            do {
                int id =  cursor.getInt(idIndex);
                text.append(cursor.getString(nameIndex) + "\n" );
            } while (cursor.moveToNext());

        }

        cursor.close();
    }

    private void removeAll(){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete(DBHelper.TABLE_CONTACTS,null,null);
        HistoryActivity.this.recreate();
    }

}