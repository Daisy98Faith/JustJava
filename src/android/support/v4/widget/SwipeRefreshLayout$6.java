// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.view.animation.Animation;
import android.view.animation.Transformation;

// Referenced classes of package android.support.v4.widget:
//            SwipeRefreshLayout, CircleImageView, MaterialProgressDrawable

class this._cls0 extends Animation
{

    final SwipeRefreshLayout this$0;

    public void applyTransformation(float f, Transformation transformation)
    {
        int i;
        int j;
        int k;
        if (!mUsingCustomStart)
        {
            i = mSpinnerOffsetEnd - Math.abs(mOriginalOffsetTop);
        } else
        {
            i = mSpinnerOffsetEnd;
        }
        j = mFrom;
        i = (int)((float)(i - mFrom) * f);
        k = mCircleView.getTop();
        setTargetOffsetTopAndBottom((j + i) - k);
        mProgress.setArrowScale(1.0F - f);
    }

    ble()
    {
        this$0 = SwipeRefreshLayout.this;
        super();
    }
}
