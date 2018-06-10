// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import java.lang.annotation.Annotation;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.support.design.widget:
//            ThemeUtils, ViewUtilsLollipop, ViewUtils, HeaderBehavior, 
//            AnimationUtils, CoordinatorLayout, MathUtils, HeaderScrollingViewBehavior

public class AppBarLayout extends LinearLayout
{
    public static class Behavior extends HeaderBehavior
    {

        private static final int INVALID_POSITION = -1;
        private static final int MAX_OFFSET_ANIMATION_DURATION = 600;
        private WeakReference mLastNestedScrollingChildRef;
        private ValueAnimator mOffsetAnimator;
        private int mOffsetDelta;
        private int mOffsetToChildIndexOnLayout;
        private boolean mOffsetToChildIndexOnLayoutIsMinHeight;
        private float mOffsetToChildIndexOnLayoutPerc;
        private DragCallback mOnDragCallback;
        private boolean mSkipNestedPreScroll;
        private boolean mWasNestedFlung;

        private void animateOffsetTo(CoordinatorLayout coordinatorlayout, AppBarLayout appbarlayout, int i, float f)
        {
            int j = Math.abs(getTopBottomOffsetForScrollingSibling() - i);
            f = Math.abs(f);
            if (f > 0.0F)
            {
                j = Math.round(1000F * ((float)j / f)) * 3;
            } else
            {
                j = (int)((1.0F + (float)j / (float)appbarlayout.getHeight()) * 150F);
            }
            animateOffsetWithDuration(coordinatorlayout, appbarlayout, i, j);
        }

        private void animateOffsetWithDuration(final CoordinatorLayout coordinatorLayout, AppBarLayout appbarlayout, int i, int j)
        {
            int k = getTopBottomOffsetForScrollingSibling();
            if (k == i)
            {
                if (mOffsetAnimator != null && mOffsetAnimator.isRunning())
                {
                    mOffsetAnimator.cancel();
                }
                return;
            }
            if (mOffsetAnimator == null)
            {
                mOffsetAnimator = new ValueAnimator();
                mOffsetAnimator.setInterpolator(AnimationUtils.DECELERATE_INTERPOLATOR);
                mOffsetAnimator.addUpdateListener(appbarlayout. new android.animation.ValueAnimator.AnimatorUpdateListener() {

                    final Behavior this$0;
                    final AppBarLayout val$child;
                    final CoordinatorLayout val$coordinatorLayout;

                    public void onAnimationUpdate(ValueAnimator valueanimator)
                    {
                        setHeaderTopBottomOffset(coordinatorLayout, child, ((Integer)valueanimator.getAnimatedValue()).intValue());
                    }

            
            {
                this$0 = final_behavior;
                coordinatorLayout = coordinatorlayout;
                child = AppBarLayout.this;
                super();
            }
                });
            } else
            {
                mOffsetAnimator.cancel();
            }
            mOffsetAnimator.setDuration(Math.min(j, 600));
            mOffsetAnimator.setIntValues(new int[] {
                k, i
            });
            mOffsetAnimator.start();
        }

        private static boolean checkFlag(int i, int j)
        {
            return (i & j) == j;
        }

        private static View getAppBarChildOnOffset(AppBarLayout appbarlayout, int i)
        {
            int j = Math.abs(i);
            i = 0;
            for (int k = appbarlayout.getChildCount(); i < k; i++)
            {
                View view = appbarlayout.getChildAt(i);
                if (j >= view.getTop() && j <= view.getBottom())
                {
                    return view;
                }
            }

            return null;
        }

        private int getChildIndexOnOffset(AppBarLayout appbarlayout, int i)
        {
            int j = 0;
            for (int k = appbarlayout.getChildCount(); j < k; j++)
            {
                View view = appbarlayout.getChildAt(j);
                if (view.getTop() <= -i && view.getBottom() >= -i)
                {
                    return j;
                }
            }

            return -1;
        }

        private int interpolateOffset(AppBarLayout appbarlayout, int i)
        {
            int l = Math.abs(i);
            int k = 0;
            int i1 = appbarlayout.getChildCount();
            do
            {
label0:
                {
                    int j = i;
                    if (k < i1)
                    {
                        View view = appbarlayout.getChildAt(k);
                        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                        Interpolator interpolator = layoutparams.getScrollInterpolator();
                        if (l < view.getTop() || l > view.getBottom())
                        {
                            break label0;
                        }
                        j = i;
                        if (interpolator != null)
                        {
                            j = 0;
                            int j1 = layoutparams.getScrollFlags();
                            if ((j1 & 1) != 0)
                            {
                                k = 0 + (view.getHeight() + layoutparams.topMargin + layoutparams.bottomMargin);
                                j = k;
                                if ((j1 & 2) != 0)
                                {
                                    j = k - ViewCompat.getMinimumHeight(view);
                                }
                            }
                            k = j;
                            if (ViewCompat.getFitsSystemWindows(view))
                            {
                                k = j - appbarlayout.getTopInset();
                            }
                            j = i;
                            if (k > 0)
                            {
                                j = view.getTop();
                                j = Math.round((float)k * interpolator.getInterpolation((float)(l - j) / (float)k));
                                j = Integer.signum(i) * (view.getTop() + j);
                            }
                        }
                    }
                    return j;
                }
                k++;
            } while (true);
        }

