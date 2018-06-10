// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v4.util.ArrayMap;
import java.util.ArrayList;

// Referenced classes of package android.support.transition:
//            Transition

class r extends AnimatorListenerAdapter
{

    final Transition this$0;
    final ArrayMap val$runningAnimators;

    public void onAnimationEnd(Animator animator)
    {
        val$runningAnimators.remove(animator);
        Transition.access$000(Transition.this).remove(animator);
    }

    public void onAnimationStart(Animator animator)
    {
        Transition.access$000(Transition.this).add(animator);
    }

    r()
    {
        this$0 = final_transition;
        val$runningAnimators = ArrayMap.this;
        super();
    }
}
