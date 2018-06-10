// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.OverScroller;

public final class ScrollerCompat
{

    OverScroller mScroller;

    ScrollerCompat(Context context, Interpolator interpolator)
    {
        if (interpolator != null)
        {
            context = new OverScroller(context, interpolator);
        } else
        {
            context = new OverScroller(context);
        }
        mScroller = context;
    }

    public static ScrollerCompat create(Context context)
    {
        return create(context, null);
    }

    public static ScrollerCompat create(Context context, Interpolator interpolator)
    {
        return new ScrollerCompat(context, interpolator);
    }

    public void abortAnimation()
    {
        mScroller.abortAnimation();
    }

    public boolean computeScrollOffset()
    {
        return mScroller.computeScrollOffset();
    }

    public void fling(int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1)
    {
        mScroller.fling(i, j, k, l, i1, j1, k1, l1);
    }

    public void fling(int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1, int i2, int j2)
    {
        mScroller.fling(i, j, k, l, i1, j1, k1, l1, i2, j2);
    }

    public float getCurrVelocity()
    {
        return mScroller.getCurrVelocity();
    }

    public int getCurrX()
    {
        return mScroller.getCurrX();
    }

    public int getCurrY()
    {
        return mScroller.getCurrY();
    }

    public int getFinalX()
    {
        return mScroller.getFinalX();
    }

    public int getFinalY()
    {
        return mScroller.getFinalY();
    }

    public boolean isFinished()
    {
        return mScroller.isFinished();
    }

    public boolean isOverScrolled()
    {
        return mScroller.isOverScrolled();
    }

    public void notifyHorizontalEdgeReached(int i, int j, int k)
    {
        mScroller.notifyHorizontalEdgeReached(i, j, k);
    }

    public void notifyVerticalEdgeReached(int i, int j, int k)
    {
        mScroller.notifyVerticalEdgeReached(i, j, k);
    }

    public boolean springBack(int i, int j, int k, int l, int i1, int j1)
    {
        return mScroller.springBack(i, j, k, l, i1, j1);
    }

    public void startScroll(int i, int j, int k, int l)
    {
        mScroller.startScroll(i, j, k, l);
    }

    public void startScroll(int i, int j, int k, int l, int i1)
    {
        mScroller.startScroll(i, j, k, l, i1);
    }
}
