// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.internal;

import android.animation.ValueAnimator;
import android.widget.TextView;

// Referenced classes of package android.support.design.internal:
//            TextScale

class val.view
    implements android.animation.r.AnimatorUpdateListener
{

    final TextScale this$0;
    final TextView val$view;

    public void onAnimationUpdate(ValueAnimator valueanimator)
    {
        float f = ((Float)valueanimator.getAnimatedValue()).floatValue();
        val$view.setScaleX(f);
        val$view.setScaleY(f);
    }

    teListener()
    {
        this$0 = final_textscale;
        val$view = TextView.this;
        super();
    }
}
