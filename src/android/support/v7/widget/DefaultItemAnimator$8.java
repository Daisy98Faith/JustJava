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
    final angeInfo val$changeInfo;
    final View val$newView;
    final ViewPropertyAnimator val$newViewAnimation;

    public void onAnimationEnd(Animator animator)
    {
        val$newViewAnimation.setListener(null);
        val$newView.setAlpha(1.0F);
        val$newView.setTranslationX(0.0F);
        val$newView.setTranslationY(0.0F);
        dispatchChangeFinished(val$changeInfo.newHolder, false);
        mChangeAnimations.remove(val$changeInfo.newHolder);
        dispatchFinishedWhenDone();
    }

    public void onAnimationStart(Animator animator)
    {
        dispatchChangeStarting(val$changeInfo.newHolder, false);
    }

    angeInfo()
    {
        this$0 = final_defaultitemanimator;
        val$changeInfo = angeinfo;
        val$newViewAnimation = viewpropertyanimator;
        val$newView = View.this;
        super();
    }
}
