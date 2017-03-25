package mytwistedidea.wordpress.com.prayukti;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by imCyrus on 08-02-2017.
 */

public class UnscrollableListView extends ListView {
    public UnscrollableListView(Context context) {
        super(context);
    }

    public UnscrollableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UnscrollableListView(Context context, AttributeSet attrs,
                                int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int maxHeightSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, maxHeightSpec);
    }
}
