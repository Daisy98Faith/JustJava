// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.transition.Transition;
import android.view.View;
import java.util.ArrayList;

// Referenced classes of package android.support.v4.app:
//            FragmentTransitionCompat21

static final class val.exitingViews
    implements android.transition.
{

    final ArrayList val$exitingViews;
    final View val$fragmentView;

    public void onTransitionCancel(Transition transition)
    {
    }

    public void onTransitionEnd(Transition transition)
    {
        transition.removeListener(this);
        val$fragmentView.setVisibility(8);
        int j = val$exitingViews.size();
        for (int i = 0; i < j; i++)
        {
            ((View)val$exitingViews.get(i)).setVisibility(0);
        }

    }

    public void onTransitionPause(Transition transition)
    {
    }

    public void onTransitionResume(Transition transition)
    {
    }

    public void onTransitionStart(Transition transition)
    {
    }

    _cls9(View view, ArrayList arraylist)
    {
        val$fragmentView = view;
        val$exitingViews = arraylist;
        super();
    }
}
