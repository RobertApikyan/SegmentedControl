package segmented_control.widget.custom.android.com.segmentedcontrol;

import java.util.ArrayList;
import java.util.List;

import segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column.SegmentViewHolder;
import segmented_control.widget.custom.android.com.segmentedcontrol.listeners.OnSegmentClickListener;
import segmented_control.widget.custom.android.com.segmentedcontrol.listeners.OnSegmentSelectRequestListener;
import segmented_control.widget.custom.android.com.segmentedcontrol.listeners.OnSegmentSelectedListener;

import static segmented_control.widget.custom.android.com.segmentedcontrol.utils.Utils.lazy;


/**
 * Created by Robert Apikyan on 9/8/2017.
 */

class Notifier<D> implements OnSegmentClickListener<D>, OnSegmentSelectedListener<D>, OnSegmentSelectRequestListener<D> {
    private List<OnSegmentClickListener<D>> onSegmentClickListeners;
    private List<OnSegmentSelectedListener<D>> onSegmentSelectedListeners;
    @SuppressWarnings("unchecked")
    private OnSegmentSelectRequestListener<D> onSegmentSelectRequestListener;

    @Override
    public void onSegmentClick(final SegmentViewHolder<D> segmentViewHolder) {
        onEvent(onSegmentClickListeners, new Consumer<OnSegmentClickListener<D>>() {
            @Override
            public void apply(OnSegmentClickListener<D> onSegmentClickListener) {
                onSegmentClickListener.onSegmentClick(segmentViewHolder);
            }
        });
    }

    @Override
    public void onSegmentSelected(final SegmentViewHolder<D> segmentViewHolder, final boolean isSelected, final boolean isReselected) {
        onEvent(onSegmentSelectedListeners, new Consumer<OnSegmentSelectedListener<D>>() {
            @Override
            public void apply(OnSegmentSelectedListener<D> onSegmentSelectedListener) {
                onSegmentSelectedListener.onSegmentSelected(segmentViewHolder, isSelected, isReselected);
            }
        });
    }

    @Override
    public boolean onSegmentSelectRequest(final SegmentViewHolder<D> segmentViewHolder) {
        return onSegmentSelectRequestListener == null || onSegmentSelectRequestListener.onSegmentSelectRequest(segmentViewHolder);
    }

    void addOnSegmentClickListener(OnSegmentClickListener<D> onSegmentClickListener) {
        onSegmentClickListeners = lazy(onSegmentClickListeners, new ArrayList<OnSegmentClickListener<D>>());
        onSegmentClickListeners.add(onSegmentClickListener);
    }

    void removeOnSegmentClickListener(OnSegmentClickListener<D> onSegmentClickListener) {
        onSegmentClickListeners = lazy(onSegmentClickListeners, new ArrayList<OnSegmentClickListener<D>>());
        onSegmentClickListeners.remove(onSegmentClickListener);
    }

    void addOnSegmentSelectListener(OnSegmentSelectedListener<D> onSegmentSelectedListener) {
        onSegmentSelectedListeners = lazy(onSegmentSelectedListeners, new ArrayList<OnSegmentSelectedListener<D>>());
        onSegmentSelectedListeners.add(onSegmentSelectedListener);
    }

    void removeOnSegmentSelectListener(OnSegmentSelectedListener<D> onSegmentSelectedListener) {
        onSegmentSelectedListeners = lazy(onSegmentSelectedListeners, new ArrayList<OnSegmentSelectedListener<D>>());
        onSegmentSelectedListeners.remove(onSegmentSelectedListener);
    }

    void setOnSegmentSelectRequestListener(OnSegmentSelectRequestListener<D> onSegmentSelectRequestListener) {
        this.onSegmentSelectRequestListener = onSegmentSelectRequestListener;
    }


    private <T> void onEvent(List<T> eventListeners, Consumer<T> listenerConsumer) {
        if (eventListeners != null && eventListeners.size() != 0) {
            for (T t : eventListeners) {
                listenerConsumer.apply(t);
            }
        }
    }
}
