// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

// Referenced classes of package android.support.design.widget:
//            TabLayout

class val.position extends AnimatorListenerAdapter
{

    final electionOffset this$1;
    final int val$position;

    public void onAnimationEnd(Animator animator)
    {
        electedPosition = val$position;
        electionOffset = 0.0F;
    }

    ()
    {
        this$1 = final_;
        val$position = I.this;
        super();
    }
}
