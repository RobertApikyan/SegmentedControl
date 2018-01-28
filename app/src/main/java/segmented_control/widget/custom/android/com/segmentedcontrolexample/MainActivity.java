package segmented_control.widget.custom.android.com.segmentedcontrolexample;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;

import com.google.android.libraries.remixer.annotation.BooleanVariableMethod;
import com.google.android.libraries.remixer.annotation.ColorListVariableMethod;
import com.google.android.libraries.remixer.annotation.RangeVariableMethod;
import com.google.android.libraries.remixer.annotation.RemixerBinder;
import com.google.android.libraries.remixer.ui.RemixerInitialization;
import com.google.android.libraries.remixer.ui.view.RemixerFragment;

import java.util.Arrays;

import segmented_control.widget.custom.android.com.segmentedcontrol.SegmentedControl;

public class MainActivity extends AppCompatActivity {
    SegmentedControl<String> segmentedControl;
    private String[] segmentData;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RemixerInitialization.initRemixer(getApplication());
        segmentData = getResources().getStringArray(R.array.segments);
        //noinspection unchecked
        segmentedControl = (SegmentedControl) findViewById(R.id.segmented_control);

        RemixerBinder.bind(this);
        RemixerFragment.newInstance().attachToFab(this, (FloatingActionButton) findViewById(R.id.edit_fab));
    }

    @BooleanVariableMethod(initialValue = true)
    public void setDistributeEvenly(Boolean willDistributeEvenly) {
        segmentedControl.setDistributeEvenly(willDistributeEvenly);
        segmentedControl.notifyConfigIsChanged();
    }

    @RangeVariableMethod(minValue = 1, maxValue = 5, initialValue = 4)
    public void setColumnCount(Float columnCount) {
        segmentedControl.setColumnCount(columnCount.intValue());
        segmentedControl.notifyConfigIsChanged();
    }

    @RangeVariableMethod(minValue = 1, maxValue = 31, initialValue = 3)
    public void setSegmentCount(Float segmentCount) {
        segmentedControl.removeAllSegments();
        String[] data = new String[segmentCount.intValue()];
        System.arraycopy(segmentData, 0, data, 0, data.length);
        segmentedControl.addSegments(Arrays.asList(data));
    }

    @RangeVariableMethod(minValue = 25, maxValue = 64, initialValue = 40)
    public void setTextSize(Float textSize) {
        segmentedControl.setTextSize(textSize.intValue());
        segmentedControl.notifyConfigIsChanged();
    }

    @RangeVariableMethod(minValue = 0, maxValue = 50, initialValue = 20)
    public void setTextVerticalPadding(Float padding) {
        segmentedControl.setTextVerticalPadding(padding.intValue());
        segmentedControl.notifyConfigIsChanged();
    }

    @RangeVariableMethod(minValue = 0, maxValue = 50, initialValue = 0)
    public void setTextHorizontalPadding(Float padding) {
        segmentedControl.setTextHorizontalPadding(padding.intValue());
        segmentedControl.notifyConfigIsChanged();
    }

    @RangeVariableMethod(minValue = 1, maxValue = 4, initialValue = 2)
    public void setStockWidth(Float width) {
        segmentedControl.setStockWidth(width.intValue());
        segmentedControl.notifyConfigIsChanged();
    }

    @RangeVariableMethod(minValue = 0, maxValue = 50, initialValue = 0)
    public void setSegmentVerticalMargin(Float margin) {
        segmentedControl.setSegmentVerticalMargin(margin.intValue());
        segmentedControl.notifyConfigIsChanged();
    }

    @RangeVariableMethod(minValue = 0, maxValue = 50, initialValue = 0)
    public void setSegmentHorizontalMargin(Float margin) {
        segmentedControl.setSegmentHorizontalMargin(margin.intValue());
        segmentedControl.notifyConfigIsChanged();
    }

    @BooleanVariableMethod()
    public void setRadiusForEverySegment(Boolean radiusForEverySegment) {
        segmentedControl.setRadiusForEverySegment(radiusForEverySegment);
        segmentedControl.notifyConfigIsChanged();
    }

    @RangeVariableMethod(minValue = 0, maxValue = 60, initialValue = 30)
    public void setRadius(Float radius) {
        segmentedControl.setRadius(radius.intValue());
        segmentedControl.notifyConfigIsChanged();
    }

    @RangeVariableMethod(minValue = 0, maxValue = 60, initialValue = 30)
    public void setTopLeftRadius(Float radius) {
        segmentedControl.setTopLeftRadius(radius.intValue());
        segmentedControl.notifyConfigIsChanged();
    }

    @RangeVariableMethod(minValue = 0, maxValue = 60, initialValue = 30)
    public void setTopRightRadius(Float radius) {
        segmentedControl.setTopRightRadius(radius.intValue());
        segmentedControl.notifyConfigIsChanged();
    }

    @RangeVariableMethod(minValue = 0, maxValue = 60, initialValue = 30)
    public void setBottomRightRadius(Float radius) {
        segmentedControl.setBottomRightRadius(radius.intValue());
        segmentedControl.notifyConfigIsChanged();
    }

    @RangeVariableMethod(minValue = 0, maxValue = 60, initialValue = 30)
    public void setBottomLeftRadius(Float radius) {
        segmentedControl.setBottomLeftRadius(radius.intValue());
        segmentedControl.notifyConfigIsChanged();
    }

    @ColorListVariableMethod(limitedToValues = {-49023, -13615201, -12627531, -8875876, -10011977, -328966, -16739862, -16738680, -15138817})
    public void setSelectedStockColor(Integer color) {
        segmentedControl.setSelectedStockColor(color);
        segmentedControl.notifyConfigIsChanged();
    }

    @ColorListVariableMethod(limitedToValues = {-49023, -13615201, -12627531, -8875876, -10011977, -328966, -16739862, -16738680, -15138817})
    public void setUnSelectedStockColor(Integer color) {
        segmentedControl.setUnSelectedStockColor(color);
        segmentedControl.notifyConfigIsChanged();
    }

    @ColorListVariableMethod(limitedToValues = {-49023, -13615201, -12627531, -8875876, -10011977, -328966, -16739862, -16738680, -15138817})
    public void setSelectedBackgroundColor(Integer color) {
        segmentedControl.setSelectedBackgroundColor(color);
        segmentedControl.notifyConfigIsChanged();
    }

    @ColorListVariableMethod(limitedToValues = {-328966, -13615201, -49023, -8875876, -10011977, -12627531, -16739862, -16738680, -15138817})
    public void setUnSelectedBackgroundColor(Integer color) {
        segmentedControl.setUnSelectedBackgroundColor(color);
        segmentedControl.notifyConfigIsChanged();
    }

    @ColorListVariableMethod(limitedToValues = {-328966, -13615201, -49023, -8875876, -10011977, -12627531, -16739862, -16738680, -15138817})
    public void setSelectedTextColor(Integer color) {
        segmentedControl.setSelectedTextColor(color);
        segmentedControl.notifyConfigIsChanged();
    }

    @ColorListVariableMethod(limitedToValues = {-49023, -13615201, -12627531, -8875876, -10011977, -328966, -16739862, -16738680, -15138817})
    public void setUnSelectedTextColor(Integer color) {
        segmentedControl.setUnSelectedTextColor(color);
        segmentedControl.notifyConfigIsChanged();
    }

    public void onResetClick(View view) {
        setDistributeEvenly(true);
        setColumnCount(3f);
        setSegmentCount(3f);
        setTextSize(40f);
        setTextVerticalPadding(20f);
        setTextHorizontalPadding(0f);
        setStockWidth(2f);
        setSegmentVerticalMargin(0f);
        setSegmentHorizontalMargin(0f);
        setRadiusForEverySegment(false);
        setRadius(30f);
        setSelectedStockColor(-49023);
        setUnSelectedStockColor(-49023);
        setSelectedBackgroundColor(-49023);
        setUnSelectedBackgroundColor(-328966);
        setSelectedTextColor(-328966);
        setUnSelectedTextColor(-49023);
    }
}
