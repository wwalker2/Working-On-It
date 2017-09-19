package com.example.wes19_000.working_on_it;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DateDisplay extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_display);

        Intent intent = getIntent();
        setTitle(intent.getStringExtra("selectedDate"));
        //String message = intent.getStringExtra(MainMenu.EXTRA_MESSAGE);

        TextView textView = (TextView) findViewById(R.id.textView);
        //textView.setText(intent.getStringExtra("selectedDate"));
    }

    public void saveInfo(View view){
        EditText text = (EditText)findViewById(R.id.name);
        JobEntry job = new JobEntry(text.getText().toString());
        //job.setClientName(text.getText().toString());
    }
}
