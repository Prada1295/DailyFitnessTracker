package com.example.dailyfitnesstracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CaloriesActivity extends AppCompatActivity {
    private EditText weightInput;
    private EditText ageInput;
    private TextView caloriesBurned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories);

        weightInput = findViewById(R.id.weight_input);
        ageInput = findViewById(R.id.age_input);
        caloriesBurned = findViewById(R.id.calories_burned);

        Button calculateButton = findViewById(R.id.calculate_button);
        Button backButton = findViewById(R.id.back_button);

        // Setting an OnClickListener for the Calculate button
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int steps = getIntent().getIntExtra("steps", 0);
                if (steps == 0) {
                    Toast.makeText(CaloriesActivity.this, "Please enter steps in the main screen", Toast.LENGTH_SHORT).show();
                    finish();
                    return;
                }

                // Retrieving the weight and age input from the EditText fields
                int weight = Integer.parseInt(weightInput.getText().toString());
                int age = Integer.parseInt(ageInput.getText().toString());

                // Calculating the calories burned using the provided inputs
                double calories = calculateCalories(weight, age, steps);
                caloriesBurned.setText("Calories Burned: " + Math.round(calories));
            }
        });

        // Setting an OnClickListener for the Back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // Method to calculate calories burned based on weight, age, and steps
    private double calculateCalories(int weight, int age, int steps) {
        // MET value for walking at moderate speed (e.g., 3.5 METs)
        double met = 3.5;
        // Average step length in miles (e.g., 2,000 steps per mile)
        double stepsPerMile = 2000;
        // Convert steps to miles
        double miles = steps / stepsPerMile;
        // Adjust the MET value based on age (example adjustment, you can refine this)
        double ageFactor = 1 - (age - 20) * 0.001;
        // Calories burned per mile = METs * weight (lbs) * miles * ageFactor
        double caloriesPerMile = met * weight * miles * ageFactor;
        return caloriesPerMile;
    }
}