        private boolean shouldJumpElevationState(CoordinatorLayout coordinatorlayout, AppBarLayout appbarlayout)
        {
            boolean flag1 = false;
            coordinatorlayout = coordinatorlayout.getDependents(appbarlayout);
            int i = 0;
            int j = coordinatorlayout.size();
            do
            {
label0:
                {
                    boolean flag = flag1;
                    if (i < j)
                    {
                        appbarlayout = ((CoordinatorLayout.LayoutParams)((View)coordinatorlayout.get(i)).getLayoutParams()).getBehavior();
                        if (!(appbarlayout instanceof ScrollingViewBehavior))
                        {
                            break label0;
                        }
                        flag = flag1;
                        if (((ScrollingViewBehavior)appbarlayout).getOverlayTop() != 0)
                        {
                            flag = true;
                        }
                    }
                    return flag;
                }
                i++;
            } while (true);
        }

        private void snapToChildIfNeeded(CoordinatorLayout coordinatorlayout, AppBarLayout appbarlayout)
        {
            int i1 = getTopBottomOffsetForScrollingSibling();
            int k = getChildIndexOnOffset(appbarlayout, i1);
            if (k >= 0)
            {
                View view = appbarlayout.getChildAt(k);
                int j1 = ((LayoutParams)view.getLayoutParams()).getScrollFlags();
                if ((j1 & 0x11) == 17)
                {
                    int l = -view.getTop();
                    int i = -view.getBottom();
                    int j = i;
                    if (k == appbarlayout.getChildCount() - 1)
                    {
                        j = i + appbarlayout.getTopInset();
                    }
                    if (checkFlag(j1, 2))
                    {
                        i = j + ViewCompat.getMinimumHeight(view);
                        k = l;
                    } else
                    {
                        i = j;
                        k = l;
                        if (checkFlag(j1, 5))
                        {
                            i = j + ViewCompat.getMinimumHeight(view);
                            if (i1 < i)
                            {
                                k = i;
                                i = j;
                            } else
                            {
                                k = l;
                            }
                        }
                    }
                    if (i1 >= (i + k) / 2)
                    {
                        i = k;
                    }
                    animateOffsetTo(coordinatorlayout, appbarlayout, MathUtils.constrain(i, -appbarlayout.getTotalScrollRange(), 0), 0.0F);
                }
            }
        }

        private void updateAppBarLayoutDrawableState(CoordinatorLayout coordinatorlayout, AppBarLayout appbarlayout, int i, int j, boolean flag)
        {
            View view = getAppBarChildOnOffset(appbarlayout, i);
            if (view == null) goto _L2; else goto _L1
_L1:
            int k;
            boolean flag1;
            boolean flag2;
            k = ((LayoutParams)view.getLayoutParams()).getScrollFlags();
            flag2 = false;
            flag1 = flag2;
            if ((k & 1) == 0) goto _L4; else goto _L3
_L3:
            int l = ViewCompat.getMinimumHeight(view);
            if (j <= 0 || (k & 0xc) == 0) goto _L6; else goto _L5
_L5:
            if (-i >= view.getBottom() - l - appbarlayout.getTopInset())
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
_L4:
            flag1 = appbarlayout.setCollapsedState(flag1);
            if (android.os.Build.VERSION.SDK_INT >= 11 && (flag || flag1 && shouldJumpElevationState(coordinatorlayout, appbarlayout)))
            {
                appbarlayout.jumpDrawablesToCurrentState();
            }
_L2:
            return;
_L6:
            flag1 = flag2;
            if ((k & 2) != 0)
            {
                if (-i >= view.getBottom() - l - appbarlayout.getTopInset())
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
            }
            if (true) goto _L4; else goto _L7
_L7:
        }

        boolean canDragView(AppBarLayout appbarlayout)
        {
            boolean flag1 = true;
            if (mOnDragCallback == null) goto _L2; else goto _L1
_L1:
            boolean flag = mOnDragCallback.canDrag(appbarlayout);
_L4:
            return flag;
_L2:
            flag = flag1;
            if (mLastNestedScrollingChildRef == null) goto _L4; else goto _L3
_L3:
            appbarlayout = (View)mLastNestedScrollingChildRef.get();
            if (appbarlayout == null || !appbarlayout.isShown())
            {
                break; /* Loop/switch isn't completed */
            }
            flag = flag1;
            if (!ViewCompat.canScrollVertically(appbarlayout, -1)) goto _L4; else goto _L5
_L5:
            return false;
        }

        volatile boolean canDragView(View view)
        {
            return canDragView((AppBarLayout)view);
        }

        public volatile int getLeftAndRightOffset()
        {
            return super.getLeftAndRightOffset();
        }

        int getMaxDragOffset(AppBarLayout appbarlayout)
        {
            return -appbarlayout.getDownNestedScrollRange();
        }

        volatile int getMaxDragOffset(View view)
        {
            return getMaxDragOffset((AppBarLayout)view);
        }

        int getScrollRangeForDragFling(AppBarLayout appbarlayout)
        {
            return appbarlayout.getTotalScrollRange();
        }

        volatile int getScrollRangeForDragFling(View view)
        {
            return getScrollRangeForDragFling((AppBarLayout)view);
        }

        public volatile int getTopAndBottomOffset()
        {
            return super.getTopAndBottomOffset();
        }

        int getTopBottomOffsetForScrollingSibling()
        {
            return getTopAndBottomOffset() + mOffsetDelta;
        }

        boolean isOffsetAnimatorRunning()
        {
            return mOffsetAnimator != null && mOffsetAnimator.isRunning();
        }

        void onFlingFinished(CoordinatorLayout coordinatorlayout, AppBarLayout appbarlayout)
        {
            snapToChildIfNeeded(coordinatorlayout, appbarlayout);
        }

        volatile void onFlingFinished(CoordinatorLayout coordinatorlayout, View view)
        {
            onFlingFinished(coordinatorlayout, (AppBarLayout)view);
        }

