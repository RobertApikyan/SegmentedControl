package segmented_control.widget.custom.android.com.segmentedcontrol;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;

import section_layout.widget.custom.android.com.sectionlayout.SectionLayout;
import view_component.lib_android.com.view_component.base_view.ViewComponent;

/**
 * Created by Robert Apikyan on 9/5/2017.
 */

class SegmentedControlViewComponent<D> extends ViewComponent {
    final SectionLayout<D> verticalSectionLayout;

    SegmentedControlViewComponent(@NonNull View rootView) {
        super(rootView);
        //noinspection unchecked
        verticalSectionLayout = (SectionLayout<D>) getRootViewGroup().getChildAt(0);
        verticalSectionLayout.setOrientation(LinearLayout.VERTICAL);
    }
}
