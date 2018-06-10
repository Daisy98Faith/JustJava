// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.util.Log;
import android.view.animation.Interpolator;

// Referenced classes of package android.support.v7.widget:
//            RecyclerView

public static class mInterpolator
{

    public static final int UNDEFINED_DURATION = 0x80000000;
    private boolean mChanged;
    private int mConsecutiveUpdates;
    private int mDuration;
    private int mDx;
    private int mDy;
    private Interpolator mInterpolator;
    private int mJumpToPosition;

    private void validate()
    {
        if (mInterpolator != null && mDuration < 1)
        {
            throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
        }
        if (mDuration < 1)
        {
            throw new IllegalStateException("Scroll duration must be a positive number");
        } else
        {
            return;
        }
    }

    public int getDuration()
    {
        return mDuration;
    }

    public int getDx()
    {
        return mDx;
    }

    public int getDy()
    {
        return mDy;
    }

    public Interpolator getInterpolator()
    {
        return mInterpolator;
    }

    boolean hasJumpTarget()
    {
        return mJumpToPosition >= 0;
    }

    public void jumpTo(int i)
    {
        mJumpToPosition = i;
    }

    void runIfNecessary(RecyclerView recyclerview)
    {
        if (mJumpToPosition >= 0)
        {
            int i = mJumpToPosition;
            mJumpToPosition = -1;
            recyclerview.jumpToPositionForSmoothScroller(i);
            mChanged = false;
            return;
        }
        if (mChanged)
        {
            validate();
            if (mInterpolator == null)
            {
                if (mDuration == 0x80000000)
                {
                    recyclerview.mViewFlinger.llBy(mDx, mDy);
                } else
                {
                    recyclerview.mViewFlinger.llBy(mDx, mDy, mDuration);
                }
            } else
            {
                recyclerview.mViewFlinger.llBy(mDx, mDy, mDuration, mInterpolator);
            }
            mConsecutiveUpdates = mConsecutiveUpdates + 1;
            if (mConsecutiveUpdates > 10)
            {
                Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
            }
            mChanged = false;
            return;
        } else
        {
            mConsecutiveUpdates = 0;
            return;
        }
    }

    public void setDuration(int i)
    {
        mChanged = true;
        mDuration = i;
    }

    public void setDx(int i)
    {
        mChanged = true;
        mDx = i;
    }

    public void setDy(int i)
    {
        mChanged = true;
        mDy = i;
    }

    public void setInterpolator(Interpolator interpolator)
    {
        mChanged = true;
        mInterpolator = interpolator;
    }

    public void update(int i, int j, int k, Interpolator interpolator)
    {
        mDx = i;
        mDy = j;
        mDuration = k;
        mInterpolator = interpolator;
        mChanged = true;
    }

    public (int i, int j)
    {
        this(i, j, 0x80000000, null);
    }

    public <init>(int i, int j, int k)
    {
        this(i, j, k, null);
    }

    public <init>(int i, int j, int k, Interpolator interpolator)
    {
        mJumpToPosition = -1;
        mChanged = false;
        mConsecutiveUpdates = 0;
        mDx = i;
        mDy = j;
        mDuration = k;
        mInterpolator = interpolator;
    }
}
