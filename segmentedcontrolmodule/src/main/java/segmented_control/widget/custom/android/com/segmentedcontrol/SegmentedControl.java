package segmented_control.widget.custom.android.com.segmentedcontrol;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;

import java.util.List;

import section_layout.widget.custom.android.com.sectionlayout.SectionLayout;
import segmented_control.widget.custom.android.com.segmented_control.R;
import segmented_control.widget.custom.android.com.segmentedcontrol.custom_segment.SegmentAdapterImpl;
import segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column.SegmentAdapter;
import segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column.SegmentViewHolder;
import segmented_control.widget.custom.android.com.segmentedcontrol.listeners.OnSegmentClickListener;
import segmented_control.widget.custom.android.com.segmentedcontrol.listeners.OnSegmentSelectRequestListener;
import segmented_control.widget.custom.android.com.segmentedcontrol.listeners.OnSegmentSelectedListener;
import view_component.lib_android.com.view_component.base_view.layouts.ComponentFrameLayout;

/**
 * Created by Robert Apikyan on 8/18/2017.
 * /*     Attributes
 * <attr name="distributeEvenly" format="boolean" /> {@link #setDistributeEvenly(boolean)}
 * <attr name="columnCount" format="integer" /> {@link #setColumnCount(int)}
 * <attr name="segments" format="reference" /> {@link #addSegments(Object[])} {@link #addSegments(List)}
 * <attr name="selectedStrokeColor" format="color" /> {@link #setSelectedStrokeColor(int)}
 * <attr name="unSelectedStrokeColor" format="color" /> {@link #setUnSelectedStrokeColor(int)}
 * <attr name="strokeWidth" format="dimension" />{@link #setStrokeWidth(int)}
 * <attr name="selectedBackgroundColor" format="color" /> {@link #setSelectedBackgroundColor(int)}
 * <attr name="unSelectedBackgroundColor" format="color" /> {@link #setUnSelectedBackgroundColor(int)}
 * <attr name="selectedTextColor" format="color"/> {@link #setSelectedTextColor(int)}
 * <attr name="unSelectedTextColor" format="color"/> {@link #setUnSelectedTextColor(int)}
 * <attr name="textSize" format="dimension"/> {@link #setTextSize(int)}
 * <attr name="textHorizontalPadding" format="dimension"/> {@link #setTextHorizontalPadding(int)}
 * <attr name="textVerticalPadding" format="dimension"/> {@link #setTextVerticalPadding(int)}
 * <attr name="segmentVerticalMargin" format="dimension"/> {@link #setSegmentVerticalMargin(int)}
 * <attr name="segmentHorizontalMargin" format="dimension"/> {@link #setSegmentHorizontalMargin(int)}
 * <attr name="radius" format="dimension"/> {@link #setRadius(int)}
 * <attr name="topLeftRadius" format="dimension"/> {@link #setTopLeftRadius(int)}
 * <attr name="topRightRadius" format="dimension"/> {@link #setTopRightRadius(int)}
 * <attr name="bottomRightRadius" format="dimension"/> {@link #setBottomRightRadius(int)}
 * <attr name="bottomLeftRadius" format="dimension"/> {@link #setBottomLeftRadius(int)}
 * <attr name="radiusForEverySegment" format="boolean"/> {@link #setRadiusForEverySegment(boolean)}
 *
 * @param <D>
 */
public class SegmentedControl<D> extends ComponentFrameLayout<SegmentedControlViewComponent<D>, SegmentedControlControllerComponent<D>> {
    public SegmentedControl(Context context) {
        this(context, null);
    }

