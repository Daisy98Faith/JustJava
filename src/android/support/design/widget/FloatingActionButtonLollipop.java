// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.View;

// Referenced classes of package android.support.design.widget:
//            FloatingActionButtonImpl, VisibilityAwareImageButton, ShadowViewDelegate, ShadowDrawableWrapper, 
//            CircularBorderDrawableLollipop, CircularBorderDrawable

class FloatingActionButtonLollipop extends FloatingActionButtonImpl
{
    static class AlwaysStatefulGradientDrawable extends GradientDrawable
    {

        public boolean isStateful()
        {
            return true;
        }

        AlwaysStatefulGradientDrawable()
        {
        }
    }


    private InsetDrawable mInsetDrawable;

    FloatingActionButtonLollipop(VisibilityAwareImageButton visibilityawareimagebutton, ShadowViewDelegate shadowviewdelegate)
    {
        super(visibilityawareimagebutton, shadowviewdelegate);
    }

    public float getElevation()
    {
        return mView.getElevation();
    }

    void getPadding(Rect rect)
    {
        if (mShadowViewDelegate.isCompatPaddingEnabled())
        {
            float f = mShadowViewDelegate.getRadius();
            float f1 = getElevation() + mPressedTranslationZ;
            int i = (int)Math.ceil(ShadowDrawableWrapper.calculateHorizontalPadding(f1, f, false));
            int j = (int)Math.ceil(ShadowDrawableWrapper.calculateVerticalPadding(f1, f, false));
            rect.set(i, j, i, j);
            return;
        } else
        {
            rect.set(0, 0, 0, 0);
            return;
        }
    }

    void jumpDrawableToCurrentState()
    {
    }

    CircularBorderDrawable newCircularDrawable()
    {
        return new CircularBorderDrawableLollipop();
    }

    GradientDrawable newGradientDrawableForShape()
    {
        return new AlwaysStatefulGradientDrawable();
    }

    void onCompatShadowChanged()
    {
        updatePadding();
    }

    void onDrawableStateChanged(int ai[])
    {
    }

    void onElevationsChanged(float f, float f1)
    {
        if (android.os.Build.VERSION.SDK_INT == 21)
        {
            if (mView.isEnabled())
            {
                mView.setElevation(f);
                if (mView.isFocused() || mView.isPressed())
                {
                    mView.setTranslationZ(f1);
                } else
                {
                    mView.setTranslationZ(0.0F);
                }
            } else
            {
                mView.setElevation(0.0F);
                mView.setTranslationZ(0.0F);
            }
        } else
        {
            StateListAnimator statelistanimator = new StateListAnimator();
            AnimatorSet animatorset = new AnimatorSet();
            animatorset.play(ObjectAnimator.ofFloat(mView, "elevation", new float[] {
                f
            }).setDuration(0L)).with(ObjectAnimator.ofFloat(mView, View.TRANSLATION_Z, new float[] {
                f1
            }).setDuration(100L));
            animatorset.setInterpolator(ANIM_INTERPOLATOR);
            statelistanimator.addState(PRESSED_ENABLED_STATE_SET, animatorset);
            animatorset = new AnimatorSet();
            animatorset.play(ObjectAnimator.ofFloat(mView, "elevation", new float[] {
                f
            }).setDuration(0L)).with(ObjectAnimator.ofFloat(mView, View.TRANSLATION_Z, new float[] {
                f1
            }).setDuration(100L));
            animatorset.setInterpolator(ANIM_INTERPOLATOR);
            statelistanimator.addState(FOCUSED_ENABLED_STATE_SET, animatorset);
            animatorset = new AnimatorSet();
            animatorset.playSequentially(new Animator[] {
                ObjectAnimator.ofFloat(mView, "elevation", new float[] {
                    f
                }).setDuration(0L), ObjectAnimator.ofFloat(mView, View.TRANSLATION_Z, new float[] {
                    mView.getTranslationZ()
                }).setDuration(100L), ObjectAnimator.ofFloat(mView, View.TRANSLATION_Z, new float[] {
                    0.0F
                }).setDuration(100L)
            });
            animatorset.setInterpolator(ANIM_INTERPOLATOR);
            statelistanimator.addState(ENABLED_STATE_SET, animatorset);
            animatorset = new AnimatorSet();
            animatorset.play(ObjectAnimator.ofFloat(mView, "elevation", new float[] {
                0.0F
            }).setDuration(0L)).with(ObjectAnimator.ofFloat(mView, View.TRANSLATION_Z, new float[] {
                0.0F
            }).setDuration(0L));
            animatorset.setInterpolator(ANIM_INTERPOLATOR);
            statelistanimator.addState(EMPTY_STATE_SET, animatorset);
            mView.setStateListAnimator(statelistanimator);
        }
        if (mShadowViewDelegate.isCompatPaddingEnabled())
        {
            updatePadding();
        }
    }

    void onPaddingUpdated(Rect rect)
    {
        if (mShadowViewDelegate.isCompatPaddingEnabled())
        {
            mInsetDrawable = new InsetDrawable(mRippleDrawable, rect.left, rect.top, rect.right, rect.bottom);
            mShadowViewDelegate.setBackgroundDrawable(mInsetDrawable);
            return;
        } else
        {
            mShadowViewDelegate.setBackgroundDrawable(mRippleDrawable);
            return;
        }
    }

    boolean requirePreDrawListener()
    {
        return false;
    }

    void setBackgroundDrawable(ColorStateList colorstatelist, android.graphics.PorterDuff.Mode mode, int i, int j)
    {
        mShapeDrawable = DrawableCompat.wrap(createShapeDrawable());
        DrawableCompat.setTintList(mShapeDrawable, colorstatelist);
        if (mode != null)
        {
            DrawableCompat.setTintMode(mShapeDrawable, mode);
        }
        if (j > 0)
        {
            mBorderDrawable = createBorderDrawable(j, colorstatelist);
            colorstatelist = new LayerDrawable(new Drawable[] {
                mBorderDrawable, mShapeDrawable
            });
        } else
        {
            mBorderDrawable = null;
            colorstatelist = mShapeDrawable;
        }
        mRippleDrawable = new RippleDrawable(ColorStateList.valueOf(i), colorstatelist, null);
        mContentBackground = mRippleDrawable;
        mShadowViewDelegate.setBackgroundDrawable(mRippleDrawable);
    }

    void setRippleColor(int i)
    {
        if (mRippleDrawable instanceof RippleDrawable)
        {
            ((RippleDrawable)mRippleDrawable).setColor(ColorStateList.valueOf(i));
            return;
        } else
        {
            super.setRippleColor(i);
            return;
        }
    }
}
