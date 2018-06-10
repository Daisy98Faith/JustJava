// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.Space;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.AppCompatDrawableManager;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DrawableUtils;
import android.support.v7.widget.TintTypedArray;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AccelerateInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

// Referenced classes of package android.support.design.widget:
//            CollapsingTextHelper, ThemeUtils, AnimationUtils, ViewUtils, 
//            CheckableImageButton, DrawableUtils, TextInputEditText, ViewGroupUtils

public class TextInputLayout extends LinearLayout
{
    static class SavedState extends AbsSavedState
    {

        public static final android.os.Parcelable.Creator CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks() {

            public SavedState createFromParcel(Parcel parcel, ClassLoader classloader)
            {
                return new SavedState(parcel, classloader);
            }

            public volatile Object createFromParcel(Parcel parcel, ClassLoader classloader)
            {
                return createFromParcel(parcel, classloader);
            }

            public SavedState[] newArray(int i)
            {
                return new SavedState[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        });
        CharSequence error;

        public String toString()
        {
            return (new StringBuilder()).append("TextInputLayout.SavedState{").append(Integer.toHexString(System.identityHashCode(this))).append(" error=").append(error).append("}").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            TextUtils.writeToParcel(error, parcel, i);
        }


        SavedState(Parcel parcel, ClassLoader classloader)
        {
            super(parcel, classloader);
            error = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        }

        SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }
    }

    private class TextInputAccessibilityDelegate extends AccessibilityDelegateCompat
    {

        final TextInputLayout this$0;

        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
        {
            super.onInitializeAccessibilityEvent(view, accessibilityevent);
            accessibilityevent.setClassName(android/support/design/widget/TextInputLayout.getSimpleName());
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
            super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
            accessibilitynodeinfocompat.setClassName(android/support/design/widget/TextInputLayout.getSimpleName());
            view = mCollapsingTextHelper.getText();
            if (!TextUtils.isEmpty(view))
            {
                accessibilitynodeinfocompat.setText(view);
            }
            if (mEditText != null)
            {
                accessibilitynodeinfocompat.setLabelFor(mEditText);
            }
            if (mErrorView != null)
            {
                view = mErrorView.getText();
            } else
            {
                view = null;
            }
            if (!TextUtils.isEmpty(view))
            {
                accessibilitynodeinfocompat.setContentInvalid(true);
                accessibilitynodeinfocompat.setError(view);
            }
        }

        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
        {
            super.onPopulateAccessibilityEvent(view, accessibilityevent);
            view = mCollapsingTextHelper.getText();
            if (!TextUtils.isEmpty(view))
            {
                accessibilityevent.getText().add(view);
            }
        }

        TextInputAccessibilityDelegate()
        {
            this$0 = TextInputLayout.this;
            super();
        }
    }


    private static final int ANIMATION_DURATION = 200;
    private static final int INVALID_MAX_LENGTH = -1;
    private static final String LOG_TAG = "TextInputLayout";
    private ValueAnimator mAnimator;
    final CollapsingTextHelper mCollapsingTextHelper;
    boolean mCounterEnabled;
    private int mCounterMaxLength;
    private int mCounterOverflowTextAppearance;
    private boolean mCounterOverflowed;
    private int mCounterTextAppearance;
    private TextView mCounterView;
    private ColorStateList mDefaultTextColor;
    EditText mEditText;
    private CharSequence mError;
    private boolean mErrorEnabled;
    private boolean mErrorShown;
    private int mErrorTextAppearance;
    TextView mErrorView;
    private ColorStateList mFocusedTextColor;
    private boolean mHasPasswordToggleTintList;
    private boolean mHasPasswordToggleTintMode;
    private boolean mHasReconstructedEditTextBackground;
    private CharSequence mHint;
    private boolean mHintAnimationEnabled;
    private boolean mHintEnabled;
    private boolean mHintExpanded;
    private boolean mInDrawableStateChanged;
    private LinearLayout mIndicatorArea;
    private int mIndicatorsAdded;
    private final FrameLayout mInputFrame;
    private Drawable mOriginalEditTextEndDrawable;
    private CharSequence mPasswordToggleContentDesc;
    private Drawable mPasswordToggleDrawable;
    private Drawable mPasswordToggleDummyDrawable;
    private boolean mPasswordToggleEnabled;
    private ColorStateList mPasswordToggleTintList;
    private android.graphics.PorterDuff.Mode mPasswordToggleTintMode;
    private CheckableImageButton mPasswordToggleView;
    private boolean mPasswordToggledVisible;
    private boolean mRestoringSavedState;
    private Paint mTmpPaint;
    private final Rect mTmpRect;
    private Typeface mTypeface;

    public TextInputLayout(Context context)
    {
        this(context, null);
    }

