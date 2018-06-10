// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.support.v4.view.ViewCompat;
import android.widget.PopupWindow;

// Referenced classes of package android.support.v7.widget:
//            ListPopupWindow, DropDownListView

private class this._cls0
    implements Runnable
{

    final ListPopupWindow this$0;

    public void run()
    {
        if (mDropDownList != null && ViewCompat.isAttachedToWindow(mDropDownList) && mDropDownList.getCount() > mDropDownList.getChildCount() && mDropDownList.getChildCount() <= mListItemExpandMaximum)
        {
            mPopup.setInputMethodMode(2);
            show();
        }
    }

    ()
    {
        this$0 = ListPopupWindow.this;
        super();
    }
}
