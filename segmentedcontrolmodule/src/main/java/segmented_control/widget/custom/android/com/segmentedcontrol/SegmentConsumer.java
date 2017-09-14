package segmented_control.widget.custom.android.com.segmentedcontrol;

import segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column.SegmentViewHolder;

public interface SegmentConsumer<D> {
    void apply(SegmentViewHolder<D> segmentViewHolder);
}