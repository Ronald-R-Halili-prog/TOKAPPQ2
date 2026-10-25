package com.example.familytasks; // change to your package

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Locale;

public class CreateTaskActivity extends AppCompatActivity {

    private EditText etTaskName, etTaskDetails;
    private Button btnTime, btnDone;
    private Spinner spChild;
    private String chosenTime = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        etTaskName = findViewById(R.id.etTaskName);
        etTaskDetails = findViewById(R.id.etTaskDetails);
        btnTime = findViewById(R.id.btnTime);
        btnDone = findViewById(R.id.btnDone);
        spChild = findViewById(R.id.spChild);

        // Replace with your real children list
        String[] children = {"Mia", "Leo", "Zoe"};
        spChild.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, children));

        btnTime.setOnClickListener(v -> {
            Calendar now = Calendar.getInstance();
            new TimePickerDialog(this, (picker, hour, minute) -> {
                int hour12 = (hour % 12 == 0) ? 12 : hour % 12;
                String amPm = hour < 12 ? "AM" : "PM";
                chosenTime = String.format(Locale.getDefault(), "%d:%02d %s", hour12, minute, amPm);
                btnTime.setText(chosenTime);
            }, now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), false).show();
        });

        btnDone.setOnClickListener(v -> saveTask());
    }

    private void saveTask() {
        String name = etTaskName.getText().toString().trim();
        String details = etTaskDetails.getText().toString().trim();
        String child = spChild.getSelectedItem().toString();

        if (name.isEmpty()) {
            etTaskName.setError("Enter a task name");
            return;
        }
        if (details.isEmpty()) {
            etTaskDetails.setError("Enter what to do");
            return;
        }
        if (chosenTime == null) {
            Toast.makeText(this, "Please set a time", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO: save name, details, chosenTime, child to your database
        Toast.makeText(this,
                name + " for " + child + " by " + chosenTime,
                Toast.LENGTH_LONG).show();
        finish();
    }
}