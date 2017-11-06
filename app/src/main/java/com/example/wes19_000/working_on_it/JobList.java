package com.example.wes19_000.working_on_it;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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

        Cursor results = db.getBetweenDates(this.getTitle().toString());
        if (results.moveToFirst() == true) {
            do {
                TextView clientName = newTextField();
                clientName.setText(results.getString(0));
                Log.d("Query Result ", clientName.getText().toString());
            } while (results.moveToNext());
        }
        results.close();

    }

    public void addClient(View view) {
        Intent intent = new Intent(this, NewJob.class);
        intent.putExtra("startDate", this.getTitle().toString());
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Cursor cursor = db.getBetweenDates(this.getTitle().toString());
        cursor.moveToLast();
        TextView clientName = newTextField();
        clientName.setText(cursor.getString(0));
        Log.d("Query Result ", clientName.getText().toString());

        cursor.close();
    }

    public TextView newTextField() {
        LinearLayout layout = (LinearLayout) findViewById(R.id.job_layout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.setOrientation(LinearLayout.VERTICAL);

        final TextView textView = new TextView(this);
        textView.setId(0);
        textView.setLayoutParams(params);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toEditScreen(textView.getText().toString());
            }
        });
        layout.addView(textView);
        return textView;
    }

    //TODO Allow the user to edit information on a Job.
    private void toEditScreen(String name){
        Intent intent = new Intent(this, EditJobScreen.class);
        intent.putExtra("selectedJob", name);
        startActivity(intent);
    }
}
