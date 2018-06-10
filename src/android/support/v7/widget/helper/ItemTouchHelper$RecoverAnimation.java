// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget.helper;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;

// Referenced classes of package android.support.v7.widget.helper:
//            ItemTouchHelper

private class setFraction
    implements android.animation.coverAnimation
{

    final int mActionState;
    final int mAnimationType;
    boolean mEnded;
    private float mFraction;
    public boolean mIsPendingCleanup;
    boolean mOverridden;
    final float mStartDx;
    final float mStartDy;
    final float mTargetX;
    final float mTargetY;
    private final ValueAnimator mValueAnimator = ValueAnimator.ofFloat(new float[] {
        0.0F, 1.0F
    });
    final android.support.v7.widget.mation.mStartDy mViewHolder;
    float mX;
    float mY;
    final ItemTouchHelper this$0;

    public void cancel()
    {
        mValueAnimator.cancel();
    }

    public void onAnimationCancel(Animator animator)
    {
        setFraction(1.0F);
    }

    public void onAnimationEnd(Animator animator)
    {
        if (!mEnded)
        {
            mViewHolder.clable(true);
        }
        mEnded = true;
    }

    public void onAnimationRepeat(Animator animator)
    {
    }

    public void onAnimationStart(Animator animator)
    {
    }

    public void setDuration(long l)
    {
        mValueAnimator.setDuration(l);
    }

    public void setFraction(float f)
    {
        mFraction = f;
    }

    public void start()
    {
        mViewHolder.clable(false);
        mValueAnimator.start();
    }

    public void update()
    {
        if (mStartDx == mTargetX)
        {
            mX = mViewHolder.mViewHolder.getTranslationX();
        } else
        {
            mX = mStartDx + mFraction * (mTargetX - mStartDx);
        }
        if (mStartDy == mTargetY)
        {
            mY = mViewHolder.mViewHolder.getTranslationY();
            return;
        } else
        {
            mY = mStartDy + mFraction * (mTargetY - mStartDy);
            return;
        }
    }

    _cls1.val.this._cls0(android.support.v7.widget.mation mation, int i, int j, float f, float f1, float f2, 
            float f3)
    {
        this.this$0 = ItemTouchHelper.this;
        super();
        mOverridden = false;
        mEnded = false;
        mActionState = j;
        mAnimationType = i;
        mViewHolder = mation;
        mStartDx = f;
        mStartDy = f1;
        mTargetX = f2;
        mTargetY = f3;
        mValueAnimator.addUpdateListener(new android.animation.ValueAnimator.AnimatorUpdateListener() {

            final ItemTouchHelper.RecoverAnimation this$1;
            final ItemTouchHelper val$this$0;

            public void onAnimationUpdate(ValueAnimator valueanimator)
            {
                setFraction(valueanimator.getAnimatedFraction());
            }

            
            {
                this$1 = ItemTouchHelper.RecoverAnimation.this;
                this$0 = itemtouchhelper;
                super();
            }
        });
        mValueAnimator.setTarget(mation.mValueAnimator);
        mValueAnimator.addListener(this);
        setFraction(0.0F);
    }
}
