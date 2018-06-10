// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;

// Referenced classes of package android.support.design.widget:
//            ViewOffsetBehavior, CoordinatorLayout, MathUtils

abstract class HeaderBehavior extends ViewOffsetBehavior
{
    private class FlingRunnable
        implements Runnable
    {

        private final View mLayout;
        private final CoordinatorLayout mParent;
        final HeaderBehavior this$0;

        public void run()
        {
label0:
            {
                if (mLayout != null && mScroller != null)
                {
                    if (!mScroller.computeScrollOffset())
                    {
                        break label0;
                    }
                    setHeaderTopBottomOffset(mParent, mLayout, mScroller.getCurrY());
                    ViewCompat.postOnAnimation(mLayout, this);
                }
                return;
            }
            onFlingFinished(mParent, mLayout);
        }

        FlingRunnable(CoordinatorLayout coordinatorlayout, View view)
        {
            this$0 = HeaderBehavior.this;
            super();
            mParent = coordinatorlayout;
            mLayout = view;
        }
    }


    private static final int INVALID_POINTER = -1;
    private int mActivePointerId;
    private Runnable mFlingRunnable;
    private boolean mIsBeingDragged;
    private int mLastMotionY;
    OverScroller mScroller;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;

    public HeaderBehavior()
    {
        mActivePointerId = -1;
        mTouchSlop = -1;
    }

    public HeaderBehavior(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mActivePointerId = -1;
        mTouchSlop = -1;
    }

    private void ensureVelocityTracker()
    {
        if (mVelocityTracker == null)
        {
            mVelocityTracker = VelocityTracker.obtain();
        }
    }

    boolean canDragView(View view)
    {
        return false;
    }

    final boolean fling(CoordinatorLayout coordinatorlayout, View view, int i, int j, float f)
    {
        if (mFlingRunnable != null)
        {
            view.removeCallbacks(mFlingRunnable);
            mFlingRunnable = null;
        }
        if (mScroller == null)
        {
            mScroller = new OverScroller(view.getContext());
        }
        mScroller.fling(0, getTopAndBottomOffset(), 0, Math.round(f), 0, 0, i, j);
        if (mScroller.computeScrollOffset())
        {
            mFlingRunnable = new FlingRunnable(coordinatorlayout, view);
            ViewCompat.postOnAnimation(view, mFlingRunnable);
            return true;
        } else
        {
            onFlingFinished(coordinatorlayout, view);
            return false;
        }
    }

    int getMaxDragOffset(View view)
    {
        return -view.getHeight();
    }

    int getScrollRangeForDragFling(View view)
    {
        return view.getHeight();
    }

    int getTopBottomOffsetForScrollingSibling()
    {
        return getTopAndBottomOffset();
    }

