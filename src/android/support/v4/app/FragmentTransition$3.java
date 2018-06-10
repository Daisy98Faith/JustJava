// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.graphics.Rect;
import android.support.v4.util.ArrayMap;
import android.view.View;

// Referenced classes of package android.support.v4.app:
//            FragmentTransition, FragmentTransitionCompat21, Fragment

static final class val.epicenter
    implements Runnable
{

    final Rect val$epicenter;
    final View val$epicenterView;
    final Fragment val$inFragment;
    final boolean val$inIsPop;
    final ArrayMap val$inSharedElements;
    final Fragment val$outFragment;

    public void run()
    {
        FragmentTransition.access$200(val$inFragment, val$outFragment, val$inIsPop, val$inSharedElements, false);
        if (val$epicenterView != null)
        {
            FragmentTransitionCompat21.getBoundsOnScreen(val$epicenterView, val$epicenter);
        }
    }

    pat21(Fragment fragment, Fragment fragment1, boolean flag, ArrayMap arraymap, View view, Rect rect)
    {
        val$inFragment = fragment;
        val$outFragment = fragment1;
        val$inIsPop = flag;
        val$inSharedElements = arraymap;
        val$epicenterView = view;
        val$epicenter = rect;
        super();
    }
}
