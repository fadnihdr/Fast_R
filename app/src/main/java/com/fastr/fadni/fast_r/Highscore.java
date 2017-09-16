package com.fastr.fadni.fast_r;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Highscore extends AppCompatActivity {
    private ScoresDAOHelper scores;
    ArrayAdapter<String> stringArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        scores = new ScoresDAOHelper(this);
    }


    protected void onStart(){
        super.onStart();
        stringArray = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        Cursor cursor = scores.getReadableDatabase()
                .rawQuery("select * from scores", null);
        StringBuilder builder = new StringBuilder();
        builder.append("scores: ");
        while (cursor.moveToNext()) {
            System.out.println("id: " + cursor.getString(0)
                    + " value: " + cursor.getString(1));
            stringArray.add(cursor.getString(1));
            builder.append(cursor.getString(1)).append(" ");
        }

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(stringArray);
        cursor.close();
    }


}
