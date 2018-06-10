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
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PointerIconCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.content.res.AppCompatResources;
import android.text.Layout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.lang.annotation.Annotation;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package android.support.design.widget:
//            ThemeUtils, TabItem, AnimationUtils

public class TabLayout extends HorizontalScrollView
{
    private class AdapterChangeListener
        implements android.support.v4.view.ViewPager.OnAdapterChangeListener
    {

        private boolean mAutoRefresh;
        final TabLayout this$0;

        public void onAdapterChanged(ViewPager viewpager, PagerAdapter pageradapter, PagerAdapter pageradapter1)
        {
            if (mViewPager == viewpager)
            {
                setPagerAdapter(pageradapter1, mAutoRefresh);
            }
        }

        void setAutoRefresh(boolean flag)
        {
            mAutoRefresh = flag;
        }

        AdapterChangeListener()
        {
            this$0 = TabLayout.this;
            super();
        }
    }

    public static interface Mode
        extends Annotation
    {
    }

    public static interface OnTabSelectedListener
    {

        public abstract void onTabReselected(Tab tab);

        public abstract void onTabSelected(Tab tab);

        public abstract void onTabUnselected(Tab tab);
    }

    private class PagerAdapterObserver extends DataSetObserver
    {

        final TabLayout this$0;

        public void onChanged()
        {
            populateFromPagerAdapter();
        }

        public void onInvalidated()
        {
            populateFromPagerAdapter();
        }

        PagerAdapterObserver()
        {
            this$0 = TabLayout.this;
            super();
        }
    }

    private class SlidingTabStrip extends LinearLayout
    {

        private ValueAnimator mIndicatorAnimator;
        private int mIndicatorLeft;
        private int mIndicatorRight;
        private int mLayoutDirection;
        private int mSelectedIndicatorHeight;
        private final Paint mSelectedIndicatorPaint = new Paint();
        int mSelectedPosition;
        float mSelectionOffset;
        final TabLayout this$0;

        private void updateIndicatorPosition()
        {
            View view = getChildAt(mSelectedPosition);
            int i;
            int j;
            if (view != null && view.getWidth() > 0)
            {
                int l = view.getLeft();
                int k = view.getRight();
                i = l;
                j = k;
                if (mSelectionOffset > 0.0F)
                {
                    i = l;
                    j = k;
                    if (mSelectedPosition < getChildCount() - 1)
                    {
                        View view1 = getChildAt(mSelectedPosition + 1);
                        i = (int)(mSelectionOffset * (float)view1.getLeft() + (1.0F - mSelectionOffset) * (float)l);
                        j = (int)(mSelectionOffset * (float)view1.getRight() + (1.0F - mSelectionOffset) * (float)k);
                    }
                }
            } else
            {
                j = -1;
                i = -1;
            }
            setIndicatorPosition(i, j);
        }

        void animateIndicatorToPosition(int i, int j)
        {
            if (mIndicatorAnimator != null && mIndicatorAnimator.isRunning())
            {
                mIndicatorAnimator.cancel();
            }
            View view;
            final int startLeft;
            if (ViewCompat.getLayoutDirection(this) == 1)
            {
                startLeft = 1;
            } else
            {
                startLeft = 0;
            }
            view = getChildAt(i);
            if (view == null)
            {
                updateIndicatorPosition();
            } else
            {
                final int targetLeft = view.getLeft();
                int k = view.getRight();
                final int startRight;
                if (Math.abs(i - mSelectedPosition) <= 1)
                {
                    startLeft = mIndicatorLeft;
                    startRight = mIndicatorRight;
                } else
                {
                    startRight = dpToPx(24);
                    if (i < mSelectedPosition)
                    {
                        if (startLeft != 0)
                        {
                            startRight = targetLeft - startRight;
                            startLeft = startRight;
                        } else
                        {
                            startRight = k + startRight;
                            startLeft = startRight;
                        }
                    } else
                    if (startLeft != 0)
                    {
                        startRight = k + startRight;
                        startLeft = startRight;
                    } else
                    {
                        startRight = targetLeft - startRight;
                        startLeft = startRight;
                    }
                }
                if (startLeft != targetLeft || startRight != k)
                {
                    ValueAnimator valueanimator = new ValueAnimator();
                    mIndicatorAnimator = valueanimator;
                    valueanimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
                    valueanimator.setDuration(j);
                    valueanimator.setFloatValues(new float[] {
                        0.0F, 1.0F
                    });
                    valueanimator.addUpdateListener(k. new android.animation.ValueAnimator.AnimatorUpdateListener() {

                        final SlidingTabStrip this$1;
                        final int val$startLeft;
                        final int val$startRight;
                        final int val$targetLeft;
                        final int val$targetRight;

                        public void onAnimationUpdate(ValueAnimator valueanimator)
                        {
                            float f = valueanimator.getAnimatedFraction();
                            setIndicatorPosition(AnimationUtils.lerp(startLeft, targetLeft, f), AnimationUtils.lerp(startRight, targetRight, f));
                        }

            
            {
                this$1 = final_slidingtabstrip;
                startLeft = i;
                targetLeft = j;
                startRight = k;
                targetRight = I.this;
                super();
            }
                    });
                    valueanimator.addListener(i. new AnimatorListenerAdapter() {

                        final SlidingTabStrip this$1;
                        final int val$position;

                        public void onAnimationEnd(Animator animator)
                        {
                            mSelectedPosition = position;
                            mSelectionOffset = 0.0F;
                        }

            
            {
                this$1 = final_slidingtabstrip;
                position = I.this;
                super();
            }
                    });
                    valueanimator.start();
                    return;
                }
            }
        }

        boolean childrenNeedLayout()
        {
            int i = 0;
            for (int j = getChildCount(); i < j; i++)
            {
                if (getChildAt(i).getWidth() <= 0)
                {
                    return true;
                }
            }

            return false;
        }

        public void draw(Canvas canvas)
        {
            super.draw(canvas);
            if (mIndicatorLeft >= 0 && mIndicatorRight > mIndicatorLeft)
            {
                canvas.drawRect(mIndicatorLeft, getHeight() - mSelectedIndicatorHeight, mIndicatorRight, getHeight(), mSelectedIndicatorPaint);
            }
        }

        float getIndicatorPosition()
        {
            return (float)mSelectedPosition + mSelectionOffset;
        }