    public SegmentedControl(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SegmentedControl(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAttr(attrs, defStyle);
    }

    private void initAttr(AttributeSet attrs, int defStyle) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SegmentedControl, defStyle, 0);
        fetchAccentColor();
        try {
            attrDistributeEvenly(typedArray);
            attrColumnCount(typedArray);
            attrSelectedStrokeColor(typedArray);
            attrUnSelectedStrokeColor(typedArray);
            attrStrokeWidth(typedArray);
            attrSelectedBackgroundColor(typedArray);
            attrFocusedBackgroundColor(typedArray);
            attrUnSelectedBackgroundColor(typedArray);
            attrSelectedTextColor(typedArray);
            attrUnSelectedTextColor(typedArray);
            attrTextSize(typedArray);
            attrFontAssetPath(typedArray);
            attrTextVerticalPadding(typedArray);
            attrTextHorizontalPadding(typedArray);
            attrSegmentHorizontalMargin(typedArray);
            attrSegmentVerticalMargin(typedArray);
            attrTopLeftRadius(typedArray);
            attrTopRightRadius(typedArray);
            attrBottomRightRadius(typedArray);
            attrBottomLeftRadius(typedArray);
            attrRadius(typedArray);
            attrRadiusForEverySegment(typedArray);
            attrSegments(typedArray);
        } finally {
            typedArray.recycle();
        }
    }

    private void fetchAccentColor() {
        TypedValue tv = new TypedValue();
        TypedArray typedArray = getContext().obtainStyledAttributes(tv.data, new int[]{R.attr.colorAccent});
        try {
            getControllerComponent().setAccentColor(typedArray.getColor(0, 0));
        } finally {
            typedArray.recycle();
        }
    }

    private void attrSelectedStrokeColor(TypedArray typedArray) {
        obtainColorAttr(typedArray, R.styleable.SegmentedControl_selectedStrokeColor, new Consumer<Integer>() {
            @Override
            public void apply(Integer color) {
                getControllerComponent().setSelectedStrokeColor(color);
            }
        });
    }

    private void attrUnSelectedStrokeColor(TypedArray typedArray) {
        obtainColorAttr(typedArray, R.styleable.SegmentedControl_unSelectedStrokeColor, new Consumer<Integer>() {
            @Override
            public void apply(Integer color) {
                getControllerComponent().setUnSelectedStrokeColor(color);
            }
        });
    }

    private void attrStrokeWidth(TypedArray typedArray) {
        obtainDimensionAttr(typedArray, R.styleable.SegmentedControl_strokeWidth, new Consumer<Integer>() {
            @Override
            public void apply(Integer width) {
                getControllerComponent().setStrokeWidth(width);
            }
        });
    }

    private void attrSelectedBackgroundColor(TypedArray typedArray) {
        obtainColorAttr(typedArray, R.styleable.SegmentedControl_selectedBackgroundColor, new Consumer<Integer>() {
            @Override
            public void apply(Integer color) {
                getControllerComponent().setSelectedBackgroundColor(color);
            }
        });
    }

    private void attrUnSelectedBackgroundColor(TypedArray typedArray) {
        obtainColorAttr(typedArray, R.styleable.SegmentedControl_unSelectedBackgroundColor, new Consumer<Integer>() {
            @Override
            public void apply(Integer color) {
                getControllerComponent().setUnSelectedBackgroundColor(color);
            }
        });
    }

    private void attrFocusedBackgroundColor(TypedArray typedArray) {
        obtainColorAttr(typedArray, R.styleable.SegmentedControl_focusedBackgroundColor, new Consumer<Integer>() {
            @Override
            public void apply(Integer color) {
                getControllerComponent().setUnSelectedBackgroundColor(color);
            }
        });
    }

    private void attrSelectedTextColor(TypedArray typedArray) {
        obtainColorAttr(typedArray, R.styleable.SegmentedControl_selectedTextColor, new Consumer<Integer>() {
            @Override
            public void apply(Integer color) {
                getControllerComponent().setSelectedTextColor(color);
            }
        });
    }

    private void attrUnSelectedTextColor(TypedArray typedArray) {
        obtainColorAttr(typedArray, R.styleable.SegmentedControl_unSelectedTextColor, new Consumer<Integer>() {
            @Override
            public void apply(Integer color) {
                getControllerComponent().setUnSelectedTextColor(color);
            }
        });
    }

    private void attrTextSize(TypedArray typedArray) {
        int textSize = typedArray.getDimensionPixelSize(R.styleable.SegmentedControl_textSize, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, getResources().getDisplayMetrics()));
        if (textSize > 0) {
            getControllerComponent().setTextSize(textSize);
        }
    }


    private void attrFontAssetPath(TypedArray typedArray) {
        String fontPath = typedArray.getString(R.styleable.SegmentedControl_fontAssetPath);
        if (fontPath !=null && !fontPath.isEmpty()) {
            getControllerComponent().setTypeFace(Typeface.createFromAsset(getContext().getAssets(),fontPath));
        }
    }

    private void attrTextVerticalPadding(TypedArray typedArray) {
        obtainDimensionAttr(typedArray, R.styleable.SegmentedControl_textVerticalPadding, new Consumer<Integer>() {
            @Override
            public void apply(Integer dimen) {
                getControllerComponent().setTextVerticalPadding(dimen);
            }
        });
    }

    private void attrTextHorizontalPadding(TypedArray typedArray) {
        obtainDimensionAttr(typedArray, R.styleable.SegmentedControl_textHorizontalPadding, new Consumer<Integer>() {
            @Override
            public void apply(Integer dimen) {
                getControllerComponent().setTextHorizontalPadding(dimen);
            }
        });
    }

    private void attrSegmentVerticalMargin(TypedArray typedArray) {
        obtainDimensionAttr(typedArray, R.styleable.SegmentedControl_segmentVerticalMargin, new Consumer<Integer>() {
            @Override
            public void apply(Integer dimen) {
                getControllerComponent().setSegmentVerticalMargin(dimen);
            }
        });
    }

    private void attrSegmentHorizontalMargin(TypedArray typedArray) {
        obtainDimensionAttr(typedArray, R.styleable.SegmentedControl_segmentHorizontalMargin, new Consumer<Integer>() {
            @Override
            public void apply(Integer dimen) {
                getControllerComponent().setSegmentHorizontalMargin(dimen);
            }
        });
    }

    private void attrRadius(TypedArray typedArray) {
        obtainDimensionAttr(typedArray, R.styleable.SegmentedControl_radius, new Consumer<Integer>() {
            @Override
            public void apply(Integer dimen) {
                getControllerComponent().setRadius(dimen);
            }
        });
    }

    private void attrTopLeftRadius(TypedArray typedArray) {
        obtainDimensionAttr(typedArray, R.styleable.SegmentedControl_topLeftRadius, new Consumer<Integer>() {
            @Override
            public void apply(Integer dimen) {
                getControllerComponent().setTopLeftRadius(dimen);
            }
        });
    }

    private void attrTopRightRadius(TypedArray typedArray) {
        obtainDimensionAttr(typedArray, R.styleable.SegmentedControl_topRightRadius, new Consumer<Integer>() {
            @Override
            public void apply(Integer dimen) {
                getControllerComponent().setTopRightRadius(dimen);
            }
        });
    }

    private void attrBottomRightRadius(TypedArray typedArray) {
        obtainDimensionAttr(typedArray, R.styleable.SegmentedControl_bottomRightRadius, new Consumer<Integer>() {
            @Override
            public void apply(Integer dimen) {
                getControllerComponent().setBottomRightRadius(dimen);
            }
        });
    }

    private void attrBottomLeftRadius(TypedArray typedArray) {
        obtainDimensionAttr(typedArray, R.styleable.SegmentedControl_bottomLeftRadius, new Consumer<Integer>() {
            @Override
            public void apply(Integer dimen) {
                getControllerComponent().setBottomLeftRadius(dimen);
            }
        });
    }

    private void attrRadiusForEverySegment(TypedArray typedArray) {
        boolean radiusForEverySegment = typedArray.getBoolean(R.styleable.SegmentedControl_radiusForEverySegment, false);
        getControllerComponent().setRadiusForEverySegment(radiusForEverySegment);
    }

    private void attrSegments(TypedArray typedArray) {
        CharSequence[] items = typedArray.getTextArray(R.styleable.SegmentedControl_segments);
        useDefaultAdapter();
        //noinspection unchecked
        getControllerComponent().addSegments((D[]) items);
    }

    private void attrColumnCount(TypedArray typedArray) {
        int columnCount = typedArray.getInteger(R.styleable.SegmentedControl_columnCount, Configs.DEFAULT_COLUMN_COUNT);
        getControllerComponent().setColumnCount(columnCount);
        notifyConfigIsChanged();
    }

    private void attrDistributeEvenly(TypedArray typedArray) {
        boolean willDistribute = typedArray.getBoolean(R.styleable.SegmentedControl_distributeEvenly, false);
        getControllerComponent().setDistributeEvenly(willDistribute);
        getControllerComponent().notifyConfigIsChanged();
    }

    private void obtainColorAttr(TypedArray typedArray, int attr, Consumer<Integer> colorConsumer) {
        int color = typedArray.getColor(attr, -1);
        if (color != -1) {
            colorConsumer.apply(color);
        }
    }

    private void obtainDimensionAttr(TypedArray typedArray, int attr, Consumer<Integer> dimensionConsumer) {
        int dimensionPixelSize = typedArray.getDimensionPixelSize(attr, -1);
        if (dimensionPixelSize != -1) {
            dimensionConsumer.apply(dimensionPixelSize);
        }
    }

    @NonNull
    @Override
    public SegmentedControlViewComponent<D> createViewComponent(@NonNull LayoutInflater layoutInflater) {
        addView(new SectionLayout<D>(getContext()), 0);
        return new SegmentedControlViewComponent<>(this);
    }

    @NonNull
    @Override
    public SegmentedControlControllerComponent<D> createControllerComponent() {
        return new SegmentedControlControllerComponent<>();
    }

    /**
     * @param columnCount The column count for SegmentedControl
     */
    public void setColumnCount(int columnCount) {
        Assert.columnCount(columnCount);
        getControllerComponent().setColumnCount(columnCount);
    }

    /**
     * @param adapter, NonNull adapter (extended from {@link SegmentAdapter})
     */
    public void setAdapter(@NonNull SegmentAdapter adapter) {
        Assert.adapter(adapter);
        getControllerComponent().setAdapter(adapter);
    }

    /**
     * set the adapter {@link SegmentAdapter} with {@link segmented_control.widget.custom.android.com.segmentedcontrol.custom_segment.SegmentViewHolderImpl} view holder
     * where data D data type is String. use segmentedControl.addSegments(String[] segmentDataArray) or segmentedControl.addSegments(List<String> segmentDataList) methods
     */
    public void useDefaultAdapter() {
        setAdapter(new SegmentAdapterImpl());
    }

    /**
     * Add segments to segmentController
     *
     * @param segmentData, specified array Data type
     */
    public void addSegments(D[] segmentData) {
        getControllerComponent().addSegments(segmentData);
    }

    /**
     * Add segments to segmentController
     *
     * @param segmentData, specified list Data type
     */
    public void addSegments(List<D> segmentData) {
        getControllerComponent().addSegments(segmentData);
    }

    /**
     * @param willDistributeEvenly, true each section width with will be equal to each other,
     *                              false each section width will be measured depending on its content width;
     */
    public void setDistributeEvenly(boolean willDistributeEvenly) {
        getControllerComponent().setDistributeEvenly(willDistributeEvenly);
    }

    /**
     * @param onSegmentClickListener, every time click event will be notified,
     *                                even if the segment is already selected
     */
    public void addOnSegmentClickListener(OnSegmentClickListener<D> onSegmentClickListener) {
        getControllerComponent().addOnSegmentClickListener(onSegmentClickListener);
    }

    /**
     * @param onSegmentSelectedListener, event will be notified, when segment is selected and unSelected and reselected,
     *                                   for more info check out {@link OnSegmentSelectedListener} class
     */
    public void addOnSegmentSelectListener(OnSegmentSelectedListener<D> onSegmentSelectedListener) {
        getControllerComponent().addOnSegmentSelectListener(onSegmentSelectedListener);
    }

    /**
     * @param onSegmentSelectRequestListener, event will be triggered after click event and before selection event
     *                                        for more info click out {@link OnSegmentSelectRequestListener} class
     */
    public void setOnSegmentSelectRequestListener(OnSegmentSelectRequestListener<D> onSegmentSelectRequestListener) {
        getControllerComponent().setOnSegmentSelectRequestListener(onSegmentSelectRequestListener);
    }

    /**
     * Removes all segments
     */
    public void removeAllSegments() {
        getControllerComponent().removeAllSegments();
    }

    /**
     * find the segment view holder with the segment absolute position
     *
     * @param position, the segment position
     * @return SegmentViewHolder instance for specified position
     */
    public SegmentViewHolder<D> findSegmentByAbsolutePosition(int position) {
        Assert.outOfBounds(position, size(), "SegmentedControl#findSegmentByAbsolutePosition");
        return getControllerComponent().findSegmentByAbsolutePosition(position);
    }

    /**
     * find the segment with column number and row position
     *
     * @param column, Segment column number
     * @param row,    Segment row position
     * @return SegmentViewHolder instance for specified column and row numbers
     */
    public SegmentViewHolder<D> findSegmentByColumnAndRow(int column, int row) {
        Assert.outOfBounds(getControllerComponent().getAbsolutePosition(column, row), size(), "SegmentedControl#setSelectedSegment");
        return getControllerComponent().findSegmentByColumnAndRow(column, row);
    }

    /**
     * Select the selected position
     *
     * @param position, Segment position
     */
    public void setSelectedSegment(int position) {
        Assert.outOfBounds(position, size(), "SegmentedControl#setSelectedSegment");
        getControllerComponent().setSelectedSegment(position);
    }

    /**
     * Select the segment with specified column number and row position
     *
     * @param column, Segment column number
     * @param row,    Segment row position
     */
    public void setSelectedSegment(int column, int row) {
        Assert.outOfBounds(getControllerComponent().getAbsolutePosition(column, row), size(), "SegmentedControl#setSelectedSegment");
        getControllerComponent().setSelectedSegment(column, row);
    }

    /**
     * Iterate on segments, and pass the view holders as an argument for {@link SegmentConsumer}
     *
     * @param segmentConsumer, Segment consumer
     */
    public void forEachSegment(SegmentConsumer<D> segmentConsumer) {
        getControllerComponent().forEachSegment(segmentConsumer);
    }

    public void setSelectedStrokeColor(int color) {
        getControllerComponent().setSelectedStrokeColor(color);
    }

    public void setUnSelectedStrokeColor(int color) {
        getControllerComponent().setUnSelectedStrokeColor(color);
    }

    public void setStrokeWidth(int width) {
        getControllerComponent().setStrokeWidth(width);
    }

    public void setSelectedBackgroundColor(int color) {
        getControllerComponent().setSelectedBackgroundColor(color);
    }

    public void setUnSelectedBackgroundColor(int color) {
        getControllerComponent().setUnSelectedBackgroundColor(color);
    }

    public void setSelectedTextColor(int color) {
        getControllerComponent().setSelectedTextColor(color);
    }

    public void setUnSelectedTextColor(int color) {
        getControllerComponent().setUnSelectedTextColor(color);
    }

    public void setTextSize(int textSize) {
        getControllerComponent().setTextSize(textSize);
    }

    public void setTypeFace(Typeface typeFace){getControllerComponent().setTypeFace(typeFace);}

    public void setTextVerticalPadding(int padding) {
        getControllerComponent().setTextVerticalPadding(padding);
    }

    public void setTextHorizontalPadding(int padding) {
        getControllerComponent().setTextHorizontalPadding(padding);
    }

    public void setSegmentVerticalMargin(int margin) {
        getControllerComponent().setSegmentVerticalMargin(margin);
    }

    public void setSegmentHorizontalMargin(int margin) {
        getControllerComponent().setSegmentHorizontalMargin(margin);
    }

    public void setRadius(int radius) {
        getControllerComponent().setRadius(radius);
    }

    public void setTopLeftRadius(int radius) {
        getControllerComponent().setTopLeftRadius(radius);
    }

    public void setTopRightRadius(int radius) {
        getControllerComponent().setTopRightRadius(radius);
    }

    public void setBottomRightRadius(int radius) {
        getControllerComponent().setBottomRightRadius(radius);
    }

    public void setBottomLeftRadius(int radius) {
        getControllerComponent().setBottomLeftRadius(radius);
    }

    /**
     * @param radiusForEverySegment, true every segment corners will be rounded, false only top left,top right, bottom right and bottom left corners will be rounded
     */
    public void setRadiusForEverySegment(boolean radiusForEverySegment) {
        getControllerComponent().setRadiusForEverySegment(radiusForEverySegment);
    }

    /**
     * Removes the last selected segment selection, SegmentViewHolder's onSegmentSelected  method will be called
     * with isSelected = false, isReselected = false
     */
    public void clearSelection(){
        getControllerComponent().clearSelection(false);
    }

    /**
     * Removes the last selected segment selection, SegmentViewHolder's onSegmentSelected  method will be called
     * with isSelected = false, isReselected = false.
     * @param notifySegmentSelectedListener if true SegmentSelectedListeners will be notified.
     */
    public void clearSelection(boolean notifySegmentSelectedListener){
        getControllerComponent().clearSelection(notifySegmentSelectedListener);
    }

    /**
     * Call this method after every configuration change
     * setColumnCount, setRadius et...
     */
    public void notifyConfigIsChanged() {
        getControllerComponent().notifyConfigIsChanged();
    }

    public int size() {
        return getControllerComponent().size();
    }

    public SegmentViewHolder<D> getSelectedViewHolder() {
        return getControllerComponent().getSelectedViewHolder();
    }

    /**
     * @return int[]{column,row} Section column and row numbers
     */
    public int[] getSelectedColumnAndRow() {
        return getControllerComponent().getSelectedColumnAndRow();
    }

    public int getSelectedAbsolutePosition() {
        return getControllerComponent().getSelectedAbsolutePosition();
    }

    public boolean hasSelectedSegment() {
        return getControllerComponent().isSelected();
    }
}
