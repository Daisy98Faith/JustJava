// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.view.MotionEvent;
import android.view.View;

// Referenced classes of package android.support.design.widget:
//            SwipeDismissBehavior, BaseTransientBottomBar, CoordinatorLayout, SnackbarManager

final class this._cls0 extends SwipeDismissBehavior
{

    final BaseTransientBottomBar this$0;

    public boolean canSwipeDismissView(View view)
    {
        return view instanceof aseLayout;
    }

    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorlayout, aseLayout aselayout, MotionEvent motionevent)
    {
        motionevent.getActionMasked();
        JVM INSTR tableswitch 0 3: default 36
    //                   0 44
    //                   1 78
    //                   2 36
    //                   3 78;
           goto _L1 _L2 _L3 _L1 _L3
_L1:
        return super.onInterceptTouchEvent(coordinatorlayout, aselayout, motionevent);
_L2:
        if (coordinatorlayout.isPointInChildBounds(aselayout, (int)motionevent.getX(), (int)motionevent.getY()))
        {
            SnackbarManager.getInstance().pauseTimeout(mManagerCallback);
        }
        continue; /* Loop/switch isn't completed */
_L3:
        SnackbarManager.getInstance().restoreTimeoutIfPaused(mManagerCallback);
        if (true) goto _L1; else goto _L4
_L4:
    }

    public volatile boolean onInterceptTouchEvent(CoordinatorLayout coordinatorlayout, View view, MotionEvent motionevent)
    {
        return onInterceptTouchEvent(coordinatorlayout, (aseLayout)view, motionevent);
    }

    aseLayout()
    {
        this$0 = BaseTransientBottomBar.this;
        super();
    }
}