    public TextInputLayout(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public TextInputLayout(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset);
        mTmpRect = new Rect();
        mCollapsingTextHelper = new CollapsingTextHelper(this);
        ThemeUtils.checkAppCompatTheme(context);
        setOrientation(1);
        setWillNotDraw(false);
        setAddStatesFromChildren(true);
        mInputFrame = new FrameLayout(context);
        mInputFrame.setAddStatesFromChildren(true);
        addView(mInputFrame);
        mCollapsingTextHelper.setTextSizeInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        mCollapsingTextHelper.setPositionInterpolator(new AccelerateInterpolator());
        mCollapsingTextHelper.setCollapsedTextGravity(0x800033);
        context = TintTypedArray.obtainStyledAttributes(context, attributeset, android.support.design.R.styleable.TextInputLayout, i, android.support.design.R.style.Widget_Design_TextInputLayout);
        mHintEnabled = context.getBoolean(android.support.design.R.styleable.TextInputLayout_hintEnabled, true);
        setHint(context.getText(android.support.design.R.styleable.TextInputLayout_android_hint));
        mHintAnimationEnabled = context.getBoolean(android.support.design.R.styleable.TextInputLayout_hintAnimationEnabled, true);
        if (context.hasValue(android.support.design.R.styleable.TextInputLayout_android_textColorHint))
        {
            attributeset = context.getColorStateList(android.support.design.R.styleable.TextInputLayout_android_textColorHint);
            mFocusedTextColor = attributeset;
            mDefaultTextColor = attributeset;
        }
        if (context.getResourceId(android.support.design.R.styleable.TextInputLayout_hintTextAppearance, -1) != -1)
        {
            setHintTextAppearance(context.getResourceId(android.support.design.R.styleable.TextInputLayout_hintTextAppearance, 0));
        }
        mErrorTextAppearance = context.getResourceId(android.support.design.R.styleable.TextInputLayout_errorTextAppearance, 0);
        boolean flag = context.getBoolean(android.support.design.R.styleable.TextInputLayout_errorEnabled, false);
        boolean flag1 = context.getBoolean(android.support.design.R.styleable.TextInputLayout_counterEnabled, false);
        setCounterMaxLength(context.getInt(android.support.design.R.styleable.TextInputLayout_counterMaxLength, -1));
        mCounterTextAppearance = context.getResourceId(android.support.design.R.styleable.TextInputLayout_counterTextAppearance, 0);
        mCounterOverflowTextAppearance = context.getResourceId(android.support.design.R.styleable.TextInputLayout_counterOverflowTextAppearance, 0);
        mPasswordToggleEnabled = context.getBoolean(android.support.design.R.styleable.TextInputLayout_passwordToggleEnabled, false);
        mPasswordToggleDrawable = context.getDrawable(android.support.design.R.styleable.TextInputLayout_passwordToggleDrawable);
        mPasswordToggleContentDesc = context.getText(android.support.design.R.styleable.TextInputLayout_passwordToggleContentDescription);
        if (context.hasValue(android.support.design.R.styleable.TextInputLayout_passwordToggleTint))
        {
            mHasPasswordToggleTintList = true;
            mPasswordToggleTintList = context.getColorStateList(android.support.design.R.styleable.TextInputLayout_passwordToggleTint);
        }
        if (context.hasValue(android.support.design.R.styleable.TextInputLayout_passwordToggleTintMode))
        {
            mHasPasswordToggleTintMode = true;
            mPasswordToggleTintMode = ViewUtils.parseTintMode(context.getInt(android.support.design.R.styleable.TextInputLayout_passwordToggleTintMode, -1), null);
        }
        context.recycle();
        setErrorEnabled(flag);
        setCounterEnabled(flag1);
        applyPasswordToggleTint();
        if (ViewCompat.getImportantForAccessibility(this) == 0)
        {
            ViewCompat.setImportantForAccessibility(this, 1);
        }
        ViewCompat.setAccessibilityDelegate(this, new TextInputAccessibilityDelegate());
    }

    private void addIndicator(TextView textview, int i)
    {
        if (mIndicatorArea == null)
        {
            mIndicatorArea = new LinearLayout(getContext());
            mIndicatorArea.setOrientation(0);
            addView(mIndicatorArea, -1, -2);
            Space space = new Space(getContext());
            android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(0, 0, 1.0F);
            mIndicatorArea.addView(space, layoutparams);
            if (mEditText != null)
            {
                adjustIndicatorPadding();
            }
        }
        mIndicatorArea.setVisibility(0);
        mIndicatorArea.addView(textview, i);
        mIndicatorsAdded = mIndicatorsAdded + 1;
    }

