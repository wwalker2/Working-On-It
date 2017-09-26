package com.example.wes19_000.working_on_it;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class JobList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list);

        Intent intent = getIntent();
        setTitle(intent.getStringExtra("selectedDate"));
    }
}
