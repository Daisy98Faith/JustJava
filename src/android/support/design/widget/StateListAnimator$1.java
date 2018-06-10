// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

// Referenced classes of package android.support.design.widget:
//            StateListAnimator

class this._cls0 extends AnimatorListenerAdapter
{

    final StateListAnimator this$0;

    public void onAnimationEnd(Animator animator)
    {
        if (mRunningAnimator == animator)
        {
            mRunningAnimator = null;
        }
    }

    ()
    {
        this$0 = StateListAnimator.this;
        super();
    }
}
