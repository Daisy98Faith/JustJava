// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;

// Referenced classes of package android.support.v7.widget:
//            FastScroller

private class <init> extends AnimatorListenerAdapter
{

    private boolean mCanceled;
    final FastScroller this$0;

    public void onAnimationCancel(Animator animator)
    {
        mCanceled = true;
    }

    public void onAnimationEnd(Animator animator)
    {
        if (mCanceled)
        {
            mCanceled = false;
            return;
        }
        if (((Float)FastScroller.access$200(FastScroller.this).getAnimatedValue()).floatValue() == 0.0F)
        {
            FastScroller.access$302(FastScroller.this, 0);
            FastScroller.access$400(FastScroller.this, 0);
            return;
        } else
        {
            FastScroller.access$302(FastScroller.this, 2);
            FastScroller.access$500(FastScroller.this);
            return;
        }
    }

    private ()
    {
        this$0 = FastScroller.this;
        super();
        mCanceled = false;
    }

    mCanceled(mCanceled mcanceled)
    {
        this();
    }
}