    private void adjustIndicatorPadding()
    {
        ViewCompat.setPaddingRelative(mIndicatorArea, ViewCompat.getPaddingStart(mEditText), 0, ViewCompat.getPaddingEnd(mEditText), mEditText.getPaddingBottom());
    }

    private void applyPasswordToggleTint()
    {
        if (mPasswordToggleDrawable != null && (mHasPasswordToggleTintList || mHasPasswordToggleTintMode))
        {
            mPasswordToggleDrawable = DrawableCompat.wrap(mPasswordToggleDrawable).mutate();
            if (mHasPasswordToggleTintList)
            {
                DrawableCompat.setTintList(mPasswordToggleDrawable, mPasswordToggleTintList);
            }
            if (mHasPasswordToggleTintMode)
            {
                DrawableCompat.setTintMode(mPasswordToggleDrawable, mPasswordToggleTintMode);
            }
            if (mPasswordToggleView != null && mPasswordToggleView.getDrawable() != mPasswordToggleDrawable)
            {
                mPasswordToggleView.setImageDrawable(mPasswordToggleDrawable);
            }
        }
    }

    private static boolean arrayContains(int ai[], int i)
    {
        boolean flag1 = false;
        int k = ai.length;
        int j = 0;
        do
        {
label0:
            {
                boolean flag = flag1;
                if (j < k)
                {
                    if (ai[j] != i)
                    {
                        break label0;
                    }
                    flag = true;
                }
                return flag;
            }
            j++;
        } while (true);
    }

    private void collapseHint(boolean flag)
    {
        if (mAnimator != null && mAnimator.isRunning())
        {
            mAnimator.cancel();
        }
        if (flag && mHintAnimationEnabled)
        {
            animateToExpansionFraction(1.0F);
        } else
        {
            mCollapsingTextHelper.setExpansionFraction(1.0F);
        }
        mHintExpanded = false;
    }

    private void ensureBackgroundDrawableStateWorkaround()
    {
        int i = android.os.Build.VERSION.SDK_INT;
        Drawable drawable;
        if (i == 21 || i == 22)
        {
            if ((drawable = mEditText.getBackground()) != null && !mHasReconstructedEditTextBackground)
            {
                Drawable drawable1 = drawable.getConstantState().newDrawable();
                if (drawable instanceof DrawableContainer)
                {
                    mHasReconstructedEditTextBackground = DrawableUtils.setContainerConstantState((DrawableContainer)drawable, drawable1.getConstantState());
                }
                if (!mHasReconstructedEditTextBackground)
                {
                    ViewCompat.setBackground(mEditText, drawable1);
                    mHasReconstructedEditTextBackground = true;
                    return;
                }
            }
        }
    }

    private void expandHint(boolean flag)
    {
        if (mAnimator != null && mAnimator.isRunning())
        {
            mAnimator.cancel();
        }
        if (flag && mHintAnimationEnabled)
        {
            animateToExpansionFraction(0.0F);
        } else
        {
            mCollapsingTextHelper.setExpansionFraction(0.0F);
        }
        mHintExpanded = true;
    }

    private boolean hasPasswordTransformation()
    {
        return mEditText != null && (mEditText.getTransformationMethod() instanceof PasswordTransformationMethod);
    }

    private static void recursiveSetEnabled(ViewGroup viewgroup, boolean flag)
    {
        int i = 0;
        for (int j = viewgroup.getChildCount(); i < j; i++)
        {
            View view = viewgroup.getChildAt(i);
            view.setEnabled(flag);
            if (view instanceof ViewGroup)
            {
                recursiveSetEnabled((ViewGroup)view, flag);
            }
        }

    }

    private void removeIndicator(TextView textview)
    {
        if (mIndicatorArea != null)
        {
            mIndicatorArea.removeView(textview);
            int i = mIndicatorsAdded - 1;
            mIndicatorsAdded = i;
            if (i == 0)
            {
                mIndicatorArea.setVisibility(8);
            }
        }
    }

