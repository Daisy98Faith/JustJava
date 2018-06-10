// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import java.util.List;

// Referenced classes of package android.support.design.widget:
//            HeaderScrollingViewBehavior, AppBarLayout, CoordinatorLayout

public static class ehavior_overlapTop extends HeaderScrollingViewBehavior
{

    private static int getAppBarLayoutOffset(AppBarLayout appbarlayout)
    {
        appbarlayout = ((ehavior_overlapTop)appbarlayout.getLayoutParams()).ehavior();
        if (appbarlayout instanceof ehavior)
        {
            return ((ehavior)appbarlayout).ffsetForScrollingSibling();
        } else
        {
            return 0;
        }
    }

    private void offsetChildAsNeeded(CoordinatorLayout coordinatorlayout, View view, View view1)
    {
        coordinatorlayout = ((ffsetForScrollingSibling)view1.getLayoutParams()).ehavior();
        if (coordinatorlayout instanceof ehavior)
        {
            coordinatorlayout = (ehavior)coordinatorlayout;
            ViewCompat.offsetTopAndBottom(view, ((view1.getBottom() - view.getTop()) + ehavior(coordinatorlayout) + getVerticalLayoutGap()) - getOverlapPixelsForOffset(view1));
        }
    }

    AppBarLayout findFirstDependency(List list)
    {
        int i = 0;
        for (int j = list.size(); i < j; i++)
        {
            View view = (View)list.get(i);
            if (view instanceof AppBarLayout)
            {
                return (AppBarLayout)view;
            }
        }

        return null;
    }

    volatile View findFirstDependency(List list)
    {
        return findFirstDependency(list);
    }

    public volatile int getLeftAndRightOffset()
    {
        return super.getLeftAndRightOffset();
    }

    float getOverlapRatioForOffset(View view)
    {
        if (view instanceof AppBarLayout)
        {
            view = (AppBarLayout)view;
            int j = view.getTotalScrollRange();
            int k = view.getDownNestedPreScrollRange();
            int i = getAppBarLayoutOffset(view);
            if (k == 0 || j + i > k)
            {
                if ((j -= k) != 0)
                {
                    return 1.0F + (float)i / (float)j;
                }
            }
        }
        return 0.0F;
    }

    int getScrollRange(View view)
    {
        if (view instanceof AppBarLayout)
        {
            return ((AppBarLayout)view).getTotalScrollRange();
        } else
        {
            return super.getScrollRange(view);
        }
    }

    public volatile int getTopAndBottomOffset()
    {
        return super.getTopAndBottomOffset();
    }

    public boolean layoutDependsOn(CoordinatorLayout coordinatorlayout, View view, View view1)
    {
        return view1 instanceof AppBarLayout;
    }

    public boolean onDependentViewChanged(CoordinatorLayout coordinatorlayout, View view, View view1)
    {
        offsetChildAsNeeded(coordinatorlayout, view, view1);
        return false;
    }

    public volatile boolean onLayoutChild(CoordinatorLayout coordinatorlayout, View view, int i)
    {
        return super.onLayoutChild(coordinatorlayout, view, i);
    }

    public volatile boolean onMeasureChild(CoordinatorLayout coordinatorlayout, View view, int i, int j, int k, int l)
    {
        return super.onMeasureChild(coordinatorlayout, view, i, j, k, l);
    }

    public boolean onRequestChildRectangleOnScreen(CoordinatorLayout coordinatorlayout, View view, Rect rect, boolean flag)
    {
        AppBarLayout appbarlayout = findFirstDependency(coordinatorlayout.getDependencies(view));
        if (appbarlayout != null)
        {
            rect.offset(view.getLeft(), view.getTop());
            view = mTempRect1;
            view.set(0, 0, coordinatorlayout.getWidth(), coordinatorlayout.getHeight());
            if (!view.contains(rect))
            {
                if (!flag)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                appbarlayout.setExpanded(false, flag);
                return true;
            }
        }
        return false;
    }

    public volatile boolean setLeftAndRightOffset(int i)
    {
        return super.setLeftAndRightOffset(i);
    }

    public volatile boolean setTopAndBottomOffset(int i)
    {
        return super.setTopAndBottomOffset(i);
    }

    public ()
    {
    }

    public (Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        context = context.obtainStyledAttributes(attributeset, android.support.design.ayout);
        setOverlayTop(context.getDimensionPixelSize(android.support.design.ayout_behavior_overlapTop, 0));
        context.recycle();
    }
}
