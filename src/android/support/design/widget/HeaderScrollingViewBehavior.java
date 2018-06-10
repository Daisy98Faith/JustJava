// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.util.AttributeSet;
import android.view.View;
import java.util.List;

// Referenced classes of package android.support.design.widget:
//            ViewOffsetBehavior, MathUtils, CoordinatorLayout

abstract class HeaderScrollingViewBehavior extends ViewOffsetBehavior
{

    private int mOverlayTop;
    final Rect mTempRect1;
    final Rect mTempRect2;
    private int mVerticalLayoutGap;

    public HeaderScrollingViewBehavior()
    {
        mTempRect1 = new Rect();
        mTempRect2 = new Rect();
        mVerticalLayoutGap = 0;
    }

    public HeaderScrollingViewBehavior(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mTempRect1 = new Rect();
        mTempRect2 = new Rect();
        mVerticalLayoutGap = 0;
    }

    private static int resolveGravity(int i)
    {
        int j = i;
        if (i == 0)
        {
            j = 0x800033;
        }
        return j;
    }

    abstract View findFirstDependency(List list);

    final int getOverlapPixelsForOffset(View view)
    {
        if (mOverlayTop == 0)
        {
            return 0;
        } else
        {
            return MathUtils.constrain((int)(getOverlapRatioForOffset(view) * (float)mOverlayTop), 0, mOverlayTop);
        }
    }

    float getOverlapRatioForOffset(View view)
    {
        return 1.0F;
    }

    public final int getOverlayTop()
    {
        return mOverlayTop;
    }

    int getScrollRange(View view)
    {
        return view.getMeasuredHeight();
    }

    final int getVerticalLayoutGap()
    {
        return mVerticalLayoutGap;
    }

    protected void layoutChild(CoordinatorLayout coordinatorlayout, View view, int i)
    {
        View view1 = findFirstDependency(coordinatorlayout.getDependencies(view));
        if (view1 != null)
        {
            CoordinatorLayout.LayoutParams layoutparams = (CoordinatorLayout.LayoutParams)view.getLayoutParams();
            Rect rect = mTempRect1;
            rect.set(coordinatorlayout.getPaddingLeft() + layoutparams.leftMargin, view1.getBottom() + layoutparams.topMargin, coordinatorlayout.getWidth() - coordinatorlayout.getPaddingRight() - layoutparams.rightMargin, (coordinatorlayout.getHeight() + view1.getBottom()) - coordinatorlayout.getPaddingBottom() - layoutparams.bottomMargin);
            WindowInsetsCompat windowinsetscompat = coordinatorlayout.getLastWindowInsets();
            if (windowinsetscompat != null && ViewCompat.getFitsSystemWindows(coordinatorlayout) && !ViewCompat.getFitsSystemWindows(view))
            {
                rect.left = rect.left + windowinsetscompat.getSystemWindowInsetLeft();
                rect.right = rect.right - windowinsetscompat.getSystemWindowInsetRight();
            }
            coordinatorlayout = mTempRect2;
            GravityCompat.apply(resolveGravity(layoutparams.gravity), view.getMeasuredWidth(), view.getMeasuredHeight(), rect, coordinatorlayout, i);
            i = getOverlapPixelsForOffset(view1);
            view.layout(((Rect) (coordinatorlayout)).left, ((Rect) (coordinatorlayout)).top - i, ((Rect) (coordinatorlayout)).right, ((Rect) (coordinatorlayout)).bottom - i);
            mVerticalLayoutGap = ((Rect) (coordinatorlayout)).top - view1.getBottom();
            return;
        } else
        {
            super.layoutChild(coordinatorlayout, view, i);
            mVerticalLayoutGap = 0;
            return;
        }
    }

    public boolean onMeasureChild(CoordinatorLayout coordinatorlayout, View view, int i, int j, int k, int l)
    {
        int j1 = view.getLayoutParams().height;
        if (j1 == -1 || j1 == -2)
        {
            View view1 = findFirstDependency(coordinatorlayout.getDependencies(view));
            if (view1 != null)
            {
                if (ViewCompat.getFitsSystemWindows(view1) && !ViewCompat.getFitsSystemWindows(view))
                {
                    ViewCompat.setFitsSystemWindows(view, true);
                    if (ViewCompat.getFitsSystemWindows(view))
                    {
                        view.requestLayout();
                        return true;
                    }
                }
                int i1 = android.view.View.MeasureSpec.getSize(k);
                k = i1;
                if (i1 == 0)
                {
                    k = coordinatorlayout.getHeight();
                }
                int k1 = view1.getMeasuredHeight();
                int l1 = getScrollRange(view1);
                if (j1 == -1)
                {
                    i1 = 0x40000000;
                } else
                {
                    i1 = 0x80000000;
                }
                coordinatorlayout.onMeasureChild(view, i, j, android.view.View.MeasureSpec.makeMeasureSpec((k - k1) + l1, i1), l);
                return true;
            }
        }
        return false;
    }

    public final void setOverlayTop(int i)
    {
        mOverlayTop = i;
    }
}
