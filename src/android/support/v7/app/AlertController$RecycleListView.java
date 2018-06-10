// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ListView;

// Referenced classes of package android.support.v7.app:
//            AlertController

public static class w_paddingTopNoTitle extends ListView
{

    private final int mPaddingBottomNoButtons;
    private final int mPaddingTopNoTitle;

    public void setHasDecor(boolean flag, boolean flag1)
    {
        if (!flag1 || !flag)
        {
            int k = getPaddingLeft();
            int i;
            int j;
            int l;
            if (flag)
            {
                i = getPaddingTop();
            } else
            {
                i = mPaddingTopNoTitle;
            }
            l = getPaddingRight();
            if (flag1)
            {
                j = getPaddingBottom();
            } else
            {
                j = mPaddingBottomNoButtons;
            }
            setPadding(k, i, l, j);
        }
    }

    public (Context context)
    {
        this(context, null);
    }

    public <init>(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        context = context.obtainStyledAttributes(attributeset, android.support.v7.appcompat.);
        mPaddingBottomNoButtons = context.getDimensionPixelOffset(android.support.v7.appcompat.ingBottomNoButtons, -1);
        mPaddingTopNoTitle = context.getDimensionPixelOffset(android.support.v7.appcompat.ingTopNoTitle, -1);
    }
}
