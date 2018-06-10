// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.animation.ValueAnimator;
import android.support.v4.view.ViewCompat;

// Referenced classes of package android.support.design.widget:
//            BaseTransientBottomBar

class mPreviousAnimatedIntValue
    implements android.animation.teListener
{

    private int mPreviousAnimatedIntValue;
    final BaseTransientBottomBar this$0;

    public void onAnimationUpdate(ValueAnimator valueanimator)
    {
        int i = ((Integer)valueanimator.getAnimatedValue()).intValue();
        if (BaseTransientBottomBar.access$100())
        {
            ViewCompat.offsetTopAndBottom(mView, i - mPreviousAnimatedIntValue);
        } else
        {
            mView.setTranslationY(i);
        }
        mPreviousAnimatedIntValue = i;
    }

    ckbarBaseLayout()
    {
        this$0 = BaseTransientBottomBar.this;
        super();
        mPreviousAnimatedIntValue = 0;
    }
}
