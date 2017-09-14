package segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column;

import android.support.annotation.NonNull;
import android.view.View;

import section_layout.widget.custom.android.com.sectionlayout.distributive_section_layout.DistributiveSectionLayout;

/**
 * Created by Robert Apikyan on 9/7/2017.
 */

public abstract class SegmentViewHolder<D> extends DistributiveSectionLayout.ViewHolder<SegmentData<D>> {
    private SegmentData<D> segmentData;

    private final View.OnClickListener onSectionViewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (segmentData != null)
                segmentData.getOnSegmentClickListener().onSegmentClick(SegmentViewHolder.this);
        }
    };

    public SegmentViewHolder(@NonNull View sectionView) {
        super(sectionView);
    }

    @Override
    protected final void onBind(SegmentData<D> segmentData) {
        this.segmentData = segmentData;
        getSectionView().setOnClickListener(onSectionViewClickListener);
        onSegmentBind(segmentData.getSegmentData());
    }

    public final void setSelected(boolean isSelected) {
        if (segmentData.isSelected() && isSelected) {
            segmentData.isSelected = true;
            onSegmentSelected(true, true);
        } else if (isSelected) {
            segmentData.isSelected = true;
            onSegmentSelected(true, false);
        } else {
            segmentData.isSelected = false;
            onSegmentSelected(false, false);
        }
    }

    /**
     * Override this method in order to define, performed action, selected, unselected, reselected
     *
     * @param isSelected,   represent selected state
     * @param isReselected, represent reselected state
     */
    public void onSegmentSelected(boolean isSelected, boolean isReselected) {

    }

    public int getAbsolutePosition() {
        return segmentData.absolutePosition;
    }

    public boolean isSelected() {
        return segmentData.isSelected;
    }

    public int getRow() {
        return segmentData.getRow();
    }

    public int getColumn() {
        return segmentData.getColumn();
    }

    public D getSegmentData() {
        return segmentData.getSegmentData();
    }

    public int getSelectedStockColor() {
        return segmentData.getSelectedStockColor();
    }

    public int getUnSelectedStockColor() {
        return segmentData.getUnSelectedStockColor();
    }

    public int getStockWidth() {
        return segmentData.getStockWidth();
    }

    public int getSelectBackgroundColor() {
        return segmentData.getSelectBackgroundColor();
    }

    public int getUnSelectedBackgroundColor() {
        return segmentData.getUnSelectedBackgroundColor();
    }

    public int getSelectedTextColor() {
        return segmentData.getSelectedTextColor();
    }

    public int getUnSelectedTextColor() {
        return segmentData.getUnSelectedTextColor();
    }

    public int getTextSize() {
        return segmentData.getTextSize();
    }

    public int getCurrentSize() {
        return segmentData.getCurrentSize();
    }

    public int getColumnCount() {
        return segmentData.getColumnCount();
    }

    public int getTextHorizontalPadding() {
        return segmentData.getTextHorizontalPadding();
    }

    public int getTextVerticalPadding() {
        return segmentData.getTextVerticalPadding();
    }

    public int getSegmentVerticalMargin() {
        return segmentData.getSegmentVerticalMargin();
    }

    public int getSegmentHorizontalMargin() {
        return segmentData.getSegmentHorizontalMargin();
    }

    public int getTopLeftRadius() {
        return segmentData.getTopLeftRadius();
    }

    public int getTopRightRadius() {
        return segmentData.getTopRightRadius();
    }

    public int getBottomRightRadius() {
        return segmentData.getBottomRightRadius();
    }

    public int getBottomLeftRadius() {
        return segmentData.getBottomLeftRadius();
    }

    public boolean isRadiusForEverySegment() {
        return segmentData.isRadiusForEverySegment();
    }

    protected abstract void onSegmentBind(D segmentData);
}
