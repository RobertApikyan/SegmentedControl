package segmented_control.widget.custom.android.com.segmentedcontrol;

import android.graphics.Typeface;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import section_layout.widget.custom.android.com.sectionlayout.SectionLayout;
import section_layout.widget.custom.android.com.sectionlayout.distributive_section_layout.DistributiveSectionLayout;
import segmented_control.widget.custom.android.com.segmentedcontrol.item_row.SegmentRowAdapter;
import segmented_control.widget.custom.android.com.segmentedcontrol.item_row.SegmentRowViewHolder;
import segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column.SegmentAdapter;
import segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column.SegmentData;
import segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column.SegmentDecoration;
import segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column.SegmentViewHolder;
import segmented_control.widget.custom.android.com.segmentedcontrol.listeners.OnSegmentClickListener;
import segmented_control.widget.custom.android.com.segmentedcontrol.listeners.OnSegmentSelectRequestListener;
import segmented_control.widget.custom.android.com.segmentedcontrol.listeners.OnSegmentSelectedListener;
import view_component.lib_android.com.view_component.base_view.ControllerComponent;

/**
 * Created by Robert Apikyan on 9/5/2017.
 */

class SegmentedControlControllerComponent<D> extends ControllerComponent<SegmentedControlViewComponent<D>> {
    private final Configs configs = Configs.getDefault();
    //    private SegmentViewHolder<D> lastClickedSegmentViewHolder;
    private LinkedList<SegmentViewHolder<D>> selectedSegments = new LinkedList<>();
    private final Notifier<D> notifier = new Notifier<>();
    private final List<D> dataList = new ArrayList<>();

//    private final OnSegmentClickListener<D> _onSegmentClickListener = new OnSegmentClickListener<D>() {
//        @Override
//        public void onSegmentClick(SegmentViewHolder<D> segmentViewHolder) {
//            notifier.onSegmentClick(segmentViewHolder);
//
//            if (segmentViewHolder.equals(lastClickedSegmentViewHolder)) {
//                // on section reselected
//                segmentViewHolder.setSelected(true);
//                notifier.onSegmentSelected(segmentViewHolder, true, true);
//            } else if (notifier.onSegmentSelectRequest(segmentViewHolder)) {
//                // on section selected
//                // unSelect the last one
//                if (lastClickedSegmentViewHolder != null) {
//                    lastClickedSegmentViewHolder.setSelected(false);
//                    notifier.onSegmentSelected(lastClickedSegmentViewHolder, false, false);
//                }
//                // select the current
//                lastClickedSegmentViewHolder = segmentViewHolder;
//                segmentViewHolder.setSelected(true);
//                notifier.onSegmentSelected(segmentViewHolder, true, false);
//            }
//        }
//    };

    private final OnSegmentClickListener<D> onSegmentClickListener = new OnSegmentClickListener<D>() {
        @Override
        public void onSegmentClick(SegmentViewHolder<D> segmentViewHolder) {
            notifier.onSegmentClick(segmentViewHolder);

            int index = selectedSegments.indexOf(segmentViewHolder);
            boolean contains = index != -1;
            if (contains) {
                // on section reselected
                SegmentViewHolder<D> viewHolder = selectedSegments.get(index);
                viewHolder.setSelected(true);
                notifier.onSegmentSelected(segmentViewHolder, true, true);
            } else if (notifier.onSegmentSelectRequest(segmentViewHolder)) {
                // on section selected
                // unSelect the last one
                SegmentViewHolder<D> lastSelected = addSelectedSegmentViewHolder(segmentViewHolder);
                if (lastSelected != null) {
                    lastSelected.setSelected(false);
                    notifier.onSegmentSelected(lastSelected, false, false);
                }
                // select the current
                segmentViewHolder.setSelected(true);
                notifier.onSegmentSelected(segmentViewHolder, true, false);
            }
        }
    };

    /**
     * Watch the supported selections count, if limit is reached the oldest segment will be removed from
     * selections list and returned back to method caller
     *
     * @param segmentViewHolder new selected segment;
     * @return oldest selection
     */
    @Nullable
    private SegmentViewHolder<D> addSelectedSegmentViewHolder(SegmentViewHolder<D> segmentViewHolder) {
        selectedSegments.add(segmentViewHolder);
        if (selectedSegments.size() > configs.supportedSelectionsCount) {
            return selectedSegments.remove(0);
        }
        return null;
    }

