// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.OverScroller;

// Referenced classes of package android.support.design.widget:
//            HeaderBehavior, CoordinatorLayout

private class mLayout
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

    (CoordinatorLayout coordinatorlayout, View view)
    {
        this$0 = HeaderBehavior.this;
        super();
        mParent = coordinatorlayout;
        mLayout = view;
    }
}
