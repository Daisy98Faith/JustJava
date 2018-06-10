// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

// Referenced classes of package android.support.design.widget:
//            BaseTransientBottomBar

class val.event extends AnimatorListenerAdapter
{

    final BaseTransientBottomBar this$0;
    final int val$event;

    public void onAnimationEnd(Animator animator)
    {
        onViewHidden(val$event);
    }

    public void onAnimationStart(Animator animator)
    {
        BaseTransientBottomBar.access$000(BaseTransientBottomBar.this).animateContentOut(0, 180);
    }

    tentViewCallback()
    {
        this$0 = final_basetransientbottombar;
        val$event = I.this;
        super();
    }
}
