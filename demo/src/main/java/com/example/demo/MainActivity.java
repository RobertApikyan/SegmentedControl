package com.example.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import segmented_control.widget.custom.android.com.segmentedcontrol.SegmentedControl;

public class MainActivity extends AppCompatActivity {
    SegmentedControl<String> segmentedControl;
    private String[] segmentData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        segmentData = getResources().getStringArray(R.array.segments);

        segmentedControl = findViewById(R.id.segmented_control);
        segmentedControl.setSelectedStrokeColor(-13615201);
        segmentedControl.setTextVerticalPadding(16);
        segmentedControl.setDistributeEvenly(true);
        segmentedControl.notifyConfigIsChanged();
    }
}
