// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.os.Handler;
import android.widget.AbsListView;
import android.widget.PopupWindow;

// Referenced classes of package android.support.v7.widget:
//            ListPopupWindow

private class this._cls0
    implements android.widget.llListener
{

    final ListPopupWindow this$0;

    public void onScroll(AbsListView abslistview, int i, int j, int k)
    {
    }

    public void onScrollStateChanged(AbsListView abslistview, int i)
    {
        if (i == 1 && !isInputMethodNotNeeded() && mPopup.getContentView() != null)
        {
            mHandler.removeCallbacks(mResizePopupRunnable);
            mResizePopupRunnable.run();
        }
    }

    ()
    {
        this$0 = ListPopupWindow.this;
        super();
    }
}
