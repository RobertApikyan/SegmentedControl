package segmented_control.widget.custom.android.com.segmentedcontrol.custom_segment;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import segmented_control.widget.custom.android.com.segmented_control.R;
import segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column.SegmentViewHolder;

import static segmented_control.widget.custom.android.com.segmentedcontrol.utils.Utils.createRadius;
import static segmented_control.widget.custom.android.com.segmentedcontrol.utils.Utils.defineRadiusForPosition;
import static segmented_control.widget.custom.android.com.segmentedcontrol.utils.Utils.getBackground;

/**
 * Created by Robert Apikyan on 9/8/2017.
 */

public class SegmentViewHolderImpl extends SegmentViewHolder<CharSequence> {
    private TextView itemTV;
    private float[] radius;

    public SegmentViewHolderImpl(@NonNull View sectionView) {
        super(sectionView);
        itemTV = (TextView) sectionView.findViewById(R.id.item_segment_tv);
    }

    @Override
    protected void onSegmentBind(CharSequence segmentData) {
        itemTV.setText(segmentData);
        if (isRadiusForEverySegment()) {
            radius = createRadius(getTopLeftRadius(), getTopRightRadius(), getBottomRightRadius(), getBottomLeftRadius());
        } else {
            radius = defineRadiusForPosition(getAbsolutePosition(), getColumnCount(), getCurrentSize(), getTopLeftRadius(), getTopRightRadius(), getBottomRightRadius(), getBottomLeftRadius());
        }
        setSectionDecorationSelected(false);
        itemTV.setTextSize(TypedValue.COMPLEX_UNIT_PX, getTextSize());
        itemTV.setPadding(getTextHorizontalPadding(), getTextVerticalPadding(), getTextHorizontalPadding(), getTextVerticalPadding());
        ViewGroup.MarginLayoutParams.class.cast(itemTV.getLayoutParams()).setMargins(getSegmentHorizontalMargin(), getSegmentVerticalMargin(), getSegmentHorizontalMargin(), getSegmentVerticalMargin());
    }

    @Override
    public void onSegmentSelected(boolean isSelected, boolean isReselected) {
        super.onSegmentSelected(isSelected, isReselected);
        if (isSelected) {
            setSectionDecorationSelected(true);
        } else {
            setSectionDecorationSelected(false);
        }
    }

    private Drawable getSelectedBackground() {
        return getBackground(getStockWidth(), getSelectedStockColor(), getSelectBackgroundColor(), radius);
    }

    private Drawable getUnSelectedBackground() {
        return getBackground(getStockWidth(), getUnSelectedStockColor(), getUnSelectedBackgroundColor(), radius);
    }

    private void setSectionDecorationSelected(boolean isSelected) {
        setBackground(isSelected ? getSelectedBackground() : getUnSelectedBackground());
        itemTV.setTextColor(isSelected ? getSelectedTextColor() : getUnSelectedTextColor());
    }

    private void setBackground(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            itemTV.setBackground(drawable);
        } else {
            //noinspection deprecation
            itemTV.setBackgroundDrawable(drawable);
        }
    }
}
