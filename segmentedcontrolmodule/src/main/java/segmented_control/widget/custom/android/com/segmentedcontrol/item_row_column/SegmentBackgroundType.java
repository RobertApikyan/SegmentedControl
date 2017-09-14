package segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column.SegmentBackgroundType.BOTTOM_LEFT_BG;
import static segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column.SegmentBackgroundType.BOTTOM_RIGHT_BG;
import static segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column.SegmentBackgroundType.BOTTOM_SINGLE_BG;
import static segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column.SegmentBackgroundType.MIDDLE_BG;
import static segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column.SegmentBackgroundType.SINGLE_BG;
import static segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column.SegmentBackgroundType.TOP_LEFT_BG;
import static segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column.SegmentBackgroundType.TOP_LEFT_SINGLE_BG;
import static segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column.SegmentBackgroundType.TOP_RIGHT_BG;
import static segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column.SegmentBackgroundType.TOP_RIGHT_SINGLE_BG;
import static segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column.SegmentBackgroundType.TOP_SINGLE_BG;

/**
 * Created by Robert Apikyan on 9/12/2017.
 */
@IntDef(value = {SINGLE_BG,
        TOP_SINGLE_BG,
        TOP_LEFT_BG,
        TOP_LEFT_SINGLE_BG,
        TOP_RIGHT_SINGLE_BG,
        TOP_RIGHT_BG,
        MIDDLE_BG,
        BOTTOM_SINGLE_BG,
        BOTTOM_LEFT_BG,
        BOTTOM_RIGHT_BG})
@Retention(RetentionPolicy.SOURCE)
public @interface SegmentBackgroundType {
    int SINGLE_BG = 0;
    int TOP_SINGLE_BG = 1;
    int TOP_LEFT_BG = 2;
    int TOP_LEFT_SINGLE_BG = 3;
    int TOP_RIGHT_SINGLE_BG = 4;
    int TOP_RIGHT_BG = 5;
    int MIDDLE_BG = 6;
    int BOTTOM_SINGLE_BG = 7;
    int BOTTOM_LEFT_BG = 8;
    int BOTTOM_RIGHT_BG = 9;
}