    private void setEditText(EditText edittext)
    {
        if (mEditText != null)
        {
            throw new IllegalArgumentException("We already have an EditText, can only have one");
        }
        if (!(edittext instanceof TextInputEditText))
        {
            Log.i("TextInputLayout", "EditText added is not a TextInputEditText. Please switch to using that class instead.");
        }
        mEditText = edittext;
        if (!hasPasswordTransformation())
        {
            mCollapsingTextHelper.setTypefaces(mEditText.getTypeface());
        }
        mCollapsingTextHelper.setExpandedTextSize(mEditText.getTextSize());
        int i = mEditText.getGravity();
        mCollapsingTextHelper.setCollapsedTextGravity(i & 0xffffff8f | 0x30);
        mCollapsingTextHelper.setExpandedTextGravity(i);
        mEditText.addTextChangedListener(new TextWatcher() {

            final TextInputLayout this$0;

            public void afterTextChanged(Editable editable)
            {
                TextInputLayout textinputlayout = TextInputLayout.this;
                boolean flag;
                if (!mRestoringSavedState)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                textinputlayout.updateLabelState(flag);
                if (mCounterEnabled)
                {
                    updateCounter(editable.length());
                }
            }

            public void beforeTextChanged(CharSequence charsequence, int j, int k, int l)
            {
            }

            public void onTextChanged(CharSequence charsequence, int j, int k, int l)
            {
            }

            
            {
                this$0 = TextInputLayout.this;
                super();
            }
        });
        if (mDefaultTextColor == null)
        {
            mDefaultTextColor = mEditText.getHintTextColors();
        }
        if (mHintEnabled && TextUtils.isEmpty(mHint))
        {
            setHint(mEditText.getHint());
            mEditText.setHint(null);
        }
        if (mCounterView != null)
        {
            updateCounter(mEditText.getText().length());
        }
        if (mIndicatorArea != null)
        {
            adjustIndicatorPadding();
        }
        updatePasswordToggleView();
        updateLabelState(false, true);
    }

    private void setError(final CharSequence error, boolean flag)
    {
        boolean flag1 = true;
        mError = error;
        if (!mErrorEnabled)
        {
            if (TextUtils.isEmpty(error))
            {
                return;
            }
            setErrorEnabled(true);
        }
        if (TextUtils.isEmpty(error))
        {
            flag1 = false;
        }
        mErrorShown = flag1;
        mErrorView.animate().cancel();
        if (!mErrorShown) goto _L2; else goto _L1
_L1:
        mErrorView.setText(error);
        mErrorView.setVisibility(0);
        if (flag)
        {
            if (mErrorView.getAlpha() == 1.0F)
            {
                mErrorView.setAlpha(0.0F);
            }
            mErrorView.animate().alpha(1.0F).setDuration(200L).setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR).setListener(new AnimatorListenerAdapter() {

                final TextInputLayout this$0;

                public void onAnimationStart(Animator animator)
                {
                    mErrorView.setVisibility(0);
                }

            
            {
                this$0 = TextInputLayout.this;
                super();
            }
            }).start();
        } else
        {
            mErrorView.setAlpha(1.0F);
        }
_L4:
        updateEditTextBackground();
        updateLabelState(flag);
        return;
_L2:
        if (mErrorView.getVisibility() == 0)
        {
            if (flag)
            {
                mErrorView.animate().alpha(0.0F).setDuration(200L).setInterpolator(AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR).setListener(new AnimatorListenerAdapter() {

                    final TextInputLayout this$0;
                    final CharSequence val$error;

                    public void onAnimationEnd(Animator animator)
                    {
                        mErrorView.setText(error);
                        mErrorView.setVisibility(4);
                    }

            
            {
                this$0 = TextInputLayout.this;
                error = charsequence;
                super();
            }
                }).start();
            } else
            {
                mErrorView.setText(error);
                mErrorView.setVisibility(4);
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    private void setHintInternal(CharSequence charsequence)
    {
        mHint = charsequence;
        mCollapsingTextHelper.setText(charsequence);
    }

    private boolean shouldShowPasswordIcon()
    {
        return mPasswordToggleEnabled && (hasPasswordTransformation() || mPasswordToggledVisible);
    }

    private void updateEditTextBackground()
    {
        Drawable drawable1;
        if (mEditText != null)
        {
            if ((drawable1 = mEditText.getBackground()) != null)
            {
                ensureBackgroundDrawableStateWorkaround();
                Drawable drawable = drawable1;
                if (DrawableUtils.canSafelyMutateDrawable(drawable1))
                {
                    drawable = drawable1.mutate();
                }
                if (mErrorShown && mErrorView != null)
                {
                    drawable.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(mErrorView.getCurrentTextColor(), android.graphics.PorterDuff.Mode.SRC_IN));
                    return;
                }
                if (mCounterOverflowed && mCounterView != null)
                {
                    drawable.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(mCounterView.getCurrentTextColor(), android.graphics.PorterDuff.Mode.SRC_IN));
                    return;
                } else
                {
                    DrawableCompat.clearColorFilter(drawable);
                    mEditText.refreshDrawableState();
                    return;
                }
            }
        }
    }

