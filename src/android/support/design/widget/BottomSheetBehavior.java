// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.lang.annotation.Annotation;
import java.lang.ref.WeakReference;

// Referenced classes of package android.support.design.widget:
//            CoordinatorLayout, MathUtils

public class BottomSheetBehavior extends CoordinatorLayout.Behavior
{
    public static abstract class BottomSheetCallback
    {

        public abstract void onSlide(View view, float f);

        public abstract void onStateChanged(View view, int i);

        public BottomSheetCallback()
        {
        }
    }

    protected static class SavedState extends AbsSavedState
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
        final int state;

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(state);
        }


        public SavedState(Parcel parcel)
        {
            this(parcel, ((ClassLoader) (null)));
        }

        public SavedState(Parcel parcel, ClassLoader classloader)
        {
            super(parcel, classloader);
            state = parcel.readInt();
        }

        public SavedState(Parcelable parcelable, int i)
        {
            super(parcelable);
            state = i;
        }
    }

    private class SettleRunnable
        implements Runnable
    {

        private final int mTargetState;
        private final View mView;
        final BottomSheetBehavior this$0;

        public void run()
        {
            if (mViewDragHelper != null && mViewDragHelper.continueSettling(true))
            {
                ViewCompat.postOnAnimation(mView, this);
                return;
            } else
            {
                setStateInternal(mTargetState);
                return;
            }
        }

        SettleRunnable(View view, int i)
        {
            this$0 = BottomSheetBehavior.this;
            super();
            mView = view;
            mTargetState = i;
        }
    }

    public static interface State
        extends Annotation
    {
    }


    private static final float HIDE_FRICTION = 0.1F;
    private static final float HIDE_THRESHOLD = 0.5F;
    public static final int PEEK_HEIGHT_AUTO = -1;
    public static final int STATE_COLLAPSED = 4;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_EXPANDED = 3;
    public static final int STATE_HIDDEN = 5;
    public static final int STATE_SETTLING = 2;
    int mActivePointerId;
    private BottomSheetCallback mCallback;
    private final android.support.v4.widget.ViewDragHelper.Callback mDragCallback;
    boolean mHideable;
    private boolean mIgnoreEvents;
    private int mInitialY;
    private int mLastNestedScrollDy;
    int mMaxOffset;
    private float mMaximumVelocity;
    int mMinOffset;
    private boolean mNestedScrolled;
    WeakReference mNestedScrollingChildRef;
    int mParentHeight;
    private int mPeekHeight;
    private boolean mPeekHeightAuto;
    private int mPeekHeightMin;
    private boolean mSkipCollapsed;
    int mState;
    boolean mTouchingScrollingChild;
    private VelocityTracker mVelocityTracker;
    ViewDragHelper mViewDragHelper;
    WeakReference mViewRef;

    public BottomSheetBehavior()
    {
        mState = 4;
        mDragCallback = new android.support.v4.widget.ViewDragHelper.Callback() {

            final BottomSheetBehavior this$0;

            public int clampViewPositionHorizontal(View view, int i, int j)
            {
                return view.getLeft();
            }

            public int clampViewPositionVertical(View view, int i, int j)
            {
                int k = mMinOffset;
                if (mHideable)
                {
                    j = mParentHeight;
                } else
                {
                    j = mMaxOffset;
                }
                return MathUtils.constrain(i, k, j);
            }

            public int getViewVerticalDragRange(View view)
            {
                if (mHideable)
                {
                    return mParentHeight - mMinOffset;
                } else
                {
                    return mMaxOffset - mMinOffset;
                }
            }

            public void onViewDragStateChanged(int i)
            {
                if (i == 1)
                {
                    setStateInternal(1);
                }
            }

            public void onViewPositionChanged(View view, int i, int j, int k, int l)
            {
                dispatchOnSlide(j);
            }

            public void onViewReleased(View view, float f, float f1)
            {
                int i;
                int j;
                if (f1 < 0.0F)
                {
                    j = mMinOffset;
                    i = 3;
                } else
                if (mHideable && shouldHide(view, f1))
                {
                    j = mParentHeight;
                    i = 5;
                } else
                if (f1 == 0.0F)
                {
                    i = view.getTop();
                    if (Math.abs(i - mMinOffset) < Math.abs(i - mMaxOffset))
                    {
                        j = mMinOffset;
                        i = 3;
                    } else
                    {
                        j = mMaxOffset;
                        i = 4;
                    }
                } else
                {
                    j = mMaxOffset;
                    i = 4;
                }
                if (mViewDragHelper.settleCapturedViewAt(view.getLeft(), j))
                {
                    setStateInternal(2);
                    ViewCompat.postOnAnimation(view, new SettleRunnable(view, i));
                    return;
                } else
                {
                    setStateInternal(i);
                    return;
                }
            }

            public boolean tryCaptureView(View view, int i)
            {
                boolean flag;
                flag = true;
                break MISSING_BLOCK_LABEL_3;
                View view1;
                for (; view1 != null && ViewCompat.canScrollVertically(view1, -1); view1 = (View)mNestedScrollingChildRef.get())
                {
                    do
                    {
                        return false;
                    } while (mState == 1 || mTouchingScrollingChild);
                    if (mState != 3 || mActivePointerId != i)
                    {
                        break;
                    }
                }

                if (mViewRef == null || mViewRef.get() != view)
                {
                    flag = false;
                }
                return flag;
            }

            
            {
                this$0 = BottomSheetBehavior.this;
                super();
            }
        };
    }

    public BottomSheetBehavior(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mState = 4;
        mDragCallback = new _cls2();
        attributeset = context.obtainStyledAttributes(attributeset, android.support.design.R.styleable.BottomSheetBehavior_Layout);
        TypedValue typedvalue = attributeset.peekValue(android.support.design.R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight);
        if (typedvalue != null && typedvalue.data == -1)
        {
            setPeekHeight(typedvalue.data);
        } else
        {
            setPeekHeight(attributeset.getDimensionPixelSize(android.support.design.R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight, -1));
        }
        setHideable(attributeset.getBoolean(android.support.design.R.styleable.BottomSheetBehavior_Layout_behavior_hideable, false));
        setSkipCollapsed(attributeset.getBoolean(android.support.design.R.styleable.BottomSheetBehavior_Layout_behavior_skipCollapsed, false));
        attributeset.recycle();
        mMaximumVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }

    public static BottomSheetBehavior from(View view)
    {
        view = view.getLayoutParams();
        if (!(view instanceof CoordinatorLayout.LayoutParams))
        {
            throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
        }
        view = ((CoordinatorLayout.LayoutParams)view).getBehavior();
        if (!(view instanceof BottomSheetBehavior))
        {
            throw new IllegalArgumentException("The view is not associated with BottomSheetBehavior");
        } else
        {
            return (BottomSheetBehavior)view;
        }
    }

    private float getYVelocity()
    {
        mVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
        return mVelocityTracker.getYVelocity(mActivePointerId);
    }

    private void reset()
    {
        mActivePointerId = -1;
        if (mVelocityTracker != null)
        {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    void dispatchOnSlide(int i)
    {
        View view;
label0:
        {
            view = (View)mViewRef.get();
            if (view != null && mCallback != null)
            {
                if (i <= mMaxOffset)
                {
                    break label0;
                }
                mCallback.onSlide(view, (float)(mMaxOffset - i) / (float)(mParentHeight - mMaxOffset));
            }
            return;
        }
        mCallback.onSlide(view, (float)(mMaxOffset - i) / (float)(mMaxOffset - mMinOffset));
    }

    View findScrollingChild(View view)
    {
        if (ViewCompat.isNestedScrollingEnabled(view))
        {
            return view;
        }
        if (view instanceof ViewGroup)
        {
            view = (ViewGroup)view;
            int i = 0;
            for (int j = view.getChildCount(); i < j; i++)
            {
                View view1 = findScrollingChild(view.getChildAt(i));
                if (view1 != null)
                {
                    return view1;
                }
            }

        }
        return null;
    }

    public final int getPeekHeight()
    {
        if (mPeekHeightAuto)
        {
            return -1;
        } else
        {
            return mPeekHeight;
        }
    }

    int getPeekHeightMin()
    {
        return mPeekHeightMin;
    }

    public boolean getSkipCollapsed()
    {
        return mSkipCollapsed;
    }

    public final int getState()
    {
        return mState;
    }

    public boolean isHideable()
    {
        return mHideable;
    }

    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorlayout, View view, MotionEvent motionevent)
    {
        int i;
        boolean flag2;
        flag2 = true;
        if (!view.isShown())
        {
            mIgnoreEvents = true;
            return false;
        }
        i = motionevent.getActionMasked();
        if (i == 0)
        {
            reset();
        }
        if (mVelocityTracker == null)
        {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(motionevent);
        i;
        JVM INSTR tableswitch 0 3: default 88
    //                   0 132
    //                   1 108
    //                   2 88
    //                   3 108;
           goto _L1 _L2 _L3 _L1 _L3
_L1:
        if (!mIgnoreEvents && mViewDragHelper.shouldInterceptTouchEvent(motionevent))
        {
            return true;
        }
        break; /* Loop/switch isn't completed */
_L3:
        mTouchingScrollingChild = false;
        mActivePointerId = -1;
        if (mIgnoreEvents)
        {
            mIgnoreEvents = false;
            return false;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        int j = (int)motionevent.getX();
        mInitialY = (int)motionevent.getY();
        View view1;
        boolean flag;
        if (mNestedScrollingChildRef != null)
        {
            view1 = (View)mNestedScrollingChildRef.get();
        } else
        {
            view1 = null;
        }
        if (view1 != null && coordinatorlayout.isPointInChildBounds(view1, j, mInitialY))
        {
            mActivePointerId = motionevent.getPointerId(motionevent.getActionIndex());
            mTouchingScrollingChild = true;
        }
        if (mActivePointerId == -1 && !coordinatorlayout.isPointInChildBounds(view, j, mInitialY))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        mIgnoreEvents = flag;
        if (true) goto _L1; else goto _L4
_L4:
        view = (View)mNestedScrollingChildRef.get();
        boolean flag1;
        if (i == 2 && view != null && !mIgnoreEvents && mState != 1 && !coordinatorlayout.isPointInChildBounds(view, (int)motionevent.getX(), (int)motionevent.getY()) && Math.abs((float)mInitialY - motionevent.getY()) > (float)mViewDragHelper.getTouchSlop())
        {
            flag1 = flag2;
        } else
        {
            flag1 = false;
        }
        return flag1;
    }

    public boolean onLayoutChild(CoordinatorLayout coordinatorlayout, View view, int i)
    {
        int j;
        if (ViewCompat.getFitsSystemWindows(coordinatorlayout) && !ViewCompat.getFitsSystemWindows(view))
        {
            ViewCompat.setFitsSystemWindows(view, true);
        }
        j = view.getTop();
        coordinatorlayout.onLayoutChild(view, i);
        mParentHeight = coordinatorlayout.getHeight();
        if (mPeekHeightAuto)
        {
            if (mPeekHeightMin == 0)
            {
                mPeekHeightMin = coordinatorlayout.getResources().getDimensionPixelSize(android.support.design.R.dimen.design_bottom_sheet_peek_height_min);
            }
            i = Math.max(mPeekHeightMin, mParentHeight - (coordinatorlayout.getWidth() * 9) / 16);
        } else
        {
            i = mPeekHeight;
        }
        mMinOffset = Math.max(0, mParentHeight - view.getHeight());
        mMaxOffset = Math.max(mParentHeight - i, mMinOffset);
        if (mState != 3) goto _L2; else goto _L1
_L1:
        ViewCompat.offsetTopAndBottom(view, mMinOffset);
_L4:
        if (mViewDragHelper == null)
        {
            mViewDragHelper = ViewDragHelper.create(coordinatorlayout, mDragCallback);
        }
        mViewRef = new WeakReference(view);
        mNestedScrollingChildRef = new WeakReference(findScrollingChild(view));
        return true;
_L2:
        if (mHideable && mState == 5)
        {
            ViewCompat.offsetTopAndBottom(view, mParentHeight);
        } else
        if (mState == 4)
        {
            ViewCompat.offsetTopAndBottom(view, mMaxOffset);
        } else
        if (mState == 1 || mState == 2)
        {
            ViewCompat.offsetTopAndBottom(view, j - view.getTop());
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public boolean onNestedPreFling(CoordinatorLayout coordinatorlayout, View view, View view1, float f, float f1)
    {
        return view1 == mNestedScrollingChildRef.get() && (mState != 3 || super.onNestedPreFling(coordinatorlayout, view, view1, f, f1));
    }

    public void onNestedPreScroll(CoordinatorLayout coordinatorlayout, View view, View view1, int i, int j, int ai[])
    {
        int k;
        if (view1 != (View)mNestedScrollingChildRef.get())
        {
            return;
        }
        i = view.getTop();
        k = i - j;
        if (j <= 0) goto _L2; else goto _L1
_L1:
        if (k < mMinOffset)
        {
            ai[1] = i - mMinOffset;
            ViewCompat.offsetTopAndBottom(view, -ai[1]);
            setStateInternal(3);
        } else
        {
            ai[1] = j;
            ViewCompat.offsetTopAndBottom(view, -j);
            setStateInternal(1);
        }
_L4:
        dispatchOnSlide(view.getTop());
        mLastNestedScrollDy = j;
        mNestedScrolled = true;
        return;
_L2:
        if (j < 0 && !ViewCompat.canScrollVertically(view1, -1))
        {
            if (k <= mMaxOffset || mHideable)
            {
                ai[1] = j;
                ViewCompat.offsetTopAndBottom(view, -j);
                setStateInternal(1);
            } else
            {
                ai[1] = i - mMaxOffset;
                ViewCompat.offsetTopAndBottom(view, -ai[1]);
                setStateInternal(4);
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void onRestoreInstanceState(CoordinatorLayout coordinatorlayout, View view, Parcelable parcelable)
    {
        parcelable = (SavedState)parcelable;
        super.onRestoreInstanceState(coordinatorlayout, view, parcelable.getSuperState());
        if (((SavedState) (parcelable)).state == 1 || ((SavedState) (parcelable)).state == 2)
        {
            mState = 4;
            return;
        } else
        {
            mState = ((SavedState) (parcelable)).state;
            return;
        }
    }

    public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorlayout, View view)
    {
        return new SavedState(super.onSaveInstanceState(coordinatorlayout, view), mState);
    }

    public boolean onStartNestedScroll(CoordinatorLayout coordinatorlayout, View view, View view1, View view2, int i)
    {
        boolean flag = false;
        mLastNestedScrollDy = 0;
        mNestedScrolled = false;
        if ((i & 2) != 0)
        {
            flag = true;
        }
        return flag;
    }

    public void onStopNestedScroll(CoordinatorLayout coordinatorlayout, View view, View view1)
    {
        if (view.getTop() == mMinOffset)
        {
            setStateInternal(3);
        } else
        if (view1 == mNestedScrollingChildRef.get() && mNestedScrolled)
        {
            int i;
            int j;
            if (mLastNestedScrollDy > 0)
            {
                j = mMinOffset;
                i = 3;
            } else
            if (mHideable && shouldHide(view, getYVelocity()))
            {
                j = mParentHeight;
                i = 5;
            } else
            if (mLastNestedScrollDy == 0)
            {
                i = view.getTop();
                if (Math.abs(i - mMinOffset) < Math.abs(i - mMaxOffset))
                {
                    j = mMinOffset;
                    i = 3;
                } else
                {
                    j = mMaxOffset;
                    i = 4;
                }
            } else
            {
                j = mMaxOffset;
                i = 4;
            }
            if (mViewDragHelper.smoothSlideViewTo(view, view.getLeft(), j))
            {
                setStateInternal(2);
                ViewCompat.postOnAnimation(view, new SettleRunnable(view, i));
            } else
            {
                setStateInternal(i);
            }
            mNestedScrolled = false;
            return;
        }
    }

    public boolean onTouchEvent(CoordinatorLayout coordinatorlayout, View view, MotionEvent motionevent)
    {
        boolean flag1 = true;
        if (view.isShown()) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        int i;
        i = motionevent.getActionMasked();
        if (mState != 1)
        {
            break; /* Loop/switch isn't completed */
        }
        flag = flag1;
        if (i == 0) goto _L4; else goto _L3
_L3:
        mViewDragHelper.processTouchEvent(motionevent);
        if (i == 0)
        {
            reset();
        }
        if (mVelocityTracker == null)
        {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(motionevent);
        if (i == 2 && !mIgnoreEvents && Math.abs((float)mInitialY - motionevent.getY()) > (float)mViewDragHelper.getTouchSlop())
        {
            mViewDragHelper.captureChildView(view, motionevent.getPointerId(motionevent.getActionIndex()));
        }
        flag = flag1;
        if (mIgnoreEvents)
        {
            return false;
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    public void setBottomSheetCallback(BottomSheetCallback bottomsheetcallback)
    {
        mCallback = bottomsheetcallback;
    }

    public void setHideable(boolean flag)
    {
        mHideable = flag;
    }

    public final void setPeekHeight(int i)
    {
        boolean flag = false;
        if (i != -1) goto _L2; else goto _L1
_L1:
        if (!mPeekHeightAuto)
        {
            mPeekHeightAuto = true;
            flag = true;
        }
_L4:
        if (flag && mState == 4 && mViewRef != null)
        {
            View view = (View)mViewRef.get();
            if (view != null)
            {
                view.requestLayout();
            }
        }
        return;
_L2:
        if (mPeekHeightAuto || mPeekHeight != i)
        {
            mPeekHeightAuto = false;
            mPeekHeight = Math.max(0, i);
            mMaxOffset = mParentHeight - i;
            flag = true;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void setSkipCollapsed(boolean flag)
    {
        mSkipCollapsed = flag;
    }

    public final void setState(final int state)
    {
        if (state != mState) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (mViewRef != null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (state == 4 || state == 3 || mHideable && state == 5)
        {
            mState = state;
            return;
        }
        if (true) goto _L1; else goto _L3
_L3:
        final View child = (View)mViewRef.get();
        if (child != null)
        {
            ViewParent viewparent = child.getParent();
            if (viewparent != null && viewparent.isLayoutRequested() && ViewCompat.isAttachedToWindow(child))
            {
                child.post(new Runnable() {

                    final BottomSheetBehavior this$0;
                    final View val$child;
                    final int val$state;

                    public void run()
                    {
                        startSettlingAnimation(child, state);
                    }

            
            {
                this$0 = BottomSheetBehavior.this;
                child = view;
                state = i;
                super();
            }
                });
                return;
            } else
            {
                startSettlingAnimation(child, state);
                return;
            }
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    void setStateInternal(int i)
    {
        if (mState != i)
        {
            mState = i;
            View view = (View)mViewRef.get();
            if (view != null && mCallback != null)
            {
                mCallback.onStateChanged(view, i);
                return;
            }
        }
    }

    boolean shouldHide(View view, float f)
    {
        if (!mSkipCollapsed)
        {
            if (view.getTop() < mMaxOffset)
            {
                return false;
            }
            if (Math.abs(((float)view.getTop() + 0.1F * f) - (float)mMaxOffset) / (float)mPeekHeight <= 0.5F)
            {
                return false;
            }
        }
        return true;
    }

    void startSettlingAnimation(View view, int i)
    {
        int j;
        if (i == 4)
        {
            j = mMaxOffset;
        } else
        if (i == 3)
        {
            j = mMinOffset;
        } else
        if (mHideable && i == 5)
        {
            j = mParentHeight;
        } else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Illegal state argument: ").append(i).toString());
        }
        if (mViewDragHelper.smoothSlideViewTo(view, view.getLeft(), j))
        {
            setStateInternal(2);
            ViewCompat.postOnAnimation(view, new SettleRunnable(view, i));
            return;
        } else
        {
            setStateInternal(i);
            return;
        }
    }
}
