// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.internal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class BaselineLayout extends ViewGroup
{

    private int mBaseline;

    public BaselineLayout(Context context)
    {
        super(context, null, 0);
        mBaseline = -1;
    }

    public BaselineLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset, 0);
        mBaseline = -1;
    }

    public BaselineLayout(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mBaseline = -1;
    }

    public int getBaseline()
    {
        return mBaseline;
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        int j1 = getChildCount();
        int k1 = getPaddingLeft();
        int l1 = getPaddingRight();
        int i1 = getPaddingTop();
        j = 0;
        while (j < j1) 
        {
            View view = getChildAt(j);
            if (view.getVisibility() != 8)
            {
                int i2 = view.getMeasuredWidth();
                int j2 = view.getMeasuredHeight();
                int k2 = k1 + (k - i - l1 - k1 - i2) / 2;
                if (mBaseline != -1 && view.getBaseline() != -1)
                {
                    l = (mBaseline + i1) - view.getBaseline();
                } else
                {
                    l = i1;
                }
                view.layout(k2, l, k2 + i2, l + j2);
            }
            j++;
        }
    }

    protected void onMeasure(int i, int j)
    {
        int l2 = getChildCount();
        int k2 = 0;
        int k = 0;
        int l = -1;
        int i1 = -1;
        int i2 = 0;
        int j2 = 0;
        while (j2 < l2) 
        {
            View view = getChildAt(j2);
            if (view.getVisibility() != 8)
            {
                measureChild(view, i, j);
                int i3 = view.getBaseline();
                int l1 = l;
                int j1 = i1;
                if (i3 != -1)
                {
                    l1 = Math.max(l, i3);
                    j1 = Math.max(i1, view.getMeasuredHeight() - i3);
                }
                k2 = Math.max(k2, view.getMeasuredWidth());
                k = Math.max(k, view.getMeasuredHeight());
                i2 = View.combineMeasuredStates(i2, view.getMeasuredState());
                l = l1;
                i1 = j1;
            }
            j2++;
        }
        int k1 = k;
        if (l != -1)
        {
            k1 = Math.max(k, l + Math.max(i1, getPaddingBottom()));
            mBaseline = l;
        }
        k = Math.max(k1, getSuggestedMinimumHeight());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(k2, getSuggestedMinimumWidth()), i, i2), View.resolveSizeAndState(k, j, i2 << 16));
    }
}
