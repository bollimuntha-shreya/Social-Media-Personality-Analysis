package com.example.person;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class Result extends AppCompatActivity {
    private PieChartView pieChartView;
    float salesPercent, techPercent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_layout);

        Intent intent = getIntent();
        if (intent.hasExtra("salesPercent")) {
            salesPercent = intent.getFloatExtra("salesPercent", 0.0f);
        }

        if (intent.hasExtra("techPercent")) {
            techPercent = intent.getFloatExtra("techPercent", 0.0f);
        }


        pieChartView = findViewById(R.id.pie_chart);

        List<SliceValue> slices = new ArrayList<>();
        slices.add(new SliceValue(salesPercent, Color.BLUE).setLabel("Sales"));
        slices.add(new SliceValue(techPercent, Color.RED).setLabel("Tech"));


        PieChartData pieChartData = new PieChartData(slices);
        pieChartData.setHasLabels(true);

        pieChartView.setPieChartData(pieChartData);

        Log.d("Sales Percent", String.valueOf(salesPercent));
        Log.d("Tech Percent", String.valueOf(techPercent));



    }
}
