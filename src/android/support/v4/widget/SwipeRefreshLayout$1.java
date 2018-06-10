// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.view.animation.Animation;

// Referenced classes of package android.support.v4.widget:
//            SwipeRefreshLayout, MaterialProgressDrawable, CircleImageView

class this._cls0
    implements android.view.animation.stener
{

    final SwipeRefreshLayout this$0;

    public void onAnimationEnd(Animation animation)
    {
        if (mRefreshing)
        {
            mProgress.setAlpha(255);
            mProgress.start();
            if (mNotify && mListener != null)
            {
                mListener.onRefresh();
            }
            mCurrentTargetOffsetTop = mCircleView.getTop();
            return;
        } else
        {
            reset();
            return;
        }
    }

    public void onAnimationRepeat(Animation animation)
    {
    }

    public void onAnimationStart(Animation animation)
    {
    }

    RefreshListener()
    {
        this$0 = SwipeRefreshLayout.this;
        super();
    }
}
