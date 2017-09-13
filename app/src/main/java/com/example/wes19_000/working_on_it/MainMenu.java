package com.example.wes19_000.working_on_it;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.wes19_000.working_on_it";
    DatePicker datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        datePicker = (DatePicker)findViewById(R.id.datePicker2);
    }

    public void pickDate(View view){
        Intent intent = new Intent(this, DateDisplay.class);
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();
        String date = day + "/" + month + "/" + year;

        intent.putExtra(EXTRA_MESSAGE, date);
        startActivity(intent);

    }
}
