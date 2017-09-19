package segmented_control.widget.custom.android.com.segmentedcontrolexample;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column.SegmentViewHolder;

/**
 * Created by Robert Apikyan on 9/7/2017.
 */

public class AppSegmentViewHolder extends SegmentViewHolder<String> {
    TextView textView;

    public AppSegmentViewHolder(@NonNull View sectionView) {
        super(sectionView);
        textView = (TextView) sectionView.findViewById(R.id.text_view);
    }

    @Override
    protected void onSegmentBind(String segmentData) {
        textView.setText(segmentData);
    }
}
