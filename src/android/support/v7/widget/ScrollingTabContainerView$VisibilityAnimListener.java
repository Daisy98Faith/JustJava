// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.ViewPropertyAnimator;

// Referenced classes of package android.support.v7.widget:
//            ScrollingTabContainerView

protected class mCanceled extends AnimatorListenerAdapter
{

    private boolean mCanceled;
    private int mFinalVisibility;
    final ScrollingTabContainerView this$0;

    public void onAnimationCancel(Animator animator)
    {
        mCanceled = true;
    }

    public void onAnimationEnd(Animator animator)
    {
        if (mCanceled)
        {
            return;
        } else
        {
            mVisibilityAnim = null;
            setVisibility(mFinalVisibility);
            return;
        }
    }

    public void onAnimationStart(Animator animator)
    {
        setVisibility(0);
        mCanceled = false;
    }

    public mCanceled withFinalVisibility(ViewPropertyAnimator viewpropertyanimator, int i)
    {
        mFinalVisibility = i;
        mVisibilityAnim = viewpropertyanimator;
        return this;
    }

    protected ()
    {
        this$0 = ScrollingTabContainerView.this;
        super();
        mCanceled = false;
    }
}
