package com.example.wes19_000.working_on_it;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewJob extends AppCompatActivity {
    private JobEntry job = new JobEntry();
    private DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_job);
        setTitle("Add New Client");
        db = new DatabaseHelper(this);

        Intent intent = getIntent();

        EditText startDate = (EditText)findViewById(R.id.startDate);
        startDate.setText(intent.getStringExtra("startDate"));
        job.setStartDate(startDate.getText().toString());
    }

    public void saveInfo(View view){
        EditText nameText = (EditText)findViewById(R.id.clientName);
        EditText addressText = (EditText)findViewById(R.id.clientAddress);
        EditText phoneText = (EditText)findViewById(R.id.clientPhone);
        EditText jobText = (EditText)findViewById(R.id.jobDescription);
        EditText toolText = (EditText)findViewById(R.id.tools);
        EditText payText = (EditText)findViewById(R.id.payment);
        EditText endDate = (EditText)findViewById(R.id.endDate);

        //Tools need to me comma separated.
        String[] tools = toolText.getText().toString().split(",");

        if(!nameText.getText().toString().matches("")){
            job.setClientName(nameText.getText().toString());
        }

        if(!addressText.getText().toString().matches("")){
            job.setClientAddress(addressText.getText().toString());
        }

        if(!phoneText.getText().toString().matches("")){
            job.setClientPhone(phoneText.getText().toString());
        }

        if(!jobText.getText().toString().matches("")){
            job.setJobDescription(jobText.getText().toString());
        }

        if(!toolText.getText().toString().matches("")){
            job.setToolList(tools);
        }

        if(!payText.getText().toString().matches("")) {
            job.setJobPay(Double.parseDouble(payText.getText().toString()));
        }

        if(!endDate.getText().toString().matches("")) {
            job.setEndDate(endDate.getText().toString());
        }

        db.insertJob(job);

        finish();
    }
}
