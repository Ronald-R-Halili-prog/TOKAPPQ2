package com.example.tokappq2;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class MainActivity1 extends AppCompatActivity {

    private final GradientAnimator animator1 = new GradientAnimator();
    private final GradientAnimator animator2 = new GradientAnimator();
    private final GradientAnimator animator3 = new GradientAnimator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button assignedBtn = findViewById(R.id.button4);
        Button detailsBtn = findViewById(R.id.button);
        Button completedBtn = findViewById(R.id.button3);

        float radius = 50 * getResources().getDisplayMetrics().density;
        int[] setA = {0xFFFFB6C1, 0xFFB3E5FC};
        int[] setB = {0xFFD1C4E9, 0xFFFFF59D};

        animator1.attach(assignedBtn, setA, setB, radius);
        animator2.attach(detailsBtn, setA, setB, radius);
        animator3.attach(completedBtn, setA, setB, radius);
        Objects.requireNonNull(getSupportActionBar()).hide();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        animator1.stop();
        animator2.stop();
        animator3.stop();}
    }