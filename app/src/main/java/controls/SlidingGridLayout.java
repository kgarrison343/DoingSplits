package controls;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridLayout;

/*
 * Created by Kyle Garrison on 2/24/2018.
 */

public class SlidingGridLayout extends GridLayout {

    private boolean isDescended;

    public SlidingGridLayout(Context context) {
        super(context);
        isDescended = false;
    }

    public SlidingGridLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        isDescended = false;
    }

    public SlidingGridLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        isDescended = false;
    }

    @Override
    protected void onAnimationEnd(){
        super.onAnimationEnd();
        this.clearAnimation();

        final int slideOffset = isDescended ? -this.getHeight() : this.getHeight();
        this.setY(this.getY() + slideOffset);

        isDescended = !isDescended;

        this.setDrawingCacheEnabled(false);
    }

    public boolean getIsDescended(){
        return isDescended;
    }
}