        public boolean onLayoutChild(CoordinatorLayout coordinatorlayout, AppBarLayout appbarlayout, int i)
        {
            int j;
            boolean flag;
            flag = super.onLayoutChild(coordinatorlayout, appbarlayout, i);
            j = appbarlayout.getPendingAction();
            if (mOffsetToChildIndexOnLayout < 0 || (j & 8) != 0) goto _L2; else goto _L1
_L1:
            View view = appbarlayout.getChildAt(mOffsetToChildIndexOnLayout);
            i = -view.getBottom();
            if (mOffsetToChildIndexOnLayoutIsMinHeight)
            {
                i += ViewCompat.getMinimumHeight(view) + appbarlayout.getTopInset();
            } else
            {
                i += Math.round((float)view.getHeight() * mOffsetToChildIndexOnLayoutPerc);
            }
            setHeaderTopBottomOffset(coordinatorlayout, appbarlayout, i);
_L4:
            appbarlayout.resetPendingAction();
            mOffsetToChildIndexOnLayout = -1;
            setTopAndBottomOffset(MathUtils.constrain(getTopAndBottomOffset(), -appbarlayout.getTotalScrollRange(), 0));
            updateAppBarLayoutDrawableState(coordinatorlayout, appbarlayout, getTopAndBottomOffset(), 0, true);
            appbarlayout.dispatchOffsetUpdates(getTopAndBottomOffset());
            return flag;
_L2:
            if (j != 0)
            {
                if ((j & 4) != 0)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if ((j & 2) != 0)
                {
                    j = -appbarlayout.getUpNestedPreScrollRange();
                    if (i != 0)
                    {
                        animateOffsetTo(coordinatorlayout, appbarlayout, j, 0.0F);
                    } else
                    {
                        setHeaderTopBottomOffset(coordinatorlayout, appbarlayout, j);
                    }
                } else
                if ((j & 1) != 0)
                {
                    if (i != 0)
                    {
                        animateOffsetTo(coordinatorlayout, appbarlayout, 0, 0.0F);
                    } else
                    {
                        setHeaderTopBottomOffset(coordinatorlayout, appbarlayout, 0);
                    }
                }
            }
            if (true) goto _L4; else goto _L3
_L3:
        }

        public volatile boolean onLayoutChild(CoordinatorLayout coordinatorlayout, View view, int i)
        {
            return onLayoutChild(coordinatorlayout, (AppBarLayout)view, i);
        }

        public boolean onMeasureChild(CoordinatorLayout coordinatorlayout, AppBarLayout appbarlayout, int i, int j, int k, int l)
        {
            if (((CoordinatorLayout.LayoutParams)appbarlayout.getLayoutParams()).height == -2)
            {
                coordinatorlayout.onMeasureChild(appbarlayout, i, j, android.view.View.MeasureSpec.makeMeasureSpec(0, 0), l);
                return true;
            } else
            {
                return super.onMeasureChild(coordinatorlayout, appbarlayout, i, j, k, l);
            }
        }

        public volatile boolean onMeasureChild(CoordinatorLayout coordinatorlayout, View view, int i, int j, int k, int l)
        {
            return onMeasureChild(coordinatorlayout, (AppBarLayout)view, i, j, k, l);
        }

        public boolean onNestedFling(CoordinatorLayout coordinatorlayout, AppBarLayout appbarlayout, View view, float f, float f1, boolean flag)
        {
            boolean flag1 = false;
            if (flag) goto _L2; else goto _L1
_L1:
            flag = fling(coordinatorlayout, appbarlayout, -appbarlayout.getTotalScrollRange(), 0, -f1);
_L4:
            mWasNestedFlung = flag;
            return flag;
_L2:
            if (f1 < 0.0F)
            {
                int i = -appbarlayout.getTotalScrollRange() + appbarlayout.getDownNestedPreScrollRange();
                flag = flag1;
                if (getTopBottomOffsetForScrollingSibling() < i)
                {
                    animateOffsetTo(coordinatorlayout, appbarlayout, i, f1);
                    flag = true;
                }
            } else
            {
                int j = -appbarlayout.getUpNestedPreScrollRange();
                flag = flag1;
                if (getTopBottomOffsetForScrollingSibling() > j)
                {
                    animateOffsetTo(coordinatorlayout, appbarlayout, j, f1);
                    flag = true;
                }
            }
            if (true) goto _L4; else goto _L3
_L3:
        }

        public volatile boolean onNestedFling(CoordinatorLayout coordinatorlayout, View view, View view1, float f, float f1, boolean flag)
        {
            return onNestedFling(coordinatorlayout, (AppBarLayout)view, view1, f, f1, flag);
        }

        public void onNestedPreScroll(CoordinatorLayout coordinatorlayout, AppBarLayout appbarlayout, View view, int i, int j, int ai[])
        {
            if (j != 0 && !mSkipNestedPreScroll)
            {
                int k;
                if (j < 0)
                {
                    i = -appbarlayout.getTotalScrollRange();
                    k = i + appbarlayout.getDownNestedPreScrollRange();
                } else
                {
                    i = -appbarlayout.getUpNestedPreScrollRange();
                    k = 0;
                }
                ai[1] = scroll(coordinatorlayout, appbarlayout, j, i, k);
            }
        }

        public volatile void onNestedPreScroll(CoordinatorLayout coordinatorlayout, View view, View view1, int i, int j, int ai[])
        {
            onNestedPreScroll(coordinatorlayout, (AppBarLayout)view, view1, i, j, ai);
        }

