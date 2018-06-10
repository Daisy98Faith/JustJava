// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.animation.ValueAnimator;

// Referenced classes of package android.support.design.widget:
//            TabLayout, AnimationUtils

class val.targetRight
    implements android.animation.Listener
{

    final val.targetRight this$1;
    final int val$startLeft;
    final int val$startRight;
    final int val$targetLeft;
    final int val$targetRight;

    public void onAnimationUpdate(ValueAnimator valueanimator)
    {
        float f = valueanimator.getAnimatedFraction();
        tIndicatorPosition(AnimationUtils.lerp(val$startLeft, val$targetLeft, f), AnimationUtils.lerp(val$startRight, val$targetRight, f));
    }

    ()
    {
        this$1 = final_;
        val$startLeft = i;
        val$targetLeft = j;
        val$startRight = k;
        val$targetRight = I.this;
        super();
    }
}
