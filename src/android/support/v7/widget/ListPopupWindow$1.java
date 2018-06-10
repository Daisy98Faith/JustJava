// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.support.v7.view.menu.ShowableListMenu;
import android.view.View;

// Referenced classes of package android.support.v7.widget:
//            ForwardingListener, ListPopupWindow

class  extends ForwardingListener
{

    final ListPopupWindow this$0;

    public volatile ShowableListMenu getPopup()
    {
        return getPopup();
    }

    public ListPopupWindow getPopup()
    {
        return ListPopupWindow.this;
    }

    u(View view)
    {
        this$0 = ListPopupWindow.this;
        super(view);
    }
}