        protected void onLayout(boolean flag, int i, int j, int k, int l)
        {
            super.onLayout(flag, i, j, k, l);
            if (mIndicatorAnimator != null && mIndicatorAnimator.isRunning())
            {
                mIndicatorAnimator.cancel();
                long l1 = mIndicatorAnimator.getDuration();
                animateIndicatorToPosition(mSelectedPosition, Math.round((1.0F - mIndicatorAnimator.getAnimatedFraction()) * (float)l1));
                return;
            } else
            {
                updateIndicatorPosition();
                return;
            }
        }

        protected void onMeasure(int i, int j)
        {
            super.onMeasure(i, j);
            break MISSING_BLOCK_LABEL_6;
            boolean flag1;
label0:
            while (!flag1) 
            {
                int l;
                int l1;
                do
                {
                    do
                    {
                        return;
                    } while (android.view.View.MeasureSpec.getMode(i) != 0x40000000 || mMode != 1 || mTabGravity != 1);
                    l1 = getChildCount();
                    l = 0;
                    for (int k = 0; k < l1;)
                    {
                        View view = getChildAt(k);
                        int i1 = l;
                        if (view.getVisibility() == 0)
                        {
                            i1 = Math.max(l, view.getMeasuredWidth());
                        }
                        k++;
                        l = i1;
                    }

                } while (l <= 0);
                int j1 = dpToPx(16);
                boolean flag = false;
                if (l * l1 <= getMeasuredWidth() - j1 * 2)
                {
                    int k1 = 0;
                    do
                    {
                        flag1 = flag;
                        if (k1 >= l1)
                        {
                            continue label0;
                        }
                        android.widget.LinearLayout.LayoutParams layoutparams = (android.widget.LinearLayout.LayoutParams)getChildAt(k1).getLayoutParams();
                        if (layoutparams.width != l || layoutparams.weight != 0.0F)
                        {
                            layoutparams.width = l;
                            layoutparams.weight = 0.0F;
                            flag = true;
                        }
                        k1++;
                    } while (true);
                }
                mTabGravity = 0;
                updateTabViews(false);
                flag1 = true;
            }
            super.onMeasure(i, j);
            return;
        }

        public void onRtlPropertiesChanged(int i)
        {
            super.onRtlPropertiesChanged(i);
            if (android.os.Build.VERSION.SDK_INT < 23 && mLayoutDirection != i)
            {
                requestLayout();
                mLayoutDirection = i;
            }
        }

