// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

// Referenced classes of package android.support.design.widget:
//            Snackbar

public static final class nackbarBaseLayout extends nackbarBaseLayout
{

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        j = getChildCount();
        int k = getMeasuredWidth();
        int l = getPaddingLeft();
        int i1 = getPaddingRight();
        for (i = 0; i < j; i++)
        {
            View view = getChildAt(i);
            if (view.getLayoutParams().idth == -1)
            {
                view.measure(android.view.sureSpec(k - l - i1, 0x40000000), android.view.sureSpec(view.getMeasuredHeight(), 0x40000000));
            }
        }

    }

    public nackbarBaseLayout(Context context)
    {
        super(context);
    }

    public nackbarBaseLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }
}
