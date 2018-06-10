// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.view.View;
import java.lang.ref.WeakReference;

// Referenced classes of package android.support.design.widget:
//            BottomSheetBehavior, MathUtils

class nit> extends android.support.v4.widget.k
{

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
            ViewCompat.postOnAnimation(view, new ttleRunnable(BottomSheetBehavior.this, view, i));
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

    ttleRunnable()
    {
        this$0 = BottomSheetBehavior.this;
        super();
    }
}