        void setIndicatorPosition(int i, int j)
        {
            if (i != mIndicatorLeft || j != mIndicatorRight)
            {
                mIndicatorLeft = i;
                mIndicatorRight = j;
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        void setIndicatorPositionFromTabPosition(int i, float f)
        {
            if (mIndicatorAnimator != null && mIndicatorAnimator.isRunning())
            {
                mIndicatorAnimator.cancel();
            }
            mSelectedPosition = i;
            mSelectionOffset = f;
            updateIndicatorPosition();
        }

        void setSelectedIndicatorColor(int i)
        {
            if (mSelectedIndicatorPaint.getColor() != i)
            {
                mSelectedIndicatorPaint.setColor(i);
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        void setSelectedIndicatorHeight(int i)
        {
            if (mSelectedIndicatorHeight != i)
            {
                mSelectedIndicatorHeight = i;
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        SlidingTabStrip(Context context)
        {
            this$0 = TabLayout.this;
            super(context);
            mSelectedPosition = -1;
            mLayoutDirection = -1;
            mIndicatorLeft = -1;
            mIndicatorRight = -1;
            setWillNotDraw(false);
        }
    }

    public static final class Tab
    {

        public static final int INVALID_POSITION = -1;
        private CharSequence mContentDesc;
        private View mCustomView;
        private Drawable mIcon;
        TabLayout mParent;
        private int mPosition;
        private Object mTag;
        private CharSequence mText;
        TabView mView;

        public CharSequence getContentDescription()
        {
            return mContentDesc;
        }

        public View getCustomView()
        {
            return mCustomView;
        }

        public Drawable getIcon()
        {
            return mIcon;
        }

        public int getPosition()
        {
            return mPosition;
        }

        public Object getTag()
        {
            return mTag;
        }

        public CharSequence getText()
        {
            return mText;
        }

        public boolean isSelected()
        {
            if (mParent == null)
            {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            return mParent.getSelectedTabPosition() == mPosition;
        }

        void reset()
        {
            mParent = null;
            mView = null;
            mTag = null;
            mIcon = null;
            mText = null;
            mContentDesc = null;
            mPosition = -1;
            mCustomView = null;
        }

        public void select()
        {
            if (mParent == null)
            {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            } else
            {
                mParent.selectTab(this);
                return;
            }
        }

        public Tab setContentDescription(int i)
        {
            if (mParent == null)
            {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            } else
            {
                return setContentDescription(mParent.getResources().getText(i));
            }
        }

        public Tab setContentDescription(CharSequence charsequence)
        {
            mContentDesc = charsequence;
            updateView();
            return this;
        }

        public Tab setCustomView(int i)
        {
            return setCustomView(LayoutInflater.from(mView.getContext()).inflate(i, mView, false));
        }

        public Tab setCustomView(View view)
        {
            mCustomView = view;
            updateView();
            return this;
        }

        public Tab setIcon(int i)
        {
            if (mParent == null)
            {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            } else
            {
                return setIcon(AppCompatResources.getDrawable(mParent.getContext(), i));
            }
        }

        public Tab setIcon(Drawable drawable)
        {
            mIcon = drawable;
            updateView();
            return this;
        }

        void setPosition(int i)
        {
            mPosition = i;
        }

        public Tab setTag(Object obj)
        {
            mTag = obj;
            return this;
        }

        public Tab setText(int i)
        {
            if (mParent == null)
            {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            } else
            {
                return setText(mParent.getResources().getText(i));
            }
        }

        public Tab setText(CharSequence charsequence)
        {
            mText = charsequence;
            updateView();
            return this;
        }

        void updateView()
        {
            if (mView != null)
            {
                mView.update();
            }
        }

        Tab()
        {
            mPosition = -1;
        }
    }

    public static interface TabGravity
        extends Annotation
    {
    }

    public static class TabLayoutOnPageChangeListener
        implements android.support.v4.view.ViewPager.OnPageChangeListener
    {

        private int mPreviousScrollState;
        private int mScrollState;
        private final WeakReference mTabLayoutRef;

        public void onPageScrollStateChanged(int i)
        {
            mPreviousScrollState = mScrollState;
            mScrollState = i;
        }

        public void onPageScrolled(int i, float f, int j)
        {
            TabLayout tablayout = (TabLayout)mTabLayoutRef.get();
            if (tablayout != null)
            {
                boolean flag;
                boolean flag1;
                if (mScrollState != 2 || mPreviousScrollState == 1)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (mScrollState != 2 || mPreviousScrollState != 0)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                tablayout.setScrollPosition(i, f, flag, flag1);
            }
        }

        public void onPageSelected(int i)
        {
            TabLayout tablayout = (TabLayout)mTabLayoutRef.get();
            if (tablayout != null && tablayout.getSelectedTabPosition() != i && i < tablayout.getTabCount())
            {
                boolean flag;
                if (mScrollState == 0 || mScrollState == 2 && mPreviousScrollState == 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                tablayout.selectTab(tablayout.getTabAt(i), flag);
            }
        }

        void reset()
        {
            mScrollState = 0;
            mPreviousScrollState = 0;
        }

        public TabLayoutOnPageChangeListener(TabLayout tablayout)
        {
            mTabLayoutRef = new WeakReference(tablayout);
        }
    }

    class TabView extends LinearLayout
    {

        private ImageView mCustomIconView;
        private TextView mCustomTextView;
        private View mCustomView;
        private int mDefaultMaxLines;
        private ImageView mIconView;
        private Tab mTab;
        private TextView mTextView;
        final TabLayout this$0;

        private float approximateLineWidth(Layout layout, int i, float f)
        {
            return layout.getLineWidth(i) * (f / layout.getPaint().getTextSize());
        }

        private void updateTextAndIcon(TextView textview, ImageView imageview)
        {
            Object obj = null;
            CharSequence charsequence;
            Drawable drawable;
            CharSequence charsequence1;
            boolean flag;
            if (mTab != null)
            {
                drawable = mTab.getIcon();
            } else
            {
                drawable = null;
            }
            if (mTab != null)
            {
                charsequence1 = mTab.getText();
            } else
            {
                charsequence1 = null;
            }
            if (mTab != null)
            {
                charsequence = mTab.getContentDescription();
            } else
            {
                charsequence = null;
            }
            if (imageview != null)
            {
                int i;
                boolean flag1;
                if (drawable != null)
                {
                    imageview.setImageDrawable(drawable);
                    imageview.setVisibility(0);
                    setVisibility(0);
                } else
                {
                    imageview.setVisibility(8);
                    imageview.setImageDrawable(null);
                }
                imageview.setContentDescription(charsequence);
            }
            if (!TextUtils.isEmpty(charsequence1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (textview != null)
            {
                if (flag)
                {
                    textview.setText(charsequence1);
                    textview.setVisibility(0);
                    setVisibility(0);
                } else
                {
                    textview.setVisibility(8);
                    textview.setText(null);
                }
                textview.setContentDescription(charsequence);
            }
            if (imageview != null)
            {
                textview = (android.view.ViewGroup.MarginLayoutParams)imageview.getLayoutParams();
                flag1 = false;
                i = ((flag1) ? 1 : 0);
                if (flag)
                {
                    i = ((flag1) ? 1 : 0);
                    if (imageview.getVisibility() == 0)
                    {
                        i = dpToPx(8);
                    }
                }
                if (i != ((android.view.ViewGroup.MarginLayoutParams) (textview)).bottomMargin)
                {
                    textview.bottomMargin = i;
                    imageview.requestLayout();
                }
            }
            if (flag)
            {
                textview = obj;
            } else
            {
                textview = charsequence;
            }
            ViewCompat.setTooltipText(this, textview);
        }

        public Tab getTab()
        {
            return mTab;
        }

        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityevent)
        {
            super.onInitializeAccessibilityEvent(accessibilityevent);
            accessibilityevent.setClassName(android/support/v7/app/ActionBar$Tab.getName());
        }

        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            super.onInitializeAccessibilityNodeInfo(accessibilitynodeinfo);
            accessibilitynodeinfo.setClassName(android/support/v7/app/ActionBar$Tab.getName());
        }

        public void onMeasure(int i, int j)
        {
            int k = android.view.View.MeasureSpec.getSize(i);
            int i1 = android.view.View.MeasureSpec.getMode(i);
            int k1 = getTabMaxWidth();
            if (k1 > 0 && (i1 == 0 || k > k1))
            {
                i = android.view.View.MeasureSpec.makeMeasureSpec(mTabMaxWidth, 0x80000000);
            }
            super.onMeasure(i, j);
            if (mTextView == null) goto _L2; else goto _L1
_L1:
            float f1;
            int j1;
            getResources();
            f1 = mTabTextSize;
            j1 = mDefaultMaxLines;
            if (mIconView == null || mIconView.getVisibility() != 0) goto _L4; else goto _L3
_L3:
            float f;
            int l;
            l = 1;
            f = f1;
_L6:
            f1 = mTextView.getTextSize();
            int l1 = mTextView.getLineCount();
            j1 = TextViewCompat.getMaxLines(mTextView);
            if (f != f1 || j1 >= 0 && l != j1)
            {
label0:
                {
                    boolean flag = true;
                    j1 = ((flag) ? 1 : 0);
                    if (mMode != 1)
                    {
                        break label0;
                    }
                    j1 = ((flag) ? 1 : 0);
                    if (f <= f1)
                    {
                        break label0;
                    }
                    j1 = ((flag) ? 1 : 0);
                    if (l1 != 1)
                    {
                        break label0;
                    }
                    Layout layout = mTextView.getLayout();
                    if (layout != null)
                    {
                        j1 = ((flag) ? 1 : 0);
                        if (approximateLineWidth(layout, 0, f) <= (float)(getMeasuredWidth() - getPaddingLeft() - getPaddingRight()))
                        {
                            break label0;
                        }
                    }
                    j1 = 0;
                }
                if (j1 != 0)
                {
                    mTextView.setTextSize(0, f);
                    mTextView.setMaxLines(l);
                    super.onMeasure(i, j);
                }
            }
_L2:
            return;
_L4:
            l = j1;
            f = f1;
            if (mTextView != null)
            {
                l = j1;
                f = f1;
                if (mTextView.getLineCount() > 1)
                {
                    f = mTabTextMultiLineSize;
                    l = j1;
                }
            }
            if (true) goto _L6; else goto _L5
_L5:
        }

        public boolean performClick()
        {
            boolean flag1 = super.performClick();
            boolean flag = flag1;
            if (mTab != null)
            {
                if (!flag1)
                {
                    playSoundEffect(0);
                }
                mTab.select();
                flag = true;
            }
            return flag;
        }

        void reset()
        {
            setTab(null);
            setSelected(false);
        }

        public void setSelected(boolean flag)
        {
            boolean flag1;
            if (isSelected() != flag)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            super.setSelected(flag);
            if (flag1 && flag && android.os.Build.VERSION.SDK_INT < 16)
            {
                sendAccessibilityEvent(4);
            }
            if (mTextView != null)
            {
                mTextView.setSelected(flag);
            }
            if (mIconView != null)
            {
                mIconView.setSelected(flag);
            }
            if (mCustomView != null)
            {
                mCustomView.setSelected(flag);
            }
        }

        void setTab(Tab tab)
        {
            if (tab != mTab)
            {
                mTab = tab;
                update();
            }
        }

        final void update()
        {
            Tab tab = mTab;
            Object obj;
            boolean flag;
            if (tab != null)
            {
                obj = tab.getCustomView();
            } else
            {
                obj = null;
            }
            if (obj != null)
            {
                android.view.ViewParent viewparent = ((View) (obj)).getParent();
                if (viewparent != this)
                {
                    if (viewparent != null)
                    {
                        ((ViewGroup)viewparent).removeView(((View) (obj)));
                    }
                    addView(((View) (obj)));
                }
                mCustomView = ((View) (obj));
                if (mTextView != null)
                {
                    mTextView.setVisibility(8);
                }
                if (mIconView != null)
                {
                    mIconView.setVisibility(8);
                    mIconView.setImageDrawable(null);
                }
                mCustomTextView = (TextView)((View) (obj)).findViewById(0x1020014);
                if (mCustomTextView != null)
                {
                    mDefaultMaxLines = TextViewCompat.getMaxLines(mCustomTextView);
                }
                mCustomIconView = (ImageView)((View) (obj)).findViewById(0x1020006);
            } else
            {
                if (mCustomView != null)
                {
                    removeView(mCustomView);
                    mCustomView = null;
                }
                mCustomTextView = null;
                mCustomIconView = null;
            }
            if (mCustomView == null)
            {
                if (mIconView == null)
                {
                    obj = (ImageView)LayoutInflater.from(getContext()).inflate(android.support.design.R.layout.design_layout_tab_icon, this, false);
                    addView(((View) (obj)), 0);
                    mIconView = ((ImageView) (obj));
                }
                if (mTextView == null)
                {
                    obj = (TextView)LayoutInflater.from(getContext()).inflate(android.support.design.R.layout.design_layout_tab_text, this, false);
                    addView(((View) (obj)));
                    mTextView = ((TextView) (obj));
                    mDefaultMaxLines = TextViewCompat.getMaxLines(mTextView);
                }
                TextViewCompat.setTextAppearance(mTextView, mTabTextAppearance);
                if (mTabTextColors != null)
                {
                    mTextView.setTextColor(mTabTextColors);
                }
                updateTextAndIcon(mTextView, mIconView);
            } else
            if (mCustomTextView != null || mCustomIconView != null)
            {
                updateTextAndIcon(mCustomTextView, mCustomIconView);
            }
            if (tab != null && tab.isSelected())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            setSelected(flag);
        }

        public TabView(Context context)
        {
            this$0 = TabLayout.this;
            super(context);
            mDefaultMaxLines = 2;
            if (mTabBackgroundResId != 0)
            {
                ViewCompat.setBackground(this, AppCompatResources.getDrawable(context, mTabBackgroundResId));
            }
            ViewCompat.setPaddingRelative(this, mTabPaddingStart, mTabPaddingTop, mTabPaddingEnd, mTabPaddingBottom);
            setGravity(17);
            setOrientation(1);
            setClickable(true);
            ViewCompat.setPointerIcon(this, PointerIconCompat.getSystemIcon(getContext(), 1002));
        }
    }

    public static class ViewPagerOnTabSelectedListener
        implements OnTabSelectedListener
    {

        private final ViewPager mViewPager;

        public void onTabReselected(Tab tab)
        {
        }

        public void onTabSelected(Tab tab)
        {
            mViewPager.setCurrentItem(tab.getPosition());
        }

        public void onTabUnselected(Tab tab)
        {
        }

        public ViewPagerOnTabSelectedListener(ViewPager viewpager)
        {
            mViewPager = viewpager;
        }
    }


    private static final int ANIMATION_DURATION = 300;
    static final int DEFAULT_GAP_TEXT_ICON = 8;
    private static final int DEFAULT_HEIGHT = 48;
    private static final int DEFAULT_HEIGHT_WITH_TEXT_ICON = 72;
    static final int FIXED_WRAP_GUTTER_MIN = 16;
    public static final int GRAVITY_CENTER = 1;
    public static final int GRAVITY_FILL = 0;
    private static final int INVALID_WIDTH = -1;
    public static final int MODE_FIXED = 1;
    public static final int MODE_SCROLLABLE = 0;
    static final int MOTION_NON_ADJACENT_OFFSET = 24;
    private static final int TAB_MIN_WIDTH_MARGIN = 56;
    private static final android.support.v4.util.Pools.Pool sTabPool = new android.support.v4.util.Pools.SynchronizedPool(16);
    private AdapterChangeListener mAdapterChangeListener;
    private int mContentInsetStart;
    private OnTabSelectedListener mCurrentVpSelectedListener;
    int mMode;
    private TabLayoutOnPageChangeListener mPageChangeListener;
    private PagerAdapter mPagerAdapter;
    private DataSetObserver mPagerAdapterObserver;
    private final int mRequestedTabMaxWidth;
    private final int mRequestedTabMinWidth;
    private ValueAnimator mScrollAnimator;
    private final int mScrollableTabMinWidth;
    private OnTabSelectedListener mSelectedListener;
    private final ArrayList mSelectedListeners;
    private Tab mSelectedTab;
    private boolean mSetupViewPagerImplicitly;
    final int mTabBackgroundResId;
    int mTabGravity;
    int mTabMaxWidth;
    int mTabPaddingBottom;
    int mTabPaddingEnd;
    int mTabPaddingStart;
    int mTabPaddingTop;
    private final SlidingTabStrip mTabStrip;
    int mTabTextAppearance;
    ColorStateList mTabTextColors;
    float mTabTextMultiLineSize;
    float mTabTextSize;
    private final android.support.v4.util.Pools.Pool mTabViewPool;
    private final ArrayList mTabs;
    ViewPager mViewPager;

    public TabLayout(Context context)
    {
        this(context, null);
    }

    public TabLayout(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public TabLayout(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mTabs = new ArrayList();
        mTabMaxWidth = 0x7fffffff;
        mSelectedListeners = new ArrayList();
        mTabViewPool = new android.support.v4.util.Pools.SimplePool(12);
        ThemeUtils.checkAppCompatTheme(context);
        setHorizontalScrollBarEnabled(false);
        mTabStrip = new SlidingTabStrip(context);
        super.addView(mTabStrip, 0, new android.widget.FrameLayout.LayoutParams(-2, -1));
        attributeset = context.obtainStyledAttributes(attributeset, android.support.design.R.styleable.TabLayout, i, android.support.design.R.style.Widget_Design_TabLayout);
        mTabStrip.setSelectedIndicatorHeight(attributeset.getDimensionPixelSize(android.support.design.R.styleable.TabLayout_tabIndicatorHeight, 0));
        mTabStrip.setSelectedIndicatorColor(attributeset.getColor(android.support.design.R.styleable.TabLayout_tabIndicatorColor, 0));
        i = attributeset.getDimensionPixelSize(android.support.design.R.styleable.TabLayout_tabPadding, 0);
        mTabPaddingBottom = i;
        mTabPaddingEnd = i;
        mTabPaddingTop = i;
        mTabPaddingStart = i;
        mTabPaddingStart = attributeset.getDimensionPixelSize(android.support.design.R.styleable.TabLayout_tabPaddingStart, mTabPaddingStart);
        mTabPaddingTop = attributeset.getDimensionPixelSize(android.support.design.R.styleable.TabLayout_tabPaddingTop, mTabPaddingTop);
        mTabPaddingEnd = attributeset.getDimensionPixelSize(android.support.design.R.styleable.TabLayout_tabPaddingEnd, mTabPaddingEnd);
        mTabPaddingBottom = attributeset.getDimensionPixelSize(android.support.design.R.styleable.TabLayout_tabPaddingBottom, mTabPaddingBottom);
        mTabTextAppearance = attributeset.getResourceId(android.support.design.R.styleable.TabLayout_tabTextAppearance, android.support.design.R.style.TextAppearance_Design_Tab);
        context = context.obtainStyledAttributes(mTabTextAppearance, android.support.v7.appcompat.R.styleable.TextAppearance);
        mTabTextSize = context.getDimensionPixelSize(android.support.v7.appcompat.R.styleable.TextAppearance_android_textSize, 0);
        mTabTextColors = context.getColorStateList(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColor);
        context.recycle();
        if (attributeset.hasValue(android.support.design.R.styleable.TabLayout_tabTextColor))
        {
            mTabTextColors = attributeset.getColorStateList(android.support.design.R.styleable.TabLayout_tabTextColor);
        }
        if (attributeset.hasValue(android.support.design.R.styleable.TabLayout_tabSelectedTextColor))
        {
            i = attributeset.getColor(android.support.design.R.styleable.TabLayout_tabSelectedTextColor, 0);
            mTabTextColors = createColorStateList(mTabTextColors.getDefaultColor(), i);
        }
        mRequestedTabMinWidth = attributeset.getDimensionPixelSize(android.support.design.R.styleable.TabLayout_tabMinWidth, -1);
        mRequestedTabMaxWidth = attributeset.getDimensionPixelSize(android.support.design.R.styleable.TabLayout_tabMaxWidth, -1);
        mTabBackgroundResId = attributeset.getResourceId(android.support.design.R.styleable.TabLayout_tabBackground, 0);
        mContentInsetStart = attributeset.getDimensionPixelSize(android.support.design.R.styleable.TabLayout_tabContentStart, 0);
        mMode = attributeset.getInt(android.support.design.R.styleable.TabLayout_tabMode, 1);
        mTabGravity = attributeset.getInt(android.support.design.R.styleable.TabLayout_tabGravity, 0);
        attributeset.recycle();
        context = getResources();
        mTabTextMultiLineSize = context.getDimensionPixelSize(android.support.design.R.dimen.design_tab_text_size_2line);
        mScrollableTabMinWidth = context.getDimensionPixelSize(android.support.design.R.dimen.design_tab_scrollable_min_width);
        applyModeAndGravity();
        return;
        attributeset;
        context.recycle();
        throw attributeset;
    }

    private void addTabFromItemView(TabItem tabitem)
    {
        Tab tab = newTab();
        if (tabitem.mText != null)
        {
            tab.setText(tabitem.mText);
        }
        if (tabitem.mIcon != null)
        {
            tab.setIcon(tabitem.mIcon);
        }
        if (tabitem.mCustomLayout != 0)
        {
            tab.setCustomView(tabitem.mCustomLayout);
        }
        if (!TextUtils.isEmpty(tabitem.getContentDescription()))
        {
            tab.setContentDescription(tabitem.getContentDescription());
        }
        addTab(tab);
    }

    private void addTabView(Tab tab)
    {
        TabView tabview = tab.mView;
        mTabStrip.addView(tabview, tab.getPosition(), createLayoutParamsForTabs());
    }

    private void addViewInternal(View view)
    {
        if (view instanceof TabItem)
        {
            addTabFromItemView((TabItem)view);
            return;
        } else
        {
            throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
        }
    }

    private void animateToTab(int i)
    {
        if (i == -1)
        {
            return;
        }
        if (getWindowToken() == null || !ViewCompat.isLaidOut(this) || mTabStrip.childrenNeedLayout())
        {
            setScrollPosition(i, 0.0F, true);
            return;
        }
        int j = getScrollX();
        int k = calculateScrollXForTab(i, 0.0F);
        if (j != k)
        {
            ensureScrollAnimator();
            mScrollAnimator.setIntValues(new int[] {
                j, k
            });
            mScrollAnimator.start();
        }
        mTabStrip.animateIndicatorToPosition(i, 300);
    }

    private void applyModeAndGravity()
    {
        int i = 0;
        if (mMode == 0)
        {
            i = Math.max(0, mContentInsetStart - mTabPaddingStart);
        }
        ViewCompat.setPaddingRelative(mTabStrip, i, 0, 0, 0);
        mMode;
        JVM INSTR tableswitch 0 1: default 60
    //                   0 77
    //                   1 66;
           goto _L1 _L2 _L3
_L1:
        updateTabViews(true);
        return;
_L3:
        mTabStrip.setGravity(1);
        continue; /* Loop/switch isn't completed */
_L2:
        mTabStrip.setGravity(0x800003);
        if (true) goto _L1; else goto _L4
_L4:
    }

    private int calculateScrollXForTab(int i, float f)
    {
        int j = 0;
        if (mMode == 0)
        {
            View view1 = mTabStrip.getChildAt(i);
            View view;
            int k;
            if (i + 1 < mTabStrip.getChildCount())
            {
                view = mTabStrip.getChildAt(i + 1);
            } else
            {
                view = null;
            }
            if (view1 != null)
            {
                i = view1.getWidth();
            } else
            {
                i = 0;
            }
            if (view != null)
            {
                j = view.getWidth();
            }
            k = (view1.getLeft() + i / 2) - getWidth() / 2;
            i = (int)((float)(i + j) * 0.5F * f);
            if (ViewCompat.getLayoutDirection(this) == 0)
            {
                return k + i;
            } else
            {
                return k - i;
            }
        } else
        {
            return 0;
        }
    }

    private void configureTab(Tab tab, int i)
    {
        tab.setPosition(i);
        mTabs.add(i, tab);
        int j = mTabs.size();
        for (i++; i < j; i++)
        {
            ((Tab)mTabs.get(i)).setPosition(i);
        }

    }

    private static ColorStateList createColorStateList(int i, int j)
    {
        int ai[][] = new int[2][];
        int ai1[] = new int[2];
        ai[0] = SELECTED_STATE_SET;
        ai1[0] = j;
        j = 0 + 1;
        ai[j] = EMPTY_STATE_SET;
        ai1[j] = i;
        return new ColorStateList(ai, ai1);
    }

    private android.widget.LinearLayout.LayoutParams createLayoutParamsForTabs()
    {
        android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-2, -1);
        updateTabViewLayoutParams(layoutparams);
        return layoutparams;
    }

    private TabView createTabView(Tab tab)
    {
        TabView tabview;
        TabView tabview1;
        if (mTabViewPool != null)
        {
            tabview = (TabView)mTabViewPool.acquire();
        } else
        {
            tabview = null;
        }
        tabview1 = tabview;
        if (tabview == null)
        {
            tabview1 = new TabView(getContext());
        }
        tabview1.setTab(tab);
        tabview1.setFocusable(true);
        tabview1.setMinimumWidth(getTabMinWidth());
        return tabview1;
    }

    private void dispatchTabReselected(Tab tab)
    {
        for (int i = mSelectedListeners.size() - 1; i >= 0; i--)
        {
            ((OnTabSelectedListener)mSelectedListeners.get(i)).onTabReselected(tab);
        }

    }

    private void dispatchTabSelected(Tab tab)
    {
        for (int i = mSelectedListeners.size() - 1; i >= 0; i--)
        {
            ((OnTabSelectedListener)mSelectedListeners.get(i)).onTabSelected(tab);
        }

    }

    private void dispatchTabUnselected(Tab tab)
    {
        for (int i = mSelectedListeners.size() - 1; i >= 0; i--)
        {
            ((OnTabSelectedListener)mSelectedListeners.get(i)).onTabUnselected(tab);
        }

    }

    private void ensureScrollAnimator()
    {
        if (mScrollAnimator == null)
        {
            mScrollAnimator = new ValueAnimator();
            mScrollAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            mScrollAnimator.setDuration(300L);
            mScrollAnimator.addUpdateListener(new android.animation.ValueAnimator.AnimatorUpdateListener() {

                final TabLayout this$0;

                public void onAnimationUpdate(ValueAnimator valueanimator)
                {
                    scrollTo(((Integer)valueanimator.getAnimatedValue()).intValue(), 0);
                }

            
            {
                this$0 = TabLayout.this;
                super();
            }
            });
        }
    }

    private int getDefaultHeight()
    {
        boolean flag1 = false;
        int i = 0;
        int j = mTabs.size();
        do
        {
label0:
            {
                boolean flag = flag1;
                if (i < j)
                {
                    Tab tab = (Tab)mTabs.get(i);
                    if (tab == null || tab.getIcon() == null || TextUtils.isEmpty(tab.getText()))
                    {
                        break label0;
                    }
                    flag = true;
                }
                return !flag ? 48 : 72;
            }
            i++;
        } while (true);
    }

    private float getScrollPosition()
    {
        return mTabStrip.getIndicatorPosition();
    }

    private int getTabMinWidth()
    {
        if (mRequestedTabMinWidth != -1)
        {
            return mRequestedTabMinWidth;
        }
        if (mMode == 0)
        {
            return mScrollableTabMinWidth;
        } else
        {
            return 0;
        }
    }

    private int getTabScrollRange()
    {
        return Math.max(0, mTabStrip.getWidth() - getWidth() - getPaddingLeft() - getPaddingRight());
    }

    private void removeTabViewAt(int i)
    {
        TabView tabview = (TabView)mTabStrip.getChildAt(i);
        mTabStrip.removeViewAt(i);
        if (tabview != null)
        {
            tabview.reset();
            mTabViewPool.release(tabview);
        }
        requestLayout();
    }

    private void setSelectedTabView(int i)
    {
        int k = mTabStrip.getChildCount();
        if (i < k)
        {
            int j = 0;
            while (j < k) 
            {
                View view = mTabStrip.getChildAt(j);
                boolean flag;
                if (j == i)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                view.setSelected(flag);
                j++;
            }
        }
    }

    private void setupWithViewPager(ViewPager viewpager, boolean flag, boolean flag1)
    {
        if (mViewPager != null)
        {
            if (mPageChangeListener != null)
            {
                mViewPager.removeOnPageChangeListener(mPageChangeListener);
            }
            if (mAdapterChangeListener != null)
            {
                mViewPager.removeOnAdapterChangeListener(mAdapterChangeListener);
            }
        }
        if (mCurrentVpSelectedListener != null)
        {
            removeOnTabSelectedListener(mCurrentVpSelectedListener);
            mCurrentVpSelectedListener = null;
        }
        if (viewpager != null)
        {
            mViewPager = viewpager;
            if (mPageChangeListener == null)
            {
                mPageChangeListener = new TabLayoutOnPageChangeListener(this);
            }
            mPageChangeListener.reset();
            viewpager.addOnPageChangeListener(mPageChangeListener);
            mCurrentVpSelectedListener = new ViewPagerOnTabSelectedListener(viewpager);
            addOnTabSelectedListener(mCurrentVpSelectedListener);
            PagerAdapter pageradapter = viewpager.getAdapter();
            if (pageradapter != null)
            {
                setPagerAdapter(pageradapter, flag);
            }
            if (mAdapterChangeListener == null)
            {
                mAdapterChangeListener = new AdapterChangeListener();
            }
            mAdapterChangeListener.setAutoRefresh(flag);
            viewpager.addOnAdapterChangeListener(mAdapterChangeListener);
            setScrollPosition(viewpager.getCurrentItem(), 0.0F, true);
        } else
        {
            mViewPager = null;
            setPagerAdapter(null, false);
        }
        mSetupViewPagerImplicitly = flag1;
    }

    private void updateAllTabs()
    {
        int i = 0;
        for (int j = mTabs.size(); i < j; i++)
        {
            ((Tab)mTabs.get(i)).updateView();
        }

    }

    private void updateTabViewLayoutParams(android.widget.LinearLayout.LayoutParams layoutparams)
    {
        if (mMode == 1 && mTabGravity == 0)
        {
            layoutparams.width = 0;
            layoutparams.weight = 1.0F;
            return;
        } else
        {
            layoutparams.width = -2;
            layoutparams.weight = 0.0F;
            return;
        }
    }

    public void addOnTabSelectedListener(OnTabSelectedListener ontabselectedlistener)
    {
        if (!mSelectedListeners.contains(ontabselectedlistener))
        {
            mSelectedListeners.add(ontabselectedlistener);
        }
    }

    public void addTab(Tab tab)
    {
        addTab(tab, mTabs.isEmpty());
    }

    public void addTab(Tab tab, int i)
    {
        addTab(tab, i, mTabs.isEmpty());
    }

    public void addTab(Tab tab, int i, boolean flag)
    {
        if (tab.mParent != this)
        {
            throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
        }
        configureTab(tab, i);
        addTabView(tab);
        if (flag)
        {
            tab.select();
        }
    }

    public void addTab(Tab tab, boolean flag)
    {
        addTab(tab, mTabs.size(), flag);
    }

    public void addView(View view)
    {
        addViewInternal(view);
    }

    public void addView(View view, int i)
    {
        addViewInternal(view);
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutparams)
    {
        addViewInternal(view);
    }

    public void addView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        addViewInternal(view);
    }

    public void clearOnTabSelectedListeners()
    {
        mSelectedListeners.clear();
    }

    int dpToPx(int i)
    {
        return Math.round(getResources().getDisplayMetrics().density * (float)i);
    }

    public volatile android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return generateLayoutParams(attributeset);
    }

    public android.widget.FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return generateDefaultLayoutParams();
    }

    public int getSelectedTabPosition()
    {
        if (mSelectedTab != null)
        {
            return mSelectedTab.getPosition();
        } else
        {
            return -1;
        }
    }

    public Tab getTabAt(int i)
    {
        if (i < 0 || i >= getTabCount())
        {
            return null;
        } else
        {
            return (Tab)mTabs.get(i);
        }
    }

    public int getTabCount()
    {
        return mTabs.size();
    }

    public int getTabGravity()
    {
        return mTabGravity;
    }

    int getTabMaxWidth()
    {
        return mTabMaxWidth;
    }

    public int getTabMode()
    {
        return mMode;
    }

    public ColorStateList getTabTextColors()
    {
        return mTabTextColors;
    }

    public Tab newTab()
    {
        Tab tab1 = (Tab)sTabPool.acquire();
        Tab tab = tab1;
        if (tab1 == null)
        {
            tab = new Tab();
        }
        tab.mParent = this;
        tab.mView = createTabView(tab);
        return tab;
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        if (mViewPager == null)
        {
            android.view.ViewParent viewparent = getParent();
            if (viewparent instanceof ViewPager)
            {
                setupWithViewPager((ViewPager)viewparent, true, true);
            }
        }
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if (mSetupViewPagerImplicitly)
        {
            setupWithViewPager(null);
            mSetupViewPagerImplicitly = false;
        }
    }

    protected void onMeasure(int i, int j)
    {
        int k = dpToPx(getDefaultHeight()) + getPaddingTop() + getPaddingBottom();
        android.view.View.MeasureSpec.getMode(j);
        JVM INSTR lookupswitch 2: default 52
    //                   -2147483648: 173
    //                   0: 192;
           goto _L1 _L2 _L3
_L1:
        View view;
        k = android.view.View.MeasureSpec.getSize(i);
        if (android.view.View.MeasureSpec.getMode(i) != 0)
        {
            if (mRequestedTabMaxWidth > 0)
            {
                k = mRequestedTabMaxWidth;
            } else
            {
                k -= dpToPx(56);
            }
            mTabMaxWidth = k;
        }
        super.onMeasure(i, j);
        if (getChildCount() != 1) goto _L5; else goto _L4
_L4:
        view = getChildAt(0);
        i = 0;
        mMode;
        JVM INSTR tableswitch 0 1: default 132
    //                   0 218
    //                   1 239;
           goto _L6 _L7 _L8
_L6:
        break; /* Loop/switch isn't completed */
_L8:
        break MISSING_BLOCK_LABEL_239;
_L9:
        if (i != 0)
        {
            i = getChildMeasureSpec(j, getPaddingTop() + getPaddingBottom(), view.getLayoutParams().height);
            view.measure(android.view.View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0x40000000), i);
        }
_L5:
        return;
_L2:
        j = android.view.View.MeasureSpec.makeMeasureSpec(Math.min(k, android.view.View.MeasureSpec.getSize(j)), 0x40000000);
          goto _L1
_L3:
        j = android.view.View.MeasureSpec.makeMeasureSpec(k, 0x40000000);
          goto _L1
_L7:
        if (view.getMeasuredWidth() < getMeasuredWidth())
        {
            i = 1;
        } else
        {
            i = 0;
        }
          goto _L9
        if (view.getMeasuredWidth() != getMeasuredWidth())
        {
            i = 1;
        } else
        {
            i = 0;
        }
          goto _L9
    }

    void populateFromPagerAdapter()
    {
        removeAllTabs();
        if (mPagerAdapter != null)
        {
            int k = mPagerAdapter.getCount();
            for (int i = 0; i < k; i++)
            {
                addTab(newTab().setText(mPagerAdapter.getPageTitle(i)), false);
            }

            if (mViewPager != null && k > 0)
            {
                int j = mViewPager.getCurrentItem();
                if (j != getSelectedTabPosition() && j < getTabCount())
                {
                    selectTab(getTabAt(j));
                }
            }
        }
    }

    public void removeAllTabs()
    {
        for (int i = mTabStrip.getChildCount() - 1; i >= 0; i--)
        {
            removeTabViewAt(i);
        }

        Tab tab;
        for (Iterator iterator = mTabs.iterator(); iterator.hasNext(); sTabPool.release(tab))
        {
            tab = (Tab)iterator.next();
            iterator.remove();
            tab.reset();
        }

        mSelectedTab = null;
    }

    public void removeOnTabSelectedListener(OnTabSelectedListener ontabselectedlistener)
    {
        mSelectedListeners.remove(ontabselectedlistener);
    }

    public void removeTab(Tab tab)
    {
        if (tab.mParent != this)
        {
            throw new IllegalArgumentException("Tab does not belong to this TabLayout.");
        } else
        {
            removeTabAt(tab.getPosition());
            return;
        }
    }

    public void removeTabAt(int i)
    {
        Tab tab;
        int j;
        int l;
        if (mSelectedTab != null)
        {
            j = mSelectedTab.getPosition();
        } else
        {
            j = 0;
        }
        removeTabViewAt(i);
        tab = (Tab)mTabs.remove(i);
        if (tab != null)
        {
            tab.reset();
            sTabPool.release(tab);
        }
        l = mTabs.size();
        for (int k = i; k < l; k++)
        {
            ((Tab)mTabs.get(k)).setPosition(k);
        }

        if (j == i)
        {
            Tab tab1;
            if (mTabs.isEmpty())
            {
                tab1 = null;
            } else
            {
                tab1 = (Tab)mTabs.get(Math.max(0, i - 1));
            }
            selectTab(tab1);
        }
    }

    void selectTab(Tab tab)
    {
        selectTab(tab, true);
    }

    void selectTab(Tab tab, boolean flag)
    {
        Tab tab1 = mSelectedTab;
        if (tab1 == tab)
        {
            if (tab1 != null)
            {
                dispatchTabReselected(tab);
                animateToTab(tab.getPosition());
            }
        } else
        {
            int i;
            if (tab != null)
            {
                i = tab.getPosition();
            } else
            {
                i = -1;
            }
            if (flag)
            {
                if ((tab1 == null || tab1.getPosition() == -1) && i != -1)
                {
                    setScrollPosition(i, 0.0F, true);
                } else
                {
                    animateToTab(i);
                }
                if (i != -1)
                {
                    setSelectedTabView(i);
                }
            }
            if (tab1 != null)
            {
                dispatchTabUnselected(tab1);
            }
            mSelectedTab = tab;
            if (tab != null)
            {
                dispatchTabSelected(tab);
                return;
            }
        }
    }

    public void setOnTabSelectedListener(OnTabSelectedListener ontabselectedlistener)
    {
        if (mSelectedListener != null)
        {
            removeOnTabSelectedListener(mSelectedListener);
        }
        mSelectedListener = ontabselectedlistener;
        if (ontabselectedlistener != null)
        {
            addOnTabSelectedListener(ontabselectedlistener);
        }
    }

    void setPagerAdapter(PagerAdapter pageradapter, boolean flag)
    {
        if (mPagerAdapter != null && mPagerAdapterObserver != null)
        {
            mPagerAdapter.unregisterDataSetObserver(mPagerAdapterObserver);
        }
        mPagerAdapter = pageradapter;
        if (flag && pageradapter != null)
        {
            if (mPagerAdapterObserver == null)
            {
                mPagerAdapterObserver = new PagerAdapterObserver();
            }
            pageradapter.registerDataSetObserver(mPagerAdapterObserver);
        }
        populateFromPagerAdapter();
    }

    void setScrollAnimatorListener(android.animation.Animator.AnimatorListener animatorlistener)
    {
        ensureScrollAnimator();
        mScrollAnimator.addListener(animatorlistener);
    }

    public void setScrollPosition(int i, float f, boolean flag)
    {
        setScrollPosition(i, f, flag, true);
    }

    void setScrollPosition(int i, float f, boolean flag, boolean flag1)
    {
        int j = Math.round((float)i + f);
        if (j >= 0 && j < mTabStrip.getChildCount())
        {
            if (flag1)
            {
                mTabStrip.setIndicatorPositionFromTabPosition(i, f);
            }
            if (mScrollAnimator != null && mScrollAnimator.isRunning())
            {
                mScrollAnimator.cancel();
            }
            scrollTo(calculateScrollXForTab(i, f), 0);
            if (flag)
            {
                setSelectedTabView(j);
                return;
            }
        }
    }

    public void setSelectedTabIndicatorColor(int i)
    {
        mTabStrip.setSelectedIndicatorColor(i);
    }

    public void setSelectedTabIndicatorHeight(int i)
    {
        mTabStrip.setSelectedIndicatorHeight(i);
    }

    public void setTabGravity(int i)
    {
        if (mTabGravity != i)
        {
            mTabGravity = i;
            applyModeAndGravity();
        }
    }

    public void setTabMode(int i)
    {
        if (i != mMode)
        {
            mMode = i;
            applyModeAndGravity();
        }
    }

    public void setTabTextColors(int i, int j)
    {
        setTabTextColors(createColorStateList(i, j));
    }

    public void setTabTextColors(ColorStateList colorstatelist)
    {
        if (mTabTextColors != colorstatelist)
        {
            mTabTextColors = colorstatelist;
            updateAllTabs();
        }
    }

    public void setTabsFromPagerAdapter(PagerAdapter pageradapter)
    {
        setPagerAdapter(pageradapter, false);
    }

    public void setupWithViewPager(ViewPager viewpager)
    {
        setupWithViewPager(viewpager, true);
    }

    public void setupWithViewPager(ViewPager viewpager, boolean flag)
    {
        setupWithViewPager(viewpager, flag, false);
    }

    public boolean shouldDelayChildPressedState()
    {
        return getTabScrollRange() > 0;
    }

    void updateTabViews(boolean flag)
    {
        for (int i = 0; i < mTabStrip.getChildCount(); i++)
        {
            View view = mTabStrip.getChildAt(i);
            view.setMinimumWidth(getTabMinWidth());
            updateTabViewLayoutParams((android.widget.LinearLayout.LayoutParams)view.getLayoutParams());
            if (flag)
            {
                view.requestLayout();
            }
        }

    }

}
