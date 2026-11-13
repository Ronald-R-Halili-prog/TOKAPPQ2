package com.example.tokappq2;

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

    private static final String[] CHILDREN = {"Select child", "Child 1", "Child 2", "Child 3"};

    private EditText etTaskName;
    private EditText etTaskDetails;
    private Button btnTime;
    private Button btnDone;
    private Spinner spChild;

    private String selectedTime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        initViews();
        setupSpinner();
        setupListeners();
    }

    private void initViews() {
        etTaskName = findViewById(R.id.etTaskName);
        etTaskDetails = findViewById(R.id.etTaskDetails);
        btnTime = findViewById(R.id.btnTime);
        btnDone = findViewById(R.id.btnDone);
        spChild = findViewById(R.id.spChild);
    }

    private void setupSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, CHILDREN);
        spChild.setAdapter(adapter);
    }

    private void setupListeners() {
        btnTime.setOnClickListener(v -> showTimePicker());
        btnDone.setOnClickListener(v -> saveTask());
    }

    private void showTimePicker() {
        Calendar now = Calendar.getInstance();
        TimePickerDialog dialog = new TimePickerDialog(
                this,
                (view, hour, minute) -> {
                    selectedTime = String.format(Locale.getDefault(), "%02d:%02d", hour, minute);
                    btnTime.setText(selectedTime);
                },
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                true);
        dialog.show();
    }

    private void saveTask() {
        String name = etTaskName.getText().toString().trim();
        String details = etTaskDetails.getText().toString().trim();

        if (name.isEmpty()) {
            etTaskName.setError("Please enter a task name");
            return;
        }
        if (details.isEmpty()) {
            etTaskDetails.setError("Please describe the task");
            return;
        }
        if (selectedTime.isEmpty()) {
            Toast.makeText(this, "Please set a finish time", Toast.LENGTH_SHORT).show();
            return;
        }
        if (spChild.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Please select a child", Toast.LENGTH_SHORT).show();
            return;
        }

        String child = spChild.getSelectedItem().toString();
        Toast.makeText(this,
                "Task \"" + name + "\" assigned to " + child + " by " + selectedTime,
                Toast.LENGTH_LONG).show();
        finish();
    }
}