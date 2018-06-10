// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.support.v4.util.ArrayMap;
import java.util.ArrayList;

// Referenced classes of package android.support.transition:
//            TransitionManager, Transition

class er extends er
{

    final ceneRoot this$0;
    final ArrayMap val$runningTransitions;

    public void onTransitionEnd(Transition transition)
    {
        ((ArrayList)val$runningTransitions.get(ceneRoot)).remove(transition);
    }

    er()
    {
        this$0 = final_er;
        val$runningTransitions = ArrayMap.this;
        super();
    }
}