    void onFlingFinished(CoordinatorLayout coordinatorlayout, View view)
    {
    }

    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorlayout, View view, MotionEvent motionevent)
    {
        if (mTouchSlop < 0)
        {
            mTouchSlop = ViewConfiguration.get(coordinatorlayout.getContext()).getScaledTouchSlop();
        }
        if (motionevent.getAction() == 2 && mIsBeingDragged)
        {
            return true;
        }
        motionevent.getActionMasked();
        JVM INSTR tableswitch 0 3: default 72
    //                   0 92
    //                   1 219
    //                   2 153
    //                   3 219;
           goto _L1 _L2 _L3 _L4 _L3
_L1:
        if (mVelocityTracker != null)
        {
            mVelocityTracker.addMovement(motionevent);
        }
        return mIsBeingDragged;
_L2:
        mIsBeingDragged = false;
        int i = (int)motionevent.getX();
        int k = (int)motionevent.getY();
        if (canDragView(view) && coordinatorlayout.isPointInChildBounds(view, i, k))
        {
            mLastMotionY = k;
            mActivePointerId = motionevent.getPointerId(0);
            ensureVelocityTracker();
        }
        continue; /* Loop/switch isn't completed */
_L4:
        int j = mActivePointerId;
        if (j != -1)
        {
            j = motionevent.findPointerIndex(j);
            if (j != -1)
            {
                j = (int)motionevent.getY(j);
                if (Math.abs(j - mLastMotionY) > mTouchSlop)
                {
                    mIsBeingDragged = true;
                    mLastMotionY = j;
                }
            }
        }
        continue; /* Loop/switch isn't completed */
_L3:
        mIsBeingDragged = false;
        mActivePointerId = -1;
        if (mVelocityTracker != null)
        {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
        if (true) goto _L1; else goto _L5
_L5:
    }

    public boolean onTouchEvent(CoordinatorLayout coordinatorlayout, View view, MotionEvent motionevent)
    {
        if (mTouchSlop < 0)
        {
            mTouchSlop = ViewConfiguration.get(coordinatorlayout.getContext()).getScaledTouchSlop();
        }
        motionevent.getActionMasked();
        JVM INSTR tableswitch 0 3: default 56
    //                   0 73
    //                   1 256
    //                   2 131
    //                   3 310;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        if (mVelocityTracker != null)
        {
            mVelocityTracker.addMovement(motionevent);
        }
        return true;
_L2:
        int i = (int)motionevent.getX();
        int k = (int)motionevent.getY();
        if (coordinatorlayout.isPointInChildBounds(view, i, k) && canDragView(view))
        {
            mLastMotionY = k;
            mActivePointerId = motionevent.getPointerId(0);
            ensureVelocityTracker();
        } else
        {
            return false;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        int j = motionevent.findPointerIndex(mActivePointerId);
        if (j == -1)
        {
            return false;
        }
        int i1 = (int)motionevent.getY(j);
        int l = mLastMotionY - i1;
        j = l;
        if (!mIsBeingDragged)
        {
            j = l;
            if (Math.abs(l) > mTouchSlop)
            {
                mIsBeingDragged = true;
                if (l > 0)
                {
                    j = l - mTouchSlop;
                } else
                {
                    j = l + mTouchSlop;
                }
            }
        }
        if (mIsBeingDragged)
        {
            mLastMotionY = i1;
            scroll(coordinatorlayout, view, j, getMaxDragOffset(view), 0);
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (mVelocityTracker != null)
        {
            mVelocityTracker.addMovement(motionevent);
            mVelocityTracker.computeCurrentVelocity(1000);
            float f = mVelocityTracker.getYVelocity(mActivePointerId);
            fling(coordinatorlayout, view, -getScrollRangeForDragFling(view), 0, f);
        }
_L5:
        mIsBeingDragged = false;
        mActivePointerId = -1;
        if (mVelocityTracker != null)
        {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
        if (true) goto _L1; else goto _L6
_L6:
    }

    final int scroll(CoordinatorLayout coordinatorlayout, View view, int i, int j, int k)
    {
        return setHeaderTopBottomOffset(coordinatorlayout, view, getTopBottomOffsetForScrollingSibling() - i, j, k);
    }

    int setHeaderTopBottomOffset(CoordinatorLayout coordinatorlayout, View view, int i)
    {
        return setHeaderTopBottomOffset(coordinatorlayout, view, i, 0x80000000, 0x7fffffff);
    }

    int setHeaderTopBottomOffset(CoordinatorLayout coordinatorlayout, View view, int i, int j, int k)
    {
        int i1 = getTopAndBottomOffset();
        boolean flag = false;
        int l = ((flag) ? 1 : 0);
        if (j != 0)
        {
            l = ((flag) ? 1 : 0);
            if (i1 >= j)
            {
                l = ((flag) ? 1 : 0);
                if (i1 <= k)
                {
                    i = MathUtils.constrain(i, j, k);
                    l = ((flag) ? 1 : 0);
                    if (i1 != i)
                    {
                        setTopAndBottomOffset(i);
                        l = i1 - i;
                    }
                }
            }
        }
        return l;
    }
}
