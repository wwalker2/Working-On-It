package com.example.wes19_000.working_on_it;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditJobScreen extends AppCompatActivity {
    private DatabaseHelper db;
    private String currentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_job_screen);
        db = new DatabaseHelper(this);

        Intent intent = getIntent();
        fillTextFields(intent.getStringExtra("selectedJob"));
    }

    //Fills the text fields with the data currently tied to the selected job.
    public void fillTextFields(String name){
        EditText nameField = (EditText)findViewById(R.id.clientNameEdit);
        EditText addressText = (EditText)findViewById(R.id.clientAddressEdit);
        EditText phoneText = (EditText)findViewById(R.id.clientPhoneEdit);
        EditText jobText = (EditText)findViewById(R.id.jobDescriptionEdit);
        EditText toolText = (EditText)findViewById(R.id.toolsEdit);
        EditText payText = (EditText)findViewById(R.id.paymentEdit);
        EditText startDate = (EditText)findViewById(R.id.startDateEdit);
        EditText endDate = (EditText)findViewById(R.id.endDateEdit);

        Cursor jobEntry = db.getData(name);
        nameField.setText(jobEntry.getString(0));
        startDate.setText(jobEntry.getString(1));
        endDate.setText(jobEntry.getString(2));

        currentName = jobEntry.getString(0);
        jobEntry.close();
    }

    public void saveEdits(View view){
        EditText nameField = (EditText)findViewById(R.id.clientNameEdit);
        String editedName = nameField.getText().toString();

        Cursor cursor = db.updateDataforEdit(currentName, editedName);
        finish();

    }
}
