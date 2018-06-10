// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

// Referenced classes of package android.support.design.widget:
//            ThemeUtils, CollapsingTextHelper, AnimationUtils, ViewOffsetHelper, 
//            AppBarLayout, ViewGroupUtils, ViewUtils, MathUtils

public class CollapsingToolbarLayout extends FrameLayout
{
    public static class LayoutParams extends android.widget.FrameLayout.LayoutParams
    {

        public static final int COLLAPSE_MODE_OFF = 0;
        public static final int COLLAPSE_MODE_PARALLAX = 2;
        public static final int COLLAPSE_MODE_PIN = 1;
        private static final float DEFAULT_PARALLAX_MULTIPLIER = 0.5F;
        int mCollapseMode;
        float mParallaxMult;

        public int getCollapseMode()
        {
            return mCollapseMode;
        }

        public float getParallaxMultiplier()
        {
            return mParallaxMult;
        }

        public void setCollapseMode(int i)
        {
            mCollapseMode = i;
        }

        public void setParallaxMultiplier(float f)
        {
            mParallaxMult = f;
        }

        public LayoutParams(int i, int j)
        {
            super(i, j);
            mCollapseMode = 0;
            mParallaxMult = 0.5F;
        }

        public LayoutParams(int i, int j, int k)
        {
            super(i, j, k);
            mCollapseMode = 0;
            mParallaxMult = 0.5F;
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            mCollapseMode = 0;
            mParallaxMult = 0.5F;
            context = context.obtainStyledAttributes(attributeset, android.support.design.R.styleable.CollapsingToolbarLayout_Layout);
            mCollapseMode = context.getInt(android.support.design.R.styleable.CollapsingToolbarLayout_Layout_layout_collapseMode, 0);
            setParallaxMultiplier(context.getFloat(android.support.design.R.styleable.CollapsingToolbarLayout_Layout_layout_collapseParallaxMultiplier, 0.5F));
            context.recycle();
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            super(layoutparams);
            mCollapseMode = 0;
            mParallaxMult = 0.5F;
        }

        public LayoutParams(android.view.ViewGroup.MarginLayoutParams marginlayoutparams)
        {
            super(marginlayoutparams);
            mCollapseMode = 0;
            mParallaxMult = 0.5F;
        }

        public LayoutParams(android.widget.FrameLayout.LayoutParams layoutparams)
        {
            super(layoutparams);
            mCollapseMode = 0;
            mParallaxMult = 0.5F;
        }
    }

    private class OffsetUpdateListener
        implements AppBarLayout.OnOffsetChangedListener
    {

        final CollapsingToolbarLayout this$0;

        public void onOffsetChanged(AppBarLayout appbarlayout, int i)
        {
            LayoutParams layoutparams;
            ViewOffsetHelper viewoffsethelper;
            int j;
            mCurrentOffset = i;
            int k;
            int i1;
            if (mLastInsets != null)
            {
                j = mLastInsets.getSystemWindowInsetTop();
            } else
            {
                j = 0;
            }
            k = 0;
            i1 = getChildCount();
            if (k >= i1)
            {
                break MISSING_BLOCK_LABEL_158;
            }
            appbarlayout = getChildAt(k);
            layoutparams = (LayoutParams)appbarlayout.getLayoutParams();
            viewoffsethelper = CollapsingToolbarLayout.getViewOffsetHelper(appbarlayout);
            switch (layoutparams.mCollapseMode)
            {
            default:
                break;

            case 1: // '\001'
                break; /* Loop/switch isn't completed */

            case 2: // '\002'
                break;
            }
            break MISSING_BLOCK_LABEL_138;
_L4:
            k++;
            if (true) goto _L2; else goto _L1
_L2:
            break MISSING_BLOCK_LABEL_42;
_L1:
            viewoffsethelper.setTopAndBottomOffset(MathUtils.constrain(-i, 0, getMaxOffsetForPinChild(appbarlayout)));
            continue; /* Loop/switch isn't completed */
            viewoffsethelper.setTopAndBottomOffset(Math.round((float)(-i) * layoutparams.mParallaxMult));
            if (true) goto _L4; else goto _L3
_L3:
            updateScrimVisibility();
            if (mStatusBarScrim != null && j > 0)
            {
                ViewCompat.postInvalidateOnAnimation(CollapsingToolbarLayout.this);
            }
            int l = getHeight();
            int j1 = ViewCompat.getMinimumHeight(CollapsingToolbarLayout.this);
            mCollapsingTextHelper.setExpansionFraction((float)Math.abs(i) / (float)(l - j1 - j));
            return;
        }

        OffsetUpdateListener()
        {
            this$0 = CollapsingToolbarLayout.this;
            super();
        }
    }


