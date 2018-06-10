// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;
import java.util.ArrayList;

// Referenced classes of package android.support.v7.widget:
//            DefaultItemAnimator

class  extends AnimatorListenerAdapter
{

    final DefaultItemAnimator this$0;
    final ViewPropertyAnimator val$animation;
    final int val$deltaX;
    final int val$deltaY;
    final r val$holder;
    final View val$view;

    public void onAnimationCancel(Animator animator)
    {
        if (val$deltaX != 0)
        {
            val$view.setTranslationX(0.0F);
        }
        if (val$deltaY != 0)
        {
            val$view.setTranslationY(0.0F);
        }
    }

    public void onAnimationEnd(Animator animator)
    {
        val$animation.setListener(null);
        dispatchMoveFinished(val$holder);
        mMoveAnimations.remove(val$holder);
        dispatchFinishedWhenDone();
    }

    public void onAnimationStart(Animator animator)
    {
        dispatchMoveStarting(val$holder);
    }

    r()
    {
        this$0 = final_defaultitemanimator;
        val$holder = r;
        val$deltaX = i;
        val$view = view1;
        val$deltaY = j;
        val$animation = ViewPropertyAnimator.this;
        super();
    }
}
