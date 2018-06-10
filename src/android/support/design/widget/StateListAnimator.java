// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.util.StateSet;
import java.util.ArrayList;

final class StateListAnimator
{
    static class Tuple
    {

        final ValueAnimator mAnimator;
        final int mSpecs[];

        Tuple(int ai[], ValueAnimator valueanimator)
        {
            mSpecs = ai;
            mAnimator = valueanimator;
        }
    }


    private final android.animation.Animator.AnimatorListener mAnimationListener = new AnimatorListenerAdapter() {

        final StateListAnimator this$0;

        public void onAnimationEnd(Animator animator)
        {
            if (mRunningAnimator == animator)
            {
                mRunningAnimator = null;
            }
        }

            
            {
                this$0 = StateListAnimator.this;
                super();
            }
    };
    private Tuple mLastMatch;
    ValueAnimator mRunningAnimator;
    private final ArrayList mTuples = new ArrayList();

    StateListAnimator()
    {
        mLastMatch = null;
        mRunningAnimator = null;
    }

    private void cancel()
    {
        if (mRunningAnimator != null)
        {
            mRunningAnimator.cancel();
            mRunningAnimator = null;
        }
    }

    private void start(Tuple tuple)
    {
        mRunningAnimator = tuple.mAnimator;
        mRunningAnimator.start();
    }

    public void addState(int ai[], ValueAnimator valueanimator)
    {
        ai = new Tuple(ai, valueanimator);
        valueanimator.addListener(mAnimationListener);
        mTuples.add(ai);
    }

    public void jumpToCurrentState()
    {
        if (mRunningAnimator != null)
        {
            mRunningAnimator.end();
            mRunningAnimator = null;
        }
    }

    void setState(int ai[])
    {
        Object obj;
        int i;
        int j;
        obj = null;
        j = mTuples.size();
        i = 0;
_L8:
        Tuple tuple = obj;
        if (i >= j) goto _L2; else goto _L1
_L1:
        tuple = (Tuple)mTuples.get(i);
        if (!StateSet.stateSetMatches(tuple.mSpecs, ai)) goto _L3; else goto _L2
_L2:
        if (tuple != mLastMatch) goto _L5; else goto _L4
_L4:
        return;
_L3:
        i++;
        continue; /* Loop/switch isn't completed */
_L5:
        if (mLastMatch != null)
        {
            cancel();
        }
        mLastMatch = tuple;
        if (tuple == null) goto _L4; else goto _L6
_L6:
        start(tuple);
        return;
        if (true) goto _L8; else goto _L7
_L7:
    }
}
