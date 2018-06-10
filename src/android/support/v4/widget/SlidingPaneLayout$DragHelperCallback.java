// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.view.View;

// Referenced classes of package android.support.v4.widget:
//            SlidingPaneLayout, ViewDragHelper

private class this._cls0 extends this._cls0
{

    final SlidingPaneLayout this$0;

    public int clampViewPositionHorizontal(View view, int i, int j)
    {
        view = (this._cls0)mSlideableView.getLayoutParams();
        if (isLayoutRtlSupport())
        {
            j = getWidth() - (getPaddingRight() + ((this._cls0) (view)).argin + mSlideableView.getWidth());
            int k = mSlideRange;
            return Math.max(Math.min(i, j), j - k);
        } else
        {
            j = getPaddingLeft() + ((argin) (view)).rgin;
            int l = mSlideRange;
            return Math.min(Math.max(i, j), j + l);
        }
    }

    public int clampViewPositionVertical(View view, int i, int j)
    {
        return view.getTop();
    }

    public int getViewHorizontalDragRange(View view)
    {
        return mSlideRange;
    }

    public void onEdgeDragStarted(int i, int j)
    {
        mDragHelper.captureChildView(mSlideableView, j);
    }

    public void onViewCaptured(View view, int i)
    {
        setAllChildrenVisible();
    }

    public void onViewDragStateChanged(int i)
    {
label0:
        {
            if (mDragHelper.getViewDragState() == 0)
            {
                if (mSlideOffset != 0.0F)
                {
                    break label0;
                }
                updateObscuredViewsVisibility(mSlideableView);
                dispatchOnPanelClosed(mSlideableView);
                mPreservedOpenState = false;
            }
            return;
        }
        dispatchOnPanelOpened(mSlideableView);
        mPreservedOpenState = true;
    }

    public void onViewPositionChanged(View view, int i, int j, int k, int l)
    {
        onPanelDragged(i);
        invalidate();
    }

    public void onViewReleased(View view, float f, float f1)
    {
          = ()view.getLayoutParams();
        if (!isLayoutRtlSupport()) goto _L2; else goto _L1
_L1:
        int i;
label0:
        {
            int j = getPaddingRight() + .argin;
            if (f >= 0.0F)
            {
                i = j;
                if (f != 0.0F)
                {
                    break label0;
                }
                i = j;
                if (mSlideOffset <= 0.5F)
                {
                    break label0;
                }
            }
            i = j + mSlideRange;
        }
        int k = mSlideableView.getWidth();
        i = getWidth() - i - k;
_L4:
        mDragHelper.settleCapturedViewAt(i, view.getTop());
        invalidate();
        return;
_L2:
        int l = getPaddingLeft() + .rgin;
        if (f <= 0.0F)
        {
            i = l;
            if (f != 0.0F)
            {
                continue; /* Loop/switch isn't completed */
            }
            i = l;
            if (mSlideOffset <= 0.5F)
            {
                continue; /* Loop/switch isn't completed */
            }
        }
        i = l + mSlideRange;
        if (true) goto _L4; else goto _L3
_L3:
    }

    public boolean tryCaptureView(View view, int i)
    {
        if (mIsUnableToDrag)
        {
            return false;
        } else
        {
            return ((rgin)view.getLayoutParams()).ble;
        }
    }

    ()
    {
        this$0 = SlidingPaneLayout.this;
        super();
    }
}
