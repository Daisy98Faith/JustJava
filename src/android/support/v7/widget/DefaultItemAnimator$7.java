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
    final ViewPropertyAnimator val$oldViewAnim;
    final View val$view;

    public void onAnimationEnd(Animator animator)
    {
        val$oldViewAnim.setListener(null);
        val$view.setAlpha(1.0F);
        val$view.setTranslationX(0.0F);
        val$view.setTranslationY(0.0F);
        dispatchChangeFinished(val$changeInfo.oldHolder, true);
        mChangeAnimations.remove(val$changeInfo.oldHolder);
        dispatchFinishedWhenDone();
    }

    public void onAnimationStart(Animator animator)
    {
        dispatchChangeStarting(val$changeInfo.oldHolder, true);
    }

    angeInfo()
    {
        this$0 = final_defaultitemanimator;
        val$changeInfo = angeinfo;
        val$oldViewAnim = viewpropertyanimator;
        val$view = View.this;
        super();
    }
}
