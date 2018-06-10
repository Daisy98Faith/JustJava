// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.view.View;

// Referenced classes of package android.support.design.widget:
//            BottomSheetBehavior

private class mTargetState
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

    (View view, int i)
    {
        this$0 = BottomSheetBehavior.this;
        super();
        mView = view;
        mTargetState = i;
    }
}
