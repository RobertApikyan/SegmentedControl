package segmented_control.widget.custom.android.com.segmentedcontrol.listeners;

import segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column.SegmentViewHolder;

/**
 * Created by Robert Apikyan on 9/7/2017.
 */

public interface OnSegmentClickListener<D> {
    /**
     * @param segmentViewHolder, the clicked segment view holder instance
     */
    void onSegmentClick(SegmentViewHolder<D> segmentViewHolder);
}
