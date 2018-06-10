// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatImageHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import java.lang.annotation.Annotation;
import java.util.List;

// Referenced classes of package android.support.design.widget:
//            VisibilityAwareImageButton, ThemeUtils, ViewUtils, FloatingActionButtonImpl, 
//            FloatingActionButtonLollipop, BottomSheetBehavior, CoordinatorLayout, ViewGroupUtils, 
//            AppBarLayout, ShadowViewDelegate

public class FloatingActionButton extends VisibilityAwareImageButton
{
    public static class Behavior extends CoordinatorLayout.Behavior
    {

        private static final boolean AUTO_HIDE_DEFAULT = true;
        private boolean mAutoHideEnabled;
        private OnVisibilityChangedListener mInternalAutoHideListener;
        private Rect mTmpRect;

        private static boolean isBottomSheet(View view)
        {
            view = view.getLayoutParams();
            if (view instanceof CoordinatorLayout.LayoutParams)
            {
                return ((CoordinatorLayout.LayoutParams)view).getBehavior() instanceof BottomSheetBehavior;
            } else
            {
                return false;
            }
        }

        private void offsetIfNeeded(CoordinatorLayout coordinatorlayout, FloatingActionButton floatingactionbutton)
        {
            Rect rect = floatingactionbutton.mShadowPadding;
            if (rect != null && rect.centerX() > 0 && rect.centerY() > 0)
            {
                CoordinatorLayout.LayoutParams layoutparams = (CoordinatorLayout.LayoutParams)floatingactionbutton.getLayoutParams();
                int j = 0;
                int i = 0;
                if (floatingactionbutton.getRight() >= coordinatorlayout.getWidth() - layoutparams.rightMargin)
                {
                    i = rect.right;
                } else
                if (floatingactionbutton.getLeft() <= layoutparams.leftMargin)
                {
                    i = -rect.left;
                }
                if (floatingactionbutton.getBottom() >= coordinatorlayout.getHeight() - layoutparams.bottomMargin)
                {
                    j = rect.bottom;
                } else
                if (floatingactionbutton.getTop() <= layoutparams.topMargin)
                {
                    j = -rect.top;
                }
                if (j != 0)
                {
                    ViewCompat.offsetTopAndBottom(floatingactionbutton, j);
                }
                if (i != 0)
                {
                    ViewCompat.offsetLeftAndRight(floatingactionbutton, i);
                }
            }
        }

        private boolean shouldUpdateVisibility(View view, FloatingActionButton floatingactionbutton)
        {
            for (CoordinatorLayout.LayoutParams layoutparams = (CoordinatorLayout.LayoutParams)floatingactionbutton.getLayoutParams(); !mAutoHideEnabled || layoutparams.getAnchorId() != view.getId() || floatingactionbutton.getUserSetVisibility() != 0;)
            {
                return false;
            }

            return true;
        }

        private boolean updateFabVisibilityForAppBarLayout(CoordinatorLayout coordinatorlayout, AppBarLayout appbarlayout, FloatingActionButton floatingactionbutton)
        {
            if (!shouldUpdateVisibility(appbarlayout, floatingactionbutton))
            {
                return false;
            }
            if (mTmpRect == null)
            {
                mTmpRect = new Rect();
            }
            Rect rect = mTmpRect;
            ViewGroupUtils.getDescendantRect(coordinatorlayout, appbarlayout, rect);
            if (rect.bottom <= appbarlayout.getMinimumHeightForVisibleOverlappingContent())
            {
                floatingactionbutton.hide(mInternalAutoHideListener, false);
            } else
            {
                floatingactionbutton.show(mInternalAutoHideListener, false);
            }
            return true;
        }

        private boolean updateFabVisibilityForBottomSheet(View view, FloatingActionButton floatingactionbutton)
        {
            if (!shouldUpdateVisibility(view, floatingactionbutton))
            {
                return false;
            }
            CoordinatorLayout.LayoutParams layoutparams = (CoordinatorLayout.LayoutParams)floatingactionbutton.getLayoutParams();
            if (view.getTop() < floatingactionbutton.getHeight() / 2 + layoutparams.topMargin)
            {
                floatingactionbutton.hide(mInternalAutoHideListener, false);
            } else
            {
                floatingactionbutton.show(mInternalAutoHideListener, false);
            }
            return true;
        }

        public boolean getInsetDodgeRect(CoordinatorLayout coordinatorlayout, FloatingActionButton floatingactionbutton, Rect rect)
        {
            coordinatorlayout = floatingactionbutton.mShadowPadding;
            rect.set(floatingactionbutton.getLeft() + ((Rect) (coordinatorlayout)).left, floatingactionbutton.getTop() + ((Rect) (coordinatorlayout)).top, floatingactionbutton.getRight() - ((Rect) (coordinatorlayout)).right, floatingactionbutton.getBottom() - ((Rect) (coordinatorlayout)).bottom);
            return true;
        }

