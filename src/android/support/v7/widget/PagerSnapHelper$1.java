// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;

// Referenced classes of package android.support.v7.widget:
//            LinearSmoothScroller, PagerSnapHelper, RecyclerView

class er extends LinearSmoothScroller
{

    final PagerSnapHelper this$0;

    protected float calculateSpeedPerPixel(DisplayMetrics displaymetrics)
    {
        return 100F / (float)displaymetrics.densityDpi;
    }

    protected int calculateTimeForScrolling(int i)
    {
        return Math.min(100, super.calculateTimeForScrolling(i));
    }

    protected void onTargetFound(View view,  , hScroller.Action action)
    {
        view = calculateDistanceToFinalSnap(mRecyclerView.getLayoutManager(), view);
        int i = view[0];
        int j = view[1];
        int k = calculateTimeForDeceleration(Math.max(Math.abs(i), Math.abs(j)));
        if (k > 0)
        {
            action.update(i, j, k, mDecelerateInterpolator);
        }
    }

    hScroller.Action(Context context)
    {
        this$0 = PagerSnapHelper.this;
        super(context);
    }
}
