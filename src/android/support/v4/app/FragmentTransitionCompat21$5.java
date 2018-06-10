// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.transition.Transition;
import java.util.ArrayList;

// Referenced classes of package android.support.v4.app:
//            FragmentTransitionCompat21

static final class val.sharedElementsIn
    implements android.transition.
{

    final Object val$enterTransition;
    final ArrayList val$enteringViews;
    final Object val$exitTransition;
    final ArrayList val$exitingViews;
    final Object val$sharedElementTransition;
    final ArrayList val$sharedElementsIn;

    public void onTransitionCancel(Transition transition)
    {
    }

    public void onTransitionEnd(Transition transition)
    {
    }

    public void onTransitionPause(Transition transition)
    {
    }

    public void onTransitionResume(Transition transition)
    {
    }

    public void onTransitionStart(Transition transition)
    {
        if (val$enterTransition != null)
        {
            FragmentTransitionCompat21.replaceTargets(val$enterTransition, val$enteringViews, null);
        }
        if (val$exitTransition != null)
        {
            FragmentTransitionCompat21.replaceTargets(val$exitTransition, val$exitingViews, null);
        }
        if (val$sharedElementTransition != null)
        {
            FragmentTransitionCompat21.replaceTargets(val$sharedElementTransition, val$sharedElementsIn, null);
        }
    }

    _cls9(Object obj, ArrayList arraylist, Object obj1, ArrayList arraylist1, Object obj2, ArrayList arraylist2)
    {
        val$enterTransition = obj;
        val$enteringViews = arraylist;
        val$exitTransition = obj1;
        val$exitingViews = arraylist1;
        val$sharedElementTransition = obj2;
        val$sharedElementsIn = arraylist2;
        super();
    }
}
