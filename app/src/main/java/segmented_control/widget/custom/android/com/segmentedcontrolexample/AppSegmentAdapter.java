package segmented_control.widget.custom.android.com.segmentedcontrolexample;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column.SegmentAdapter;

/**
 * Created by Robert Apikyan on 9/7/2017.
 */

public class AppSegmentAdapter extends SegmentAdapter<String, AppSegmentViewHolder> {

    @NonNull
    @Override
    protected AppSegmentViewHolder onCreateViewHolder(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
        return new AppSegmentViewHolder(layoutInflater.inflate(R.layout.item_segment, null));
    }
}
