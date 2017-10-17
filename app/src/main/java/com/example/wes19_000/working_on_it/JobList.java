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


    }

    public void addClient(View view) {
        Intent intent = new Intent(this, NewJob.class);
        intent.putExtra("startDate", this.getTitle().toString());
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Cursor cursor = db.getDataByDate(this.getTitle().toString());
        do {
            TextView clientName = newTextField();
            clientName.setText(cursor.getString(0));
            Log.d("Query Result ", clientName.getText().toString());
        } while (cursor.moveToNext());
        cursor.close();
    }

    public TextView newTextField() {
        LinearLayout layout = (LinearLayout) findViewById(R.id.job_layout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.setOrientation(LinearLayout.VERTICAL);

        TextView textView = new TextView(this);
        textView.setId(0);
        textView.setLayoutParams(params);

        layout.addView(textView);
        return textView;
    }
}