    private SegmentViewHolder<D> getLastSelectedViewHolder() {
        return selectedSegments.size() > 0 ? selectedSegments.getLast() : null;
    }

    private void addSegment(D segmentData) {
        if (getVerticalSectionLayout().size() == 0 || !canAddToLastRow()) {
            addNewRow();
        }

        addSegmentToLastRow(segmentData);
    }

    // removeLastSelected = true
//    private void removeAllSegments(boolean removeLastSelected) {
//        for (int row = 0; row < getVerticalSectionLayout().size(); row++) {
//            getHorizontalSectionLayout(row).removeAllSections();
//        }
//        getVerticalSectionLayout().removeAllSections();
//        dataList.clear();
//
//        if (removeLastSelected) {
//            lastClickedSegmentViewHolder = null;
//        }
//    }

    private void removeAllSegments(boolean removeLastSelected) {
        for (int row = 0; row < getVerticalSectionLayout().size(); row++) {
            getHorizontalSectionLayout(row).removeAllSections();
        }
        getVerticalSectionLayout().removeAllSections();
        dataList.clear();

        if (removeLastSelected) {
            selectedSegments.clear();
        }
    }

    private void addSegmentToLastRow(D segmentData) {
        DistributiveSectionLayout<SegmentData<D>> horizontalSectionLayout = getHorizontalSectionLayout(getLastRowIndex());
        horizontalSectionLayout
                .addSection(SegmentData.create(segmentData, onSegmentClickListener, getAbsolutePosition(getLastHorizontalSectionLayout().size(),
                        getVerticalSectionLayout().size() - 1), getLastRowIndex(), horizontalSectionLayout.size(), size(), configs.columnCount, configs.segmentDecoration));
        horizontalSectionLayout.requestLayout();
    }

    private int[] getRowAndColumnWithAbsolutePosition(int position) {
        int smallDiff = position % configs.columnCount;
        int diff = position - smallDiff;
        int row = (diff / configs.columnCount) + (smallDiff == configs.columnCount ? 1 : 0);
        int column = position % configs.columnCount;
        return new int[]{row, column};
    }

    void notifyConfigIsChanged() {
        recreate(false);
    }

//    public void clearSelection(boolean notifySegmentSelectedListener) {
//        if (lastClickedSegmentViewHolder != null) {
//            lastClickedSegmentViewHolder.setSelected(false);
//
//            if (notifySegmentSelectedListener) {
//                notifier.onSegmentSelected(lastClickedSegmentViewHolder, false, false);
//            }
//
//            lastClickedSegmentViewHolder = null;
//        }
//    }

    public void clearSelection(boolean notifySegmentSelectedListener) {
        if (!selectedSegments.isEmpty()) {
            for (SegmentViewHolder<D> selectedSegment : selectedSegments) {
                selectedSegment.setSelected(false);

                if (notifySegmentSelectedListener) {
                    notifier.onSegmentSelected(selectedSegment, false, false);
                }
            }

            selectedSegments.clear();
        }
    }

//    private void recreate(boolean removeLastSelected) {
//        if (dataList.size() == 0) return;
//        List<D> itemsData = new ArrayList<>(dataList);
//        removeAllSegments(removeLastSelected);
//        addSegments(itemsData);
//        if (lastClickedSegmentViewHolder != null) {
//            setSelectedSegment(lastClickedSegmentViewHolder.getAbsolutePosition());
//        }
//    }

    private void recreate(boolean removeLastSelected) {
        if (dataList.size() == 0) return;
        List<D> itemsData = new ArrayList<>(dataList);
        removeAllSegments(removeLastSelected);
        addSegments(itemsData);

        SegmentViewHolder<D> lastSelection = getLastSelectedViewHolder();
        if (lastSelection != null) {
            setSelectedSegment(lastSelection.getAbsolutePosition());
        }
    }

