// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.animation.ValueAnimator;

// Referenced classes of package android.support.design.widget:
//            TextInputLayout, CollapsingTextHelper

class this._cls0
    implements android.animation.atorUpdateListener
{

    final TextInputLayout this$0;

    public void onAnimationUpdate(ValueAnimator valueanimator)
    {
        mCollapsingTextHelper.setExpansionFraction(((Float)valueanimator.getAnimatedValue()).floatValue());
    }

    er()
    {
        this$0 = TextInputLayout.this;
        super();
    }
}
