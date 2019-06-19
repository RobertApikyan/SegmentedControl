package segmented_control.widget.custom.android.com.segmentedcontrol;

import section_layout.widget.custom.android.com.sectionlayout.SectionLayout;

/**
 * Created by Robert Apikyan on 9/7/2017.
 */

class Assert {

    static void adapter(SectionLayout.Adapter adapter) {
        throwIf(adapter == null, new IllegalArgumentException("SegmentedControl#setAdapter -> adapter can't be null"));
    }

    static void columnCount(int columnCount) {
        throwIf(columnCount < Configs.DEFAULT_COLUMN_COUNT,
                new IllegalArgumentException("SegmentedControl#setColumnCount -> columnCounts value is invalid:" + " " + "columnCount = " + columnCount));
    }

    static void outOfBounds(int position, int size, String methodName) {
        throwIf(position > size, new IndexOutOfBoundsException(methodName + " -> " + "position = " + position + " " + "size = " + size));
    }

    private static void throwIf(boolean willThrow, Exception exception) {
        if (willThrow) {
            try {
                throw exception;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void supportedSelectionsCount(int supportedSelectionsCount) {
        throwIf(supportedSelectionsCount < Configs.DEFAULT_SUPPORTED_SELECTIONS_COUNT,
                new IllegalStateException("SegmentedControl#setSupportedSelectionsCount -> supportedSelectionsCount value is invalid: " +
                        " " + "supportedSelectionsCount= "+supportedSelectionsCount));
    }
}