    private boolean canAddToLastRow() {
        return getHorizontalSectionLayout(getLastRowIndex()).size() < configs.columnCount;
    }

    private void addNewRow() {
        //noinspection unchecked
        getVerticalSectionLayout().addSection(configs.willDistributeEvenly);
    }

    private SectionLayout getVerticalSectionLayout() {
        //noinspection ConstantConditions
        return getViewComponent().verticalSectionLayout;
    }

    private DistributiveSectionLayout<SegmentData<D>> getHorizontalSectionLayout(int row) {
        //noinspection unchecked
        SegmentRowViewHolder<D> segmentedViewHolder = (SegmentRowViewHolder<D>) getVerticalSectionLayout().getViewHolderForAdapterPosition(row);
        return segmentedViewHolder.getDistributiveSectionLayout();
    }

    private DistributiveSectionLayout<SegmentData<D>> getLastHorizontalSectionLayout() {
        return getHorizontalSectionLayout(getLastRowIndex());
    }

    private int getLastRowIndex() {
        return getVerticalSectionLayout().size() - 1;
    }

    SegmentViewHolder<D> findSegmentByAbsolutePosition(int position) {
        int[] point = getRowAndColumnWithAbsolutePosition(position);
        return findSegmentByColumnAndRow(point[0], point[1]);
    }

    SegmentViewHolder<D> findSegmentByColumnAndRow(int column, int row) {
        return (SegmentViewHolder<D>) getHorizontalSectionLayout(column).getViewHolderForAdapterPosition(row);
    }

    void forEachSegment(SegmentConsumer<D> segmentConsumer) {
        for (int row = 0; row < getVerticalSectionLayout().size(); row++) {
            DistributiveSectionLayout<SegmentData<D>> horizontalSectionLayout = getHorizontalSectionLayout(row);
            for (int column = 0; column < horizontalSectionLayout.size(); column++) {
                segmentConsumer.apply(findSegmentByColumnAndRow(row, column));
            }
        }
    }

    void setAccentColor(int color) {
        configs.segmentDecoration = SegmentDecoration.createDefault(getContext(), color);
    }

    void setSelectedStrokeColor(int color) {
        configs.segmentDecoration.selectedStrokeColor = color;
    }

    void setUnSelectedStrokeColor(int color) {
        configs.segmentDecoration.unSelectedStrokeColor = color;
    }

    void setStrokeWidth(int width) {
        configs.segmentDecoration.strokeWidth = width;
    }

    void setSelectedBackgroundColor(int color) {
        configs.segmentDecoration.selectBackgroundColor = color;
    }

    void setUnSelectedBackgroundColor(int color) {
        configs.segmentDecoration.unSelectedBackgroundColor = color;
    }

    void setFocusedBackgroundColor(int color) {
        configs.segmentDecoration.focusedBackgroundColor = color;
    }

    void setSelectionAnimationDuration(int duration) {
        configs.segmentDecoration.selectionAnimationDuration = duration;
    }

    void setSelectedTextColor(int color) {
        configs.segmentDecoration.selectedTextColor = color;
    }

    void setUnSelectedTextColor(int color) {
        configs.segmentDecoration.unSelectedTextColor = color;
    }

    void setTextSize(int textSize) {
        configs.segmentDecoration.textSize = textSize;
    }

    void setTypeFace(Typeface typeFace) {
        configs.segmentDecoration.typeface = typeFace;
    }

    void setTextVerticalPadding(int padding) {
        configs.segmentDecoration.textVerticalPadding = padding;
    }

    void setTextHorizontalPadding(int padding) {
        configs.segmentDecoration.textHorizontalPadding = padding;
    }

    void setSegmentVerticalMargin(int margin) {
        configs.segmentDecoration.segmentVerticalMargin = margin;
    }

    void setSegmentHorizontalMargin(int margin) {
        configs.segmentDecoration.segmentHorizontalMargin = margin;
    }

    void setRadius(int radius) {
        setTopLeftRadius(radius);
        setTopRightRadius(radius);
        setBottomRightRadius(radius);
        setBottomLeftRadius(radius);
    }

    void setTopLeftRadius(int radius) {
        configs.segmentDecoration.topLeftRadius = radius;
    }

