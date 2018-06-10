// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;


// Referenced classes of package android.support.transition:
//            TransitionSet, Transition

class itionListenerAdapter extends itionListenerAdapter
{

    final TransitionSet this$0;
    final Transition val$nextTransition;

    public void onTransitionEnd(Transition transition)
    {
        val$nextTransition.runAnimators();
        transition.removeListener(this);
    }

    itionListenerAdapter()
    {
        this$0 = final_transitionset;
        val$nextTransition = Transition.this;
        super();
    }
}