        public volatile boolean getInsetDodgeRect(CoordinatorLayout coordinatorlayout, View view, Rect rect)
        {
            return getInsetDodgeRect(coordinatorlayout, (FloatingActionButton)view, rect);
        }

        public boolean isAutoHideEnabled()
        {
            return mAutoHideEnabled;
        }

        public void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams layoutparams)
        {
            if (layoutparams.dodgeInsetEdges == 0)
            {
                layoutparams.dodgeInsetEdges = 80;
            }
        }

        public boolean onDependentViewChanged(CoordinatorLayout coordinatorlayout, FloatingActionButton floatingactionbutton, View view)
        {
            if (!(view instanceof AppBarLayout)) goto _L2; else goto _L1
_L1:
            updateFabVisibilityForAppBarLayout(coordinatorlayout, (AppBarLayout)view, floatingactionbutton);
_L4:
            return false;
_L2:
            if (isBottomSheet(view))
            {
                updateFabVisibilityForBottomSheet(view, floatingactionbutton);
            }
            if (true) goto _L4; else goto _L3
_L3:
        }

        public volatile boolean onDependentViewChanged(CoordinatorLayout coordinatorlayout, View view, View view1)
        {
            return onDependentViewChanged(coordinatorlayout, (FloatingActionButton)view, view1);
        }

        public boolean onLayoutChild(CoordinatorLayout coordinatorlayout, FloatingActionButton floatingactionbutton, int i)
        {
            List list = coordinatorlayout.getDependencies(floatingactionbutton);
            int j = 0;
            int k = list.size();
            do
            {
label0:
                {
                    if (j < k)
                    {
                        View view = (View)list.get(j);
                        if ((view instanceof AppBarLayout) ? !updateFabVisibilityForAppBarLayout(coordinatorlayout, (AppBarLayout)view, floatingactionbutton) : !isBottomSheet(view) || !updateFabVisibilityForBottomSheet(view, floatingactionbutton))
                        {
                            break label0;
                        }
                    }
                    coordinatorlayout.onLayoutChild(floatingactionbutton, i);
                    offsetIfNeeded(coordinatorlayout, floatingactionbutton);
                    return true;
                }
                j++;
            } while (true);
        }

        public volatile boolean onLayoutChild(CoordinatorLayout coordinatorlayout, View view, int i)
        {
            return onLayoutChild(coordinatorlayout, (FloatingActionButton)view, i);
        }

        public void setAutoHideEnabled(boolean flag)
        {
            mAutoHideEnabled = flag;
        }

        void setInternalAutoHideListener(OnVisibilityChangedListener onvisibilitychangedlistener)
        {
            mInternalAutoHideListener = onvisibilitychangedlistener;
        }

        public Behavior()
        {
            mAutoHideEnabled = true;
        }

