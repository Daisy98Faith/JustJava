// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.view.animation.Animation;

// Referenced classes of package android.support.design.widget:
//            BaseTransientBottomBar

class val.event
    implements android.view.animation.r
{

    final BaseTransientBottomBar this$0;
    final int val$event;

    public void onAnimationEnd(Animation animation)
    {
        onViewHidden(val$event);
    }

    public void onAnimationRepeat(Animation animation)
    {
    }

    public void onAnimationStart(Animation animation)
    {
    }

    ()
    {
        this$0 = final_basetransientbottombar;
        val$event = I.this;
        super();
    }
}
