// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

// Referenced classes of package android.support.v7.widget:
//            LinearLayoutCompat

public class AlertDialogLayout extends LinearLayoutCompat
{

    public AlertDialogLayout(Context context)
    {
        super(context);
    }

    public AlertDialogLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    private void forceUniformWidth(int i, int j)
    {
        int l = android.view.View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0x40000000);
        for (int k = 0; k < i; k++)
        {
            View view = getChildAt(k);
            if (view.getVisibility() == 8)
            {
                continue;
            }
            LinearLayoutCompat.LayoutParams layoutparams = (LinearLayoutCompat.LayoutParams)view.getLayoutParams();
            if (layoutparams.width == -1)
            {
                int i1 = layoutparams.height;
                layoutparams.height = view.getMeasuredHeight();
                measureChildWithMargins(view, l, 0, j, 0);
                layoutparams.height = i1;
            }
        }

    }

    private static int resolveMinimumHeight(View view)
    {
        int i = ViewCompat.getMinimumHeight(view);
        if (i > 0)
        {
            return i;
        }
        if (view instanceof ViewGroup)
        {
            view = (ViewGroup)view;
            if (view.getChildCount() == 1)
            {
                return resolveMinimumHeight(view.getChildAt(0));
            }
        }
        return 0;
    }

    private void setChildFrame(View view, int i, int j, int k, int l)
    {
        view.layout(i, j, i + k, j + l);
    }

    private boolean tryOnMeasure(int i, int j)
    {
        View view2 = null;
        View view4 = null;
        View view3 = null;
        int i3 = getChildCount();
        int k = 0;
        while (k < i3) 
        {
            View view = getChildAt(k);
            if (view.getVisibility() != 8)
            {
                int l = view.getId();
                if (l == android.support.v7.appcompat.R.id.topPanel)
                {
                    view2 = view;
                } else
                if (l == android.support.v7.appcompat.R.id.buttonPanel)
                {
                    view4 = view;
                } else
                if (l == android.support.v7.appcompat.R.id.contentPanel || l == android.support.v7.appcompat.R.id.customPanel)
                {
                    if (view3 != null)
                    {
                        return false;
                    }
                    view3 = view;
                } else
                {
                    return false;
                }
            }
            k++;
        }
        int k3 = android.view.View.MeasureSpec.getMode(j);
        int k2 = android.view.View.MeasureSpec.getSize(j);
        int j3 = android.view.View.MeasureSpec.getMode(i);
        int i1 = 0;
        k = getPaddingTop() + getPaddingBottom();
        int j1 = k;
        if (view2 != null)
        {
            view2.measure(i, 0);
            j1 = k + view2.getMeasuredHeight();
            i1 = View.combineMeasuredStates(0, view2.getMeasuredState());
        }
        k = 0;
        int j2 = 0;
        int k1 = i1;
        int l1 = j1;
        if (view4 != null)
        {
            view4.measure(i, 0);
            k = resolveMinimumHeight(view4);
            j2 = view4.getMeasuredHeight() - k;
            l1 = j1 + k;
            k1 = View.combineMeasuredStates(i1, view4.getMeasuredState());
        }
        int i2 = 0;
        i1 = k1;
        j1 = l1;
        if (view3 != null)
        {
            View view1;
            int l2;
            if (k3 == 0)
            {
                i1 = 0;
            } else
            {
                i1 = android.view.View.MeasureSpec.makeMeasureSpec(Math.max(0, k2 - l1), k3);
            }
            view3.measure(i, i1);
            i2 = view3.getMeasuredHeight();
            j1 = l1 + i2;
            i1 = View.combineMeasuredStates(k1, view3.getMeasuredState());
        }
        k2 -= j1;
        k1 = i1;
        l2 = k2;
        l1 = j1;
        if (view4 != null)
        {
            j2 = Math.min(k2, j2);
            l1 = k;
            k1 = k2;
            if (j2 > 0)
            {
                k1 = k2 - j2;
                l1 = k + j2;
            }
            view4.measure(i, android.view.View.MeasureSpec.makeMeasureSpec(l1, 0x40000000));
            l1 = (j1 - k) + view4.getMeasuredHeight();
            k = View.combineMeasuredStates(i1, view4.getMeasuredState());
            l2 = k1;
            k1 = k;
        }
        i1 = k1;
        k = l1;
        if (view3 != null)
        {
            i1 = k1;
            k = l1;
            if (l2 > 0)
            {
                view3.measure(i, android.view.View.MeasureSpec.makeMeasureSpec(i2 + l2, k3));
                k = (l1 - i2) + view3.getMeasuredHeight();
                i1 = View.combineMeasuredStates(k1, view3.getMeasuredState());
            }
        }
        k1 = 0;
        for (j1 = 0; j1 < i3;)
        {
            view1 = getChildAt(j1);
            l1 = k1;
            if (view1.getVisibility() != 8)
            {
                l1 = Math.max(k1, view1.getMeasuredWidth());
            }
            j1++;
            k1 = l1;
        }

        setMeasuredDimension(View.resolveSizeAndState(k1 + (getPaddingLeft() + getPaddingRight()), i, i1), View.resolveSizeAndState(k, j, 0));
        if (j3 != 0x40000000)
        {
            forceUniformWidth(i3, j);
        }
        return true;
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        j1 = getPaddingLeft();
        k1 = k - i;
        l1 = getPaddingRight();
        i2 = getPaddingRight();
        i = getMeasuredHeight();
        j2 = getChildCount();
        k2 = getGravity();
        k2 & 0x70;
        JVM INSTR lookupswitch 2: default 72
    //                   16: 297
    //                   80: 282;
           goto _L1 _L2 _L3
_L1:
        i = getPaddingTop();
_L11:
        LinearLayoutCompat.LayoutParams layoutparams;
        int l2;
        Object obj = getDividerDrawable();
        int i1;
        int i3;
        if (obj == null)
        {
            k = 0;
        } else
        {
            k = ((Drawable) (obj)).getIntrinsicHeight();
        }
_L10:
        for (l = 0; l >= j2;)
        {
            break MISSING_BLOCK_LABEL_374;
        }

        obj = getChildAt(l);
        j = i;
        if (obj == null) goto _L5; else goto _L4
_L4:
        j = i;
        if (((View) (obj)).getVisibility() == 8) goto _L5; else goto _L6
_L6:
        l2 = ((View) (obj)).getMeasuredWidth();
        i3 = ((View) (obj)).getMeasuredHeight();
        layoutparams = (LinearLayoutCompat.LayoutParams)((View) (obj)).getLayoutParams();
        i1 = layoutparams.gravity;
        j = i1;
        if (i1 < 0)
        {
            j = k2 & 0x800007;
        }
        GravityCompat.getAbsoluteGravity(j, ViewCompat.getLayoutDirection(this)) & 7;
        JVM INSTR lookupswitch 2: default 212
    //                   1: 324
    //                   5: 356;
           goto _L7 _L8 _L9
_L7:
        j = j1 + layoutparams.leftMargin;
_L12:
        i1 = i;
        if (hasDividerBeforeChildAt(l))
        {
            i1 = i + k;
        }
        i = i1 + layoutparams.topMargin;
        setChildFrame(((View) (obj)), j, i, l2, i3);
        j = i + (layoutparams.bottomMargin + i3);
_L5:
        l++;
        i = j;
          goto _L10
_L3:
        i = (getPaddingTop() + l) - j - i;
          goto _L11
_L2:
        i = getPaddingTop() + (l - j - i) / 2;
          goto _L11
_L8:
        j = ((k1 - j1 - i2 - l2) / 2 + j1 + layoutparams.leftMargin) - layoutparams.rightMargin;
          goto _L12
_L9:
        j = k1 - l1 - l2 - layoutparams.rightMargin;
          goto _L12
          goto _L10
    }

    protected void onMeasure(int i, int j)
    {
        if (!tryOnMeasure(i, j))
        {
            super.onMeasure(i, j);
        }
    }
}