    private void updateInputLayoutMargins()
    {
        android.widget.LinearLayout.LayoutParams layoutparams = (android.widget.LinearLayout.LayoutParams)mInputFrame.getLayoutParams();
        int i;
        if (mHintEnabled)
        {
            if (mTmpPaint == null)
            {
                mTmpPaint = new Paint();
            }
            mTmpPaint.setTypeface(mCollapsingTextHelper.getCollapsedTypeface());
            mTmpPaint.setTextSize(mCollapsingTextHelper.getCollapsedTextSize());
            i = (int)(-mTmpPaint.ascent());
        } else
        {
            i = 0;
        }
        if (i != layoutparams.topMargin)
        {
            layoutparams.topMargin = i;
            mInputFrame.requestLayout();
        }
    }

    private void updatePasswordToggleView()
    {
        if (mEditText != null)
        {
            if (shouldShowPasswordIcon())
            {
                if (mPasswordToggleView == null)
                {
                    mPasswordToggleView = (CheckableImageButton)LayoutInflater.from(getContext()).inflate(android.support.design.R.layout.design_text_input_password_icon, mInputFrame, false);
                    mPasswordToggleView.setImageDrawable(mPasswordToggleDrawable);
                    mPasswordToggleView.setContentDescription(mPasswordToggleContentDesc);
                    mInputFrame.addView(mPasswordToggleView);
                    mPasswordToggleView.setOnClickListener(new android.view.View.OnClickListener() {

                        final TextInputLayout this$0;

                        public void onClick(View view)
                        {
                            passwordVisibilityToggleRequested();
                        }

            
            {
                this$0 = TextInputLayout.this;
                super();
            }
                    });
                }
                if (mEditText != null && ViewCompat.getMinimumHeight(mEditText) <= 0)
                {
                    mEditText.setMinimumHeight(ViewCompat.getMinimumHeight(mPasswordToggleView));
                }
                mPasswordToggleView.setVisibility(0);
                mPasswordToggleView.setChecked(mPasswordToggledVisible);
                if (mPasswordToggleDummyDrawable == null)
                {
                    mPasswordToggleDummyDrawable = new ColorDrawable();
                }
                mPasswordToggleDummyDrawable.setBounds(0, 0, mPasswordToggleView.getMeasuredWidth(), 1);
                Drawable adrawable[] = TextViewCompat.getCompoundDrawablesRelative(mEditText);
                if (adrawable[2] != mPasswordToggleDummyDrawable)
                {
                    mOriginalEditTextEndDrawable = adrawable[2];
                }
                TextViewCompat.setCompoundDrawablesRelative(mEditText, adrawable[0], adrawable[1], mPasswordToggleDummyDrawable, adrawable[3]);
                mPasswordToggleView.setPadding(mEditText.getPaddingLeft(), mEditText.getPaddingTop(), mEditText.getPaddingRight(), mEditText.getPaddingBottom());
                return;
            }
            if (mPasswordToggleView != null && mPasswordToggleView.getVisibility() == 0)
            {
                mPasswordToggleView.setVisibility(8);
            }
            if (mPasswordToggleDummyDrawable != null)
            {
                Drawable adrawable1[] = TextViewCompat.getCompoundDrawablesRelative(mEditText);
                if (adrawable1[2] == mPasswordToggleDummyDrawable)
                {
                    TextViewCompat.setCompoundDrawablesRelative(mEditText, adrawable1[0], adrawable1[1], mOriginalEditTextEndDrawable, adrawable1[3]);
                    mPasswordToggleDummyDrawable = null;
                    return;
                }
            }
        }
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutparams)
    {
        if (view instanceof EditText)
        {
            android.widget.FrameLayout.LayoutParams layoutparams1 = new android.widget.FrameLayout.LayoutParams(layoutparams);
            layoutparams1.gravity = layoutparams1.gravity & 0xffffff8f | 0x10;
            mInputFrame.addView(view, layoutparams1);
            mInputFrame.setLayoutParams(layoutparams);
            updateInputLayoutMargins();
            setEditText((EditText)view);
            return;
        } else
        {
            super.addView(view, i, layoutparams);
            return;
        }
    }

    void animateToExpansionFraction(float f)
    {
        if (mCollapsingTextHelper.getExpansionFraction() == f)
        {
            return;
        }
        if (mAnimator == null)
        {
            mAnimator = new ValueAnimator();
            mAnimator.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
            mAnimator.setDuration(200L);
            mAnimator.addUpdateListener(new android.animation.ValueAnimator.AnimatorUpdateListener() {

                final TextInputLayout this$0;

                public void onAnimationUpdate(ValueAnimator valueanimator)
                {
                    mCollapsingTextHelper.setExpansionFraction(((Float)valueanimator.getAnimatedValue()).floatValue());
                }

            
            {
                this$0 = TextInputLayout.this;
                super();
            }
            });
        }
        mAnimator.setFloatValues(new float[] {
            mCollapsingTextHelper.getExpansionFraction(), f
        });
        mAnimator.start();
    }

