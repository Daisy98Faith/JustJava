// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget.helper;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import java.util.List;

// Referenced classes of package android.support.v7.widget.helper:
//            ItemTouchHelper

class this._cls0
    implements android.support.v7.widget.mTouchListener
{

    final ItemTouchHelper this$0;

    public boolean onInterceptTouchEvent(RecyclerView recyclerview, MotionEvent motionevent)
    {
        mGestureDetector.onTouchEvent(motionevent);
        int i = motionevent.getActionMasked();
        if (i == 0)
        {
            mActivePointerId = motionevent.getPointerId(0);
            mInitialTouchX = motionevent.getX();
            mInitialTouchY = motionevent.getY();
            obtainVelocityTracker();
            if (mSelected == null)
            {
                recyclerview = findAnimation(motionevent);
                if (recyclerview != null)
                {
                    ItemTouchHelper itemtouchhelper = ItemTouchHelper.this;
                    itemtouchhelper.mInitialTouchX = itemtouchhelper.mInitialTouchX - ((coverAnimation) (recyclerview)).mX;
                    itemtouchhelper = ItemTouchHelper.this;
                    itemtouchhelper.mInitialTouchY = itemtouchhelper.mInitialTouchY - ((coverAnimation) (recyclerview)).mY;
                    endRecoverAnimation(((coverAnimation) (recyclerview)).mViewHolder, true);
                    if (mPendingCleanup.remove(((coverAnimation) (recyclerview)).mViewHolder.itemView))
                    {
                        mCallback.clearView(mRecyclerView, ((coverAnimation) (recyclerview)).mViewHolder);
                    }
                    select(((coverAnimation) (recyclerview)).mViewHolder, ((coverAnimation) (recyclerview)).mActionState);
                    updateDxDy(motionevent, mSelectedFlags, 0);
                }
            }
        } else
        if (i == 3 || i == 1)
        {
            mActivePointerId = -1;
            select(null, 0);
        } else
        if (mActivePointerId != -1)
        {
            int j = motionevent.findPointerIndex(mActivePointerId);
            if (j >= 0)
            {
                checkSelectForSwipe(i, motionevent, j);
            }
        }
        if (mVelocityTracker != null)
        {
            mVelocityTracker.addMovement(motionevent);
        }
        return mSelected != null;
    }

    public void onRequestDisallowInterceptTouchEvent(boolean flag)
    {
        if (!flag)
        {
            return;
        } else
        {
            select(null, 0);
            return;
        }
    }

    public void onTouchEvent(RecyclerView recyclerview, MotionEvent motionevent)
    {
        int i;
        i = 0;
        mGestureDetector.onTouchEvent(motionevent);
        if (mVelocityTracker != null)
        {
            mVelocityTracker.addMovement(motionevent);
        }
        if (mActivePointerId != -1) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int j;
        int k;
        j = motionevent.getActionMasked();
        k = motionevent.findPointerIndex(mActivePointerId);
        if (k >= 0)
        {
            checkSelectForSwipe(j, motionevent, k);
        }
        recyclerview = mSelected;
        if (recyclerview == null) goto _L1; else goto _L3
_L3:
        j;
        JVM INSTR tableswitch 1 6: default 136
    //                   1 137
    //                   2 155
    //                   3 226
    //                   4 136
    //                   5 136
    //                   6 249;
           goto _L4 _L5 _L6 _L7 _L4 _L4 _L8
_L4:
        return;
_L5:
        select(null, 0);
        mActivePointerId = -1;
        return;
_L6:
        if (k >= 0)
        {
            updateDxDy(motionevent, mSelectedFlags, k);
            moveIfNecessary(recyclerview);
            mRecyclerView.removeCallbacks(mScrollRunnable);
            mScrollRunnable.run();
            mRecyclerView.invalidate();
            return;
        }
        break; /* Loop/switch isn't completed */
_L7:
        if (mVelocityTracker != null)
        {
            mVelocityTracker.clear();
        }
        if (true) goto _L5; else goto _L9
_L9:
        break; /* Loop/switch isn't completed */
_L8:
        j = motionevent.getActionIndex();
        continue; /* Loop/switch isn't completed */
        if (true) goto _L1; else goto _L10
_L10:
        if (motionevent.getPointerId(j) != mActivePointerId) goto _L1; else goto _L11
_L11:
        if (j == 0)
        {
            i = 1;
        }
        mActivePointerId = motionevent.getPointerId(i);
        updateDxDy(motionevent, mSelectedFlags, j);
        return;
    }

    llback()
    {
        this$0 = ItemTouchHelper.this;
        super();
    }
}