        public void onNestedScroll(CoordinatorLayout coordinatorlayout, AppBarLayout appbarlayout, View view, int i, int j, int k, int l)
        {
            if (l < 0)
            {
                scroll(coordinatorlayout, appbarlayout, l, -appbarlayout.getDownNestedScrollRange(), 0);
                mSkipNestedPreScroll = true;
                return;
            } else
            {
                mSkipNestedPreScroll = false;
                return;
            }
        }

        public volatile void onNestedScroll(CoordinatorLayout coordinatorlayout, View view, View view1, int i, int j, int k, int l)
        {
            onNestedScroll(coordinatorlayout, (AppBarLayout)view, view1, i, j, k, l);
        }

        public void onRestoreInstanceState(CoordinatorLayout coordinatorlayout, AppBarLayout appbarlayout, Parcelable parcelable)
        {
            if (parcelable instanceof SavedState)
            {
                parcelable = (SavedState)parcelable;
                super.onRestoreInstanceState(coordinatorlayout, appbarlayout, parcelable.getSuperState());
                mOffsetToChildIndexOnLayout = ((SavedState) (parcelable)).firstVisibleChildIndex;
                mOffsetToChildIndexOnLayoutPerc = ((SavedState) (parcelable)).firstVisibleChildPercentageShown;
                mOffsetToChildIndexOnLayoutIsMinHeight = ((SavedState) (parcelable)).firstVisibleChildAtMinimumHeight;
                return;
            } else
            {
                super.onRestoreInstanceState(coordinatorlayout, appbarlayout, parcelable);
                mOffsetToChildIndexOnLayout = -1;
                return;
            }
        }

        public volatile void onRestoreInstanceState(CoordinatorLayout coordinatorlayout, View view, Parcelable parcelable)
        {
            onRestoreInstanceState(coordinatorlayout, (AppBarLayout)view, parcelable);
        }

        public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorlayout, AppBarLayout appbarlayout)
        {
            Object obj = super.onSaveInstanceState(coordinatorlayout, appbarlayout);
            int j = getTopAndBottomOffset();
            int i = 0;
            for (int k = appbarlayout.getChildCount(); i < k; i++)
            {
                coordinatorlayout = appbarlayout.getChildAt(i);
                int l = coordinatorlayout.getBottom() + j;
                if (coordinatorlayout.getTop() + j <= 0 && l >= 0)
                {
                    obj = new SavedState(((Parcelable) (obj)));
                    obj.firstVisibleChildIndex = i;
                    boolean flag;
                    if (l == ViewCompat.getMinimumHeight(coordinatorlayout) + appbarlayout.getTopInset())
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    obj.firstVisibleChildAtMinimumHeight = flag;
                    obj.firstVisibleChildPercentageShown = (float)l / (float)coordinatorlayout.getHeight();
                    return ((Parcelable) (obj));
                }
            }

            return ((Parcelable) (obj));
        }

        public volatile Parcelable onSaveInstanceState(CoordinatorLayout coordinatorlayout, View view)
        {
            return onSaveInstanceState(coordinatorlayout, (AppBarLayout)view);
        }