    protected void dispatchRestoreInstanceState(SparseArray sparsearray)
    {
        mRestoringSavedState = true;
        super.dispatchRestoreInstanceState(sparsearray);
        mRestoringSavedState = false;
    }

    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        if (mHintEnabled)
        {
            mCollapsingTextHelper.draw(canvas);
        }
    }

    protected void drawableStateChanged()
    {
        boolean flag1 = true;
        if (mInDrawableStateChanged)
        {
            return;
        }
        mInDrawableStateChanged = true;
        super.drawableStateChanged();
        int ai[] = getDrawableState();
        boolean flag = false;
        if (!ViewCompat.isLaidOut(this) || !isEnabled())
        {
            flag1 = false;
        }
        updateLabelState(flag1);
        updateEditTextBackground();
        if (mCollapsingTextHelper != null)
        {
            flag = false | mCollapsingTextHelper.setState(ai);
        }
        if (flag)
        {
            invalidate();
        }
        mInDrawableStateChanged = false;
    }

    public int getCounterMaxLength()
    {
        return mCounterMaxLength;
    }

    public EditText getEditText()
    {
        return mEditText;
    }

    public CharSequence getError()
    {
        if (mErrorEnabled)
        {
            return mError;
        } else
        {
            return null;
        }
    }

    public CharSequence getHint()
    {
        if (mHintEnabled)
        {
            return mHint;
        } else
        {
            return null;
        }
    }

    public CharSequence getPasswordVisibilityToggleContentDescription()
    {
        return mPasswordToggleContentDesc;
    }

    public Drawable getPasswordVisibilityToggleDrawable()
    {
        return mPasswordToggleDrawable;
    }

    public Typeface getTypeface()
    {
        return mTypeface;
    }

    public boolean isCounterEnabled()
    {
        return mCounterEnabled;
    }

    public boolean isErrorEnabled()
    {
        return mErrorEnabled;
    }

    public boolean isHintAnimationEnabled()
    {
        return mHintAnimationEnabled;
    }

    public boolean isHintEnabled()
    {
        return mHintEnabled;
    }

    final boolean isHintExpanded()
    {
        return mHintExpanded;
    }

    public boolean isPasswordVisibilityToggleEnabled()
    {
        return mPasswordToggleEnabled;
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        if (mHintEnabled && mEditText != null)
        {
            Rect rect = mTmpRect;
            ViewGroupUtils.getDescendantRect(this, mEditText, rect);
            i = rect.left + mEditText.getCompoundPaddingLeft();
            k = rect.right - mEditText.getCompoundPaddingRight();
            mCollapsingTextHelper.setExpandedBounds(i, rect.top + mEditText.getCompoundPaddingTop(), k, rect.bottom - mEditText.getCompoundPaddingBottom());
            mCollapsingTextHelper.setCollapsedBounds(i, getPaddingTop(), k, l - j - getPaddingBottom());
            mCollapsingTextHelper.recalculate();
        }
    }

    protected void onMeasure(int i, int j)
    {
        updatePasswordToggleView();
        super.onMeasure(i, j);
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        if (!(parcelable instanceof SavedState))
        {
            super.onRestoreInstanceState(parcelable);
            return;
        } else
        {
            parcelable = (SavedState)parcelable;
            super.onRestoreInstanceState(parcelable.getSuperState());
            setError(((SavedState) (parcelable)).error);
            requestLayout();
            return;
        }
    }

    public Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        if (mErrorShown)
        {
            savedstate.error = getError();
        }
        return savedstate;
    }

    void passwordVisibilityToggleRequested()
    {
        if (mPasswordToggleEnabled)
        {
            int i = mEditText.getSelectionEnd();
            if (hasPasswordTransformation())
            {
                mEditText.setTransformationMethod(null);
                mPasswordToggledVisible = true;
            } else
            {
                mEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                mPasswordToggledVisible = false;
            }
            mPasswordToggleView.setChecked(mPasswordToggledVisible);
            mEditText.setSelection(i);
        }
    }

    public void setCounterEnabled(boolean flag)
    {
        if (mCounterEnabled != flag)
        {
            if (flag)
            {
                mCounterView = new AppCompatTextView(getContext());
                mCounterView.setId(android.support.design.R.id.textinput_counter);
                if (mTypeface != null)
                {
                    mCounterView.setTypeface(mTypeface);
                }
                mCounterView.setMaxLines(1);
                try
                {
                    TextViewCompat.setTextAppearance(mCounterView, mCounterTextAppearance);
                }
                catch (Exception exception)
                {
                    TextViewCompat.setTextAppearance(mCounterView, android.support.v7.appcompat.R.style.TextAppearance_AppCompat_Caption);
                    mCounterView.setTextColor(ContextCompat.getColor(getContext(), android.support.design.R.color.design_textinput_error_color_light));
                }
                addIndicator(mCounterView, -1);
                if (mEditText == null)
                {
                    updateCounter(0);
                } else
                {
                    updateCounter(mEditText.getText().length());
                }
            } else
            {
                removeIndicator(mCounterView);
                mCounterView = null;
            }
            mCounterEnabled = flag;
        }
    }

    public void setCounterMaxLength(int i)
    {
        if (mCounterMaxLength != i)
        {
            if (i > 0)
            {
                mCounterMaxLength = i;
            } else
            {
                mCounterMaxLength = -1;
            }
            if (mCounterEnabled)
            {
                if (mEditText == null)
                {
                    i = 0;
                } else
                {
                    i = mEditText.getText().length();
                }
                updateCounter(i);
            }
        }
    }

    public void setEnabled(boolean flag)
    {
        recursiveSetEnabled(this, flag);
        super.setEnabled(flag);
    }

    public void setError(CharSequence charsequence)
    {
        boolean flag;
        if (ViewCompat.isLaidOut(this) && isEnabled() && (mErrorView == null || !TextUtils.equals(mErrorView.getText(), charsequence)))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        setError(charsequence, flag);
    }

    public void setErrorEnabled(boolean flag)
    {
        if (mErrorEnabled == flag) goto _L2; else goto _L1
_L1:
        boolean flag2;
        if (mErrorView != null)
        {
            mErrorView.animate().cancel();
        }
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_190;
        }
        mErrorView = new AppCompatTextView(getContext());
        mErrorView.setId(android.support.design.R.id.textinput_error);
        if (mTypeface != null)
        {
            mErrorView.setTypeface(mTypeface);
        }
        flag2 = false;
        TextViewCompat.setTextAppearance(mErrorView, mErrorTextAppearance);
        boolean flag1 = flag2;
        int i;
        if (android.os.Build.VERSION.SDK_INT < 23)
        {
            break MISSING_BLOCK_LABEL_122;
        }
        i = mErrorView.getTextColors().getDefaultColor();
        flag1 = flag2;
        if (i == -65281)
        {
            flag1 = true;
        }