        public Behavior(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            context = context.obtainStyledAttributes(attributeset, android.support.design.R.styleable.FloatingActionButton_Behavior_Layout);
            mAutoHideEnabled = context.getBoolean(android.support.design.R.styleable.FloatingActionButton_Behavior_Layout_behavior_autoHide, true);
            context.recycle();
        }
    }

    public static abstract class OnVisibilityChangedListener
    {

        public void onHidden(FloatingActionButton floatingactionbutton)
        {
        }

        public void onShown(FloatingActionButton floatingactionbutton)
        {
        }

        public OnVisibilityChangedListener()
        {
        }
    }

    private class ShadowDelegateImpl
        implements ShadowViewDelegate
    {

        final FloatingActionButton this$0;

        public float getRadius()
        {
            return (float)getSizeDimension() / 2.0F;
        }

        public boolean isCompatPaddingEnabled()
        {
            return mCompatPadding;
        }

        public void setBackgroundDrawable(Drawable drawable)
        {
            VisibilityAwareImageButton.this.setBackgroundDrawable(drawable);
        }

        public void setShadowPadding(int i, int j, int k, int l)
        {
            mShadowPadding.set(i, j, k, l);
            setPadding(mImagePadding + i, mImagePadding + j, mImagePadding + k, mImagePadding + l);
        }

        ShadowDelegateImpl()
        {
            this$0 = FloatingActionButton.this;
            super();
        }
    }

    public static interface Size
        extends Annotation
    {
    }


    private static final int AUTO_MINI_LARGEST_SCREEN_WIDTH = 470;
    private static final String LOG_TAG = "FloatingActionButton";
    public static final int SIZE_AUTO = -1;
    public static final int SIZE_MINI = 1;
    public static final int SIZE_NORMAL = 0;
    private ColorStateList mBackgroundTint;
    private android.graphics.PorterDuff.Mode mBackgroundTintMode;
    private int mBorderWidth;
    boolean mCompatPadding;
    private AppCompatImageHelper mImageHelper;
    int mImagePadding;
    private FloatingActionButtonImpl mImpl;
    private int mMaxImageSize;
    private int mRippleColor;
    final Rect mShadowPadding;
    private int mSize;
    private final Rect mTouchArea;

    public FloatingActionButton(Context context)
    {
        this(context, null);
    }

    public FloatingActionButton(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public FloatingActionButton(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mShadowPadding = new Rect();
        mTouchArea = new Rect();
        ThemeUtils.checkAppCompatTheme(context);
        context = context.obtainStyledAttributes(attributeset, android.support.design.R.styleable.FloatingActionButton, i, android.support.design.R.style.Widget_Design_FloatingActionButton);
        mBackgroundTint = context.getColorStateList(android.support.design.R.styleable.FloatingActionButton_backgroundTint);
        mBackgroundTintMode = ViewUtils.parseTintMode(context.getInt(android.support.design.R.styleable.FloatingActionButton_backgroundTintMode, -1), null);
        mRippleColor = context.getColor(android.support.design.R.styleable.FloatingActionButton_rippleColor, 0);
        mSize = context.getInt(android.support.design.R.styleable.FloatingActionButton_fabSize, -1);
        mBorderWidth = context.getDimensionPixelSize(android.support.design.R.styleable.FloatingActionButton_borderWidth, 0);
        float f = context.getDimension(android.support.design.R.styleable.FloatingActionButton_elevation, 0.0F);
        float f1 = context.getDimension(android.support.design.R.styleable.FloatingActionButton_pressedTranslationZ, 0.0F);
        mCompatPadding = context.getBoolean(android.support.design.R.styleable.FloatingActionButton_useCompatPadding, false);
        context.recycle();
        mImageHelper = new AppCompatImageHelper(this);
        mImageHelper.loadFromAttributes(attributeset, i);
        mMaxImageSize = (int)getResources().getDimension(android.support.design.R.dimen.design_fab_image_size);
        getImpl().setBackgroundDrawable(mBackgroundTint, mBackgroundTintMode, mRippleColor, mBorderWidth);
        getImpl().setElevation(f);
        getImpl().setPressedTranslationZ(f1);
    }

    private FloatingActionButtonImpl createImpl()
    {
        if (android.os.Build.VERSION.SDK_INT >= 21)
        {
            return new FloatingActionButtonLollipop(this, new ShadowDelegateImpl());
        } else
        {
            return new FloatingActionButtonImpl(this, new ShadowDelegateImpl());
        }
    }

    private FloatingActionButtonImpl getImpl()
    {
        if (mImpl == null)
        {
            mImpl = createImpl();
        }
        return mImpl;
    }

    private int getSizeDimension(int i)
    {
        Resources resources = getResources();
        switch (i)
        {
        case 0: // '\0'
        default:
            return resources.getDimensionPixelSize(android.support.design.R.dimen.design_fab_size_normal);

        case -1: 
            if (Math.max(resources.getConfiguration().screenWidthDp, resources.getConfiguration().screenHeightDp) < 470)
            {
                return getSizeDimension(1);
            } else
            {
                return getSizeDimension(0);
            }

        case 1: // '\001'
            return resources.getDimensionPixelSize(android.support.design.R.dimen.design_fab_size_mini);
        }
    }

    private static int resolveAdjustedSize(int i, int j)
    {
        int k = android.view.View.MeasureSpec.getMode(j);
        j = android.view.View.MeasureSpec.getSize(j);
        switch (k)
        {
        default:
            return i;

        case 0: // '\0'
            return i;

        case -2147483648: 
            return Math.min(i, j);

        case 1073741824: 
            return j;
        }
    }

    private FloatingActionButtonImpl.InternalVisibilityChangedListener wrapOnVisibilityChangedListener(final OnVisibilityChangedListener listener)
    {
        if (listener == null)
        {
            return null;
        } else
        {
            return new FloatingActionButtonImpl.InternalVisibilityChangedListener() {

                final FloatingActionButton this$0;
                final OnVisibilityChangedListener val$listener;

                public void onHidden()
                {
                    listener.onHidden(FloatingActionButton.this);
                }

                public void onShown()
                {
                    listener.onShown(FloatingActionButton.this);
                }

            
            {
                this$0 = FloatingActionButton.this;
                listener = onvisibilitychangedlistener;
                super();
            }
            };
        }
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        getImpl().onDrawableStateChanged(getDrawableState());
    }

    public ColorStateList getBackgroundTintList()
    {
        return mBackgroundTint;
    }

    public android.graphics.PorterDuff.Mode getBackgroundTintMode()
    {
        return mBackgroundTintMode;
    }

    public float getCompatElevation()
    {
        return getImpl().getElevation();
    }

    public Drawable getContentBackground()
    {
        return getImpl().getContentBackground();
    }

    public boolean getContentRect(Rect rect)
    {
        boolean flag = false;
        if (ViewCompat.isLaidOut(this))
        {
            rect.set(0, 0, getWidth(), getHeight());
            rect.left = rect.left + mShadowPadding.left;
            rect.top = rect.top + mShadowPadding.top;
            rect.right = rect.right - mShadowPadding.right;
            rect.bottom = rect.bottom - mShadowPadding.bottom;
            flag = true;
        }
        return flag;
    }

    public int getRippleColor()
    {
        return mRippleColor;
    }

    public int getSize()
    {
        return mSize;
    }

    int getSizeDimension()
    {
        return getSizeDimension(mSize);
    }

    public boolean getUseCompatPadding()
    {
        return mCompatPadding;
    }

    public void hide()
    {
        hide(null);
    }

    public void hide(OnVisibilityChangedListener onvisibilitychangedlistener)
    {
        hide(onvisibilitychangedlistener, true);
    }

    void hide(OnVisibilityChangedListener onvisibilitychangedlistener, boolean flag)
    {
        getImpl().hide(wrapOnVisibilityChangedListener(onvisibilitychangedlistener), flag);
    }

    public void jumpDrawablesToCurrentState()
    {
        super.jumpDrawablesToCurrentState();
        getImpl().jumpDrawableToCurrentState();
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        getImpl().onAttachedToWindow();
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        getImpl().onDetachedFromWindow();
    }

    protected void onMeasure(int i, int j)
    {
        int k = getSizeDimension();
        mImagePadding = (k - mMaxImageSize) / 2;
        getImpl().updatePadding();
        i = Math.min(resolveAdjustedSize(k, i), resolveAdjustedSize(k, j));
        setMeasuredDimension(mShadowPadding.left + i + mShadowPadding.right, mShadowPadding.top + i + mShadowPadding.bottom);
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        motionevent.getAction();
        JVM INSTR tableswitch 0 0: default 24
    //                   0 30;
           goto _L1 _L2
_L1:
        return super.onTouchEvent(motionevent);
_L2:
        if (getContentRect(mTouchArea) && !mTouchArea.contains((int)motionevent.getX(), (int)motionevent.getY()))
        {
            return false;
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    public void setBackgroundColor(int i)
    {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }

    public void setBackgroundDrawable(Drawable drawable)
    {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }

    public void setBackgroundResource(int i)
    {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }

    public void setBackgroundTintList(ColorStateList colorstatelist)
    {
        if (mBackgroundTint != colorstatelist)
        {
            mBackgroundTint = colorstatelist;
            getImpl().setBackgroundTintList(colorstatelist);
        }
    }

    public void setBackgroundTintMode(android.graphics.PorterDuff.Mode mode)
    {
        if (mBackgroundTintMode != mode)
        {
            mBackgroundTintMode = mode;
            getImpl().setBackgroundTintMode(mode);
        }
    }

    public void setCompatElevation(float f)
    {
        getImpl().setElevation(f);
    }

    public void setImageResource(int i)
    {
        mImageHelper.setImageResource(i);
    }

    public void setRippleColor(int i)
    {
        if (mRippleColor != i)
        {
            mRippleColor = i;
            getImpl().setRippleColor(i);
        }
    }

    public void setSize(int i)
    {
        if (i != mSize)
        {
            mSize = i;
            requestLayout();
        }
    }

    public void setUseCompatPadding(boolean flag)
    {
        if (mCompatPadding != flag)
        {
            mCompatPadding = flag;
            getImpl().onCompatShadowChanged();
        }
    }

    public volatile void setVisibility(int i)
    {
        super.setVisibility(i);
    }

    public void show()
    {
        show(null);
    }

    public void show(OnVisibilityChangedListener onvisibilitychangedlistener)
    {
        show(onvisibilitychangedlistener, true);
    }

    void show(OnVisibilityChangedListener onvisibilitychangedlistener, boolean flag)
    {
        getImpl().show(wrapOnVisibilityChangedListener(onvisibilitychangedlistener), flag);
    }

}
