// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.view.ViewGroup;

// Referenced classes of package android.support.transition:
//            ChangeBounds, ViewGroupUtils, Transition

class mCanceled extends sitionListenerAdapter
{

    boolean mCanceled;
    final ChangeBounds this$0;
    final ViewGroup val$parent;

    public void onTransitionCancel(Transition transition)
    {
        ViewGroupUtils.suppressLayout(val$parent, false);
        mCanceled = true;
    }

    public void onTransitionEnd(Transition transition)
    {
        if (!mCanceled)
        {
            ViewGroupUtils.suppressLayout(val$parent, false);
        }
    }

    public void onTransitionPause(Transition transition)
    {
        ViewGroupUtils.suppressLayout(val$parent, false);
    }

    public void onTransitionResume(Transition transition)
    {
        ViewGroupUtils.suppressLayout(val$parent, true);
    }

    sitionListenerAdapter()
    {
        this$0 = final_changebounds;
        val$parent = ViewGroup.this;
        super();
        mCanceled = false;
    }
}
