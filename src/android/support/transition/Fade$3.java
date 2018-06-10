// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

// Referenced classes of package android.support.transition:
//            Fade, ViewGroupUtils, ViewGroupOverlayImpl

class mPausedAlpha extends AnimatorListenerAdapter
{

    boolean mCanceled;
    float mPausedAlpha;
    final Fade this$0;
    final View val$finalOverlayView;
    final ViewGroup val$finalSceneRoot;
    final View val$finalView;
    final View val$finalViewToKeep;
    final int val$finalVisibility;

    public void onAnimationCancel(Animator animator)
    {
        mCanceled = true;
        if (mPausedAlpha >= 0.0F)
        {
            val$finalView.setAlpha(mPausedAlpha);
        }
    }

    public void onAnimationEnd(Animator animator)
    {
        if (!mCanceled)
        {
            val$finalView.setAlpha(1.0F);
        }
        if (val$finalViewToKeep != null && !mCanceled)
        {
            val$finalViewToKeep.setVisibility(val$finalVisibility);
        }
        if (val$finalOverlayView != null)
        {
            ViewGroupUtils.getOverlay(val$finalSceneRoot).remove(val$finalOverlayView);
        }
    }

    upOverlayImpl()
    {
        this$0 = final_fade;
        val$finalView = view;
        val$finalViewToKeep = view1;
        val$finalVisibility = i;
        val$finalOverlayView = view2;
        val$finalSceneRoot = ViewGroup.this;
        super();
        mCanceled = false;
        mPausedAlpha = -1F;
    }
}
