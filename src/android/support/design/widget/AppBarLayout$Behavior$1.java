// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.animation.ValueAnimator;

// Referenced classes of package android.support.design.widget:
//            AppBarLayout, CoordinatorLayout

class val.child
    implements android.animation.dateListener
{

    final val.child this$0;
    final AppBarLayout val$child;
    final CoordinatorLayout val$coordinatorLayout;

    public void onAnimationUpdate(ValueAnimator valueanimator)
    {
        tHeaderTopBottomOffset(val$coordinatorLayout, val$child, ((Integer)valueanimator.getAnimatedValue()).intValue());
    }

    ()
    {
        this$0 = final_;
        val$coordinatorLayout = coordinatorlayout;
        val$child = AppBarLayout.this;
        super();
    }
}
