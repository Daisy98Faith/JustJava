// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.graphics.Rect;
import android.support.v4.util.ArrayMap;
import android.view.View;
import java.util.ArrayList;

// Referenced classes of package android.support.v4.app:
//            FragmentTransition, FragmentTransitionCompat21, Fragment

static final class val.inEpicenter
    implements Runnable
{

    final Object val$enterTransition;
    final Object val$finalSharedElementTransition;
    final agmentContainerTransition val$fragments;
    final Rect val$inEpicenter;
    final Fragment val$inFragment;
    final boolean val$inIsPop;
    final ArrayMap val$nameOverrides;
    final View val$nonExistentView;
    final Fragment val$outFragment;
    final ArrayList val$sharedElementsIn;
    final ArrayList val$sharedElementsOut;

    public void run()
    {
        Object obj = FragmentTransition.access$300(val$nameOverrides, val$finalSharedElementTransition, val$fragments);
        if (obj != null)
        {
            val$sharedElementsIn.addAll(((ArrayMap) (obj)).values());
            val$sharedElementsIn.add(val$nonExistentView);
        }
        FragmentTransition.access$200(val$inFragment, val$outFragment, val$inIsPop, ((ArrayMap) (obj)), false);
        if (val$finalSharedElementTransition != null)
        {
            FragmentTransitionCompat21.swapSharedElementTargets(val$finalSharedElementTransition, val$sharedElementsOut, val$sharedElementsIn);
            obj = FragmentTransition.access$400(((ArrayMap) (obj)), val$fragments, val$enterTransition, val$inIsPop);
            if (obj != null)
            {
                FragmentTransitionCompat21.getBoundsOnScreen(((View) (obj)), val$inEpicenter);
            }
        }
    }

    agmentContainerTransition(ArrayMap arraymap, Object obj, agmentContainerTransition agmentcontainertransition, ArrayList arraylist, View view, Fragment fragment, Fragment fragment1, 
            boolean flag, ArrayList arraylist1, Object obj1, Rect rect)
    {
        val$nameOverrides = arraymap;
        val$finalSharedElementTransition = obj;
        val$fragments = agmentcontainertransition;
        val$sharedElementsIn = arraylist;
        val$nonExistentView = view;
        val$inFragment = fragment;
        val$outFragment = fragment1;
        val$inIsPop = flag;
        val$sharedElementsOut = arraylist1;
        val$enterTransition = obj1;
        val$inEpicenter = rect;
        super();
    }
}
