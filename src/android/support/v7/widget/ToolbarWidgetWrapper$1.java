// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.support.v7.view.menu.ActionMenuItem;
import android.view.View;

// Referenced classes of package android.support.v7.widget:
//            ToolbarWidgetWrapper, Toolbar

class itle
    implements android.view.getWrapper._cls1
{

    final ActionMenuItem mNavItem;
    final ToolbarWidgetWrapper this$0;

    public void onClick(View view)
    {
        if (mWindowCallback != null && mMenuPrepared)
        {
            mWindowCallback.temSelected(0, mNavItem);
        }
    }

    ()
    {
        this$0 = ToolbarWidgetWrapper.this;
        super();
        mNavItem = new ActionMenuItem(mToolbar.getContext(), 0, 0x102002c, 0, 0, mTitle);
    }
}