    private static final int DEFAULT_SCRIM_ANIMATION_DURATION = 600;
    final CollapsingTextHelper mCollapsingTextHelper;
    private boolean mCollapsingTitleEnabled;
    private Drawable mContentScrim;
    int mCurrentOffset;
    private boolean mDrawCollapsingTitle;
    private View mDummyView;
    private int mExpandedMarginBottom;
    private int mExpandedMarginEnd;
    private int mExpandedMarginStart;
    private int mExpandedMarginTop;
    WindowInsetsCompat mLastInsets;
    private AppBarLayout.OnOffsetChangedListener mOnOffsetChangedListener;
    private boolean mRefreshToolbar;
    private int mScrimAlpha;
    private long mScrimAnimationDuration;
    private ValueAnimator mScrimAnimator;
    private int mScrimVisibleHeightTrigger;
    private boolean mScrimsAreShown;
    Drawable mStatusBarScrim;
    private final Rect mTmpRect;
    private Toolbar mToolbar;
    private View mToolbarDirectChild;
    private int mToolbarId;

    public CollapsingToolbarLayout(Context context)
    {
        this(context, null);
    }

    public CollapsingToolbarLayout(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public CollapsingToolbarLayout(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mRefreshToolbar = true;
        mTmpRect = new Rect();
        mScrimVisibleHeightTrigger = -1;
        ThemeUtils.checkAppCompatTheme(context);
        mCollapsingTextHelper = new CollapsingTextHelper(this);
        mCollapsingTextHelper.setTextSizeInterpolator(AnimationUtils.DECELERATE_INTERPOLATOR);
        context = context.obtainStyledAttributes(attributeset, android.support.design.R.styleable.CollapsingToolbarLayout, i, android.support.design.R.style.Widget_Design_CollapsingToolbar);
        mCollapsingTextHelper.setExpandedTextGravity(context.getInt(android.support.design.R.styleable.CollapsingToolbarLayout_expandedTitleGravity, 0x800053));
        mCollapsingTextHelper.setCollapsedTextGravity(context.getInt(android.support.design.R.styleable.CollapsingToolbarLayout_collapsedTitleGravity, 0x800013));
        i = context.getDimensionPixelSize(android.support.design.R.styleable.CollapsingToolbarLayout_expandedTitleMargin, 0);
        mExpandedMarginBottom = i;
        mExpandedMarginEnd = i;
        mExpandedMarginTop = i;
        mExpandedMarginStart = i;
        if (context.hasValue(android.support.design.R.styleable.CollapsingToolbarLayout_expandedTitleMarginStart))
        {
            mExpandedMarginStart = context.getDimensionPixelSize(android.support.design.R.styleable.CollapsingToolbarLayout_expandedTitleMarginStart, 0);
        }
        if (context.hasValue(android.support.design.R.styleable.CollapsingToolbarLayout_expandedTitleMarginEnd))
        {
            mExpandedMarginEnd = context.getDimensionPixelSize(android.support.design.R.styleable.CollapsingToolbarLayout_expandedTitleMarginEnd, 0);
        }
        if (context.hasValue(android.support.design.R.styleable.CollapsingToolbarLayout_expandedTitleMarginTop))
        {
            mExpandedMarginTop = context.getDimensionPixelSize(android.support.design.R.styleable.CollapsingToolbarLayout_expandedTitleMarginTop, 0);
        }
        if (context.hasValue(android.support.design.R.styleable.CollapsingToolbarLayout_expandedTitleMarginBottom))
        {
            mExpandedMarginBottom = context.getDimensionPixelSize(android.support.design.R.styleable.CollapsingToolbarLayout_expandedTitleMarginBottom, 0);
        }
        mCollapsingTitleEnabled = context.getBoolean(android.support.design.R.styleable.CollapsingToolbarLayout_titleEnabled, true);
        setTitle(context.getText(android.support.design.R.styleable.CollapsingToolbarLayout_title));
        mCollapsingTextHelper.setExpandedTextAppearance(android.support.design.R.style.TextAppearance_Design_CollapsingToolbar_Expanded);
        mCollapsingTextHelper.setCollapsedTextAppearance(android.support.v7.appcompat.R.style.TextAppearance_AppCompat_Widget_ActionBar_Title);
        if (context.hasValue(android.support.design.R.styleable.CollapsingToolbarLayout_expandedTitleTextAppearance))
        {
            mCollapsingTextHelper.setExpandedTextAppearance(context.getResourceId(android.support.design.R.styleable.CollapsingToolbarLayout_expandedTitleTextAppearance, 0));
        }
        if (context.hasValue(android.support.design.R.styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance))
        {
            mCollapsingTextHelper.setCollapsedTextAppearance(context.getResourceId(android.support.design.R.styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance, 0));
        }
        mScrimVisibleHeightTrigger = context.getDimensionPixelSize(android.support.design.R.styleable.CollapsingToolbarLayout_scrimVisibleHeightTrigger, -1);
        mScrimAnimationDuration = context.getInt(android.support.design.R.styleable.CollapsingToolbarLayout_scrimAnimationDuration, 600);
        setContentScrim(context.getDrawable(android.support.design.R.styleable.CollapsingToolbarLayout_contentScrim));
        setStatusBarScrim(context.getDrawable(android.support.design.R.styleable.CollapsingToolbarLayout_statusBarScrim));
        mToolbarId = context.getResourceId(android.support.design.R.styleable.CollapsingToolbarLayout_toolbarId, -1);
        context.recycle();
        setWillNotDraw(false);
        ViewCompat.setOnApplyWindowInsetsListener(this, new OnApplyWindowInsetsListener() {

            final CollapsingToolbarLayout this$0;

            public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowinsetscompat)
            {
                return onWindowInsetChanged(windowinsetscompat);
            }

            
            {
                this$0 = CollapsingToolbarLayout.this;
                super();
            }
        });
    }

