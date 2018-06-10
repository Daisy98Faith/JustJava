// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.view.View;

// Referenced classes of package android.support.transition:
//            Transition, TransitionValues, WindowIdImpl

private static class mTransition
{

    String mName;
    Transition mTransition;
    TransitionValues mValues;
    View mView;
    WindowIdImpl mWindowId;

    _cls9(View view, String s, Transition transition, WindowIdImpl windowidimpl, TransitionValues transitionvalues)
    {
        mView = view;
        mName = s;
        mValues = transitionvalues;
        mWindowId = windowidimpl;
        mTransition = transition;
    }
}
