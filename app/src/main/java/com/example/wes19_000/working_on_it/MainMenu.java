package com.example.wes19_000.working_on_it;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;


public class MainMenu extends AppCompatActivity  {
    private DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        datePicker = (DatePicker) findViewById(R.id.datePicker2);
        datePicker.init(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String date = (monthOfYear + 1) + "/" + dayOfMonth + "/" + year;
                openDateDisplay(view,date);
            }
        });
    }

    public void openDateDisplay(View view, String date){
        Intent i = new Intent(this, JobList.class);
        i.putExtra("selectedDate", date);
        startActivity(i);
    }

}
