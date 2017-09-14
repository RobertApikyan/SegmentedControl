package segmented_control.widget.custom.android.com.segmentedcontrol.listeners;

import segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column.SegmentViewHolder;

/**
 * Created by Robert Apikyan on 9/7/2017.
 */

public interface OnSegmentSelectedListener<D> {
    /**
     * The Event will be triggered: if the segment is selected, reselected and unSelected
     * 1. Segment is selected for first time:  isSelected = true ,isReselected = false
     * 2. Segment is reselected: isSelected = true ,isReselected = true
     * 3. Segment is unSelected: isSelected = false ,isReselected = false
     *
     * @param segmentViewHolder event related segmentViewHolder
     * @param isSelected,       true segment is selected
     * @param isReselected,     true segment is reselected
     */
    void onSegmentSelected(SegmentViewHolder<D> segmentViewHolder, boolean isSelected, boolean isReselected);
}
