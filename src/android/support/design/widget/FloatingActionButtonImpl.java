// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.animation.Interpolator;

// Referenced classes of package android.support.design.widget:
//            AnimationUtils, StateListAnimator, VisibilityAwareImageButton, ShadowDrawableWrapper, 
//            CircularBorderDrawable, ShadowViewDelegate

class FloatingActionButtonImpl
{
    private class DisabledElevationAnimation extends ShadowAnimatorImpl
    {

        final FloatingActionButtonImpl this$0;

        protected float getTargetShadowSize()
        {
            return 0.0F;
        }

        DisabledElevationAnimation()
        {
            this$0 = FloatingActionButtonImpl.this;
            super();
        }
    }

    private class ElevateToTranslationZAnimation extends ShadowAnimatorImpl
    {

        final FloatingActionButtonImpl this$0;

        protected float getTargetShadowSize()
        {
            return mElevation + mPressedTranslationZ;
        }

        ElevateToTranslationZAnimation()
        {
            this$0 = FloatingActionButtonImpl.this;
            super();
        }
    }

    static interface InternalVisibilityChangedListener
    {

        public abstract void onHidden();

        public abstract void onShown();
    }

    private class ResetElevationAnimation extends ShadowAnimatorImpl
    {

        final FloatingActionButtonImpl this$0;

        protected float getTargetShadowSize()
        {
            return mElevation;
        }

        ResetElevationAnimation()
        {
            this$0 = FloatingActionButtonImpl.this;
            super();
        }
    }

    private abstract class ShadowAnimatorImpl extends AnimatorListenerAdapter
        implements android.animation.ValueAnimator.AnimatorUpdateListener
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

