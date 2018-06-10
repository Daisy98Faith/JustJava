// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.view.View;

// Referenced classes of package android.support.transition:
//            Fade, Transition

class mCanceled extends ion.TransitionListenerAdapter
{

    boolean mCanceled;
    float mPausedAlpha;
    final Fade this$0;
    final View val$endView;

    public void onTransitionCancel(Transition transition)
    {
        val$endView.setAlpha(1.0F);
        mCanceled = true;
    }

    public void onTransitionEnd(Transition transition)
    {
        if (!mCanceled)
        {
            val$endView.setAlpha(1.0F);
        }
    }

    public void onTransitionPause(Transition transition)
    {
        mPausedAlpha = val$endView.getAlpha();
        val$endView.setAlpha(1.0F);
    }

    public void onTransitionResume(Transition transition)
    {
        val$endView.setAlpha(mPausedAlpha);
    }

    ion()
    {
        this$0 = final_fade;
        val$endView = View.this;
        super();
        mCanceled = false;
    }
}
