package segmented_control.widget.custom.android.com.segmentedcontrol.utils;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.IntRange;
import android.view.View;

import segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column.SegmentBackgroundType;
import segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column.SegmentViewHolder;

/**
 * Created by Robert Apikyan on 9/8/2017.
 */

public class Utils {
    public static <T> T lazy(T nullable, T nonNull) {
        if (nullable == null) {
            nullable = nonNull;
        }
        return nullable;
    }

    /**
     * Utility method, use to define segment background type
     *
     * @param absolutePosition, Segment absolute position from {@link SegmentViewHolder#getAbsolutePosition()}
     * @param columnCount,      from {@link SegmentViewHolder#getColumnCount()}
     * @param size,             from {@link SegmentViewHolder#getCurrentSize()}
     * @return {@link SegmentBackgroundType}
     */
    @SegmentBackgroundType
    public static int defineSegmentBackground(@IntRange(from = 0) int absolutePosition, @IntRange(from = 1) int columnCount, @IntRange(from = 1) int size) {

        // if only one item
        if (size == 1) {
            return SegmentBackgroundType.SINGLE_BG;
        }

        // if one column
        if (columnCount == 1) {
            // for first
            if (absolutePosition == 0) {
                return SegmentBackgroundType.TOP_SINGLE_BG;
            }

            // for last
            if (absolutePosition == size - 1) {
                return SegmentBackgroundType.BOTTOM_SINGLE_BG;
            }
        }

        // if not one column, but one row
        if (size <= columnCount) {
            if (absolutePosition == 0) {
                return SegmentBackgroundType.TOP_LEFT_SINGLE_BG;
            }

            if (absolutePosition == size - 1) {
                return SegmentBackgroundType.TOP_RIGHT_SINGLE_BG;
            }
        }

        // if not one column and multi row
        if (absolutePosition == 0) {
            return SegmentBackgroundType.TOP_LEFT_BG;
        }

        if (absolutePosition == columnCount - 1) {
            return SegmentBackgroundType.TOP_RIGHT_BG;
        }

        int notCompletedRowItemsCount = size % columnCount;

        int completeRowsItemsCount = size - notCompletedRowItemsCount;

        if (notCompletedRowItemsCount == 1 && absolutePosition == completeRowsItemsCount) {
            return SegmentBackgroundType.BOTTOM_SINGLE_BG;
        }

        if (notCompletedRowItemsCount == 0) {
            if (absolutePosition == size - columnCount) {
                return SegmentBackgroundType.BOTTOM_LEFT_BG;
            }
            if (absolutePosition == size - 1) {
                return SegmentBackgroundType.BOTTOM_RIGHT_BG;
            }
        } else if (notCompletedRowItemsCount > 0) {
            if (absolutePosition == size - notCompletedRowItemsCount) {
                return SegmentBackgroundType.BOTTOM_LEFT_BG;
            }
            if (absolutePosition == size - 1) {
                return SegmentBackgroundType.BOTTOM_RIGHT_BG;
            }
        }

        return SegmentBackgroundType.MIDDLE_BG;
    }

    /**
     * Use to define segment corner radius
     *
     * @param absolutePosition,  Segment absolute position from {@link SegmentViewHolder#getAbsolutePosition()}
     * @param columnCount,       from {@link SegmentViewHolder#getColumnCount()}
     * @param size,              from {@link SegmentViewHolder#getCurrentSize()}
     * @param topLeftRadius,     from {@link SegmentViewHolder#getTopLeftRadius()}
     * @param topRightRadius,    from {@link SegmentViewHolder#getTopRightRadius()} ()}
     * @param bottomRightRadius, from {@link SegmentViewHolder#getBottomRightRadius()}
     * @param bottomLeftRadius,  from  {@link SegmentViewHolder#getBottomLeftRadius()}
     * @return, float[] corners radius,
     */
    public static float[] defineRadiusForPosition(@IntRange(from = 0) int absolutePosition, @IntRange(from = 1) int columnCount, @IntRange(from = 1) int size,
                                                  int topLeftRadius, int topRightRadius, int bottomRightRadius, int bottomLeftRadius) {
        @SegmentBackgroundType
        int bgType = defineSegmentBackground(absolutePosition, columnCount, size);

        switch (bgType) {

            case SegmentBackgroundType.BOTTOM_LEFT_BG:
                return createRadius(0, 0, 0, bottomLeftRadius);

            case SegmentBackgroundType.BOTTOM_RIGHT_BG:
                return createRadius(0, 0, bottomRightRadius, 0);

            case SegmentBackgroundType.BOTTOM_SINGLE_BG:
                return createRadius(0, 0, bottomRightRadius, bottomLeftRadius);

            case SegmentBackgroundType.MIDDLE_BG:
                return createRadius(0, 0, 0, 0);

            case SegmentBackgroundType.SINGLE_BG:
                return createRadius(topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius);

            case SegmentBackgroundType.TOP_LEFT_BG:
                return createRadius(topLeftRadius, 0, 0, 0);

            case SegmentBackgroundType.TOP_LEFT_SINGLE_BG:
                return createRadius(topLeftRadius, 0, 0, bottomLeftRadius);

            case SegmentBackgroundType.TOP_RIGHT_BG:
                return createRadius(0, topRightRadius, 0, 0);

            case SegmentBackgroundType.TOP_RIGHT_SINGLE_BG:
                return createRadius(0, topRightRadius, bottomRightRadius, 0);

            case SegmentBackgroundType.TOP_SINGLE_BG:
                return createRadius(topLeftRadius, topRightRadius, 0, 0);

            default:
                return createRadius(0, 0, 0, 0);
        }
    }

    public static float[] createRadius(float topLeft, float topRight, float bottomRight, float bottomLeft) {
        return new float[]{topLeft, topLeft, topRight, topRight, bottomRight, bottomRight, bottomLeft, bottomLeft};
    }

    /**
     *
     * @param strokeWidth, stroke width
     * @param strokeColor, stroke color
     * @param argb, background color
     * @param radii, use {@link #defineRadiusForPosition(int, int, int, int, int, int, int)} method to define radii
     * @return background drawable
     */
    public static Drawable getBackground(int strokeWidth, int strokeColor, int argb, float[] radii) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setStroke(strokeWidth, strokeColor);
        drawable.setCornerRadii(radii);
        drawable.setColor(argb);
        return drawable;
    }

    public static ValueAnimator createBackgroundAnimation(int argbStart,int argbEnd){
        return ValueAnimator.ofObject(ArgbEvaluator.getInstance(),argbStart, argbEnd);
    }
}