    private void animateScrim(int i)
    {
        ensureToolbar();
        if (mScrimAnimator != null) goto _L2; else goto _L1
_L1:
        mScrimAnimator = new ValueAnimator();
        mScrimAnimator.setDuration(mScrimAnimationDuration);
        ValueAnimator valueanimator = mScrimAnimator;
        android.view.animation.Interpolator interpolator;
        if (i > mScrimAlpha)
        {
            interpolator = AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR;
        } else
        {
            interpolator = AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR;
        }
        valueanimator.setInterpolator(interpolator);
        mScrimAnimator.addUpdateListener(new android.animation.ValueAnimator.AnimatorUpdateListener() {

            final CollapsingToolbarLayout this$0;

            public void onAnimationUpdate(ValueAnimator valueanimator1)
            {
                setScrimAlpha(((Integer)valueanimator1.getAnimatedValue()).intValue());
            }

            
            {
                this$0 = CollapsingToolbarLayout.this;
                super();
            }
        });
_L4:
        mScrimAnimator.setIntValues(new int[] {
            mScrimAlpha, i
        });
        mScrimAnimator.start();
        return;
_L2:
        if (mScrimAnimator.isRunning())
        {
            mScrimAnimator.cancel();
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    private void ensureToolbar()
    {
        if (!mRefreshToolbar)
        {
            return;
        }
        mToolbar = null;
        mToolbarDirectChild = null;
        if (mToolbarId != -1)
        {
            mToolbar = (Toolbar)findViewById(mToolbarId);
            if (mToolbar != null)
            {
                mToolbarDirectChild = findDirectChild(mToolbar);
            }
        }
        if (mToolbar != null) goto _L2; else goto _L1
_L1:
        Object obj1;
        int i;
        int j;
        obj1 = null;
        i = 0;
        j = getChildCount();
_L8:
        Object obj = obj1;
        if (i >= j) goto _L4; else goto _L3
_L3:
        obj = getChildAt(i);
        if (!(obj instanceof Toolbar)) goto _L6; else goto _L5
_L5:
        obj = (Toolbar)obj;
_L4:
        mToolbar = ((Toolbar) (obj));
_L2:
        updateDummyView();
        mRefreshToolbar = false;
        return;
_L6:
        i++;
        if (true) goto _L8; else goto _L7
_L7:
    }

    private View findDirectChild(View view)
    {
        View view1 = view;
        for (view = view.getParent(); view != this && view != null; view = view.getParent())
        {
            if (view instanceof View)
            {
                view1 = (View)view;
            }
        }

        return view1;
    }

    private static int getHeightWithMargins(View view)
    {
        Object obj = view.getLayoutParams();
        if (obj instanceof android.view.ViewGroup.MarginLayoutParams)
        {
            obj = (android.view.ViewGroup.MarginLayoutParams)obj;
            return view.getHeight() + ((android.view.ViewGroup.MarginLayoutParams) (obj)).topMargin + ((android.view.ViewGroup.MarginLayoutParams) (obj)).bottomMargin;
        } else
        {
            return view.getHeight();
        }
    }

    static ViewOffsetHelper getViewOffsetHelper(View view)
    {
        ViewOffsetHelper viewoffsethelper1 = (ViewOffsetHelper)view.getTag(android.support.design.R.id.view_offset_helper);
        ViewOffsetHelper viewoffsethelper = viewoffsethelper1;
        if (viewoffsethelper1 == null)
        {
            viewoffsethelper = new ViewOffsetHelper(view);
            view.setTag(android.support.design.R.id.view_offset_helper, viewoffsethelper);
        }
        return viewoffsethelper;
    }

    private boolean isToolbarChild(View view)
    {
        if (mToolbarDirectChild != null && mToolbarDirectChild != this) goto _L2; else goto _L1
_L1:
        if (view != mToolbar) goto _L4; else goto _L3
_L3:
        return true;
_L4:
        return false;
_L2:
        if (view != mToolbarDirectChild)
        {
            return false;
        }
        if (true) goto _L3; else goto _L5
_L5:
    }

    private void updateDummyView()
    {
        if (!mCollapsingTitleEnabled && mDummyView != null)
        {
            ViewParent viewparent = mDummyView.getParent();
            if (viewparent instanceof ViewGroup)
            {
                ((ViewGroup)viewparent).removeView(mDummyView);
            }
        }
        if (mCollapsingTitleEnabled && mToolbar != null)
        {
            if (mDummyView == null)
            {
                mDummyView = new View(getContext());
            }
            if (mDummyView.getParent() == null)
            {
                mToolbar.addView(mDummyView, -1, -1);
            }
        }
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return layoutparams instanceof LayoutParams;
    }

    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        ensureToolbar();
        if (mToolbar == null && mContentScrim != null && mScrimAlpha > 0)
        {
            mContentScrim.mutate().setAlpha(mScrimAlpha);
            mContentScrim.draw(canvas);
        }
        if (mCollapsingTitleEnabled && mDrawCollapsingTitle)
        {
            mCollapsingTextHelper.draw(canvas);
        }
        if (mStatusBarScrim != null && mScrimAlpha > 0)
        {
            int i;
            if (mLastInsets != null)
            {
                i = mLastInsets.getSystemWindowInsetTop();
            } else
            {
                i = 0;
            }
            if (i > 0)
            {
                mStatusBarScrim.setBounds(0, -mCurrentOffset, getWidth(), i - mCurrentOffset);
                mStatusBarScrim.mutate().setAlpha(mScrimAlpha);
                mStatusBarScrim.draw(canvas);
            }
        }
    }

    protected boolean drawChild(Canvas canvas, View view, long l)
    {
        boolean flag1 = false;
        boolean flag = flag1;
        if (mContentScrim != null)
        {
            flag = flag1;
            if (mScrimAlpha > 0)
            {
                flag = flag1;
                if (isToolbarChild(view))
                {
                    mContentScrim.mutate().setAlpha(mScrimAlpha);
                    mContentScrim.draw(canvas);
                    flag = true;
                }
            }
        }
        return super.drawChild(canvas, view, l) || flag;
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        int ai[] = getDrawableState();
        boolean flag1 = false;
        Drawable drawable = mStatusBarScrim;
        boolean flag = flag1;
        if (drawable != null)
        {
            flag = flag1;
            if (drawable.isStateful())
            {
                flag = false | drawable.setState(ai);
            }
        }
        drawable = mContentScrim;
        flag1 = flag;
        if (drawable != null)
        {
            flag1 = flag;
            if (drawable.isStateful())
            {
                flag1 = flag | drawable.setState(ai);
            }
        }
        flag = flag1;
        if (mCollapsingTextHelper != null)
        {
            flag = flag1 | mCollapsingTextHelper.setState(ai);
        }
        if (flag)
        {
            invalidate();
        }
    }

    protected LayoutParams generateDefaultLayoutParams()
    {
        return new LayoutParams(-1, -1);
    }

    protected volatile android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return generateDefaultLayoutParams();
    }

