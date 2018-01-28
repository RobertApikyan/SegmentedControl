package segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column;

import android.graphics.Typeface;

import segmented_control.widget.custom.android.com.segmentedcontrol.listeners.OnSegmentClickListener;

/**
 * Created by Robert Apikyan on 9/7/2017.
 */

public class SegmentData<D> {
    boolean isSelected;
    int absolutePosition;

    private int row;
    private int column;

    private D segmentData;
    private OnSegmentClickListener<D> onSegmentClickListener;

    private SegmentDecoration segmentDecoration;
    private int currentSize;
    private int columnCount;

    public static <D> SegmentData<D> create(D segmentData, OnSegmentClickListener<D> onSegmentClickListener, int absolutePosition, int row, int column, int currentSize, int columnCount, SegmentDecoration segmentDecoration) {
        SegmentData<D> sd = new SegmentData<>();
        sd.segmentData = segmentData;
        sd.absolutePosition = absolutePosition;
        sd.row = row;
        sd.column = column;
        sd.currentSize = currentSize;
        sd.columnCount = columnCount;
        sd.segmentDecoration = segmentDecoration;
        sd.onSegmentClickListener = onSegmentClickListener;
        return sd;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public int getAbsolutePosition() {
        return absolutePosition;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public D getSegmentData() {
        return segmentData;
    }

    public OnSegmentClickListener<D> getOnSegmentClickListener() {
        return onSegmentClickListener;
    }

    public int getSelectedStockColor() {
        return segmentDecoration.getSelectedStockColor();
    }

    public int getUnSelectedStockColor() {
        return segmentDecoration.getUnSelectedStockColor();
    }

    public int getStockWidth() {
        return segmentDecoration.getStockWidth();
    }

    public int getSelectBackgroundColor() {
        return segmentDecoration.getSelectBackgroundColor();
    }

    public int getUnSelectedBackgroundColor() {
        return segmentDecoration.getUnSelectedBackgroundColor();
    }

    public int getSelectedTextColor() {
        return segmentDecoration.getSelectedTextColor();
    }

    public int getUnSelectedTextColor() {
        return segmentDecoration.getUnSelectedTextColor();
    }

    public int getTextSize() {
        return segmentDecoration.getTextSize();
    }

    public Typeface getTypeFace(){
        return segmentDecoration.getTypeface();
    }

    public int getTextHorizontalPadding() {
        return segmentDecoration.getTextHorizontalPadding();
    }

    public int getTextVerticalPadding() {
        return segmentDecoration.getTextVerticalPadding();
    }

    public int getSegmentVerticalMargin() {
        return segmentDecoration.getSegmentVerticalMargin();
    }

    public int getSegmentHorizontalMargin() {
        return segmentDecoration.getSegmentHorizontalMargin();
    }

    public int getTopLeftRadius() {
        return segmentDecoration.getTopLeftRadius();
    }

    public int getTopRightRadius() {
        return segmentDecoration.getTopRightRadius();
    }

    public int getBottomRightRadius() {
        return segmentDecoration.getBottomRightRadius();
    }

    public int getBottomLeftRadius() {
        return segmentDecoration.getBottomLeftRadius();
    }

    public boolean isRadiusForEverySegment() {
        return segmentDecoration.isRadiusForEverySegment();
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public int getColumnCount() {
        return columnCount;
    }
}
