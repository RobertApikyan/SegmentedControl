package segmented_control.widget.custom.android.com.segmentedcontrol.item_row;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;

import section_layout.widget.custom.android.com.sectionlayout.SectionLayout;
import section_layout.widget.custom.android.com.sectionlayout.distributive_section_layout.DistributiveSectionLayout;
import segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column.SegmentAdapter;
import segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column.SegmentData;

/**
 * Created by Robert Apikyan on 9/7/2017.
 */

public class SegmentRowViewHolder<D> extends SectionLayout.ViewHolder<Boolean> {
    private final DistributiveSectionLayout<SegmentData<D>> distributiveSectionLayout;

    SegmentRowViewHolder(@NonNull View sectionView, SegmentAdapter segmentAdapter) {
        super(sectionView);
        //noinspection unchecked
        distributiveSectionLayout = (DistributiveSectionLayout<SegmentData<D>>) sectionView;
        distributiveSectionLayout.setOrientation(LinearLayout.HORIZONTAL);
        distributiveSectionLayout.withAdapter(segmentAdapter);
    }

    @Override
    protected void onBind(Boolean willDistributeEvenly) {
        distributiveSectionLayout.distributeEvenly(willDistributeEvenly);
    }

    public DistributiveSectionLayout<SegmentData<D>> getDistributiveSectionLayout() {
        return distributiveSectionLayout;
    }
}
