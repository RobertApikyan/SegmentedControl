package segmented_control.widget.custom.android.com.segmentedcontrol.listeners;

import segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column.SegmentViewHolder;

/**
 * Created by Robert Apikyan on 9/12/2017.
 */

public interface OnSegmentSelectRequestListener<D> {
    /**
     * The event will be triggered before perform segment selection,and after segment click event
     *
     * @param segmentViewHolder, clicked segment view holder
     * @return false segment selection will be ignored, true segment selection will be performed
     */
    boolean onSegmentSelectRequest(SegmentViewHolder<D> segmentViewHolder);
}