    void setTopRightRadius(int radius) {
        configs.segmentDecoration.topRightRadius = radius;
    }

    void setBottomRightRadius(int radius) {
        configs.segmentDecoration.bottomRightRadius = radius;
    }

    void setBottomLeftRadius(int radius) {
        configs.segmentDecoration.bottomLeftRadius = radius;
    }

    void setRadiusForEverySegment(boolean radiusForEverySegment) {
        configs.segmentDecoration.radiusForEverySegment = radiusForEverySegment;
    }

    void setAdapter(SegmentAdapter adapter) {
        //noinspection ConstantConditions, setAdapter will be called from SegmentedControl
        getViewComponent().verticalSectionLayout.withAdapter(new SegmentRowAdapter(adapter));
    }

    void addSegments(D[] segmentDataArray) {
        if (segmentDataArray == null || segmentDataArray.length == 0) return;
        addSegments(new ArrayList<>(Arrays.asList(segmentDataArray)));
    }

    void addSegments(List<D> segmentDataList) {
        if (segmentDataList == null || segmentDataList.size() == 0) return;

        dataList.addAll(new ArrayList<>(segmentDataList));

        for (D segmentData : dataList) {
            addSegment(segmentData);
        }
    }

    void removeAllSegments() {
        removeAllSegments(true);
    }

    void setDistributeEvenly(boolean willDistributeEvenly) {
        configs.willDistributeEvenly = willDistributeEvenly;
    }

    void setSupportedSelectionsCount(int supportedSelectionsCount) {
        configs.supportedSelectionsCount = supportedSelectionsCount;
    }

    void setColumnCount(int columnCount) {
        configs.columnCount = columnCount;
    }

    void addOnSegmentClickListener(OnSegmentClickListener<D> onSegmentClickListener) {
        notifier.addOnSegmentClickListener(onSegmentClickListener);
    }

    void removeOnSegmentClickListener(OnSegmentClickListener<D> onSegmentClickListener) {
        notifier.removeOnSegmentClickListener(onSegmentClickListener);
    }

    void addOnSegmentSelectListener(OnSegmentSelectedListener<D> onSegmentSelectedListener) {
        notifier.addOnSegmentSelectListener(onSegmentSelectedListener);
    }

    void removeOnSegmentSelectListener(OnSegmentSelectedListener<D> onSegmentSelectedListener) {
        notifier.removeOnSegmentSelectListener(onSegmentSelectedListener);
    }

    void setOnSegmentSelectRequestListener(OnSegmentSelectRequestListener<D> onSegmentSelectRequestListener) {
        notifier.setOnSegmentSelectRequestListener(onSegmentSelectRequestListener);
    }

    void setSelectedSegment(int absolutePosition) {
        int[] point = getRowAndColumnWithAbsolutePosition(absolutePosition);
        setSelectedSegment(point[0], point[1]);
    }

    void setSelectedSegment(int column, int row) {
        onSegmentClickListener.onSegmentClick(findSegmentByColumnAndRow(column, row));
    }

//    SegmentViewHolder<D> getSelectedViewHolder() {
//        return lastClickedSegmentViewHolder;
//    }

    List<SegmentViewHolder<D>> getSelectedViewHolders() {
        return selectedSegments;
    }

    int[] getLastSelectedColumnAndRow() {
        SegmentViewHolder<D> lastSelection = getLastSelectedViewHolder();
        if (lastSelection != null) {
            return new int[]{lastSelection.getColumn(), lastSelection.getRow()};
        } else {
            return new int[]{-1, -1};
        }
    }

    //
    int getLastSelectedAbsolutePosition() {
        SegmentViewHolder<D> lastSelection = getLastSelectedViewHolder();
        if (lastSelection != null) {
            return lastSelection.getAbsolutePosition();
        } else {
            return -1;
        }
    }

//    boolean isSelected() {
//        return lastClickedSegmentViewHolder != null;
//    }

    boolean isSelected() {
        return !selectedSegments.isEmpty();
    }

    int size() {
        return dataList.size();
    }

    int getAbsolutePosition(int column, int row) {
        return row * configs.columnCount + column;
    }
}
