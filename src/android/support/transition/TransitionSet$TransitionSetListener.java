// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;


// Referenced classes of package android.support.transition:
//            TransitionSet, Transition

static class mTransitionSet extends 
{

    TransitionSet mTransitionSet;

    public void onTransitionEnd(Transition transition)
    {
        int _tmp = TransitionSet.access$106(mTransitionSet);
        if (TransitionSet.access$100(mTransitionSet) == 0)
        {
            TransitionSet.access$002(mTransitionSet, false);
            mTransitionSet.end();
        }
        transition.removeListener(this);
    }

    public void onTransitionStart(Transition transition)
    {
        if (!TransitionSet.access$000(mTransitionSet))
        {
            mTransitionSet.start();
            TransitionSet.access$002(mTransitionSet, true);
        }
    }

    (TransitionSet transitionset)
    {
        mTransitionSet = transitionset;
    }
}