_L3:
        if (flag1)
        {
            TextViewCompat.setTextAppearance(mErrorView, android.support.v7.appcompat.R.style.TextAppearance_AppCompat_Caption);
            mErrorView.setTextColor(ContextCompat.getColor(getContext(), android.support.design.R.color.design_textinput_error_color_light));
        }
        mErrorView.setVisibility(4);
        ViewCompat.setAccessibilityLiveRegion(mErrorView, 1);
        addIndicator(mErrorView, 0);
_L4:
        mErrorEnabled = flag;
_L2:
        return;
        Exception exception;
        exception;
        flag1 = true;
          goto _L3
        mErrorShown = false;
        updateEditTextBackground();
        removeIndicator(mErrorView);
        mErrorView = null;
          goto _L4
    }

    public void setErrorTextAppearance(int i)
    {
        mErrorTextAppearance = i;
        if (mErrorView != null)
        {
            TextViewCompat.setTextAppearance(mErrorView, i);
        }
    }

    public void setHint(CharSequence charsequence)
    {
        if (mHintEnabled)
        {
            setHintInternal(charsequence);
            sendAccessibilityEvent(2048);
        }
    }

    public void setHintAnimationEnabled(boolean flag)
    {
        mHintAnimationEnabled = flag;
    }

    public void setHintEnabled(boolean flag)
    {
        if (flag == mHintEnabled) goto _L2; else goto _L1
_L1:
        CharSequence charsequence;
        mHintEnabled = flag;
        charsequence = mEditText.getHint();
        if (mHintEnabled) goto _L4; else goto _L3
_L3:
        if (!TextUtils.isEmpty(mHint) && TextUtils.isEmpty(charsequence))
        {
            mEditText.setHint(mHint);
        }
        setHintInternal(null);
_L6:
        if (mEditText != null)
        {
            updateInputLayoutMargins();
        }
_L2:
        return;
_L4:
        if (!TextUtils.isEmpty(charsequence))
        {
            if (TextUtils.isEmpty(mHint))
            {
                setHint(charsequence);
            }
            mEditText.setHint(null);
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public void setHintTextAppearance(int i)
    {
        mCollapsingTextHelper.setCollapsedTextAppearance(i);
        mFocusedTextColor = mCollapsingTextHelper.getCollapsedTextColor();
        if (mEditText != null)
        {
            updateLabelState(false);
            updateInputLayoutMargins();
        }
    }

    public void setPasswordVisibilityToggleContentDescription(int i)
    {
        CharSequence charsequence;
        if (i != 0)
        {
            charsequence = getResources().getText(i);
        } else
        {
            charsequence = null;
        }
        setPasswordVisibilityToggleContentDescription(charsequence);
    }

    public void setPasswordVisibilityToggleContentDescription(CharSequence charsequence)
    {
        mPasswordToggleContentDesc = charsequence;
        if (mPasswordToggleView != null)
        {
            mPasswordToggleView.setContentDescription(charsequence);
        }
    }

    public void setPasswordVisibilityToggleDrawable(int i)
    {
        Drawable drawable;
        if (i != 0)
        {
            drawable = AppCompatResources.getDrawable(getContext(), i);
        } else
        {
            drawable = null;
        }
        setPasswordVisibilityToggleDrawable(drawable);
    }

    public void setPasswordVisibilityToggleDrawable(Drawable drawable)
    {
        mPasswordToggleDrawable = drawable;
        if (mPasswordToggleView != null)
        {
            mPasswordToggleView.setImageDrawable(drawable);
        }
    }

    public void setPasswordVisibilityToggleEnabled(boolean flag)
    {
        if (mPasswordToggleEnabled != flag)
        {
            mPasswordToggleEnabled = flag;
            if (!flag && mPasswordToggledVisible && mEditText != null)
            {
                mEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            mPasswordToggledVisible = false;
            updatePasswordToggleView();
        }
    }

    public void setPasswordVisibilityToggleTintList(ColorStateList colorstatelist)
    {
        mPasswordToggleTintList = colorstatelist;
        mHasPasswordToggleTintList = true;
        applyPasswordToggleTint();
    }

    public void setPasswordVisibilityToggleTintMode(android.graphics.PorterDuff.Mode mode)
    {
        mPasswordToggleTintMode = mode;
        mHasPasswordToggleTintMode = true;
        applyPasswordToggleTint();
    }

    public void setTypeface(Typeface typeface)
    {
        if (typeface != mTypeface)
        {
            mTypeface = typeface;
            mCollapsingTextHelper.setTypefaces(typeface);
            if (mCounterView != null)
            {
                mCounterView.setTypeface(typeface);
            }
            if (mErrorView != null)
            {
                mErrorView.setTypeface(typeface);
            }
        }
    }

    void updateCounter(int i)
    {
        boolean flag1 = mCounterOverflowed;
        if (mCounterMaxLength == -1)
        {
            mCounterView.setText(String.valueOf(i));
            mCounterOverflowed = false;
        } else
        {
            boolean flag;
            if (i > mCounterMaxLength)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            mCounterOverflowed = flag;
            if (flag1 != mCounterOverflowed)
            {
                TextView textview = mCounterView;
                int j;
                if (mCounterOverflowed)
                {
                    j = mCounterOverflowTextAppearance;
                } else
                {
                    j = mCounterTextAppearance;
                }
                TextViewCompat.setTextAppearance(textview, j);
            }
            mCounterView.setText(getContext().getString(android.support.design.R.string.character_counter_pattern, new Object[] {
                Integer.valueOf(i), Integer.valueOf(mCounterMaxLength)
            }));
        }
        if (mEditText != null && flag1 != mCounterOverflowed)
        {
            updateLabelState(false);
            updateEditTextBackground();
        }
    }

    void updateLabelState(boolean flag)
    {
        updateLabelState(flag, false);
    }

    void updateLabelState(boolean flag, boolean flag1)
    {
        boolean flag4;
        boolean flag5;
        flag4 = isEnabled();
        boolean flag2;
        boolean flag3;
        if (mEditText != null && !TextUtils.isEmpty(mEditText.getText()))
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        flag5 = arrayContains(getDrawableState(), 0x101009c);
        if (!TextUtils.isEmpty(getError()))
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        if (mDefaultTextColor != null)
        {
            mCollapsingTextHelper.setExpandedTextColor(mDefaultTextColor);
        }
        if (!flag4 || !mCounterOverflowed || mCounterView == null) goto _L2; else goto _L1
_L1:
        mCollapsingTextHelper.setCollapsedTextColor(mCounterView.getTextColors());
_L8:
        if (!flag2 && (!isEnabled() || !flag5 && !flag3)) goto _L4; else goto _L3
_L3:
        if (flag1 || mHintExpanded)
        {
            collapseHint(flag);
        }
_L6:
        return;
_L2:
        if (flag4 && flag5 && mFocusedTextColor != null)
        {
            mCollapsingTextHelper.setCollapsedTextColor(mFocusedTextColor);
        } else
        if (mDefaultTextColor != null)
        {
            mCollapsingTextHelper.setCollapsedTextColor(mDefaultTextColor);
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (!flag1 && mHintExpanded) goto _L6; else goto _L5
_L5:
        expandHint(flag);
        return;
        if (true) goto _L8; else goto _L7
_L7:
    }

}
