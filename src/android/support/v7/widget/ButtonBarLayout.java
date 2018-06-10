// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;

public class ButtonBarLayout extends LinearLayout
{

    private static final int ALLOW_STACKING_MIN_HEIGHT_DP = 320;
    private static final int PEEK_BUTTON_DP = 16;
    private boolean mAllowStacking;
    private int mLastWidthSize;

    public ButtonBarLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mLastWidthSize = -1;
        boolean flag;
        if (getResources().getConfiguration().screenHeightDp >= 320)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        context = context.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.ButtonBarLayout);
        mAllowStacking = context.getBoolean(android.support.v7.appcompat.R.styleable.ButtonBarLayout_allowStacking, flag);
        context.recycle();
    }

    private int getNextVisibleChildIndex(int i)
    {
        for (int j = getChildCount(); i < j; i++)
        {
            if (getChildAt(i).getVisibility() == 0)
            {
                return i;
            }
        }

        return -1;
    }

    private boolean isStacked()
    {
        return getOrientation() == 1;
    }

    private void setStacked(boolean flag)
    {
        View view;
        int i;
        if (flag)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        setOrientation(i);
        if (flag)
        {
            i = 5;
        } else
        {
            i = 80;
        }
        setGravity(i);
        view = findViewById(android.support.v7.appcompat.R.id.spacer);
        if (view != null)
        {
            if (flag)
            {
                i = 8;
            } else
            {
                i = 4;
            }
            view.setVisibility(i);
        }
        for (i = getChildCount() - 2; i >= 0; i--)
        {
            bringChildToFront(getChildAt(i));
        }

    }

    protected void onMeasure(int i, int j)
    {
        int l = android.view.View.MeasureSpec.getSize(i);
        if (mAllowStacking)
        {
            if (l > mLastWidthSize && isStacked())
            {
                setStacked(false);
            }
            mLastWidthSize = l;
        }
        int k = 0;
        boolean flag;
        if (!isStacked() && android.view.View.MeasureSpec.getMode(i) == 0x40000000)
        {
            l = android.view.View.MeasureSpec.makeMeasureSpec(l, 0x80000000);
            k = 1;
        } else
        {
            l = i;
        }
        super.onMeasure(l, j);
        flag = k;
        if (mAllowStacking)
        {
            flag = k;
            if (!isStacked())
            {
                View view;
                android.widget.LinearLayout.LayoutParams layoutparams;
                if ((getMeasuredWidthAndState() & 0xff000000) == 0x1000000)
                {
                    l = 1;
                } else
                {
                    l = 0;
                }
                flag = k;
                if (l != 0)
                {
                    setStacked(true);
                    flag = true;
                }
            }
        }
        if (flag)
        {
            super.onMeasure(i, j);
        }
        i = 0;
        k = getNextVisibleChildIndex(0);
        if (k >= 0)
        {
            view = getChildAt(k);
            layoutparams = (android.widget.LinearLayout.LayoutParams)view.getLayoutParams();
            j = 0 + (getPaddingTop() + view.getMeasuredHeight() + layoutparams.topMargin + layoutparams.bottomMargin);
            if (isStacked())
            {
                k = getNextVisibleChildIndex(k + 1);
                i = j;
                if (k >= 0)
                {
                    i = (int)((float)j + ((float)getChildAt(k).getPaddingTop() + 16F * getResources().getDisplayMetrics().density));
                }
            } else
            {
                i = j + getPaddingBottom();
            }
        }
        if (ViewCompat.getMinimumHeight(this) != i)
        {
            setMinimumHeight(i);
        }
    }

    public void setAllowStacking(boolean flag)
    {
        if (mAllowStacking != flag)
        {
            mAllowStacking = flag;
            if (!mAllowStacking && getOrientation() == 1)
            {
                setStacked(false);
            }
            requestLayout();
        }
    }
}
