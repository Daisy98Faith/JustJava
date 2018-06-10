// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.FrameLayout;

// Referenced classes of package android.support.design.widget:
//            BaseTransientBottomBar

static class setClickable extends FrameLayout
{

    private Listener mOnAttachStateChangeListener;
    private ner mOnLayoutChangeListener;

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        if (mOnAttachStateChangeListener != null)
        {
            mOnAttachStateChangeListener.onViewAttachedToWindow(this);
        }
        ViewCompat.requestApplyInsets(this);
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if (mOnAttachStateChangeListener != null)
        {
            mOnAttachStateChangeListener.onViewDetachedFromWindow(this);
        }
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        if (mOnLayoutChangeListener != null)
        {
            mOnLayoutChangeListener.onLayoutChange(this, i, j, k, l);
        }
    }

    void setOnAttachStateChangeListener(Listener listener)
    {
        mOnAttachStateChangeListener = listener;
    }

    void setOnLayoutChangeListener(ner ner)
    {
        mOnLayoutChangeListener = ner;
    }

    ner(Context context)
    {
        this(context, null);
    }

    <init>(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        context = context.obtainStyledAttributes(attributeset, android.support.design.Layout);
        if (context.hasValue(android.support.design.Layout))
        {
            ViewCompat.setElevation(this, context.getDimensionPixelSize(android.support.design.Layout, 0));
        }
        context.recycle();
        setClickable(true);
    }
}
