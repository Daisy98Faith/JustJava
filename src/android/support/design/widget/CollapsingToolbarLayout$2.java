// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.animation.ValueAnimator;

// Referenced classes of package android.support.design.widget:
//            CollapsingToolbarLayout

class this._cls0
    implements android.animation.teListener
{

    final CollapsingToolbarLayout this$0;

    public void onAnimationUpdate(ValueAnimator valueanimator)
    {
        setScrimAlpha(((Integer)valueanimator.getAnimatedValue()).intValue());
    }

    ()
    {
        this$0 = CollapsingToolbarLayout.this;
        super();
    }
}
