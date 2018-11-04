package segmented_control.widget.custom.android.com.segmentedcontrol.custom_segment;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import segmented_control.widget.custom.android.com.segmented_control.R;
import segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column.SegmentViewHolder;

import static segmented_control.widget.custom.android.com.segmentedcontrol.utils.Utils.createBackgroundAnimation;
import static segmented_control.widget.custom.android.com.segmentedcontrol.utils.Utils.createRadius;
import static segmented_control.widget.custom.android.com.segmentedcontrol.utils.Utils.defineRadiusForPosition;
import static segmented_control.widget.custom.android.com.segmentedcontrol.utils.Utils.getBackground;
import static segmented_control.widget.custom.android.com.segmentedcontrol.utils.Utils.isInViewBounds;

/**
 * Created by Robert Apikyan on 9/8/2017.
 */

public class SegmentViewHolderImpl extends SegmentViewHolder<CharSequence> {
    private static final int ANIM_DURATION = 300;
    private TextView itemTV;
    private float[] radius;
    private ValueAnimator va;

    private final ValueAnimator.AnimatorUpdateListener bgAnimListener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            int colorArgb = (int) animation.getAnimatedValue();
            Drawable bg = getBackground(getStrokeWidth(), getUnSelectedStrokeColor(), colorArgb, radius);
            setBackground(bg);
        }
    };

    @SuppressWarnings("FieldCanBeLocal")
    @SuppressLint("ClickableViewAccessibility")
    private final View.OnTouchListener segmentTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (isSelected()) {
                return false;
            }
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                setBackground(getFocusedBackground());

            } else if (event.getAction() == MotionEvent.ACTION_UP ||
                    event.getAction() == MotionEvent.ACTION_CANCEL ||
                    event.getAction() == MotionEvent.ACTION_POINTER_UP ||
                    event.getAction() == MotionEvent.ACTION_OUTSIDE) {

                if (!isInViewBounds(event.getX(), event.getY(), getSectionView())) {
                    setBackground(isSelected() ? getSelectedBackground() : getUnSelectedBackground());
                }
            }
            return false;
        }
    };

    @SuppressLint("ClickableViewAccessibility")
    public SegmentViewHolderImpl(@NonNull View sectionView) {
        super(sectionView);
        itemTV = sectionView.findViewById(R.id.item_segment_tv);
        sectionView.setOnTouchListener(segmentTouchListener);
    }

    @Override
    protected void onSegmentBind(CharSequence segmentData) {
        itemTV.setText(segmentData);
        if (isRadiusForEverySegment()) {
            radius = createRadius(getTopLeftRadius(), getTopRightRadius(), getBottomRightRadius(), getBottomLeftRadius());
        } else {
            radius = defineRadiusForPosition(getAbsolutePosition(), getColumnCount(), getCurrentSize(), getTopLeftRadius(), getTopRightRadius(), getBottomRightRadius(), getBottomLeftRadius());
        }
        setSectionDecorationSelected(false, false);
        itemTV.setTextSize(TypedValue.COMPLEX_UNIT_PX, getTextSize());
        if (getTypeFace() != null) {
            itemTV.setTypeface(getTypeFace());
        }
        itemTV.setPadding(getTextHorizontalPadding(), getTextVerticalPadding(), getTextHorizontalPadding(), getTextVerticalPadding());
        ViewGroup.MarginLayoutParams.class.cast(itemTV.getLayoutParams()).setMargins(getSegmentHorizontalMargin(), getSegmentVerticalMargin(), getSegmentHorizontalMargin(), getSegmentVerticalMargin());
    }

    @Override
    public void onSegmentSelected(boolean isSelected, boolean isReselected) {
        super.onSegmentSelected(isSelected, isReselected);
        setSectionDecorationSelected(isSelected, isReselected);
    }

    private Drawable getSelectedBackground() {
        return getBackground(getStrokeWidth(), getSelectedStrokeColor(), getSelectBackgroundColor(), radius);
    }

    private Drawable getUnSelectedBackground() {
        return getBackground(getStrokeWidth(), getUnSelectedStrokeColor(), getUnSelectedBackgroundColor(), radius);
    }

    private Drawable getFocusedBackground() {
        return getBackground(getStrokeWidth(), isSelected() ? getSelectedStrokeColor() : getUnSelectedStrokeColor(), getFocusedBackgroundColor(), radius);
    }

    private void setSectionDecorationSelected(boolean isSelected, boolean isReselected) {
        if (isReselected)
            return;

        if (hasBackground()) {
            animateNewBackground(isSelected);
        } else {
            setBackground(isSelected ? getSelectedBackground() : getUnSelectedBackground());
        }

        itemTV.setTextColor(isSelected ? getSelectedTextColor() : getUnSelectedTextColor());
    }

    private void animateNewBackground(boolean isSelected) {

        if (va != null) {
            va.end();
            va.removeUpdateListener(bgAnimListener);
        }

        // animate
        int startColor = isSelected ? getFocusedBackgroundColor() : getSelectBackgroundColor();
        int endColor = isSelected ? getSelectBackgroundColor() : getUnSelectedBackgroundColor();

        va = createBackgroundAnimation(startColor, endColor);

        va.addUpdateListener(bgAnimListener);

        va.setDuration(ANIM_DURATION);

        va.start();
    }

    private void setBackground(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            itemTV.setBackground(drawable);
        } else {
            //noinspection deprecation
            itemTV.setBackgroundDrawable(drawable);
        }
    }

    private boolean hasBackground() {
        return itemTV.getBackground() != null;
    }
}
