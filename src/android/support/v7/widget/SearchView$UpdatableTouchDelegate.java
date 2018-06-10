// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewConfiguration;

// Referenced classes of package android.support.v7.widget:
//            SearchView

private static class mDelegateView extends TouchDelegate
{

    private final Rect mActualBounds = new Rect();
    private boolean mDelegateTargeted;
    private final View mDelegateView;
    private final int mSlop;
    private final Rect mSlopBounds = new Rect();
    private final Rect mTargetBounds = new Rect();

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        boolean flag1;
        int i;
        int j;
        boolean flag2;
        boolean flag3;
        i = (int)motionevent.getX();
        j = (int)motionevent.getY();
        flag2 = false;
        flag1 = true;
        flag3 = false;
        motionevent.getAction();
        JVM INSTR tableswitch 0 3: default 56
    //                   0 118
    //                   1 147
    //                   2 147
    //                   3 193;
           goto _L1 _L2 _L3 _L3 _L4
_L4:
        break MISSING_BLOCK_LABEL_193;
_L1:
        boolean flag = flag1;
_L5:
        if (flag2)
        {
            boolean flag4;
            if (flag && !mActualBounds.contains(i, j))
            {
                motionevent.setLocation(mDelegateView.getWidth() / 2, mDelegateView.getHeight() / 2);
            } else
            {
                motionevent.setLocation(i - mActualBounds.left, j - mActualBounds.top);
            }
            flag3 = mDelegateView.dispatchTouchEvent(motionevent);
        }
        return flag3;
_L2:
        flag = flag1;
        if (mTargetBounds.contains(i, j))
        {
            mDelegateTargeted = true;
            flag2 = true;
            flag = flag1;
        }
          goto _L5
_L3:
        flag4 = mDelegateTargeted;
        flag = flag1;
        flag2 = flag4;
        if (flag4)
        {
            flag = flag1;
            flag2 = flag4;
            if (!mSlopBounds.contains(i, j))
            {
                flag = false;
                flag2 = flag4;
            }
        }
          goto _L5
        flag2 = mDelegateTargeted;
        mDelegateTargeted = false;
        flag = flag1;
          goto _L5
    }

    public void setBounds(Rect rect, Rect rect1)
    {
        mTargetBounds.set(rect);
        mSlopBounds.set(rect);
        mSlopBounds.inset(-mSlop, -mSlop);
        mActualBounds.set(rect1);
    }

    public A(Rect rect, Rect rect1, View view)
    {
        super(rect, view);
        mSlop = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        setBounds(rect, rect1);
        mDelegateView = view;
    }
}
