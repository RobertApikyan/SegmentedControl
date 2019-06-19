package segmented_control.widget.custom.android.com.segmentedcontrol;

import segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column.SegmentDecoration;

/**
 * Created by Robert Apikyan on 9/7/2017.
 */

class Configs {
    static final int DEFAULT_COLUMN_COUNT = 2;
    static final int DEFAULT_SUPPORTED_SELECTIONS_COUNT = 1;

    boolean willDistributeEvenly;

    int columnCount;
    int supportedSelectionsCount;

    SegmentDecoration segmentDecoration = new SegmentDecoration();

    static Configs getDefault() {
        Configs configs = new Configs();
        configs.willDistributeEvenly = false;
        configs.columnCount = DEFAULT_COLUMN_COUNT;
        configs.supportedSelectionsCount = DEFAULT_SUPPORTED_SELECTIONS_COUNT;
        return configs;
    }
}
