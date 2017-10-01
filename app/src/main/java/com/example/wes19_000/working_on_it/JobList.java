package com.example.wes19_000.working_on_it;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class JobList extends AppCompatActivity {
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list);
        db = new DatabaseHelper(this);

        Intent intent = getIntent();
        String thisDate = intent.getStringExtra("selectedDate");
        setTitle(thisDate);


    }

    public void addClient(View view){
        Intent intent = new Intent(this, NewJob.class);
        intent.putExtra("startDate",this.getTitle().toString());
        startActivity(intent);
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        TextView clientName = (TextView)findViewById(R.id.todayClientName);
        Cursor cursor = db.getData(0);
        cursor.moveToFirst();

        clientName.setText(cursor.getColumnIndex(DatabaseHelper.NAME_COLUMN));
        cursor.close();
    }
}
