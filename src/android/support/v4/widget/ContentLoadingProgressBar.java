// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class ContentLoadingProgressBar extends ProgressBar
{

    private static final int MIN_DELAY = 500;
    private static final int MIN_SHOW_TIME = 500;
    private final Runnable mDelayedHide;
    private final Runnable mDelayedShow;
    boolean mDismissed;
    boolean mPostedHide;
    boolean mPostedShow;
    long mStartTime;

    public ContentLoadingProgressBar(Context context)
    {
        this(context, null);
    }

    public ContentLoadingProgressBar(Context context, AttributeSet attributeset)
    {
        super(context, attributeset, 0);
        mStartTime = -1L;
        mPostedHide = false;
        mPostedShow = false;
        mDismissed = false;
        mDelayedHide = new Runnable() {

            final ContentLoadingProgressBar this$0;

            public void run()
            {
                mPostedHide = false;
                mStartTime = -1L;
                setVisibility(8);
            }

            
            {
                this$0 = ContentLoadingProgressBar.this;
                super();
            }
        };
        mDelayedShow = new Runnable() {

            final ContentLoadingProgressBar this$0;

            public void run()
            {
                mPostedShow = false;
                if (!mDismissed)
                {
                    mStartTime = System.currentTimeMillis();
                    setVisibility(0);
                }
            }

            
            {
                this$0 = ContentLoadingProgressBar.this;
                super();
            }
        };
    }

    private void removeCallbacks()
    {
        removeCallbacks(mDelayedHide);
        removeCallbacks(mDelayedShow);
    }

    public void hide()
    {
        mDismissed = true;
        removeCallbacks(mDelayedShow);
        long l = System.currentTimeMillis() - mStartTime;
        if (l >= 500L || mStartTime == -1L)
        {
            setVisibility(8);
        } else
        if (!mPostedHide)
        {
            postDelayed(mDelayedHide, 500L - l);
            mPostedHide = true;
            return;
        }
    }

    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        removeCallbacks();
    }

    public void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        removeCallbacks();
    }

    public void show()
    {
        mStartTime = -1L;
        mDismissed = false;
        removeCallbacks(mDelayedHide);
        if (!mPostedShow)
        {
            postDelayed(mDelayedShow, 500L);
            mPostedShow = true;
        }
    }
}
