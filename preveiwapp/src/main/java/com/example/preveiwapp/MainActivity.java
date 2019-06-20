package com.example.preveiwapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import segmented_control.widget.custom.android.com.segmentedcontrol.SegmentedControl;
import segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column.SegmentViewHolder;
import segmented_control.widget.custom.android.com.segmentedcontrol.listeners.OnSegmentClickListener;
import segmented_control.widget.custom.android.com.segmentedcontrol.listeners.OnSegmentSelectedListener;

public class MainActivity extends AppCompatActivity {
    SegmentedControl<String> sc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sc = findViewById(R.id.sc);

        sc.addSegments(getResources().getStringArray(R.array.segments));

        sc.setColumnCount(3);
        sc.notifyConfigIsChanged();

        sc.addOnSegmentSelectListener(new OnSegmentSelectedListener<String>() {
            @Override
            public void onSegmentSelected(SegmentViewHolder<String> segmentViewHolder, boolean isSelected, boolean isReselected) {
                Log.d("Segment Select",segmentViewHolder.getSegmentData() + " " + "isSelected= " + isSelected + " isReselected= "+ isReselected);
            }
        });

        sc.addOnSegmentClickListener(new OnSegmentClickListener<String>() {
            @Override
            public void onSegmentClick(SegmentViewHolder<String> segmentViewHolder) {
                Log.d("Segment Click",segmentViewHolder.getSegmentData());
            }
        });

    }
}
