package com.example.wes19_000.working_on_it;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity  {
    private DatePicker datePicker;
    private TextView dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        datePicker = (DatePicker) findViewById(R.id.datePicker2);
        datePicker.init(2017, 9, 13, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String date = (monthOfYear) + "/" + dayOfMonth + "/" + year;
                dateText.setText(date);
                openDateDisplay(view,date);
            }
        });

        dateText = (TextView)findViewById(R.id.textView2);
    }

    public void openDateDisplay(View view, String date){
        Intent i = new Intent(this, DateDisplay.class);
        i.putExtra("selectedDate", date);
        startActivity(i);
    }

}
