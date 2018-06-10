// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;

// Referenced classes of package android.support.design.widget:
//            FloatingActionButtonImpl, ShadowDrawableWrapper

private abstract class <init> extends AnimatorListenerAdapter
    implements android.animation.nimatorImpl
{

    private float mShadowSizeEnd;
    private float mShadowSizeStart;
    private boolean mValidValues;
    final FloatingActionButtonImpl this$0;

    protected abstract float getTargetShadowSize();

    public void onAnimationEnd(Animator animator)
    {
        mShadowDrawable.setShadowSize(mShadowSizeEnd);
        mValidValues = false;
    }

    public void onAnimationUpdate(ValueAnimator valueanimator)
    {
        if (!mValidValues)
        {
            mShadowSizeStart = mShadowDrawable.getShadowSize();
            mShadowSizeEnd = getTargetShadowSize();
            mValidValues = true;
        }
        mShadowDrawable.setShadowSize(mShadowSizeStart + (mShadowSizeEnd - mShadowSizeStart) * valueanimator.getAnimatedFraction());
    }

    private ()
    {
        this$0 = FloatingActionButtonImpl.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