        public boolean onStartNestedScroll(CoordinatorLayout coordinatorlayout, AppBarLayout appbarlayout, View view, View view1, int i)
        {
            boolean flag;
            if ((i & 2) != 0 && appbarlayout.hasScrollableChildren() && coordinatorlayout.getHeight() - view.getHeight() <= appbarlayout.getHeight())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag && mOffsetAnimator != null)
            {
                mOffsetAnimator.cancel();
            }
            mLastNestedScrollingChildRef = null;
            return flag;
        }

        public volatile boolean onStartNestedScroll(CoordinatorLayout coordinatorlayout, View view, View view1, View view2, int i)
        {
            return onStartNestedScroll(coordinatorlayout, (AppBarLayout)view, view1, view2, i);
        }

        public void onStopNestedScroll(CoordinatorLayout coordinatorlayout, AppBarLayout appbarlayout, View view)
        {
            if (!mWasNestedFlung)
            {
                snapToChildIfNeeded(coordinatorlayout, appbarlayout);
            }
            mSkipNestedPreScroll = false;
            mWasNestedFlung = false;
            mLastNestedScrollingChildRef = new WeakReference(view);
        }

        public volatile void onStopNestedScroll(CoordinatorLayout coordinatorlayout, View view, View view1)
        {
            onStopNestedScroll(coordinatorlayout, (AppBarLayout)view, view1);
        }

        public void setDragCallback(DragCallback dragcallback)
        {
            mOnDragCallback = dragcallback;
        }

        int setHeaderTopBottomOffset(CoordinatorLayout coordinatorlayout, AppBarLayout appbarlayout, int i, int j, int k)
        {
            int l = getTopBottomOffsetForScrollingSibling();
            boolean flag = false;
            if (j != 0 && l >= j && l <= k)
            {
                j = MathUtils.constrain(i, j, k);
                i = ((flag) ? 1 : 0);
                if (l != j)
                {
                    boolean flag1;
                    if (appbarlayout.hasChildWithInterpolator())
                    {
                        i = interpolateOffset(appbarlayout, j);
                    } else
                    {
                        i = j;
                    }
                    flag1 = setTopAndBottomOffset(i);
                    k = l - j;
                    mOffsetDelta = j - i;
                    if (!flag1 && appbarlayout.hasChildWithInterpolator())
                    {
                        coordinatorlayout.dispatchDependentViewsChanged(appbarlayout);
                    }
                    appbarlayout.dispatchOffsetUpdates(getTopAndBottomOffset());
                    if (j < l)
                    {
                        i = -1;
                    } else
                    {
                        i = 1;
                    }
                    updateAppBarLayoutDrawableState(coordinatorlayout, appbarlayout, j, i, false);
                    i = k;
                }
                return i;
            } else
            {
                mOffsetDelta = 0;
                return 0;
            }
        }

        volatile int setHeaderTopBottomOffset(CoordinatorLayout coordinatorlayout, View view, int i, int j, int k)
        {
            return setHeaderTopBottomOffset(coordinatorlayout, (AppBarLayout)view, i, j, k);
        }

        public volatile boolean setLeftAndRightOffset(int i)
        {
            return super.setLeftAndRightOffset(i);
        }

        public volatile boolean setTopAndBottomOffset(int i)
        {
            return super.setTopAndBottomOffset(i);
        }


        public Behavior()
        {
            mOffsetToChildIndexOnLayout = -1;
        }

        public Behavior(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            mOffsetToChildIndexOnLayout = -1;
        }
    }

    public static abstract class Behavior.DragCallback
    {

        public abstract boolean canDrag(AppBarLayout appbarlayout);

        public Behavior.DragCallback()
        {
        }
    }

    protected static class Behavior.SavedState extends AbsSavedState
    {

        public static final android.os.Parcelable.Creator CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks() {

            public Behavior.SavedState createFromParcel(Parcel parcel, ClassLoader classloader)
            {
                return new Behavior.SavedState(parcel, classloader);
            }

            public volatile Object createFromParcel(Parcel parcel, ClassLoader classloader)
            {
                return createFromParcel(parcel, classloader);
            }

            public Behavior.SavedState[] newArray(int i)
            {
                return new Behavior.SavedState[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        });
        boolean firstVisibleChildAtMinimumHeight;
        int firstVisibleChildIndex;
        float firstVisibleChildPercentageShown;

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(firstVisibleChildIndex);
            parcel.writeFloat(firstVisibleChildPercentageShown);
            if (firstVisibleChildAtMinimumHeight)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            parcel.writeByte((byte)i);
        }


        public Behavior.SavedState(Parcel parcel, ClassLoader classloader)
        {
            super(parcel, classloader);
            firstVisibleChildIndex = parcel.readInt();
            firstVisibleChildPercentageShown = parcel.readFloat();
            boolean flag;
            if (parcel.readByte() != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            firstVisibleChildAtMinimumHeight = flag;
        }

        public Behavior.SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }
    }

    public static class LayoutParams extends android.widget.LinearLayout.LayoutParams
    {

        static final int COLLAPSIBLE_FLAGS = 10;
        static final int FLAG_QUICK_RETURN = 5;
        static final int FLAG_SNAP = 17;
        public static final int SCROLL_FLAG_ENTER_ALWAYS = 4;
        public static final int SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED = 8;
        public static final int SCROLL_FLAG_EXIT_UNTIL_COLLAPSED = 2;
        public static final int SCROLL_FLAG_SCROLL = 1;
        public static final int SCROLL_FLAG_SNAP = 16;
        int mScrollFlags;
        Interpolator mScrollInterpolator;

        public int getScrollFlags()
        {
            return mScrollFlags;
        }

        public Interpolator getScrollInterpolator()
        {
            return mScrollInterpolator;
        }

        boolean isCollapsible()
        {
            return (mScrollFlags & 1) == 1 && (mScrollFlags & 0xa) != 0;
        }

        public void setScrollFlags(int i)
        {
            mScrollFlags = i;
        }

        public void setScrollInterpolator(Interpolator interpolator)
        {
            mScrollInterpolator = interpolator;
        }

        public LayoutParams(int i, int j)
        {
            super(i, j);
            mScrollFlags = 1;
        }

        public LayoutParams(int i, int j, float f)
        {
            super(i, j, f);
            mScrollFlags = 1;
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            mScrollFlags = 1;
            attributeset = context.obtainStyledAttributes(attributeset, android.support.design.R.styleable.AppBarLayout_Layout);
            mScrollFlags = attributeset.getInt(android.support.design.R.styleable.AppBarLayout_Layout_layout_scrollFlags, 0);
            if (attributeset.hasValue(android.support.design.R.styleable.AppBarLayout_Layout_layout_scrollInterpolator))
            {
                mScrollInterpolator = AnimationUtils.loadInterpolator(context, attributeset.getResourceId(android.support.design.R.styleable.AppBarLayout_Layout_layout_scrollInterpolator, 0));
            }
            attributeset.recycle();
        }

        public LayoutParams(LayoutParams layoutparams)
        {
            super(layoutparams);
            mScrollFlags = 1;
            mScrollFlags = layoutparams.mScrollFlags;
            mScrollInterpolator = layoutparams.mScrollInterpolator;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            super(layoutparams);
            mScrollFlags = 1;
        }

        public LayoutParams(android.view.ViewGroup.MarginLayoutParams marginlayoutparams)
        {
            super(marginlayoutparams);
            mScrollFlags = 1;
        }

        public LayoutParams(android.widget.LinearLayout.LayoutParams layoutparams)
        {
            super(layoutparams);
            mScrollFlags = 1;
        }
    }

    public static interface LayoutParams.ScrollFlags
        extends Annotation
    {
    }

    public static interface OnOffsetChangedListener
    {

        public abstract void onOffsetChanged(AppBarLayout appbarlayout, int i);
    }

    public static class ScrollingViewBehavior extends HeaderScrollingViewBehavior
    {

        private static int getAppBarLayoutOffset(AppBarLayout appbarlayout)
        {
            appbarlayout = ((CoordinatorLayout.LayoutParams)appbarlayout.getLayoutParams()).getBehavior();
            if (appbarlayout instanceof Behavior)
            {
                return ((Behavior)appbarlayout).getTopBottomOffsetForScrollingSibling();
            } else
            {
                return 0;
            }
        }

        private void offsetChildAsNeeded(CoordinatorLayout coordinatorlayout, View view, View view1)
        {
            coordinatorlayout = ((CoordinatorLayout.LayoutParams)view1.getLayoutParams()).getBehavior();
            if (coordinatorlayout instanceof Behavior)
            {
                coordinatorlayout = (Behavior)coordinatorlayout;
                ViewCompat.offsetTopAndBottom(view, ((view1.getBottom() - view.getTop()) + ((Behavior) (coordinatorlayout)).mOffsetDelta + getVerticalLayoutGap()) - getOverlapPixelsForOffset(view1));
            }
        }

        AppBarLayout findFirstDependency(List list)
        {
            int i = 0;
            for (int j = list.size(); i < j; i++)
            {
                View view = (View)list.get(i);
                if (view instanceof AppBarLayout)
                {
                    return (AppBarLayout)view;
                }
            }

            return null;
        }

        volatile View findFirstDependency(List list)
        {
            return findFirstDependency(list);
        }

        public volatile int getLeftAndRightOffset()
        {
            return super.getLeftAndRightOffset();
        }

        float getOverlapRatioForOffset(View view)
        {
            if (view instanceof AppBarLayout)
            {
                view = (AppBarLayout)view;
                int j = view.getTotalScrollRange();
                int k = view.getDownNestedPreScrollRange();
                int i = getAppBarLayoutOffset(view);
                if (k == 0 || j + i > k)
                {
                    if ((j -= k) != 0)
                    {
                        return 1.0F + (float)i / (float)j;
                    }
                }
            }
            return 0.0F;
        }

        int getScrollRange(View view)
        {
            if (view instanceof AppBarLayout)
            {
                return ((AppBarLayout)view).getTotalScrollRange();
            } else
            {
                return super.getScrollRange(view);
            }
        }

        public volatile int getTopAndBottomOffset()
        {
            return super.getTopAndBottomOffset();
        }

        public boolean layoutDependsOn(CoordinatorLayout coordinatorlayout, View view, View view1)
        {
            return view1 instanceof AppBarLayout;
        }

        public boolean onDependentViewChanged(CoordinatorLayout coordinatorlayout, View view, View view1)
        {
            offsetChildAsNeeded(coordinatorlayout, view, view1);
            return false;
        }

        public volatile boolean onLayoutChild(CoordinatorLayout coordinatorlayout, View view, int i)
        {
            return super.onLayoutChild(coordinatorlayout, view, i);
        }

        public volatile boolean onMeasureChild(CoordinatorLayout coordinatorlayout, View view, int i, int j, int k, int l)
        {
            return super.onMeasureChild(coordinatorlayout, view, i, j, k, l);
        }

        public boolean onRequestChildRectangleOnScreen(CoordinatorLayout coordinatorlayout, View view, Rect rect, boolean flag)
        {
            AppBarLayout appbarlayout = findFirstDependency(coordinatorlayout.getDependencies(view));
            if (appbarlayout != null)
            {
                rect.offset(view.getLeft(), view.getTop());
                view = mTempRect1;
                view.set(0, 0, coordinatorlayout.getWidth(), coordinatorlayout.getHeight());
                if (!view.contains(rect))
                {
                    if (!flag)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    appbarlayout.setExpanded(false, flag);
                    return true;
                }
            }
            return false;
        }

        public volatile boolean setLeftAndRightOffset(int i)
        {
            return super.setLeftAndRightOffset(i);
        }

        public volatile boolean setTopAndBottomOffset(int i)
        {
            return super.setTopAndBottomOffset(i);
        }

        public ScrollingViewBehavior()
        {
        }

        public ScrollingViewBehavior(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            context = context.obtainStyledAttributes(attributeset, android.support.design.R.styleable.ScrollingViewBehavior_Layout);
            setOverlayTop(context.getDimensionPixelSize(android.support.design.R.styleable.ScrollingViewBehavior_Layout_behavior_overlapTop, 0));
            context.recycle();
        }
    }


    private static final int INVALID_SCROLL_RANGE = -1;
    static final int PENDING_ACTION_ANIMATE_ENABLED = 4;
    static final int PENDING_ACTION_COLLAPSED = 2;
    static final int PENDING_ACTION_EXPANDED = 1;
    static final int PENDING_ACTION_FORCE = 8;
    static final int PENDING_ACTION_NONE = 0;
    private boolean mCollapsed;
    private boolean mCollapsible;
    private int mDownPreScrollRange;
    private int mDownScrollRange;
    private boolean mHaveChildWithInterpolator;
    private WindowInsetsCompat mLastInsets;
    private List mListeners;
    private int mPendingAction;
    private final int mTmpStatesArray[];
    private int mTotalScrollRange;

    public AppBarLayout(Context context)
    {
        this(context, null);
    }

    public AppBarLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mTotalScrollRange = -1;
        mDownPreScrollRange = -1;
        mDownScrollRange = -1;
        mPendingAction = 0;
        mTmpStatesArray = new int[2];
        setOrientation(1);
        ThemeUtils.checkAppCompatTheme(context);
        if (android.os.Build.VERSION.SDK_INT >= 21)
        {
            ViewUtilsLollipop.setBoundsViewOutlineProvider(this);
            ViewUtilsLollipop.setStateListAnimatorFromAttrs(this, attributeset, 0, android.support.design.R.style.Widget_Design_AppBarLayout);
        }
        context = context.obtainStyledAttributes(attributeset, android.support.design.R.styleable.AppBarLayout, 0, android.support.design.R.style.Widget_Design_AppBarLayout);
        ViewCompat.setBackground(this, context.getDrawable(android.support.design.R.styleable.AppBarLayout_android_background));
        if (context.hasValue(android.support.design.R.styleable.AppBarLayout_expanded))
        {
            setExpanded(context.getBoolean(android.support.design.R.styleable.AppBarLayout_expanded, false), false, false);
        }
        if (android.os.Build.VERSION.SDK_INT >= 21 && context.hasValue(android.support.design.R.styleable.AppBarLayout_elevation))
        {
            ViewUtilsLollipop.setDefaultAppBarLayoutStateListAnimator(this, context.getDimensionPixelSize(android.support.design.R.styleable.AppBarLayout_elevation, 0));
        }
        context.recycle();
        ViewCompat.setOnApplyWindowInsetsListener(this, new OnApplyWindowInsetsListener() {

            final AppBarLayout this$0;

            public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowinsetscompat)
            {
                return onWindowInsetChanged(windowinsetscompat);
            }

            
            {
                this$0 = AppBarLayout.this;
                super();
            }
        });
    }

    private void invalidateScrollRanges()
    {
        mTotalScrollRange = -1;
        mDownPreScrollRange = -1;
        mDownScrollRange = -1;
    }

    private boolean setCollapsibleState(boolean flag)
    {
        if (mCollapsible != flag)
        {
            mCollapsible = flag;
            refreshDrawableState();
            return true;
        } else
        {
            return false;
        }
    }

    private void setExpanded(boolean flag, boolean flag1, boolean flag2)
    {
        byte byte2 = 0;
        byte byte0;
        byte byte1;
        if (flag)
        {
            byte0 = 1;
        } else
        {
            byte0 = 2;
        }
        if (flag1)
        {
            byte1 = 4;
        } else
        {
            byte1 = 0;
        }
        if (flag2)
        {
            byte2 = 8;
        }
        mPendingAction = byte2 | (byte1 | byte0);
        requestLayout();
    }

    private void updateCollapsible()
    {
        boolean flag1 = false;
        int i = 0;
        int j = getChildCount();
        do
        {
label0:
            {
                boolean flag = flag1;
                if (i < j)
                {
                    if (!((LayoutParams)getChildAt(i).getLayoutParams()).isCollapsible())
                    {
                        break label0;
                    }
                    flag = true;
                }
                setCollapsibleState(flag);
                return;
            }
            i++;
        } while (true);
    }

    public void addOnOffsetChangedListener(OnOffsetChangedListener onoffsetchangedlistener)
    {
        if (mListeners == null)
        {
            mListeners = new ArrayList();
        }
        if (onoffsetchangedlistener != null && !mListeners.contains(onoffsetchangedlistener))
        {
            mListeners.add(onoffsetchangedlistener);
        }
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return layoutparams instanceof LayoutParams;
    }

    void dispatchOffsetUpdates(int i)
    {
        if (mListeners != null)
        {
            int j = 0;
            for (int k = mListeners.size(); j < k; j++)
            {
                OnOffsetChangedListener onoffsetchangedlistener = (OnOffsetChangedListener)mListeners.get(j);
                if (onoffsetchangedlistener != null)
                {
                    onoffsetchangedlistener.onOffsetChanged(this, i);
                }
            }

        }
    }

    protected LayoutParams generateDefaultLayoutParams()
    {
        return new LayoutParams(-1, -2);
    }

    protected volatile android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return generateDefaultLayoutParams();
    }

    protected volatile android.widget.LinearLayout.LayoutParams generateDefaultLayoutParams()
    {
        return generateDefaultLayoutParams();
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new LayoutParams(getContext(), attributeset);
    }

    protected LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        if (android.os.Build.VERSION.SDK_INT >= 19 && (layoutparams instanceof android.widget.LinearLayout.LayoutParams))
        {
            return new LayoutParams((android.widget.LinearLayout.LayoutParams)layoutparams);
        }
        if (layoutparams instanceof android.view.ViewGroup.MarginLayoutParams)
        {
            return new LayoutParams((android.view.ViewGroup.MarginLayoutParams)layoutparams);
        } else
        {
            return new LayoutParams(layoutparams);
        }
    }

    public volatile android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return generateLayoutParams(attributeset);
    }

    protected volatile android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return generateLayoutParams(layoutparams);
    }

    public volatile android.widget.LinearLayout.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return generateLayoutParams(attributeset);
    }

    protected volatile android.widget.LinearLayout.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return generateLayoutParams(layoutparams);
    }

    int getDownNestedPreScrollRange()
    {
        int k;
        int l;
        if (mDownPreScrollRange != -1)
        {
            return mDownPreScrollRange;
        }
        l = 0;
        k = getChildCount() - 1;
_L5:
        if (k < 0) goto _L2; else goto _L1
_L1:
        View view;
        LayoutParams layoutparams;
        int i;
        int i1;
        view = getChildAt(k);
        layoutparams = (LayoutParams)view.getLayoutParams();
        i = view.getMeasuredHeight();
        i1 = layoutparams.mScrollFlags;
        if ((i1 & 5) != 5) goto _L4; else goto _L3
_L3:
        l += layoutparams.topMargin + layoutparams.bottomMargin;
        if ((i1 & 8) != 0)
        {
            i = l + ViewCompat.getMinimumHeight(view);
        } else
        if ((i1 & 2) != 0)
        {
            i = l + (i - ViewCompat.getMinimumHeight(view));
        } else
        {
            i = l + (i - getTopInset());
        }
_L6:
        k--;
        l = i;
          goto _L5
_L4:
        i = l;
        if (l <= 0) goto _L6; else goto _L2
_L2:
        int j = Math.max(0, l);
        mDownPreScrollRange = j;
        return j;
    }

    int getDownNestedScrollRange()
    {
        if (mDownScrollRange != -1)
        {
            return mDownScrollRange;
        }
        int i = 0;
        int j = 0;
        int l = getChildCount();
        do
        {
label0:
            {
                int k = i;
                if (j < l)
                {
                    View view = getChildAt(j);
                    LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                    int j1 = view.getMeasuredHeight();
                    int k1 = layoutparams.topMargin;
                    int l1 = layoutparams.bottomMargin;
                    int i1 = layoutparams.mScrollFlags;
                    k = i;
                    if ((i1 & 1) != 0)
                    {
                        i += j1 + (k1 + l1);
                        if ((i1 & 2) == 0)
                        {
                            break label0;
                        }
                        k = i - (ViewCompat.getMinimumHeight(view) + getTopInset());
                    }
                }
                i = Math.max(0, k);
                mDownScrollRange = i;
                return i;
            }
            j++;
        } while (true);
    }

    final int getMinimumHeightForVisibleOverlappingContent()
    {
        int j = getTopInset();
        int i = ViewCompat.getMinimumHeight(this);
        if (i != 0)
        {
            return i * 2 + j;
        }
        i = getChildCount();
        if (i >= 1)
        {
            i = ViewCompat.getMinimumHeight(getChildAt(i - 1));
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            return i * 2 + j;
        } else
        {
            return getHeight() / 3;
        }
    }

    int getPendingAction()
    {
        return mPendingAction;
    }

    public float getTargetElevation()
    {
        return 0.0F;
    }

    final int getTopInset()
    {
        if (mLastInsets != null)
        {
            return mLastInsets.getSystemWindowInsetTop();
        } else
        {
            return 0;
        }
    }

    public final int getTotalScrollRange()
    {
        if (mTotalScrollRange != -1)
        {
            return mTotalScrollRange;
        }
        int i = 0;
        int j = 0;
        int l = getChildCount();
        do
        {
label0:
            {
                int k = i;
                if (j < l)
                {
                    View view = getChildAt(j);
                    LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                    int j1 = view.getMeasuredHeight();
                    int i1 = layoutparams.mScrollFlags;
                    k = i;
                    if ((i1 & 1) != 0)
                    {
                        i += layoutparams.topMargin + j1 + layoutparams.bottomMargin;
                        if ((i1 & 2) == 0)
                        {
                            break label0;
                        }
                        k = i - ViewCompat.getMinimumHeight(view);
                    }
                }
                i = Math.max(0, k - getTopInset());
                mTotalScrollRange = i;
                return i;
            }
            j++;
        } while (true);
    }

    int getUpNestedPreScrollRange()
    {
        return getTotalScrollRange();
    }

    boolean hasChildWithInterpolator()
    {
        return mHaveChildWithInterpolator;
    }

    boolean hasScrollableChildren()
    {
        return getTotalScrollRange() != 0;
    }

    protected int[] onCreateDrawableState(int i)
    {
        int ai[] = mTmpStatesArray;
        int ai1[] = super.onCreateDrawableState(ai.length + i);
        if (mCollapsible)
        {
            i = android.support.design.R.attr.state_collapsible;
        } else
        {
            i = -android.support.design.R.attr.state_collapsible;
        }
        ai[0] = i;
        if (mCollapsible && mCollapsed)
        {
            i = android.support.design.R.attr.state_collapsed;
        } else
        {
            i = -android.support.design.R.attr.state_collapsed;
        }
        ai[1] = i;
        return mergeDrawableStates(ai1, ai);
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        invalidateScrollRanges();
        mHaveChildWithInterpolator = false;
        i = 0;
        j = getChildCount();
        do
        {
label0:
            {
                if (i < j)
                {
                    if (((LayoutParams)getChildAt(i).getLayoutParams()).getScrollInterpolator() == null)
                    {
                        break label0;
                    }
                    mHaveChildWithInterpolator = true;
                }
                updateCollapsible();
                return;
            }
            i++;
        } while (true);
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        invalidateScrollRanges();
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
            invalidateScrollRanges();
        }
        return windowinsetscompat;
    }

    public void removeOnOffsetChangedListener(OnOffsetChangedListener onoffsetchangedlistener)
    {
        if (mListeners != null && onoffsetchangedlistener != null)
        {
            mListeners.remove(onoffsetchangedlistener);
        }
    }

    void resetPendingAction()
    {
        mPendingAction = 0;
    }

    boolean setCollapsedState(boolean flag)
    {
        if (mCollapsed != flag)
        {
            mCollapsed = flag;
            refreshDrawableState();
            return true;
        } else
        {
            return false;
        }
    }

    public void setExpanded(boolean flag)
    {
        setExpanded(flag, ViewCompat.isLaidOut(this));
    }

    public void setExpanded(boolean flag, boolean flag1)
    {
        setExpanded(flag, flag1, true);
    }

    public void setOrientation(int i)
    {
        if (i != 1)
        {
            throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
        } else
        {
            super.setOrientation(i);
            return;
        }
    }

    public void setTargetElevation(float f)
    {
        if (android.os.Build.VERSION.SDK_INT >= 21)
        {
            ViewUtilsLollipop.setDefaultAppBarLayoutStateListAnimator(this, f);
        }
    }
}