    protected volatile android.widget.FrameLayout.LayoutParams generateDefaultLayoutParams()
    {
        return generateDefaultLayoutParams();
    }

    public volatile android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return generateLayoutParams(attributeset);
    }

    protected volatile android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return generateLayoutParams(layoutparams);
    }

    public android.widget.FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new LayoutParams(getContext(), attributeset);
    }

    protected android.widget.FrameLayout.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return new LayoutParams(layoutparams);
    }

    public int getCollapsedTitleGravity()
    {
        return mCollapsingTextHelper.getCollapsedTextGravity();
    }

    public Typeface getCollapsedTitleTypeface()
    {
        return mCollapsingTextHelper.getCollapsedTypeface();
    }

    public Drawable getContentScrim()
    {
        return mContentScrim;
    }

    public int getExpandedTitleGravity()
    {
        return mCollapsingTextHelper.getExpandedTextGravity();
    }

    public int getExpandedTitleMarginBottom()
    {
        return mExpandedMarginBottom;
    }

    public int getExpandedTitleMarginEnd()
    {
        return mExpandedMarginEnd;
    }

    public int getExpandedTitleMarginStart()
    {
        return mExpandedMarginStart;
    }

    public int getExpandedTitleMarginTop()
    {
        return mExpandedMarginTop;
    }

    public Typeface getExpandedTitleTypeface()
    {
        return mCollapsingTextHelper.getExpandedTypeface();
    }

    final int getMaxOffsetForPinChild(View view)
    {
        ViewOffsetHelper viewoffsethelper = getViewOffsetHelper(view);
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        return getHeight() - viewoffsethelper.getLayoutTop() - view.getHeight() - layoutparams.bottomMargin;
    }

    int getScrimAlpha()
    {
        return mScrimAlpha;
    }

    public long getScrimAnimationDuration()
    {
        return mScrimAnimationDuration;
    }

    public int getScrimVisibleHeightTrigger()
    {
        if (mScrimVisibleHeightTrigger >= 0)
        {
            return mScrimVisibleHeightTrigger;
        }
        int i;
        int j;
        if (mLastInsets != null)
        {
            i = mLastInsets.getSystemWindowInsetTop();
        } else
        {
            i = 0;
        }
        j = ViewCompat.getMinimumHeight(this);
        if (j > 0)
        {
            return Math.min(j * 2 + i, getHeight());
        } else
        {
            return getHeight() / 3;
        }
    }

    public Drawable getStatusBarScrim()
    {
        return mStatusBarScrim;
    }

    public CharSequence getTitle()
    {
        if (mCollapsingTitleEnabled)
        {
            return mCollapsingTextHelper.getText();
        } else
        {
            return null;
        }
    }

    public boolean isTitleEnabled()
    {
        return mCollapsingTitleEnabled;
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        ViewParent viewparent = getParent();
        if (viewparent instanceof AppBarLayout)
        {
            ViewCompat.setFitsSystemWindows(this, ViewCompat.getFitsSystemWindows((View)viewparent));
            if (mOnOffsetChangedListener == null)
            {
                mOnOffsetChangedListener = new OffsetUpdateListener();
            }
            ((AppBarLayout)viewparent).addOnOffsetChangedListener(mOnOffsetChangedListener);
            ViewCompat.requestApplyInsets(this);
        }
    }

    protected void onDetachedFromWindow()
    {
        ViewParent viewparent = getParent();
        if (mOnOffsetChangedListener != null && (viewparent instanceof AppBarLayout))
        {
            ((AppBarLayout)viewparent).removeOnOffsetChangedListener(mOnOffsetChangedListener);
        }
        super.onDetachedFromWindow();
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        if (mLastInsets != null)
        {
            int k1 = mLastInsets.getSystemWindowInsetTop();
            int i1 = 0;
            for (int i2 = getChildCount(); i1 < i2; i1++)
            {
                View view = getChildAt(i1);
                if (!ViewCompat.getFitsSystemWindows(view) && view.getTop() < k1)
                {
                    ViewCompat.offsetTopAndBottom(view, k1);
                }
            }

        }
        if (mCollapsingTitleEnabled && mDummyView != null)
        {
            if (ViewCompat.isAttachedToWindow(mDummyView) && mDummyView.getVisibility() == 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            mDrawCollapsingTitle = flag;
            if (mDrawCollapsingTitle)
            {
                Object obj;
                int j1;
                int l1;
                int j2;
                int k2;
                int l2;
                int i3;
                int j3;
                int k3;
                if (ViewCompat.getLayoutDirection(this) == 1)
                {
                    j1 = 1;
                } else
                {
                    j1 = 0;
                }
                if (mToolbarDirectChild != null)
                {
                    obj = mToolbarDirectChild;
                } else
                {
                    obj = mToolbar;
                }
                k2 = getMaxOffsetForPinChild(((View) (obj)));
                ViewGroupUtils.getDescendantRect(this, mDummyView, mTmpRect);
                obj = mCollapsingTextHelper;
                l2 = mTmpRect.left;
                if (j1 != 0)
                {
                    l1 = mToolbar.getTitleMarginEnd();
                } else
                {
                    l1 = mToolbar.getTitleMarginStart();
                }
                i3 = mTmpRect.top;
                j3 = mToolbar.getTitleMarginTop();
                k3 = mTmpRect.right;
                if (j1 != 0)
                {
                    j2 = mToolbar.getTitleMarginStart();
                } else
                {
                    j2 = mToolbar.getTitleMarginEnd();
                }
                ((CollapsingTextHelper) (obj)).setCollapsedBounds(l2 + l1, j3 + (i3 + k2), j2 + k3, (mTmpRect.bottom + k2) - mToolbar.getTitleMarginBottom());
                obj = mCollapsingTextHelper;
                if (j1 != 0)
                {
                    l1 = mExpandedMarginEnd;
                } else
                {
                    l1 = mExpandedMarginStart;
                }
                j2 = mTmpRect.top;
                k2 = mExpandedMarginTop;
                if (j1 != 0)
                {
                    j1 = mExpandedMarginStart;
                } else
                {
                    j1 = mExpandedMarginEnd;
                }
                ((CollapsingTextHelper) (obj)).setExpandedBounds(l1, k2 + j2, k - i - j1, l - j - mExpandedMarginBottom);
                mCollapsingTextHelper.recalculate();
            }
        }
        i = 0;
        for (j = getChildCount(); i < j; i++)
        {
            getViewOffsetHelper(getChildAt(i)).onViewLayout();
        }

        if (mToolbar != null)
        {
            if (mCollapsingTitleEnabled && TextUtils.isEmpty(mCollapsingTextHelper.getText()))
            {
                mCollapsingTextHelper.setText(mToolbar.getTitle());
            }
            if (mToolbarDirectChild == null || mToolbarDirectChild == this)
            {
                setMinimumHeight(getHeightWithMargins(mToolbar));
            } else
            {
                setMinimumHeight(getHeightWithMargins(mToolbarDirectChild));
            }
        }
        updateScrimVisibility();
    }

    protected void onMeasure(int i, int j)
    {
        ensureToolbar();
        super.onMeasure(i, j);
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
        if (mContentScrim != null)
        {
            mContentScrim.setBounds(0, 0, i, j);
        }
    }

    WindowInsetsCompat onWindowInsetChanged(WindowInsetsCompat windowinsetscompat)
    {
        WindowInsetsCompat windowinsetscompat1 = null;
        if (ViewCompat.getFitsSystemWindows(this))
        {
            windowinsetscompat1 = windowinsetscompat;
        }
        if (!ViewUtils.objectEquals(mLastInsets, windowinsetscompat1))
        {
            mLastInsets = windowinsetscompat1;
            requestLayout();
        }
        return windowinsetscompat.consumeSystemWindowInsets();
    }

    public void setCollapsedTitleGravity(int i)
    {
        mCollapsingTextHelper.setCollapsedTextGravity(i);
    }

    public void setCollapsedTitleTextAppearance(int i)
    {
        mCollapsingTextHelper.setCollapsedTextAppearance(i);
    }

    public void setCollapsedTitleTextColor(int i)
    {
        setCollapsedTitleTextColor(ColorStateList.valueOf(i));
    }

    public void setCollapsedTitleTextColor(ColorStateList colorstatelist)
    {
        mCollapsingTextHelper.setCollapsedTextColor(colorstatelist);
    }

    public void setCollapsedTitleTypeface(Typeface typeface)
    {
        mCollapsingTextHelper.setCollapsedTypeface(typeface);
    }

    public void setContentScrim(Drawable drawable)
    {
        Drawable drawable1 = null;
        if (mContentScrim != drawable)
        {
            if (mContentScrim != null)
            {
                mContentScrim.setCallback(null);
            }
            if (drawable != null)
            {
                drawable1 = drawable.mutate();
            }
            mContentScrim = drawable1;
            if (mContentScrim != null)
            {
                mContentScrim.setBounds(0, 0, getWidth(), getHeight());
                mContentScrim.setCallback(this);
                mContentScrim.setAlpha(mScrimAlpha);
            }
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void setContentScrimColor(int i)
    {
        setContentScrim(new ColorDrawable(i));
    }

    public void setContentScrimResource(int i)
    {
        setContentScrim(ContextCompat.getDrawable(getContext(), i));
    }

    public void setExpandedTitleColor(int i)
    {
        setExpandedTitleTextColor(ColorStateList.valueOf(i));
    }

    public void setExpandedTitleGravity(int i)
    {
        mCollapsingTextHelper.setExpandedTextGravity(i);
    }

    public void setExpandedTitleMargin(int i, int j, int k, int l)
    {
        mExpandedMarginStart = i;
        mExpandedMarginTop = j;
        mExpandedMarginEnd = k;
        mExpandedMarginBottom = l;
        requestLayout();
    }

    public void setExpandedTitleMarginBottom(int i)
    {
        mExpandedMarginBottom = i;
        requestLayout();
    }

    public void setExpandedTitleMarginEnd(int i)
    {
        mExpandedMarginEnd = i;
        requestLayout();
    }

    public void setExpandedTitleMarginStart(int i)
    {
        mExpandedMarginStart = i;
        requestLayout();
    }

    public void setExpandedTitleMarginTop(int i)
    {
        mExpandedMarginTop = i;
        requestLayout();
    }

    public void setExpandedTitleTextAppearance(int i)
    {
        mCollapsingTextHelper.setExpandedTextAppearance(i);
    }

    public void setExpandedTitleTextColor(ColorStateList colorstatelist)
    {
        mCollapsingTextHelper.setExpandedTextColor(colorstatelist);
    }

    public void setExpandedTitleTypeface(Typeface typeface)
    {
        mCollapsingTextHelper.setExpandedTypeface(typeface);
    }

    void setScrimAlpha(int i)
    {
        if (i != mScrimAlpha)
        {
            if (mContentScrim != null && mToolbar != null)
            {
                ViewCompat.postInvalidateOnAnimation(mToolbar);
            }
            mScrimAlpha = i;
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void setScrimAnimationDuration(long l)
    {
        mScrimAnimationDuration = l;
    }

    public void setScrimVisibleHeightTrigger(int i)
    {
        if (mScrimVisibleHeightTrigger != i)
        {
            mScrimVisibleHeightTrigger = i;
            updateScrimVisibility();
        }
    }

    public void setScrimsShown(boolean flag)
    {
        boolean flag1;
        if (ViewCompat.isLaidOut(this) && !isInEditMode())
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        setScrimsShown(flag, flag1);
    }

    public void setScrimsShown(boolean flag, boolean flag1)
    {
        char c = '\377';
        if (mScrimsAreShown != flag)
        {
            if (flag1)
            {
                if (!flag)
                {
                    c = '\0';
                }
                animateScrim(c);
            } else
            {
                if (!flag)
                {
                    c = '\0';
                }
                setScrimAlpha(c);
            }
            mScrimsAreShown = flag;
        }
    }

    public void setStatusBarScrim(Drawable drawable)
    {
        Drawable drawable1 = null;
        if (mStatusBarScrim != drawable)
        {
            if (mStatusBarScrim != null)
            {
                mStatusBarScrim.setCallback(null);
            }
            if (drawable != null)
            {
                drawable1 = drawable.mutate();
            }
            mStatusBarScrim = drawable1;
            if (mStatusBarScrim != null)
            {
                if (mStatusBarScrim.isStateful())
                {
                    mStatusBarScrim.setState(getDrawableState());
                }
                DrawableCompat.setLayoutDirection(mStatusBarScrim, ViewCompat.getLayoutDirection(this));
                drawable = mStatusBarScrim;
                boolean flag;
                if (getVisibility() == 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                drawable.setVisible(flag, false);
                mStatusBarScrim.setCallback(this);
                mStatusBarScrim.setAlpha(mScrimAlpha);
            }
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void setStatusBarScrimColor(int i)
    {
        setStatusBarScrim(new ColorDrawable(i));
    }

    public void setStatusBarScrimResource(int i)
    {
        setStatusBarScrim(ContextCompat.getDrawable(getContext(), i));
    }

    public void setTitle(CharSequence charsequence)
    {
        mCollapsingTextHelper.setText(charsequence);
    }

    public void setTitleEnabled(boolean flag)
    {
        if (flag != mCollapsingTitleEnabled)
        {
            mCollapsingTitleEnabled = flag;
            updateDummyView();
            requestLayout();
        }
    }

    public void setVisibility(int i)
    {
        super.setVisibility(i);
        boolean flag;
        if (i == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (mStatusBarScrim != null && mStatusBarScrim.isVisible() != flag)
        {
            mStatusBarScrim.setVisible(flag, false);
        }
        if (mContentScrim != null && mContentScrim.isVisible() != flag)
        {
            mContentScrim.setVisible(flag, false);
        }
    }

    final void updateScrimVisibility()
    {
        if (mContentScrim != null || mStatusBarScrim != null)
        {
            boolean flag;
            if (getHeight() + mCurrentOffset < getScrimVisibleHeightTrigger())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            setScrimsShown(flag);
        }
    }

    protected boolean verifyDrawable(Drawable drawable)
    {
        return super.verifyDrawable(drawable) || drawable == mContentScrim || drawable == mStatusBarScrim;
    }
}
