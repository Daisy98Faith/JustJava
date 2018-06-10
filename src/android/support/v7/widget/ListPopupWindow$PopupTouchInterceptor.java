// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;

// Referenced classes of package android.support.v7.widget:
//            ListPopupWindow

private class this._cls0
    implements android.view.hInterceptor
{

    final ListPopupWindow this$0;

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        int i;
        int j;
        int k;
        i = motionevent.getAction();
        j = (int)motionevent.getX();
        k = (int)motionevent.getY();
        if (i != 0 || mPopup == null || !mPopup.isShowing() || j < 0 || j >= mPopup.getWidth() || k < 0 || k >= mPopup.getHeight()) goto _L2; else goto _L1
_L1:
        mHandler.postDelayed(mResizePopupRunnable, 250L);
_L4:
        return false;
_L2:
        if (i == 1)
        {
            mHandler.removeCallbacks(mResizePopupRunnable);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    ()
    {
        this$0 = ListPopupWindow.this;
        super();
    }
}
