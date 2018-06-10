// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.view.View;
import java.util.ArrayList;

// Referenced classes of package android.support.v4.app:
//            FragmentTransition, FragmentTransitionCompat21, Fragment

static final class val.exitTransition
    implements Runnable
{

    final Object val$enterTransition;
    final ArrayList val$enteringViews;
    final Object val$exitTransition;
    final ArrayList val$exitingViews;
    final Fragment val$inFragment;
    final View val$nonExistentView;
    final ArrayList val$sharedElementsIn;

    public void run()
    {
        if (val$enterTransition != null)
        {
            FragmentTransitionCompat21.removeTarget(val$enterTransition, val$nonExistentView);
            ArrayList arraylist = FragmentTransition.access$100(val$enterTransition, val$inFragment, val$sharedElementsIn, val$nonExistentView);
            val$enteringViews.addAll(arraylist);
        }
        if (val$exitingViews != null)
        {
            if (val$exitTransition != null)
            {
                ArrayList arraylist1 = new ArrayList();
                arraylist1.add(val$nonExistentView);
                FragmentTransitionCompat21.replaceTargets(val$exitTransition, val$exitingViews, arraylist1);
            }
            val$exitingViews.clear();
            val$exitingViews.add(val$nonExistentView);
        }
    }

    pat21(Object obj, View view, Fragment fragment, ArrayList arraylist, ArrayList arraylist1, ArrayList arraylist2, Object obj1)
    {
        val$enterTransition = obj;
        val$nonExistentView = view;
        val$inFragment = fragment;
        val$sharedElementsIn = arraylist;
        val$enteringViews = arraylist1;
        val$exitingViews = arraylist2;
        val$exitTransition = obj1;
        super();
    }
}
