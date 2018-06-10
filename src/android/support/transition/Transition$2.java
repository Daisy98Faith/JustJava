// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

// Referenced classes of package android.support.transition:
//            Transition

class r extends AnimatorListenerAdapter
{

    final Transition this$0;

    public void onAnimationEnd(Animator animator)
    {
        end();
        animator.removeListener(this);
    }

    r()
    {
        this$0 = Transition.this;
        super();
    }
}