        private ShadowAnimatorImpl()
        {
            this$0 = FloatingActionButtonImpl.this;
            super();
        }

    }


    static final Interpolator ANIM_INTERPOLATOR;
    static final int ANIM_STATE_HIDING = 1;
    static final int ANIM_STATE_NONE = 0;
    static final int ANIM_STATE_SHOWING = 2;
    static final int EMPTY_STATE_SET[] = new int[0];
    static final int ENABLED_STATE_SET[] = {
        0x101009e
    };
    static final int FOCUSED_ENABLED_STATE_SET[] = {
        0x101009c, 0x101009e
    };
    static final long PRESSED_ANIM_DELAY = 100L;
    static final long PRESSED_ANIM_DURATION = 100L;
    static final int PRESSED_ENABLED_STATE_SET[] = {
        0x10100a7, 0x101009e
    };
    static final int SHOW_HIDE_ANIM_DURATION = 200;
    int mAnimState;
    CircularBorderDrawable mBorderDrawable;
    Drawable mContentBackground;
    float mElevation;
    private android.view.ViewTreeObserver.OnPreDrawListener mPreDrawListener;
    float mPressedTranslationZ;
    Drawable mRippleDrawable;
    private float mRotation;
    ShadowDrawableWrapper mShadowDrawable;
    final ShadowViewDelegate mShadowViewDelegate;
    Drawable mShapeDrawable;
    private final StateListAnimator mStateListAnimator = new StateListAnimator();
    private final Rect mTmpRect = new Rect();
    final VisibilityAwareImageButton mView;

    FloatingActionButtonImpl(VisibilityAwareImageButton visibilityawareimagebutton, ShadowViewDelegate shadowviewdelegate)
    {
        mAnimState = 0;
        mView = visibilityawareimagebutton;
        mShadowViewDelegate = shadowviewdelegate;
        mStateListAnimator.addState(PRESSED_ENABLED_STATE_SET, createAnimator(new ElevateToTranslationZAnimation()));
        mStateListAnimator.addState(FOCUSED_ENABLED_STATE_SET, createAnimator(new ElevateToTranslationZAnimation()));
        mStateListAnimator.addState(ENABLED_STATE_SET, createAnimator(new ResetElevationAnimation()));
        mStateListAnimator.addState(EMPTY_STATE_SET, createAnimator(new DisabledElevationAnimation()));
        mRotation = mView.getRotation();
    }

    private ValueAnimator createAnimator(ShadowAnimatorImpl shadowanimatorimpl)
    {
        ValueAnimator valueanimator = new ValueAnimator();
        valueanimator.setInterpolator(ANIM_INTERPOLATOR);
        valueanimator.setDuration(100L);
        valueanimator.addListener(shadowanimatorimpl);
        valueanimator.addUpdateListener(shadowanimatorimpl);
        valueanimator.setFloatValues(new float[] {
            0.0F, 1.0F
        });
        return valueanimator;
    }

    private static ColorStateList createColorStateList(int i)
    {
        int ai[][] = new int[3][];
        int ai1[] = new int[3];
        ai[0] = FOCUSED_ENABLED_STATE_SET;
        ai1[0] = i;
        int j = 0 + 1;
        ai[j] = PRESSED_ENABLED_STATE_SET;
        ai1[j] = i;
        i = j + 1;
        ai[i] = new int[0];
        ai1[i] = 0;
        return new ColorStateList(ai, ai1);
    }

    private void ensurePreDrawListener()
    {
        if (mPreDrawListener == null)
        {
            mPreDrawListener = new android.view.ViewTreeObserver.OnPreDrawListener() {

                final FloatingActionButtonImpl this$0;

                public boolean onPreDraw()
                {
                    FloatingActionButtonImpl.this.onPreDraw();
                    return true;
                }

            
            {
                this$0 = FloatingActionButtonImpl.this;
                super();
            }
            };
        }
    }

    private boolean shouldAnimateVisibilityChange()
    {
        return ViewCompat.isLaidOut(mView) && !mView.isInEditMode();
    }

    private void updateFromViewRotation()
    {
        if (android.os.Build.VERSION.SDK_INT != 19) goto _L2; else goto _L1
_L1:
        if (mRotation % 90F == 0.0F) goto _L4; else goto _L3
_L3:
        if (mView.getLayerType() != 1)
        {
            mView.setLayerType(1, null);
        }
_L2:
        if (mShadowDrawable != null)
        {
            mShadowDrawable.setRotation(-mRotation);
        }
        if (mBorderDrawable != null)
        {
            mBorderDrawable.setRotation(-mRotation);
        }
        return;
_L4:
        if (mView.getLayerType() != 0)
        {
            mView.setLayerType(0, null);
        }
        if (true) goto _L2; else goto _L5
_L5:
    }

    CircularBorderDrawable createBorderDrawable(int i, ColorStateList colorstatelist)
    {
        android.content.Context context = mView.getContext();
        CircularBorderDrawable circularborderdrawable = newCircularDrawable();
        circularborderdrawable.setGradientColors(ContextCompat.getColor(context, android.support.design.R.color.design_fab_stroke_top_outer_color), ContextCompat.getColor(context, android.support.design.R.color.design_fab_stroke_top_inner_color), ContextCompat.getColor(context, android.support.design.R.color.design_fab_stroke_end_inner_color), ContextCompat.getColor(context, android.support.design.R.color.design_fab_stroke_end_outer_color));
        circularborderdrawable.setBorderWidth(i);
        circularborderdrawable.setBorderTint(colorstatelist);
        return circularborderdrawable;
    }

    GradientDrawable createShapeDrawable()
    {
        GradientDrawable gradientdrawable = newGradientDrawableForShape();
        gradientdrawable.setShape(1);
        gradientdrawable.setColor(-1);
        return gradientdrawable;
    }

    final Drawable getContentBackground()
    {
        return mContentBackground;
    }

    float getElevation()
    {
        return mElevation;
    }

    void getPadding(Rect rect)
    {
        mShadowDrawable.getPadding(rect);
    }

    void hide(final InternalVisibilityChangedListener listener, final boolean fromUser)
    {
        if (!isOrWillBeHidden())
        {
            mView.animate().cancel();
            if (shouldAnimateVisibilityChange())
            {
                mAnimState = 1;
                mView.animate().scaleX(0.0F).scaleY(0.0F).alpha(0.0F).setDuration(200L).setInterpolator(AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR).setListener(new AnimatorListenerAdapter() {

                    private boolean mCancelled;
                    final FloatingActionButtonImpl this$0;
                    final boolean val$fromUser;
                    final InternalVisibilityChangedListener val$listener;

                    public void onAnimationCancel(Animator animator)
                    {
                        mCancelled = true;
                    }

                    public void onAnimationEnd(Animator animator)
                    {
                        mAnimState = 0;
                        if (!mCancelled)
                        {
                            animator = mView;
                            byte byte1;
                            if (fromUser)
                            {
                                byte1 = 8;
                            } else
                            {
                                byte1 = 4;
                            }
                            animator.internalSetVisibility(byte1, fromUser);
                            if (listener != null)
                            {
                                listener.onHidden();
                            }
                        }
                    }

                    public void onAnimationStart(Animator animator)
                    {
                        mView.internalSetVisibility(0, fromUser);
                        mCancelled = false;
                    }

            
            {
                this$0 = FloatingActionButtonImpl.this;
                fromUser = flag;
                listener = internalvisibilitychangedlistener;
                super();
            }
                });
                return;
            }
            VisibilityAwareImageButton visibilityawareimagebutton = mView;
            byte byte0;
            if (fromUser)
            {
                byte0 = 8;
            } else
            {
                byte0 = 4;
            }
            visibilityawareimagebutton.internalSetVisibility(byte0, fromUser);
            if (listener != null)
            {
                listener.onHidden();
                return;
            }
        }
    }

    boolean isOrWillBeHidden()
    {
        if (mView.getVisibility() != 0) goto _L2; else goto _L1
_L1:
        if (mAnimState != 1) goto _L4; else goto _L3
_L3:
        return true;
_L4:
        return false;
_L2:
        if (mAnimState == 2)
        {
            return false;
        }
        if (true) goto _L3; else goto _L5
_L5:
    }

    boolean isOrWillBeShown()
    {
        if (mView.getVisibility() == 0) goto _L2; else goto _L1
_L1:
        if (mAnimState != 2) goto _L4; else goto _L3
_L3:
        return true;
_L4:
        return false;
_L2:
        if (mAnimState == 1)
        {
            return false;
        }
        if (true) goto _L3; else goto _L5
_L5:
    }

    void jumpDrawableToCurrentState()
    {
        mStateListAnimator.jumpToCurrentState();
    }

    CircularBorderDrawable newCircularDrawable()
    {
        return new CircularBorderDrawable();
    }

    GradientDrawable newGradientDrawableForShape()
    {
        return new GradientDrawable();
    }

    void onAttachedToWindow()
    {
        if (requirePreDrawListener())
        {
            ensurePreDrawListener();
            mView.getViewTreeObserver().addOnPreDrawListener(mPreDrawListener);
        }
    }

    void onCompatShadowChanged()
    {
    }

    void onDetachedFromWindow()
    {
        if (mPreDrawListener != null)
        {
            mView.getViewTreeObserver().removeOnPreDrawListener(mPreDrawListener);
            mPreDrawListener = null;
        }
    }

    void onDrawableStateChanged(int ai[])
    {
        mStateListAnimator.setState(ai);
    }

    void onElevationsChanged(float f, float f1)
    {
        if (mShadowDrawable != null)
        {
            mShadowDrawable.setShadowSize(f, mPressedTranslationZ + f);
            updatePadding();
        }
    }

    void onPaddingUpdated(Rect rect)
    {
    }

    void onPreDraw()
    {
        float f = mView.getRotation();
        if (mRotation != f)
        {
            mRotation = f;
            updateFromViewRotation();
        }
    }

    boolean requirePreDrawListener()
    {
        return true;
    }

    void setBackgroundDrawable(ColorStateList colorstatelist, android.graphics.PorterDuff.Mode mode, int i, int j)
    {
        mShapeDrawable = DrawableCompat.wrap(createShapeDrawable());
        DrawableCompat.setTintList(mShapeDrawable, colorstatelist);
        if (mode != null)
        {
            DrawableCompat.setTintMode(mShapeDrawable, mode);
        }
        mRippleDrawable = DrawableCompat.wrap(createShapeDrawable());
        DrawableCompat.setTintList(mRippleDrawable, createColorStateList(i));
        if (j > 0)
        {
            mBorderDrawable = createBorderDrawable(j, colorstatelist);
            colorstatelist = new Drawable[3];
            colorstatelist[0] = mBorderDrawable;
            colorstatelist[1] = mShapeDrawable;
            colorstatelist[2] = mRippleDrawable;
        } else
        {
            mBorderDrawable = null;
            colorstatelist = new Drawable[2];
            colorstatelist[0] = mShapeDrawable;
            colorstatelist[1] = mRippleDrawable;
        }
        mContentBackground = new LayerDrawable(colorstatelist);
        mShadowDrawable = new ShadowDrawableWrapper(mView.getContext(), mContentBackground, mShadowViewDelegate.getRadius(), mElevation, mElevation + mPressedTranslationZ);
        mShadowDrawable.setAddPaddingForCorners(false);
        mShadowViewDelegate.setBackgroundDrawable(mShadowDrawable);
    }

    void setBackgroundTintList(ColorStateList colorstatelist)
    {
        if (mShapeDrawable != null)
        {
            DrawableCompat.setTintList(mShapeDrawable, colorstatelist);
        }
        if (mBorderDrawable != null)
        {
            mBorderDrawable.setBorderTint(colorstatelist);
        }
    }

    void setBackgroundTintMode(android.graphics.PorterDuff.Mode mode)
    {
        if (mShapeDrawable != null)
        {
            DrawableCompat.setTintMode(mShapeDrawable, mode);
        }
    }

    final void setElevation(float f)
    {
        if (mElevation != f)
        {
            mElevation = f;
            onElevationsChanged(f, mPressedTranslationZ);
        }
    }

    final void setPressedTranslationZ(float f)
    {
        if (mPressedTranslationZ != f)
        {
            mPressedTranslationZ = f;
            onElevationsChanged(mElevation, f);
        }
    }

    void setRippleColor(int i)
    {
        if (mRippleDrawable != null)
        {
            DrawableCompat.setTintList(mRippleDrawable, createColorStateList(i));
        }
    }

    void show(final InternalVisibilityChangedListener listener, final boolean fromUser)
    {
        if (!isOrWillBeShown())
        {
            mView.animate().cancel();
            if (shouldAnimateVisibilityChange())
            {
                mAnimState = 2;
                if (mView.getVisibility() != 0)
                {
                    mView.setAlpha(0.0F);
                    mView.setScaleY(0.0F);
                    mView.setScaleX(0.0F);
                }
                mView.animate().scaleX(1.0F).scaleY(1.0F).alpha(1.0F).setDuration(200L).setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR).setListener(new AnimatorListenerAdapter() {

                    final FloatingActionButtonImpl this$0;
                    final boolean val$fromUser;
                    final InternalVisibilityChangedListener val$listener;

                    public void onAnimationEnd(Animator animator)
                    {
                        mAnimState = 0;
                        if (listener != null)
                        {
                            listener.onShown();
                        }
                    }

                    public void onAnimationStart(Animator animator)
                    {
                        mView.internalSetVisibility(0, fromUser);
                    }

            
            {
                this$0 = FloatingActionButtonImpl.this;
                fromUser = flag;
                listener = internalvisibilitychangedlistener;
                super();
            }
                });
                return;
            }
            mView.internalSetVisibility(0, fromUser);
            mView.setAlpha(1.0F);
            mView.setScaleY(1.0F);
            mView.setScaleX(1.0F);
            if (listener != null)
            {
                listener.onShown();
                return;
            }
        }
    }

    final void updatePadding()
    {
        Rect rect = mTmpRect;
        getPadding(rect);
        onPaddingUpdated(rect);
        mShadowViewDelegate.setShadowPadding(rect.left, rect.top, rect.right, rect.bottom);
    }

    static 
    {
        ANIM_INTERPOLATOR = AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR;
    }
}
